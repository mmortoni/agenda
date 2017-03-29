package agenda;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import agenda.AgendaJob;
import agenda.Agenda;

import hello.Job;

public class ExecutaJob {
    static AtomicInteger counter = new AtomicInteger();
    static List<Agenda> jobsEmExecucao = new ArrayList<Agenda>();
    
	public static void executa(Job job) {
		
		try {
			String nameJobDetail =  "jobD" + counter.incrementAndGet();
			String nameTrigger =  "cronTriggerT" + counter.incrementAndGet();
			
			JobDetail jobDetail = JobBuilder.newJob(AgendaJob.class).withIdentity(nameJobDetail, "group1").build();
			
			jobDetail.getJobDataMap().put(AgendaJob.NAME, job.getName());
			jobDetail.getJobDataMap().put(AgendaJob.MSG, job.getMsg());

			Trigger trigger = TriggerBuilder.newTrigger().withIdentity(nameTrigger, "group1")
					.withSchedule(CronScheduleBuilder.cronSchedule(job.getCron())).build();

			Agenda agenda = new Agenda(job.getName(), nameJobDetail, new StdSchedulerFactory().getScheduler());
			agenda.getScheduler().start();
			agenda.getScheduler().scheduleJob(jobDetail, trigger);
			jobsEmExecucao.add(agenda);
			
//			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
//			scheduler.start();
//			scheduler.scheduleJob(jobDetail, trigger);
			
			Thread.sleep(100000);
			
			agenda.getScheduler().shutdown();
			
			if(jobsEmExecucao.size() > 0)
				jobsEmExecucao.remove(jobsEmExecucao.size()-1);
			
//			scheduler.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteJob(Job job) {
    	for (int i = 0; i < jobsEmExecucao.size(); i++) {
    		if(jobsEmExecucao.get(i).getNameJob().equalsIgnoreCase(job.getName())) {
    			try {
					//jobsEmExecucao.get(i).getScheduler().shutdown();
					jobsEmExecucao.get(i).getScheduler().deleteJob(JobKey.jobKey(job.getName(), "group1"));
	    			//jobsEmExecucao.remove(i);
				} catch (SchedulerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			break;
    		}
    	}
	}
}
