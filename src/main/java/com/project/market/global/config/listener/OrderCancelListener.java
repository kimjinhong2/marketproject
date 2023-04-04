package com.project.market.global.config.listener;

import com.project.market.global.event.OrderCancelEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCancelListener implements ApplicationListener<OrderCancelEvent> { //서비스 간의 의존성을 줄이기 위한 이벤트 사용

    @Override
    public void onApplicationEvent(OrderCancelEvent event) {
        System.out.println(("Order 취소 이벤트 발생, OrderId = " + event.getOrder().getId()));
    }
}