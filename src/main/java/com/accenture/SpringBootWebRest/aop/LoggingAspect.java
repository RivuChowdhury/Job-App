package com.accenture.SpringBootWebRest.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	
	private static final Logger LOGGER= LoggerFactory.getLogger(LoggingAspect.class);
	
	//return type, fully-qualified-class-name.methodname(args). * means for all and .. for args means for all
	/*'Before' is a point cut which means it is an expression used to specify when we want to advice to the call.*/
	@Before("execution(* com.accenture.SpringBootWebRest.service.JobService.getjob(..))")
	/*'JoinPoint' is used to get a hold(information) on the method called.e.g. here we get a hold on the getJob()
    method.getSignnature() gives us the name of the method called(getJob) in this case and we print it using getter.  */
	public void logMethodCall(JoinPoint jp) {
		LOGGER.info("Method called "+jp.getSignature().getName());
	}
	
	//@After("execution(* com.accenture.SpringBootWebRest.service.JobService.getjob(..))")
	@After("execution(* com.accenture.SpringBootWebRest.service.JobService.getjob(..)) || execution(* com.accenture.SpringBootWebRest.service.JobService.getAllJobs())")
	public void logMethodExecuted(JoinPoint jp) {
		LOGGER.info("Method executed "+jp.getSignature().getName());
	}
	
	@AfterThrowing("execution(* com.accenture.SpringBootWebRest.service.JobService.getjob(..)) || execution(* com.accenture.SpringBootWebRest.service.JobService.getAllJobs())")
	public void logMethodCrashed(JoinPoint jp) {
		LOGGER.info("Method has some issues "+jp.getSignature().getName());
	}
	
	@AfterReturning("execution(* com.accenture.SpringBootWebRest.service.JobService.getjob(..)) || execution(* com.accenture.SpringBootWebRest.service.JobService.getAllJobs())")
	public void logMethodExecutedSuccess(JoinPoint jp) {
		LOGGER.info("Method successfully executed "+jp.getSignature().getName());
	}

}
