package com.AADHA.Starters.DBMSProject.model;

public class books {
    private int book_id,ISBN,SRN_own,staff_own;
    private String name,author,genre,DOP,return_date;

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public int getSRN_own() {
        return SRN_own;
    }

    public void setSRN_own(int SRN_own) {
        this.SRN_own = SRN_own;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDOP() {
        return DOP;
    }

    public void setDOP(String DOP) {
        this.DOP = DOP;
    }

    public int getStaff_own() {
        return staff_own;
    }

    public void setStaff_own(int staff_own) {
        this.staff_own = staff_own;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }
}
