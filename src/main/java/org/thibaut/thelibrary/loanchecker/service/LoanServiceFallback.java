package org.thibaut.thelibrary.loanchecker.service;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.thibaut.thelibrary.loanchecker.dto.LoanDTO;

import java.util.List;

@Service
public class LoanServiceFallback implements LoanService{

	@Override
	public List< LoanDTO > findAll( ) {
		return null;
	}

	@Override
	public List< LoanDTO > findAllByReturnedIsFalse( ) {
		return null;
	}

	@Override
	public LoanDTO save( @NonNull LoanDTO loanDTO ) {
		return null;
	}
}
