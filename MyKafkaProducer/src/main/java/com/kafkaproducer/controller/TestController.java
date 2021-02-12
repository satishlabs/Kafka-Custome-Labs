package com.kafkaproducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafkaproducer.entity.Order;
import com.kafkaproducer.service.TestService;

@RestController
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@GetMapping("/testMsg")
	public String testMessage() {
		System.out.println("TestController --  testMessage()");
		testService.sendMessage("Hello Guys - This is message1");
		testService.sendMessage("Hello Guys - This is message2");
		return "String message sent to Kafka Server";
	}
	
	@GetMapping("/testOrder")
	public String placeOrder() {
		System.out.println("TestController --  placeOrder()");
		Order myorder = new Order(5001, "8the Feb,2021", "Satish", 5, 5000, "New");
		
		testService.sendOrder(myorder);
		testService.sendOrder(myorder);
		
		return "Order sent to Kafak Server @";
	}
}
