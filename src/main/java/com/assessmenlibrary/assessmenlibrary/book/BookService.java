package com.assessmenlibrary.assessmenlibrary.book;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class BookService {

    private final BookRepository bookRepository; 

   
    public BookService(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }
    //Get all book
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }
   
    //get book by id
    public ResponseEntity<?> getBook(long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if (optionalBook.isPresent()) {
            return ResponseEntity.ok(optionalBook.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No book found with ID: " + bookId);
        }
    }

    //search book by name
    public ResponseEntity<?> searchBook(String searchWord) {
        Optional<Book> optionalBook = bookRepository.findBookByName(searchWord);

        if (optionalBook.isPresent()) {
            return ResponseEntity.ok(optionalBook.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No book found with Name " + searchWord);
        }
    }

    //add new book
    public ResponseEntity<String> addNewBook(Book book) {
        Optional<Book> bookByName = bookRepository.findBookByName(book.getName());

        if (bookByName.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Book already registered");
        }

        bookRepository.save(book);

        return ResponseEntity.status(HttpStatus.CREATED).body("Book registered successfully");
    }
    
    //delete book by id
    public ResponseEntity<String> deleteBook(Long bookId){
        boolean exists= bookRepository.existsById(bookId);
        if(!exists){
             return ResponseEntity.status(HttpStatus.CONFLICT).body("book does not exist");
        }       
        bookRepository.deleteById(bookId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Book deleted successfully");

    }

    //edit book
    @Transactional
    public ResponseEntity<String> updateBook(Long bookId, String name, String author, String category, String status,
            Integer copy, LocalDate dateadded) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();

            if (name != null && name.length() > 0 && !Objects.equals(book.getName(), name)) {
                Optional<Book> bookByName = bookRepository.findBookByName(name);

                if (bookByName.isPresent()) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Book already Registered: " + name);
                }

                book.setName(name);
                return ResponseEntity.status(HttpStatus.OK).body("Book updated successfully");
            }

            if (author != null && author.length() > 0 && !Objects.equals(book.getAuthor(), author)) {
                book.setAuthor(author);
            }

            if (category != null && category.length() > 0 && !Objects.equals(book.getCategory(), category)) {
                book.setCategory(category);
            }

            if (status != null && status.length() > 0 && !Objects.equals(book.getStatus(), status)) {
                book.setStatus(status);
            }

            if (copy != null && !Objects.equals(book.getCopy(), copy)) {
                book.setCopy(copy);
            }

            if (dateadded != null && !Objects.equals(book.getDateadded(), dateadded)) {
                book.setDateadded(dateadded);
            }

            bookRepository.save(book); // Save changes to the database

            return ResponseEntity.status(HttpStatus.OK).body("Book updated successfully");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found with ID: " + bookId);
    }
}
