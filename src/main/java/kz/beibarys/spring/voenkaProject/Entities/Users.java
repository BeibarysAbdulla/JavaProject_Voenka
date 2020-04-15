package kz.beibarys.spring.voenkaProject.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class Users extends BaseEntity {
    @Email
    @NotNull
    @Column(name="email")
    private String email;

    @NotNull
    @Column(name = "password")
    private String password;

    @Column(name="passwordConfirm")
    private String passwordConfirm;

    @NotNull
    @Column(name="name")
    private String name;

    @NotNull
    @Column(name="surname")
    private String surname;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Roles> roles;


}
