package com.project.market.api.login.form.validator;

import com.project.market.api.login.form.dto.FormLoginRequestDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class FormLoginValidator implements Validator {

    private static final String emailRegExp =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";//이메일에 사용되는 정규 표현식
    private final Pattern pattern;

    public FormLoginValidator() {
        pattern = Pattern.compile(emailRegExp);//정의한 정규표현식을 사용하여 패턴을 정한다.
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FormLoginRequestDto.class.isAssignableFrom(clazz);//clazz클래스는 FormLoginRequestDto.class에서 구현한 클래스인지 알아보는 메소드
    }

    @Override
    public void validate(Object target, Errors errors) {
        FormLoginRequestDto requestDto = (FormLoginRequestDto) target;

        validateEmail(errors, requestDto);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required", "비밀번호는 필수 입력 정보입니다.");
    }

    private void validateEmail(Errors errors, FormLoginRequestDto formRequestDto) { //이메일 검증
        if (isEmailNull(formRequestDto)) {
            errors.rejectValue("email", "required", "이메일은 필수 입력 정보입니다.");
            return;
        }

        Matcher matcher = pattern.matcher(formRequestDto.getEmail());//정규식에 부합하는지 여부 확인
        if (!matcher.matches()) {
            errors.rejectValue("email", "bad", "올바르지 않은 이메일 형식입니다.");
        }
    }

    private boolean isEmailNull(FormLoginRequestDto formRequestDto) { //이메일이 입력 되었는지 확인
        return formRequestDto.getEmail() == null || formRequestDto.getEmail().trim().isEmpty();
    }
}