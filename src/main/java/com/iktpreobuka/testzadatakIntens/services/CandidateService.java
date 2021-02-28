package com.iktpreobuka.testzadatakIntens.services;

import java.util.List;
import java.util.Set;


import org.springframework.stereotype.Service;

import com.iktpreobuka.testzadatakIntens.Entities.CandidateEntity;

import com.iktpreobuka.testzadatakIntens.Entities.DTO.CandidateDto;
import com.iktpreobuka.testzadatakIntens.exceptions.CustomException;

//@Service
public interface CandidateService {
	public Iterable<CandidateEntity>getAllCandidates();
	
	public CandidateEntity getCandidateById(Integer candidateId);
	
	public List<CandidateEntity> getCandidatesBySkill(String skillName) throws CustomException;
	
	public List<CandidateEntity> getCandidateByName(String firstName, String lastName) throws CustomException;
	
	

	//public ResponseEntity<?> getCandidateByName(String lastName); trenutno u repozitorijumu (ili da vratim ovde)?

	public CandidateEntity addNewCandidate(CandidateDto candidate)throws CustomException;

	
//	public ResponseEntity<?> modifySkill(Integer candidateId, String skillName);// ili mozda proslediti skillId
	
	
	
	public CandidateEntity removeCandidate(String email) throws CustomException;

}
