package com.iktpreobuka.testzadatakIntens.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.iktpreobuka.testzadatakIntens.Entities.CandidateEntity;
import com.iktpreobuka.testzadatakIntens.exceptions.CustomException;
import com.iktpreobuka.testzadatakIntens.repositories.CandidateRepository;
import com.iktpreobuka.testzadatakIntens.services.CandidateService;
import com.iktpreobuka.testzadatakIntens.services.CandidateServiceImpl;


@SpringBootTest
public class CandidateServiceTest  {
	@Mock
    private CandidateRepository candidateRepository;

//    @InjectMocks // auto inject helloRepository
	
    private CandidateService candidateService;

    @BeforeEach
    void setMockOutput() {
        candidateService = new CandidateServiceImpl(candidateRepository);
    }

    @DisplayName("Test Mock candidateService + candidateRepository")
    @Test
    void testRemoveCandidate() throws CustomException {
    	CandidateEntity candidate=new CandidateEntity();
    	candidate.setCandidateId(5);
    	candidate.setIsActive(true);
   
    	when(candidateRepository.findByEmail("milkrej@yahoo.com")).thenReturn(candidate);
    	when(candidateRepository.save(candidate)).thenReturn(candidate);
    	
    	CandidateEntity removedCandidate=candidateService.removeCandidate("milkrej@yahoo.com");
        assertFalse(removedCandidate.getIsActive());
        assertEquals(5, removedCandidate.getCandidateId().intValue());
    }
    @Test
    void testWhenCandidateIsNull() throws CustomException{
    	
    	CustomException thrown = assertThrows( CustomException.class,()->{
    		when(candidateRepository.findByEmail("milkrej@yahoo.com")).thenReturn(null);
        	candidateService.removeCandidate("milkrej@yahoo.com");
    	});

        String expectedMessage = "Candidate with provided email not found.";
        String actualMessage = thrown.getMessage();

    	
    	assertTrue(actualMessage.contains(expectedMessage));
    	
    }
}
