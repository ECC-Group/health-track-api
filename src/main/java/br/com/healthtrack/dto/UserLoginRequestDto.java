package br.com.healthtrack.dto;

import br.com.healthtrack.entity.User;

public class UserLoginRequestDto {

    private Integer userId;
    private String email;
    private String password;

    public static UserLoginRequestDto fromUser(User user) {
        UserLoginRequestDto userRequestDto = new UserLoginRequestDto();
        userRequestDto.setEmail(user.getEmail());
        userRequestDto.setPassword(user.getPassword());
        userRequestDto.setUserId(user.getUserId());

        return userRequestDto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
