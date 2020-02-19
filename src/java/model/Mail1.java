/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testingmail;

import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Tieuphieu
 */
public class Mail1 {

    public Mail1() {
    }
    

    private String userName;
    private String passWord;
    private String host;
    private String port;
    private String starttls;
    private String auth;
    private boolean debug;
    private String socketFactoryClass;
    private String fallback;
    private List<String> to = null;
    private List<String> cc = null;
    private List<String> bcc = null;
    private String subject;
    private String text;

    public Mail1(String userName, String passWord, String host, String port, String starttls, String auth, boolean debug, String socketFactoryClass, String fallback,
            List<String> to, List<String> cc, List<String> bcc, String subject, String text) {
        this.userName = userName;
        this.passWord = passWord;
        this.host = host;
        this.port = port;
        this.starttls = starttls;
        this.auth = auth;
        this.debug = debug;
        this.socketFactoryClass = socketFactoryClass;
        this.fallback = fallback;
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
        this.text = text;
    }
    //This is for google

    //    Mail.sendMail("tomorrow.will.come89@gmail.com", "1314isdie", "smtp.gmail.com", "465", "true",
    //           "true", true, "javax.net.ssl.SSLSocketFactory", "false", to, cc, bcc,
    //         "hi this mail was send by Thanh",
    //         "This is my style...of reply..");
    // dG9tb3Jyb3cud2lsbC5jb21lODlAZ21haWwuY29t
    // MTMxNGlzZGll
    
  public  String sendEmail(String from, String to, String subject, String mailText, String fromPassword)
    {
       String temp = "Send email successful!!!";
       String smtpServer = "mail.saigontech.edu.vn";
       String userName = "kimlt07@saigontech.edu.vn";
       String password= "kim123";
       if(fromPassword!=null)
           password = fromPassword;
        if((to == null)  && (subject == null))
           temp = "tonull";
        if(mailText == null)
	        mailText = "";
        if(from == null)
	        from = "liemht@saigontech.edu.vn";
        try {
            Properties props = System.getProperties();
            props.put( "mail.smtp.host", smtpServer ) ;
            //SMTP server authentication is set to false, by default. Setting it to true as shown below
            props.put( "mail.smtp.auth", "true" ) ;
            Session session = Session.getDefaultInstance(props, null);
            MimeMessage message = new MimeMessage(session);
            //Setting the 'from', 'to', 'cc' addresses and the 'subject'
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            //Making the mail body as inline and of html type
            MimeMultipart mp = new MimeMultipart();
            MimeBodyPart text = new MimeBodyPart();
            //text.setDisposition(Part.INLINE);
            text.setContent(mailText, "text/html");
            mp.addBodyPart(text);
            message.setContent(mp);
            //SMTP authentication
            Transport transport = session.getTransport ("smtp") ;
            transport.connect (smtpServer, userName, password) ;
            message.saveChanges();
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
			//temp = "0k";
           // return temp;
        } catch (Exception e){
		  return e.getMessage();
        }
		//temp = "kkk";
	return temp;
}
    
    
//    public synchronized boolean sendMail() {
//        Properties props = new Properties();
//        //Properties props=System.getProperties();
//        props.put("mail.smtp.user", userName);
//        props.put("mail.smtp.host", host);
//        if (!"".equals(port)) {
//            props.put("mail.smtp.port", port);
//        }
//        if (!"".equals(starttls)) {
//            props.put("mail.smtp.starttls.enable", starttls);
//        }
//        props.put("mail.smtp.auth", auth);
//        if (debug) {
//            props.put("mail.smtp.debug", "true");
//        } else {
//            props.put("mail.smtp.debug", "false");
//        }
//        if (!"".equals(port)) {
//            props.put("mail.smtp.socketFactory.port", port);
//        }
//        if (!"".equals(socketFactoryClass)) {
//            props.put("mail.smtp.socketFactory.class", socketFactoryClass);
//        }
//        if (!"".equals(fallback)) {
//            props.put("mail.smtp.socketFactory.fallback", fallback);
//        }
//
//        try {
//            Session session = Session.getDefaultInstance(props, null);
//            session.setDebug(debug);
//            MimeMessage msg = new MimeMessage(session);
//            msg.setText(text);
//            msg.setSubject(subject);
//            msg.setFrom(new InternetAddress(userName));
//
//            if (to != null) {
//                for (String temp : to) {
//                    msg.addRecipient(Message.RecipientType.TO, new InternetAddress(temp));
//                }
//            }
//
//            if (cc != null) {
//                for (String temp : cc) {
//                    msg.addRecipient(Message.RecipientType.CC, new InternetAddress(temp));
//                }
//            }
//
//            if (bcc != null) {
//                for (String temp : bcc) {
//                    msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(temp));
//                }
//            }
//
//            msg.saveChanges();
//            Transport transport = session.getTransport("smtp");
//            transport.connect(host, userName, passWord);
//            transport.sendMessage(msg, msg.getAllRecipients());
//            transport.close();
//            return true;
//        } catch (Exception mex) {
//            mex.printStackTrace();
//            return false;
//        }
//    }
}
