package jwd.finalExam.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.finalExam.model.Banka;
import jwd.finalExam.repository.BankaRepository;
import jwd.finalExam.service.BankaService;

@Service
@Transactional
public class JpaBankaService implements BankaService {
	
	@Autowired
	private BankaRepository banRep;;

	@Override
	public List<Banka> findAll() {
		// TODO Auto-generated method stub
		return banRep.findAll();
	}

	@Override
	public Banka findOne(Long id) {
		// TODO Auto-generated method stub
		return banRep.findOne(id);
	}

	@Override
	public Banka save(Banka banka) {
		// TODO Auto-generated method stub
		return banRep.save(banka);
	}
	
	
	@PostConstruct
	public void init() {
		Banka ban1 = new Banka("intesa", 10000000.0);

		Banka ban2 = new Banka("komercijalna", 15200000.0);
		save(ban1);
		save(ban2);
	}

}
