package permutation;

import model.CostIncome;

import java.util.ArrayList;
import java.util.Comparator;

public class NoChangePermutator implements Permutator {
    @Override
    public ArrayList<CostIncome> getNewOrders(int performance, int count, ArrayList<CostIncome> costIncomes) {
        return costIncomes;
    }
}
