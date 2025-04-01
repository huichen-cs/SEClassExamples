package edu.cuny.chen.whereismystuff.test;

import android.app.Application;
import android.content.Context;
import androidx.test.runner.AndroidJUnitRunner;
import io.cucumber.android.runner.CucumberAndroidJUnitRunner;

public class HybridTestRunner extends AndroidJUnitRunner {
    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        // Delegate to Cucumber's runner if the test is a Cucumber scenario
        if (isCucumberTest()) {
            return new CucumberAndroidJUnitRunner().newApplication(cl, className, context);
        }
        // Otherwise, use the standard AndroidJUnitRunner
        return super.newApplication(cl, className, context);
    }

    /**
     * Detect whether the current test is a Cucumber scenario.
     * You can customize this logic based on your project structure.
     */
    private boolean isCucumberTest() {
        // Example: Check if the test class has Cucumber annotations
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement element : stackTrace) {
            if (element.getClassName().contains("cucumber")) { // Weak check; improve as needed
                return true;
            }
        }
        return false;
    }

//    private boolean isCucumberTest() {
//        try {
//            Class<?> testClass = Class.forName(getTargetContext().getClass().getName());
//            return testClass.isAnnotationPresent(io.cucumber.java.Before.class);
//        } catch (ClassNotFoundException e) {
//            return false;
//        }
//    }
}