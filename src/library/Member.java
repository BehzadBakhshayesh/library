package library;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Member {
    private ArrayList<Book> borrowedBooks = new ArrayList<>();
    private final long id;
    private String name;
    private String email;

    public Member(String name, String email) {
        if (name == null || name.isBlank()) {
            throw new LibraryException("Name can not be blank");
        }
        if (!this.isValidEmail(email)) {
            throw new LibraryException("Email is not valid");
        }
        this.id = IdGenerator.generatMemberId();
        this.name = name;
        this.email = email;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new LibraryException("Name can not be blank");
        }
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        if (!this.isValidEmail(email)) {
            throw new LibraryException("Email is not valid");
        }
        this.email = email;
    }
    public static boolean isValidEmail(String email) {
        return email != null
                && !email.isBlank()
                && email.contains("@")
                && email.contains(".");
    }

    public ArrayList<Book> borrowedBooks() {
        return new ArrayList<>(this.borrowedBooks);
    }

    public void addBorrowedBooks(Book book) {
        this.borrowedBooks.add(book);
    }

    public void removeBorrowedBook(Book book) {
        this.borrowedBooks.remove(book);
    }

    public boolean hasBorrowedBook(Book book) {
        return borrowedBooks.contains(book);
    }

    @Override
    public String toString() {
        return "ID: " + this.id + " | Name: " + this.name + " | Email: " + this.email + " | Borrowed Books: " + borrowedBooks.stream()
                .map(book -> book.getTitle())
                .collect(Collectors.joining(", "));
    }
}
