package com.gbc.codingmates.service.candidate;

import com.gbc.codingmates.domain.candidate.Candidate;
import com.gbc.codingmates.domain.candidate.CandidateRepository;
import com.gbc.codingmates.dto.candidate.CandidateDto;
import com.gbc.codingmates.dto.member.MemberDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CandidateService {
    private final CandidateRepository candidateRepository;
    private final ModelMapper modelMapper;

    //save candidate's application
    @Transactional
    public ResponseEntity<Long> saveCandidate(final CandidateDto candidateDto){
        Candidate candidate = modelMapper.map(candidateDto, Candidate.class);
        candidateRepository.save(modelMapper.map(candidateDto, Candidate.class));
        return ResponseEntity.ok(candidate.getId());
    }

    //list all
    public ResponseEntity<List<Candidate>> listAll(){
        return ResponseEntity.ok(candidateRepository.findAll());
    }

    //delete candidate's application
    @Transactional
    public ResponseEntity<Long> delete(final MemberDto memberDto, final CandidateDto candidateDto) throws AccessDeniedException{
        Candidate candidate = candidateRepository.findById(candidateDto.getId()).orElseThrow(()->new IllegalStateException());
        Long candidateId = candidate.getMember_id();
        if (candidateId != memberDto.getMemberId()) {
            throw new AccessDeniedException("you are not authorised");
        }
        Long id = candidate.getId();
        candidateRepository.deleteById(id);
        return ResponseEntity.ok(id);
    }
}
