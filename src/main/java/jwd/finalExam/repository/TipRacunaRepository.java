package jwd.finalExam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.finalExam.model.Tipracuna;

@Repository
public interface TipRacunaRepository extends JpaRepository<Tipracuna, Long>{

	List<Tipracuna> findAllByBankaId(Long id);

}
