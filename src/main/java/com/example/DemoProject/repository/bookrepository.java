package com.example.DemoProject.repository;
import org.springframework.stereotype.Repository;
import com.example.DemoProject.model.book;
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    
}

// JpaRepository provides built-in methods for CRUD operations, such as save(), findAll(), findById(), existsById(), count(), and deleteById(). You can use these methods without explicitly defining them in your repository interface.
//find()- get all the books from the database
//save()- save a book to the database
//findById()- find a book by its id
//existsById()- check if a book exists by its id
//count()- count the number of books in the database
//deleteById()- delete a book by its id