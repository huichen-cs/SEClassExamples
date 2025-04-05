Feature: Locate Item by Description Words
  As a DIY hobbyist
  I want to look up an item by words of its description
  In order to locate it.

  Scenario: Locate items
    Given I have a LocateItemByDescriptionActivity
    And I have the following items
      |Item                       |Location   | Subarea            |Quantity|
      |Flathead screwdriver set   |Office desk| bottom right drawer| 1      |
      |Philip head screwdriver set|Office desk| middle right drawer| 1      |
      |Precision screwdriver set  |Office desk| top right drawer   | 2      |
      |Amazon basics multimeter   |Home office| bookcase, top shelf| 1      |
      |Fluke multimeter           |Home office| bookcase, top shelf| 1      |
    When I enter 'screwdriver' in the item description box
    And I press the 'Locate Items' button
    Then I should see the following items on the display
      |Item                       |Location    | Subarea            |Quantity|
      |Flathead screwdriver set   |Office desk | bottom right drawer| 1      |
      |Philip head screwdriver set|Office desk | middle right drawer| 1      |
      |Precision screwdriver set  |Office desk | top right drawer   | 2      |
