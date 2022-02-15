package org.thibaut.thelibrary.loanchecker.service;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.thibaut.thelibrary.loanchecker.dto.BookDTO;

@Service
public class BookFeignClientFallback implements BookFeignClient {

	@Override
	public BookDTO findById( @NonNull Long id ) {
		return null;
	}
}
