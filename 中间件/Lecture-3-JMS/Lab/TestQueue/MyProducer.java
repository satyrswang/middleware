package TestQueue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class MyProducer {

	public static void main(String[] args) throws JMSException {

		String jmsProviderAddress = "tcp://localhost:61616";
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				jmsProviderAddress);

		Connection connection = connectionFactory.createConnection();
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);
		Destination dest = session.createQueue("testQueue");
		MessageProducer producer = session.createProducer(dest);

		Message message = session.createTextMessage("Hello World");
		producer.send(message);

		producer.close();
		session.close();
		connection.close();

		System.out.println("Message sent.");
	}
}
