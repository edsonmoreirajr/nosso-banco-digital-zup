package br.com.zup.nossobancodigitalzup.core.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.zup.nossobancodigitalzup.domain.service.EnvioEmailService;
import br.com.zup.nossobancodigitalzup.infrastructure.service.email.FakeEnvioEmailService;
import br.com.zup.nossobancodigitalzup.infrastructure.service.email.SmtpEnvioEmailService;

@Configuration
public class EmailConfig {

	@Autowired
	private EmailProperties emailProperties;

	@Bean
	public EnvioEmailService envioEmailService() {
		switch (emailProperties.getImpl()) {
			case FAKE:
				return new FakeEnvioEmailService();
			case SMTP:
				return new SmtpEnvioEmailService();
			default:
				return null;
		}
	}
}
