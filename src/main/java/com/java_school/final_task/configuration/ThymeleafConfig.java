package com.java_school.final_task.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 * Configuration class for Thymeleaf template engine setup.
 * Provides bean definitions for configuring Thymeleaf template resolution and processing.
 */
@Configuration
public class ThymeleafConfig {
    /**
     * Configures and provides a ClassLoaderTemplateResolver bean for Thymeleaf.
     *
     * @return ClassLoaderTemplateResolver A configured template resolver for resolving templates from the classpath.
     */
    @Bean
    public ClassLoaderTemplateResolver templateResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setOrder(1);
        return templateResolver;
    }

    /**
     * Configures and provides a SpringTemplateEngine bean for Thymeleaf.
     *
     * @return SpringTemplateEngine A configured template engine that integrates with Spring and Thymeleaf.
     */
    @Bean
    public SpringTemplateEngine templateEngine() {
        var templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());

        return templateEngine;
    }
}
