package com.project.market.global.config.jpa;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareImpl implements AuditorAware<String>{

	@Override
	public Optional<String> getCurrentAuditor() {
		// TODO Auto-generated method stub
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();//�α��� ���� Ȯ��
		
		if(null==authentication || !authentication.isAuthenticated()) {//���Ȱ˻簡 �ȵǰų� ���� ������ ������ ��α������� ����
			return Optional.of("Unknown");
		}
		
		//ȸ�� ���� ������ ���� ��� email ������ ����
        return Optional.of(authentication.getName());
	}

	
}
