package com.project.market.global.config.jpa;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareImpl implements AuditorAware<String>{

	@Override
	public Optional<String> getCurrentAuditor() {
		// TODO Auto-generated method stub
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();//로그인 인증 확인
		
		if(null==authentication || !authentication.isAuthenticated()) {//보안검사가 안되거나 인증 정보가 없으면 비로그인으로 간주
			return Optional.of("Unknown");
		}
		
		//회원 인증 정보가 있을 경우 email 값으로 설정
        return Optional.of(authentication.getName());
	}

	
}
