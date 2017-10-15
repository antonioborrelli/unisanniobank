package it.unisannio.bank.application;

import java.sql.Date;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.ejb3.annotation.ResourceAdapter;

import it.unisannio.bank.model.Account;
import it.unisannio.bank.model.Log;

@MessageDriven(name = "LogConsumer", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "unisanniobankQueue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
@ResourceAdapter(value = "activemq-ra.rar")
public class LogConsumer implements MessageListener {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void onMessage(Message msg) {
		// TODO Auto-generated method stub
		try {
			Log log = new Log();
			
			String operation = msg.getStringProperty("operation");
			Date date = new Date(msg.getLongProperty("date"));
			int accountId = msg.getIntProperty("accountId");
			double amount = msg.getDoubleProperty("amount");

			log.setOperation(operation);
			log.setData(date);
			log.setIdAccount(accountId);
			log.setAmount(amount);
			

			
			Account account = entityManager.find(Account.class, accountId);
			account.addLog(log);
			
			entityManager.persist(account);
		
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
