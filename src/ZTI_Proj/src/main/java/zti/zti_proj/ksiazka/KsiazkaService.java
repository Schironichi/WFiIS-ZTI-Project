package zti.zti_proj.ksiazka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Serwis obsługujący operacje na książkach.
 * Odpowiada za logikę biznesową związaną z książkami.
 */
@Service
public class KsiazkaService {
    private final KsiazkaRepository ksiazkaRepository;

    /**
     * Konstruktor klasy KsiazkaService.
     *
     * @param ksiazkaRepository repozytorium książek
     */
    @Autowired
    public KsiazkaService(KsiazkaRepository ksiazkaRepository) {
        this.ksiazkaRepository = ksiazkaRepository;
    }

    /**
     * Metoda pobierająca listę książek.
     *
     * @return lista książek
     */
    public List<Map<String, Object>> getKsiazkaList() {
        return ksiazkaRepository.getKsiazkaList();
    }

    /**
     * Metoda pobierająca dane książki na podstawie identyfikatora.
     *
     * @param id_ksiazka identyfikator książki
     * @return dane książki
     */
    public Map<String, Object> getKsiazkaById(Integer id_ksiazka) {
        return ksiazkaRepository.getKsiazkaById(id_ksiazka);
    }

    /**
     * Metoda dodająca nową książkę.
     *
     * @param ksiazkaData dane książki
     */
    public void addKsiazka(Map<String, Object> ksiazkaData) {
        ksiazkaRepository.createKsiazka(ksiazkaData);
    }

    /**
     * Metoda aktualizująca dane książki.
     *
     * @param ksiazkaData dane książki
     */
    public void updateKsiazka(Map<String, Object> ksiazkaData) {
        ksiazkaRepository.updateKsiazka(ksiazkaData);
    }

    /**
     * Metoda usuwająca książkę na podstawie identyfikatora.
     *
     * @param id_ksiazka identyfikator książki
     */
    public void deleteKsiazka(Integer id_ksiazka) {
        ksiazkaRepository.deleteKsiazka(id_ksiazka);
    }
}