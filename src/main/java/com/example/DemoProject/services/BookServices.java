public class BookServices {
    private final bookrepository bookRepository;

    public BookServices(bookrepository bookRepository) {
        this.bookRepository = bookRepository;
    }

   public Book addBook(Book book) {
        return bookRepository.save(book);
    }
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(()->new runtimeException("Book not found with id: " + id));
    }
}

