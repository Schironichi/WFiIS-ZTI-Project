package zti.zti_proj.dostawa_ksiazka;

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
 * Repozytorium obsługujące dostawę książek.
 * Odpowiada za operacje na bazie danych związane z dostawą książek.
 */
@Repository
public class DostawaKsiazkaRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Konstruktor klasy DostawaKsiazkaRepository.
     *
     * @param dataSource źródło danych
     */
    public DostawaKsiazkaRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Metoda pobierająca dostawę książek na podstawie identyfikatora dostawy.
     *
     * @param id_dostawa identyfikator dostawy
     * @return lista dostaw książek
     */
    public List<Map<String, Object>> getDostawaKsiazkaByDostawa(Integer id_dostawa) {
        String sql = "SELECT * FROM lista_dostawa WHERE id_dostawa = ?";
        return jdbcTemplate.queryForList(sql, id_dostawa);
    }

    /**
     * Metoda pobierająca dostawę książki na podstawie identyfikatorów dostawy i książki.
     *
     * @param id_dostawa identyfikator dostawy
     * @param id_ksiazka identyfikator książki
     * @return dane dostawy książki
     */
    public Map<String, Object> getDostawaKsiazkaByIds(Integer id_dostawa, Integer id_ksiazka) {
        String sql = "SELECT * FROM dostawa_ksiazka WHERE id_dostawa = ? AND id_ksiazka = ?";
        return jdbcTemplate.queryForMap(sql, id_dostawa, id_ksiazka);
    }

    /**
     * Metoda tworząca nową dostawę książki.
     *
     * @param dostawaKsiazkaData dane dostawy książki
     */
    public void createDostawaKsiazka(Map<String, Object> dostawaKsiazkaData) {
        String sql = "INSERT INTO dostawa_ksiazka (id_dostawa, id_ksiazka, ilosc) VALUES (?, ?, ?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, (Integer) dostawaKsiazkaData.get("id_dostawa"));
            ps.setInt(2, (Integer) dostawaKsiazkaData.get("id_ksiazka"));
            ps.setInt(3, (Integer) dostawaKsiazkaData.get("ilosc"));
            return ps;
        });
    }

    /**
     * Metoda aktualizująca dostawę książki.
     *
     * @param dostawaKsiazkaData dane dostawy książki
     */
    public void updateDostawaKsiazka(Map<String, Object> dostawaKsiazkaData) {
        String sql = "UPDATE dostawa_ksiazka SET ilosc = ? WHERE id_dostawa = ? AND id_ksiazka = ?";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, (Integer) dostawaKsiazkaData.get("ilosc"));
            ps.setInt(2, (Integer) dostawaKsiazkaData.get("id_dostawa"));
            ps.setInt(3, (Integer) dostawaKsiazkaData.get("id_ksiazka"));
            return ps;
        });
    }

    /**
     * Metoda usuwająca dostawę książki.
     *
     * @param id_dostawa identyfikator dostawy
     * @param id_ksiazka identyfikator książki
     */
    public void deleteDostawaKsiazka(Integer id_dostawa, Integer id_ksiazka) {
        String sql = "DELETE FROM dostawa_ksiazka WHERE id_dostawa = ? AND id_ksiazka = ?";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id_dostawa);
            ps.setInt(2, id_ksiazka);
            return ps;
        });
    }
}
