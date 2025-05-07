package Lesson_11.task4;

public class Loan {
    private final int amount;
    private final String loanNumber;

    public Loan(int amount, String loanNumber) {
        this.amount = amount;
        this.loanNumber = loanNumber;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "amount=" + amount +
                ", loanNumber='" + loanNumber + '\'' +
                '}';
    }
}
