package dbrider.spring;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class Table2Repository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Table2Repository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Map<String, Object> findLast(String type) {
        return namedParameterJdbcTemplate.queryForMap(
                """
                    SELECT *
                    FROM table2 t
                    WHERE t.type = :type
                    ORDER BY t.row_created_on DESC
                    LIMIT 1
                """, Map.of("type", type)
        );
    }

    public void save(Record aRecord) {
        namedParameterJdbcTemplate.update(
                """
                    INSERT INTO table2 (id, type)
                    VALUES (:id, :type)
                """,
                Map.of("id", aRecord.id(), "type", aRecord.type())
        );
    }

}
