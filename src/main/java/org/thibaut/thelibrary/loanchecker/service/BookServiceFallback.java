package org.thibaut.thelibrary.loanchecker.service;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.thibaut.thelibrary.loanchecker.dto.BookDTO;

@Service
//@Qualifier(value = "BookServiceFallback")
public class BookServiceFallback implements BookService {

	@Override
	public BookDTO findById( @NonNull Long id ) {
		return null;
	}
}
