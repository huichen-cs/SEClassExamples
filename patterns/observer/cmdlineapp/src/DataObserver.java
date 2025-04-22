import java.util.EventListener;

public interface DataObserver extends EventListener {
    public void onDataChange(DataChangeEvent event);
}
