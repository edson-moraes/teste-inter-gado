Feature: Animal Controller Test

Scenario: Get animal should return 404 for invalid id
Given url 'http://localhost:8080/animal/8'
When method GET
Then status 404