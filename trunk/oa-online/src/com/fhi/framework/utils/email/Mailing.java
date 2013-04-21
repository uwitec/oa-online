package com.fhi.framework.utils.email;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
/**
 * @author 鑫缘
 * @author chankyic@gmail.com 
 * @author 2009年11月19日
 */
public class Mailing {

	private String smtpHost = "smtpcom.263xmail.com"; // 发送邮件服务器
	private Integer smtpPost=25; // 发送邮件服务器
	private String userName = "TE-Tracker@fortune-global.net"; // 邮件服务器登录用户名
	private String passWord = "888888"; // 邮件服务器登录密码
	private String from; // 发送人邮件地址
	private boolean mailDebug=false;

	/**
	 * 发送邮件
	 * @param String subject 邮件标题
	 * @param String content 邮件内容
	 * @param String[] toAddresses 收件人地址
	 * @param String[] ccAddresses 抄送人地址
	 * @param String[] fileDirs 文件绝对路径
	 * @param String[] fileNames 显示文件名(不含后缀名)
	 * @return 发送是否成功
	 */
	public boolean sendMessage(String subject,String content,String[] toAddresses,String[] ccAddresses,String[] fileDirs,String[] fileNames){
		try {	
			Properties props = new Properties();
			props.setProperty("mail.transport.protocol", "smtp");
			props.setProperty("mail.auth","true");
			props.setProperty("mail.debug", mailDebug?"true":"false");
			props.setProperty("mail.smtp.host", smtpHost); // Get session						
			
			Session session = Session.getInstance(props);
			//创建邮件
			Message message = new MimeMessage(session);
			//设置来源		
			if(from==null||"".equals(from)){
				message.setFrom(new InternetAddress(userName));
			}
			else{
				message.setFrom(new InternetAddress(from));
			}
			//邮件标题
			message.setSubject(subject);		
			
			//创建邮件空间
			Multipart multipart = new MimeMultipart();
			
			//创建邮件内容数据
			MimeBodyPart messageBodyPart = new MimeBodyPart(); // fill message
			//创建邮件文字内容
			messageBodyPart.setContent(content,"text/html;charset=UTF-8");
			//将邮件内容添加到邮件中
			multipart.addBodyPart(messageBodyPart); // Part two is attachment
			
			//设置附件
			if(fileDirs!=null){
				for(int i=0;i<fileDirs.length;i++){
//					String fileDir = new String(fileDirs[i].getBytes("UTF-8"),"ISO8859-1");
					String fileDir = fileDirs[i];
					//创见附件
					messageBodyPart = new MimeBodyPart();
					//设置附件文件地址
					DataSource source = new FileDataSource(fileDir);			
					//载入附件数据
					messageBodyPart.setDataHandler(new DataHandler(source));
					//设置附件显示名称 包括后缀
					if(fileNames!=null&&fileNames.length>i&&fileNames[i]!=null&&!"".equals(fileNames[i])){
						String fileName = new String(fileNames[i].getBytes("UTF-8"),"ISO8859-1");
						messageBodyPart.setFileName(fileName+fileDir.substring(fileDir.lastIndexOf(".")));
					}
					else{
						if(fileDir.lastIndexOf("/")>0){
							messageBodyPart.setFileName(fileDir.substring(fileDir.lastIndexOf("/")+1));
						}
						else{
							messageBodyPart.setFileName(fileDir.substring(fileDir.lastIndexOf("\\")+1));
						}
					}				
					//将附件添加到邮件中			
					multipart.addBodyPart(messageBodyPart); // Put parts in message
				}
			}
			
			//将邮件内容放入邮件中
			message.setContent(multipart); // Send the message			
			
			//整合发件地址 5 5
			Address[] addresses;
			if(ccAddresses==null){
				addresses = new Address[toAddresses.length];
			}
			else{
				addresses = new Address[toAddresses.length+ccAddresses.length];
			}
			
			//创建收件人地址
			Address[] to_Addresses = new Address[toAddresses.length];
			for(int i=0;i<toAddresses.length;i++){
				to_Addresses[i]=new InternetAddress(toAddresses[i]);
				addresses[i]=to_Addresses[i];
			}
			//创建抄送人地址
			if(ccAddresses!=null){
				Address[] cc_Addresses = new Address[ccAddresses.length];			
				for(int i=0;i<ccAddresses.length;i++){
					cc_Addresses[i]=new InternetAddress(ccAddresses[i]);
					addresses[toAddresses.length+i]=cc_Addresses[i];
				}
				//设置抄送人
				message.addRecipients(Message.RecipientType.CC,cc_Addresses);
			}
			Transport transport = session.getTransport();
			//连接服务器
			transport.connect(smtpHost,smtpPost, userName, passWord);
			
			//设置收件人
			message.addRecipients(Message.RecipientType.TO,to_Addresses);			
			
			//发送邮件
			transport.sendMessage(message,addresses);
			
			transport.close();
			return true;
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(UnsupportedEncodingException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 发送邮件
	 * @param mailVo
	 * @return 发送是否成功
	 */
	public boolean sendMessage(MailVo mailVo){
		return this.sendMessage(mailVo.getMailTitle(), mailVo.getMailContent(), mailVo.getRecipients().split(";"), mailVo.getCopyRecipients().split(";"), mailVo.getFileNames(),null);
	}

	/**
	 * 设置发件服务器地址
	 * @param smtpHost
	 */
	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	/**
	 * 设置发件服务器端口
	 * @param smtpPost
	 */
	public void setSmtpPost(Integer smtpPost) {
		this.smtpPost = smtpPost;
	}

	/**
	 * 设置发件服务器账号
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 设置发件服务器密码
	 * @param passWord
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	/**
	 * 设置发件人
	 * @param from
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	
	/**
	 * 是否在控制台打出调试信息
	 * @param mailDebug
	 */
	public void setMailDebug(boolean mailDebug) {
		this.mailDebug = mailDebug;
	}	
}
