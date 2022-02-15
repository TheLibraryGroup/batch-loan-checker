package org.thibaut.thelibrary.loanchecker.batch.step;

import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import org.thibaut.thelibrary.loanchecker.dto.LoanDTO;
import org.thibaut.thelibrary.loanchecker.dto.SimpleMailMessageDTO;
import org.thibaut.thelibrary.loanchecker.service.AccountFeignClient;
import org.thibaut.thelibrary.loanchecker.service.BookFeignClient;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class Processor implements ItemProcessor< List< LoanDTO >, List< SimpleMailMessageDTO >> {

	private BookFeignClient bookFeignClient;
	private AccountFeignClient accountFeignClient;

	@Override
	public List< SimpleMailMessageDTO > process( List< LoanDTO > loanDTOList ) throws Exception {
		return loanDTOList.parallelStream()
						.map( loanDTO -> {
							final String bookTitle = bookFeignClient.findById( loanDTO.getBookId( ) ).getTitle( );
							return SimpleMailMessageDTO.builder()
									.from( "thelibrary.thib@gmail.com" )
									.to( accountFeignClient.findById( loanDTO.getUserId() ).getEmail() )
									.subject( "Please don't forget your loan" )
									.text( "Dear, you should have give the book '" + bookTitle + "' back. Thank you" )
										.build();
						})
						.collect( Collectors.toList());
	}
}
