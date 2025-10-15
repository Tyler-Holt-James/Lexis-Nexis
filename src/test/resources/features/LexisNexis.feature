Feature: This feature opens and interacts with the LexisNexis technical test page.

  Background:
    Given I have opened a browser
    When I navigate to the Homepage
    Then I verify that the Homepage has loaded

  Scenario: LexisNexis technical test
    Given I pass the following form details:
      | textInput  | password    | textArea                  | dropdownSelect | dropdownDatalist | fileInput | colourPicker | datePicker |
      | test input | password123 | This is a text area field | Two            | Seattle          | bunny.png | Red          | 24/12/2026 |
    # datePicker is in an english format, however in the code it automatically changes it to the correct formatting the form expects
    When I click the other available form details
    # disabledInput | readonlyInput | hiddenInput | checkbox | radio | exampleRange
    # disabledInput, readonlyInput and hiddenInput will be asserted to check that they exist
    And I click the "Return to index" link and collect the amount of html links that appear
    # wasn't sure if this needed to be done or not, so I set up an assertion to check if the amount of html links that appear are equal to the amount that I counted
    # then I made the page go back to the form before submitting
    Then I submit the form
    And I verify that the form has been submitted
    And I close the browser