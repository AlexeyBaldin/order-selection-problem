package permutation;

import model.CostIncome;

import java.util.ArrayList;

public class Permutator1 implements Permutator {

    private double coefficient = 0.0;

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }
    @Override
    public ArrayList<CostIncome> getNewOrders(int performance, int count, ArrayList<CostIncome> costIncomes) {

        double average = 0.0;
        double dispersion = 0.0;
        for (CostIncome costIncome :
                costIncomes) {
            average += (double) costIncome.getIncome() / costIncome.getCost();
        }
        average /= costIncomes.size();

        ArrayList<CostIncome> left = new ArrayList<>();
        ArrayList<CostIncome> right = new ArrayList<>();

        costIncomes.sort((o1, o2) -> {
            return o2.getIncome() - o1.getIncome();
        });

        for (CostIncome costIncome :
                costIncomes) {
            if((double) costIncome.getIncome() / costIncome.getCost() > average) {
                left.add(costIncome);
            } else {
                right.add(costIncome);
            }
        }

        left.sort((o1, o2) -> Double.compare((double) o1.getIncome() / o1.getCost(), (double) o2.getIncome() / o2.getCost()));
        right.sort((o2, o1) -> Double.compare((double) o1.getIncome() / o1.getCost(), (double) o2.getIncome() / o2.getCost()));

        ArrayList<CostIncome> newOrder = new ArrayList<>(left);
        newOrder.addAll(right);

        return newOrder;
    }
}
