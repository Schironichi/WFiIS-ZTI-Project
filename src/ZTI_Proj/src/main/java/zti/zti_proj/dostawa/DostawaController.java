package zti.zti_proj.dostawa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Kontroler obsługujący zasoby dostaw.
 * Odpowiada za obsługę żądań HTTP związanych z dostawami.
 */
@RestController
@RequestMapping("/api")
public class DostawaController {
    private final DostawaService dostawaService;

    /**
     * Konstruktor klasy DostawaController.
     *
     * @param dostawaService serwis dostaw
     */
    @Autowired
    public DostawaController(DostawaService dostawaService) {
        this.dostawaService = dostawaService;
    }

    /**
     * Metoda obsługująca żądanie pobrania listy dostaw.
     *
     * @return lista dostaw
     */
    @CrossOrigin
    @GetMapping("/dostawa")
    public List<Map<String, Object>> getDostawaList() {
        return dostawaService.getDostawaList();
    }

    /**
     * Metoda obsługująca żądanie pobrania danych dostawy na podstawie identyfikatora.
     *
     * @param id_dostawa identyfikator dostawy
     * @return dane dostawy
     */
    @CrossOrigin
    @GetMapping("/dostawa/{id_dostawa}")
    public Map<String, Object> getDostawaById(@PathVariable Integer id_dostawa) {
        return dostawaService.getDostawaById(id_dostawa);
    }

    /**
     * Metoda obsługująca żądanie dodania nowej dostawy.
     *
     * @param dostawaData dane dostawy w formacie JSON
     * @return odpowiedź z kodem HTTP i komunikatem
     */
    @CrossOrigin
    @PostMapping("/dostawa")
    public ResponseEntity<String> addDostawa(@RequestBody Map<String, Object> dostawaData) {
        dostawaService.addDostawa(dostawaData);
        return new ResponseEntity<>("Dostawa created successfully", HttpStatus.CREATED);
    }

    /**
     * Metoda obsługująca żądanie aktualizacji danych dostawy.
     *
     * @param dostawaData dane dostawy w formacie JSON
     * @return odpowiedź z kodem HTTP i komunikatem
     */
    @CrossOrigin
    @PutMapping("/dostawa")
    public ResponseEntity<String> updateDostawa(@RequestBody Map<String, Object> dostawaData) {
        dostawaService.updateDostawa(dostawaData);
        return new ResponseEntity<>("Dostawa updated successfully", HttpStatus.OK);
    }

    /**
     * Metoda obsługująca żądanie usunięcia dostawy na podstawie identyfikatora.
     *
     * @param id_dostawa identyfikator dostawy
     * @return odpowiedź z kodem HTTP i komunikatem
     */
    @CrossOrigin
    @DeleteMapping("/dostawa/{id_dostawa}")
    public ResponseEntity<String> deleteDostawa(@PathVariable Integer id_dostawa) {
        dostawaService.deleteDostawa(id_dostawa);
        return new ResponseEntity<>("Dostawa removed successfully", HttpStatus.OK);
    }
}