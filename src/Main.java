import model.Dataset;
import permutation.NoChangePermutator;
import solver.OrderSelectionColumn;
import solver.OrderSelectionRecurrent;
import util.Reader;

import java.util.ArrayList;
import java.util.Iterator;

public class Main {

    static ArrayList<Dataset> datasets = Reader.getDatasets();

    public static void main(String[] args) {

        datasets.forEach(dataset -> {
            System.out.println(dataset.getFile());

            Task task = new Task(dataset, new OrderSelectionColumn(), new NoChangePermutator(), 2);

            System.out.println("   " + dataset);
            System.out.println("   " + task.getMaxIncome() + " " + task.getOrders());
        });


//        Task task1 = new Task(datasets.get(0), new OrderSelectionRecurrent());

    }
}
