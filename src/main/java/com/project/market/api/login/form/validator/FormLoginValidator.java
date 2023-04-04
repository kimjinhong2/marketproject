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
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";//�̸��Ͽ� ���Ǵ� ���� ǥ����
    private final Pattern pattern;

    public FormLoginValidator() {
        pattern = Pattern.compile(emailRegExp);//������ ����ǥ������ ����Ͽ� ������ ���Ѵ�.
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FormLoginRequestDto.class.isAssignableFrom(clazz);//clazzŬ������ FormLoginRequestDto.class���� ������ Ŭ�������� �˾ƺ��� �޼ҵ�
    }

    @Override
    public void validate(Object target, Errors errors) {
        FormLoginRequestDto requestDto = (FormLoginRequestDto) target;

        validateEmail(errors, requestDto);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required", "��й�ȣ�� �ʼ� �Է� �����Դϴ�.");
    }

    private void validateEmail(Errors errors, FormLoginRequestDto formRequestDto) { //�̸��� ����
        if (isEmailNull(formRequestDto)) {
            errors.rejectValue("email", "required", "�̸����� �ʼ� �Է� �����Դϴ�.");
            return;
        }

        Matcher matcher = pattern.matcher(formRequestDto.getEmail());//���ԽĿ� �����ϴ��� ���� Ȯ��
        if (!matcher.matches()) {
            errors.rejectValue("email", "bad", "�ùٸ��� ���� �̸��� �����Դϴ�.");
        }
    }

    private boolean isEmailNull(FormLoginRequestDto formRequestDto) { //�̸����� �Է� �Ǿ����� Ȯ��
        return formRequestDto.getEmail() == null || formRequestDto.getEmail().trim().isEmpty();
    }
}