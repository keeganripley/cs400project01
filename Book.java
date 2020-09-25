
/**
 * This class represents a book available in the bookstore
 * 
 * @author elaine
 *
 */
public class Book {
  int isbn; // ISBN number but without first 3 numbers (978); therefore only stores last 10
                    // digits of ISBN # (starts with 1 or 0)
  private String title; // book title
  private int price; // book price
  private String genre; // genre of book
  private int quantity; // number of books currently available

  /**
   * Creates instance of book
   * 
   * @param isbn
   * @param title
   * @param price
   * @param genre
   * @param quantity
   */
  public Book(int isbn, String title, int price, String genre, int quantity) {
    this.isbn = isbn;
    this.title = title;
    this.price = price;
    this.genre = genre;
    this.quantity = quantity;
  }

  /**
   * @return the quantity available
   */
  public int getQuantity() {
    return quantity;
  }

  /**
   * @param quantity the quantity to set
   */
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  /**
   * @return the isbn number
   */
  public int getIsbn() {
    return isbn;
  }

  /**
   * @return the title of book
   */
  public String getTitle() {
    return title;
  }

  /**
   * @return the price of book
   */
  public int getPrice() {
    return price;
  }

  /**
   * @return the genre of book
   */
  public String getGenre() {
    return genre;
  }


}
