Feature: To Verify booked flight Id is unique

Scenario: Verify Id generated after booking each flight should be unique

Given User is on the BlazeDemo website
When user books a flight
Then an Id is generated
And user books another flight
Then another Id is generated
Then check two Id's generated are unique
 