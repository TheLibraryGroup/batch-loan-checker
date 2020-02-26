package org.thibaut.thelibrary.loanchecker.service;

import lombok.NonNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.thibaut.thelibrary.loanchecker.dto.LoanDTO;

import java.util.List;

@Service
@FeignClient(name = "THELIBRARY-MS-LOAN", fallback = LoanServiceFallback.class)
public interface LoanService {

	@GetMapping("/loans")
	public List< LoanDTO > findAll();

	@GetMapping("/loans/ongoing")
	public List< LoanDTO > findAllByReturnedIsFalse();

	@PostMapping("/loan")
	public LoanDTO save( @RequestBody @NonNull LoanDTO loanDTO);
}
