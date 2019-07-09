Feature: Exercise1
  Background:
    Given I am on the JDI Home Page

  Scenario: Home page title verification
    Then Title should be 'Home Page'

  Scenario: Login verification
    When I login as user 'epam' with password '1234'
    Then User name should be 'PITER CHAILOVSKII'

  Scenario: Home page content verification
    Then Page contains 4 images
    And  Page contains 4 text labels
    And  Page contains description
    And  Page contains headline

  Scenario: Menu items verification
    When I click on 'Service' subcategory in the header
    Then Header 'Service' drop down contains the following options:
      | SUPPORT            |
      | DATES              |
      | COMPLEX TABLE      |
      | SIMPLE TABLE       |
      | USER TABLE         |
      | TABLE WITH PAGES   |
      | DIFFERENT ELEMENTS |
      | PERFORMANCE        |

  Scenario: Left menu items verification
    When I click on 'Service' subcategory in the left section
    Then Left section 'Service' drop down contains the following options:
      | SUPPORT            |
      | DATES              |
      | COMPLEX TABLE      |
      | SIMPLE TABLE       |
      | USER TABLE         |
      | TABLE WITH PAGES   |
      | DIFFERENT ELEMENTS |
      | PERFORMANCE        |

  Scenario: 'Different Elements' page content verification
    Given I am on the 'Different Elements' page
    Then Page contains 4 checkboxes
    And  Page contains 4 radios
    And  Page contains 1 dropdown
    And  Page contains 2 buttons
    And  Page contains right section
    And  Page contains left section

  Scenario Outline: Checkboxes selection verification
    Given I am on the 'Different Elements' page
    When I select checkbox "<element>"
    Then log row with value corresponded to element "<element>" is displayed

    Examples:
      | element |
      | Water |
      | Wind  |

  Scenario Outline: Radio selection verification
    Given I am on the 'Different Elements' page
    When I select radio button "<metal>"
    Then log row with value corresponded to metal "<metal>" is displayed

    Examples:
      | metal |
      | Selen |

  Scenario Outline: Dropdown selection verification
    Given I am on the 'Different Elements' page
    When I select dropdown "<color>"
    Then log row with value corresponded to color "<color>" is displayed

    Examples:
      | color |
      | Yellow |

  Scenario Outline: Checkboxes deselection verification
    Given I am on the 'Different Elements' page
    When I select checkbox "<element>"
    Then log row with value corresponded to element "<element>" deselection is displayed

    Examples:
      | element |
      | Water |
      | Wind  |
