package ramblings.MonkeyBusiness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ramblings.MonkeyBusiness.model.Troop;

public interface TroopRepository extends JpaRepository<Troop, Integer> {
}
