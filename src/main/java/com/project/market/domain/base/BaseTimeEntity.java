package com.project.market.domain.base;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@EntityListeners(value = {AuditingEntityListener.class})
@Getter
@MappedSuperclass
public abstract class BaseTimeEntity {

	@CreatedDate//��� �ð�
	@Column(updatable = false, nullable = false)
	private LocalDateTime createTime;//����� �ð�
	
	@LastModifiedDate//���� �ð�
	@Column(nullable = false)
	private LocalDateTime upDateTime;//������Ʈ�� �ð�
}
