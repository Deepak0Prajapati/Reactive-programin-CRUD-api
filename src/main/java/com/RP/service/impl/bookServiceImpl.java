package com.RP.service.impl;

import com.RP.entities.Book;
import com.RP.entities.repositories.BookRepository;
import com.RP.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class bookServiceImpl implements BookService {

    private BookRepository bookRepository;
    @Autowired
    public bookServiceImpl(BookRepository bookRepository) {
        this.bookRepository=bookRepository;
    }

    @Override
    public Mono<Book> create(Book book) {
        Mono<Book> createdBook = bookRepository.save(book);

        return createdBook;
    }

    @Override
    public Flux<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Mono<Book> get(int bookId) {
        Mono<Book> Item = bookRepository.findById(bookId);
        return Item;
    }

    @Override
    public Mono<Book> update(int bookId, Book book) {
        Mono<Book> oldBook = bookRepository.findById(bookId);

        return   oldBook.flatMap(book1->{
            book1.setName(book.getName());
            book1.setDescription(book.getDescription());
            book1.setAuthor(book.getAuthor());
            book1.setPublisher(book.getPublisher());
            return bookRepository.save(book1);
        });
    }

    @Override
    public Mono<Void> delete(int bookId) {
        return bookRepository.findById(bookId)
                .flatMap(book1->
                    bookRepository.delete(book1)
                );

    }

    @Override
    public Flux<Book> search(String query) {

        return null;
    }
}
