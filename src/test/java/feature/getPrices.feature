Feature: Test price retrieval for various conditions

  Scenario: Get price for product 35455 at 10:00 on June 14
    Given url 'http://localhost:8080/api/prices'
    And param applicationDate = '2020-06-14T10:00:00'
    And param productId = 35455
    And param brandId = 1
    When method get
    Then status 200
    And match $[0].price == 35.50

  Scenario: Get price for product 35455 at 16:00 on June 14
    Given url 'http://localhost:8080/api/prices'
    And param applicationDate = '2020-06-14T16:00:00'
    And param productId = 35455
    And param brandId = 1
    When method get
    Then status 200
    And match $[0].price == 25.45

  Scenario: Get price for product 35455 at 21:00 on June 14
    Given url 'http://localhost:8080/api/prices'
    And param applicationDate = '2020-06-14T21:00:00'
    And param productId = 35455
    And param brandId = 1
    When method get
    Then status 200
    And match $[0].price == 35.50
    
  Scenario: Get price for product 35455 at 10:00 on June 15
    Given url 'http://localhost:8080/api/prices'
    And param applicationDate = '2020-06-15T10:00:00'
    And param productId = 35455
    And param brandId = 1
    When method get
    Then status 200
    And match $[0].price == 30.50
   
   Scenario: Get price for product 35455 at 21:00 on June 16
    Given url 'http://localhost:8080/api/prices'
    And param applicationDate = '2020-06-16T21:00:00'
    And param productId = 35455
    And param brandId = 1
    When method get
    Then status 200
    And match $[0].price == 38.95
