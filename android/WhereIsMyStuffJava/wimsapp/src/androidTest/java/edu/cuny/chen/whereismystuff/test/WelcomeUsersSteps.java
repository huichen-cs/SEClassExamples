package edu.cuny.chen.whereismystuff.test;

import edu.cuny.chen.whereismystuff.R;
import edu.cuny.chen.whereismystuff.WelcomeUsersActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.platform.app.InstrumentationRegistry;

import static junit.framework.TestCase.fail;

import android.app.Instrumentation;
import android.content.Intent;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WelcomeUsersSteps {
    private ActivityScenario<?> scenario;

    @Before
    public void setup() {
        // No need to launch here if you're doing it in the When step
    }

    @Given("I have a WelcomeUsersActivity and a LocateItByWordsActivity")
    public void verifyActivityExists() {
        // Verify class exists without launching
        try {
            Class.forName("edu.cuny.chen.whereismystuff.WelcomeUsersActivity");
        } catch (ClassNotFoundException e) {
            fail("WelcomeUsersActivity does not exist");
        }

        try {
            Class.forName("edu.cuny.chen.whereismystuff.LocateItemsByWordsActivity");
        } catch (ClassNotFoundException e) {
            fail("LocateItByWordsActivity does not exist");
        }

    }

    @When("It launches it on the device")
    public void launchActivity() {
        scenario = ActivityScenario.launch(WelcomeUsersActivity.class);
    }

    @Then("it shows a 'Welcome to Where Is My Stuff App' message")
    public void verifyWelcomeMessage() {
        onView(withText("Welcome to Where is My Stuff App"))
                .check(matches(isDisplayed()));
    }

    @Then("it shows a 'Author: Chen' message")
    public void verifyAuthorMessage() {
        onView(withId(R.id.author_textview))
                .check(matches(withText("Author: Chen")))
                .check(matches(isDisplayed()));
    }

    @Then("it shows a 'Version: 0.01' message")
    public void verifyVersionMessage() {
        onView(withId(R.id.version_textview))
                .check(matches(withText("Version: 0.01")))
                .check(matches(isDisplayed()));
    }


    @And("it shows a 'Locate Items' button")
    public void itShowsALocateItButton() {
        onView(withId(R.id.locate_items_button))
                .check(matches(withText("Locate Items")))
                .check(matches(isDisplayed()));
    }

    @When("I click the 'Locate Items' button")
    public void iClickTheLocateItButton() {
        onView(withId(R.id.locate_items_button)).perform(click());
    }


    @Then("It launches the LocateItemsByWordsActivity")
    public void itLaunchesTheLocateItByWordsActivity() {
        onView(withId(R.id.locate_items_by_words))
                .check(matches(isDisplayed()));
    }


    @After
    public void tearDown() {
        if (scenario != null) {
            scenario.close();
        }
    }
}
