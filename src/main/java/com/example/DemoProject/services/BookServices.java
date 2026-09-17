package com.example.DemoProject.services;

import com.example.DemoProject.model.Book;
import com.example.DemoProject.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookServices {
    private final BookRepository bookRepository;

    public BookServices(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book ID cannot be null");
        }
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Book not found with id: " + id));
    }

    public Book updateBook(Long id, Book bookDetails) {
        if (id == null && bookDetails != null) {
            id = bookDetails.getId();
        }
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book ID is required for update");
        }
        Book existingBook = getBookById(id);
        if (bookDetails.getTitle() != null) {
            existingBook.setTitle(bookDetails.getTitle());
        }
        if (bookDetails.getAuthor() != null) {
            existingBook.setAuthor(bookDetails.getAuthor());
        }
        if (bookDetails.getPrice() != null) {
            existingBook.setPrice(bookDetails.getPrice());
        }
        return bookRepository.save(existingBook);
    }

    public String deleteBook(Long id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book ID is required for deletion");
        }
        Book existingBook = getBookById(id);
        bookRepository.delete(existingBook);
        return "Book with ID " + id + " deleted successfully";
    }
}

