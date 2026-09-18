package com.example.DemoProject.services;

import com.example.DemoProject.model.Author;
import com.example.DemoProject.model.Book;
import com.example.DemoProject.repository.AuthorRepository;
import com.example.DemoProject.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookServices {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServices(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Book addBook(Book book) {
        if (book.getAuthor() != null && book.getAuthor().getId() != null) {
            Author author = authorRepository.findById(book.getAuthor().getId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Author not found with id: " + book.getAuthor().getId()));
            book.setAuthor(author);
        }
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
            if (bookDetails.getAuthor().getId() != null) {
                Author author = authorRepository.findById(bookDetails.getAuthor().getId())
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Author not found with id: " + bookDetails.getAuthor().getId()));
                existingBook.setAuthor(author);
            } else {
                existingBook.setAuthor(bookDetails.getAuthor());
            }
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

