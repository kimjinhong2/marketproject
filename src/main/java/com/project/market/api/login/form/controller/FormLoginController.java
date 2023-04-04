package com.project.market.api.login.form.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.market.api.login.form.dto.FormLoginRequestDto;
import com.project.market.api.login.form.dto.FormRegisterDto;
import com.project.market.api.login.form.service.FormLoginService;
import com.project.market.api.login.form.validator.FormLoginValidator;
import com.project.market.api.login.form.validator.FormRegisterValidator;
import com.project.market.global.config.security.jwt.TokenDto;
import com.project.market.global.error.exception.InvalidParameterException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FormLoginController {

	private final FormLoginService formLoginService;
	private final FormRegisterValidator formRegisterValidator;
    private final FormLoginValidator formLoginValidator;
	
    @PostMapping(value = "/register")
	public ResponseEntity register(@Valid @RequestBody FormRegisterDto formRegisterDto, Errors errors) { // loginform에서 받아온 정보를 ResponseEntity로 감싸고 json으로 반환하기 위해 @requestbody 사용
		formRegisterValidator.validate(formRegisterDto, errors);
		
		if(errors.hasErrors()) {
			InvalidParameterException.throwErrorMessage(errors); //오류 있을시 에러내용 보내기
		}
		formLoginService.registerMember(formRegisterDto);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<TokenDto> login(@Valid @RequestBody FormLoginRequestDto formLoginDto, Errors errors) {
		formLoginValidator.validate(formLoginDto, errors);
		
		if (errors.hasErrors()) {
            InvalidParameterException.throwErrorMessage(errors);
        }

        return ResponseEntity.ok(formLoginService.formLogin(formLoginDto));
	}
}
