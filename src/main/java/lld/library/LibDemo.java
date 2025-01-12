package lld.library;

public class LibDemo {
    /*
    The library management system should allow librarians to manage books, members, and borrowing activities.
    The system should support adding, updating, and removing books from the library catalog.
    Each book should have details such as title, author, ISBN, publication year, and availability status.
    The system should allow members to borrow and return books.
    Each member should have details such as name, member ID, contact information, and borrowing history.
    The system should enforce borrowing rules, such as a maximum number of books that can be borrowed at a time and loan duration.
    The system should handle concurrent access to the library catalog and member records.
    The system should be extensible to accommodate future enhancements and new features.
     */

    /*
        Member
            id
            phno

        Book - Title, Author, ISBN, publication year.

        CatalogManager
            Map<BookTitle, Deque<Book>> availableCopies
            Map<BookTitle, Deque<Book>> borrowedCopies
            sync addBook(Book b)
            sync removeBook(Book b)
            sync updateBookCopiesOnBorrow(Book b)
            sync updateBookCopiesOnReturn(Book b)
            boolean isBookAvailable(String BookTitle)

        MembershipService
            addMember(Member m)
            Member getMember(String MemberId)

        LibrarySystem - CatalogManager, MembershipService, BorrowConfiguration, PenaltyCalculator
               Map<MemberId, Set<BorrowedBook>>
               BorrowedBook borrowBook(Book b, String MemberID, Date currentDate)
               int returnBook(BorrowedBook bb, Date currentDate)

        BorrowConfiguration
           booksLimit
           timeLimit
           penaltyPerDayDelay

        PenaltyCalculator
            BorrowConfiguration
            calculate(BorrowedBook bb, Date currentDate)

        BorrowedBook
            borrowDate
            Book
            Member
     */
}
