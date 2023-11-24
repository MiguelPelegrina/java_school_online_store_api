package mothers.book.parameter;

import com.java_school.final_task.domain.book.parameter.BookParameterDTO;
import com.java_school.final_task.domain.book.parameter.BookParameterEntity;
import com.java_school.final_task.domain.book.parameter.format.BookParametersFormatDTO;
import com.java_school.final_task.domain.book.parameter.format.BookParametersFormatEntity;

public class BookParameterMother {
    public static BookParameterEntity createBookParameter(){
        return BookParameterEntity.builder()
                .id(1)
                .isActive(true)
                .author("Author")
                .format(BookParametersFormatEntity.builder()
                        .name("Format")
                        .build())
                .build();
    }

    public static BookParameterDTO createBookParameterDTO(){
        return BookParameterDTO.builder()
                .author("Author")
                .format(new BookParametersFormatDTO("Hardcover"))
                .isActive(true)
                .build();
    }
}
