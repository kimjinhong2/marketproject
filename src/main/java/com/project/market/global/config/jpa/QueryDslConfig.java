package com.project.market.global.config.jpa;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.persistence.*;


@Configuration
public class QueryDslConfig {//프로젝트 어느 곳에서나 JPAQueryFactory를 주입받아 QueryDSL을 사용

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager); 
    }
}