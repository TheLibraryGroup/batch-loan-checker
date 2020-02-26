package org.thibaut.thelibrary.loanchecker.batch.config;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thibaut.thelibrary.loanchecker.dto.LoanDTO;
import org.thibaut.thelibrary.loanchecker.batch.listener.JobCompletionListener;
import org.thibaut.thelibrary.loanchecker.batch.step.Processor;
import org.thibaut.thelibrary.loanchecker.batch.step.Reader;
import org.thibaut.thelibrary.loanchecker.batch.step.Writer;
import org.thibaut.thelibrary.loanchecker.dto.SimpleMailMessageDTO;

import java.util.List;

@Configuration
@AllArgsConstructor
public class BatchConfig {

	private JobBuilderFactory jobBuilderFactory;

	private StepBuilderFactory stepBuilderFactory;

	private final Reader reader;
	private final Processor processor;
	private final Writer writer;


	@Bean
	public Job processJob() {
		return jobBuilderFactory.get("processJob")
				.incrementer(new RunIdIncrementer())
		        .listener(listener())
				.flow(orderStep1())
		        .end()
			       .build();
	}

	@Bean
	public Step orderStep1() {
		return stepBuilderFactory.get("orderStep1").< List< LoanDTO >, List<SimpleMailMessageDTO> > chunk(1)
				.reader(reader)
		        .processor( processor )
				.writer(writer)
				       .build();
	}

	@Bean
	public JobExecutionListener listener() {
		return new JobCompletionListener();
	}

}
