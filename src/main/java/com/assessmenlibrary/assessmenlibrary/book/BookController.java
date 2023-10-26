package com.assessmenlibrary.assessmenlibrary.book;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (path = "api/v1/")

public class BookController {
    
        private final BookService bookService;
        
       
        public BookController(BookService bookService){
            this.bookService=bookService;
        }

    	@GetMapping("getbooks")
	public List<Book> getBooks(){
		return bookService.getBooks();
	}

    @PostMapping("addbook")
    public ResponseEntity<String> registerNewBook(@RequestBody Book book) {
        ResponseEntity<String> responseEntity = bookService.addNewBook(book);
        return responseEntity;
    }
    @DeleteMapping(path = "delete/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable ("bookId") Long bookId) {
        ResponseEntity<String> responseEntity = bookService.deleteBook(bookId);
        return responseEntity;
    }


    @PutMapping(path = "edit/{bookId}")
    public ResponseEntity<String> updateStudent(
        @PathVariable("bookId") Long bookId,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String category,
        @RequestParam(required = false) String author,
        @RequestParam(required = false) String status,
        @RequestParam(required = false) Integer copy,
        @RequestParam(required = false) LocalDate dateadded
    )
    {
        ResponseEntity<String> responseEntity = bookService.updateBook(bookId, name, author, category, status, copy, dateadded);
        return responseEntity;

    }

    @GetMapping(path = "getBook/{bookId}")
    public ResponseEntity<?> getBook(@PathVariable("bookId") Long bookId) {
        return bookService.getBook(bookId);
    }
    @GetMapping(path = "searchBook/{searchWord}")
    public ResponseEntity<?> searchBook(@PathVariable("searchWord") String searchWord) {
        return bookService.searchBook(searchWord);
    }
}
