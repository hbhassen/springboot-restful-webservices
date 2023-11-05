package net.javaguides.springboot.service;



import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class BookService {
    @Cacheable("books")
    public String getBookNameByIsbn(String isbn) {
    	log.info("getBookNameByIsbn:"+isbn);
        String value =findBookInSlowSource(isbn);
        log.info("getBookNameByIsbn value:"+value);
        return value;
    }

    private String findBookInSlowSource(String isbn) {
        // some long processing
    	log.info("findBookInSlowSource:"+isbn);
        try {
        	log.info("before sleep findBookInSlowSource:");
            Thread.sleep(3000);
            log.info("after sleep findBookInSlowSource:");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        log.info("after catch findBookInSlowSource:");
        return "Sample Book Name";
    }
}
