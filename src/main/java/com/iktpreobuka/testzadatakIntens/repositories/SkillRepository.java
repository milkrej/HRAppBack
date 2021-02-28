package com.iktpreobuka.testzadatakIntens.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.testzadatakIntens.Entities.SkillEntity;

public interface SkillRepository extends CrudRepository<SkillEntity, Integer> {
	public SkillEntity findBySkillName (String skillName);
	@Query(value="SELECT * FROM Skills s INNER JOIN candidate_skills cS on cS.candidate_id=s.skill_id INNER JOIN Candidates c ON c.candidate_id= cS.skill_id WHERE c.candidate_id=?1", nativeQuery=true)
	public List<SkillEntity>findAllSkillOfCandidate(Integer candidateId);
	

}
