package net.javaguides.springboot.controller;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.service.BookService;

@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {

    private final BookService bookService;

    BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{isbn}")
    public String getBookNameByIsbn(@PathVariable("isbn") String isbn) {
    	log.info("******************* getBookNameByIsbn:"+isbn);
    	String test= bookService.getBookNameByIsbn(isbn);
    	log.info("******************* getBookNameByIsbn final:"+test);
    	return test;
    }
}

