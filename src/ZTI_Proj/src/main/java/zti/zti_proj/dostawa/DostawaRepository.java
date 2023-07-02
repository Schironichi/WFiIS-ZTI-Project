package zti.zti_proj.dostawa;

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
 * Klasa repozytorium dla encji "Dostawa".
 * Odpowiada za dostęp do bazy danych i wykonywanie operacji CRUD na encji "Dostawa".
 */
@Repository
public class DostawaRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Konstruktor klasy DostawaRepository.
     *
     * @param dataSource źródło danych do inicjalizacji obiektu JdbcTemplate
     */
    public DostawaRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Metoda zwracająca listę wszystkich dostaw.
     *
     * @return lista dostaw
     */
    public List<Map<String, Object>> getDostawaList() {
        String sql = "SELECT * FROM dostawa";
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * Metoda zwracająca dane dostawy na podstawie identyfikatora.
     *
     * @param id_dostawa identyfikator dostawy
     * @return dane dostawy
     */
    public Map<String, Object> getDostawaById(Integer id_dostawa) {
        String sql = "SELECT * FROM dostawa where id_dostawa= ?";
        return jdbcTemplate.queryForMap(sql, id_dostawa);
    }

    /**
     * Metoda tworząca nową dostawę.
     *
     * @param dostawaData dane dostawy
     */
    public void createDostawa(Map<String, Object> dostawaData) {
        String sql = "INSERT INTO dostawa (data) VALUES (?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, Date.valueOf((String) dostawaData.get("data")));
            return ps;
        });
    }

    /**
     * Metoda aktualizująca dane dostawy.
     *
     * @param dostawaData dane dostawy
     */
    public void updateDostawa(Map<String, Object> dostawaData) {
        String sql = "UPDATE dostawa SET data = ? WHERE id_dostawa = ?";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, Date.valueOf((String) dostawaData.get("data")));
            ps.setInt(2, (Integer) dostawaData.get("id_dostawa"));
            return ps;
        });
    }

    /**
     * Metoda usuwająca dostawę na podstawie identyfikatora.
     *
     * @param id_dostawa identyfikator dostawy
     */
    public void deleteDostawa(Integer id_dostawa) {
        String sql = "DELETE FROM dostawa WHERE id_dostawa = ?";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id_dostawa);
            return ps;
        });
    }
}