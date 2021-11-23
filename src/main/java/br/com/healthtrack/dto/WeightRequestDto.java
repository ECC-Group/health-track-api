package br.com.healthtrack.dto;

import br.com.healthtrack.entity.Weight;

import java.util.Date;

public class WeightRequestDto {

    private Integer weightId;
    private Date insertedWeight;
    private double kg;

    public static WeightRequestDto fromWeight(Weight weight) {
        WeightRequestDto weightRequestDto = new WeightRequestDto();
        weightRequestDto.setWeightId(weight.getWeightId());
        weightRequestDto.setInsertedWeight(weight.getInsertedWeight());
        weightRequestDto.setKg(weight.getKg());

        return weightRequestDto;
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

    public Integer getWeightId() {
        return weightId;
    }

    public void setWeightId(Integer weightId) {
        this.weightId = weightId;
    }
}
