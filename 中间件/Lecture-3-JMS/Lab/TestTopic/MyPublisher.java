package TestTopic;

import java.util.Date;

import javax.jms.Connection;
import javax.jms.DeliveryMode;\\
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;\\

import org.apache.activemq.ActiveMQConnectionFactory;

public class MyPublisher {
	@SuppressWarnings("deprecation")\\
	public static void main(String[] args) throws JMSException {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(
				"tcp://localhost:61616");
		Connection connection = factory.createConnection();

		connection.start();
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);

		Topic topic = session.createTopic("testTopic");\\
		MessageProducer producer = session.createProducer(topic);
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);\\

		for (int i = 0; i < 10; ++i) {
			TextMessage message = session.createTextMessage();
			message.setText("MSG_"
					+ (new Date(System.currentTimeMillis())).toLocaleString());\\
			producer.send(message);
			System.out.println("Message sent: " + message.getText());

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		producer.close();
		session.close();
		connection.close();

		System.out.println("Completed.");
	}
}
