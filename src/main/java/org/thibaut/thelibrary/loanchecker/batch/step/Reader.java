package org.thibaut.thelibrary.loanchecker.batch.step;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;
import org.thibaut.thelibrary.loanchecker.dto.LoanDTO;
import org.thibaut.thelibrary.loanchecker.service.LoanService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class Reader implements ItemReader< List< LoanDTO > > {

	private LoanService loanService;

	@Override
	public List< LoanDTO > read() throws Exception, UnexpectedInputException,
			                            ParseException, NonTransientResourceException {

		return loanService.findAllByReturnedIsFalse( )
				       .parallelStream().filter(
						//if StartDate + loanDuration is before now, then the loan should has been returned
						loanDTO1 -> loanDTO1.getStartDate().plusDays( loanDTO1.getDurationInDay() ).isBeforeNow() )
				       .collect( Collectors.toList() );
	}

}
