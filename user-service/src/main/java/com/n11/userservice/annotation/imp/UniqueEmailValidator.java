package com.n11.userservice.annotation.imp;

import com.n11.userservice.annotation.UniqueEmail;
import com.n11.userservice.exception.NotUniqueException;
import com.n11.userservice.model.User;
import com.n11.userservice.repository.UserRepository;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	private final UserRepository userRepository;

	public UniqueEmailValidator(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		User inDB = userRepository.findByEmail(value);
		return inDB == null;
	}

}
