package com.sitech.shop.webservice.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



import org.springframework.stereotype.Service;

import com.sitech.ssd.billing.vo.sendMessage.MailSenderInfo;

@Service("sendMessageUtilService")
@Path("/sendMessage")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SendMessageService {

	/**
	 * 以文本格式发送邮件
	 * 
	 * @param mailInfo
	 *            待发送的邮件的信息
	 */
	@POST
	@Path("/sendTextMail")
	public MailSenderInfo sendTextMail(MailSenderInfo mailInfo) {
		mailInfo.setIsSuccess(true);
		
		System.out.println("邮件类型：文本格式的邮件");
		System.out.println("邮件标题"+mailInfo.getSubject());
		System.out.println("邮件内容"+mailInfo.getContent());
		System.out.println("邮件接收人地址"+mailInfo.getToAddress());
		
/*		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate()) {
			// 如果需要身份认证，则创建一个密码验证器
			authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			// Address to = new InternetAddress(mailInfo.getToAddress());
			Address[] to = InternetAddress.parse(mailInfo.getToAddress());
			mailMessage.setRecipients(Message.RecipientType.TO, to);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// 设置邮件消息的主要内容
			String mailContent = mailInfo.getContent();
			mailMessage.setText(mailContent);
			// 发送邮件
			Transport.send(mailMessage);
			mailInfo.setIsSuccess(true);
		} catch (MessagingException ex) {
			mailInfo.setIsSuccess(false);
			ex.printStackTrace();
		}*/
		return mailInfo;
	}

	/**
	 * 以HTML格式发送邮件
	 * 
	 * @param mailInfo
	 *            待发送的邮件信息
	 */
	@POST
	@Path("/sendHtmlMail")
	public MailSenderInfo sendHtmlMail(MailSenderInfo mailInfo) {
		
		
		System.out.println("邮件类型：网页格式的邮件");
		System.out.println("邮件标题"+mailInfo.getSubject());
		System.out.println("邮件内容"+mailInfo.getContent());
		System.out.println("邮件接收人地址"+mailInfo.getToAddress());
		
	/*	// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		// 如果需要身份认证，则创建一个密码验证器
		if (mailInfo.isValidate()) {
			authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address to = new InternetAddress(mailInfo.getToAddress());
			// Message.RecipientType.TO属性表示接收者的类型为TO
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();
			// 创建一个包含HTML内容的MimeBodyPart
			BodyPart html = new MimeBodyPart();
			// 设置HTML内容
			html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
			mainPart.addBodyPart(html);
			// 将MiniMultipart对象设置为邮件内容
			mailMessage.setContent(mainPart);
			// 发送邮件
			Transport.send(mailMessage);
			mailInfo.setIsSuccess(true);
		} catch (MessagingException ex) {
			mailInfo.setIsSuccess(false);
			ex.printStackTrace();
		}*/

		return mailInfo;
	}

	/**
	 * 发送手机短信
	 * 
	 * @param mailInfo
	 * @return
	 */
	@POST
	@Path("/sendSms")
	public MailSenderInfo sendSms(MailSenderInfo mailInfo) {
		
		mailInfo.setIsSuccess(true);
		System.out.println("短信内容"+mailInfo.getSmsContent());
		System.out.println("短信接收人号码"+mailInfo.getPhoneNum());
		

/*		String content;
		try {
			content = URLEncoder.encode(mailInfo.getSmsContent(), "UTF-8");
			SmsUtil.sendMessage(mailInfo.getPhoneNum(), content, "", "", "");
			mailInfo.setIsSuccess(true);
		} catch (Exception e) {
			mailInfo.setIsSuccess(false);
			e.printStackTrace();
		}
*/
		return mailInfo;

	}

}
