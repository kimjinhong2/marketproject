package com.project.market.domain.base;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@EntityListeners(value = {AuditingEntityListener.class})//entity가 삽입,삭제 등등의 작업 할 때 전,후에 이벤트 처리를 위한 어노테이션
@Getter
@MappedSuperclass
public abstract class BaseEntity extends BaseTimeEntity{

	@CreatedBy//등록자
	@Column(updatable = false, nullable = false)
	private String createBy;//아이템 등록한 사람
	
	@LastModifiedBy//수정자
	@Column(nullable = false)
	private String modifiedBy;//아이템 수정한 사람
}
