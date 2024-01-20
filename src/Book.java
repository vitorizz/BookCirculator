
/**
 * Represents a book object with its attributes such as title, author, type, isbn, genre and year.
 * Implements Cloneable interface for creating a clone of the object.
*/

public class Book implements Cloneable{
	/** The title of the book */
	private String title;
	/** The Author of the book */
	private String author;
	/** The type of the book */
	private double type;
	/** The SIBN of the book */
	private long isbn;
	/** The genre of the book */
	private String genre;
	/** The year of creation */
	private int year;
	/**
	Constructs a new Book object with default values for attributes.
	*/
	public Book() {
		
	}
	/**
	Constructs a new Book object with the given values for attributes.
	@param title The title of the book.
	@param author The author of the book.
	@param type The type of the book.
	@param isbn The ISBN of the book.
	@param genre The genre of the book.
	@param year The year the book was published.
	*/
	public Book(String title, String author, double type, long isbn, String genre, int year) {
		this.title = title;
		this.author = author;
		this.type = type;
		this.isbn = isbn;
		this.genre = genre;
		this.year = year;
	}
	/**
	Constructs a new Book object from an array of strings with the values for attributes.
	@param s An array of strings with the values for title, author, type, isbn, genre and year in that order.
	*/
	public Book(String[] s) {
		setTitle(s[0]);
		setAuthor(s[1]);
		setType(Double.parseDouble(s[2]));
		setIsbn(Long.parseLong(s[3]));
		setGenre(s[4]); 
		setYear(Integer.parseInt(s[5]));
	}
	/**
	Returns the title of the book.
	@return The title of the book.
	*/
	public String getTitle() {
		return title;
	}
	/**
	Sets the title of the book.
	@param title The new title of the book.
	*/
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	Returns the author of the book.
	@return The author of the book.
	*/
	public String getAuthor() {
		return author;
	}
	/**
	Sets the author of the book.
	@param author The new author of the book.
	*/
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	Returns the type of the book.
	@return The type of the book.
	*/
	public double getType() {
		return type;
	}
	/**
	Sets the type of the book.
	@param type The new type of the book.
	*/
	public void setType(double type) {
		this.type = type;
	}
	/**
	Returns the ISBN of the book.
	@return The ISBN of the book.
	*/
	public long getIsbn() {
		return isbn;
	}
	/**
	Sets the ISBN of the book.
	@param isbn The new ISBN of the book.
	*/
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}
	/**
	Returns the genre of the book.
	@return The genre of the book.
	*/
	public String getGenre() {
		return genre;
	}
	/**

	Sets the genre of this Book.
	@param genre the genre to set for this Book.
	*/
	public void setGenre(String genre) {
		this.genre = genre;
	}
	/**
	Returns the year of creation.
	@return The year of the creation.
	*/
	public int getYear() {
		return year;
	}
	/**

	Sets the year of creation.
	@param year the year to set for this Book.
	*/
	public void setYear(int year) {
		this.year = year;
	}
	/**
	Compares this Book with another Book object to see if they are equal.
	@param other the other Book object to compare with this Book.
	@return true if the other Book object is equal to this Book, false otherwise.
	*/
	public boolean equals(Book other) {
		if ((other.getClass() == null) || (getClass() == null) || (other.getClass() != getClass())) {
			return false;
		}
		else {
			return ((title.equals(other.title) && (author.equals(other.author) && (type == other.type)) && (isbn == other.isbn)
					&& (genre.equals(other.genre) && (year == other.year))));
		}
	}
	/**
	Returns a string representation of this Book object.
	@return a string representation of this Book object.
	*/
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", type=" + type + ", isbn=" + isbn + ", genre=" + genre
				+ ", year=" + year + "]";
	}
	/**

	Creates and returns a copy of this Book object.
	@return a copy of this Book object.
	*/
    public Object clone() {
        try {
	        Book clone = (Book) super.clone();
	        //Create new instances for Strings since they are not primitive 
	        clone.title = new String(this.title);
	        clone.author = new String(this.author);
	        clone.genre = new String(this.genre);
	        return clone;
	    } 
        catch (CloneNotSupportedException e) {
	        throw new AssertionError(); // Should never happen
	        }
	    }

}
