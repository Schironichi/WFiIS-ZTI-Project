package zti.zti_proj.rezerwacja_ksiazka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

/**
 * Klasa {@code RezerwacjaKsiazkaRepository} jest repozytorium obsługującym operacje na tabeli rezerwacja_ksiazka.
 */
@Repository
public class RezerwacjaKsiazkaRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Tworzy nową instancję klasy {@code RezerwacjaKsiazkaRepository} z określonym źródłem danych.
     *
     * @param dataSource źródło danych do wykorzystania w operacjach
     */
    public RezerwacjaKsiazkaRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Pobiera listę rezerwacji książek dla określonej rezerwacji.
     *
     * @param id_rezerwacja identyfikator rezerwacji
     * @return lista rekordów rezerwacji książek
     */
    public List<Map<String, Object>> getRezerwacjaKsiazkaByRezerwacja(Integer id_rezerwacja) {
        String sql = "SELECT * FROM lista_rezerwacja where id_rezerwacja= ?";
        return jdbcTemplate.queryForList(sql, id_rezerwacja);
    }

    /**
     * Pobiera rekord rezerwacji książki na podstawie określonych identyfikatorów rezerwacji i książki.
     *
     * @param id_rezerwacja identyfikator rezerwacji
     * @param id_ksiazka    identyfikator książki
     * @return rekord rezerwacji książki
     */
    public Map<String, Object> getRezerwacjaKsiazkaByIds(Integer id_rezerwacja, Integer id_ksiazka) {
        String sql = "SELECT * FROM rezerwacja_ksiazka where id_rezerwacja= ? and id_ksiazka= ?";
        return jdbcTemplate.queryForMap(sql, id_rezerwacja, id_ksiazka);
    }

    /**
     * Tworzy nowy rekord rezerwacji książki na podstawie dostarczonych danych.
     *
     * @param rezerwacjaKsiazkaData dane rezerwacji książki do utworzenia
     */
    public void createRezerwacjaKsiazka(Map<String, Object> rezerwacjaKsiazkaData) {
        String sql = "INSERT INTO rezerwacja_ksiazka (id_rezerwacja, id_ksiazka, czas_wypozyczenia, rezerwacja_ilosc) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, (Integer) rezerwacjaKsiazkaData.get("id_rezerwacja"));
            ps.setInt(2, (Integer) rezerwacjaKsiazkaData.get("id_ksiazka"));
            ps.setInt(3, (Integer) rezerwacjaKsiazkaData.get("czas_wypozyczenia"));
            ps.setInt(4, (Integer) rezerwacjaKsiazkaData.get("rezerwacja_ilosc"));
            return ps;
        });
    }

    /**
     * Aktualizuje rekord rezerwacji książki na podstawie dostarczonych danych.
     *
     * @param rezerwacjaKsiazkaData dane rezerwacji książki do utworzenia
     */
    public void updateRezerwacjaKsiazka(Map<String, Object> rezerwacjaKsiazkaData) {
        String sql = "UPDATE rezerwacja_ksiazka SET czas_wypozyczenia= ?, rezerwacja_ilosc= ? WHERE id_rezerwacja = ? AND id_ksiazka = ?";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, (Integer) rezerwacjaKsiazkaData.get("czas_wypozyczenia"));
            ps.setInt(2, (Integer) rezerwacjaKsiazkaData.get("rezerwacja_ilosc"));
            ps.setInt(3, (Integer) rezerwacjaKsiazkaData.get("id_rezerwacja"));
            ps.setInt(4, (Integer) rezerwacjaKsiazkaData.get("id_ksiazka"));
            return ps;
        });
    }

    /**
     * Usuwa rekord rezerwacji książki na podstawie określonych identyfikatorów rezerwacji i książki.
     *
     * @param id_rezerwacja identyfikator rezerwacji
     * @param id_ksiazka    identyfikator książki
     */
    public void deleteRezerwacjaKsiazka(Integer id_rezerwacja, Integer id_ksiazka) {
        String sql = "DELETE FROM rezerwacja_ksiazka WHERE id_rezerwacja = ? AND id_ksiazka = ?";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id_rezerwacja);
            ps.setInt(2, id_ksiazka);
            return ps;
        });
    }
}
