package model;

import java.util.ArrayList;

public class Dataset {

    private final int performance;
    private final int count;
    private final ArrayList<Integer> cost;
    private final ArrayList<Integer> income;

    private final String file;

    public Dataset(int performance, int count, ArrayList<Integer> cost, ArrayList<Integer> income, String file) {
        this.performance = performance;
        this.count = count;
        this.cost = cost;
        this.income = income;
        this.file = file;
    }

    public int getPerformance() {
        return performance;
    }

    public int getCount() {
        return count;
    }

    public ArrayList<Integer> getCost() {
        return cost;
    }

    public ArrayList<Integer> getIncome() {
        return income;
    }

    public String getFile() {
        return file;
    }

    @Override
    public String toString() {
        return "Dataset{" +
                "performance=" + performance +
                ", count=" + count +
                ", cost=" + cost +
                ", income=" + income +
                '}';
    }
}
