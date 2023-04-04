package com.project.market.domain.member.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {
    ADMIN("ROLE_ADMIN", "�Ǹ���"),
    USER("ROLE_USER", "�����");

    private final String key;
    private final String title;

}