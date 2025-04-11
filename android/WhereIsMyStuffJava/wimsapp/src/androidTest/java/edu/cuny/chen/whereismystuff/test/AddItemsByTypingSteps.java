package edu.cuny.chen.whereismystuff.test;

import static androidx.core.app.PendingIntentCompat.getActivity;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.Assert.fail;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import androidx.room.Room;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;

import org.hamcrest.Matcher;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import edu.cuny.chen.whereismystuff.AddItemsByTypingActivity;
import edu.cuny.chen.whereismystuff.R;
import edu.cuny.chen.whereismystuff.model.ItemDatabase;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddItemsByTypingSteps {
    private ActivityScenario<AddItemsByTypingActivity> scenario;
    private ItemDatabase database;
    private Activity activity;

    @Before
    public void setup() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        database = Room.inMemoryDatabaseBuilder(context, ItemDatabase.class)
                .allowMainThreadQueries() // Only for testing!
                .build();
        ItemDatabase.setDataBase(database);
    }


    @Given("I have an AddItemsByTypingActivity")
    public void iHaveAnAddItemsByTypingActivity() {
        try {
            Class.forName("edu.cuny.chen.whereismystuff.AddItemsByTypingActivity");
        } catch (ClassNotFoundException e) {
            fail("Activity class edu.cuny.chen.whereismystuff.AddItemsByTypingActivity not found");
        }
        scenario = ActivityScenario.launch(AddItemsByTypingActivity.class);
        scenario.onActivity(a -> this.activity = a);
    }

    @When("I enter item data:")
    public void iEnterItemData(DataTable dataTable) {
        List<Map<String, String>> items = dataTable.asMaps();

        for (Map<String, String> item : items) {
            onView(withId(R.id.add_item_desc_edittext))
                    .perform(typeText(Objects.requireNonNull(item.get("Description"))));

            onView(withId(R.id.add_item_location_area_edittext))
                    .perform(typeText(Objects.requireNonNull(item.get("Location"))));

            onView(withId(R.id.add_item_location_subarea_edittext))
                    .perform(typeText(Objects.requireNonNull(item.get("Subarea"))));

            int quantity = Integer.parseInt(Objects.requireNonNull(item.get("Quantity")));
            onView(withId(R.id.add_item_quantity_number_picker))
                    .perform(setNumber(quantity));

            onView(withId(R.id.add_item_by_typing_button))
                    .check(matches(isClickable()))
                    .perform(click());
        }
    }

    @And("I enter search term {string}")
    public void iEnterSearchTerm(String searchTerm) {
        onView(withId(R.id.description_input_textview))
                .perform(typeText(searchTerm));
    }

    @And("I do the 'Locate Items' button")
    public void iClickTheButton() {
        onView(withId(R.id.locate_items_button))
                .perform(click());
    }

    @Then("I should be able to locate the matching item:")
    public void iShouldBeAbleToLocateTheMatchingItem(DataTable expectedData) {
        List<Map<String, String>> expectedItems = expectedData.asMaps(String.class, String.class);

        for (Map<String, String> expectedItem : expectedItems) {
            onView(allOf(
                    isDescendantOfA(withId(R.id.results_container_recyclerview)),
                    hasDescendant(allOf(
                            withId(R.id.item_description_textview),
                            withText(expectedItem.get("Description"))
                    )),
                    hasDescendant(allOf(
                            withId(R.id.item_location_textview),
                            withText(expectedItem.get("Location"))
                    )),
                    hasDescendant(allOf(
                            withId(R.id.item_location_subarea_textview),
                            withText(expectedItem.get("Subarea"))
                    )),
                    hasDescendant(allOf(
                            withId(R.id.item_quantity_textview),
                            withText(expectedItem.get("Quantity"))
                    ))
            )).check(matches(isDisplayed()));
        }
    }

    @After
    public void tearDown() {
        if (scenario != null) scenario.close();
        if (database != null) database.close();
    }

    private static ViewAction setNumber(final int num) {
        return new ViewAction() {
            @Override
            public String getDescription() {
                return "Set the passed number into the NumberPicker";
            }

            @Override
            public void perform(UiController uiController, View view) {
                NumberPicker np = (NumberPicker) view;
                np.setValue(num);
            }

            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(NumberPicker.class);
            }
        };
    }
}