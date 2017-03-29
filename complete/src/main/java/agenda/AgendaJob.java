package agenda;

import java.util.Date;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class AgendaJob implements Job {
	public static final String NAME = "name";
	public static final String MSG = "msg";
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		
		System.out.println(dataMap.getString(NAME) + "-> " + dataMap.getString(MSG) + ", hora de execução: " + new Date());
	}
}
