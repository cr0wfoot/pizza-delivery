package com.pizza.delivery.domain.validation;

import static java.lang.annotation.ElementType.FIELD;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Annotation used for user's login validation
 * @see UserLoginValidator
 * @see CustomerDTO
 */
@Target({FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = { UserLoginValidator.class})
public @interface UniqueLogin {
    String message();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}