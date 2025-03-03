package org.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// JAP를 사용하여 객체를 테이블과 매핑할 때 @Entity 어노테이션 필수
@Entity
@Table(name = "tutor")
public class Tutor {

    // PK (필수)
    @Id
    private Long id;

    // 필드
    // 필드에 final 키워드 사용 불가
    @Column(unique = true, length = 20, nullable = false)
    private String name;
//    private Integer age;

    // 기본 생성자 (필수)
    public Tutor() {
    }

    // 쉽게 사용하기 위해 생성자 추가
    public Tutor(Long id, String name) {
        this.id = id;
        this.name = name;
    }

//    public Tutor(Long id, String name, Integer age) {
//        this.id = id;
//        this.name = name;
//        this.age = age;
//    }
}
