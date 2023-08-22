package it.blog.rabbitmq.publisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.blog.rabbitmq.message.Passenger;

@RestController
@RequestMapping("/")
@Profile("checkin")
public class CheckInSend {

	@Autowired
	RabbitTemplate template;

	@PostMapping(value = "/send", produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<String> addBookingDirectExchange(
			@RequestBody Passenger passenger) {
				
		return ResponseEntity.ok(this.send(passenger));
	}
	
	private String send(Passenger passenger) {
		template.convertAndSend("direct-exchange", String.format("checkin-%s", passenger.getFlightNumber()), passenger);

		return "Message Sent";
	}

}
