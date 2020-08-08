package jwd.finalExam.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.finalExam.model.Racun;

@Repository
public interface RacunRepository extends JpaRepository<Racun, Long>{
	
	
	
	@Query("SELECT r FROM Racun r WHERE "
	+ "(:jmbg IS NULL or r.JMDB like :jmbg ) AND "
	+ "(:bankaId IS NULL OR r.banka.id = :bankaId) ")
	Page<Racun> search(
			@Param("jmbg") String jmbg, 
			@Param("bankaId") Long bankaId, 
			Pageable pageRequest);

}
