package com.RP.service;

import com.RP.entities.Book;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface BookService {


    public Mono<Book> create(Book book);
    public Flux<Book> getAll();
    public Mono<Book> get(int bookId);
    public Mono<Book> update(int bookId, Book book);
    public Mono<Void> delete(int bookId);
    public Flux<Book> search(String query);


}
