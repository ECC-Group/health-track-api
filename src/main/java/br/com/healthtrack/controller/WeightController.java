package br.com.healthtrack.controller;

import br.com.healthtrack.dto.WeightRequestDto;
import br.com.healthtrack.dto.WeightUpdateDto;
import br.com.healthtrack.entity.User;
import br.com.healthtrack.entity.Weight;
import br.com.healthtrack.repository.UserRepository;
import br.com.healthtrack.repository.WeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class WeightController {

    @Autowired
    private WeightRepository weightRepository;

    @Autowired
    private UserRepository userRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user/{userId}/weight")
    public List<WeightRequestDto> createWeight(@Valid @RequestBody WeightRequestDto weightRequestDto,
                                               @PathVariable Integer userId) {
        User user = userRepository.findById(userId).get();
        List<Weight> weights = new ArrayList<>();
        weights.add(Weight.fromDto(weightRequestDto, userId));

        return createListWeightDto(this.weightRepository.saveAll(weights));
    }

    @GetMapping("/user/{userId}/weight")
    @ResponseStatus(HttpStatus.OK)
    public List<WeightRequestDto> getAllWeights(@PathVariable Integer userId) {
        List<Weight> weightList = weightRepository.findAllByUserId(userId);

        return createListWeightDto(weightList);
    }

    @GetMapping("/user/{userId}/weight/{weightId}")
    @ResponseStatus(HttpStatus.OK)
    public WeightRequestDto getWeight(@PathVariable Integer userId, @PathVariable Integer weightId) {
        Weight weightFromDB = weightRepository.findByUserIdAndWeightId(userId, weightId);

        return WeightRequestDto.fromWeight(weightFromDB);
    }

    @PutMapping("/user/{userId}/weight/{weightId}")
    @ResponseStatus(HttpStatus.OK)
    public Weight updateWeight(@PathVariable Integer userId, @PathVariable Integer weightId,
                               @Valid @RequestBody WeightUpdateDto weightUpdateDto) {

        Weight weight = weightRepository.findByUserIdAndWeightId(userId, weightId);

        weight.setKg(weightUpdateDto.getKg());
        weight.setInsertedWeight(weightUpdateDto.getInsertedWeight());

        return this.weightRepository.save(weight);
    }

    @DeleteMapping("/user/{userId}/weight/{weightId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteWeight(@PathVariable Integer userId, @PathVariable Integer weightId) {

        Weight weight = weightRepository.findByUserIdAndWeightId(userId, weightId);
        this.weightRepository.delete(weight);

        return "Peso removido com sucesso";
    }

    private List<WeightRequestDto> createListWeightDto(List<Weight> weights) {
        List<WeightRequestDto> weightsRequestDto = new ArrayList<>();

        for (Weight weight : weights) {
            weightsRequestDto.add(WeightRequestDto.fromWeight(weight));
        }
        return weightsRequestDto;
    }
}
