package br.com.healthtrack.dto;

import br.com.healthtrack.entity.User;

public class UserResponseDto {

    private String name;
    private String email;
    private int age;

    public UserResponseDto(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public static UserResponseDto transformInDto(User user) {
        return new UserResponseDto(
                user.getName(),
                user.getEmail(),
                user.getAge()
        );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
