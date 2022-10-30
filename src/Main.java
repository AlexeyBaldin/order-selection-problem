import model.Dataset;
import permutation.AveragePriceWithCoeffPermutator;
import permutation.Permutator;
import permutation.Permutator1;
import permutation.ProfitSortPermutator;
import solver.OrderSelectionColumn;
import util.Reader;

import java.util.ArrayList;

public class Main {

    static ArrayList<Dataset> datasets = Reader.getDatasets();

    public static void main(String[] args) {
        //findCoeff(-0.5,0.5,0.01);
        //useCoeffAveragePrice(-0.026);
        taskWithCoefficient();

        //Task task1 = new Task(datasets.get(1), new OrderSelectionColumn(), new Permutator1(), 2);


//        Task task2 = new Task(datasets.get(0), new OrderSelectionRecurrent());

    }

    private static void taskWithCoefficient() {
        datasets.forEach(dataset -> {
            Task fullTask = new Task(dataset, new OrderSelectionColumn());
            Task baseTask = new Task(dataset, new OrderSelectionColumn(), new ProfitSortPermutator(), 3);

            Task myTask = new Task(dataset, new OrderSelectionColumn(), new AveragePriceWithCoeffPermutator(), 3,
                    -0.1, 0.1, 0.001);

            double baseQuality = (double) baseTask.getMaxIncome() / fullTask.getMaxIncome();
            double myQuality = (double) myTask.getMaxIncome() / fullTask.getMaxIncome();
            double myProfit = myQuality - baseQuality;

            System.out.println(dataset.getFile());
            System.out.println("   BasePermutation: maxIncome=" + baseTask.getMaxIncome() +
                    " | MyPermutation: maxIncome=" + myTask.getMaxIncome() + ", coefficient=" + myTask.getCoefficient() + " | baseQuality=" +
                    baseQuality + " | myQuality=" + myQuality + " | myProfit=" + myProfit);
        });
    }

    private static void findCoeff(double start, double finish, double step) {

        double oldProfit = 0.0;
        double oldCoeff = 0.0;
        for(double c = start; c <= finish; c += step) {
            double profit = 0.0;
            for (Dataset dataset :
                    datasets) {
                Task fullTask = new Task(dataset, new OrderSelectionColumn());
                Task baseTask = new Task(dataset, new OrderSelectionColumn(), new ProfitSortPermutator(), 3);
                Permutator1 permutator = new Permutator1();
                permutator.setCoefficient(c);
                Task myTask = new Task(dataset, new OrderSelectionColumn(), permutator, 3);

                double baseQuality = (double) baseTask.getMaxIncome() / fullTask.getMaxIncome();
                double myQuality = (double) myTask.getMaxIncome() / fullTask.getMaxIncome();
                profit += myQuality - baseQuality;
            }

            if(profit > oldProfit) {
                oldProfit = profit;
                oldCoeff = c;
            }
            System.out.println(c + "   " + profit);
        }

        System.out.println(oldProfit);
        System.out.println(oldCoeff);
    }

    private static void useCoeffAveragePrice(double coefficient) {
        datasets.forEach(dataset -> {
            Task fullTask = new Task(dataset, new OrderSelectionColumn());
            Task baseTask = new Task(dataset, new OrderSelectionColumn(), new ProfitSortPermutator(), 3);

            AveragePriceWithCoeffPermutator permutator = new AveragePriceWithCoeffPermutator();
            permutator.setCoefficient(coefficient);
            Task myTask = new Task(dataset, new OrderSelectionColumn(), permutator, 3);

            double baseQuality = (double) baseTask.getMaxIncome() / fullTask.getMaxIncome();
            double myQuality = (double) myTask.getMaxIncome() / fullTask.getMaxIncome();
            double myProfit = myQuality - baseQuality;

            System.out.println(dataset.getFile());
            System.out.println("   BasePermutation: maxIncome=" + baseTask.getMaxIncome() +
                    " | MyPermutation: maxIncome=" + myTask.getMaxIncome() + " | baseQuality=" +
                    baseQuality + " | myQuality=" + myQuality + " | myProfit=" + myProfit);
        });
    }
}
