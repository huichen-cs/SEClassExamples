Feature: Welcome Users
  As an App developer,
  I want to show a welcome message along with my name and app version number
  In order to inform the user what app they are using

  Scenario: Display welcome message and app author and version number
    Given I have a WelcomeUsersActivity and a LocateItByWordsActivity
    When It launches it on the device
    Then it shows a 'Welcome to Where Is My Stuff App' message
    And it shows a 'Author: Chen' message
    And it shows a 'Version: 0.01' message
    And it shows a 'Locate Items' button
    When I click the 'Locate Items' button
    Then It launches the LocateItemsByWordsActivity
