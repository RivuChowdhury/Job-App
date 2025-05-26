package com.accenture.SpringBootWebRest.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidationAspect {
	private static final Logger LOGGER= LoggerFactory.getLogger(ValidationAspect.class);
	/*Around advice can be used to validate and update any error made by user.For example,suppose the user have
	written the url as http://localhost:8080/jobPost/-2 (-ve 2) instead of http://localhost:8080/jobPost/2.
	We can check the url before call getjob() method and update the -2 to 2.To do this, alongwith the directory
	of the method to be called(e.g. getjob()) we also need to pass the argument(i.e. postId) to correct the
	postId. */
	@Around("execution(* com.accenture.SpringBootWebRest.service.JobService.getjob(..)) && args(postId)")
	/*Here, alongwith the ProceedingJointPoint to proceed to call the getjob() method,we are passing the
	postId parameter of the getjob() method we get from the url to validate and if needed correct it
	(negative to positive in this case.*/
	public Object validateAndUpdate(ProceedingJoinPoint jp,int postId) throws Throwable {
		if(postId<0) {
			LOGGER.info("PostId is negative,updating it");
			postId=-postId;
			LOGGER.info("new value "+postId);
		}
		/*proceed() methods are of two types.One that don't take any parameter and one that takes Object array
		 as parameter.In this case, as we are updating the postId parameter and we have to send this updated 
		 value back to the getjob() method, hence we are creating an object array and passing postId as element.*/
		Object obj=jp.proceed(new Object[] {postId});
		return obj;
	}

}
