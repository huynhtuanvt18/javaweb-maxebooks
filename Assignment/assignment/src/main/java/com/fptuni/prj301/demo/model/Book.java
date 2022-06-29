/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
=======
/**
 *
 * @author DUNGHUYNH
>>>>>>> addcca5fb4cd6a5949ae767f9041ca330398afb7
 */
package com.fptuni.prj301.demo.model;


import java.util.Date;

/**
 *
 * @author truon
 */
public class Book {
    private String title;
    private String author;
    private String category;
    private Date uploadDate;
    private Long bookID;
    private Boolean check;

    public Book(String name, String author, String category,Date uploadDate,Long bookID, Boolean check) {
        this.title = name;
        this.author = author;
        this.category = category;
        this.uploadDate = uploadDate;
        this.bookID= bookID;
        this.check= check;
    }

    public Book(String title, String author, String category, Date uploadDate, Long bookID) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.uploadDate = uploadDate;
        this.bookID = bookID;
    }

    

    public Book() {
        title=null;
        author = null;
        category= null;
        uploadDate= null;
        bookID= null;
        check= false;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    } 
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
}

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Long getBookID() {
        return bookID;
    }

    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }
    

}
