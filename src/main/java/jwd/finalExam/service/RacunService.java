package jwd.finalExam.service;

import java.util.List;

import org.springframework.data.domain.Page;

import jwd.finalExam.model.Racun;

public interface RacunService {
	Racun findOne(Long id);
	Racun save(Racun racun);
	Racun delete(Long id);
	Page<Racun> search(String jmbg, Long bankaId, int pageNum);
	Page<Racun> findAll(int pageNum);
	List<Racun> findAll();

}
