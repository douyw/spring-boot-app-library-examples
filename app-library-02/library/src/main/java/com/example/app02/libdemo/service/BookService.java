package com.example.app02.libdemo.service;

import com.example.app02.libdemo.domain.Book;
import com.example.app02.libdemo.repository.BookRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void saveApp(Book book) {
        bookRepository.save(book);
    }

    @Cacheable("books")
    public Book getBook(String bookId) {
        return bookRepository.findOne(bookId);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }
}
