package jwd.finalExam.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.finalExam.model.Transakcija;
import jwd.finalExam.service.RacunService;
import jwd.finalExam.service.TransakcijaService;
import jwd.finalExam.web.dto.TransakcijaDTO;


@Component
public class DTOtoTransaction implements Converter<TransakcijaDTO, Transakcija> {
	
	@Autowired
	TransakcijaService transakcijaService;
	@Autowired
	RacunService racunService;
	
	
	@Override
	public Transakcija convert(TransakcijaDTO dto) {
		Transakcija transakcija = null;
		if(dto.getId() != null && transakcijaService.findOne(dto.getId()) != null) {
			transakcija = transakcijaService.findOne(dto.getId());
			transakcija.setIznos(dto.getIznos());
			transakcija.setRacunPrimaoc(racunService.findOne(dto.getRacunPrimaocId()));
			transakcija.setRacunUplatioc(racunService.findOne(dto.getRacunUplatiocId()));
			transakcija.setStateOfTransaction(dto.getStateOfTransaction());
		}else {
			transakcija = new Transakcija();
			transakcija.setIznos(dto.getIznos());
			transakcija.setRacunPrimaoc(racunService.findOne(dto.getRacunPrimaocId()));
			transakcija.setRacunUplatioc(racunService.findOne(dto.getRacunUplatiocId()));
			transakcija.setStateOfTransaction(dto.getStateOfTransaction());
		}
		return transakcija;
	}

}
