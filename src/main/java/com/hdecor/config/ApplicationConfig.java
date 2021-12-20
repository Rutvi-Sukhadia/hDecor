package com.hdecor.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.hdecor.model.ConfigProperties;

@Configuration
@ComponentScan({ "com.hdecor.config" })
@PropertySource(value = { "classpath:config.properties" })
public class ApplicationConfig {

	@Autowired
	public Environment environment;
	
	@Autowired
	private static ConfigProperties configObject;
	
	@Bean
	@Autowired
	public ConfigProperties setConfigProperties() {
		configObject= ConfigProperties.getInstance();
		ConfigProperties.setDbUrl(environment.getRequiredProperty("jdbc.url"));
		ConfigProperties.setDbUserName(environment.getRequiredProperty("jdbc.username"));
		ConfigProperties.setDbPassword(environment.getRequiredProperty("jdbc.password"));
		ConfigProperties.setFtpServer(environment.getRequiredProperty("ftp.server"));
		ConfigProperties.setFtpUserName(environment.getRequiredProperty("ftp.username"));
		ConfigProperties.setFptPort(Integer.parseInt(environment.getRequiredProperty("ftp.port")));
		ConfigProperties.setFtpPassword(environment.getRequiredProperty("ftp.password"));
		ConfigProperties.setMailId(environment.getRequiredProperty("mail.emailId"));
		ConfigProperties.setMailPassword(environment.getRequiredProperty("mail.password"));
		ConfigProperties.setFolderPath(environment.getRequiredProperty("loadfolder.path"));
		System.out.print(environment.getRequiredProperty("loadfolder.path"));
		return configObject;
	}
	
}
