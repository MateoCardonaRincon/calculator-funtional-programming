package Calculator;

import java.util.function.IntPredicate;

public class Calculator {

    IntPredicate isZero = y -> y == 0;

    IntPredicate isPositive = y -> y > 0;

    ISum sum = (x, y) -> x + y;

    ISubtraction subtract = (x, y) -> x - y;

    private Integer multiply(Integer n1, Integer n2) {
        return (isZero.test(n1) || isZero.test(n2)) ? 0 : isPositive.test(n2) ?
                sum.calculate(n1, multiply(n1, n2 - 1)) : subtract.calculate(multiply(n1, n2 + 1), n1);
    }

    private Integer divide(Integer n1, Integer n2) {
        if (isZero.test(n2)) {
            throw new ArithmeticException("Error dividing by zero!");
        }
        int signs = (Integer.signum(n1)) * (Integer.signum(n2));
        return isZero.test(Math.abs(n1) - Math.abs(n2)) ?
                signs : signs * sum.calculate(1, divide(subtract.calculate(Math.abs(n1), Math.abs(n2)), Math.abs(n2)));
    }

    public void run() {
        System.out.println("******* Calculator Cases ********");
        System.out.println("3 + 5 = " + sum.calculate(3, 5));
        System.out.println("7 - 9 = " + subtract.calculate(7, 9));
        System.out.println("3 * 5 = " + multiply(3, 5));
        System.out.println("-3 * 2 = " + multiply(-3, 2));
        System.out.println("3 * (-2) = " + multiply(3, -2));
        System.out.println("8 / 4 = " + divide(8, 4));
        System.out.println("9 / (-3) = " + divide(9, -3));
        System.out.println("-10 / 2 = " + divide(-10, 2));
        System.out.println("-12 / (-2) = " + divide(-12, -2));
        System.out.println("8 / 0 = :'C");
        System.out.println(divide(8, 0));
    }

}
