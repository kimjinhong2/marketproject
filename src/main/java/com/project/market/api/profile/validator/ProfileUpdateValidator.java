package com.project.market.api.profile.validator;

import com.project.market.api.profile.dto.ProfileUpdateDto;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProfileUpdateValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ProfileUpdateDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProfileUpdateDto profileUpdateDto = (ProfileUpdateDto) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "memberName", "required", "�̸��� �ʼ� �Է� �����Դϴ�.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "required", "�ּҴ� �ʼ� �Է� �����Դϴ�.");
    }
}