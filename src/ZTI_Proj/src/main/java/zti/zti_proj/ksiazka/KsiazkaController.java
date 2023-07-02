package zti.zti_proj.ksiazka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Kontroler obsługujący żądania dotyczące książek.
 * Odpowiada za obsługę żądań HTTP związanych z książkami.
 */
@RestController
@RequestMapping("/api")
public class KsiazkaController {
    private final KsiazkaService ksiazkaService;

    /**
     * Konstruktor klasy KsiazkaController.
     *
     * @param ksiazkaService serwis książek
     */
    @Autowired
    public KsiazkaController(KsiazkaService ksiazkaService) {
        this.ksiazkaService = ksiazkaService;
    }

    /**
     * Metoda obsługująca żądanie pobrania listy książek.
     *
     * @return lista książek
     */
    @CrossOrigin
    @GetMapping("/ksiazka")
    public List<Map<String, Object>> getKsiazkaList() {
        return ksiazkaService.getKsiazkaList();
    }

    /**
     * Metoda obsługująca żądanie pobrania danych książki na podstawie identyfikatora.
     *
     * @param id_ksiazka identyfikator książki
     * @return dane książki
     */
    @CrossOrigin
    @GetMapping("/ksiazka/{id_ksiazka}")
    public Map<String, Object> getKsiazkaById(@PathVariable Integer id_ksiazka) {
        return ksiazkaService.getKsiazkaById(id_ksiazka);
    }

    /**
     * Metoda obsługująca żądanie dodania nowej książki.
     *
     * @param ksiazkaData dane książki
     * @return odpowiedź HTTP o sukcesie utworzenia
     */
    @CrossOrigin
    @PostMapping("/ksiazka")
    public ResponseEntity<String> addKsiazka(@RequestBody Map<String, Object> ksiazkaData) {
        ksiazkaService.addKsiazka(ksiazkaData);
        return new ResponseEntity<>("Ksiazka created successfully", HttpStatus.CREATED);
    }

    /**
     * Metoda obsługująca żądanie aktualizacji danych książki.
     *
     * @param ksiazkaData dane książki
     * @return odpowiedź HTTP o sukcesie aktualizacji
     */
    @CrossOrigin
    @PutMapping("/ksiazka")
    public ResponseEntity<String> updateKsiazka(@RequestBody Map<String, Object> ksiazkaData) {
        ksiazkaService.updateKsiazka(ksiazkaData);
        return new ResponseEntity<>("Ksiazka updated successfully", HttpStatus.OK);
    }

    /**
     * Metoda obsługująca żądanie usunięcia książki na podstawie identyfikatora.
     *
     * @param id_ksiazka identyfikator książki
     * @return odpowiedź HTTP o sukcesie usunięcia
     */
    @CrossOrigin
    @DeleteMapping("/ksiazka/{id_ksiazka}")
    public ResponseEntity<String> deleteKsiazka(@PathVariable Integer id_ksiazka) {
        ksiazkaService.deleteKsiazka(id_ksiazka);
        return new ResponseEntity<>("Ksiazka removed successfully", HttpStatus.OK);
    }
}
