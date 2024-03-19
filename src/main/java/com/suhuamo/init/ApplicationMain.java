package com.suhuamo.init;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ApplicationMain {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(ApplicationMain.class, args);
    }
}
