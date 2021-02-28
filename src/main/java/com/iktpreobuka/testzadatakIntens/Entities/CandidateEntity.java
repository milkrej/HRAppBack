package com.iktpreobuka.testzadatakIntens.Entities;

import java.util.Date;
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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Candidates")
@JsonRootName(value = "Candidates")
@JsonPropertyOrder({ "firstName", "lastName", "dateOfBirth", "contactNumber", "email" })
@JsonIgnoreProperties({"skills"})
public class CandidateEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer candidateId;
	@Column(name = "firstName", nullable = false)
	@NotNull(message = "First name must be provided.")
	private String firstName;
	@Column(name = "lastName", nullable = false)
	@NotNull(message = "Last name must be provided.")
	private String lastName;
	@Column(name = "dateOfBirth", nullable = false)
	@NotNull(message = "Date of birth must be provided.")
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-DD")
	private Date dateOfBirth;
	@Column(name = "contactNumber", nullable = false)
	@NotNull(message = "Contact number must be provided.")
	private String contactNumber; // add PhoneNum class potentially
	@Column(name = "email", nullable = false, unique=true)
	@NotNull(message = "Email must be provided.")
	@Email(message="Email must be valid.")
	private String email;
	private Boolean isActive;

//	@JsonIgnore
	@OneToMany(mappedBy = "candidate", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<SkillCandidateEntity> candidateSkill;

	public CandidateEntity() {
		super();
		this.isActive = true;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.dateOfBirth = dateOfBirth;
//		this.contactNumber = contactNumber;
//		this.email = email;
	}

	public Integer getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Integer candidateId) {
		this.candidateId = candidateId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public List<SkillCandidateEntity> getSkills() {
		return candidateSkill;
	}

	public void setSkills(List<SkillCandidateEntity> candidateSkill) {
		this.candidateSkill = candidateSkill;
	}

}
