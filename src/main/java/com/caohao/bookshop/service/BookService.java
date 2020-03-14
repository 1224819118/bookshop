package com.caohao.bookshop.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caohao.bookshop.entity.Book;
import com.caohao.bookshop.mapper.BookMapper;
import org.springframework.stereotype.Service;

/**
 * 图书业务层
 */
@Service("bookService")
public class BookService extends ServiceImpl<BookMapper, Book> {


}
