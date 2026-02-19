package com.accenture.SpringBootWebRest;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
/*Profile annotation is used to tell the Spring Boot to create a bean only when a particular profile is set. For example,
in this case we have done @Profile("dev") which means that the beans(objects) of this DBConnection class will only be 
created when we are using the dev environment from the application-dev.properties.If we use some other environment like 
qa,prod etc environment then the beans of this DBConnection class will not be created.*/
@Profile("dev")
public class DBConnection {
    private static final Logger logger=LoggerFactory.getLogger(DBConnection.class);
    
	@Value("${spring.datasource.url}")
	private String dburl;
	@Value("${spring.datasource.username}")
	private String dbUsername;
	@Value("${spring.datasource.password}")
	private String dbPassword;
	
	/*This @PostConstruct annotation is used when we want to call a method at the startup when beans are create.The method
	 which has the @PostConstruct annotation must be "Takes no parameter, returns nothing(void)" type.  */
	@PostConstruct
	public void dbconnection() {
		System.out.println("DB connection established.DB url: "+ dburl+" DB username: "+dbUsername+" Db password: "+dbPassword);
		//logger.info("DB connection established.DB url:{}, DB username:{}, Db password: {}",dburl,dbUsername,dbPassword);
	}
 
}
