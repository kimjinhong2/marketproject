package com.project.market.web.profile.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class ProfileUpdateDto {

    @NotBlank(message = "�̸��� �ʼ� �Է� ���Դϴ�.")
    private String name;

    @NotBlank(message = "�ּҴ� �ʼ� �Է� ���Դϴ�.")
    private String address;

    @Builder
    public ProfileUpdateDto(String name, String address) {
        this.name = name;
        this.address = address;
    }
}