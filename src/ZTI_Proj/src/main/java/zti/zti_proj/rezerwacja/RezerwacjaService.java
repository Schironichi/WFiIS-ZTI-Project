package zti.zti_proj.rezerwacja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Klasa jest odpowiedzialna za obsługę operacji związanych z rezerwacją.
 */
@Service
public class RezerwacjaService {
    private final RezerwacjaRepository rezerwacjaRepository;

    /**
     * Tworzy nową instancję klasy z określonym repozytorium rezerwacji.
     *
     * @param rezerwacjaRepository repozytorium rezerwacji do wykorzystania w operacjach
     */
    @Autowired
    public RezerwacjaService(RezerwacjaRepository rezerwacjaRepository) {
        this.rezerwacjaRepository = rezerwacjaRepository;
    }

    /**
     * Pobiera listę wszystkich rekordów rezerwacji.
     *
     * @return lista rekordów rezerwacji
     */
    public List<Map<String, Object>> getRezerwacjaList() {
        return rezerwacjaRepository.getRezerwacjaList();
    }

    /**
     * Pobiera rekord rezerwacji o podanym identyfikatorze.
     *
     * @param id_rezerwacja identyfikator rezerwacji do pobrania
     * @return rekord rezerwacji
     */
    public Map<String, Object> getRezerwacjaById(Integer id_rezerwacja) {
        return rezerwacjaRepository.getRezerwacjaById(id_rezerwacja);
    }

    /**
     * Dodaje nowy rekord rezerwacji na podstawie dostarczonych danych.
     *
     * @param rezerwacjaData dane rezerwacji do dodania
     */
    public void addRezerwacja(Map<String, Object> rezerwacjaData) {
        rezerwacjaRepository.createRezerwacja(rezerwacjaData);
    }

    /**
     * Aktualizuje istniejący rekord rezerwacji na podstawie dostarczonych danych.
     *
     * @param rezerwacjaData zaktualizowane dane rezerwacji
     */
    public void updateRezerwacja(Map<String, Object> rezerwacjaData) {
        rezerwacjaRepository.updateRezerwacja(rezerwacjaData);
    }

    /**
     * Usuwa rekord rezerwacji o podanym identyfikatorze.
     *
     * @param id_rezerwacja identyfikator rezerwacji do usunięcia
     */
    public void deleteRezerwacja(Integer id_rezerwacja) {
        rezerwacjaRepository.deleteRezerwacja(id_rezerwacja);
    }
}
