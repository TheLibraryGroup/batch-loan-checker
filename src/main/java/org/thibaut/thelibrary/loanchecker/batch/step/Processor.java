package org.thibaut.thelibrary.loanchecker.batch.step;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import org.thibaut.thelibrary.loanchecker.dto.LoanDTO;
import org.thibaut.thelibrary.loanchecker.dto.SimpleMailMessageDTO;
import org.thibaut.thelibrary.loanchecker.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class Processor implements ItemProcessor< List< LoanDTO >, List< SimpleMailMessageDTO >> {

	private BookService bookService;

	@Override
	public List< SimpleMailMessageDTO > process( List< LoanDTO > loanDTOList ) throws Exception {
		return loanDTOList.parallelStream()
						.map( loanDTO -> {
							final String bookTitle = bookService.findById( loanDTO.getBookId( ) ).getTitle( );
							return SimpleMailMessageDTO.builder()
									.from( "thelibrary.thib@gmail.com" )
									.to( loanDTO.getEmail() )
									.subject( "Please don't forget your loan" )
									.text( "Dear, you should have give the book '" + bookTitle + "' back. Thank you" )
										.build();
						})
						.collect( Collectors.toList());
	}
}
