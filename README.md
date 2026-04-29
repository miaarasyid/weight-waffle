# weight-waffle Summary
A Java GUI Program that helped user to calculate their BMI(Body Mass Index) based on their weight's and height's.

# Assignment Instructions
To create a GUI program for a gym to calculate BMI (Body Mass Index) based on customer's height and
weight using the Java Swing components as learned in the semester 2. 

Implement the BMI calculation in a separate method or function. 

The program should take input from the user in **text areas** for height (in meters) and weight (in kilograms). Upon clicking a button, the program should display the calculated BMI, a description of the BMI category, and a suggestion to achieve an ideal BMI. Then it will display the output in a **JFrame** using **JLabels**.

# Added Information
The BMI will be calculated using the formula: BMI = weight (kg) / (height (m) * height (m)).

The BMI based on the following ranges:
|--------------|----------------|----------------|
| BMI          | CLASSIFICATION | HEALTH RISK    |
|--------------|----------------|----------------|
| < 18.5       | Underweight    | Minimal        |
| 18.8 - 24.9  | Normal         | Minimal        |
| 25 - 29.9    | Overweight     | Increased      |
| 30 - 34.9    | Obese          | High           |
| 35 - 39.9    | Severely Obese | Very High      |
| >40          | Morbidly Obese | Extremely High |
|--------------|----------------|----------------|

# Condition
a. if you fall under the "underweight" category, a message box will appear with a note: [ you may need to gain some weight. Consult a nutritionist for a proper diet
plan.]
b. if you fall under "normal weight" category, a message box will appear with a note: [You are within a healthy weight range. Maintain a balanced diet and
regular exercise.]
c. if you fall under "overweight" category, a message box will appear with a note: [Consider incorporating more exercise and a balanced diet to reach a healthier weight.]
d. if you fall under "obesity" category, a message box will appear with a note: [t is important to consult a healthcare professional for guidance on weight
management.]

