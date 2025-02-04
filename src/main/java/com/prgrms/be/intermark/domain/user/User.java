package com.prgrms.be.intermark.domain.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import com.prgrms.be.intermark.domain.ticket.Ticket;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user",
        uniqueConstraints = {@UniqueConstraint(name = "social_uk", columnNames = {"social", "social_id"})})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "social", nullable = false)
    private Social social;

    @NotNull
    @Column(name = "social_id", nullable = false, length = 64)
    private String socialId;

    @NotNull
    @Column(name = "refresh_token", nullable = false, unique = true)
    private String refreshToken;

    @Length(min = 2, max = 20)
    @NotBlank
    @Column(name = "username", nullable = false, unique = true, length = 20)
    private String username;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role", nullable = false, length = 15)
    private UserRole role;

    @NotNull
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;

    @Nullable
    @Column(name = "birth")
    private LocalDate birth;

    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets = new ArrayList<>();

    @Builder
    public User(Social social, String socialId, String refreshToken, String username, UserRole role, boolean isDeleted, LocalDate birth, List<Ticket> tickets) {
        this.social = social;
        this.socialId = socialId;
        this.refreshToken = refreshToken;
        this.username = username;
        this.role = role;
        this.isDeleted = isDeleted;
        this.birth = birth;
        this.tickets = tickets;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }
}
