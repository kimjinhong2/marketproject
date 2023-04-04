package com.project.market.web.register.service;

import com.project.market.domain.member.entity.Member;
import com.project.market.domain.member.service.MemberService;
import com.project.market.global.error.exception.BusinessException;
import com.project.market.global.error.exception.ErrorCode;
import com.project.market.web.register.dto.MemberRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberRegisterService {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public void register(MemberRegisterDto registerDto) {//ȸ������
        validatePasswordCheck(registerDto);
        Member member = registerDto.toEntity(passwordEncoder);//��й�ȣ ���ڵ�
        memberService.register(member);
    }

    private void validatePasswordCheck(MemberRegisterDto registerDto) {//��й�ȣ Ȯ��
        String password1 = registerDto.getPassword();
        String password2 = registerDto.getPassword2();
        if (!password1.equals(password2)) {
            throw new BusinessException(ErrorCode.PASSWORD_NOT_SAME);
        }
    }
}