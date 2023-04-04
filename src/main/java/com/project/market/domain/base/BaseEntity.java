package com.project.market.domain.base;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@EntityListeners(value = {AuditingEntityListener.class})//entity�� ����,���� ����� �۾� �� �� ��,�Ŀ� �̺�Ʈ ó���� ���� ������̼�
@Getter
@MappedSuperclass
public abstract class BaseEntity extends BaseTimeEntity{

	@CreatedBy//�����
	@Column(updatable = false, nullable = false)
	private String createBy;//������ ����� ���
	
	@LastModifiedBy//������
	@Column(nullable = false)
	private String modifiedBy;//������ ������ ���
}
