package com.java_school.final_task.configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up JPAQueryFactory bean.
 * Provides bean definitions for creating and configuring JPAQueryFactory instances.
 */
@Configuration
public class JPAQueryFactoryConfig {
    /**
     * Configures and provides a JPAQueryFactory bean.
     *
     * @param entityManager The EntityManager instance to be injected into the JPAQueryFactory.
     * @return JPAQueryFactory A configured bean of JPAQueryFactory that integrates with JPA.
     */
    @Bean
    public JPAQueryFactory jpaQueryFactory(@Autowired EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }
}
