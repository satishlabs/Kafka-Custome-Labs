package com.kafkaconsumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka.entity.Order;

@Service
public class TestService {

	//Receving string message
	@KafkaListener(topics = "myjlctopic", groupId = "myjlc_group",containerFactory = "stringKafkaListenerContainerFactory")
	public void receiveMessage(String msg) {
		System.out.println("TestService -- receiveMessage()");
		System.out.println(msg);
	}
	
	
	//Receving the order details 
	@KafkaListener(topics = "myordertopic", groupId = "myorder_group",containerFactory = "orderKafkaListenerContainerFactory" )
			public void receiveOrder(Order  myorder) {
				System.out.println("TestService -- receiveOrder()");
				System.out.println(myorder);
			}
}
