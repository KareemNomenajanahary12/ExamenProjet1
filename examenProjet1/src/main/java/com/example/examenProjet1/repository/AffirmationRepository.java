package com.example.examenProjet1.repository;

import com.example.examenProjet1.model.Affirmation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AffirmationRepository {

    private final JdbcTemplate jdbcTemplate;

    public AffirmationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static class AffirmationRowMapper implements RowMapper<Affirmation> {
        @Override
        public Affirmation mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Affirmation(
                    rs.getInt("id"),
                    rs.getString("text"),
                    rs.getBoolean("value")
            );
        }
    }

    public List<Affirmation> findAll() {
        String sql = "SELECT * FROM affirmation";
        return jdbcTemplate.query(sql, new AffirmationRowMapper());
    }


    public void save(Affirmation affirmation) {
        String sql = "INSERT INTO affirmation (id, text, value) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, affirmation.getId(), affirmation.getText(), affirmation.getValue());
    }


    public void update(Affirmation affirmation) {
        String sql = "UPDATE affirmation SET text = ?, value = ? WHERE id = ?";
        jdbcTemplate.update(sql, affirmation.getText(), affirmation.getValue(), affirmation.getId());
    }


    public void deleteById(int id) {
        String sql = "DELETE FROM affirmation WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
