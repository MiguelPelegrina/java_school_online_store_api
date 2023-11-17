package com.javaSchool.FinalTask3;

import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.country.impl.CountryRestControllerImpl;
import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.country.impl.CountryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MainTests {
	@Autowired
	private CountryRestControllerImpl countryRestController;

	@Autowired
	private CountryServiceImpl countryService;

	@Test
	void contextLoads() {
		assertThat(countryRestController).isNotNull();
		assertThat(countryService).isNotNull();
	}

}
