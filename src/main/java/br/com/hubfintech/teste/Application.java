package br.com.hubfintech.teste;

import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal
 * 
 * @author José San Pedro
 */
@SpringBootApplication
public class Application {

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("BRT"));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
