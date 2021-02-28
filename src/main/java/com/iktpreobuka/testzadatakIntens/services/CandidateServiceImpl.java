package com.iktpreobuka.testzadatakIntens.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import com.iktpreobuka.testzadatakIntens.Entities.CandidateEntity;
import com.iktpreobuka.testzadatakIntens.Entities.SkillCandidateEntity;
import com.iktpreobuka.testzadatakIntens.Entities.SkillEntity;
import com.iktpreobuka.testzadatakIntens.Entities.DTO.CandidateDto;
import com.iktpreobuka.testzadatakIntens.exceptions.CustomException;
import com.iktpreobuka.testzadatakIntens.repositories.CandidateRepository;
import com.iktpreobuka.testzadatakIntens.repositories.SkillCandidateRepository;
import com.iktpreobuka.testzadatakIntens.repositories.SkillRepository;
@Service
public class CandidateServiceImpl implements CandidateService {

	@Autowired
	private CandidateRepository candidateRepository;
	
	public CandidateServiceImpl(CandidateRepository candidateRepository) {
		this.candidateRepository=candidateRepository;
	}

	
//	@RequestMapping(method = RequestMethod.GET, value = "/by-skill/{skillName}")
	
//		try {

//		SkillEntity skill = skillRepository.findByName(skillName);
//		if (skill == null) {
////			return new ResponseEntity<RestError>(new RestError(1, "Candidate with provided skill is not found."),
////					HttpStatus.NOT_FOUND);
//		}
//		Iterable<SkillsListEntity> allCandidateSkill = skillListRepository.findAll();
//		List<CandidateEntity> candidates = new ArrayList<>();
//		for (SkillsListEntity candidateSkill : allCandidateSkill) {
//			if (candidateSkill.getSkill().getSkillName().equals(skillName)) {
//				candidates.add(candidateSkill.getCandidate());
//			}
//		}
//
//		return Set<CandidateEntity>>(candidates);
//	}catch (Exception e) {
//		return new ResponseEntity<RestError>(new RestError(1, "Exception occured: " + e.getMessage()),
//				HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	

//	private String createErrorMessage(BindingResult result) {
//		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
//	}

	@Override
	public CandidateEntity addNewCandidate(CandidateDto candidate) throws CustomException {
//		try {
//			if (result.hasErrors()) {
//				return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
	

	CandidateEntity newCandidate = new CandidateEntity();
	if(candidate.getFirstName()==null||candidate.getFirstName().equals("")||candidate.getFirstName().equals(" "))
	{
		throw new CustomException("First name must be provided.");
	}
	if(candidate.getLastName()==null||candidate.getLastName().equals("")||candidate.getLastName().equals(" "))
	{
		throw new CustomException("Last name must be provided.");
	}
	if(candidate.getDateOfBirth()==null)
	{
		throw new CustomException("Date of birth must be provided.");
	}
	
	if(candidate.getContactNumber()==null||candidate.getContactNumber().equals("")||candidate.getContactNumber().equals(" "))
	{
		throw new CustomException("Phone number must be provided.");
	}
	if(candidate.getEmail()==null||!candidate.getEmail().contains("@"))
	{
		throw new CustomException("Email must be provided.");
	}
	newCandidate.setFirstName(candidate.getFirstName());
	newCandidate.setLastName(candidate.getLastName());
	newCandidate.setDateOfBirth(candidate.getDateOfBirth());
	newCandidate.setContactNumber(candidate.getContactNumber());
	newCandidate.setEmail(candidate.getEmail());
	
	candidateRepository.save(newCandidate);
	return newCandidate;

//	}catch (Exception e) {
//		return new ResponseEntity<RestError>(new RestError(1, "Exception occured: " + e.getMessage()),
//				HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	}


	@Override
	@Transactional
	public CandidateEntity removeCandidate(String email)throws CustomException {
		CandidateEntity candidate=candidateRepository.findByEmail(email);
		if(candidate==null||!candidate.getIsActive()) {
			throw new CustomException("Candidate with provided email not found.");
		}
		candidate.setIsActive(false);
		candidateRepository.save(candidate);
		return candidate;
	}

	@Override
	public List<CandidateEntity> getCandidateByName(String firstName, String lastName) throws CustomException {
		List<CandidateEntity> candidates=candidateRepository.findByFirstNameAndLastNameAndIsActiveTrue(firstName, lastName);
		if(candidates==null||candidates.isEmpty()) {
			throw new CustomException("Candidate(s) with provided name not found.");
		}
		return candidates;
	}
	@Override
	public List<CandidateEntity> getCandidatesBySkill(String skillName) throws CustomException {
		if (skillName == null || skillName.equals("") || skillName.equals(" ")) {
			return null;
		}
		List<CandidateEntity> candidates = candidateRepository.findCandidatesBySkillName(skillName);
		if (candidates==null||candidates.isEmpty()) {
			throw new CustomException("No candidates with provided skill name.");//ili mozda NullPointerException
		}
			return candidates;
	}


	@Override
	public Iterable<CandidateEntity> getAllCandidates() {
		Iterable<CandidateEntity>candidates=candidateRepository.findByIsActiveTrue();
		return candidates;
	}


	@Override
	public CandidateEntity getCandidateById(Integer candidateId) {
		CandidateEntity candidate=candidateRepository.findById(candidateId).orElse(null);
		return candidate;
	}
}
