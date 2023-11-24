package mothers.user.address.postal_code;

import com.java_school.final_task.domain.user.userAddress.postalCode.PostalCodeDTO;
import com.java_school.final_task.domain.user.userAddress.postalCode.PostalCodeEntity;
import mothers.user.address.postal_code.city.CityMother;

public class PostalCodeMother {
    public static PostalCodeEntity createPostalCode(){
        return PostalCodeEntity.builder()
                .isActive(true)
                .code("Code")
                .city(CityMother.createCity())
                .build();
    }

    public static PostalCodeDTO createPostalCodeDTO(){
        return PostalCodeDTO.builder()
                .isActive(true)
                .code("Code")
                .city(CityMother.createCityDTO())
                .build();
    }
}
