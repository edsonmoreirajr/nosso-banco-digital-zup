package br.com.zup.nossobancodigitalzup.core.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ApiSecurity {

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
}
