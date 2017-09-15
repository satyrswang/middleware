package TestTopic;

import java.util.Scanner;\\

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class MySubscriber {

	public static void main(String[] args) {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(
				"tcp://localhost:61616");

		try {
			Connection connection = factory.createConnection();
			connection.start();

			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic("testTopic");
			MessageConsumer consumer = session.createConsumer(topic);

			consumer.setMessageListener(new MessageListener() ){
				public void onMessage(Message message) {
					TextMessage txtMsg = (TextMessage) message;
					try {
						System.out.println("Message received: "
								+ txtMsg.getText());
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			};

			System.out.println("Waiting for messages... Press ENTER to exit.");
			Scanner s = new Scanner(System.in);
			s.next();
			s.close();

			consumer.setMessageListener(null);

			consumer.close();
			session.close();
			connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}

		System.out.println("Completed.");
	}
}
