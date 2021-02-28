package com.iktpreobuka.testzadatakIntens.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iktpreobuka.testzadatakIntens.Entities.CandidateEntity;

public interface CandidateRepository extends CrudRepository<CandidateEntity, Integer> {
	
	public CandidateEntity findByEmail(String email);
	
	@Query(value="SELECT * FROM candidates c WHERE c.first_name=:firstName AND c.last_name=:lastName AND is_active=1", nativeQuery=true)
	public List<CandidateEntity> findByFirstNameAndLastNameAndIsActiveTrue(@Param("firstName")String firstName, @Param("lastName")String lastName);
	
//	@Query("SELECT * from candidate c WHERE c.firstName=:firstName AND c.lastName=:lastName ANS c.isActive=true")
//	public Set<CandidateEntity>findAllByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName")String lastName);
	@Query(value="SELECT * FROM Candidates c INNER JOIN candidate_skills cS on cS.candidate_id=c.candidate_id INNER JOIN Skills s ON s.skill_id= cS.skill_id WHERE s.skill_name=?1", nativeQuery=true)
//	@Query(value="SELECT c FROM CandidateEntity c WHERE c.candidateSkill.skill.skillName=?1")
	public List<CandidateEntity>findCandidatesBySkillName(String skillName);
	
	public List<CandidateEntity>findAllByEmail(String email);
	
	public List<CandidateEntity>findByIsActiveTrue();
	
	public CandidateEntity findByCandidateId(Integer candidateId);

}
