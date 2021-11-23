package br.com.healthtrack.repository;

import br.com.healthtrack.entity.User;
import br.com.healthtrack.entity.Weight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeightRepository extends JpaRepository<Weight, Integer> {

    List<Weight> findAllByUserId(Integer userId);

    Weight findByUserIdAndWeightId(Integer userId, Integer weightId);
}
