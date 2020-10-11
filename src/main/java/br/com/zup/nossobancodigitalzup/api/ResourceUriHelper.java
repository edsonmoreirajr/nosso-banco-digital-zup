package br.com.zup.nossobancodigitalzup.api;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ResourceUriHelper {

	public static void addUriInResponseHeader(String proximaEtapda) {
		HttpServletResponse response = ((ServletRequestAttributes) 
				RequestContextHolder.getRequestAttributes()).getResponse();
		
		URI uri = ServletUriComponentsBuilder.fromCurrentServletMapping()
		.path("/" + proximaEtapda).build().toUri();
		
		response.setHeader(HttpHeaders.LOCATION, uri.toString());
	}
	
}
