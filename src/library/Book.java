package library;
import java.time.Year;

public class Book {
    private final long id;
    private String title;
    private String author;
    private int year;
    private boolean isAvailable;

    public Book(String title, String author, int year) {
        if (title == null || title.isBlank()) {
            throw new LibraryException("Title can not be blank");
        }
        if (author == null || author.isBlank()) {
            throw new LibraryException("Author can not be blank");
        }
        if (year < 1 || year > Year.now().getValue()) {
            throw new LibraryException("Year is not valid");
        }
        this.id = IdGenerator.generateBookId();
        this.title = title;
        this.author = author;
        this.year = year;
        this.isAvailable = true;
    }

    public long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new LibraryException("Title can not be blank");
        }
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        if (author == null || author.isBlank()) {
            throw new LibraryException("Author can not be blank");
        }
        this.author = author;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        if (year < 1 || year > Year.now().getValue()) {
            throw new LibraryException("Year is not valid");
        }
        this.year = year;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "ID : " + this.id + " | Title: " + this.title + " | Author: " + this.author + " | Year: " + this.year + " | Status: " + (this.isAvailable ? "Available" : "Borrowed");
    }
}



