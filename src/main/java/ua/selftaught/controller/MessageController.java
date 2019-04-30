package ua.selftaught.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.selftaught.JmsLearningApplication;

@RestController
@RequestMapping("api")
public class MessageController {

	private JmsTemplate jmsTemplate;
	
	@Autowired
	public MessageController(JmsTemplate template) {
		this.jmsTemplate = template;
	}
	
	private List<String> messages = new ArrayList<>();
	
	@GetMapping("messages")
	public List<String> messages() {
		return Collections.unmodifiableList(messages);
	}
	
	@PostMapping("newMessage/{message}")
	public String addMessage(@PathVariable("message") String newMessage) {
		
		jmsTemplate.send(JmsLearningApplication.QUEUE, (s) -> s.createTextMessage(newMessage));
		
		messages.add(newMessage);
		return newMessage;
	}
	
	
}
