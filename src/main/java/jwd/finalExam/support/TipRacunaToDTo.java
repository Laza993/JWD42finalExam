package jwd.finalExam.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.finalExam.model.Banka;
import jwd.finalExam.model.Tipracuna;
import jwd.finalExam.web.dto.BankaDTO;
import jwd.finalExam.web.dto.TipRacunaDTO;

@Component
public class TipRacunaToDTo implements Converter<Tipracuna, TipRacunaDTO> {


	@Override
	public TipRacunaDTO convert(Tipracuna source) {
		TipRacunaDTO dto = new TipRacunaDTO();
		dto.setBankaId(source.getBanka().getId());
		dto.setBankaNaziv(source.getBanka().getNaziv());
		dto.setId(source.getId());
		dto.setNaziv(source.getNaziv());
		dto.setProvizija(source.getProvizija());
		return dto;
	}
	
	public List<TipRacunaDTO> convert(List<Tipracuna> source){
	List<TipRacunaDTO> ret = new ArrayList<TipRacunaDTO>();
	
	for(Tipracuna a: source){
		ret.add(convert(a));
	}
	
	return ret;
}



}
