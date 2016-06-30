package no.aoksenholt.deltagerwebscorer.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import no.aoksenholt.deltagerwebscorer.writer.WebscorerWriter;

public class JobCompletionNotificationListener implements JobExecutionListener {
    Log log = LogFactory.getLog(getClass());

    WebscorerWriter writer;

    public JobCompletionNotificationListener(WebscorerWriter writer) {
	this.writer = writer;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
	if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
	    log.info("!!! JOB FINISHED! Time to verify the results");

	    writer.writeFile();
	}
    }

    @Override
    public void beforeJob(JobExecution arg0) {
	// TODO Auto-generated method stub

    }

}
