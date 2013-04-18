package com.fhi.framework.utils.email;

public class MailVo {
	//发件人
	private String sender;
	//收件人
	private String recipients;
	//抄送
	private String copyRecipients;
	//邮件标题
	private String mailTitle;
	//邮件开始
	private String mailHead;
	//邮件内容
	private String mailContent;
	//邮件签名
	private String mailTail;
	//邮件附件
	private String[] fileNames;
	//所属模块
	private String flag;
	/**
	 * 发件人
	 * @return
	 */
	public String getSender() {
		return sender;
	}
	/**
	 * 发件人
	 * @param sender
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}
	/**
	 * 收件人 多个以';'分开
	 * @return
	 */
	public String getRecipients() {
		return recipients;
	}
	/**
	 * 收件人 多个以';'分开
	 * @param recipients
	 */
	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}
	/**
	 * 抄送 多个以';'分开
	 * @return
	 */
	public String getCopyRecipients() {
		return copyRecipients;
	}
	/**
	 * 抄送 多个以';'分开
	 * @param copyRecipients
	 */
	public void setCopyRecipients(String copyRecipients) {
		this.copyRecipients = copyRecipients;
	}
	/**
	 * 邮件标题
	 * @return
	 */
	public String getMailTitle() {
		return mailTitle;
	}
	/**
	 * 邮件标题
	 * @param mailTitle
	 */
	public void setMailTitle(String mailTitle) {
		this.mailTitle = mailTitle;
	}
	/**
	 * 邮件头
	 * @return
	 */
	public String getMailHead() {
		return mailHead;
	}
	/**
	 * 邮件头
	 * @param mailHead
	 */
	public void setMailHead(String mailHead) {
		this.mailHead = mailHead;
	}
	/**
	 * 邮件内容
	 * @return
	 */
	public String getMailContent() {
		return mailContent;
	}
	/**
	 * 邮件内容
	 * @param mailContent
	 */
	public void setMailContent(String mailContent) {		
		this.mailContent = mailContent;
	}
	/**
	 * 邮件签名
	 * @return
	 */
	public String getMailTail() {
		
		return mailTail;
	}
	/**
	 * 邮件签名
	 * @param mailTail
	 */
	public void setMailTail(String mailTail) {
		this.mailTail = mailTail;
	}
	/**
	 * 附件
	 * @return
	 */
	public String[] getFileNames() {
		return fileNames;
	}
	/**
	 * 附件
	 * @param fileNames
	 */
	public void setFileNames(String[] fileNames) {
		this.fileNames = fileNames;
	}
	
	/**
	 * 返回邮件全部内容
	 * @return
	 */
	public String getMail(){
		StringBuilder mailStr = new StringBuilder();
		mailStr.append(this.mailHead);
		mailStr.append("<br>");
		mailStr.append(this.mailContent);
		mailStr.append("<br>----------------------------<br>");
		mailStr.append(this.mailTail);
		mailStr.append("<br>");
		return mailStr.toString().replaceAll("\r\n", "<br>");
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}	
}
