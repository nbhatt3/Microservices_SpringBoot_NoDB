# Microservices_SpringBoot_NoDB
Example showing how Microservices (3) interact with each other using Spring Boot, Eureka . No DB used in this example 

This example describes 3 microservices which interact to fetch data. Eureka Server is used for purpose of Service Discovery, 
ie. all 3 microservices register themselves with Eureka Server (folder is discovery-server).

Objective : Given a userId in the request url, provide the ratings for each movie given by that userId. 
TO-use URL - http://localhost:8081/catalog/userId

Microservice 1 - MovieInfo Service -    configured to run on port 8082 
Microservice 2 - MovieCatalog Service - configured to run on Port 8081
Microservice 3 - Ratings Data Service - configured to run on port 8083

Microservice 2 calls the Microservice 3 to get all the Ratings for a userId provided in URL call from a user.
A list of Ratings (already given by user for some movies) is received as result.

Output of first call (to Microservice 3) is as below:
{
"userRating": [
{
"movieId": "M1234",
"rating": 4
},
{
"movieId": "M5678",
"rating": 3
}
]
}

For each movieId in this,  Microservice 2 then calls the Microservice 1 to get information on each MovieID.
The Final result is shown to user .

[
{
"name": "Movie1 Test Name",
"desc": "Catalog Desc",
"rating": 4
},
{
"name": "Movie2 Test Name",
"desc": "Catalog Desc",
"rating": 3
}
]


The actual call to microservice is made using alias-name (present in each application.properties file), and each microservice 
runs on independent port defined in application.properties file of each microservice folder.


