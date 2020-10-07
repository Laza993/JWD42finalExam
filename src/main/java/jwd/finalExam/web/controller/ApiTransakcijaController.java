package jwd.finalExam.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jwd.finalExam.model.Transakcija;
import jwd.finalExam.service.TransakcijaService;
import jwd.finalExam.support.DTOtoTransaction;
import jwd.finalExam.support.TransactionToDTO;
import jwd.finalExam.web.dto.TransakcijaDTO;

@RestController
@RequestMapping(value = "api/transakcije")
public class ApiTransakcijaController {
	
	@Autowired
	TransakcijaService transakcijaService;
	@Autowired
	TransactionToDTO transakcijaToDto;
	@Autowired
	DTOtoTransaction dtoToTransakcija;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TransakcijaDTO>> getAllTransactions(){
		
		List<TransakcijaDTO> dtos = transakcijaToDto.convert(transakcijaService.findAll());
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<TransakcijaDTO> makeTransaction(@RequestBody TransakcijaDTO transakcijaDTO){
		System.out.println(transakcijaDTO.toString());
		Transakcija transakcija = transakcijaService.makeTransaction(dtoToTransakcija.convert(transakcijaDTO));
		
		if(!transakcija.getStateOfTransaction()) {
			return new ResponseEntity<>(transakcijaToDto.convert(transakcija), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(transakcijaToDto.convert(transakcija), HttpStatus.OK);
	}
	
}
