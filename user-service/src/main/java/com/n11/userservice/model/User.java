package com.n11.userservice.model;

import com.n11.userservice.general.base.BaseEntity;
import com.n11.userservice.util.enums.Gender;
import com.n11.userservice.util.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * Created By Mustafa Aykurt
 * Date:9.03.2024
 * Time:20:14
 */

@Getter
@Setter
@Entity
@Table(name = "USERS", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class User extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "User")
    @SequenceGenerator(name = "User", sequenceName = "USER_ID_SEQ")
    @Id
    private Long id;

    @Column(name = "NAME", length = 60, nullable = false)
    private String name;

    @Column(name = "SURNAME", length = 60, nullable = false)
    private String surname;

    @Column(name = "BIRTH_DATE")
    private LocalDate birthDate;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = "DECIMAL(9,6) CHECK (latitude BETWEEN -90 AND 90)", nullable = false)
    private Double latitude;

    @Column(columnDefinition = "DECIMAL(9,6) CHECK (longitude BETWEEN -180 AND 180)", nullable = false)
    private Double longitude;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @OneToMany(mappedBy = "user", targetEntity = UserReview.class, fetch = FetchType.LAZY)
    private List<UserReview> userReviews;

    public User() {
        status = UserStatus.INACTIVE;
    }
}
