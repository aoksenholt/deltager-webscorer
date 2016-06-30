package no.aoksenholt.deltagerwebscorer.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.poi.PoiItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import no.aoksenholt.deltagerwebscorer.listener.JobCompletionNotificationListener;
import no.aoksenholt.deltagerwebscorer.mapper.DeltagerRowMapper;
import no.aoksenholt.deltagerwebscorer.model.Deltager;
import no.aoksenholt.deltagerwebscorer.model.Webscorer;
import no.aoksenholt.deltagerwebscorer.processor.DeltagerProcessor;
import no.aoksenholt.deltagerwebscorer.writer.WebscorerWriter;

@Configuration
@EnableBatchProcessing
public class DeltagerWebscorerConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public PoiItemReader<Deltager> excelReader() {
	PoiItemReader<Deltager> reader = new PoiItemReader<>();
	reader.setLinesToSkip(1);
	reader.setResource(new ClassPathResource("deltager.xlsx"));
	reader.setRowMapper(rowMapper());
	return reader;
    }

    @Bean
    public JobCompletionNotificationListener listener() {
	return new JobCompletionNotificationListener(webscorerWriter());
    }

    @Bean
    public WebscorerWriter webscorerWriter() {
	return new WebscorerWriter("webscorer.xlsx");
    }

    @Bean
    public RowMapper<Deltager> rowMapper() {
	return new DeltagerRowMapper();
    }

    @Bean
    public DeltagerProcessor deltagerProcessor() {
	return new DeltagerProcessor();
    }

    // tag::jobstep[]
    @Bean
    public Job importDeltagerJob() {
	return jobBuilderFactory.get("excelFileToExcelFileJob").incrementer(new RunIdIncrementer()).listener(listener()).flow(step1()).end().build();
    }

    @Bean
    public Step step1() {
	return stepBuilderFactory.get("excelFileToExcelFileStep").<Deltager, Webscorer> chunk(1000).reader(excelReader()).processor(deltagerProcessor()).writer(webscorerWriter()).build();
    }
    // end::jobstep[]
}
