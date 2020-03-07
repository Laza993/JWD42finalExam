package jwd.finalExam.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.finalExam.model.Prevoznik;
import jwd.finalExam.web.dto.PrevoznikDTO;

@Component
public class ModelToDto implements Converter<Prevoznik, PrevoznikDTO> {

	@Override
	public PrevoznikDTO convert(Prevoznik source) {
		// TODO Auto-generated method stub
		return null;
	}

}
