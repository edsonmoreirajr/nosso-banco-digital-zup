package br.com.zup.nossobancodigitalzup.core.validation;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DataNascimentoValidator implements ConstraintValidator<DataNascimento, Date> {

	@Override
	public void initialize(DataNascimento constraintAnnotation) {
	}

	@Override
	public boolean isValid(Date dataNascimento, ConstraintValidatorContext constraintValidatorContext) {
		if (dataNascimento == null) {
			return false;
		} else {
			return validaDataNascimento(dataNascimento);
		}
	}

	private boolean validaDataNascimento(Date dataNascimento) {
		LocalDate localDataNascimento = dataNascimento.toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate();
		LocalDate currentDate = LocalDate.now();
		if (Period.between(localDataNascimento, currentDate).getYears() >= 18) {
			return true;
		} else {
			return false;
		}
	}

}
