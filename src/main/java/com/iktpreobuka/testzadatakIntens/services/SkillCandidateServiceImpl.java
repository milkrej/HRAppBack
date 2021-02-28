package com.iktpreobuka.testzadatakIntens.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.testzadatakIntens.Entities.CandidateEntity;
import com.iktpreobuka.testzadatakIntens.Entities.SkillCandidateEntity;
import com.iktpreobuka.testzadatakIntens.Entities.SkillEntity;
import com.iktpreobuka.testzadatakIntens.exceptions.CustomException;
import com.iktpreobuka.testzadatakIntens.repositories.CandidateRepository;
import com.iktpreobuka.testzadatakIntens.repositories.SkillCandidateRepository;
import com.iktpreobuka.testzadatakIntens.repositories.SkillRepository;
@Service
public class SkillCandidateServiceImpl implements SkillCandidateService {
	
	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private SkillRepository skillRepository;

	@Autowired
	private SkillCandidateRepository skillCandidateRepository;

	
	@Override
	public SkillCandidateEntity addSkillToCandidate(String email, String skillName) throws CustomException {
		
		CandidateEntity candidate=candidateRepository.findByEmail(email);
		if(candidate==null||!candidate.getIsActive()) {
			throw new CustomException("Candidate with provided email not found.");
		}
		SkillEntity skill=skillRepository.findBySkillName(skillName);
		if(skill==null) {
			throw new CustomException("Skill with provided name not found.");
		}
		if(skillCandidateRepository.findAllByCandidateAndSkill(email, skillName)!=null) {
			throw new CustomException("Selected candidate already has provided skill.");
		}
		SkillCandidateEntity newSkillCandidate=new SkillCandidateEntity();
		newSkillCandidate.setCandidate(candidate);
		newSkillCandidate.setSkill(skill);
		skillCandidateRepository.save(newSkillCandidate);
		return newSkillCandidate;
		
	}


	@Override
	public SkillCandidateEntity removeSkillFromCandidate(String email, String skillName) throws CustomException {
		SkillCandidateEntity skillCandidate=skillCandidateRepository.findAllByCandidateAndSkill(email, skillName);
		if(skillCandidate==null) {
			throw new CustomException("Candidate with provided skill does not exist.");
		}
		skillCandidateRepository.delete(skillCandidate);
//		CandidateEntity candidate=candidateRepository.findByFirstNameAndLastName(firstName, lastName);
//		if(candidate==null||candidate.getIsActive()==false) {
//			throw new CustomException("Candidate with provided name is not found.");
//		}
//		SkillEntity skill=skillRepository.findByName(skillName);
//		if(skill==null) {
//			throw new CustomException("Skill with provided name not found.");
//		}
		
		return skillCandidate;
	}

}
