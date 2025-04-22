## Command Line App Demonstrating Observer Pattern

This is a simple command line application that demonstrates the observer pattern
using:
- [java.util.EventListener](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/EventListener.html)
- [java.util.EventObject](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/EventObject.html)

The application prompts the user enter a double value, adds the value to a list, and computes and shows the sum,
the average, the min, and the max values.

The application also follows the Model-View-Controller design pattern:
- Controller: NumberCrunchingApp
- Model: NumberModel, DataObserver
- View: NumberViewer, DataSumObserver, DataMinMaxObserver, DataAverageObserver
