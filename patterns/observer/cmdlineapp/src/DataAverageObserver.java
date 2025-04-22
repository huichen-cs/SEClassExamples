import java.util.Arrays;

public class DataAverageObserver implements DataObserver {
    @Override
    public void onDataChange(DataChangeEvent event) {
        NumberModel model = (NumberModel)event.getSource();
        NumberViewer.displayAverage(Arrays.stream(model.getDataAsArray()).average().getAsDouble());
    }
}