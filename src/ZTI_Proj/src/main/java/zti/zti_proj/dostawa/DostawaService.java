package zti.zti_proj.dostawa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Serwis obsługujący operacje na dostawach.
 * Odpowiada za logikę biznesową związaną z dostawami.
 */
@Service
public class DostawaService {
    private final DostawaRepository dostawaRepository;

    /**
     * Konstruktor klasy DostawaService.
     *
     * @param dostawaRepository repozytorium dostaw
     */
    @Autowired
    public DostawaService(DostawaRepository dostawaRepository) {
        this.dostawaRepository = dostawaRepository;
    }

    /**
     * Metoda zwracająca listę wszystkich dostaw.
     *
     * @return lista dostaw
     */
    public List<Map<String, Object>> getDostawaList() {
        return dostawaRepository.getDostawaList();
    }

    /**
     * Metoda zwracająca dane dostawy na podstawie identyfikatora.
     *
     * @param id_dostawa identyfikator dostawy
     * @return dane dostawy
     */
    public Map<String, Object> getDostawaById(Integer id_dostawa) {
        return dostawaRepository.getDostawaById(id_dostawa);
    }

    /**
     * Metoda dodająca nową dostawę.
     *
     * @param dostawaData dane dostawy
     */
    public void addDostawa(Map<String, Object> dostawaData) {
        dostawaRepository.createDostawa(dostawaData);
    }

    /**
     * Metoda aktualizująca dane dostawy.
     *
     * @param dostawaData dane dostawy
     */
    public void updateDostawa(Map<String, Object> dostawaData) {
        dostawaRepository.updateDostawa(dostawaData);
    }

    /**
     * Metoda usuwająca dostawę na podstawie identyfikatora.
     *
     * @param id_dostawa identyfikator dostawy
     */
    public void deleteDostawa(Integer id_dostawa) {
        dostawaRepository.deleteDostawa(id_dostawa);
    }
}