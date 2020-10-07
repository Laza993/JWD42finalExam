package jwd.finalExam.service;

import java.util.List;


import jwd.finalExam.model.Transakcija;

public interface TransakcijaService {

	Transakcija findOne(Long id);
	
	List<Transakcija> findAll();

	Transakcija makeTransaction(Transakcija transakcija);
}
