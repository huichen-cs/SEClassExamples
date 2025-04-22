import java.util.List;
import java.util.LinkedList;

public class NumberModel {
    private List<Double> dataList;
    private List<DataObserver> observers = new LinkedList<>(); // observers

    public NumberModel() {
        dataList = new LinkedList<Double>();
    }

    public double[] getDataAsArray() {
        double[] data = new double[dataList.size()];
        int idx = 0;
        for (double d: dataList) {
            data[idx ++] = d;
        }
        return data;
    }

    public void addObserver(DataObserver observer) {
        observers.add(observer);
    }

    public void removeListener(DataObserver observer) {
        observers.remove(observer);
    }

    public void addNumber(double d) {
        dataList.add(d);
        notifyObservers();
    }

    private void notifyObservers() {
        DataChangeEvent event = new DataChangeEvent(this);
        for (DataObserver observer : observers) {
            observer.onDataChange(event);
        }
    }
}
