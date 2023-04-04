package com.project.market.api.login.form.validator;

import com.project.market.api.login.form.dto.FormRegisterDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class FormRegisterValidator implements Validator {

    private static final String emailRegExp =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private final Pattern pattern;
    private final int MIN_PASSWORD_LENGTH = 7;
    private final int MAX_PASSWORD_LENGTH = 16;
    
    public FormRegisterValidator() {
        pattern = Pattern.compile(emailRegExp);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FormRegisterDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FormRegisterDto formRequestDto = (FormRegisterDto) target;

        validateEmail(errors, formRequestDto);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "memberName", "required", "�̸��� �ʼ� �Է� �����Դϴ�.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "required", "�ּҴ� �ʼ� �Է� �����Դϴ�.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required", "��й�ȣ�� �ʼ� �Է� �����Դϴ�.");
        validatePassword(errors, formRequestDto);
    }
    
    private void validatePassword(Errors errors, FormRegisterDto formRequestDto) {
    	if(validatePasswordLength(formRequestDto)) {
    		errors.rejectValue("password", "bad", "��й�ȣ�� 8�� �̻�, 16�� ���Ϸ� �Է����ּ���.");
    	}
    	if(validatePasswordConfirm(formRequestDto)) {
    		errors.rejectValue("passwordConfirm", "noMatch","��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
    	}
    }
    
    private void validateEmail(Errors errors,FormRegisterDto formRequestDto) {
    	if(isEmailNull(formRequestDto)) {
    		errors.rejectValue("email", "required", "�̸����� �ʼ� �Է� �����Դϴ�.");
    		return;
    	}
    	
    	Matcher matcher = pattern.matcher(formRequestDto.getEmail());
    	if(!matcher.matches()) {
    		errors.rejectValue("email", "bad","�ùٸ��� ���� �̸��� �����Դϴ�.");
    	}
    }
    
    private boolean isEmailNull(FormRegisterDto formRequestDto) {
    	return formRequestDto.getEmail() == null || formRequestDto.getEmail().trim().isEmpty();
    }
    
    private boolean validatePasswordLength(FormRegisterDto formRequestDto) {
    	return formRequestDto.getPassword().length() <= MIN_PASSWORD_LENGTH
    			|| formRequestDto.getPassword().length() > MAX_PASSWORD_LENGTH
    			&& !StringUtils.isEmpty(formRequestDto.getPassword());
    }
    
    private boolean validatePasswordConfirm(FormRegisterDto formRequestDto) {
        return !formRequestDto.getPassword().equals(formRequestDto.getPasswordConfirm())
                && !StringUtils.isEmpty(formRequestDto.getPassword());
    }
}