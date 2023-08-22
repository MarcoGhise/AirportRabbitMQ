package it.blog.rabbitmq.publisher;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.blog.rabbitmq.message.PilotCommunication;

@RestController
@RequestMapping("/")
@Profile("tower")
public class PilotCommunicationSend {

	@Autowired
	RabbitTemplate template;
	
	@PostMapping(value = "/send", produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<String> addBookingDirectExchange(
			@RequestBody PilotCommunication pilotCommunication) {
				
		return ResponseEntity.ok(this.send(pilotCommunication));
	}

	private String send(PilotCommunication pilotCommunication) {
		MessageProperties messageProperties = new MessageProperties();
		messageProperties.setHeader("pilot", pilotCommunication.getPilot());
		MessageConverter messageConverter = new Jackson2JsonMessageConverter();
		Message message = messageConverter.toMessage(pilotCommunication, messageProperties);
		template.send("header-exchange", "", message);

		return "Message Sent";
	}

}
