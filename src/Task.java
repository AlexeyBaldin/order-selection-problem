import model.Dataset;
import model.Result;
import permutation.Permutator;
import solver.OrderSelection;

import java.util.ArrayList;
import java.util.Iterator;

public class Task {

    private final Result result;

    public Task(Dataset dataset, OrderSelection solver) {
        this.result = solver.orderSelection(dataset.getPerformance(), dataset.getCount(), dataset.getCost(), dataset.getIncome());
    }

    public Task(Dataset dataset, OrderSelection solver, Permutator permutator, int part) {
        ArrayList<Integer> newOrders = permutator.getNewOrders(dataset.getPerformance(), dataset.getCount(), dataset.getCost(), dataset.getIncome());

        ArrayList<Integer> newCost = new ArrayList<>();
        ArrayList<Integer> newIncome = new ArrayList<>();

        int newCount = dataset.getCount() % part == 0 ? dataset.getCount()/part : dataset.getCount()/part + 1;

        Iterator<Integer> iterator = newOrders.iterator();
        for (int i = 0; i < newCount; i++) {
            int index = iterator.next();
            newCost.add(dataset.getCost().get(index));
            newIncome.add(dataset.getIncome().get(index));
        }

        this.result = solver.orderSelection(dataset.getPerformance(), newCount, newCost, newIncome);
    }

    public int getMaxIncome() {
        return result.getMaxIncome();
    }

    public ArrayList<Integer> getOrders() {
        return result.getOrders();
    }
}
