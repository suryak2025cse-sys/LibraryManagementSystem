package com.example.DemoProject.services;

import com.example.DemoProject.model.Member;
import com.example.DemoProject.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MemberServices {
    private final MemberRepository memberRepository;

    public MemberServices(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Long id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member ID cannot be null");
        }
        return memberRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Member not found with id: " + id));
    }

    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    public Member updateMember(Long id, Member memberDetails) {
        if (id == null && memberDetails != null) {
            id = memberDetails.getId();
        }
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member ID is required for update");
        }
        Member existingMember = getMemberById(id);
        if (memberDetails.getName() != null) {
            existingMember.setName(memberDetails.getName());
        }
        if (memberDetails.getEmail() != null) {
            existingMember.setEmail(memberDetails.getEmail());
        }
        return memberRepository.save(existingMember);
    }

    public String deleteMember(Long id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member ID is required for deletion");
        }
        Member existingMember = getMemberById(id);
        memberRepository.delete(existingMember);
        return "Member with ID " + id + " deleted successfully";
    }
}
