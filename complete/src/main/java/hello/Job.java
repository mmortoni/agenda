package hello;

import java.io.Serializable;

public class Job implements Serializable {
    private String name;
    private String msg;
    private String cron;
    
	public Job(String name, String msg, String cron) {
		super();
		this.name = name;
		this.msg = msg;
		this.cron = cron;
	}
	
	public Job() {
		super();
	}
	
	public String getName() {
		return name;
	}
	public String getMsg() {
		return msg;
	}
	public String getCron() {
		return cron;
	}
}
