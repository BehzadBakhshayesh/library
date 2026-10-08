package library;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Member {
    private ArrayList<Book> borrowedBooks = new ArrayList<>();
    private int id;
    private String name;
    private String email;

    public Member(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
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
