package com.n11.userservice.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.n11.userservice.annotation.imp.UniqueEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = {UniqueEmailValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface UniqueEmail {

	String message() default "{n11.constraint.email.notUnique}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
