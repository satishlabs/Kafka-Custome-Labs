package com.kafkaconsumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.kafka.entity.Order;

@SpringBootApplication
public class AppConfig {
		
		//String message receiving  in Kafka Consumer
		@Bean
		public ConsumerFactory<String, String> stringConsumerFactory(){
			System.out.println("AppConfig -- stringConumserFactory()");
			Map<String, Object> props = new HashMap<String, Object>();
			
			props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
			props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
			props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
			props.put(ConsumerConfig.GROUP_ID_CONFIG, "myjlc_group");
			props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
			
			return new DefaultKafkaConsumerFactory<>(props);
		}
		
		@Bean(name = "stringKafkaListenerContainerFactory")
		public ConcurrentKafkaListenerContainerFactory<String, String> stringKafkaListenerContainerFactory(){
				System.out.println("AppConfig -- stringKafkaListenerContainerFactory()");
				
				ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
				
				factory.setConsumerFactory(stringConsumerFactory());
				
				return factory;
		}
	
		//Order details  receiving in Kafka Consumer
		@Bean
		public ConsumerFactory<String, Order> orderConsumerFactory(){
			System.out.println("AppConfig -- stringConumserFactory()");
			Map<String, Object> props = new HashMap<String, Object>();
			
			props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
			props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
			props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
			props.put(ConsumerConfig.GROUP_ID_CONFIG, "myorder_group");
			props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
			props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
			
			return new DefaultKafkaConsumerFactory<String,Order>(props);
		}
		
		@Bean(name = "orderKafkaListenerContainerFactory")
		public ConcurrentKafkaListenerContainerFactory<String, Order> orderKafkaListenerContainerFactory(){
				System.out.println("AppConfig -- orderKafkaListenerContainerFactory()");
				
				ConcurrentKafkaListenerContainerFactory<String, Order> factory = new ConcurrentKafkaListenerContainerFactory<String, Order>();
				
				factory.setConsumerFactory(orderConsumerFactory());
				
				return factory;
		}
}
