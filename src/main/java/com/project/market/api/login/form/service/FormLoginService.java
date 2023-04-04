package com.project.market.api.login.form.service;

import com.project.market.api.login.form.dto.FormLoginRequestDto;
import com.project.market.api.login.form.dto.FormRegisterDto;
import com.project.market.domain.member.entity.Member;
import com.project.market.domain.member.service.MemberService;
import com.project.market.global.config.security.jwt.JwtTokenProvider;
import com.project.market.global.config.security.jwt.TokenDto;
import com.project.market.global.error.exception.ErrorCode;
import com.project.market.global.error.exception.InvalidParameterException;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FormLoginService {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    @Transactional
    public Member registerMember(FormRegisterDto formRegisterDto) { //회원 등록
        Member newMember = formRegisterDto.toEntity();
        memberService.register(newMember);
        return newMember;
    }

    @Transactional
    public TokenDto formLogin(FormLoginRequestDto formLoginDto) {
    	Member member = memberService.findMember(formLoginDto.getEmail());
    	verifyPassword(member,formLoginDto);
    	TokenDto tokenDto = tokenProvider.createTokenDto(member);//토큰 발급
    	String refreshToken = tokenDto.getRefreshToken();//refresh토큰을 저장한다.
    	
    	memberService.saveRefreshToken(member, refreshToken);//사용자에게 refresh토큰을 보내준다
    	
    	return tokenDto;
    }
    
    private void verifyPassword(Member member,FormLoginRequestDto formLoginDto) { //폼에서 넘어온 비밀번호와 회원의 비밀번호가 일치한지 확인
    	if(!passwordEncoder.matches(formLoginDto.getPassword(),member.getPassword())) {
    		throw new InvalidParameterException(ErrorCode.WRONG_PASSWORD.getMessage());
    	}
    }
}