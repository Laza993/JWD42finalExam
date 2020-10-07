package jwd.finalExam.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.finalExam.model.Banka;
import jwd.finalExam.model.Tipracuna;
import jwd.finalExam.repository.BankaRepository;
import jwd.finalExam.repository.TipRacunaRepository;
import jwd.finalExam.service.BankaService;
import jwd.finalExam.service.RacunService;
import jwd.finalExam.service.TipRacunaService;

@Service
@Transactional
public class JpaTipRacunaService implements TipRacunaService {
	
	@Autowired
	private TipRacunaRepository tipRep;
	@Autowired
	private BankaService banSer;

	@Override
	public Tipracuna save(Tipracuna tip) {
		// TODO Auto-generated method stub
		return tipRep.save(tip);
	}

	@Override
	public Tipracuna findOne(Long id) {
		// TODO Auto-generated method stub
		return tipRep.findOne(id);
	}
	
	
//	@PostConstruct
	public void init() {
		Banka ban1 = banSer.findOne(1L);
		Banka ban2 = banSer.findOne(2L);	
		Tipracuna tip1 = new Tipracuna("tekuci", 5.0, ban1);
		Tipracuna tip2 = new Tipracuna("stedni", 3.0, ban2);
		Tipracuna tip3 = new Tipracuna("stedniIntesa", 3.0, ban1);
		Tipracuna tip4 = new Tipracuna("tekuciKomercijalna", 4.8, ban2);
		save(tip1);
		save(tip2);
		save(tip3);
		save(tip4);
	}

	@Override
	public List<Tipracuna> findAll() {
		// TODO Auto-generated method stub
		return tipRep.findAll();
	}

	@Override
	public List<Tipracuna> findAllByBankaId(Long id) {
		// TODO Auto-generated method stub
		return tipRep.findAllByBankaId(id);
	}

	@Override
	public Tipracuna delete(Long id) {
		Tipracuna deleted = findOne(id);
		tipRep.delete(id);
		return deleted;
	}
	

}
