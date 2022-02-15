package org.thibaut.thelibrary.loanchecker.batch.step;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.thibaut.thelibrary.loanchecker.batch.controller.JobInvokerController;
import org.thibaut.thelibrary.loanchecker.dto.SimpleMailMessageDTO;

import java.util.List;

import static org.thibaut.thelibrary.loanchecker.broker.BrokerConfig.*;

@Component
@AllArgsConstructor
public class Writer implements ItemWriter<List<SimpleMailMessageDTO> > {

	private AmqpTemplate amqpTemplate;


	@Override
	public void write( List< ? extends List< SimpleMailMessageDTO > > list ) throws Exception {
		for ( SimpleMailMessageDTO messageDTO: list.get( 0 ) ) {
			amqpTemplate.convertAndSend( TOPIC_EXCHANGE_NAME,ROUTING_KEY, messageDTO );
		}
		JobInvokerController.jobFinished = true;
	}
}
