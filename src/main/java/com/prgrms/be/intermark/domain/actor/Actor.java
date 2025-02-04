package com.prgrms.be.intermark.domain.actor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.prgrms.be.intermark.domain.casting.Casting;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "actor")
@NoArgsConstructor
@Getter
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @NotNull
    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @NotNull
    @Column(name = "gender", nullable = false, length = 10)
    private Gender gender;

    @NotNull
    @Column(name = "image_url", nullable = false, length = 2000)
    private String imageUrl;

    @OneToMany(mappedBy = "actor")
    private List<Casting> castings = new ArrayList<>();
    @Builder
    public Actor(String name, LocalDate birth, Gender gender, String imageUrl) {
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.imageUrl = imageUrl;
    }

}
