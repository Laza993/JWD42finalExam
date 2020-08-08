package jwd.finalExam.service;

import java.util.List;

import jwd.finalExam.model.Banka;

public interface BankaService {
	
	List<Banka> findAll();
	Banka findOne(Long id);
	Banka save(Banka banka);
}
