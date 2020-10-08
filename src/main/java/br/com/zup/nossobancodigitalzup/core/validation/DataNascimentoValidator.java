package br.com.zup.nossobancodigitalzup.core.validation;

import java.time.LocalDate;
import java.time.Period;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DataNascimentoValidator implements ConstraintValidator<DataNascimento, LocalDate> {

	@Override
	public void initialize(DataNascimento constraintAnnotation) {
	}

	@Override
	public boolean isValid(LocalDate dataNascimento, ConstraintValidatorContext constraintValidatorContext) {
		if (dataNascimento == null) {
			return false;
		} else {
			return validaDataNascimento(dataNascimento);
		}
	}

	private boolean validaDataNascimento(LocalDate dataNascimento) {
		LocalDate currentDate = LocalDate.now();
		if (Period.between(dataNascimento, currentDate).getYears() >= 18) {
			return true;
		} else {
			return false;
		}
	}

}
