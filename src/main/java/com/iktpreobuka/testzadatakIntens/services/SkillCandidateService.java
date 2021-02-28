package com.iktpreobuka.testzadatakIntens.services;

import org.springframework.stereotype.Service;

import com.iktpreobuka.testzadatakIntens.Entities.SkillCandidateEntity;
import com.iktpreobuka.testzadatakIntens.exceptions.CustomException;

public interface SkillCandidateService {
	public SkillCandidateEntity addSkillToCandidate(String email, String skillName) throws CustomException;
	public SkillCandidateEntity removeSkillFromCandidate(String email, String skillName) throws CustomException;


}
