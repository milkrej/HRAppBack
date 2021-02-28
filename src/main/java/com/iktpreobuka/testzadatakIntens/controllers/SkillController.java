package com.iktpreobuka.testzadatakIntens.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.testzadatakIntens.Entities.SkillEntity;
import com.iktpreobuka.testzadatakIntens.Entities.DTO.SkillDto;
import com.iktpreobuka.testzadatakIntens.exceptions.CustomException;
import com.iktpreobuka.testzadatakIntens.services.SkillService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/api/skills")
public class SkillController {
	@Autowired
	private SkillService skillService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllSkills() {
		try {
			Iterable<SkillEntity> skills = skillService.getAllSkills();
			return new ResponseEntity<Iterable<SkillEntity>>(skills, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method=RequestMethod.GET, value = "/{candidateId}")
	public ResponseEntity<?>getAllSkillsForCandidate(@PathVariable Integer candidateId){
		try {
			Iterable<SkillEntity>skills=skillService.getAllSkillsForCandidate(candidateId);
			return new ResponseEntity<Iterable<SkillEntity>>(skills, HttpStatus.OK);
		}catch(Exception e){
	
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addNewSkill(@Valid @RequestBody SkillDto skill) {
		try {

			SkillEntity newSkill = skillService.addNewSkill(skill);
			return new ResponseEntity<SkillEntity>(newSkill, HttpStatus.OK);
		} catch (CustomException ce) {
			return new ResponseEntity<>(ce.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
