package permutation;

import model.CostIncome;

import java.util.ArrayList;

public interface Permutator {
    ArrayList<CostIncome> getNewOrders(int performance, int count, ArrayList<CostIncome> costIncomes);
}
