package com.example.hiber;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HiberApplication implements CommandLineRunner {

   public static void main(String[] args) {
      SpringApplication app = new SpringApplication(HiberApplication.class);
      app.setBannerMode(Banner.Mode.OFF);
      app.run(args);

   }

   @Override
   public void run(String... args) {
      System.out.println("\n*** App started ***\n");
   }

}