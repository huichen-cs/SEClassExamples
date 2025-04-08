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

import static junit.framework.TestCase.fail;

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

    private void iHaveActivity(String activity) {
        try {
            Class.forName(activity);
        } catch (ClassNotFoundException e) {
            fail("Activity " + activity + " does not exist");
        }
    }


    @Given("I have a WelcomeUsersActivity")
    public void iHaveAWelcomeUsersActivity() {
        iHaveActivity("edu.cuny.chen.whereismystuff.WelcomeUsersActivity");
    }

    @Given("I have a WelcomeUsersActivity and a LocateItemsByWordsActivity")
    public void iHaveWelcomeUserAndLocateItemsByWordsActivities() {
        iHaveAWelcomeUsersActivity();

        iHaveActivity("edu.cuny.chen.whereismystuff.LocateItemsByWordsActivity");
    }

    @Given("I have a WelcomeUsersActivity and an AddItemsByTypingActivity")
    public void iHaveAWelcomeUsersAndAnAddItemsByTypingActivities() {
        iHaveAWelcomeUsersActivity();
        iHaveActivity("edu.cuny.chen.whereismystuff.AddItemsByTypingActivity");
    }

    @When("It launches the WelcomeUsersActivity on the device")
    public void launchActivity() {
        scenario = ActivityScenario.launch(WelcomeUsersActivity.class);
    }

    @Then("it shows a 'Welcome!' message")
    public void itShowsWelcomeMessage() {
        onView(withText("Welcome!"))
                .check(matches(isDisplayed()));
    }

    @Then("it shows a 'Author: Chen' message")
    public void itShowsAuthorMessage() {
        onView(withId(R.id.author_textview))
                .check(matches(withText("Author: Chen")))
                .check(matches(isDisplayed()));
    }

    @Then("it shows a 'Version: 0.01' message")
    public void itShowsVersionNumber() {
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

    @Then("it shows a 'Add Items' button")
    public void itShowsAAddItemsButton() {
        onView(withId(R.id.add_items_button))
                .check(matches(withText("Add Items")))
                .check(matches(isDisplayed()));
    }

    @When("I click the 'Locate Items' button")
    public void iClickTheLocateItemsButton() {
        onView(withId(R.id.locate_items_button)).perform(click());
    }

    @When("I click the 'Add Items' button")
    public void iClickTheAddItemsButton() {
        onView(withId(R.id.add_items_button)).perform(click());
    }


    @Then("It launches the LocateItemsByWordsActivity")
    public void itLaunchesTheLocateItByWordsActivity() {
        onView(withId(R.id.locate_items_by_words_layout))
                .check(matches(isDisplayed()));
    }

    @Then("It launches the AddItemsByTypingActivity")
    public void itLaunchesTheAddItemsByTypingActivity() {
        onView(withId(R.id.add_items_by_typing_layout))
                .check(matches(isDisplayed()));
    }


    @After
    public void tearDown() {
        if (scenario != null) {
            scenario.close();
        }
    }
}
