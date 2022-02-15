package org.thibaut.thelibrary.loanchecker.broker;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

//@EnableAutoConfiguration(exclude={ DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public final class BrokerConfig {

	public static final String TOPIC_EXCHANGE_NAME = "thelibrary-exchange";

	public static final String QUEUE_LOAN_OVERDUE = "mail-loan-return-overdue";

	public static final String ROUTING_KEY = "thelibrary";
}
