package com.project.market.global.config.listener;

import com.project.market.global.event.OrderCancelEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCancelListener implements ApplicationListener<OrderCancelEvent> { //���� ���� �������� ���̱� ���� �̺�Ʈ ���

    @Override
    public void onApplicationEvent(OrderCancelEvent event) {
        System.out.println(("Order ��� �̺�Ʈ �߻�, OrderId = " + event.getOrder().getId()));
    }
}