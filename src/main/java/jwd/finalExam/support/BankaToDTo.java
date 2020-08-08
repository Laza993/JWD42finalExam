package jwd.finalExam.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.finalExam.model.Banka;
import jwd.finalExam.web.dto.BankaDTO;

@Component
public class BankaToDTo implements Converter<Banka, BankaDTO> {


	@Override
	public BankaDTO convert(Banka source) {
		BankaDTO dto = new BankaDTO();
		dto.setId(source.getId());
		dto.setNaziv(source.getNaziv());
		dto.setSredstva(source.getSredstva());
		return dto;
	}
	
	public List<BankaDTO> convert(List<Banka> source){
	List<BankaDTO> ret = new ArrayList<BankaDTO>();
	
	for(Banka a: source){
		ret.add(convert(a));
	}
	
	return ret;
}

}
