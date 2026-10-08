package library;

import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Member> members = new ArrayList<>();
    private static int nextId = 1;

    public void addBook(String title, String author, int year) {
        Book newBook = new Book(title, author, year);
        this.books.add(newBook);
    }

    public ArrayList<Book> showBooks() {
        return new ArrayList<>(this.books);
    }

    public ArrayList<Book> searchBook(String search) {
        ArrayList<Book> searchList = new ArrayList<>();
        String searchText = search.toLowerCase();
        for (int i = 0; i < this.books.size(); i++) {
            if (this.books.get(i).getTitle().toLowerCase().contains(searchText)) {
                searchList.add(this.books.get(i));
            }
        }
        return new ArrayList<>(searchList);
    }

    public void editBook(int bookNumber, String title, String author, int year) {
        if (bookNumber >= 1 && bookNumber <= this.books.size()) {
            Book foundBook = this.books.get(bookNumber - 1);
            foundBook.setTitle(title);
            foundBook.setAuthor(author);
            foundBook.setYear(year);
            System.out.print(foundBook.toString());
            System.out.println("Book edit successfully!");
        } else {
            throw new LibraryException("An error occurred while editing the book! The number is invalid.");
        }
    }

    public void deleteBook(int bookNumber) {
        if (bookNumber >= 1 && bookNumber <= this.books.size()) {
            this.books.remove(bookNumber - 1);
            System.out.println("Book deleted successfully!");
        } else {
            throw new LibraryException("An error occurred while deleting the book! The number is invalid.");
        }
    }

    public void addMember(String name, String email) {
        int id = nextId++;
        Member member = new Member(id, name, email);
        this.members.add(member);
    }

    public ArrayList<Member> showMembers() {
        return new ArrayList<>(this.members);
    }

    public ArrayList<Member> searchMember(String search) {
        ArrayList<Member> searchList = new ArrayList<>();
        String searchText = search.toLowerCase();
        for (int i = 0; i < this.members.size(); i++) {
            if (this.members.get(i).getName().toLowerCase().contains(searchText) || this.members.get(i).getEmail().toLowerCase().contains(searchText)) {
                searchList.add(this.members.get(i));
            }
        }
        return new ArrayList<Member>(searchList);
    }

    public Member findMemberById(int id) {
        for (Member member : members) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }

    public void editMember(int id, String name, String email) {
        Member foundMember = this.findMemberById(id);
        if (foundMember != null) {
            foundMember.setName(name);
            foundMember.setEmail(email);
            System.out.println("Member edited successfully!");
        } else {
            throw new LibraryException("Member not found!");
        }
    }

    public void deleteMember(int id) {
        Member foundMember = this.findMemberById(id);
        if (foundMember != null) {
            if (foundMember.borrowedBooks().size() > 0) {
                throw new LibraryException("The member has borrowed a book!");
            }
            this.members.remove(foundMember);
            System.out.println("Member deleted successfully!");

        } else {
            throw new LibraryException("Member not found!");
        }
    }

    public void borrowBook(int bookNumber, int memberId) {
        if (bookNumber >= 1 && bookNumber <= this.books.size()) {
            Book foundBook = this.books.get(bookNumber - 1);
            if (foundBook.isAvailable()) {
                Member foundMember = this.findMemberById(memberId);
                if (foundMember == null) {
                    throw new LibraryException("Member not found!");
                }
                foundBook.setIsAvailable(false);
                foundMember.addBorrowedBooks(foundBook);
                System.out.println("Book borrowed successfully!");

            } else {
                throw new LibraryException("Book is already borrowed!");
            }
        } else {
            throw new LibraryException("Book not found!");
        }
    }

    public void returnBook(int bookNumber, int memberId) {

        if (bookNumber < 1 || bookNumber > this.books.size()) {
            throw new LibraryException("Book not found!");
        }
        Book foundBook = this.books.get(bookNumber - 1);
        if (foundBook.isAvailable()) {
            throw new LibraryException("Book is not borrowed!");
        }
        Member foundMember = this.findMemberById(memberId);
        if (foundMember == null) {
            throw new LibraryException("Member not found!");
        }
        if (!foundMember.hasBorrowedBook(foundBook)) {
            throw new LibraryException("This member didn't borrow this book!");
        }
        foundBook.setIsAvailable(true);
        foundMember.removeBorrowedBook(foundBook);
        System.out.println("Book returned successfully!");
    }
}
