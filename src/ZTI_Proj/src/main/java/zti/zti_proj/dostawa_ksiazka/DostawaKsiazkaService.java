package zti.zti_proj.dostawa_ksiazka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Serwis obsługujący dostawę książek.
 * Odpowiada za operacje na dostawie książek i komunikację z repozytorium.
 */
@Service
public class DostawaKsiazkaService {
    private final DostawaKsiazkaRepository dostawaKsiazkaRepository;

    /**
     * Konstruktor klasy DostawaKsiazkaService.
     *
     * @param dostawaKsiazkaRepository repozytorium dostawy książek
     */
    @Autowired
    public DostawaKsiazkaService(DostawaKsiazkaRepository dostawaKsiazkaRepository) {
        this.dostawaKsiazkaRepository = dostawaKsiazkaRepository;
    }

    /**
     * Metoda pobierająca dostawę książek na podstawie identyfikatora dostawy.
     *
     * @param id_dostawa identyfikator dostawy
     * @return lista dostaw książek
     */
    public List<Map<String, Object>> getDostawaKsiazkaByDostawa(Integer id_dostawa) {
        return dostawaKsiazkaRepository.getDostawaKsiazkaByDostawa(id_dostawa);
    }

    /**
     * Metoda pobierająca dostawę książki na podstawie identyfikatorów dostawy i książki.
     *
     * @param id_dostawa identyfikator dostawy
     * @param id_ksiazka identyfikator książki
     * @return dane dostawy książki
     */
    public Map<String, Object> getDostawaKsiazkaByIds(Integer id_dostawa, Integer id_ksiazka) {
        return dostawaKsiazkaRepository.getDostawaKsiazkaByIds(id_dostawa, id_ksiazka);
    }

    /**
     * Metoda dodająca nową dostawę książki.
     *
     * @param dostawaKsiazkaData dane dostawy książki
     */
    public void addDostawaKsiazka(Map<String, Object> dostawaKsiazkaData) {
        dostawaKsiazkaRepository.createDostawaKsiazka(dostawaKsiazkaData);
    }

    /**
     * Metoda aktualizująca dostawę książki.
     *
     * @param dostawaKsiazkaData dane dostawy książki
     */
    public void updateDostawaKsiazka(Map<String, Object> dostawaKsiazkaData) {
        dostawaKsiazkaRepository.updateDostawaKsiazka(dostawaKsiazkaData);
    }

    /**
     * Metoda usuwająca dostawę książki na podstawie identyfikatora dostawy i książki.
     *
     * @param id_dostawa identyfikator dostawy
     * @param id_ksiazka identyfikator książki
     */
    public void deleteDostawaKsiazka(Integer id_dostawa, Integer id_ksiazka) {
        dostawaKsiazkaRepository.deleteDostawaKsiazka(id_dostawa, id_ksiazka);
    }
}
