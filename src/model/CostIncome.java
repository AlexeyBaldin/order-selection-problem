package model;

public class CostIncome {

    private final int cost;
    private final int income;

    public CostIncome(int cost, int income) {
        this.cost = cost;
        this.income = income;
    }

    public int getCost() {
        return cost;
    }

    public int getIncome() {
        return income;
    }

    @Override
    public String toString() {
        return "{" +
                "cost=" + cost +
                ", income=" + income +
                '}';
    }
}
