package TestQueue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class MyConsumer {
	public static void main(String[] args) throws JMSException {
		String jmsProviderAddress = "tcp://localhost:61616";
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				jmsProviderAddress);

		Connection connection = connectionFactory.createConnection();
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);\\
		String destinationName = "testQueue";
		Destination dest = session.createQueue(destinationName);
		MessageConsumer consumer = session.createConsumer(dest);

		connection.start();

		Message message = consumer.receive();
		TextMessage textMessage = (TextMessage) message;

		String text = textMessage.getText();
		System.out.println("Received message from ActiveMQ: " + text);

		consumer.close();
		session.close();
		connection.close();

		System.out.println("Completed.");
	}
}
