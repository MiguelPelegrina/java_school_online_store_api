package mothers.user_role;

import com.java_school.final_task.domain.role.RoleDTO;
import com.java_school.final_task.domain.role.RoleEntity;
import com.java_school.final_task.domain.userRole.UserRoleEntity;
import com.java_school.final_task.domain.userRole.dto.UserRoleJsonDTO;

import java.time.LocalDate;

public class UserRoleMother {
    public static UserRoleEntity createUserRoleAdmin(){
        return UserRoleEntity.builder()
                .assignedDate(LocalDate.now())
                .id(1)
                .role(RoleEntity.builder()
                        .name("ADMIN")
                        .build())
                .build();
    }

    public static UserRoleEntity createUserRoleClient(){
        return UserRoleEntity.builder()
                .assignedDate(LocalDate.now())
                .id(1)
                .role(RoleEntity.builder()
                        .name("CLIENT")
                        .build())
                .build();
    }

    public static UserRoleJsonDTO createUserRoleDTOAdmin(){
        return UserRoleJsonDTO.builder()
                .role(RoleDTO.builder()
                        .name("ADMIN")
                        .build())
                .assignedDate(LocalDate.now())
                .build();
    }
}
