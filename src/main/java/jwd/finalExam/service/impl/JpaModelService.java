package jwd.finalExam.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.finalExam.repository.ModelRepository;
import jwd.finalExam.service.ModelService;

@Service
@Transactional
public class JpaModelService implements ModelService {
	
	@Autowired
	private ModelRepository modelRepositry;
}
