package com.example.bookmanagement.bookmanagementsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.example.bookmanagement.bookmanagementsystem.Book;
import com.example.bookmanagement.bookmanagementsystem.BookRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

	private BookService bookService;
	private BookRepository bookRepository = mock(BookRepository.class);

	Book book;

	@BeforeEach
	void setUp() {
		// MockitoAnnotations.openMocks(this); 
		book = new	Book("111", "Springboot", "Nobody", 50.00);

		bookService = new BookServiceImpl(bookRepository);
	}

	@Test // testAddBookSuccessfully // Purpose: To verify that a book can be added successfully
	//to the system with valid details. 
	// Expectation: The book is	added without errors, 	and the	returned book	details match then input.

	void testAddBookSuccessfully() {
  when(bookRepository.save(any())).thenReturn(book); Book b1 =
  bookService.addBook(book); verify(bookRepository, times(1)).save(any());
  assertEquals(b1.getAuthor(), "Nobody"); assertEquals(b1.getIsbn(),
  "111"); assertEquals(b1.getPrice(), Double.parseDouble("50.00"));
  assertEquals(b1.getTitle(), "Springboot"); }

	@Test
	// testAddBookWithExistingIsbn:
	// Purpose: To ensure the system correctly handles attempts to add a book with
	// an ISBN that already exists in the database. // Expectation: The system
	// throws a `RuntimeException`, indicating that a book
	// with the same ISBN cannot be added.

	void testAddBookWithExistingIsbn() {
		doThrow(new RuntimeException("Same ISBN exception")).when(bookRepository).save(any());
		Throwable exception = assertThrows(RuntimeException.class, () -> {
			bookService.addBook(book);
		});
		assertEquals("Same ISBN exception", exception.getMessage());
		verify(bookRepository, times(1)).save(any());
	}

	@Test // testAddBookWithIncompleteDetails
	// Purpose: To assess how the system handles attempts to add a book with
	// incomplete or invalid details.
	// Expectation: The system throws an `IllegalArgumentException`, indicating
	// that books with incomplete details cannot be added.

	void testAddBookWithIncompleteDetails() {
		doThrow(new IllegalArgumentException("Incomplete details exception")).when(bookRepository)
				.save(any());
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			bookService.addBook(book);
		});
		String log = "Incomplete details exception";
		assertEquals(log, exception.getMessage());
		verify(bookRepository, times(1)).save(any());
	}

	@Test 
	// testGetBookByIsbnSuccessfully 
	// Purpose: To test the system's	ability to	retrieve a	book by	its ISBN  successfully. 
	// Expectation: The	correct book	is returned, and	its details	match what	was  stored. 
	void  testGetBookByIsbnSuccessfully() {
  when(bookRepository.findByIsbn(anyString())).thenReturn(book); Book b1 =
  bookService.getBookByIsbn(anyString()); verify(bookRepository,
  times(1)).findByIsbn(any()); assertEquals(b1.getAuthor(), "Nobody");
  assertEquals(b1.getIsbn(), "111"); assertEquals(b1.getPrice(),
  Double.parseDouble("50.00")); assertEquals(b1.getTitle(), "Springboot"); }

	@Test 
	// testGetBookByIsbnNotFound // Purpose: To verify the system's behavior when attempting to	retrieve a	book 
  // by an ISBN that does not exist in	the database. 
	// Expectation: The system returns `null`, indicating no	book was 	found with the  provided ISBN.

	void testGetBookByIsbnNotFound() {
  when(bookRepository.findByIsbn(anyString())).thenReturn(null); Book b1 =
  bookService.getBookByIsbn(anyString()); verify(bookRepository,
  times(1)).findByIsbn(any()); assertEquals(b1,null); }

	@Test // testUpdateBookSuccessfully // Purpose: To test updating the details	of an	existing book	in the	system. 
	// Expectation: The book's details are			updated successfully, and	the updated 
	// details are correctly reflected in	the returned	book object.

	void testUpdateBookSuccessfully() {
  when(bookRepository.findByIsbn(anyString())).thenReturn(book);
  when(bookRepository.update(any())).thenReturn(book); Book b1 =
  bookService.updateBook(book.getIsbn(),book); verify(bookRepository,
  times(1)).update(any()); verify(bookRepository, times(1)).findByIsbn(any());
  assertEquals(b1.getAuthor(), "Nobody"); assertEquals(b1.getIsbn(),
  "111"); assertEquals(b1.getPrice(), Double.parseDouble("50.00"));
  assertEquals(b1.getTitle(), "Springboot"); }

	@Test 
	// testUpdateNonExistingBook // Purpose: To check the system's behavior when attempting to
	//update a  non-existing book (a book not found in the database). 
	// Expectation: The system does not update any book and returns`null`, 
	// indicating the operation was unsuccessful due to the book not being found.

	void testUpdateNonExistingBook() {
  when(bookRepository.findByIsbn(anyString())).thenReturn(null);
  when(bookRepository.update(any())).thenReturn(book); Book b1 =
  bookService.updateBook(book.getIsbn(),book); verify(bookRepository,
  times(0)).update(any()); verify(bookRepository, times(1)).findByIsbn(any());
  assertEquals(b1,null); }
}