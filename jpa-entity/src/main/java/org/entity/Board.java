package org.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "board")
public class Board {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column을 사용하지 않아도 자동으로 매핑된다.
    private Integer view;

    // 객체 필드 이름과 DB 이름을 다르게 설정할 수 있다.
    @Column(name = "title")
    private String bigTitle;

    // DB에는 기본적으로 enum이 없다.
    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    // VARCHAR()를 넘어서는 큰 용량의 문자열을 저장할 수 있다.
    @Column(columnDefinition = "longtext")
    private String contents;

    // 날짜 타입 DATE, TIME, TIMESTAMP를 사용할 수 있다.
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Transient
    private int count;

    public Board() {
    }

}
