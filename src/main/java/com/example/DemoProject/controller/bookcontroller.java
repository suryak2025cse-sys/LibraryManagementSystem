package com.example.DemoProject.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.DemoProject.model.book;
import com.example.DemoProject.services.BookServices;

@RestController //responce body and controller
@RequestMapping("/book")
public class bookcontroller {
    private final BookServices bookServices;
    public bookcontroller(BookServices bookServices) {
        this.bookServices = bookServices;
    }
    @GetMapping("/getbook")
    public book getBookDetails() {
        return bookServices.getBookDetails();
    }
    @PostMapping("/addbook")
    public book addbook(@RequestBody book b) {
        return bookServices.addbook(b);
    }

}
