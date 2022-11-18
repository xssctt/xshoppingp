package com.javaclimb.xshopping.demo;

import com.mysql.cj.protocol.FullReadInputStream;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class file {
    public static void main(String[] args) {
       try(BufferedInputStream stream=new BufferedInputStream(new FileInputStream("src/test/java/com/javaclimb/xshopping/demo/file.txt"))){

           stream.mark(0);
           System.out.print((char) stream.read());
           System.out.print((char) stream.read());
           System.out.print((char) stream.read());
           System.out.println((char) stream.read());

           stream.reset();
           System.out.print((char) stream.read());
           System.out.print((char) stream.read());
       }catch (IOException e){
           e.printStackTrace();
       }
        //filep();

    }

    public static void filep(){
        File file=new File("src/test/java/com/javaclimb/xshopping/demo/34.mp4");
        try(FileInputStream in=new FileInputStream(file);
            FileOutputStream out=new FileOutputStream("src/test/java/com/javaclimb/xshopping/demo/filecopy.mp4",true)){
            int len;
//            byte[] bytes=new byte[in.available()];
            byte[] bytes=new byte[1024*1024];
            //in.read(bytes);
//            String s=new String(bytes);
            long sum=file.length(),total=0;
            while ((len=in.read(bytes)) != -1){
                out.write(bytes,0,len);
                total+=len;
                System.out.println( ( total*100 / sum ) + "%");
            }
            out.flush();
//            System.out.println(new String(bytes));
//            out.write(s.getBytes(StandardCharsets.UTF_8));
//            out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
//            out.write();
//              out.flush();
//            byte[] bytes=new byte[ stream.available()];
//            stream.read(bytes);
//            System.out.println(new String(bytes));
//            while ((i=stream.read())!= -1){
//                System.out.print((char) i);
//
//            }

// File file=new File("src/test/java/com/javaclimb/xshopping/demo/file.txt");
//        try(FileReader reader=new FileReader(file);
//            FileWriter writer=new FileWriter("src/test/java/com/javaclimb/xshopping/demo/filecopy.txt",true)){
////            int i;
////            char[] chars=new char[3];
////            while ((i = reader.read(chars)) != -1){
////                writer.write(chars,0,i);
////            }
////            writer.flush();
//
////            while (reader.read() != -1){
////                System.out.print((char) reader.read());
////            }
// //           System.out.println((char) reader.read());
//
//
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//
//