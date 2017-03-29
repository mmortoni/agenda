package agenda;

import org.quartz.Scheduler;

public class Agenda {
    private String nameJob;
    private String nameJobDetail;
    Scheduler scheduler;
	public Agenda(String nameJob, String nameJobDetail, Scheduler scheduler) {
		super();
		this.nameJob = nameJob;
		this.nameJobDetail = nameJobDetail;
		this.scheduler = scheduler;
	}
	
	public String getNameJob() {
		return nameJob;
	}
	public void setNameJob(String nameJob) {
		this.nameJob = nameJob;
	}
	public String getNameJobDetail() {
		return nameJobDetail;
	}
	public void setNameJobDetail(String nameJobDetail) {
		this.nameJobDetail = nameJobDetail;
	}
	public Scheduler getScheduler() {
		return scheduler;
	}
	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}
}
