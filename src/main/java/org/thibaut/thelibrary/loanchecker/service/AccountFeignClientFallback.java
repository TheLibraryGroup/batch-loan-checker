package org.thibaut.thelibrary.loanchecker.service;

import org.springframework.stereotype.Service;
import org.thibaut.thelibrary.loanchecker.dto.AccountDTO;

import java.util.List;

@Service
public class AccountFeignClientFallback implements AccountFeignClient {

	@Override
	public AccountDTO findById( Long id ) {
		return null;
	}
}
