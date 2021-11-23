package br.com.healthtrack.dto;

import br.com.healthtrack.entity.User;

public class UserLoginResponseDto {

    private Integer userId;

    public UserLoginResponseDto(Integer userId) {
       this.userId = userId;
    }

    public static UserLoginResponseDto transformInDto(User user) {
        return new UserLoginResponseDto(
                user.getUserId()
        );
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
