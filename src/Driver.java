
/**
 * The program provides a method to handle books by reading data from a file,
 * organizing the books semantically valid and semantically invalid, and writing the
 * semantically invalid/valid books to a new respective file.
*/


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Driver {

	/**
	 * Reads data from a file, organizes the books semantically valid and semantically invalid, 
	 * and writes the semantically invalid books to a new file.
	 */
	public static void main(String[] args) {
		

		Scanner sc = null;
		PrintWriter pw = null;
		String s;
		String[] roughArr;
		ArrayList<Book> arrLst = new ArrayList<Book>(1);
		Book book = null;
		BookList bkLst = new BookList();
		BookList.Node iterator = null;
		boolean exists = false;
		
		try { sc = new Scanner ( new FileInputStream ("Books.txt"));
			while (sc.hasNextLine()) {
				s = sc.nextLine();
				roughArr = s.split(",");
				book = new Book(roughArr);
				
				//if semantically invalid
				if(Integer.parseInt(roughArr[5]) >= 2024) {
					exists = true;
					arrLst.add(book);
				}
				
				//semantically valid
				else {
					if (bkLst.getHead() == null) {
						//Should only happen once 
						BookList.Node ns = bkLst.new Node(book, null);
						//Trying to make circular
						ns.setNext(ns);
						bkLst.setHead(ns);
						//Creating an iterator starting at first Node
						iterator = ns;
						
					}
					else {
						BookList.Node n = bkLst.new Node(book, bkLst.getHead());
						//Make the previous Node point to the next 
						iterator.setNext(n);
						//Change the iterator so that it lies over the current Node
						iterator = n;
					}
				}
			}
			sc.close();
		}
		catch(FileNotFoundException e){
			System.out.println("File not found.");
		}

		//semantically invalid to file
		if (exists) {
			try { pw = new PrintWriter ( new FileOutputStream("YearErr.txt"));
			    for (int i = 0;  i < arrLst.size(); i++) {
			    	pw.println(arrLst.get(i));
			    }
			    pw.close();
			}
			catch(FileNotFoundException fnf) {
				System.out.println("File Not Found");
			}
		}
		
		bkLst.showContent();
		System.out.println("\n\n");
		BookList.menu(bkLst);
		
		System.out.println("Thank you for using the program.");
		
	}
		
	
	
}
	


