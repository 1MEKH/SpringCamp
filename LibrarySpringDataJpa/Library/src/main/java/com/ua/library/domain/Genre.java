package com.ua.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "genres")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id",nullable = false)
    private long id;

    @Column(name = "type", nullable = false,unique = true)
    private String type;

    public Genre(String type) {
        this.type = type;
    }
}
