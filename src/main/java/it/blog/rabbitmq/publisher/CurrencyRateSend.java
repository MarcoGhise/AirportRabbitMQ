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

import it.blog.rabbitmq.message.CurrencyRate;

@RestController
@RequestMapping("/")
@Profile("finance")
public class CurrencyRateSend{
	
	@Autowired
	RabbitTemplate template;

	@PostMapping(value = "/send", produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<String> addBookingDirectExchange(
			@RequestBody CurrencyRate currencyRate) {
				
		return ResponseEntity.ok(this.send(currencyRate));
	}
	
	private String send(CurrencyRate currencyRate) {
		template.convertAndSend("topic-exchange", currencyRate.getFlightCode(), currencyRate);

		return "Message Sent";
	}

}
