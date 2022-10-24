# Building project

Project is done with Spring Boot so you can easily run it from the command line with maven.

To start project run:

*mvn spring-boot:run*

or

*./mvnw clean spring-boot:run*

Which will run the spring boot app with the desired endpoints (described below)

# Used database
For this purpose I am using H2 in memory database with data loaded on start in *com.dominikc.currencyraterest.util.DataLoader.java*

# Cut off time endpoint

For serving cut off time for given date and currency pair the endpoint /cut-off is exposed in CountryCutOffTimeController.java

It takes in total 3 request parameters: date, currency 1 and currency 2

* date - date in ISO format ex. 2022-02-03
* currency 1 - first currency iso code
* currency 2 - second currency iso code

and returns json response in format:

{
   "cutOffTime":"TIME"
}

where "TIME" is the cut off time for exchange for provided pair of currencies or string NEVER_POSSIBLE/ALWAYS_POSSIBLE.

## Error codes

* 400 Bad Request - when provided date is in the past or when the call to the endpoint is not correct
* 404 Not Found - if the provided currency ISO codes are not present in database

# Swagger UI
For easier representation of the endpoint Swagger UI is added. Its available under: *http://localhost:8080/swagger-ui/index.html