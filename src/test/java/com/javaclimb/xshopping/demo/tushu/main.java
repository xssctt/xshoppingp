package com.javaclimb.xshopping.demo.tushu;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {

    private  static List<Book> LIST;

    public static void main(String[] args) {

        System.out.println("正在读取信息，请稍后。。");
        load();

        Scanner scanner=new Scanner(System.in);
        while (true){
            System.out.println("========欢迎进入图书管理========");
            System.out.println("1,录入书籍信息");
            System.out.println("2,查询书籍信息");
            System.out.println("3，删除书籍信息");
            System.out.println("4，修改书籍信息");
            System.out.println("5,退出系统");
            System.out.println("=============================");

            switch (scanner.nextInt()){

                case 1:
                    insert(scanner);
                    break;
                case 2:
                    get();
                    break;
                case 3:
                    deleteb(scanner);
                    break;
                case 4:
                    update(scanner);
                    break;
                case 5:
                    System.out.println("正在保存信息,请稍后。。。");
                    save();
                    System.out.println("感谢使用，再见");
                    return;


            }

        }




    }

    private static void load(){
        File file=new File("data");
        if(file.exists()){
            try(ObjectInputStream in=new ObjectInputStream(new FileInputStream("data"))){
                LIST= (List<Book>) in.readObject();
            }catch (IOException | ClassNotFoundException e){
                e.printStackTrace();
            }
        }else {
            LIST=new ArrayList<>();
        }


    }



    private static void save(){
        try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("data"))){
            out.writeObject(LIST);
            out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    private static void insert(Scanner scanner){
        System.out.println("请输入书籍信息");
        System.out.println("书籍名称");
        scanner.nextLine();

        Book book=Book.bookbulder()
                .title(scanner.nextLine())
                .autor(scanner.nextLine())
                .prace(scanner.nextInt())
                .bulder();
        scanner.nextLine();
        LIST.add(book);
        System.out.println("书记添加成功"+book);
       // System.out.println(LIST.toString());
    }

    private static void get(){
        for (int i = 0; i < LIST.size(); i++) {
            System.out.println((i+1)+"."+LIST.get(i));
        }
    }

    private static void deleteb(Scanner scanner){

        System.out.println("请输入删除书籍的序号");
        System.out.println("输入0退出");
        scanner.nextLine();
        int index=scanner.nextInt();
        if (index==0) return;
        scanner.nextLine();
        while (index>LIST.size()||index<=0){
            System.out.println("没有此书籍");
            System.out.println("请输入删除书籍的序号");
            System.out.println("输入0退出");
            index=scanner.nextInt();
            scanner.nextLine();
        }

        LIST.remove(index-1);
        System.out.println("删除成功");
    }

    private static void update(Scanner scanner){
        System.out.println("请输入修改书籍的序号");
        scanner.nextLine();
        int index=scanner.nextInt();
        scanner.nextLine();
        while (index>LIST.size()||index<0){
            System.out.println("没有此书籍");
            System.out.println("请输入删除书籍的序号");
            index=scanner.nextInt();
            scanner.nextLine();
        }

        Book book=LIST.get(index-1);
        System.out.println("请输入书名");
        book.setTitle(scanner.nextLine());
        System.out.println("请输入作者");
        book.setAutor(scanner.nextLine());
        System.out.println("请输入价格");
        book.setPrace(scanner.nextInt());
        scanner.nextLine();
        System.out.println("修改成功");
    }


}
