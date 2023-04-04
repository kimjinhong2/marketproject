package com.project.market.web.register.dto;

import com.project.market.domain.member.constant.MemberRole;
import com.project.market.domain.member.constant.MemberType;
import com.project.market.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.validation.constraints.NotBlank;


@Getter
@Setter
@NoArgsConstructor
public class MemberRegisterDto {

    @NotBlank(message = "�̸��� �ʼ� �Է� ���Դϴ�.")
    private String name;

    @NotBlank(message = "�ּҴ� �ʼ� �Է� ���Դϴ�.")
    private String address;

    @NotBlank(message = "�̸����� �ʼ� �Է� ���Դϴ�.")
    private String email;

    @NotBlank(message = "��й�ȣ�� �ʼ� �Է� ���Դϴ�.")
    @Length(min=8, max=16, message = "��й�ȣ�� 8�� �̻�, 16�� ���Ϸ� �Է����ּ���")
    private String password;

    private String password2;


    public Member toEntity(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .memberName(name)
                .memberType(MemberType.EMAIL)
                .address(address)
                .email(email)
                .password(passwordEncoder.encode(password))
                .role(MemberRole.ADMIN)
                .build();
    }

    @Builder
    public MemberRegisterDto(String name, String address, String email, String password, String password2) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        this.password2 = password2;
    }
}