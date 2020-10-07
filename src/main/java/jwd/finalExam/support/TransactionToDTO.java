package jwd.finalExam.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.finalExam.model.Transakcija;
import jwd.finalExam.web.dto.TransakcijaDTO;

@Component
public class TransactionToDTO implements Converter<Transakcija, TransakcijaDTO> {

	@Override
	public TransakcijaDTO convert(Transakcija source) {
		TransakcijaDTO dto = new TransakcijaDTO();
		dto.setIznos(source.getIznos());
		dto.setRacunPrimaocId(source.getRacunPrimaoc().getId());
		dto.setRacunPrimaocBroj(source.getRacunPrimaoc().getBrRacuna());
		
		dto.setRacunUplatiocId(source.getRacunUplatioc().getId());
		dto.setRacunUplatiocBroj(source.getRacunUplatioc().getBrRacuna());
		
		dto.setStateOfTransaction(source.getStateOfTransaction());
		
		return dto;
	}
	
	public List<TransakcijaDTO> convert(List<Transakcija> sorce){
		List<TransakcijaDTO> dtos = new ArrayList<TransakcijaDTO>();
		for(Transakcija trans : sorce) {
			TransakcijaDTO dto = convert(trans);
			dtos.add(dto);
		}
		return dtos;
	}
	

}
