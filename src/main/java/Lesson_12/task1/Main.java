package Lesson_12.task1;

public class Main {
    public static void main(String[] args) {

        Calculator calculator = new Calculator();
        int resultSum = calculator.calculate(Integer::sum, 10, 20);
        System.out.println("Сумма чисел " + resultSum);

        int resultSub = calculator.calculate((arg1, arg2) -> arg1 - arg2, 40, 30);
        System.out.println("Вычитание чисел " + resultSub);

        int resultMul = calculator.calculate((arg1, arg2) -> arg1 * arg2, 5, 5);
        System.out.println("Умножение чисел " + resultMul);

        int resultDiv = calculator.calculate((arg1, arg2) -> {
            if (arg2 == 0)
                throw new IllegalArgumentException("Делить на ноль нельзя");
            return arg1 / arg2;
        }, 30, 6);
        System.out.println("Деление чисел " + resultDiv);
        System.out.println("-----------------------");

        int resultSumNew = calculator.calculate(new Operationable() {
            @Override
            public int calculate(int arg1, int arg2) {
                return arg1 + arg2;
            }
        }, 25, 10);
        System.out.println("Сумма чисел " + resultSumNew);

        int resultSubNew = calculator.calculate(new Operationable() {
            @Override
            public int calculate(int arg1, int arg2) {
                return arg1 - arg2;
            }
        }, 25, 5);
        System.out.println("Вычетание чисел " + resultSubNew);

        int resultMulNew = calculator.calculate(new Operationable() {
            @Override
            public int calculate(int arg1, int arg2) {
                return arg1 * arg2;
            }
        }, 7, 7);
        System.out.println("Умножение чисел " + resultMulNew);

        int resultDivNew = calculator.calculate(new Operationable() {
            @Override
            public int calculate(int arg1, int arg2) {
                if (arg2 == 0) {
                    throw new IllegalArgumentException("Делить на ноль нельзя");
                }
                return arg1 / arg2;
            }
        }, 18, 0);
        System.out.println("Деление чисел " + resultDivNew);
    }
}
