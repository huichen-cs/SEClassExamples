Feature: Welcome Users
  As an App developer,
  I want to show a welcome message along with my name and app version number
  In order to inform the user what app they are using

  Scenario: Display welcome message and app author and version number
    Given I have a WelcomeUsersActivity
    When It launches the WelcomeUsersActivity on the device
    Then it shows a 'Welcome!' message
    And it shows a 'Author: Chen' message
    And it shows a 'Version: 0.01' message

  Scenario: Launch LocateItemsByWordsActivity
    Given I have a WelcomeUsersActivity and a LocateItemsByWordsActivity
    When It launches the WelcomeUsersActivity on the device
    Then it shows a 'Locate Items' button
    When I click the 'Locate Items' button
    Then It launches the LocateItemsByWordsActivity

  Scenario: Launch AddItemsByTypingActivity
    Given I have a WelcomeUsersActivity and an AddItemsByTypingActivity
    When It launches the WelcomeUsersActivity on the device
    Then it shows a 'Add Items' button
    When I click the 'Add Items' button
    Then It launches the AddItemsByTypingActivity