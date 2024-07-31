public class Main {
    public static void main(String[] args) {
        LibraryManagementSystem lms = new LibraryManagementSystem();

        lms.addBook(new Book(1, "The Great Gatsby", "F. Scott Fitzgerald"));
        lms.addBook(new Book(2, "To Kill a Mockingbird", "Harper Lee"));
        lms.addBook(new Book(3, "1984", "George Orwell"));
        lms.addBook(new Book(4, "Pride and Prejudice", "Jane Austen"));

        System.out.println("Searching for '1984' using linear search:");
        Book foundBook = lms.linearSearchByTitle("1984");
        if (foundBook != null) {
            System.out.println(foundBook);
        } else {
            System.out.println("Book not found.");
        }

        lms.sortBooksByTitle();

        System.out.println("\nSearching for '1984' using binary search:");
        foundBook = lms.binarySearchByTitle("1984");
        if (foundBook != null) {
            System.out.println(foundBook);
        } else {
            System.out.println("Book not found.");
        }
    }
}
