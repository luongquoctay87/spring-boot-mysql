package com.sample.model;

import com.sample.util.Gender;
import com.sample.util.UserStatus;
import com.sample.util.UserType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_user")
public class UserEntity extends BaseEntity{

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", columnDefinition="ENUM('male', 'female', 'other')")
    private Gender gender;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition="ENUM('sysadmin', 'admin', 'manager', 'user')")
    private UserType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition="ENUM('active', 'inactive', 'none')")
    private UserStatus status;



    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private Set<AddressEntity> addressEntities = new HashSet<>();

    public void saveAddress(AddressEntity addressEntity) {

        if (addressEntity != null) {
            if (addressEntities == null) {
                addressEntities = new HashSet<>();
            }
            addressEntities.add(addressEntity);
            addressEntity.setUser(this); // save user_id
        }
    }
}
