package net.jjwallace.springrestcrmlearn.config;



import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableJpaRepositories(
		basePackages ="net.jjwallace.springrestcrmlearn"
					   )
@ComponentScan("net.jjwallace.springrestcrmlearn")
@EnableWebMvc
@EnableTransactionManagement
@PropertySource(value = "classpath:/persistence-mysql.properties")
public class CRMConfig implements WebMvcConfigurer{
	
	@Autowired
	private Environment env;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/resources/", "/resources/","/");
	}
	
	@Bean
	public DataSource dataSource() {
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource(); 
		String jdbcUrl = env.getProperty("jdbc.url");
		String user =env.getProperty("jdbc.user");
		String password = env.getProperty("jdbc.password");
		String driver = env.getProperty("jdbc.driver");
		int initialPoolSize = Integer.parseInt(env.getProperty("connection.pool.initialPoolSize"));
		int minPoolSize = Integer.parseInt(env.getProperty("connection.pool.minPoolSize"));
		int maxPoolSize = Integer.parseInt(env.getProperty("connection.pool.maxPoolSize"));
		int maxIdleTime = Integer.parseInt(env.getProperty("connection.pool.maxIdleTime"));
		
		logger.info(">>>> jdbc.url = )" + jdbcUrl);
		logger.info(">>>> jdbc.url = )" + user);
		logger.info(">>>> jdbc.url = )" + driver);	
		try {
			securityDataSource.setDriverClass(driver);
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}
		securityDataSource.setJdbcUrl(jdbcUrl);
		securityDataSource.setUser(user);
		securityDataSource.setPassword(password);

		securityDataSource.setInitialPoolSize(initialPoolSize);
		securityDataSource.setMaxIdleTime(maxIdleTime);
		securityDataSource.setMaxPoolSize(maxPoolSize);
		securityDataSource.setMinPoolSize(minPoolSize);
		
		return securityDataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		String packagesToScan = env.getProperty("hibernate.packagesToScan");
		
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(packagesToScan);
		sessionFactory.setHibernateProperties(hibernateProperties());
		
		return sessionFactory;
		
	}
	@Bean
	Properties hibernateProperties() {
		
		Properties hibernateProperties = new Properties();
		String dialect = env.getProperty("hibernate.dialect");
		String showSql = env.getProperty("hibernate.show_sql");
		
		hibernateProperties.setProperty("hibernate.show_sql", showSql);
		hibernateProperties.setProperty("hibernate.dialect", dialect);
		
		return hibernateProperties;
	}
	@Bean
	public PlatformTransactionManager myTransactionManager() {
		
		HibernateTransactionManager myTransactionManager = new HibernateTransactionManager();
		myTransactionManager.setSessionFactory(sessionFactory().getObject());
		
		return myTransactionManager;
		
	}



}
 