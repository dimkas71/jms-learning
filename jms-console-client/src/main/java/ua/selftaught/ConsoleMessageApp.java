package ua.selftaught;

import java.util.Scanner;

import javax.jms.JMSException;

public class ConsoleMessageApp {

	private static final String QUEUE = "main queue";

	public static void main(String[] args) throws JMSException {
		

		Producer p = new Producer();
		p.create(QUEUE);
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter new message(to exit use 'Y')>");
		
		while (sc.hasNext()) {
			String toSend = sc.next();
			
			if (toSend.equalsIgnoreCase("Y")) {
				break;
			}
			
			p.sendMessage(toSend);
			
		}
		
		sc.close();
		
		p.close();

	}

}
