package com.iktpreobuka.testzadatakIntens.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iktpreobuka.testzadatakIntens.Entities.CandidateEntity;
import com.iktpreobuka.testzadatakIntens.Entities.SkillCandidateEntity;
import com.iktpreobuka.testzadatakIntens.Entities.SkillEntity;

public interface SkillCandidateRepository extends CrudRepository<SkillCandidateEntity, Integer> {
	@Query(value="SELECT * FROM candidate_skills cS INNER JOIN skills s ON s.skill_id=cS.skill_id WHERE c.skill_name =?1", nativeQuery=true)
	public Iterable<SkillCandidateEntity>findAllBySkillName(String skillName);
	
	public SkillCandidateEntity findByCandidateAndSkill(CandidateEntity candidate, SkillEntity skill);
	
	@Query(value="SELECT * FROM candidate_skills cS INNER JOIN candidates c ON c.candidate_id=cS.candidate_id INNER JOIN skills s ON s.skill_id=cS.skill_id WHERE c.email=:email AND s.skill_name=:skillName", nativeQuery=true)
	public SkillCandidateEntity findAllByCandidateAndSkill(@Param("email") String email,@Param("skillName") String skillName);
	
//	@Query("SELECT * FROM candidateSkill cs INNER JOIN candidate c ON c.candidateId=cs.candidateId WHERE c.firstName=?1 AND c.lastName=?2")
//	public Iterable<SkillCandidateEntity>findByCandidateName(String firstName, String lastName);
	
	
}

