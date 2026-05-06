import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Question 1: BMI Calculator GUI
 * Calculates BMI based on height (cm) and weight (kg) input.
 */
public class BMICalculator extends JFrame {

    // Input fields
    private JTextField heightField;
    private JTextField weightField;

    // Output labels
    private JLabel bmiResultLabel;
    private JLabel bmiCategoryLabel;
    private JLabel healthRiskLabel;
    private JLabel suggestionLabel;

    //code for the colours
    private static final Color BLUE       = Color.decode("#1e3231"); // header background
    private static final Color DARK_BLUE  = Color.decode("#8e7c93"); // button background
    private static final Color WHITE      = Color.decode("#ffffff"); // input background
    private static final Color LIGHT_BLUE = Color.decode("#dddedf"); // results background
    private static final Color TEXT_COLOR = Color.decode("#d0a5c0"); // label text
    private static final Font  MAIN_FONT  = new Font("Arial", Font.PLAIN, 14);
    private static final Font  BOLD_FONT  = new Font("Arial", Font.BOLD, 14);

    /**
     * Constructor – builds the GUI.
     */
    public BMICalculator() {
        setTitle("Gym BMI Calculator");
        setSize(520, 490);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 0));
        getContentPane().setBackground(WHITE);

        add(buildTitlePanel(),  BorderLayout.NORTH);
        add(buildInputPanel(),  BorderLayout.CENTER);
        add(buildOutputPanel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    // ------------------------------------------------------------------ panels

    /** Blue header with title + Reset button. */
    private JPanel buildTitlePanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panel.setBackground(BLUE);

        JLabel title = new JLabel("BMI Calculator");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(WHITE);
        panel.add(title);

        JButton resetBtn = makeButton("Reset", WHITE, BLUE);
        resetBtn.addActionListener(e -> resetFields());
        panel.add(resetBtn);

        return panel;
    }

    /** Input area: height, weight, calculate button. */
    private JPanel buildInputPanel() {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(WHITE);
        wrapper.setBorder(new CompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(180, 200, 220)),
                "Enter Your Details",
                TitledBorder.LEFT, TitledBorder.TOP,
                BOLD_FONT, BLUE
            ),
            BorderFactory.createEmptyBorder(8, 12, 12, 12)
        ));

        JPanel grid = new JPanel(new GridLayout(3, 2, 10, 14));
        grid.setBackground(WHITE);

        // Height row
        grid.add(makeLabel("Height (cm):"));
        heightField = makeTextField();
        grid.add(heightField);

        // Weight row
        grid.add(makeLabel("Weight (kg):"));
        weightField = makeTextField();
        grid.add(weightField);

        // Button row
        grid.add(new JLabel()); // spacer
        JButton calcBtn = makeButton("Calculate BMI", WHITE, DARK_BLUE);
        calcBtn.addActionListener(e -> calculateBMI());
        grid.add(calcBtn);

        wrapper.add(grid, BorderLayout.CENTER);
        return wrapper;
    }

    /** Results area: four output labels. */
    private JPanel buildOutputPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 0, 6));
        panel.setBackground(LIGHT_BLUE);
        panel.setBorder(new CompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(180, 200, 220)),
                "Results",
                TitledBorder.LEFT, TitledBorder.TOP,
                BOLD_FONT, BLUE
            ),
            BorderFactory.createEmptyBorder(6, 12, 10, 12)
        ));

        bmiResultLabel   = makeOutputLabel("BMI: -");
        bmiCategoryLabel = makeOutputLabel("Category: -");
        healthRiskLabel  = makeOutputLabel("Health Risk: -");
        suggestionLabel  = makeOutputLabel("Suggestion: -");

        for (JLabel lbl : new JLabel[]{bmiResultLabel, bmiCategoryLabel, healthRiskLabel, suggestionLabel}) {
            panel.add(lbl);
        }
        return panel;
    }

    // ---------------------------------------------------------------- helpers

    /** Creates a styled, readable JLabel. */
    private JLabel makeLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(MAIN_FONT);
        lbl.setForeground(TEXT_COLOR);
        lbl.setOpaque(true);
        lbl.setBackground(WHITE);
        return lbl;
    }

    /** Creates a white, black-text JTextField with a visible border. */
    private JTextField makeTextField() {
        JTextField field = new JTextField();
        field.setFont(MAIN_FONT);
        field.setForeground(TEXT_COLOR);
        field.setBackground(WHITE);
        field.setCaretColor(TEXT_COLOR);
        field.setOpaque(true);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(160, 180, 200)),
            BorderFactory.createEmptyBorder(4, 6, 4, 6)
        ));
        return field;
    }

    /** Creates a styled output JLabel. */
    private JLabel makeOutputLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(MAIN_FONT);
        lbl.setForeground(TEXT_COLOR);
        lbl.setOpaque(true);
        lbl.setBackground(LIGHT_BLUE);
        return lbl;
    }

    /** Creates a fully opaque button with explicit colours. */
    private JButton makeButton(String text, Color fg, Color bg) {
        JButton btn = new JButton(text);
        btn.setFont(BOLD_FONT);
        btn.setForeground(fg);
        btn.setBackground(bg);
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    // ------------------------------------------------------------------ logic

    /**
     * Reads height (cm) and weight (kg), validates, converts, then shows BMI.
     */
    private void calculateBMI() {
        try {
            String heightText = heightField.getText().trim();
            String weightText = weightField.getText().trim();

            if (heightText.isEmpty() || weightText.isEmpty()) {
                showError("Please enter both height and weight.");
                return;
            }

            double heightCm = Double.parseDouble(heightText);
            double weight   = Double.parseDouble(weightText);

            if (heightCm <= 0 || weight <= 0) {
                showError("Height and weight must be positive values.");
                return;
            }
            if (heightCm < 50 || heightCm > 300) {
                showError("Please enter a valid height between 50 and 300 cm.");
                return;
            }

            // Convert cm → metres for the BMI formula
            double heightM    = heightCm / 100.0;
            double bmi        = computeBMI(weight, heightM);
            String category   = getCategory(bmi);
            String healthRisk = getHealthRisk(bmi);
            String suggestion = getSuggestion(bmi);

            bmiResultLabel.setText(String.format("BMI: %.2f", bmi));
            bmiCategoryLabel.setText("Category: " + category);
            healthRiskLabel.setText("Health Risk: " + healthRisk);
            suggestionLabel.setText("<html>Suggestion: " + suggestion + "</html>");

        } catch (NumberFormatException ex) {
            showError("Please enter valid numeric values.");
        }
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Input Error", JOptionPane.WARNING_MESSAGE);
    }

    /** BMI = weight(kg) / height(m)² */
    private double computeBMI(double weight, double heightM) {
        return weight / (heightM * heightM);
    }

    private String getCategory(double bmi) {
        if (bmi < 18.5)      return "Underweight";
        else if (bmi < 25.0) return "Normal";
        else if (bmi < 30.0) return "Overweight";
        else if (bmi < 35.0) return "Obese";
        else if (bmi < 40.0) return "Severely Obese";
        else                 return "Morbidly Obese";
    }

    private String getHealthRisk(double bmi) {
        if (bmi < 18.5)      return "Minimal";
        else if (bmi < 25.0) return "Minimal";
        else if (bmi < 30.0) return "Increased";
        else if (bmi < 35.0) return "High";
        else if (bmi < 40.0) return "Very High";
        else                 return "Extremely High";
    }

    private String getSuggestion(double bmi) {
        if (bmi < 18.5)
            return "You may need to gain some weight. Consult a nutritionist for a proper diet plan.";
        else if (bmi < 25.0)
            return "You are within a healthy weight range. Maintain a balanced diet and regular exercise.";
        else if (bmi < 30.0)
            return "Consider incorporating more exercise and a balanced diet to reach a healthier weight.";
        else
            return "It is important to consult a healthcare professional for guidance on weight management.";
    }

    private void resetFields() {
        heightField.setText("");
        weightField.setText("");
        bmiResultLabel.setText("BMI: -");
        bmiCategoryLabel.setText("Category: -");
        healthRiskLabel.setText("Health Risk: -");
        suggestionLabel.setText("Suggestion: -");
    }

    public static void main(String[] args) {
        // Cross-platform L&F ensures colours are not overridden by the OS theme
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ignored) {}

        SwingUtilities.invokeLater(BMICalculator::new);
    }
}