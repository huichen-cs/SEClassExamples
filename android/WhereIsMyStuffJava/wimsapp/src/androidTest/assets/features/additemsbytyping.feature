Feature: Add Items by Typing
  As a DIY enthusiast,
  I want to add items such as tools and material by manual data entry
     (by typing description, quantity, and location)
  In order to locate them quickly later by searching words in description.


Scenario: Add Item By Typing
  Given I have an AddItemsByTypingActivity
  When I enter item data:
    |Description|Location|Subarea|Quantity|
    |Milwaukee 3/8-inch impact driver|Living room closet|bottom shelf|1|
    |Milwaukee 3/8-inch screw driver |Living room closet|bottom shelf|1|
    |Amazon-basics screw driver bit sets|Living room closet|middle shelf|2|
    |Amazon-basics impact driver bit sets|Living room closet|middle shelf|2|
  And I have a LocateItemByDescriptionActivity
  And I enter search term "Milwaukee 3/8-inch impact"
  And I do the 'Locate Items' button
  Then I should be able to locate the matching item:
    |Description|Location|Subarea|Quantity|
    |Milwaukee 3/8-inch impact driver|Living room closet|bottom shelf|1|

    Scenario: Add Item By Typing
    Given I have an AddItemsByTypingActivity
    When I enter item data:
      |Description|Location|Subarea|Quantity|
      |Milwaukee 3/8-inch impact driver|Living room closet|bottom shelf|1|
      |Milwaukee 3/8-inch screw driver |Living room closet|bottom shelf|1|
      |Amazon-basics screw driver bit sets|Living room closet|middle shelf|2|
      |Amazon-basics impact driver bit sets|Living room closet|middle shelf|2|
    And I have a LocateItemByDescriptionActivity
    And I enter search term "Milwaukee 3/8-inch screw driver"
    And I do the 'Locate Items' button
    Then I should be able to locate the matching item:
      |Description|Location|Subarea|Quantity|
      |Milwaukee 3/8-inch screw driver |Living room closet|bottom shelf|1|


  Scenario: Add Item By Typing
    Given I have an AddItemsByTypingActivity
    When I enter item data:
      |Description|Location|Subarea|Quantity|
      |Milwaukee 3/8-inch impact driver|Living room closet|bottom shelf|1|
      |Milwaukee 3/8-inch screw driver |Living room closet|bottom shelf|1|
      |Amazon-basics screw driver bit sets|Living room closet|middle shelf|2|
      |Amazon-basics impact driver bit sets|Living room closet|middle shelf|2|
    And I have a LocateItemByDescriptionActivity
    And I enter search term "screw driver bit sets"
    And I do the 'Locate Items' button
    Then I should be able to locate the matching item:
      |Description|Location|Subarea|Quantity|
      |Amazon-basics screw driver bit sets|Living room closet|middle shelf|2|

  Scenario: Add Item By Typing
    Given I have an AddItemsByTypingActivity
    When I enter item data:
      |Description|Location|Subarea|Quantity|
      |Milwaukee 3/8-inch impact driver|Living room closet|bottom shelf|1|
      |Milwaukee 3/8-inch screw driver |Living room closet|bottom shelf|1|
      |Amazon-basics screw driver bit sets|Living room closet|middle shelf|2|
      |Amazon-basics impact driver bit sets|Living room closet|middle shelf|2|
    And I have a LocateItemByDescriptionActivity
    And I enter search term "impact driver bit sets"
    And I do the 'Locate Items' button
    Then I should be able to locate the matching item:
      |Description|Location|Subarea|Quantity|
      |Amazon-basics impact driver bit sets|Living room closet|middle shelf|2|
