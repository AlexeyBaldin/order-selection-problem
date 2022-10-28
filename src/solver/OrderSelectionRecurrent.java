package solver;

import model.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class OrderSelectionRecurrent implements OrderSelection {

    private static class Key {
        private int k;
        private int w;

        public Key(int k, int w) {
            this.k = k;
            this.w = w;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return k == key.k && w == key.w;
        }

        @Override
        public int hashCode() {
            return Objects.hash(k, w);
        }

        @Override
        public String toString() {
            return "Key{" +
                    "k=" + k +
                    ", w=" + w +
                    '}';
        }
    }

    HashMap<Key, CurrentIncomeAndPath> stepsMap = new HashMap<>();
    ArrayList<Integer> cost;
    ArrayList<Integer> income;

    private static class CurrentIncomeAndPath {
        int currentIncome;
        ArrayList<Integer> path;

        public CurrentIncomeAndPath(int currentIncome, ArrayList<Integer> path) {
            this.currentIncome = currentIncome;
            this.path = path;
        }

        @Override
        public String toString() {
            return "CurrentIncomeAndPath{" +
                    "currentIncome=" + currentIncome +
                    ", path=" + path +
                    '}';
        }
    }

    private CurrentIncomeAndPath recursion(int k, int w) {
        if (k == 0) {
            if (cost.get(0) <= w) {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(0);
                return new CurrentIncomeAndPath(income.get(0), temp);
            } else {
                return new CurrentIncomeAndPath(0, new ArrayList<>());
            }
        } else {
            Key noKey = new Key(k-1, w);
            Key yesKey = new Key(k-1, w - cost.get(k));

            CurrentIncomeAndPath noCurrentIncomeAndPath;
            if(stepsMap.containsKey(noKey)) {
                noCurrentIncomeAndPath = stepsMap.get(noKey);
                //noCurrentIncomeAndPath = recursion(k - 1, w);
                //System.out.println("take no");
                System.out.println("qwerty1 " + noKey + " " + noCurrentIncomeAndPath);
            } else {
                noCurrentIncomeAndPath = recursion(k - 1, w);
                stepsMap.put(noKey, noCurrentIncomeAndPath);
            }

            if (cost.get(k) <= w) {
                CurrentIncomeAndPath yesCurrentIncomeAndPath;
                if(stepsMap.containsKey(yesKey)) {
                    yesCurrentIncomeAndPath = stepsMap.get(yesKey);
                    //yesCurrentIncomeAndPath = recursion(k - 1, w - cost.get(k));
                    //yesCurrentIncomeAndPath.currentIncome += income.get(k);
                    //System.out.println("take yes");
                } else {
                    yesCurrentIncomeAndPath = recursion(k - 1, w - cost.get(k));


                    System.out.println("qwerty2 " + yesKey + " " + yesCurrentIncomeAndPath);
                }

                if (yesCurrentIncomeAndPath.currentIncome + income.get(k) > noCurrentIncomeAndPath.currentIncome) {
                    yesCurrentIncomeAndPath.currentIncome += income.get(k);
                    stepsMap.put(yesKey, yesCurrentIncomeAndPath);
                    yesCurrentIncomeAndPath.path.add(k);
                    //System.out.println(yesCurrentIncomeAndPath);
                    return yesCurrentIncomeAndPath;
                } else {
                    return noCurrentIncomeAndPath;
                }
            } else {
                return noCurrentIncomeAndPath;
            }
        }
    }

    @Override
    public Result orderSelection(int performance, int count, ArrayList<Integer> cost, ArrayList<Integer> income) {
        this.cost = cost;
        this.income = income;

        CurrentIncomeAndPath maxIncomeAndPath = recursion(count - 1, performance);

        ArrayList<Integer> resultOrders = new ArrayList<>();
        maxIncomeAndPath.path.forEach(order -> resultOrders.add(order + 1));

        System.out.println(maxIncomeAndPath.currentIncome);
        System.out.println(resultOrders);

        return new Result(maxIncomeAndPath.currentIncome, resultOrders);
    }
}
