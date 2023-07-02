package zti.zti_proj.dostawa_ksiazka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Kontroler obsługujący operacje na dostawie książek.
 * Odpowiada za obsługę żądań HTTP związanych z dostawą książek.
 */
@RestController
@RequestMapping("/api")
public class DostawaKsiazkaController {
    private final DostawaKsiazkaService dostawaKsiazkaService;

    /**
     * Konstruktor klasy DostawaKsiazkaController.
     *
     * @param dostawaKsiazkaService serwis dostawy książek
     */
    @Autowired
    public DostawaKsiazkaController(DostawaKsiazkaService dostawaKsiazkaService) {
        this.dostawaKsiazkaService = dostawaKsiazkaService;
    }

    /**
     * Metoda obsługująca żądanie GET dla listy dostaw książek na podstawie identyfikatora dostawy.
     *
     * @param id_dostawa identyfikator dostawy
     * @return lista dostaw książek
     */
    @CrossOrigin
    @GetMapping("/dostawa-ksiazka/{id_dostawa}")
    public List<Map<String, Object>> getDostawaKsiazkaByDostawa(@PathVariable Integer id_dostawa) {
        return dostawaKsiazkaService.getDostawaKsiazkaByDostawa(id_dostawa);
    }

    /**
     * Metoda obsługująca żądanie GET dla danych dostawy książki na podstawie identyfikatorów dostawy i książki.
     *
     * @param id_dostawa identyfikator dostawy
     * @param id_ksiazka identyfikator książki
     * @return dane dostawy książki
     */
    @CrossOrigin
    @GetMapping("/dostawa-ksiazka/{id_dostawa}/{id_ksiazka}")
    public Map<String, Object> getDostawaKsiazkaByIds(@PathVariable Integer id_dostawa, @PathVariable Integer id_ksiazka) {
        return dostawaKsiazkaService.getDostawaKsiazkaByIds(id_dostawa, id_ksiazka);
    }

    /**
     * Metoda obsługująca żądanie POST dodania nowej dostawy książki.
     *
     * @param dostawaKsiazkaData dane dostawy książki
     * @return odpowiedź HTTP informująca o powodzeniu dodania dostawy książki
     */
    @CrossOrigin
    @PostMapping("/dostawa-ksiazka")
    public ResponseEntity<String> addDostawaKsiazka(@RequestBody Map<String, Object> dostawaKsiazkaData) {
        dostawaKsiazkaService.addDostawaKsiazka(dostawaKsiazkaData);
        return new ResponseEntity<>("DostawaKsiazka created successfully", HttpStatus.CREATED);
    }

    /**
     * Metoda obsługująca żądanie PUT aktualizacji dostawy książki.
     *
     * @param dostawaKsiazkaData dane dostawy książki
     * @return odpowiedź HTTP informująca o powodzeniu edycji dostawy książki
     */
    @CrossOrigin
    @PutMapping("/dostawa-ksiazka")
    public ResponseEntity<String> updateDostawaKsiazka(@RequestBody Map<String, Object> dostawaKsiazkaData) {
        dostawaKsiazkaService.updateDostawaKsiazka(dostawaKsiazkaData);
        return new ResponseEntity<>("DostawaKsiazka updated successfully", HttpStatus.OK);
    }

    /**
     * Metoda obsługująca żądanie DELETE usuwania dostawy książki.
     *
     * @param id_dostawa identyfikator dostawy
     * @param id_ksiazka identyfikator książki
     * @return odpowiedź HTTP informująca o powodzeniu usunięcia dostawy książki
     */
    @CrossOrigin
    @DeleteMapping("/dostawa-ksiazka/{id_dostawa}/{id_ksiazka}")
    public ResponseEntity<String> deleteDostawaKsiazka(@PathVariable Integer id_dostawa, @PathVariable Integer id_ksiazka) {
        dostawaKsiazkaService.deleteDostawaKsiazka(id_dostawa, id_ksiazka);
        return new ResponseEntity<>("DostawaKsiazka removed successfully", HttpStatus.OK);
    }
}
