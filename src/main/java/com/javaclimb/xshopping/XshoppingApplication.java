package com.javaclimb.xshopping;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.javaclimb.xshopping.mapper")
@SpringBootApplication
public class XshoppingApplication {

    public static void main(String[] args) {
        SpringApplication.run(XshoppingApplication.class, args);
    }

    static {
        System.setProperty("druid.mysql.usePingMethod","false");
    }
}
