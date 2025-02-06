package com.example.board.dto.member;

import lombok.Getter;

@Getter
public class MemberResponseDto {

    private final String userName;

    private final Integer age;

    public MemberResponseDto(String userName, Integer age) {
        this.userName = userName;
        this.age = age;
    }
}
