package library;

import java.util.ArrayList;
import java.time.Year;

public class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Member> members = new ArrayList<>();

    public void addBook(String title, String author, int year) {
        Book newBook = new Book(title, author, year);
        this.books.add(newBook);
        FileManager fileManager = new FileManager();
        System.out.println(fileManager.loadText("books.txt"));
    }

    public Book findBookById(long id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
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

    public void editBook(long bookId, String title, String author, int year) {
        Book foundBook = this.findBookById(bookId);
        if (foundBook == null) {
            throw new LibraryException("Book not found! Invalid ID.");
        }
        if (title == null || title.isBlank()) {
            throw new LibraryException("Title can not be blank");
        }
        if (author == null || author.isBlank()) {
            throw new LibraryException("Author can not be blank");
        }
        if (year < 1 || year > Year.now().getValue()) {
            throw new LibraryException("Year is not valid");
        }

        foundBook.setTitle(title);
        foundBook.setAuthor(author);
        foundBook.setYear(year);
        System.out.println(foundBook);
        System.out.println("Book edit successfully!");

    }

    public void deleteBook(long bookId) {
        Book foundBook = this.findBookById(bookId);
        if (foundBook == null) {
            throw new LibraryException("An error occurred while deleting the book! The number is invalid.");
        }
        if (!foundBook.isAvailable()) {
            throw new LibraryException("Book is already borrowed!");
        }
        this.books.remove(foundBook);
        System.out.println("Book deleted successfully!");

    }

    public void addMember(String name, String email) {
        Member member = new Member(name, email);
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

    public Member findMemberById(long id) {
        for (Member member : members) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }

    public void editMember(long id, String name, String email) {
        Member foundMember = this.findMemberById(id);
        if (foundMember == null) {
            throw new LibraryException("Member not found!");
        }
        if (name == null || name.isBlank()) {
            throw new LibraryException("Name can not be blank");
        }
        if (!Member.isValidEmail(email)) {
            throw new LibraryException("Email is not valid");
        }
        foundMember.setName(name);
        foundMember.setEmail(email);
        System.out.println("Member edited successfully!");

    }

    public void deleteMember(long id) {
        Member foundMember = this.findMemberById(id);
        if (foundMember == null) {
            throw new LibraryException("Member not found!");
        }
        if (!foundMember.borrowedBooks().isEmpty()) {
            throw new LibraryException("The member has borrowed a book!");
        }
        this.members.remove(foundMember);
        System.out.println("Member deleted successfully!");
    }

    public void borrowBook(long bookId, long memberId) {
        Book foundBook = this.findBookById(bookId);
        if (foundBook == null) {
            throw new LibraryException("Book not found!");
        }
        if (!foundBook.isAvailable()) {
            throw new LibraryException("Book is already borrowed!");
        }

        Member foundMember = this.findMemberById(memberId);
        if (foundMember == null) {
            throw new LibraryException("Member not found!");
        }
        foundBook.setIsAvailable(false);
        foundMember.addBorrowedBooks(foundBook);
        System.out.println("Book borrowed successfully!");
    }

    public void returnBook(long bookId, long memberId) {
        Book foundBook = this.findBookById(bookId);
        if (foundBook == null) {
            throw new LibraryException("Book not found!");
        }
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

    public void save() {
        FileManager fileManager = new FileManager();
        StringBuilder booksText = new StringBuilder();
        StringBuilder membersText = new StringBuilder();
        for (Book b : books) {
            booksText.append(b.toFileLine()).append("\n");
        }
        for (Member m : members) {
            membersText.append(m.toFileLine()).append("\n");
        }
        fileManager.saveText("books.txt", booksText.toString());
        fileManager.saveText("members.txt", membersText.toString());
    }

    public void load() {
        FileManager fileManager = new FileManager();
        // 1. اگر books.txt وجود دارد:
        //      books.clear()
        //      متن را بخوان، به خطوط بشکن، خطوط خالی را رد کن
        //      برای هر خط: Book.fromFileLine(line) → books.add(...)
        //      maxBookId را نگه دار → IdGenerator.setBookCounter(maxBookId + 1)

        // 2. اگر members.txt وجود دارد:
        //      members.clear()
        //      برای هر خط:
        //          Member m = Member.fromFileLine(line)
        //          ArrayList<Long> ids = Member.borrowedIdsFromLine(line)
        //          برای هر id: Book b = findBookById(id) ... m.addBorrowedBooks(b)
        //          members.add(m)
        //      maxMemberId را نگه دار → IdGenerator.setMemberCounter(maxMemberId + 1)
    }


}
