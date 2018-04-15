package com.example.app02.mgmt.messaging;

import com.example.app02.libdemo.domain.Book;
import com.example.app02.libdemo.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Receiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    private CountDownLatch latch;

    private final BookService bookService;

    @Autowired
    public Receiver(CountDownLatch latch, BookService bookService) {
        this.latch = latch;
        this.bookService = bookService;
    }

    public void receiveMessage(String message) {
        LOGGER.info("Received <" + message + ">");

        List<Book> books = bookService.getBooks();
        LOGGER.info("> receive message: app size = {}", books.size());

        String book_id = "app122";
        Book book = bookService.getBook(book_id);
        if (book != null) {
            LOGGER.info("app found. app id: {} - {}", book_id, book.getName());
        } else {
            LOGGER.warn("target app id not found. app id: {}", book_id);
        }

        latch.countDown();
    }
}
