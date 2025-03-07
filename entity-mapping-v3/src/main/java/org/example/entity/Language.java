package org.example.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "language")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @ManyToMany(mappedBy = "languages")
//    private List<Tutor> tutors = new ArrayList<>();

    // 읽기 전용
    @OneToMany(mappedBy = "language")
    private List<TutorLanguage> tutorLanguages = new ArrayList<>();

}
