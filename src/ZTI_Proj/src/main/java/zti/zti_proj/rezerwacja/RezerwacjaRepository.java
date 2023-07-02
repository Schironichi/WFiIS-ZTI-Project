package zti.zti_proj.rezerwacja;

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
 * Klasa jest odpowiedzialna za interakcję z bazą danych
 * w celu wykonywania operacji CRUD na tabeli "rezerwacja".
 */
@Repository
public class RezerwacjaRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Tworzy nową instancję klasy z określonym źródłem danych.
     *
     * @param dataSource źródło danych do wykorzystania w operacjach bazodanowych
     */
    public RezerwacjaRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Pobiera listę wszystkich rekordów rezerwacji z widoku "rezerwacja_view".
     *
     * @return lista rekordów rezerwacji
     */
    public List<Map<String, Object>> getRezerwacjaList() {
        String sql = "SELECT * FROM rezerwacja_view";
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * Pobiera rekord rezerwacji o podanym identyfikatorze.
     *
     * @param id_rezerwacja identyfikator rezerwacji do pobrania
     * @return rekord rezerwacji
     */
    public Map<String, Object> getRezerwacjaById(Integer id_rezerwacja) {
        String sql = "SELECT * FROM rezerwacja WHERE id_rezerwacja = ?";
        return jdbcTemplate.queryForMap(sql, id_rezerwacja);
    }

    /**
     * Tworzy nowy rekord rezerwacji na podstawie dostarczonych danych.
     *
     * @param rezerwacjaData dane rezerwacji do utworzenia
     */
    public void createRezerwacja(Map<String, Object> rezerwacjaData) {
        String sql = "INSERT INTO rezerwacja (email, telefon, imie, data_rezerwacji) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, (String) rezerwacjaData.get("email"));
            ps.setString(2, (String) rezerwacjaData.get("telefon"));
            ps.setString(3, (String) rezerwacjaData.get("imie"));
            ps.setDate(4, Date.valueOf((String) rezerwacjaData.get("data_rezerwacji")));
            return ps;
        });
    }

    /**
     * Aktualizuje istniejący rekord rezerwacji na podstawie dostarczonych danych.
     *
     * @param rezerwacjaData zaktualizowane dane rezerwacji
     */
    public void updateRezerwacja(Map<String, Object> rezerwacjaData) {
        String sql = "UPDATE rezerwacja SET email= ?, telefon= ?, imie= ?, data_rezerwacji= ? WHERE id_rezerwacja= ?";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, (String) rezerwacjaData.get("email"));
            ps.setString(2, (String) rezerwacjaData.get("telefon"));
            ps.setString(3, (String) rezerwacjaData.get("imie"));
            ps.setDate(4, Date.valueOf((String) rezerwacjaData.get("data_rezerwacji")));
            ps.setInt(5, (Integer) rezerwacjaData.get("id_rezerwacja"));
            return ps;
        });
    }

    /**
     * Usuwa rekord rezerwacji o podanym identyfikatorze.
     *
     * @param id_rezerwacja identyfikator rezerwacji do usunięcia
     */
    public void deleteRezerwacja(Integer id_rezerwacja) {
        String sql = "DELETE FROM rezerwacja WHERE id_rezerwacja = ?";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id_rezerwacja);
            return ps;
        });
    }
}
