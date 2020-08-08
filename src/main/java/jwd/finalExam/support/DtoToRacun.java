package jwd.finalExam.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.finalExam.model.Racun;
import jwd.finalExam.service.BankaService;
import jwd.finalExam.service.RacunService;
import jwd.finalExam.service.TipRacunaService;
import jwd.finalExam.web.dto.RacunDTO;

@Component
public class DtoToRacun implements Converter<RacunDTO, Racun> {
	
	@Autowired
	RacunService racSer;
	@Autowired
	BankaService banSer;
	@Autowired
	TipRacunaService tipSer;


	@Override
	public Racun convert(RacunDTO source) {
		Racun racun = null;
		if(source.getId() != null && racSer.findOne(source.getId() ) != null   ) {
			racun = racSer.findOne(source.getId());
		}else {
			racun = new Racun();
		}
		racun.setBanka(banSer.findOne(source.getBankaId()));
		racun.setTip(tipSer.findOne(source.getTipId()));
		racun.setBrRacuna(source.getBrRacuna());
		racun.setStanje(source.getStanje());
		racun.setJMDB(source.getJMDB());
		racun.setImePrezime(source.getImePrezime());
	
		return racun;
	}


}
