package it.blog.rabbitmq.exchange;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/*
 * Fanout exchange for Weather forecast
 */
@Configuration
@Profile("weather")
public class WeatherForecastExchange {
	
	@Bean
	FanoutExchange fanoutExchange() {
		return new FanoutExchange("fanout-exchange");
	}
	
	/*
	 * Fanout exchange binding
	*/
	@Bean
	Binding flightU2571Binding(Queue flightU2571Queue, FanoutExchange exchange) {
		return BindingBuilder.bind(flightU2571Queue).to(exchange);
	}

	@Bean
	Binding flightU2771Binding(Queue flightU2771Queue, FanoutExchange exchange) {
		return BindingBuilder.bind(flightU2771Queue).to(exchange);
	}

	@Bean
	Binding flightFR691Binding(Queue flightFR691Queue, FanoutExchange exchange) {
		return BindingBuilder.bind(flightFR691Queue).to(exchange);
	}

	@Bean
	Binding flightUA221Binding(Queue flightUA221Queue, FanoutExchange exchange) {
		return BindingBuilder.bind(flightUA221Queue).to(exchange);
	}
	
	@Bean
	Binding flightPrivatePR777Binding(Queue flightPrivatePR777Queue, FanoutExchange exchange) {
		return BindingBuilder.bind(flightPrivatePR777Queue).to(exchange);
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
