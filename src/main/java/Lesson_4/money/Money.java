package Lesson_4.money;

class Money {
    public int rub;
    public int penny;

    public Money(int rub, int penny) {

        if (penny > 99) {
            rub += penny / 100;
            penny %= 100;
        }

        this.rub = rub;
        this.penny = penny;
    }

    public int getRub() {
        return rub;
    }

    public int getPenny() {
        return penny;
    }

    public Money plus(Money other) {
        Money sum = new Money(this.rub + other.rub, this.penny + other.penny);
        return sum;
    }

    public Money minus(Money other) {
        if (this.rub - other.rub >= 0) {
            if (this.penny < other.penny) {
                Money minus1 = new Money(this.rub - 1 - other.rub, this.penny + 100 - other.penny);
                return minus1;
            } else {
                Money minus2 = new Money(this.rub - other.rub, this.penny - other.penny);
                return minus2;
            }
        }

        if (this.rub - other.rub < 0) {
            if (this.penny > other.penny) {
                Money minus1 = new Money(this.rub + 1 - other.rub, this.penny - 100 - other.penny);
                return minus1;
            } else {
                Money minus3 = new Money(this.rub - other.rub, this.penny - other.penny);
                return minus3;
            }
        }
        return new Money(0, 0);
    }

    public Money multiply(int multiplyes) {
        Money multiply = new Money(this.rub * multiplyes, this.penny * multiplyes);
        return multiply;
    }

    public void print() {
        System.out.println("Rub " + getRub() + " Penny " + getPenny());
    }
}

