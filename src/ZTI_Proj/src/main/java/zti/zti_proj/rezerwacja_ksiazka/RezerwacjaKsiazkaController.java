package zti.zti_proj.rezerwacja_ksiazka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Klasa {@code RezerwacjaKsiazkaController} jest kontrolerem REST API odpowiedzialnym za obsługę operacji związanych z rezerwacją książek.
 */
@RestController
@RequestMapping("/api")
public class RezerwacjaKsiazkaController {
    private final RezerwacjaKsiazkaService rezerwacjaKsiazkaService;

    /**
     * Tworzy nową instancję klasy {@code RezerwacjaKsiazkaController} z określonym serwisem rezerwacji książek.
     *
     * @param rezerwacjaKsiazkaService serwis rezerwacji książek do wykorzystania w operacjach
     */
    @Autowired
    public RezerwacjaKsiazkaController(RezerwacjaKsiazkaService rezerwacjaKsiazkaService) {
        this.rezerwacjaKsiazkaService = rezerwacjaKsiazkaService;
    }

    /**
     * Pobiera listę rezerwacji książek dla określonej rezerwacji.
     *
     * @param id_rezerwacja identyfikator rezerwacji
     * @return lista rekordów rezerwacji książek
     */
    @CrossOrigin
    @GetMapping("/rezerwacja-ksiazka/{id_rezerwacja}")
    public List<Map<String, Object>> getRezerwacjaKsiazkaByRezerwacja(@PathVariable Integer id_rezerwacja) {
        return rezerwacjaKsiazkaService.getRezerwacjaKsiazkaByRezerwacja(id_rezerwacja);
    }

    /**
     * Pobiera rekord rezerwacji książki na podstawie określonych identyfikatorów rezerwacji i książki.
     *
     * @param id_rezerwacja identyfikator rezerwacji
     * @param id_ksiazka    identyfikator książki
     * @return rekord rezerwacji książki
     */
    @CrossOrigin
    @GetMapping("/rezerwacja-ksiazka/{id_rezerwacja}/{id_ksiazka}")
    public Map<String, Object> getRezerwacjaKsiazkaByIds(@PathVariable Integer id_rezerwacja, @PathVariable Integer id_ksiazka) {
        return rezerwacjaKsiazkaService.getRezerwacjaKsiazkaByIds(id_rezerwacja, id_ksiazka);
    }

    /**
     * Dodaje nowy rekord rezerwacji książki na podstawie dostarczonych danych.
     *
     * @param rezerwacjaKsiazkaData dane rezerwacji książki do dodania
     * @return odpowiedź HTTP z informacją o powodzeniu operacji
     */
    @CrossOrigin
    @PostMapping("/rezerwacja-ksiazka")
    public ResponseEntity<String> addRezerwacjaKsiazka(@RequestBody Map<String, Object> rezerwacjaKsiazkaData) {
        rezerwacjaKsiazkaService.addRezerwacjaKsiazka(rezerwacjaKsiazkaData);
        return new ResponseEntity<>("RezerwacjaKsiazka created successfully", HttpStatus.CREATED);
    }

    /**
     * Aktualizuje rekord rezerwacji książki na podstawie dostarczonych danych.
     *
     * @param rezerwacjaKsiazkaData dane rezerwacji książki do dodania
     * @return odpowiedź HTTP z informacją o powodzeniu operacji
     */
    @CrossOrigin
    @PutMapping("/rezerwacja-ksiazka")
    public ResponseEntity<String> updateRezerwacjaKsiazka(@RequestBody Map<String, Object> rezerwacjaKsiazkaData) {
        rezerwacjaKsiazkaService.updateRezerwacjaKsiazka(rezerwacjaKsiazkaData);
        return new ResponseEntity<>("RezerwacjaKsiazka updated successfully", HttpStatus.OK);
    }

    /**
     * Usuwa rekord rezerwacji książki na podstawie określonych identyfikatorów rezerwacji i książki.
     *
     * @param id_rezerwacja identyfikator rezerwacji
     * @param id_ksiazka    identyfikator książki
     * @return odpowiedź HTTP z informacją o powodzeniu operacji
     */
    @CrossOrigin
    @DeleteMapping("/rezerwacja-ksiazka/{id_rezerwacja}/{id_ksiazka}")
    public ResponseEntity<String> deleteRezerwacjaKsiazka(@PathVariable Integer id_rezerwacja, @PathVariable Integer id_ksiazka) {
        rezerwacjaKsiazkaService.deleteRezerwacjaKsiazka(id_rezerwacja, id_ksiazka);
        return new ResponseEntity<>("RezerwacjaKsiazka removed successfully", HttpStatus.OK);
    }
}
