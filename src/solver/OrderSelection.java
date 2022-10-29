package solver;

import model.CostIncome;
import model.Result;

import java.util.ArrayList;

public interface OrderSelection {
    Result orderSelection(int performance, int count, ArrayList<CostIncome> costIncomes);
}
