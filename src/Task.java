import model.CostIncome;
import model.Dataset;
import model.Result;
import permutation.Permutator;
import permutation.PermutatorWithCoefficient;
import solver.OrderSelection;

import java.util.ArrayList;
import java.util.Iterator;

public class Task {

    private final Result result;

    private final double coefficient;


    public Task(Dataset dataset, OrderSelection solver) {
        this.coefficient = 0;
        this.result = solver.orderSelection(dataset.getPerformance(), dataset.getCount(), dataset.getCostIncomes());
        this.result.getOrders().sort((o1, o2) -> o1 - o2);
    }

    public Task(Dataset dataset, OrderSelection solver, Permutator permutator, int part) {
        this.coefficient = 0;
        ArrayList<CostIncome> newOrder = permutator.getNewOrders(dataset.getPerformance(), dataset.getCount(), dataset.getCostIncomes());

        int newCount = dataset.getCount() % part == 0 ? dataset.getCount()/part : dataset.getCount()/part + 1;

        newOrder.subList(newCount, dataset.getCount()).clear();

        this.result = solver.orderSelection(dataset.getPerformance(), newCount, newOrder);
        this.result.getOrders().sort((o1, o2) -> o1 - o2);
    }

    public Task(Dataset dataset, OrderSelection solver, PermutatorWithCoefficient permutator, int part,
                double start, double finish, double step) {

        int newCount = dataset.getCount() % part == 0 ? dataset.getCount()/part : dataset.getCount()/part + 1;

        int maxIncome = 0;
        ArrayList<Integer> orders = new ArrayList<>();

        double coefficient = 0;

        for(double c = start; c <= finish; c += step) {
            permutator.setCoefficient(c);
            ArrayList<CostIncome> newOrder = permutator.getNewOrders(dataset.getPerformance(), dataset.getCount(), dataset.getCostIncomes());

            newOrder.subList(newCount, dataset.getCount()).clear();

            Result result = solver.orderSelection(dataset.getPerformance(), newCount, newOrder);

            if(maxIncome < result.getMaxIncome()) {
                maxIncome = result.getMaxIncome();
                orders = result.getOrders();
                coefficient = c;
            }
            //System.out.println("      " + c + "  " + maxIncome);
        }

        this.result = new Result(maxIncome, orders);
        this.coefficient = coefficient;
    }

    public int getMaxIncome() {
        return result.getMaxIncome();
    }

    public double getCoefficient() {
        return coefficient;
    }

    public ArrayList<Integer> getOrders() {
        return result.getOrders();
    }
}
