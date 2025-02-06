package com.example.board.dto;

import lombok.Getter;

@Getter
public class SignUpRequestDto {

    private final String userName;

    private final String password;

    private final Integer age;

    public SignUpRequestDto(String userName, String password, Integer age) {
        this.userName = userName;
        this.password = password;
        this.age = age;
    }
}
