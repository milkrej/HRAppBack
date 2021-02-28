package com.iktpreobuka.testzadatakIntens.Entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

@Entity
@Table(name="Skills")
@JsonRootName(value="Skills")
public class SkillEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer skillId;
	@Column(name = "skillName", nullable = false, unique=true)//da li ostaviti unique ogranicenje
	@NotNull(message = "Skill name must be provided.")
	private String skillName;

//	@JsonIgnore
	@OneToMany(mappedBy = "skill", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<SkillCandidateEntity> candidateSkill;

	public SkillEntity() {
		super();
	}

	public Integer getSkillId() {
		return skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public List<SkillCandidateEntity> getCandidateSkill() {
		return candidateSkill;
	}

	public void setCandidateSkill(List<SkillCandidateEntity> candidateSkill) {
		this.candidateSkill = candidateSkill;
	}

}
