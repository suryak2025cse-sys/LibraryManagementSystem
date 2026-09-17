package com.example.DemoProject.controller;

import com.example.DemoProject.model.Book;
import com.example.DemoProject.services.BookServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping({"/books", "/book"})
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class BookController {
    private final BookServices bookServices;

    public BookController(BookServices bookServices) {
        this.bookServices = bookServices;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookServices.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookServices.getBookById(id));
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookServices.addBook(book));
    }

    // PUT with path variable: PUT /books/1
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable Long id, @RequestBody Book book) {
        return ResponseEntity.ok(bookServices.updateBook(id, book));
    }

    

    @PutMapping
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookServices.updateBook(book.getId(), book));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteBookById(@PathVariable Long id) {
        String msg = bookServices.deleteBook(id);
        return ResponseEntity.ok(Map.of("message", msg));
    }

    
    @DeleteMapping
    public ResponseEntity<Map<String, String>> deleteBookByParam(@RequestParam(required = false) Long id) {
        String msg = bookServices.deleteBook(id);
        return ResponseEntity.ok(Map.of("message", msg));
    }
}
