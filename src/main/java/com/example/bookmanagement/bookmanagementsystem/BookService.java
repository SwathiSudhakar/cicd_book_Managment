package com.example.bookmanagement.bookmanagementsystem;
public interface BookService {
 Book addBook(Book book);
 Book getBookByIsbn(String isbn);
 Book updateBook(String isbn, Book book);
}