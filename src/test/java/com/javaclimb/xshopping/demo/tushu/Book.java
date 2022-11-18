package com.javaclimb.xshopping.demo.tushu;

import java.io.Serializable;

public class Book implements Serializable {
    private String title;
    private String autor;
    private int prace;

    public Book(String title, String autor, int prace) {
        this.title = title;
        this.autor = autor;
        this.prace = prace;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setPrace(int prace) {
        this.prace = prace;
    }

    public static BookBulder bookbulder(){
        return new BookBulder();
    }

    @Override
    public String toString() {
        return "《"+title+"》"+"作者"+autor+"价格"+prace+"￥";
    }

    public static class BookBulder{
        private String title;
        private String autor;
        private int prace;

        private BookBulder(){}

        public BookBulder title(String title){
            System.out.println("书记作者");
            this.title=title;
            return this;
        }

        public BookBulder autor(String autor){
            System.out.println("书记价格");
            this.autor=autor;
            return this;
        }

        public BookBulder prace(int prace){
            this.prace=prace;
            return this;
        }

        public Book bulder(){
            return new Book(title,autor,prace);
        }


    }



}
