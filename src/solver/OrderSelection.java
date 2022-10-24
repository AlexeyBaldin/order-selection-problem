package solver;

import model.Result;

import java.util.ArrayList;

public interface OrderSelection {
    Result orderSelection(int performance, int count, ArrayList<Integer> cost, ArrayList<Integer> income);
}
