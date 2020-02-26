package org.thibaut.thelibrary.loanchecker.service;

import lombok.NonNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.thibaut.thelibrary.loanchecker.dto.BookDTO;

@FeignClient(name = "THELIBRARY-MS-BOOK", fallback = BookServiceFallback.class)
public interface BookService {

	@GetMapping("/book/{id}")
	public BookDTO findById( @PathVariable("id") @NonNull Long id);

}


