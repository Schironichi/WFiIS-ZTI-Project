package zti.zti_proj.rezerwacja_ksiazka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Klasa {@code RezerwacjaKsiazkaService} jest serwisem odpowiedzialnym za logikę biznesową związaną z rezerwacją książek.
 */
@Service
public class RezerwacjaKsiazkaService {
    private final RezerwacjaKsiazkaRepository rezerwacjaKsiazkaRepository;

    /**
     * Tworzy nową instancję klasy {@code RezerwacjaKsiazkaService} z określonym repozytorium rezerwacji książek.
     *
     * @param rezerwacjaKsiazkaRepository repozytorium rezerwacji książek do wykorzystania w operacjach
     */
    @Autowired
    public RezerwacjaKsiazkaService(RezerwacjaKsiazkaRepository rezerwacjaKsiazkaRepository) {
        this.rezerwacjaKsiazkaRepository = rezerwacjaKsiazkaRepository;
    }

    /**
     * Pobiera listę rezerwacji książek dla określonej rezerwacji.
     *
     * @param id_rezerwacja identyfikator rezerwacji
     * @return lista rekordów rezerwacji książek
     */
    public List<Map<String, Object>> getRezerwacjaKsiazkaByRezerwacja(Integer id_rezerwacja) {
        return rezerwacjaKsiazkaRepository.getRezerwacjaKsiazkaByRezerwacja(id_rezerwacja);
    }

    /**
     * Pobiera rekord rezerwacji książki na podstawie określonych identyfikatorów rezerwacji i książki.
     *
     * @param id_rezerwacja identyfikator rezerwacji
     * @param id_ksiazka    identyfikator książki
     * @return rekord rezerwacji książki
     */
    public Map<String, Object> getRezerwacjaKsiazkaByIds(Integer id_rezerwacja, Integer id_ksiazka) {
        return rezerwacjaKsiazkaRepository.getRezerwacjaKsiazkaByIds(id_rezerwacja, id_ksiazka);
    }

    /**
     * Dodaje nowy rekord rezerwacji książki na podstawie dostarczonych danych.
     *
     * @param rezerwacjaKsiazkaData dane rezerwacji książki do dodania
     */
    public void addRezerwacjaKsiazka(Map<String, Object> rezerwacjaKsiazkaData) {
        rezerwacjaKsiazkaRepository.createRezerwacjaKsiazka(rezerwacjaKsiazkaData);
    }

    /**
     * Aktualizuje istniejący rekord rezerwacji książki na podstawie dostarczonych danych.
     *
     * @param rezerwacjaKsiazkaData zaktualizowane dane rezerwacji książki
     */
    public void updateRezerwacjaKsiazka(Map<String, Object> rezerwacjaKsiazkaData) {
        rezerwacjaKsiazkaRepository.updateRezerwacjaKsiazka(rezerwacjaKsiazkaData);
    }

    /**
     * Usuwa rekord rezerwacji książki na podstawie określonych identyfikatorów rezerwacji i książki.
     *
     * @param id_rezerwacja identyfikator rezerwacji
     * @param id_ksiazka    identyfikator książki
     */
    public void deleteRezerwacjaKsiazka(Integer id_rezerwacja, Integer id_ksiazka) {
        rezerwacjaKsiazkaRepository.deleteRezerwacjaKsiazka(id_rezerwacja, id_ksiazka);
    }
}
