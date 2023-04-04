package com.project.market.global.config.jpa;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BooleanToYNConverter implements AttributeConverter<Boolean, String>{

	//Boolean ���� Y �Ǵ� N���� ��ȯ���ִ� �޼���
    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return (attribute != null && attribute) ? "Y" : "N";
    }

    //Y �Ǵ� N ���� Boolean ������ ��ȭ���ִ� �޼���
    @Override
    public Boolean convertToEntityAttribute(String yn) {
        return "Y".equalsIgnoreCase(yn);
    }
}
