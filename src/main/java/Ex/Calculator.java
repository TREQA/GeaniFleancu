package Ex;

public class Calculator {

    public double operand1;
    public double operand2;
    public String operator;

    public void calculate() {
        switch (operator) {
            case "+":
                System.out.println("Result: " + operand1 + operator + operand2);
                break;
            case "-":
                System.out.println("Result: " + (operand1 - operand2));
                break;
            case "*":
                System.out.println("Result: " + operand1 + operand2);
                break;
            case "/":
                System.out.println("Result: " + operand1 / operand2);
                break;
            default:
                System.out.println("Invalid operator");
                break;
        }
        return;
    }


}
