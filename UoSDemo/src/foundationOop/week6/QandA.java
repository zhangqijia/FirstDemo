package foundationOop.week6;

import java.util.Random;

public class QandA {

    // instance variables
    private int operand1;
    private int operand2;
    private boolean operator;
    private int correctAnswer;

    private static Random random = new Random();

    public QandA() {
        operand1 = random.nextInt(21);
        operator = random.nextBoolean();
        if (operator) {
            int bound = 21 - operand1;
            operand2 = random.nextInt(bound);
            correctAnswer = operand1 + operand2;
        } else {
            operand2 = random.nextInt(operand1 + 1);
            correctAnswer = operand1 - operand2;
        }
    }

    public int getOperand1() {
        return operand1;
    }

    public int getOperand2() {
        return operand2;
    }

    public boolean getOperator() {
        return operator;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    // CODE MISSING
}
