package edu.cuny.chen.whereismystuff.test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.fail;

import androidx.room.Room;
import androidx.test.core.app.ActivityScenario;
import androidx.test.platform.app.InstrumentationRegistry;

import android.content.Context;

import java.util.List;
import java.util.Map;

import edu.cuny.chen.whereismystuff.LocateItemsByWordsActivity;
import edu.cuny.chen.whereismystuff.WhereIsMyStuffApplication;
import edu.cuny.chen.whereismystuff.model.Item;
import edu.cuny.chen.whereismystuff.model.ItemDatabase;
import edu.cuny.chen.whereismystuff.model.Location;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import edu.cuny.chen.whereismystuff.R;

public class LocateItemsByWordsSteps {

    private ActivityScenario<LocateItemsByWordsActivity> scenario;
    private ItemDatabase database;

    @Before
    public void setup() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        database = Room.inMemoryDatabaseBuilder(context, ItemDatabase.class)
                .allowMainThreadQueries() // Only for testing!
                .build();
        ItemDatabase.setDataBase(database);
    }

    @Given("^I have a LocateItemByDescriptionActivity$")
    public void iHaveALocateItemByDescriptionActivity() {
        try {
            Class.forName("edu.cuny.chen.whereismystuff.LocateItemsByWordsActivity");
        } catch (ClassNotFoundException e) {
            fail("Activity class edu.cuny.chen.whereismystuff.LocateItemsByWordsActivity not found");
        }
        scenario = ActivityScenario.launch(LocateItemsByWordsActivity.class);
    }

    @And("I have the following items")
    public void iHaveTheFollowingItems(DataTable dataTable) {
        List<Map<String, String>> items = dataTable.asMaps();

        for (Map<String, String> item : items) {
            // 1. Insert Location first
            Location location = new Location(
                    item.get("Location"),
                    item.get("Subarea")
            );
            long locationId = database.locationDao().insert(location);

            // 2. Insert Item with foreign key
            String qtyText = item.get("Quantity");
            int qty = 0;
            if (qtyText != null) {
                qty = Integer.parseInt(qtyText);
            }
            Item newItem = new Item(
                    item.get("Item"),
                    (int)locationId,
                    qty
            );
            database.itemDao().insert(newItem);
        }
    }

    @When("I enter 'screwdriver' in the item description box")
    public void iEnterInTheItemDescriptionBox() {
        onView(withId(R.id.description_input_textview))
                .perform(typeText("screwdriver"));
    }

    @And("I press the 'Locate Items' button")
    public void iPressTheButton() {
        onView(withId(R.id.locate_items_button))
                .perform(click());
    }

    @Then("I should see the following items on the display")
    public void iShouldSeeTheFollowingItemsOnTheDisplay(DataTable expectedData) {
        List<Map<String, String>> expectedItems = expectedData.asMaps(String.class, String.class);

        for (Map<String, String> expectedItem : expectedItems) {
            onView(allOf(
                    isDescendantOfA(withId(R.id.results_container)),
                    hasDescendant(allOf(
                            withId(R.id.item_description_textview),
                            withText(expectedItem.get("Item"))
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
}
