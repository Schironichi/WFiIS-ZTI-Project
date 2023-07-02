package zti.zti_proj.ksiazka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

/**
 * Repozytorium danych dla obiektów Ksiazka.
 * Odpowiada za operacje bazodanowe związane z obiektami Ksiazka.
 */
@Repository
public class KsiazkaRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Konstruktor klasy KsiazkaRepository.
     *
     * @param dataSource źródło danych do wykonania operacji bazodanowych
     */
    public KsiazkaRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Metoda pobierająca listę książek.
     *
     * @return lista książek
     */
    public List<Map<String, Object>> getKsiazkaList() {
        String sql = "SELECT * FROM lista_ksiazek";
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * Metoda pobierająca dane książki na podstawie identyfikatora.
     *
     * @param id_ksiazka identyfikator książki
     * @return dane książki
     */
    public Map<String, Object> getKsiazkaById(Integer id_ksiazka) {
        String sql = "SELECT * FROM lista_ksiazek WHERE id_ksiazka = ?";
        return jdbcTemplate.queryForMap(sql, id_ksiazka);
    }

    /**
     * Metoda dodająca nową książkę.
     *
     * @param ksiazkaData dane książki
     */
    public void createKsiazka(Map<String, Object> ksiazkaData) {
        String sql = "INSERT INTO ksiazka (autor, tytul, gatunek, opis, wydawnictwo, rok_wydania, wypozyczona_ilosc) " +
                "VALUES (?, ?, ?, ?, ?, ?, 0)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, (String) ksiazkaData.get("autor"));
            ps.setString(2, (String) ksiazkaData.get("tytul"));
            ps.setString(3, (String) ksiazkaData.get("gatunek"));
            ps.setString(4, (String) ksiazkaData.get("opis"));
            ps.setString(5, (String) ksiazkaData.get("wydawnictwo"));
            ps.setInt(6, (Integer) ksiazkaData.get("rok_wydania"));
            return ps;
        });
    }

    /**
     * Metoda aktualizująca dane książki.
     *
     * @param ksiazkaData dane książki
     */
    public void updateKsiazka(Map<String, Object> ksiazkaData) {
        String sql = "UPDATE ksiazka SET autor = ?, tytul = ?, gatunek = ?, opis = ?, wydawnictwo = ?, rok_wydania = ? WHERE id_ksiazka = ?";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, (String) ksiazkaData.get("autor"));
            ps.setString(2, (String) ksiazkaData.get("tytul"));
            ps.setString(3, (String) ksiazkaData.get("gatunek"));
            ps.setString(4, (String) ksiazkaData.get("opis"));
            ps.setString(5, (String) ksiazkaData.get("wydawnictwo"));
            ps.setInt(6, (Integer) ksiazkaData.get("rok_wydania"));
            ps.setInt(7, (Integer) ksiazkaData.get("id_ksiazka"));
            return ps;
        });
    }

    /**
     * Metoda usuwającad ane książki na podstawie identyfikatora.
     *
     * @param id_ksiazka identyfikator książki
     */
    public void deleteKsiazka(Integer id_ksiazka) {
        String sql = "DELETE FROM ksiazka WHERE id_ksiazka = ?";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id_ksiazka);
            return ps;
        });
    }
}
