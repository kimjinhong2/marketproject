package com.project.market.global.config.jpa;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.persistence.*;


@Configuration
public class QueryDslConfig {//������Ʈ ��� �������� JPAQueryFactory�� ���Թ޾� QueryDSL�� ���

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager); 
    }
}