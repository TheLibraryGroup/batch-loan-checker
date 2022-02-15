package org.thibaut.thelibrary.loanchecker.service;

import lombok.NonNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.thibaut.thelibrary.loanchecker.dto.LoanDTO;

import java.util.List;

@FeignClient(name = "THELIBRARY-MS-LOAN", fallback = LoanFeignClientFallback.class)
public interface LoanFeignClient {

	@GetMapping("/api/loans")
	public List< LoanDTO > findAll();

	@GetMapping("/api/loans/ongoing")
	public List< LoanDTO > findAllByReturnedIsFalse();

	@PostMapping("/api/loan")
	public LoanDTO save( @RequestBody @NonNull LoanDTO loanDTO);
}
