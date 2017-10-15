package it.unisannio.bank.interceptor;



import java.util.Date;

import javax.annotation.Resource;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Interceptor {
	
	@Resource(name = "java:/ConnectionFactory")
	private QueueConnectionFactory factory;
	@Resource(name = "java:/jms/queue/unisanniobankQueue")
	private Queue queue;

	private static final String DEPOSIT = "deposit";
	private static final String WITHDRAW = "withdraw"; 
	private static final String TRANSFER = "transfer";
	@AroundInvoke
	public Object profile(InvocationContext context) throws Exception {

		QueueConnection connection = factory.createQueueConnection();
		QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		connection.start();
		QueueSender sender = session.createSender(queue);

		try {
			String methodName = context.getMethod().getName();

			Object[] parameters = context.getParameters();
			
			if(methodName.equals(DEPOSIT) || methodName.equals(WITHDRAW)){	
				double value = (double) parameters[0];
				int idAccount = (int) parameters[1];
				
				TextMessage msg = session.createTextMessage();
				msg.setStringProperty("operation", methodName);
				msg.setLongProperty("date", new Date().getTime());
				msg.setIntProperty("accountId", idAccount);
				msg.setDoubleProperty("amount", value);
				sender.send(msg);

			}else if(methodName.equals(TRANSFER)){
				
				int source = (int) parameters[0];
				int destination = (int) parameters[1];
				double value = (double) parameters[2];
				
				TextMessage withdraw_msg = session.createTextMessage();
				withdraw_msg.setStringProperty("operation", WITHDRAW);
				withdraw_msg.setLongProperty("date", new Date().getTime());
				withdraw_msg.setIntProperty("accountId", source);
				withdraw_msg.setDoubleProperty("amount", value);
				sender.send(withdraw_msg);
				
				TextMessage deposit_msg = session.createTextMessage();
				deposit_msg.setStringProperty("operation", DEPOSIT);
				deposit_msg.setLongProperty("date", new Date().getTime());
				deposit_msg.setIntProperty("accountId", destination);
				deposit_msg.setDoubleProperty("amount", value);
				sender.send(deposit_msg);

			}
			
			


			session.close();
			connection.close();
			
			
		}catch(Exception e){
			System.out.println("[INTERCEPTOR] - ERROREEEEEEEE");
		}
		
		return context.proceed();
		
	}
}
