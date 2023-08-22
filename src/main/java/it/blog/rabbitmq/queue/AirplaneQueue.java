package it.blog.rabbitmq.queue;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({ "checkin", "finance", "tower", "weather" })
public class AirplaneQueue {

	@Value("${queue.U2571}")
	private String flightU2571;

	@Value("${queue.U2771}")
	private String flightU2771;	

	@Value("${queue.FR691}")
	private String flightFR691;

	@Value("${queue.UA221}")
	private String flightUA221;
	
	@Value("${queue.PR777}")
	private String flightPrivatePR777;

	/*
	 * Queue configurations
	*/
	@Bean
	Queue flightU2571Queue() {
		return QueueBuilder.nonDurable(flightU2571).deadLetterExchange("direct-exchange")
				.deadLetterRoutingKey(flightU2571 + ".wait").build();
	}

	@Bean
	Queue waitQueue() {
		return QueueBuilder.durable(flightU2571 + ".wait").build();
	}
	@Bean
	Queue flightU2771Queue() {
		return new Queue(flightU2771, false);
	}
	
	@Bean
	Queue flightPrivatePR777Queue() {
		Map<String, Object> args = new HashMap<>();
		args.put("x-queue-type", "quorum");
		return new Queue(flightPrivatePR777, true, false, false, args);
	}

	@Bean
	Queue flightFR691Queue() {
		return new Queue(flightFR691, false);
	}

	@Bean
	Queue flightUA221Queue() {
		return new Queue(flightUA221, false);
	}

}
