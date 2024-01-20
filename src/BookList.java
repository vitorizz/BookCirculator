
/**
 * The BookList class represents a circular linked list of Book objects.
 * The list maintains a reference to the head Node and uses the Node class
 * to create a new Node containing a Book object with a reference to the next Node.
 * The BookList class provides methods for adding a Book object to the start of the list,
 * storing books from a specific year in a text file, inserting a Book object before or
 * between two specified Book objects, displaying the contents of the list, deleting consecutive
 * repeated records, and extracting a new BookList containing books with a specific author.
*/

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BookList {
	
	/**
	 * The Node class represents a node in the linked list.
	 * It contains a Book object and a reference to the next Node.
	 */
	public class Node {
		private Book b;
		private Node next;
		/**
		 * Constructs a new Node with the specified Book object and a reference to the next Node.
		 * @param book the Book object
		 * @param n the reference to the next Node
		 */
		public Node(Book book, Node n) {
			b = book;
			next = n;
		}
		/**
		 * Gets the Book object stored in the Node.
		 * @return the Book object
		 */
		public Book getB() {
			return b;
		}
		/**
		 * Sets the Book object stored in the Node.
		 * @param b the Book object to set
		 */
		public void setB(Book b) {
			this.b = b;
		}
		/**
		 * Gets the reference to the next Node.
		 * @return the reference to the next Node
		 */
		public Node getNext() {
			return next;
		}
		/**
		 * Sets the reference to the next Node.
		 * @param next the reference to the next Node to set
		 */
		public void setNext(Node next) {
			this.next = next;
		}
	}
	
	private Node head;
	
	/**
	 * Constructs a new empty BookList with a null head reference.
	 */
	public BookList() {
		head = null;
	}
	
	/**
	 * Gets the reference to the head Node of the BookList.
	 * @return the reference to the head Node
	 */
	public Node getHead() {
		return head;
	}
	
	/**
	 * Sets the reference to the head Node of the BookList.
	 * @param n the reference to the head Node to set
	 */
	public void setHead(Node n) {
		head = n;
	}
	
	/**
	 * Adds the specified Book object to the start of the BookList.
	 * If the list is empty, prints "there is nothing".
	 * @param b the Book object to add to the list
	 */
	public void addToStart(Book b) {
		if (head == null) {
			System.out.println("there is nothing");
		}
		else {
			Node t = head;
			while (t.next != head) {
				t = t.next;
			}
			Node n = new Node(b, head);
			t.next = n;
			head = n;
		}
		
	}
	
	/**
	 * Stores the Book objects from the specified year in a text file named "yr.txt".
	 * If the list is empty, prints "there is nothing".
	 * @param yr the year to store the Book objects from
	 */
	public void storeRecordsByYear(int yr) {
		PrintWriter pw = null;
		String allBooks = "";
		boolean yrFound = false;
		int counter = 0;
		
		if (head == null) {
			System.out.println("There is nothing");
		}
		else {
			Node t = head;
			while (true) {
				if (t == head) {
					counter++;
					if (counter == 2) {
						break;
					}
				}
				if (t.b.getYear() == yr) {
					yrFound = true;
					allBooks += (t.b +"\n");
				}
				t = t.next;
			}
		}
		if (yrFound) {
			try { pw = new PrintWriter ( new FileOutputStream("yr.txt"));
				pw.println(allBooks);
				pw.close();
			}
			catch(FileNotFoundException fnf) {
				System.out.println("File not found");
			}
		}
	}
	
	/**
	 * Inserts a new book before the book with the specified ISBN.
	 * If the ISBN is not found, the book is not inserted and the method returns false.
	 *
	 * @param isbn The ISBN of the book to insert before.
	 * @param b The book to be inserted.
	 * @return true if the book was inserted, false otherwise.
	 */
	public boolean insertBefore(long isbn, Book b) {
		if (head == null) {
			return false;
		}
		else {
			if (head.b.getIsbn() == isbn) {
				addToStart(b);
				return true;
			}
			Node t = head;
			while (t.next != head) {
				if (t.next.b.getIsbn() == isbn) {
					t.next =  new Node(b, t.next);
					return true;
				}
				t = t.next;
			}
			return false;
		}
		
	}
	
	/**
	 * Inserts a new book between two books with the specified ISBNs.
	 * If either of the ISBNs is not found, the book is not inserted and the method returns false.
	 *
	 * @param isbn1 The ISBN of the first book.
	 * @param isbn2 The ISBN of the second book.
	 * @param b The book to be inserted.
	 * @return true if the book was inserted, false otherwise.
	 */
	public boolean insertBetween(long isbn1, long isbn2, Book b) {
		if (head == null) {
			return false;
		}
		else {
			Node t = head;
			while (t.next != head) {
				if ((t.b.getIsbn() == isbn1) && (t.next.b.getIsbn() == isbn2)) {
					t.next =  new Node(b, t.next);
					return true;
				}
				t = t.next;
			}
			return false;
		}
	}
	
	/**
	 * Prints the contents of the BookList to the console.
	 * If the BookList is empty, prints a message indicating that there is nothing to show.
	 */
	public void showContent() {
		if (head == null) {
			System.out.println("there is nothing");
		}
		else {
			Node t = head.next;
			System.out.println(head.b);
			while (t != head) {
				System.out.println(t.b + " ==>");
				t = t.next;
			}
			System.out.println("===> Head");
		}
	}
	
	/**
	 * Deletes all consecutive repeated records from the BookList.
	 * If there are no such records, returns false.
	 *
	 * @return true if any records were deleted, false otherwise.
	 */
	public boolean delConsecutiveRepeatedRecords() {
		if (head == null) {
			return false;
		}
		else {
			int counter = 0;
			if (head == null) {
				return false;
			}
			else {
				Node t = head;
				while (true) {
					if (t == head) {
						counter++;
						if (counter == 2) {
							break;
						}
					}
					Node repeat = t;
					while(t.b.equals(repeat.next.b)) {
						repeat = repeat.next;
						if(repeat == head) {
							head = repeat.next;
						}
					}
					t.next = repeat.next;
					t = t.next;
				}
			}
		}
		return true;
	}
	
	/**
	 * Extracts all books from the BookList whose author matches the specified author.
	 * Returns a new BookList containing the extracted books.
	 * If there are no books by the specified author, returns an empty BookList.
	 *
	 * @param aut The name of the author.
	 * @return A new BookList containing the extracted books.
	 */
	public BookList extractAuthor(String aut) {
		
		Node t = head.next;
		Node head2 = null;
		Node iterator = null;
		boolean isH = false;
		int counter = 0;
		
		if (head.b.getAuthor().equals(aut)) {
			isH = true;
			Book bk = (Book)head.getB().clone();
			Node n = new Node(bk, null);
			n.next = n;
		    head2 = n;
		    iterator = n;
		}
		while (t != head) {
			if (!isH) {
				if (t.b.getAuthor().equals(aut)) {
					isH = true;
					Book bk = (Book)t.getB().clone();
					Node n = new Node(bk, null);
					n.next = n;
					head2 = n;
					iterator = n;
				}
			}
			else {
				if (t.b.getAuthor().equals(aut)) {
					Book bk = (Book)t.getB().clone();
					Node n2 = new Node(bk, null);
					n2.next = head2;
					iterator.next = n2;
					iterator = n2;
				}
			}
			t = t.next;
		}
		 // create new BookList and attach extracted nodes
	    BookList newList = new BookList();
	    if (head2 != null) {
	        newList.head = head2;
	        iterator = head2;
	        t = head2.next;
	        while (t != head2) {
	            Book bk = (Book)t.getB().clone();
	            Node n = new Node(bk, null);
	            iterator.next = n;
	            iterator = n;
	            t = t.next;
	        }
	        iterator.next = head2;
	    }
	    return newList;
	}
	
	/**
	 * Swaps the positions of two books with the specified ISBNs.
	 * If either of the ISBNs is not found, returns false.
	 *
	 * @param isbn1 The ISBN of the first book.
	 * @param isbn2 The ISBN of the second book.
	 * @return true if the books were swapped, false otherwise.
	 */
	public boolean swap(long isbn1, long isbn2) {

		Node t = head.next;
		boolean firstFND = false;
		Book temp = null;
		Node pointer = null;
		//check the head
		if ((head.b.getIsbn() == isbn1) || (head.b.getIsbn() == isbn2)) {
			firstFND = true;
			pointer = head;
			temp = (Book)head.b.clone();
		}
		//check everything else
		while (t != head) {
			if ((t.b.getIsbn() == isbn1) || (t.b.getIsbn() == isbn2)) {
				if (firstFND) {
					pointer.setB(t.b);
					t.setB(temp);
					break;
				}
				firstFND = true;
				pointer = t;
				temp = (Book)t.b.clone();
			}
			t = t.next;
		}
		return (temp != null);
	}
	
	/**
	 * Commits the changes made to the BookList to a file named "Update_Books.txt".
	 * If the BookList is empty, prints a message indicating that there is nothing to commit.
	 */
	public void commit() {
		PrintWriter pw = null;
		try { pw = new PrintWriter ( new FileOutputStream ("Update_Books.txt"));
		
		if (head == null) {
			System.out.println("There is nothing in the BookList");
		}
		else {
			Node t = head.next;
			pw.println(head.b.toString());
			while (t != head) {
				pw.println(t.b.toString() + " ==>");
				t = t.next;
			}
			pw.println("===> Head");
		}
		pw.close();
	}
		catch(FileNotFoundException fnfe) {
			System.out.println("FileNotFound");
		}
	
	}
	
	/**
	 * Menu that allows user to interact with the program
	 */
	public static void menu(BookList bList) {
		Scanner sc = new Scanner (System.in);
		boolean endMenu = false;
		int choice = 0;
		
		while (!endMenu) {
			System.out.println("\n\nEnter a choice from the following menu: \n\n"
					+ "1) Give me a year # and I would extract all records "
					+ "of that year and store them in a file for that year;\n"
					+ "2) Ask me to delete all consecutive repeated records;\n"
					+ "3) Give me an author name and I will create a new list with the records of this author and display them;\n"
					+ "4) Give me an ISBN number and a Book object, and I will insert Node with the book before the record with this ISBN;\n"
					+ "5) Give me 2 ISBN numbers and a Book object, and I will insert a Node between them, if I find them!\n"
					+ "6) Give me 2 ISBN numbers and I will swap them in the list for rearrangement of records; of course if they exist!\n"
					+ "7) Tell me to COMMIT! Your command is my wish. I will commit your list to a file called Updated_Books;\n"
					+ "8) Tell me to STOP TALKING. Remember, if you do not commit, I will not!\n"
					+ "9) Terminate Program. \n\nChoice: ");
			
			try { choice = sc.nextInt();
			if ((choice < 0) || (choice > 9)) {
				System.out.println("Enter only an integer from 1-9 as an option please.");
				continue;
			}
			if (choice == 1) {
				System.out.println("Enter the year of your choice: ");
				int yr = sc.nextInt();
				bList.storeRecordsByYear(yr);
				System.out.println("If there are such files with the given year the file yr.txt will contain them.");
				System.out.println("\nNow Displaying BookList contents: \n\n");
				bList.showContent();
			}
			if (choice == 2) {
				bList.delConsecutiveRepeatedRecords();
				System.out.println("\nNow Displaying BookList contents: \n\n");
				bList.showContent();
			}
			if (choice == 3) {
				String consume = sc.nextLine(); //eat up the empty line
				System.out.println("Enter the author of your choice: ");
				String aut = sc.nextLine();
				BookList Bist = new BookList(); 
				Bist = bList.extractAuthor(aut);
				System.out.println("\nNow Displaying BookList contents for " + aut + ": \n\n");
				Bist.showContent();
			}
			if (choice == 4) {
				String consume = sc.nextLine(); //eat up the empty line
				System.out.println("Enter the book of your choice: ");
				String bRough = sc.nextLine();
				String[] bClean = bRough.split(",");
				Book book = new Book(bClean);
				System.out.println("Enter the ISBN of your choice: ");
				Long isbn1 = sc.nextLong();
				bList.insertBefore(isbn1, book);
				System.out.println("\nNow Displaying BookList contents: \n\n");
				bList.showContent();
			}
			if (choice == 5) {
				String consume = sc.nextLine(); //eat up the empty line
				System.out.println("Enter the book of your choice: ");
				String bRough = sc.nextLine();
				String[] bClean = bRough.split(",");
				Book book = new Book(bClean);
				System.out.println("Enter the first ISBN: ");
				Long isbn1 = sc.nextLong();
				System.out.println("Enter the second ISBN: ");
				Long isbn2 = sc.nextLong();
				bList.insertBetween(isbn1, isbn2, book);
				System.out.println("\nNow Displaying BookList contents: \n\n");
				bList.showContent();
			}
			if (choice == 6) {
				System.out.println("Enter the first ISBN: ");
				Long isbn1 = sc.nextLong();
				System.out.println("Enter the second ISBN: ");
				Long isbn2 = sc.nextLong();
				bList.swap(isbn1, isbn2);
				System.out.println("\nNow Displaying BookList contents: \n\n");
				bList.showContent();
			}
			if (choice == 7) {
				bList.commit();
				System.out.println("\nNow Displaying BookList contents: \n\n");
				bList.showContent();
			}
			if (choice == 8) {
				System.out.println("Fine, I will stop talking. If you want to quit the menu select 9 instead.");
				System.out.println("\nNow Displaying BookList contents: \n\n");
				bList.showContent();
			}
			if (choice == 9) {
				endMenu = true;
			}
			}
			catch(InputMismatchException e) {
				System.out.println("You are only supposed to enter an integer from 1-9, I will restart the "
						+ "menu for you. Remember 1 to 9.");
				menu(bList);
			}
			
		}
	}
	


}


