import java.util.Arrays;

public class DataMinMaxObserver implements DataObserver {
    @Override
    public void onDataChange(DataChangeEvent event) {
        NumberModel model = (NumberModel)event.getSource();
        NumberViewer.displayMin(Arrays.stream(model.getDataAsArray()).min().getAsDouble());
        NumberViewer.displayMax(Arrays.stream(model.getDataAsArray()).max().getAsDouble());
    }
}