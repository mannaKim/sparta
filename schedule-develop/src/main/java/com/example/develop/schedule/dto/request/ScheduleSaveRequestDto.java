package com.example.develop.schedule.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleSaveRequestDto {

    @NotBlank(message = "제목은 필수 입력값입니다.")
    @Size(max = 10, message = "제목은 10글자 이내여야 합니다.")
    private String title;

    @NotBlank(message = "내용은 필수 입력값입니다.")
    private String content;
}
