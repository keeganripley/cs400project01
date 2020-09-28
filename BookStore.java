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

	// Constructor which creates the store's hash table and adds initial books to the
	// store.
	public BookStore(int capacity) {
		super(20);
		// Obtain initial books for the book store before user adds or removes books.
		Book[] books = getInitialBooks();
		// Initialize the store using global field with size 20 which is how many books
		// our store can hold.
		store = new HashTableMap<Integer, Book>(20);
		
		for (Book b : books) {
			// Get error because I call get before instantiating anything.
			store.put(b.getIsbn(), b);
			count = count + b.getQuantity();
		}

	}

	// Check to see if a book exists in the store hash table.
	public boolean containsBook(int isbn) {

		if (store.containsKey(isbn)) {
			return true;
		} else {
			return false;
		}
	}

	// Get info about a book.
	public Book getBook(int isbn) {
		// Returns the book requested by the user
		return store.get(isbn);
	}

	// Adds to the quantity of an existing book or add a new book the existing hash
	// table.
	public String donate(int isbn, Book newBook) {
		String message = "Thanks for donating " + store.get(isbn).getTitle() + "!";
		
		// If book already exists, add quantity of newBook to existing book's quantity
		// at the isbn.
		if (store.containsKey(isbn)) {
			int quantity = store.get(isbn).getQuantity();
			// Set the new quantity by taking the old quantity and adding the new book's
			// quantity.
			store.get(isbn).setQuantity(quantity + newBook.getQuantity());
			// Then increase amount of books in store by quantiy of books donated.
			count = count + store.get(isbn).getQuantity();
			return message;
		} else {
		// If book doesn't exist, add it to the store hash table.
		store.put(isbn, newBook);
		int quantity = store.get(isbn).getQuantity();
		// Then increase amount of books in store by quantiy of books donated.
		count = count + quantity;
		return message;
		}
	}

	// Removes a book from the store hash table.
	public String remove(int isbn) throws NoSuchElementException {
		String message = " Thanks for buying " + store.get(isbn).getTitle() + "!";
		try {
			store.get(isbn);
		} catch(Exception e) {
			System.out.println("This book does not exist.");
		}
//		if(store.containsKey(isbn) == false) {
//			throw new NoSuchElementException("This book does not exist.");
//		}
		// Gets the quantity of the book at the parameter isbn.
		int quantity = store.get(isbn).getQuantity();
		// If the quantity of the book is 1, then remove the book, other decrease
		// quantiy by one.
		if (quantity == 1) {
			store.remove(isbn);
		} else {
			store.get(isbn).setQuantity(quantity--);
		}
		//
		// Decrease number of books in store by one.
		count--;
		return message;
	}

	// All of the books in the store get stolen.
	public void steal() {
		// Clear the store, simulating a robbery
		store.clear();
		System.out.println("Oh no! The store has been robbed of knowledge!");
	}

	// Returns the number of books in the store, NOT the number of unique books
	// (elements).
	public int getCount() {
		return count;
	}

}
