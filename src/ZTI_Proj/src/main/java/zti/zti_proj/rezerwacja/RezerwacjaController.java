package zti.zti_proj.rezerwacja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Klasa {@code RezerwacjaController} jest kontrolerem REST API odpowiedzialnym za obsługę żądań dotyczących rezerwacji.
 */
@RestController
@RequestMapping("/api")
public class RezerwacjaController {
    private final RezerwacjaService rezerwacjaService;

    /**
     * Tworzy nową instancję klasy {@code RezerwacjaController} z określonym serwisem rezerwacji.
     *
     * @param rezerwacjaService serwis rezerwacji do wykorzystania w operacjach
     */
    @Autowired
    public RezerwacjaController(RezerwacjaService rezerwacjaService) {
        this.rezerwacjaService = rezerwacjaService;
    }

    /**
     * Obsługuje żądanie GET dla ścieżki "/rezerwacja" i zwraca listę wszystkich rekordów rezerwacji.
     *
     * @return lista rekordów rezerwacji
     */
    @CrossOrigin
    @GetMapping("/rezerwacja")
    public List<Map<String, Object>> getRezerwacjaList() {
        return rezerwacjaService.getRezerwacjaList();
    }

    /**
     * Obsługuje żądanie GET dla ścieżki "/rezerwacja/{id_rezerwacja}" i zwraca rekord rezerwacji o podanym identyfikatorze.
     *
     * @param id_rezerwacja identyfikator rezerwacji do pobrania
     * @return rekord rezerwacji
     */
    @CrossOrigin
    @GetMapping("/rezerwacja/{id_rezerwacja}")
    public Map<String, Object> getRezerwacjaById(@PathVariable Integer id_rezerwacja) {
        return rezerwacjaService.getRezerwacjaById(id_rezerwacja);
    }

    /**
     * Obsługuje żądanie POST dla ścieżki "/rezerwacja" i dodaje nowy rekord rezerwacji na podstawie dostarczonych danych.
     *
     * @param rezerwacjaData dane rezerwacji do dodania
     * @return odpowiedź HTTP z informacją o sukcesie operacji
     */
    @CrossOrigin
    @PostMapping("/rezerwacja")
    public ResponseEntity<String> addRezerwacja(@RequestBody Map<String, Object> rezerwacjaData) {
        rezerwacjaService.addRezerwacja(rezerwacjaData);
        return new ResponseEntity<>("Rezerwacja created successfully", HttpStatus.CREATED);
    }

    /**
     * Obsługuje żądanie PUT dla ścieżki "/rezerwacja" i aktualizuje istniejący rekord rezerwacji na podstawie dostarczonych danych.
     *
     * @param rezerwacjaData zaktualizowane dane rezerwacji
     * @return odpowiedź HTTP z informacją o sukcesie operacji
     */
    @CrossOrigin
    @PutMapping("/rezerwacja")
    public ResponseEntity<String> updateRezerwacja(@RequestBody Map<String, Object> rezerwacjaData) {
        rezerwacjaService.updateRezerwacja(rezerwacjaData);
        return new ResponseEntity<>("Rezerwacja updated successfully", HttpStatus.OK);
    }

    /**
     * Obsługuje żądanie DELETE dla ścieżki "/rezerwacja/{id_rezerwacja}" i usuwa istniejący rekord rezerwacji na podstawie dostarczonych danych.
     *
     * @param id_rezerwacja identyfikator rezerwacji do usunięcia
     * @return odpowiedź HTTP z informacją o sukcesie operacji
     */
    @CrossOrigin
    @DeleteMapping("/rezerwacja/{id_rezerwacja}")
    public ResponseEntity<String> deleteRezerwacja(@PathVariable Integer id_rezerwacja) {
        rezerwacjaService.deleteRezerwacja(id_rezerwacja);
        return new ResponseEntity<>("Rezerwacja removed successfully", HttpStatus.OK);
    }
}
