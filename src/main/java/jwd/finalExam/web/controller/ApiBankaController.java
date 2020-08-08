package jwd.finalExam.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jwd.finalExam.model.Banka;
import jwd.finalExam.model.Tipracuna;
import jwd.finalExam.service.BankaService;
import jwd.finalExam.service.TipRacunaService;
import jwd.finalExam.support.BankaToDTo;
import jwd.finalExam.support.TipRacunaToDTo;
import jwd.finalExam.web.dto.BankaDTO;
import jwd.finalExam.web.dto.TipRacunaDTO;





@RestController
@RequestMapping(value = "api/banke")
public class ApiBankaController{
	
	@Autowired
	BankaService banSer;
	@Autowired
	BankaToDTo banConv;
	@Autowired
	TipRacunaService tipSer;
	
	@Autowired
	TipRacunaToDTo tipToDTO;
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/tipovi-racuna")
	ResponseEntity<List<TipRacunaDTO>> findAll(@PathVariable Long id){
		
		List<Tipracuna> tipovi = tipSer.findAllByBankaId(id);
		return new ResponseEntity<>(tipToDTO.convert(tipovi), HttpStatus.OK);
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<BankaDTO>> findAll(){
		
		List<Banka> banke = banSer.findAll();
		return new ResponseEntity<>(banConv.convert(banke), HttpStatus.OK);
	}

}
