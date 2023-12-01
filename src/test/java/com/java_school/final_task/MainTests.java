package com.java_school.final_task;

import com.java_school.final_task.domain.user.user_address.postal_code.city.country.impl.CountryRestControllerImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MainTests {
    @Autowired
    private CountryRestControllerImpl controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }
}
