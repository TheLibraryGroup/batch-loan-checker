package org.thibaut.thelibrary.loanchecker.batch.step;

import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;
import org.thibaut.thelibrary.loanchecker.batch.controller.JobInvokerController;
import org.thibaut.thelibrary.loanchecker.dto.LoanDTO;
import org.thibaut.thelibrary.loanchecker.service.LoanFeignClient;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class Reader implements ItemReader< List< LoanDTO > > {

	private final LoanFeignClient loanFeignClient;

	@Override
	public List< LoanDTO > read() throws Exception, UnexpectedInputException,
			                            ParseException, NonTransientResourceException {

		if( JobInvokerController.jobFinished ){
			return null;
		}

		final List< LoanDTO > loanDTOList = loanFeignClient.findAllByReturnedIsFalse( );
		return loanDTOList
				       .parallelStream().filter(
						//if StartDate + loanDuration is before now, then the loan should has been returned
						loanDTO1 -> loanDTO1.getStartDate().plusDays( loanDTO1.getDurationInDay() ).isBeforeNow() )
				       .collect( Collectors.toList() );
	}

}
