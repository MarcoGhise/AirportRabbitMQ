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

import it.blog.rabbitmq.message.WeatherForecast;

@RestController
@RequestMapping("/")
@Profile("weather")
public class WeatherSend{

	@Autowired
	RabbitTemplate template;

	@PostMapping(value = "/send", produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<String> addBookingDirectExchange(
			@RequestBody WeatherForecast weatherForecast) {
				
		return ResponseEntity.ok(this.send(weatherForecast));
	}
	
	private String send(WeatherForecast weatherForecast) {
		template.convertAndSend("fanout-exchange", "", weatherForecast);
		
		return "Message Sent";
	}

}
