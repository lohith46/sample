# sample

Pre-requistie
1. MongoDB should be installed
2. Java 7

Below are the postman collection for the API
1. Create API

POST http://localhost:8080/hotels/createHotel
{
	"id":10,
	"name" : "Adigas",
	"description" : "Coffee Shop",
	"city" : "Bangalore",
	"rating" : 10
}

GET http://localhost:8080/hotels/getHotel/10
