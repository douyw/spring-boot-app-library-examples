package com.example.app02.libdemo.repository;

import com.example.app02.libdemo.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
