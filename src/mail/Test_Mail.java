package mail;

import org.testng.annotations.Test;
import java.util.Date;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Test_Mail {
	// SMTP address (e.g. smtp.163.com or IP)
	public static String mySMTP = "10.8.161.18"; // Email Security
//	public static String mySMTP = "10.8.161.160"; // Exchange
//	public static String mySMTP = "10.8.162.173"; // NSA 2700

	// Sender account of mailbox
//	public static String from_add = "bad@ddt.com";
//	public static String from_add = "YahooNews@jpn.com";
	public static String from_add = "google@test.com";
	public static String from_pwd = "123";

	// Receiver account of mailbox
//	public static String to_add = "Administrator@ddt.com";
	public static String to_add = "jason@ddt.com";

	@Test
	public void mainTest() throws Exception {
		// Basic Settings
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.host", mySMTP);
		props.setProperty("mail.smtp.auth", "false");

		// To open the code below /* ... */ to enable SSL
		/*
		 * e.g. SMTP(SSL) for QQ: 465/587 smtpPort = "465";
		 * props.setProperty("mail.smtp.port", smtpPort);
		 * props.setProperty("mail.smtp.socketFactory.class",
		 * "javax.net.ssl.SSLSocketFactory");
		 * props.setProperty("mail.smtp.socketFactory.fallback", "false");
		 * props.setProperty("mail.smtp.socketFactory.port", smtpPort);
		 */

		// Configure session
		Session session = Session.getInstance(props);
		// Debug mode to show log
		session.setDebug(true);

		// Create an email
		MimeMessage message = createMimeMessage(session, from_add, to_add);
		Transport transport = session.getTransport();

		/*
		 * Error as below, 1. Mail server is not opening SMTP service. 2. Wrong
		 * credentials due to special password configured on mail server. 3. SSL is
		 * required for certain mail server. 4. Too many times access for certain mail
		 * server. 5. Read clearly in log. All errors are recorded in log!!
		 */

		// Connect
		transport.connect(from_add, from_pwd);

		// Send email including TO, CCs
		transport.sendMessage(message, message.getAllRecipients());

		// Close
		transport.close();
	}

	@Test
	public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail) throws Exception {
		// 1. Create a new email
		MimeMessage message = new MimeMessage(session);

		// 2. From: sender
		message.setFrom(new InternetAddress(sendMail, "mail test", "UTF-8"));

		// 3. To: recipient
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "recipient", "UTF-8"));

		// 4. Subject: title
//		message.setSubject("トクプラ 500円以下＆送料無料！", "UTF-8");
//		message.setSubject("PayPayモールのおすすめストア", "UTF-8");
		message.setSubject("お米・お肉や果物が人気！", "UTF-8");
//		message.setSubject("オミクロン対応接種９月開始検討", "UTF-8");
//		message.setSubject("パキスタン洪水 国土の３分の１水没", "UTF-8");
//		message.setSubject("「東宝シンデレラ」朝ドラで躍進", "UTF-8");
//		message.setSubject("東京ディズニーリゾート、パークでのマスク着用ルール９月１日より変更", "UTF-8");
//		message.setSubject("Happy Everyday!", "UTF-8");

		// 5. Content: text
//		message.setContent("Hello! This is a test mail.", "text/plain");
//		message.setContent("spam junk 22222222222", "text/plain");
		message.setContent("おはようございます！登録をお願いします。", "text/plain");

		// 6. Time
		message.setSentDate(new Date());

		// 7. Save
		message.saveChanges();

		return message;
	}

}
