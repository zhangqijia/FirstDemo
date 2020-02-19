package foundationOop.week6;

/*
  A TenQuestions class
  Written by: YG
  First written: 15/09/07
*/

import foundationOop.assignment03.sheffield.EasyReader;

class TenQuestions {
    public static void main(String[] args) {

        // class foundationOop.assignment03.constant
        final int NUMQUESTIONS = 10;

        // class variable
        int correctCount = 0;

        // read from the keyboard
        EasyReader keyboard = new EasyReader();

        // ten questions
        for (int i = 0; i < NUMQUESTIONS; i++) {

            // create questions and the answer
            QandA qa = new QandA();
            int operand1 = qa.getOperand1();
            int operand2 = qa.getOperand2();
            boolean operator = qa.getOperator();
            int correctAnswer = qa.getCorrectAnswer();

            // display the question
            System.out.println("Question " + (i + 1) + ":");
            System.out.print("    " + operand1);
            if (operator) {
                System.out.print("+");
            } else {
                System.out.print("-");
            }
            System.out.print(operand2 + "=? ");

            // read the key input
            int answer = keyboard.readInt();

            // display the outcome
            if (answer == correctAnswer) {
                correctCount++;
                System.out.println("    Correct.");
            } else {
                System.out.println("    Incorrect -- correct answer is "
                        + correctAnswer + ".");
            }
        }

        // summary statement
        System.out.println("You scored " + correctCount + " out of "
                + NUMQUESTIONS + " questions.");

    }
}