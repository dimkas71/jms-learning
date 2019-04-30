package ua.selftaught;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {
	
	private static final String BROKER_URL = "tcp://localhost:61616";
	private static final String USER = "admin";
	private static final String PASSWORD = "admin";
	
	private Connection connection;
	private Session session;
	private MessageProducer producer;
	
	
	public void create(String destinationName) throws JMSException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USER, PASSWORD, BROKER_URL);
		connection = connectionFactory.createConnection();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Destination destination = session.createQueue(destinationName);
		
		producer = session.createProducer(destination);
	}
	
	public void close() throws JMSException {
		connection.close();
	}
	
	public void sendMessage(String aMessage) throws JMSException {
		TextMessage message = session.createTextMessage(aMessage);
		producer.send(message);
	}

}
