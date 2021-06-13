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
import mini.kh1.corona.model.vo.user.User;
import mini.kh1.corona.view.InsertPage;

public class MailNotification {
	static BookerList list = new BookerList();
	static ArrayList<Booker> bookerlist = list.getBookerList();
	static String name ;
	static String date ;
	static String hospital ;
	//static List<User> age = InsertPage.temp.getJoinlist();//bookerlist
	static BookerList bookerlist2 = new BookerList();
	static List<Booker> age = bookerlist2.getBookerList();
	
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
	public static void mailText()
	{
		System.out.println("=====메일확인======");
		Reservation r = new Reservation();
		String[][] dd = new String [age.size()/2][3];
		dd = r.cut();
		for(int i=0; i<age.size()/2; i++)
		{
			for(int j=0; j<3; j++)
			{
				System.out.println(dd[i][j]+"!!");
				for(int k=0; k<bookerlist.size(); k++)
				{
					if(dd[i][j].equals(bookerlist.get(k).getName()))
					{
						hospital = bookerlist.get(k).gethName();
						name = bookerlist.get(k).getName();
						date = dd[i][2];
						String text= name+"님, 이와 같이 예약이 완료되었습니다.\n"
									 +"-일시 : "+dd[i][2]+"\n"
									 +"-장소 :"+hospital+ "\n\n";
						MailSend(bookerlist.get(k).getEmail(), mailTitle(), text);
					}
						
				}
				
				
			}
		}
		
		//return dd;
	}
	public void sendtoUser()
	{
		for(int i=0; i<bookerlist.size(); i++)
		{
			System.out.println(bookerlist.get(i).getEmail());
			//MailSend(bookerlist.get(i).getEmail(), mailTitle(), mailText());
			
		}
		
	}

}
