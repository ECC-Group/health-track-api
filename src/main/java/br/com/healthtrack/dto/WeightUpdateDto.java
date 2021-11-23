package br.com.healthtrack.dto;

import br.com.healthtrack.entity.Weight;

import java.util.Date;

public class WeightUpdateDto {

    private Date insertedWeight;
    private double kg;

    public static WeightUpdateDto fromWeight(Weight weight) {
        WeightUpdateDto weightUpdateDto = new WeightUpdateDto();
        weightUpdateDto.setInsertedWeight(weight.getInsertedWeight());
        weightUpdateDto.setKg(weight.getKg());

        return weightUpdateDto;
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
}
