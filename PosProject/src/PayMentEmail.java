import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class PayMentEmail{
	String host = "smtp.naver.com";            
	final String user = "deuackr2017@naver.com";   //�߽��� ���̵�
	final String password = "deu@2017";          //�߽��� ��й�ȣ
	
	public void SMTP(String toEmail,String setMessage){
		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		// Compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			message.setSubject("Pos�� ���� ����");//���� ����
			message.setText(setMessage);//���� ����

			// send the message
			Transport.send(message);
			System.out.println("message sent successfully...");

		} catch (MessagingException e) {
			e.printStackTrace();
		}	
	}
}

 

