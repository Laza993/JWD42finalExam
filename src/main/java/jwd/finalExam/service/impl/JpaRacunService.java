package jwd.finalExam.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.finalExam.model.Banka;
import jwd.finalExam.model.Racun;
import jwd.finalExam.model.Tipracuna;
import jwd.finalExam.repository.BankaRepository;
import jwd.finalExam.repository.RacunRepository;
import jwd.finalExam.service.BankaService;
import jwd.finalExam.service.RacunService;
import jwd.finalExam.service.TipRacunaService;

@Service
@Transactional
public class JpaRacunService implements RacunService {
	
	@Autowired
	private RacunRepository racRep;
	
	@Autowired
	private BankaService banSer;
	@Autowired
	private TipRacunaService tipSer;



	@Override
	public Racun findOne(Long id) {
		// TODO Auto-generated method stub
		return racRep.findOne(id);
	}

	@Override
	public Racun save(Racun racun) {
		// TODO Auto-generated method stub
		return racRep.save(racun);
	}

	@Override
	public Racun delete(Long id) {
		Racun deleted = findOne(id);
		racRep.delete(id);
		return deleted;
	}
	
	
	@PostConstruct
	public void init() {
		Banka ban1 = banSer.findOne(1L);

		Banka ban2 = banSer.findOne(1L);
		
		
		Tipracuna tip1 = tipSer.findOne(1L);
		Tipracuna tip2 = tipSer.findOne(2L);
		
		Racun rac1 = new Racun("lazaLaz", "0805223", "521", 1200.0, tip1, ban1);

		Racun rac2 = new Racun("kika", "451223", "221", 1000.0, tip2, ban2);
		save(rac1);
		save(rac2);
	}

	@Override
	public Page<Racun> search(String jmbg, Long bankaId, int pageNum) {
		if(jmbg != null) {
			jmbg = '%' + jmbg + '%';
		}

		return racRep.search(jmbg, bankaId, new PageRequest(pageNum, 2));
	}

	@Override
	public Page<Racun> findAll(int pageNum) {
		
		return racRep.findAll(new PageRequest(pageNum, 2));
	}

}
