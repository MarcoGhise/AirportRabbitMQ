package it.blog.rabbitmq.exchange;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/*
 * Header exchange for pilot communication
 */
@Configuration
@Profile("tower")
public class PilotCommunicationExchange {
	
	@Bean
	HeadersExchange headerExchange() {
		return new HeadersExchange("header-exchange");
	}
	
	/*
	 * Header exchange binding
	 */
	@Bean
	Binding flightU2571Binding(Queue flightU2571Queue, HeadersExchange exchange) {
		return BindingBuilder.bind(flightU2571Queue).to(exchange).where("pilot").matches("Smith");
	}

	@Bean
	Binding flightU2771Binding(Queue flightU2771Queue, HeadersExchange exchange) {
		return BindingBuilder.bind(flightU2771Queue).to(exchange).where("pilot").matches("William");
	}

	@Bean
	Binding flightFR691Binding(Queue flightFR691Queue, HeadersExchange exchange) {
		return BindingBuilder.bind(flightFR691Queue).to(exchange).where("pilot").matches("Appleton");
	}

	@Bean
	Binding flightUA221Binding(Queue flightUA221Queue, HeadersExchange exchange) {
		return BindingBuilder.bind(flightUA221Queue).to(exchange).where("pilot").matches("Stevens");
	}
	
	@Bean
	Binding flightPrivatePR777Binding(Queue flightPrivatePR777, HeadersExchange exchange) {
		return BindingBuilder.bind(flightPrivatePR777).to(exchange).where("pilot").matches("Eric");
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
