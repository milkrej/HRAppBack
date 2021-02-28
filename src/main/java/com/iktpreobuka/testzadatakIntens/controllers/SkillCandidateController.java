package com.iktpreobuka.testzadatakIntens.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.testzadatakIntens.Entities.SkillCandidateEntity;
import com.iktpreobuka.testzadatakIntens.exceptions.CustomException;
import com.iktpreobuka.testzadatakIntens.services.SkillCandidateService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping(path = "/api/candidateSkills")
public class SkillCandidateController {

	@Autowired
	private SkillCandidateService skillCandidateService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addSkillToCandidate(@RequestParam("email") String email, @RequestParam("skillName") String skillName) {
		try {
			SkillCandidateEntity newSkillCandidate = skillCandidateService.addSkillToCandidate(email, skillName);
			return new ResponseEntity<SkillCandidateEntity>(newSkillCandidate, HttpStatus.OK);
		} catch (CustomException ce) {
			return new ResponseEntity<>(ce.getMessage(), HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<?> removeSkillFromCandidate(@RequestParam("email") String email, @RequestParam("skillName")String skillName) {
		try {
			SkillCandidateEntity skillCandidate = skillCandidateService.removeSkillFromCandidate(email, skillName);
			return new ResponseEntity<SkillCandidateEntity>(skillCandidate, HttpStatus.OK);
		} catch (CustomException ce) {
			return new ResponseEntity<>(ce.getMessage(), HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
