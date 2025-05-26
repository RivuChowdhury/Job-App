package com.accenture.SpringBootWebRest.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerfomanceMonitorAspect {
	private static final Logger LOGGER= LoggerFactory.getLogger(PerfomanceMonitorAspect.class);
	
	/*Before advice is executed before the method called and After advice is executed after the method called.
	 In case we want an advice at both before and after the method execution i.e. around the method execution
	 we use Around advice.*/
	@Around("execution(* com.accenture.SpringBootWebRest.service.JobService.getjob(..))")
	//@Around("execution(* com.accenture.SpringBootWebRest.service.JobService.*(..))") //for all methods inside JobService class.
	public Object monitorTime(ProceedingJoinPoint jp) throws Throwable {
		long start=System.currentTimeMillis();
		/*This proceed method is used to call the method that is needed to be called and executed like getjob() 
		method in this case.This proceed() method return Object hence for this proceed method to work we have
		to create one instance variable of Object type and return that object.  */
		Object obj=jp.proceed();
		long end=System.currentTimeMillis();
		
		LOGGER.info("Time taken: "+jp.getSignature().getName()+" "+(end-start)+" ms");
		return obj; 
	}

}
