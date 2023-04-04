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

	@CreatedDate//등록 시간
	@Column(updatable = false, nullable = false)
	private LocalDateTime createTime;//등록한 시간
	
	@LastModifiedDate//수정 시간
	@Column(nullable = false)
	private LocalDateTime upDateTime;//업데이트한 시간
}
