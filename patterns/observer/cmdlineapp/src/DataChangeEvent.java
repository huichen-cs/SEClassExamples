import java.util.EventObject;

public class DataChangeEvent extends EventObject  {
    public DataChangeEvent(NumberModel model) {
        super(model);
    }    
}
