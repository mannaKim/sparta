package com.example.board.dto;

import lombok.Getter;

@Getter
public class SignUpResponseDto {

    private final Long id;

    private final String userName;

    private final Integer age;

    public SignUpResponseDto(Long id, String userName, Integer age) {
        this.id = id;
        this.userName = userName;
        this.age = age;
    }
}
