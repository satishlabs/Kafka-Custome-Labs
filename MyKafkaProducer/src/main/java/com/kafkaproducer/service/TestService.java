package com.kafkaproducer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.kafkaproducer.entity.Order;

@Service
public class TestService {
	
	@Autowired
	private KafkaTemplate<String, String> strinKafkaTemplate;
	
	@Autowired
	private KafkaTemplate<String, Order> orderKafkaTemplate;
	
	public void sendMessage(String message) {
		System.out.println("TestService -- sendMessage()");
		strinKafkaTemplate.send("myjlctopic", message);
	}
	
	public void sendOrder(Order myorder) {
		System.out.println("TestService -- sendOrder()");
		orderKafkaTemplate.send("myordertopic", myorder);
	}
}
