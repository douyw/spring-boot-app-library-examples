package com.example.app02.mgmt.task;

import com.example.app02.libdemo.domain.Book;
import com.example.app02.libdemo.service.BookService;
import com.example.app02.mgmt.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private final BookService bookService;

    private final MessageService messageService;

    public ScheduledTasks(BookService bookService, MessageService messageService) {
        this.bookService = bookService;
        this.messageService = messageService;
    }

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        try {
            messageService.sendMessage(dateFormat.format(new Date()));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        testAppInfo();
    }

    private void testAppInfo() {
        Book book = new Book();
        book.setId("app122");
        book.setName("AppName");
        bookService.saveApp(book);
        List<Book> books = bookService.getBooks();
        log.info("books size: {}", books.size());
    }
}
