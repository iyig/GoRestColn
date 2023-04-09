Feature: Data table example

  Scenario: POST-Create a new user input
    Given set api endpoint "public/v1/users"
    And user creates new user with request body "John Doe 8","male","john1299@gmail.com","active"
    Then validate the user details
      | id | name       | Gender | Email            | Status |
      | 0  | John Doe 8 | male   | john1299@gmail.com | active |