package hello;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hello.Job;

import agenda.ExecutaJob;

@RestController
public class JobController {

    private List<Job> listOfJobs = new ArrayList<Job>();

    public List<Job> getListOfJobs() {
		return this.listOfJobs;
	}

	public void setJob(Job job) {
		this.listOfJobs.add(job);
	}

	@RequestMapping(value = "/jobs", method = RequestMethod.GET)
    public ResponseEntity<List<Job>>  getJobs() {
	    return new ResponseEntity<List<Job>>(this.getListOfJobs(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/jobs", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseEntity<Job> createJob(@RequestBody Job job) {
    	this.setJob(job);
    	ExecutaJob.executa(job);
		return new ResponseEntity<Job>(job, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/jobs/{name}", method = RequestMethod.DELETE, headers="Accept=application/json")
    public ResponseEntity<String> deleteJob(@PathVariable String name) {

    	for (int i = 0; i < this.listOfJobs.size(); i++) {
    		if(this.listOfJobs.get(i).getName().equalsIgnoreCase(name)) {
    			ExecutaJob.deleteJob(this.listOfJobs.get(i));
    			this.listOfJobs.remove(i);
    			return new ResponseEntity<String>(name, HttpStatus.OK);
    		}
    	}
    	
		return new ResponseEntity<String>("No Job found for Name " + name, HttpStatus.NOT_FOUND);
	}
}
