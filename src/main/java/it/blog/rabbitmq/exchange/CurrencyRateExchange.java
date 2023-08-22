package it.blog.rabbitmq.exchange;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/*
 * Topic exchange for Current currency rate
 */
@Configuration
@Profile("finance")
public class CurrencyRateExchange {
	
	@Bean
	TopicExchange topicExchange() {
		return new TopicExchange("topic-exchange");
	}
	
	/*
	 * Topic exhange binding
	 */
	@Bean
	Binding flightU2571Binding(Queue flightU2571Queue, TopicExchange exchange) {
		return BindingBuilder.bind(flightU2571Queue).to(exchange).with("flight.eu.*");
	}

	@Bean
	Binding flightU2771Binding(Queue flightU2771Queue, TopicExchange exchange) {
		return BindingBuilder.bind(flightU2771Queue).to(exchange).with("flight.eu.*");
	}

	@Bean
	Binding flightFR691Binding(Queue flightFR691Queue, TopicExchange exchange) {
		return BindingBuilder.bind(flightFR691Queue).to(exchange).with("flight.uk.*");
	}

	@Bean
	Binding flightUA221Binding(Queue flightUA221Queue, TopicExchange exchange) {
		return BindingBuilder.bind(flightUA221Queue).to(exchange).with("flight.us.*");
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
