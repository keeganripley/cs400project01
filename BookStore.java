import java.util.NoSuchElementException;

// --== CS400 File Header Information ==--
// Name: Keegan Ripley
// Email: kjripley@wisc.edu
// Team: EE
// Role: Backend Developer
// TA: Keren
// Lecturer: Florian
// Notes to Grader: <optional extra notes>
public class BookStore extends BookCollection {

	HashTableMap<Integer, Book> store; // Creates an instance of HashTableMap to represent the book store.
	private int count; // Stores the number of books in the book store, NOT the number of elements in
						// the hash table.

	/**
	 * 
	 * @param capacity: max number of books in the bookstore
	 * 
	 *                  This constructor method is used to generate a book store,
	 *                  which is a hash table. It will then add the books that will
	 *                  be available upon the book store opening.
	 */
	public BookStore(int capacity) throws FileNotFoundException{
		super(20);
		count = 0;
		// Obtain initial books for the book store before user adds or removes books.
		addInitialBooksToTable();
		Book[] books = getInitialBooks();
		// Initialize the book store.
		store = new HashTableMap<Integer, Book>(20);
		// Iterate through the first 4 indices, adding the initial books.
		for (int i = 0; i < books.length && books[i] != null; i++) {
			count = count + books[i].getQuantity();
			// If the book is already in the hash table, update quantity.
			if (store.containsKey(books[i].getIsbn())) {
				store.get(books[i].getIsbn())
						.setQuantity(store.get(books[i].getIsbn()).getQuantity() + books[i].getQuantity());
				// Otherwise the book does not exist yet, so put it into the book store.
			} else
				store.put(books[i].getIsbn(), books[i]);

		}

	}

	/**
	 * 
	 * @param isbn: isbn of the book user is checking
	 * @return true if book exists, false if it doesn't
	 * 
	 *         This method checks to see if a book exists in the book store.
	 */
	public boolean containsBook(int isbn) {
		if (store.containsKey(isbn)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param isbn: isbn of the book user is checking
	 * @return the book at the isbn given by user
	 * 
	 *         This method gets the book the user wants to look at.
	 */
	public Book getBook(int isbn) {
		return store.get(isbn);
	}

	/**
	 * 
	 * @param isbn:    isbn of the book user is checking
	 * @param newBook: the new book to be added
	 * @return true if book successfully added, false if not added
	 * 
	 *         This method adds a book to the book store, as if a user donated it.
	 */
	public boolean donate(int isbn, Book newBook) {
		// If the book already exists, update quantity.
		if (store.containsKey(isbn)) {
			store.get(isbn).setQuantity(store.get(isbn).getQuantity() + newBook.getQuantity());
			// Otherwise the book does not exist yet, so put it into the book store.
		} else
			store.put(isbn, newBook);

		return (containsBook(isbn));
	}

	/**
	 * 
	 * @param isbn: isbn of the book user is checking
	 * @return either the title of the book or a message saying it does not exist.
	 * @throws NoSuchElementException
	 * 
	 *                                This method removes a book that has been
	 *                                purchased by the user.
	 */
	public String remove(int isbn) {
		// If the book exists, remove it.
		if (containsBook(isbn)) {
			// Create a message to be returned when book is purchased.
			Book selling = store.get(isbn);
			String confirmSale = "Book bought: " + selling.getTitle();

			// If quantity is below 2, remove the book from the bookstore.
			if (store.get(isbn).getQuantity() < 2) {
				store.remove(isbn);
			}
			// Otherwise decrease quantity of the book by one.
			else {
				store.get(isbn).setQuantity(store.get(isbn).getQuantity() - 1);
				count--;
			}

			return confirmSale;
		// Otherwise tell user book does not exist.
		} else
			return "I'm sorry, we do not have this book.";
	}

	/**
	 * This method removes all books from the book store, simulating being robbed.
	 */
	public void steal() {
		store.clear();
		System.out.println("Oh no! The store has been robbed of knowledge!");
	}

	/**
	 * 
	 * @return total number of books in the book store, NOT the number of elements
	 *         in the hash table.
	 *         
	 *         This method is a getter for count.
	 */
	public int getCount() {
		return count;
	}

}
