package com.example.DemoProject.services;

import com.example.DemoProject.model.Author;
import com.example.DemoProject.repository.AuthorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AuthorServices {
    private final AuthorRepository authorRepository;

    public AuthorServices(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author ID cannot be null");
        }
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Author not found with id: " + id));
    }

    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author updateAuthor(Long id, Author authorDetails) {
        if (id == null && authorDetails != null) {
            id = authorDetails.getId();
        }
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author ID is required for update");
        }
        Author existingAuthor = getAuthorById(id);
        if (authorDetails.getName() != null) {
            existingAuthor.setName(authorDetails.getName());
        }
        if (authorDetails.getEmail() != null) {
            existingAuthor.setEmail(authorDetails.getEmail());
        }
        return authorRepository.save(existingAuthor);
    }

    public String deleteAuthor(Long id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author ID is required for deletion");
        }
        Author existingAuthor = getAuthorById(id);
        authorRepository.delete(existingAuthor);
        return "Author with ID " + id + " deleted successfully";
    }
}
