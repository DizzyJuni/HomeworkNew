package Lesson_11.task4;

import java.util.ArrayList;
import java.util.List;

public class LoanFinder {
    public List<Loan> nearestTwoLoans(List<Loan> loans, int requestedSum) {
        List<Loan> result = new ArrayList<>();

        int sum = loans.get(0).getAmount() + loans.get(1).getAmount();
        int difference = Math.abs(requestedSum - sum);

        for (int i = 0; i < loans.size(); i++) {
            for (int j = i + 1; j < loans.size(); j++) {

                int currentSum = loans.get(i).getAmount() + loans.get(j).getAmount();
                int currentDifference = Math.abs(currentSum - requestedSum);

                if (currentDifference <= difference) {
                    difference = currentDifference;
                    result.clear();
                    result.add(loans.get(i));
                    result.add(loans.get(j));
                }
            }
        }
        return result;
    }
}
