package br.com.healthtrack.entity;

import br.com.healthtrack.dto.WeightRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_WEIGHT")
public class Weight {

    @Id
    @Column(name = "weight_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer weightId;

    @Column(name = "inserted_weight", nullable = false)
    private Date insertedWeight;

    @Column(nullable = false)
    private double kg;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false,
            updatable = false)
    @JsonIgnore
    private User user;

    @Column(name = "user_id")
    private Integer userId;

    public static Weight fromDto(WeightRequestDto weightRequestDto, Integer userId) {
        Weight weightConverted = new Weight();
        weightConverted.setUserId(userId);
        weightConverted.setInsertedWeight(weightRequestDto.getInsertedWeight());
        weightConverted.setKg(weightRequestDto.getKg());

        return weightConverted;
    }


    public Integer getWeightId() {
        return weightId;
    }

    public void setWeightId(Integer weightId) {
        this.weightId = weightId;
    }

    public Date getInsertedWeight() {
        return insertedWeight;
    }

    public void setInsertedWeight(Date insertedWeight) {
        this.insertedWeight = insertedWeight;
    }

    public double getKg() {
        return kg;
    }

    public void setKg(double kg) {
        this.kg = kg;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
