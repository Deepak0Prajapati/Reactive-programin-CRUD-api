package com.RP.controller;

import com.RP.entities.Book;
import com.RP.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService=bookService;
    }

    @PostMapping
    public Mono<Book> create(@RequestBody Book book){
       return bookService.create(book);

    }

    @GetMapping
    public Flux<Book> getAll(){
        Flux<Book> all = bookService.getAll();
        return all;
    }

    @GetMapping("/{bookid}")
    public Mono<Book> getById(@PathVariable int bookid){
        return bookService.get(bookid);
    }

    @PutMapping("/{bookId}")
    public Mono<Book> updateBook(@PathVariable int bookId,@RequestBody Book book){
        return  bookService.update(bookId,book);
    }

    @DeleteMapping("/{bookid}")
    public  Mono<Void> deleteBook(@PathVariable int bookid){
        return bookService.delete(bookid);
    }




}
