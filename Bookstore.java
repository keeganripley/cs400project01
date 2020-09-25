// --== CS400 File Header Information ==--
// Name: Keegan Ripley
// Email: kjripley@wisc.edu
// Team: EE
// Role: Backend Developer
// TA: Keren
// Lecturer: Florian
// Notes to Grader: <optional extra notes>
public class Bookstore<KeyType, ValueType> {
	// KeyIsbn is last 10 digits of ISBN of book
	// ValueBook is a book object that has 5 parameters:
	// isbn, title, price, genre, quantity
	// To retrieve, get method for desired stored value
	HashTableMap<Integer, Book> bookstore = new HashTableMap<Integer, Book>(20);
	Book newBook = new Book(isbn, title, price, genre, quantity);
	

	// put() method alteration
	// if key exists, then quantity++
	// if key doesn't exists, put book into hash table
	public void put(Integer isbn, Book newBook) {
		if (bookstore.containsKey(isbn)) {
			newBook.setQuantity(quantity++);
		} else {
			Book newBookInfo = new Book(isbn, null, isbn, null, isbn);
			newBook = newBookInfo;
		}
	}
	
	// remove() method alteration
	// quantity--
	// if quantity == 0, remove book
	public void remove(Integer isbn) {
		int index = bookstore.hash(isbn);
		// Find book at index, decrease quantity by 1
		if (newBook.getQuantity() == 0) {
			bookstore.remove(isbn);
		} else {
			newBook.setQuantity(quantity--);
		}
		
	}

	// int buy() method
	// if containsKey == true
	// then return getPrice of book
	public int buy(Integer isbn) {
		int price = newBook.getPrice();
		bookstore.remove(isbn);
		return price;
	}
	
	// donate(isbn, book)
	// would be same as put method no?
	public void donate(Integer isbn, Book newBook) {
		bookstore.put(isbn, newBook);
	}
	
	// steal method
	// run clear() then return "we've been robbed of knowledge!")
	public void steal() {
		bookstore.clear();
		System.out.println("We've been robbed of knowledge!");
	}
	

	// Possible implementations
	// Create new hash table class--won't work for other members hash table classes
}
