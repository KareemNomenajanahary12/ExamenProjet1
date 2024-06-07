package com.example.examenProjet1.service;

import com.example.examenProjet1.model.Affirmation;
import com.example.examenProjet1.repository.AffirmationRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Service
public class AffirmationService {

    private final JdbcTemplate jdbcTemplate;

    public AffirmationService(JdbcTemplate jdbcTemplate) {
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

    public List<Affirmation> getAllAffirmations() {
        String sql = "SELECT * FROM affirmation";
        return jdbcTemplate.query(sql, new AffirmationRowMapper());
    }

    public void saveAffirmation(Affirmation affirmation) {
        String sql = "INSERT INTO affirmation (text, value) VALUES (?, ?)";
        jdbcTemplate.update(sql, affirmation.getText(), affirmation.getValue());
    }

    public void updateAffirmation(Affirmation affirmation) {
        String sql = "UPDATE affirmation SET text = ?, value = ? WHERE id = ?";
        jdbcTemplate.update(sql, affirmation.getText(), affirmation.getValue(), affirmation.getId());
    }

    public void deleteAffirmationById(int id) {
        String sql = "DELETE FROM affirmation WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public String evaluateAffirmation(String text) {
        List<Affirmation> affirmations = getAllAffirmations();

        for (Affirmation affirmation : affirmations) {
            if (affirmation.getText().equalsIgnoreCase(text)) {
                return affirmation.getValue() != null ? affirmation.getValue().toString() : "je ne sais pas";
            }
        }
        return "je ne sais pas";
    }

    public String evaluateAnd(Boolean a, Boolean b) {
        return (a && b) ? "true" : "false";
    }

    public String evaluateOr(Boolean a, Boolean b) {
        return (a || b) ? "true" : "false";
    }

    public String evaluateTherefore(Boolean a, Boolean b) {
        return (!a || b) ? "true" : "false";
    }
}
