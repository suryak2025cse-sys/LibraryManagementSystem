package com.example.DemoProject.controller;

import com.example.DemoProject.model.Member;
import com.example.DemoProject.services.MemberServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping({"/members", "/member"})
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class MemberController {
    private final MemberServices memberServices;

    public MemberController(MemberServices memberServices) {
        this.memberServices = memberServices;
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberServices.getAllMembers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        return ResponseEntity.ok(memberServices.getMemberById(id));
    }

    @PostMapping
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberServices.addMember(member));
    }

    // PUT with path variable: PUT /members/1
    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMemberById(@PathVariable Long id, @RequestBody Member member) {
        return ResponseEntity.ok(memberServices.updateMember(id, member));
    }

    // PUT with request body containing ID: PUT /members
    @PutMapping
    public ResponseEntity<Member> updateMember(@RequestBody Member member) {
        return ResponseEntity.ok(memberServices.updateMember(member.getId(), member));
    }

    // DELETE with path variable: DELETE /members/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteMemberById(@PathVariable Long id) {
        String message = memberServices.deleteMember(id);
        return ResponseEntity.ok(Map.of("message", message));
    }

    // DELETE with query parameter: DELETE /members?id=1
    @DeleteMapping
    public ResponseEntity<Map<String, String>> deleteMemberByParam(@RequestParam(required = false) Long id) {
        String message = memberServices.deleteMember(id);
        return ResponseEntity.ok(Map.of("message", message));
    }
}
