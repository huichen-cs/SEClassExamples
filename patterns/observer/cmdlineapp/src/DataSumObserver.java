import java.util.Arrays;

public class DataSumObserver implements DataObserver {
    @Override
    public void onDataChange(DataChangeEvent event) {
        NumberModel model = (NumberModel)event.getSource();
        NumberViewer.displaySum(Arrays.stream(model.getDataAsArray()).sum());
    }
}
