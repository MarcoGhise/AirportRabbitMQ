## Article
[RabbitMQ Exchange types - Put them into practice](https://wp.me/p2rVu3-13u)

[RabbitMQ - Build Quorum Queue in Cluster](https://wp.me/p2rVu3-1d0)

## Publisher

### Check-in passenger
java -jar AirportRabbitMQ-1.0.0.jar --spring.profiles.active=checkin --server.port=8080

### Airplane Currency
java -jar AirportRabbitMQ-1.0.0.jar --spring.profiles.active=finance --server.port=8081

### Tower communication to Pilot
java -jar AirportRabbitMQ-1.0.0.jar --spring.profiles.active=tower --server.port=8082

### Weather forecast
java -jar AirportRabbitMQ-1.0.0.jar --spring.profiles.active=weather --server.port=8083

## Consumer

### Flight U2571
java -jar AirportRabbitMQ-1.0.0.jar --spring.profiles.active=airplane --server.port=8085 --flight.code=flight.eu.U2571

### Flight U2771
java -jar AirportRabbitMQ-1.0.0.jar --spring.profiles.active=airplane --server.port=8086 --flight.code=flight.eu.U2771

### Flight FR691
java -jar AirportRabbitMQ-1.0.0.jar --spring.profiles.active=airplane --server.port=8087 --flight.code=flight.uk.FR691

### Flight UA221
java -jar AirportRabbitMQ-1.0.0.jar --spring.profiles.active=airplane --server.port=8088 --flight.code=flight.us.UA221


## Send Passenger Request

### Checkin
curl -H "Content-type:application/json" -X POST -d '{"name":"Carl","surname":"Baker","flightNumber":"U2571","seat":"15A"}' http://10.21.40.14:8080/send

### Currency
curl -H "Content-type:application/json" -X POST -d '{"flightCode":"flight.eu.U2771","currency":"EURO"}' http://10.21.40.14:8081/send

### Tower
curl -H "Content-type:application/json" -X POST -d '{"pilot":"William","message":"Ready for take off"}' http://10.21.40.14:8082/send

### Weather
curl -H "Content-type:application/json" -X POST -d '{"temp":29,"windSpeed":5,"description":"Sunny"}' http://10.21.40.14:8083/send

## Publisher Cluster

### Weather forecast
java -jar AirportRabbitMQ-1.0.0.jar --spring.profiles.active=cluster,weather --server.port=8083

## Consumer Cluster

### Flight PR777
java -jar AirportRabbitMQ-1.0.0.jar --spring.profiles.active=cluster,airplane --server.port=8089 --flight.code=flight.private.PR777

## Message Flow simulator
https://rmq-topology.dbproductions.de/