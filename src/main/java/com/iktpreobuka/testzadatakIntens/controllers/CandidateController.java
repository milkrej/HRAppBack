package com.iktpreobuka.testzadatakIntens.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.testzadatakIntens.Entities.CandidateEntity;
import com.iktpreobuka.testzadatakIntens.Entities.DTO.CandidateDto;
import com.iktpreobuka.testzadatakIntens.exceptions.CustomException;
import com.iktpreobuka.testzadatakIntens.services.CandidateService;
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping(path = "/api/jobcandidates")
public class CandidateController {

	@Autowired
	private CandidateService candidateService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?>getAllCandidates(){
		try {
			Iterable<CandidateEntity>candidates=candidateService.getAllCandidates();
			return new ResponseEntity<Iterable<CandidateEntity>>(candidates, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/by-name")
	public ResponseEntity<?> getCandidateByName(@RequestParam(value = "firstName") String firstName,
			@RequestParam(value = "lastName") String lastName) {
		try {
			List<CandidateEntity> candidates = candidateService.getCandidateByName(firstName, lastName);
			return new ResponseEntity<Iterable<CandidateEntity>>(candidates, HttpStatus.OK);
		} catch (CustomException ce) {
			return new ResponseEntity<>(ce.getMessage(), HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/by-skill/")
	public ResponseEntity<?> getCandidatesBySkill(@RequestParam(value = "skillName") String skillName) {
		try {
			List<CandidateEntity> candidates = candidateService.getCandidatesBySkill(skillName);
			return new ResponseEntity<Iterable<CandidateEntity>>(candidates, HttpStatus.OK);
		} catch (CustomException ce) {
			return new ResponseEntity<>(ce.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/by-candidateId/candidateId={candidateId}")
	public ResponseEntity<?> getCandidateById(@PathVariable("candidateId") Integer candidateId) {
		try {
			CandidateEntity candidate = candidateService.getCandidateById(candidateId);
			return new ResponseEntity<CandidateEntity>(candidate, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addNewCandidate(@Valid @RequestBody CandidateDto candidate) {
		try {
			CandidateEntity newCandidate = candidateService.addNewCandidate(candidate);
			return new ResponseEntity<CandidateEntity>(newCandidate, HttpStatus.OK);
		} catch (CustomException ce) {
			return new ResponseEntity<>(ce.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{email}")
	public ResponseEntity<?> removeCandidate(@PathVariable String email) {
		try {
			CandidateEntity candidate = candidateService.removeCandidate(email);
			return new ResponseEntity<CandidateEntity>(candidate, HttpStatus.OK);
		} catch (CustomException ce) {
			return new ResponseEntity<>(ce.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
