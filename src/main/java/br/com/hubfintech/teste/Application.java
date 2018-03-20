package br.com.hubfintech.teste;

import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal
 * 
 * @author José San Pedro
 */
@SpringBootApplication
public class Application {

    private static final Logger LOGGER = Logger.getLogger(Application.class);
    
    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("BRT"));
    }

    public static void main(String[] args) {
        BasicConfigurator.configure();
        LOGGER.info("Iniciando aplicação...");
        
        SpringApplication.run(Application.class, args);
    }
}
