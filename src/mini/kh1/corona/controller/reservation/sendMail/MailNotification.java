package mini.kh1.corona.controller.reservation.sendMail;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import mini.kh1.corona.controller.user.UserList;

public class MailNotification {
	
	public static void MailSend(String email, String title, String text) { 
	    String host = "smtp.gmail.com";
	    final String user = "alsrud9259@gmail.com"; 
	    String sender = "alsrud9259@gmail.com";
	    final String password = "alsrud31017";

	    Properties props = new Properties(); 
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", host); 	
	    props.put("mail.smtp.port", 587); 
	    props.put("mail.smtp.auth", "true"); 
	    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");	//ssl설정 안하면 convert안됨
	    props.put("mail.smtp.ssl.protocols", "TLSv1.2");
                                                                             
        //인증	    
	    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() { 
	        protected javax.mail.PasswordAuthentication getPasswordAuthentication() { 
	            return new PasswordAuthentication(user, password); 
	        } 
	    }); 
	    
	    try { 
	        MimeMessage message = new MimeMessage(session); 
            //받는사람 메일
	        message.setFrom(new InternetAddress(sender)); 
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email)); 

	        // 메일 제목 
	        message.setSubject(title); 
	        // 메일 내용 
	        message.setText(text); 
	        
	        Transport.send(message); 
	        System.out.println("메일 전송 성공");
	    } catch (MessagingException e) {
	        e.printStackTrace(); 
	    } 
	}
	public static String mailTitle()
	{
		String title="[코로나 예방 접종 확인 안내]";
		return title;
	}
	public static String mailText()
	{
		String text="이와 같이 예약이 완료되었습니다.\n"
				+ "-일시 : 2021년 06월 01일\n" + "-장소 : 서울시 강남구 A병원\n";
		return text;
	}
	public void sendtoUser()
	{
		for(int i=0; i<UserList.UserList().size(); i++)
		{
			System.out.println(UserList.UserList().get(i).getEmail());
			MailSend(UserList.UserList().get(i).getEmail(), mailTitle(), mailText());
		}
		
	}

}
