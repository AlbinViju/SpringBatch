package com.quest.spring.batch;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppMain {

	static Job job;
	static JobLauncher jobLauncher;
	static ApplicationContext context;
	private static String[] springConfig = {"spring/batch/config/spring-beans.xml"};
	
	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext(springConfig);
		
		job = (Job) context.getBean("helloWorldJob");
		jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		
		try {
			JobExecution execution = jobLauncher.run(job, new JobParameters());
			System.out.println("Exit Status: "+ execution.getStatus());
		} catch (JobExecutionAlreadyRunningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobRestartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobInstanceAlreadyCompleteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done");

	}

}
