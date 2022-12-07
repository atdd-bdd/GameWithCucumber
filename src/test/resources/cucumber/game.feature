Feature: Game
  Background:
    Given Directions are:
      | Direction  |
      | N          |
      | S          |
      | E          |
      | W          |
      | U          |
      | D          |
    And rooms are
      | Name      |
      | Main      |
      | Storage   |
      | Ballroom  |
    And connections are
      | FromRoom   | ToRoom   | Direction   | ReverseDirection |
      | Main       | Storage   | N          | S                |
      | Main       | Ballroom  | S          | N                |
      | Storage    | Ballroom  | W          | E                |
    And player is
      | Name   | CurrentRoom |
      | George | Main         |
  Scenario: A Move Command
    When command is
      | Verb   | Noun   |
      | N      |        |
    Then person is now
      | Name   | CurrentRoom  |
      | George | Storage       |
  Scenario: A Move Command from Text
    When input is
    | Input |
    | N     |
    Then output is
    | Output |
    | You are in Storage |
  Scenario: A Bad Move Command from Text
    When input is
      | Input |
      | U     |
    Then output is
      | Output |
      | Could not do that |
  Scenario: A Bad Move Command from Text
    When input is
      | Input |
      | Z     |
    Then output is
      | Output |
      | Could not do that |
  Scenario: A Move Command in Reverse Direction
    When command is
      | Verb   | Noun   |
      | N      |        |
    Then person is now
      | Name   | CurrentRoom  |
      | George | Storage       |
    When command is
      | Verb   | Noun   |
      | S      |        |
    Then person is now
      | Name   | CurrentRoom  |
      | George | Main         |

#Scenario: Run Live
 #   When run live