package mothers.request;

import com.java_school.final_task.domain.order.OrderRequest;
import com.java_school.final_task.domain.user.UserRequest;
import com.java_school.final_task.security.JwtUtil;
import org.mockito.MockedStatic;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;

import static org.mockito.Mockito.mockStatic;

public class RequestMother {
    public static OrderRequest createOrderRequest() {
        OrderRequest request = new OrderRequest();
        request.setName("Name");
        request.setDate(LocalDate.now());
        request.setDeliveryMethod("");
        request.setOrderStatus("");
        request.setPaymentMethod("");
        request.setPaymentStatus("");
        request.setPage(0);
        request.setSize(10);
        request.setSortType("ASC");
        request.setSortProperty("name");

        return request;
    }

    public static UserRequest createUserRequest() {
        UserRequest request = new UserRequest();
        request.setName("Name");
        request.setPage(0);
        request.setPage(10);
        request.setSortType("ASC");
        request.setSortProperty("name");
        return request;
    }

    public static PageRequest createPageRequest(UserRequest request) {
        return PageRequest.of(
                request.getPage(),
                request.getSize(),
                Sort.Direction.valueOf(request.getSortType()),
                request.getSortProperty()
        );
    }

    public static void prepareRequestAttributes(int id) {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaWd1ZWxAZW1haWwuY29tIiwiaWQiOjQsInJvbGVzIjpbIkFETUlOIl0sImV4cCI6MTkxNjY3Mzk1NX0.wrX_u_broIqIiO-47Z5pZ4hI8zvA40Yj40nAdBCpFJM");

        ServletRequestAttributes requestAttributes = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(requestAttributes);

        try (MockedStatic<JwtUtil> mockedStatic = mockStatic(JwtUtil.class)) {
            mockedStatic.when(() -> JwtUtil.getIdFromToken(requestAttributes)).thenReturn(id);
        }
    }
}
