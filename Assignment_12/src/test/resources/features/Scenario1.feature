Feature: Verify WebDriver University site

  Scenario: Verify the site title and image functionality
    Given I launch the URL "http://webdriveruniversity.com/index.html"
    When I verify the page title
    And I click on the "IFRAME" link
    Then a new tab should open
    And I switch to the new tab
    Then I verify the image is present
    When I click on the right arrow button
    Then the image should change accordingly
