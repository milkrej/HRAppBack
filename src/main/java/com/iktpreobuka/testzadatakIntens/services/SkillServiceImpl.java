package com.iktpreobuka.testzadatakIntens.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.testzadatakIntens.Entities.SkillEntity;
import com.iktpreobuka.testzadatakIntens.Entities.DTO.SkillDto;
import com.iktpreobuka.testzadatakIntens.exceptions.CustomException;
import com.iktpreobuka.testzadatakIntens.repositories.SkillRepository;
@Service
public class SkillServiceImpl implements SkillService {
	
	@Autowired
	private SkillRepository skillRepository;
	
	

	@Override
	public SkillEntity addNewSkill(SkillDto skill) throws CustomException {
		
		SkillEntity newSkill=new SkillEntity();
		if(skill.getSkillName()==null||skill.getSkillName()==""||skill.getSkillName()==" ") {
			throw new CustomException("Skill name must be provided.");
		}
		if(skillRepository.findBySkillName(skill.getSkillName())!=null) {
			throw new CustomException("Skill with provided name already exists.");
		}
		newSkill.setSkillName(skill.getSkillName());
		skillRepository.save(newSkill);
		
		return newSkill;
	}



	@Override
	public Iterable<SkillEntity> getAllSkills() {
		Iterable<SkillEntity>skills=skillRepository.findAll();
		return skills;
	}



	@Override
	public Iterable<SkillEntity> getAllSkillsForCandidate(Integer candidateId) {
		Iterable<SkillEntity>skills=skillRepository.findAllSkillOfCandidate(candidateId);
		return skills;
	}

}
