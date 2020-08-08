package jwd.finalExam.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.finalExam.model.Racun;
import jwd.finalExam.service.RacunService;
import jwd.finalExam.support.DtoToRacun;
import jwd.finalExam.support.RacunToDto;
import jwd.finalExam.web.dto.RacunDTO;





@RestController
@RequestMapping(value = "api/racuni")
public class ApiRacunController{
	@Autowired
	RacunService racSer;
	@Autowired
	RacunToDto racConv;
	
	@Autowired
	DtoToRacun dtoToRac;
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	ResponseEntity<RacunDTO> findOne(@PathVariable Long id){
		
		Racun racun = racSer.findOne(id);
		return new ResponseEntity<>(racConv.convert(racun), HttpStatus.OK);
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<RacunDTO>> findAll(
			@RequestParam(value="pageNum", defaultValue = "0") int pageNum,
			@RequestParam(required = false) String jmbg,
			@RequestParam(required = false) Long bankaId
			){
		
		Page<Racun> racuni = null;
		
		if(jmbg != null || bankaId != null) {
			racuni = racSer.search(jmbg, bankaId, pageNum);
		}else {
			racuni = racSer.findAll(pageNum);
		}
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(racuni.getTotalPages()));
		return new ResponseEntity<>(racConv.convert(racuni.getContent()), headers, HttpStatus.OK);
	}
	
	
	

	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	
	public ResponseEntity<RacunDTO> add(@RequestBody RacunDTO newRac){
		System.out.println(newRac.toString());

		Racun savedRacun = racSer.save(dtoToRac.convert(newRac));

		return new ResponseEntity<>(racConv.convert(savedRacun), HttpStatus.CREATED);
	}

	@RequestMapping(method=RequestMethod.PUT, value="/{id}",
					consumes="application/json")
	public ResponseEntity<RacunDTO> edit(@RequestBody RacunDTO dto,
											@PathVariable Long id){
		if(!id.equals(dto.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Racun persisted = racSer.save(dtoToRac.convert(dto));
	
		return new ResponseEntity<>(racConv.convert(persisted), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	ResponseEntity<RacunDTO> delete(@PathVariable Long id){
		
		if(racSer.findOne(id) != null && racSer.findOne(id).getStanje() != 0) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		Racun deleted = racSer.delete(id);
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(racConv.convert(deleted),	HttpStatus.OK);
	}

}
