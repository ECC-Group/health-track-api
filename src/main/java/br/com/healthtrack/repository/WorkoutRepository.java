package br.com.healthtrack.repository;

import br.com.healthtrack.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<Workout, Integer> {
}
