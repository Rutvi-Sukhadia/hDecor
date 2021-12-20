package com.hdecor.model;

public class ConfigProperties {

	private static ConfigProperties instance;
	
	//Database Config
	private static  String dbUrl;
	private static  String dbUserName;
	private static String dbPassword;
	
	//FTP Utils Config
	private static String ftpServer;
	private static int fptPort;
	private static String ftpUserName;
	private static String ftpPassword;
	
	//Mail config
	private static String mailId;
	private static String mailPassword;
	
	//Local Upload/Download Folder path
	private static String folderPath;
	
	private ConfigProperties() {
		
	}
	
	public static ConfigProperties getInstance() {
		if(instance == null) {
			instance = new ConfigProperties();
		}
		return instance;
	}
	
	public static  String getDbUrl() {
		return dbUrl;
	}
	public static  void setDbUrl(String dbUrl) {
		ConfigProperties.dbUrl = dbUrl;
	}
	public static  String getDbUserName() {
		return dbUserName;
	}
	public static void setDbUserName(String dbUserName) {
		ConfigProperties.dbUserName = dbUserName;
	}
	public static String getDbPassword() {
		return dbPassword;
	}
	public static void setDbPassword(String dbPassword) {
		ConfigProperties.dbPassword = dbPassword;
	}

	public static String getFtpServer() {
		return ftpServer;
	}

	public static void setFtpServer(String ftpServer) {
		ConfigProperties.ftpServer = ftpServer;
	}

	public static int getFptPort() {
		return fptPort;
	}

	public static void setFptPort(int fptPort) {
		ConfigProperties.fptPort = fptPort;
	}

	public static String getFtpUserName() {
		return ftpUserName;
	}

	public static void setFtpUserName(String ftpUserName) {
		ConfigProperties.ftpUserName = ftpUserName;
	}

	public static String getFtpPassword() {
		return ftpPassword;
	}

	public static void setFtpPassword(String ftpPassword) {
		ConfigProperties.ftpPassword = ftpPassword;
	}

	public static String getMailId() {
		return mailId;
	}

	public static void setMailId(String mailId) {
		ConfigProperties.mailId = mailId;
	}

	public static String getMailPassword() {
		return mailPassword;
	}

	public static void setMailPassword(String mailPassword) {
		ConfigProperties.mailPassword = mailPassword;
	}

	public static String getFolderPath() {
		return folderPath;
	}

	public static void setFolderPath(String folderPath) {
		ConfigProperties.folderPath = folderPath;
	}
	
}
