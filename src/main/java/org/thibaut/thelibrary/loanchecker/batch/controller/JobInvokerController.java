package org.thibaut.thelibrary.loanchecker.batch.controller;
 
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("*")
@AllArgsConstructor
public class JobInvokerController {
 
    JobLauncher jobLauncher;
 
    Job processJob;

    public static Boolean jobFinished;
 
    @RequestMapping("/invokejob")
    public String handle() throws Exception {

    	jobFinished = false;

        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(processJob, jobParameters);


 
        return "Batch job has been invoked";
    }
}
