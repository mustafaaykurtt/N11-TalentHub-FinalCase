package com.n11.userservice.model;

import com.n11.userservice.general.base.BaseEntity;
import com.n11.userservice.util.enums.Score;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_reviews")
public class UserReview extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserReview")
    @SequenceGenerator(name = "UserReview", sequenceName = "UserReview_ID_SEQ")
    @Id
    private Long id;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String comment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Score score;

    @Column(nullable = false)
    private Long restaurantId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

}
