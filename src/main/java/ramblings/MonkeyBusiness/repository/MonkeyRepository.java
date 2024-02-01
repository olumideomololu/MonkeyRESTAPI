package ramblings.MonkeyBusiness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ramblings.MonkeyBusiness.model.Monkey;

import java.util.List;

public interface MonkeyRepository extends JpaRepository<Monkey, Integer> {
    List<Monkey> findAllByNameContaining(String name);
}
