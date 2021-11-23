package br.com.healthtrack.entity;

import br.com.healthtrack.dto.UserLoginRequestDto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "T_USER")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String password;

    @Column(nullable = false)
    private int age;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Weight> weights;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Workout> workouts;

    public User(Integer id, String name, String email, String password, int age) {
        this.userId = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;

    }

    public User() {
    }

    public static User fromDto(UserLoginRequestDto userLoginRequestDto) {
        User user = new User();
        user.setPassword(userLoginRequestDto.getPassword());
        user.setEmail(userLoginRequestDto.getEmail());
        user.setUserId(user.getUserId());

        return user;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
