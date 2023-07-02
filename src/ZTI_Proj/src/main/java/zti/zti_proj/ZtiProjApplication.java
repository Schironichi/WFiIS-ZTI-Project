package zti.zti_proj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Główna klasa aplikacji ZtiProj.
 * Uruchamia aplikację Spring Boot.
 */
@SpringBootApplication
public class ZtiProjApplication {
    /**
     * Metoda główna, uruchamiająca aplikację.
     *
     * @param args argumenty wiersza poleceń
     */
    public static void main(String[] args) {
        SpringApplication.run(ZtiProjApplication.class, args);
    }

}
