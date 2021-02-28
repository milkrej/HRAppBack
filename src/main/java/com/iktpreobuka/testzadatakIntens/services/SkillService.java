package com.iktpreobuka.testzadatakIntens.services;





import com.iktpreobuka.testzadatakIntens.Entities.SkillEntity;
import com.iktpreobuka.testzadatakIntens.Entities.DTO.SkillDto;
import com.iktpreobuka.testzadatakIntens.exceptions.CustomException;

public interface SkillService {
public Iterable<SkillEntity>getAllSkills();
public SkillEntity addNewSkill(SkillDto skill) throws CustomException;
public Iterable<SkillEntity>getAllSkillsForCandidate(Integer candidateId);

}
