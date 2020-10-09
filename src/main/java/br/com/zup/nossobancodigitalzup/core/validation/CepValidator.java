package br.com.zup.nossobancodigitalzup.core.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CepValidator implements ConstraintValidator<Cep, String>{

	private Pattern pattern = Pattern.compile("[0-9]{5}-[0-9]{3}");

	@Override
	public void initialize(Cep constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext arg1) {
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}
}
