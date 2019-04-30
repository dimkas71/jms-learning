package ua.selftaught;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class JmsLearningApplication implements CommandLineRunner {

	private static final String QUEUE = "main queue";
	
	private static final Logger logger = LoggerFactory.getLogger(JmsLearningApplication.class);
	
	@Value("${spring.application.name}")
	private String applicationName;
	
	
	private JmsTemplate jmsTemplate;
	
	@Autowired
	public JmsLearningApplication(JmsTemplate template) {
		this.jmsTemplate = template;
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(JmsLearningApplication.class, args);
	}


	@JmsListener(destination = QUEUE)
	public void onMessage(String message) {
		logger.info("Message: " + message);
	}
	
	
	@Override
	public void run(String... args) throws Exception {
		logger.info("Hello {}", applicationName);
		jmsTemplate.send(QUEUE, (session) -> session.createTextMessage("Hello for everyone"));
	}

}
