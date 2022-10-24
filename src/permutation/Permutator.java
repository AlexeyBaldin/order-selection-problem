package permutation;

import java.util.ArrayList;

public interface Permutator {
    ArrayList<Integer> getNewOrders(int performance, int count, ArrayList<Integer> cost, ArrayList<Integer> income);
}
