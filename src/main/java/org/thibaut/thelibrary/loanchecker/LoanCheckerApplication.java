package org.thibaut.thelibrary.loanchecker;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableBatchProcessing
@SpringBootApplication
public class LoanCheckerApplication {

	public static void main( String[] args ) {
		SpringApplication.run( LoanCheckerApplication.class, args );
	}

}
