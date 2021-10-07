Feature: Book a flight.

Scenario: Verify user should be able to book a flight successfully

    Given User is on the BlazeDemo website
    When User Selects From and To
    And clicks on Find Flights button
    Then all the available flights should be displayed
    And user selects a flight and clicks on the choose flight button
    Then page to enter all the details displayed
    And user clicks on Purchase flight button after entering all the details
    Then flight booked message should be displayed with Id
    
    
    