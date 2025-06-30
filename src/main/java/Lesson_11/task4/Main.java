package Lesson_11.task4;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        LoanFinder loanFinder = new LoanFinder();
        List<Loan> list = List.of(
                new Loan(10, "123"),
                new Loan(50, "234"),
                new Loan(100, "345"),
                new Loan(20, "456")
        );

        System.out.println(loanFinder.nearestTwoLoans(list, 30));
        System.out.println(loanFinder.nearestTwoLoans(list, 130));
        System.out.println(loanFinder.nearestTwoLoans(list, 140));
    }
}
