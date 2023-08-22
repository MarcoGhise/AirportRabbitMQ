package it.blog.rabbitmq.exchange;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/*
 * Direct Exchange for check in operations
 */
@Configuration
@Profile("checkin")
public class CheckInExchange {

	@Value("${queue.U2571}")
	private String flightU2571;
		
	/*
	 * Exchange configurations
	 */
	@Bean
	DirectExchange directExchange() {
		return new DirectExchange("direct-exchange");
	}
	/*
	 * Direct exchange binding
	 */
	@Bean
	Binding waitBinding(Queue waitQueue, DirectExchange exchange) {
		return BindingBuilder.bind(waitQueue).to(exchange).with(flightU2571 + ".wait");
	}	
	@Bean
	Binding flightU2571Binding(Queue flightU2571Queue, DirectExchange exchange) {
		return BindingBuilder.bind(flightU2571Queue).to(exchange).with("checkin-U2571");
	}

	@Bean
	Binding flightU2771Binding(Queue flightU2771Queue, DirectExchange exchange) {
		return BindingBuilder.bind(flightU2771Queue).to(exchange).with("checkin-U2771");
	}

	@Bean
	Binding flightFR691Binding(Queue flightFR691Queue, DirectExchange exchange) {
		return BindingBuilder.bind(flightFR691Queue).to(exchange).with("checkin-FR691");
	}

	@Bean
	Binding flightUA221Binding(Queue flightUA221Queue, DirectExchange exchange) {
		return BindingBuilder.bind(flightUA221Queue).to(exchange).with("checkin-UA221");
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}
}
