package org.thibaut.thelibrary.loanchecker.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.thibaut.thelibrary.loanchecker.dto.AccountDTO;

@FeignClient(name = "THELIBRARY-MS-ACCOUNT", fallback = LoanFeignClientFallback.class)
public interface AccountFeignClient {

	@GetMapping("/api/account/{id}")
	public AccountDTO findById( @PathVariable("id") Long id );

}
