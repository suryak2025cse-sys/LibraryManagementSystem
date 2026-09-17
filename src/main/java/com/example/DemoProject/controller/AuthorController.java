package com.example.DemoProject.controller;

import com.example.DemoProject.model.Author;
import com.example.DemoProject.services.AuthorServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping({"/authors", "/author"})
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class AuthorController {
    private final AuthorServices authorServices;

    public AuthorController(AuthorServices authorServices) {
        this.authorServices = authorServices;
    }

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorServices.getAllAuthors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        return ResponseEntity.ok(authorServices.getAuthorById(id));
    }

    @PostMapping
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorServices.addAuthor(author));
    }

    // PUT with path variable: PUT /authors/1
    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthorById(@PathVariable Long id, @RequestBody Author author) {
        return ResponseEntity.ok(authorServices.updateAuthor(id, author));
    }

    // PUT with request body containing ID: PUT /authors
    @PutMapping
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author) {
        return ResponseEntity.ok(authorServices.updateAuthor(author.getId(), author));
    }

    // DELETE with path variable: DELETE /authors/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteAuthorById(@PathVariable Long id) {
        String message = authorServices.deleteAuthor(id);
        return ResponseEntity.ok(Map.of("message", message));
    }

    // DELETE with query parameter: DELETE /authors?id=1
    @DeleteMapping
    public ResponseEntity<Map<String, String>> deleteAuthorByParam(@RequestParam(required = false) Long id) {
        String message = authorServices.deleteAuthor(id);
        return ResponseEntity.ok(Map.of("message", message));
    }
}
