package library;

public class Book {
    private String title;
    private String author;
    private int year;
    private  boolean isAvailable;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.isAvailable =true;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getYear() {
        return this.year;
    }
    public void setYear(int year) {
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
        return "Title: " + this.title + " | Author: " + this.author + " | Year: " + this.year + " | Status: " + (this.isAvailable ? "Available": "Borrowed");
    }
}
