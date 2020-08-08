package jwd.finalExam.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.finalExam.model.Racun;
import jwd.finalExam.web.dto.RacunDTO;

@Component
public class RacunToDto implements Converter<Racun, RacunDTO> {

	@Override
	public RacunDTO convert(Racun source) {
		RacunDTO dto = new RacunDTO();
		dto.setBankaId(source.getBanka().getId());
		dto.setBankaNaziv(source.getBanka().getNaziv());
		
		dto.setBrRacuna(source.getBrRacuna());
		
		dto.setId(source.getId());
		dto.setImePrezime(source.getImePrezime());
		
		dto.setJMDB(source.getJMDB());
		
		dto.setStanje(source.getStanje());
		
		dto.setTipId(source.getTip().getId());
		dto.setTipNaziv(source.getTip().getNaziv());
		
		
		return dto;
	}

	
	public List<RacunDTO> convert(List<Racun> racuni){
	List<RacunDTO> ret = new ArrayList<RacunDTO>();
	
	for(Racun a: racuni){
		ret.add(convert(a));
	}
	
	return ret;
}

}
