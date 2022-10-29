import model.CostIncome;
import model.Dataset;
import model.Result;
import permutation.Permutator;
import solver.OrderSelection;

import java.util.ArrayList;
import java.util.Iterator;

public class Task {

    private final Result result;

    public Task(Dataset dataset, OrderSelection solver) {
        this.result = solver.orderSelection(dataset.getPerformance(), dataset.getCount(), dataset.getCostIncomes());
        this.result.getOrders().sort((o1, o2) -> o1 - o2);
    }

    public Task(Dataset dataset, OrderSelection solver, Permutator permutator, int part) {
        ArrayList<CostIncome> newOrder = permutator.getNewOrders(dataset.getPerformance(), dataset.getCount(), dataset.getCostIncomes());

        int newCount = dataset.getCount() % part == 0 ? dataset.getCount()/part : dataset.getCount()/part + 1;

        newOrder.subList(newCount, dataset.getCount()).clear();

        this.result = solver.orderSelection(dataset.getPerformance(), newCount, newOrder);
        this.result.getOrders().sort((o1, o2) -> o1 - o2);
    }

    public int getMaxIncome() {
        return result.getMaxIncome();
    }

    public ArrayList<Integer> getOrders() {
        return result.getOrders();
    }
}
