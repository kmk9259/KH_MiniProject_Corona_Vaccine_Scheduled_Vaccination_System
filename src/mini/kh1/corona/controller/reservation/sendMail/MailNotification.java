  
package mini.kh1.corona.controller.reservation.sendMail;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import mini.kh1.corona.controller.reservation.Reservation;
import mini.kh1.corona.controller.user.BookerList;
import mini.kh1.corona.model.vo.Booker;

public class MailNotification {
	static BookerList list = new BookerList();
	static ArrayList<Booker> bookerlist = list.getBookerList();
	static List<Booker> age = list.getBookerList();
	static String name ;
	static String date ;
	static String hospital ;
	/*1. Session
	 * 2. port
	 * 3. message
	 * 4. Properties
	 * 5. SSL/TLS , Starttls
	 * SMTP : 인터넷에서 이메일을 보내기 위해 사용되는 프로토콜
	 * */
	public static void MailSend(String email, String title, String text) {
		//지메일을 이용한 메일전송
		//메일 전송 사람의 이메일과 비밀번호 세팅
	    String host = "smtp.gmail.com";
	    final String user = "이메일"; 
	    String sender = "이메일";
	    final String password = "비밀번호";
	    
	    //properties :키와 값을 String 타입 제한
	    Properties props = new Properties(); 
	    //starttls : 명시적 보안 설정, 자바메일에 tls모드 시작
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", host); 	
	    props.put("mail.smtp.port", 587); 
	    props.put("mail.smtp.auth", "true"); 
	    //ssl : 전송된 데이터 암호화 후 보안 유지
	    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");	//ssl설정 안하면 convert안됨
	    props.put("mail.smtp.ssl.protocols", "TLSv1.2");
                                                                             
        //session - sender
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
	public static void mailText()
	{
		Reservation r = new Reservation();
		String[][] dd = new String [age.size()/2][3];
		dd = r.cut();
		for(int i=0; i<age.size()/2; i++)
		{
			for(int j=0; j<3; j++)
			{
				System.out.println(dd[i][j]+"!");
				for(int k=0; k<bookerlist.size(); k++)
				{
					if(dd[i][j].equals(bookerlist.get(k).getName()))
					{
						hospital = bookerlist.get(k).gethName();
						name = bookerlist.get(k).getName();
						date = dd[i][2];
						String temp= name+"님, 이와 같이 예약이 완료되었습니다.\n"
									 +"√ 일시 : "+dd[i][2]+"\n"
									 +"√ 장소 : "+hospital+ "\n\n";
						String temp2 = "☑ 접종 후 유의사항\n\n"
										+"√ 접종 후 최소 3시간 이상 안정을 취하시고, 내일까지 무리하지 않습니다.\n"
										+"√ 최소 3일간은 특별한 증상이 나타나는지 주의 깊게 관찰합니다.\n"
										+"√ 만약 39도 이상의 고열이 있거나 두드러기 등 알레르기 반응이 나타나면 진료를 받도록 합니다.\n"
										+"√ 마스크 착용, 거리두기 등 코로나19 예방수칙을 계속 지켜주시기 바랍니다.";
						String text = temp+temp2;
								
						MailSend(bookerlist.get(k).getEmail(), mailTitle(), text);
					}
						
				}
			}
		}
		
	}


}
