package com.project.market.global.error.exception;

import lombok.Getter;
import java.util.Arrays;

@Getter
public enum ErrorCode {

    // ����
    ALREADY_REGISTERED_MEMBER(400, "�̹� ���Ե� ȸ�� �Դϴ�."),
    PASSWORD_NOT_SAME(400, "��й�ȣ�� �ٸ��ϴ�."),
    NO_MATCHING_MEMBER(400, "�ش� ȸ���� �������� �ʽ��ϴ�."),
    LOGIN_ERROR(400, "�α��� ���� �߻�."),
    NOT_VALID_MEMBER_TYPE(400, "��ȿ�� �α��� Ÿ���� �ƴմϴ�."),
    WRONG_PASSWORD(400, "��й�ȣ�� Ȯ�����ּ���."),
    NOT_VALID_TOKEN(403, "Token�� ��ȿ���� �ʽ��ϴ�."),
    TOKEN_EXPIRED(403, "Token�� ����Ǿ����ϴ�."),
    TOKNE_EMPTY(403, "Token�� �������� �ʽ��ϴ�."),
    
    NO_REP_IMAGE(400, "��ǥ �̹����� ������ּ���."),

    ADD_ITEM_ERROR(400, "��ǰ ����߿� ������ �߻��߽��ϴ�."),

    NO_MATCHING_ITEM(400, "�ش� ��ǰ�� �������� �ʽ��ϴ�."),
    
    UPDATE_ITEM_ERROR(400, "��ǰ �����߿� ������ �߻��߽��ϴ�."),
    
    NOT_ENOUGH_STOCK(409, "��ǰ�� ��� �����մϴ�."),
    
    NO_MATCHING_ORDER_ITEM(400, "�ֹ� ������ �������� �ʽ��ϴ�."),
    CART_ITEM_NOT_SELECTED(400, "�ֹ��� ��ǰ�� �������ּ���."),
    
    
    ;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private int status;
    private String message;
    
    public static ErrorCode getErrorCodeByErrorMessage(String message) {
        return Arrays.stream(ErrorCode.values()).filter(e -> e.message.equals(message)).findFirst().orElse(null);
    }

}