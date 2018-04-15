package com.example.app02.mgmt.controller;

import com.example.app02.libdemo.domain.Book;
import com.example.app02.libdemo.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok(bookService.getBooks());
    }

    @PostMapping("/books")
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        bookService.saveApp(book);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/books/{book_id}")
    public ResponseEntity<Book> getBook(@PathVariable("book_id") String bookId) {
        Book app = bookService.getBook(bookId);
        if (app == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(app);
    }
}
