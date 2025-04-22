import java.util.Scanner;

public class NumberCrunchingApp {
    private static final double EXIT_NUMBER = 999999.;
    private static final Scanner in = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        NumberModel model = new NumberModel();
    
        DataObserver[] observerList = {
            new DataSumObserver(),
            new DataAverageObserver(),
            new DataMinMaxObserver()
        };   
        for (DataObserver observer: observerList) {
            model.addObserver(observer);
        }

        while (true) {
            System.out.print("Enter number: ");
            double d = in.nextDouble();
            if (d >= EXIT_NUMBER) break;
            model.addNumber(d);
        }
        System.out.println("Bye!");
    }
}
