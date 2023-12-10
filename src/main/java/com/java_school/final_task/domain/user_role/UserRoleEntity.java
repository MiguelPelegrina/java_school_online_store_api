package com.java_school.final_task.domain.user_role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.java_school.final_task.domain.role.RoleEntity;
import com.java_school.final_task.domain.user.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@Builder
//@Data <-- Had to remove data, as it produced a Stack Overflow when calling the hashCode method during the saving of a
// new user
@Getter
@Setter
@ToString
@Entity
@RequiredArgsConstructor
@Schema(description = "Role that is assigned to a user")
@Table(name = "user_roles", schema = "public", catalog = "online_store")
public class UserRoleEntity implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    @ToString.Exclude
    private RoleEntity role;

    @Column(name = "assigned_date", nullable = false)
    private LocalDate assignedDate;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleEntity other = (UserRoleEntity) o;
        return getId() != 0 && getId() == other.getId();
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}
