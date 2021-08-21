package com.brainframe.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BrainframeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BrainframeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ServiceApi api = new ServiceApi("http://192.168.1.13:80");
        api.getServerVersion();
        api.getLicenseInfo();
    }
}
