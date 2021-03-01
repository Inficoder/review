package com.buaa.review.designPattern.iterator;

import java.util.ArrayList;
import java.util.List;

public class BookShelf implements Container{

    private List<Book> books;

    public BookShelf() {
        this.books = new ArrayList<Book>();
    }

    public Book getBookAt(int index) {
        return books.get(index);
    }

    public void appendBook(Book book) {
        books.add(book);
    }

    public int getLength() {
        return books.size();
    }

    public Iterator getIterator() {
        return null;
    }



}
