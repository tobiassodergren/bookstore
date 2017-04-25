package org.sodergren.bookstore;

import org.sodergren.model.BookOrderStatus;
import org.sodergren.model.entity.Book;
import org.sodergren.model.entity.BookList;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.toList;

public class BookStore implements BookList {

    private final Map<String, UUID> searchIndex = new ConcurrentHashMap<>();
    private final Map<UUID, BookStock> store = new HashMap<>();

    /**
     * Find books containing instances of <pre>searchString</pre> in either author or title
     *
     * @param searchString A partial name of either author or book title
     * @return A array of {@link Book}s that matched the <pre>searchString</pre>.
     */
    @Override
    public Book[] list(String searchString) {
        return searchIndex.entrySet().stream()
                .filter((entry) -> searchString.isEmpty() || entry.getKey().contains(searchString))
                .map((entry) -> store.get(entry.getValue()).getBook())
                .toArray(Book[]::new);
    }

    /**
     * Add books to the store. Books may already exist in store, in which case the quantity raised with the specified value.
     * <p>
     * This method is synchronized because the {@link #buy(Book...)} method also updated the quantity.
     *
     * @param book     The book to be added
     * @param quantity The quantity to add for the specified book
     * @return True if book did not previously exist, false if book already exist in stock and the total quantity is updated.
     */
    @Override
    public synchronized boolean add(Book book, int quantity) {
        return addBooksToStore(book, quantity);
    }

    @Override
    public synchronized int[] buy(Book... books) {

        final List<BookOrderStatus> result = new ArrayList<>(books.length);

        for (Book book : books) {
            UUID id = book.getId();

            if (!store.containsKey(id)) {
                result.add(BookOrderStatus.DOES_NOT_EXIST);
                continue;
            }

            boolean didRemoveBooks = removeBooksFromStore(book, 1);

            if (didRemoveBooks) {
                result.add(BookOrderStatus.OK);
            } else {
                result.add(BookOrderStatus.NOT_IN_STOCK);
            }
        }

        return statusListToIntArray(result);
    }

    @Override
    public synchronized Book getById(UUID uuid) throws NotFoundException {
        if (store.containsKey(uuid)) {
            return store.get(uuid).getBook();
        } else {
            throw new NotFoundException("Book", uuid);
        }
    }

    public Collection<BookStock> getStock() {
        return store.values();
    }

    private boolean addBooksToStore(Book book, int quantity) {
        final UUID id = book.getId();

        if (store.containsKey(id)) {
            store.put(id, store.get(id).add(quantity));
            return false;
        }

        store.put(id, new BookStock(id, book, quantity));
        searchIndex.put(book.getTitle() + ":" + book.getAuthor(), id);
        return true;
    }

    private boolean removeBooksFromStore(Book book, int quantity) {
        final UUID id = book.getId();

        final BookStock stock = store.get(id);

        if (stock.getQuantity() >= quantity) {
            store.put(id, stock.subtract(quantity));
            if (store.get(id).getQuantity() == 0) {
                removeBookFromIndex(id);
            }
            return true;
        } else {
            return false;
        }
    }

    private void removeBookFromIndex(UUID id) {
        final List<String> toRemove = searchIndex.entrySet().stream()
                .filter(entry -> entry.getValue().equals(id))
                .map(Map.Entry::getKey)
                .collect(toList());

        toRemove.forEach(key -> searchIndex.remove(key));
    }

    private int[] statusListToIntArray(List<BookOrderStatus> result) {
        int[] intResult = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            intResult[i] = result.get(i).getStatusCode();
        }

        return intResult;
    }

    int quantityFor(Book book) {
        if (store.containsKey(book.getId())) {
            return store.get(book.getId()).getQuantity();
        }
        return 0;
    }

}
