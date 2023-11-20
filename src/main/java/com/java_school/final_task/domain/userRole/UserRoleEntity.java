package com.java_school.final_task.domain.userRole;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.java_school.final_task.domain.role.RoleEntity;
import com.java_school.final_task.domain.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@Builder
//@Data <-- Had to remove data, as it produced a Stack Overflow when calling the hashCode method during the saving of a
// new user
@Getter
@Setter
@ToString
@Entity
@RequiredArgsConstructor
@Table(name = "user_roles", schema = "public", catalog = "online_store")
public class UserRoleEntity {
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
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        UserRoleEntity userRole = (UserRoleEntity) o;
        return Objects.equals(getId(), userRole.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
