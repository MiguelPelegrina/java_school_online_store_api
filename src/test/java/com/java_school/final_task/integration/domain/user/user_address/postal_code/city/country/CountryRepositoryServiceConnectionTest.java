package com.java_school.final_task.integration.domain.user.user_address.postal_code.city.country;

import com.java_school.final_task.TestContainersConfiguration;
import com.java_school.final_task.domain.user.user_address.postal_code.city.country.CountryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Testcontainers
@Import(TestContainersConfiguration.class)
public class CountryRepositoryServiceConnectionTest {
    @Autowired
    private CountryService service;

    @Test
    void TestCountryQuantity() {
        assertThat(service.getAllInstances(Optional.of(true), "").size()).isEqualTo(2);
    }
}
