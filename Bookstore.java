// --== CS400 File Header Information ==--
// Name: Keegan Ripley
// Email: kjripley@wisc.edu
// Team: EE
// Role: Backend Developer
// TA: Keren
// Lecturer: Florian
// Notes to Grader: <optional extra notes>
public class Bookstore<KeyType, ValueType> {
	private int isbn; // ISBN number but without first 3 numbers (978); therefore only stores last 10
	// digits of ISBN # (starts with 1 or 0)
	private String title; // book title
	private int price; // book price
	private String genre; // genre of book
	private int quantity; // number of books currently available

	// KeyIsbn is last 10 digits of ISBN of book
	// ValueBook is a book object that has 5 parameters:
	// isbn, title, price, genre, quantity
	// To retrieve, get method for desired stored value
	HashTableMap<Integer, Book> bookstore = new HashTableMap<Integer, Book>(20);

	// put() method alteration
	// if key exists, then quantity++
	// if key doesn't exists, put book into hash table
	public void put(Integer key, Book newBook) {
		// set global variables to equal the information of the parameter book
		isbn = newBook.getIsbn();
		title = newBook.getTitle();
		genre = newBook.getGenre();
		quantity = newBook.getQuantity();
		// Create new book that has information held in global variables
		Book book = new Book(isbn, title, price, genre, quantity);
		// If ISBN exists, add one to the quantity.
		if (bookstore.containsKey(key)) {
			book.setQuantity(quantity++);
		// Otherwise add a new book at the index of the hashed ISBN.
		} else {
		bookstore.put(isbn, book);
		}
	}

	// remove() method alteration
	// quantity--
	// if quantity == 0, remove book
	public void remove(Integer isbn) {
		// If quantity = 0 then remove the book.
		if (bookstore.get(isbn).getQuantity() == 0) {
			bookstore.remove(isbn);
		// Find book at index, decrease quantity by 1
		} else {
			bookstore.get(isbn).setQuantity(quantity--);
		}
	}

	// donate(isbn, book)
	public void donate(Integer isbn, Book newBook) {
		bookstore.put(isbn, newBook);
	}

	// steal method
	// run clear() then return "we've been robbed of knowledge!")
	public void steal() {
		bookstore.clear();
		System.out.println("We've been robbed of knowledge!");
	}
}
