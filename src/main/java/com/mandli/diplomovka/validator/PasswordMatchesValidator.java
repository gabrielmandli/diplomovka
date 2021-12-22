package com.mandli.diplomovka.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Service;

import com.mandli.diplomovka.dto.UserDto;

@Service
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {

        UserDto userDto = (UserDto) object;
        return userDto.getPassword().equals(userDto.getConfirm());
    }
}
