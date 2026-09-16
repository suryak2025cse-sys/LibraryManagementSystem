package com.example.DemoProject.services;
import org.springframework.stereotype.Service;
import com.example.DemoProject.model.book;
@Service
public class BookServices {
    public book getBookDetails() {
        return new book("java", "james gosling", 100);
        
    }
    public book addbook(book b) {
        
        return b;
    }
}
