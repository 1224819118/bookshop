package com.caohao.bookshop;

import com.caohao.bookshop.entity.Book;
import com.caohao.bookshop.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BookshopApplicationTests {

    @Autowired
    BookService bookService;
    @Test
    void finBookList() {
        bookService.list().forEach(System.out::println);
    }

}
