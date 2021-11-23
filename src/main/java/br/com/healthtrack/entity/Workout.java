package br.com.healthtrack.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_WORKOUT")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "inserted_workout", nullable = false)
    private Date insertedWorkout;

    @Column(nullable = false)
    private String workoutType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Workout(Integer id, Date insertedWorkout, String workoutType, User user) {
        this.id = id;
        this.insertedWorkout = insertedWorkout;
        this.workoutType = workoutType;
        this.user = user;
    }

    public Workout() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInsertedWorkout() {
        return insertedWorkout;
    }

    public void setInsertedWorkout(Date insertedWorkout) {
        this.insertedWorkout = insertedWorkout;
    }

    public String getWorkoutType() {
        return workoutType;
    }

    public void setWorkoutType(String workoutType) {
        this.workoutType = workoutType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
