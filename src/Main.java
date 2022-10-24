import model.Dataset;
import solver.OrderSelectionRecurrent;
import util.Reader;

import java.util.ArrayList;
import java.util.Iterator;

public class Main {

    static ArrayList<Dataset> datasets = Reader.getDatasets();

    public static void main(String[] args) {

        Task task = new Task(datasets.get(1), new OrderSelectionRecurrent());

    }
}
