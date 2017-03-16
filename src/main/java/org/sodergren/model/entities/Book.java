package org.sodergren.model.entities;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.UUID;

public class Book {
    private final UUID id;
    private final String title;
    private final String author;
    private final BigDecimal price;

    public Book(String title, String author, BigDecimal price) {
        if (title == null || author == null || price == null) {
            throw new IllegalArgumentException("Input data must not be null");
        }

        this.title = title;
        this.author = author;
        this.price = price;
        this.id = UUID.nameUUIDFromBytes(constructByteArrayFromFields());

    }

    private byte[] constructByteArrayFromFields() {
        return (title + ':' + author + ':' + price.toPlainString()).getBytes(Charset.forName("UTF-8"));
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return id.equals(book.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }
}
