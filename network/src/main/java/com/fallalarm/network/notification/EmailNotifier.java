package com.fallalarm.network.notification;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class EmailNotifier  {
 
	private static final Logger logger = LoggerFactory.getLogger(EmailNotifier.class);
	
    private static Properties mailServerProperties;
    private static Session getMailSession;
    private static MimeMessage generateMailMessage;
    
    public static void main(String args[]) throws AddressException, MessagingException {
        generateAndSendEmail("10001","Test patient", "4", "cs595fallalarmemail1@gmail.com");
        System.out.println("Email notification sent successfully");
    }
 
    /**
     * Sends email notification
     * @param patientId
     * @param riskLevel
     * @param toAddress
     * @throws AddressException
     * @throws MessagingException
     */
    public static void generateAndSendEmail(String patientId, String patientName, String riskLevel, String toAddress) throws AddressException, MessagingException {
 
        System.out.println("\n 1st ===> setup Mail Server Properties..");
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587"); // TLS Port
        mailServerProperties.put("mail.smtp.auth", "true"); // Enable Authentication
        mailServerProperties.put("mail.smtp.starttls.enable", "true"); // Enable StartTLS
        System.out.println("Mail Server Properties have been setup successfully..");
 
        System.out.println("\n\n 2nd ===> get Mail Session..");
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
        generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(toAddress));
        generateMailMessage.setSubject("Patient ID -"+ patientId +" fall alert ");
        String emailBody = "Test email NPU fallalarm project";
        generateMailMessage.setContent(emailBody, "text/html");
        System.out.println("Mail Session has been created successfully..");
 
        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        Transport transport = getMailSession.getTransport("smtp");
        // Enter your correct gmail UserID and Password
        transport.connect("smtp.gmail.com", "cs595fallalarmemail1@gmail.com", "alarm123");
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }
}