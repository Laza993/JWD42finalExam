package jwd.finalExam.service;

import java.util.List;

import jwd.finalExam.model.Tipracuna;

public interface TipRacunaService {
	Tipracuna save(Tipracuna tip);
	Tipracuna findOne(Long id);
	Tipracuna delete(Long id);
	List<Tipracuna> findAll();
	List<Tipracuna> findAllByBankaId(Long id);
	
}
