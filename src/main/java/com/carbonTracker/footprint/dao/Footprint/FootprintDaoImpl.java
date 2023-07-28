package com.carbonTracker.footprint.dao.Footprint;

import com.carbonTracker.footprint.model.footprint.Footprint;
import com.carbonTracker.footprint.model.footprint.FootprintMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FootprintDaoImpl implements FootprintDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FootprintDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Footprint> getUserFootprint(int id){

        String sql = """
                 SElECT * FROM user u
                    LEFT JOIN vehicle v
                    ON u.id = v.userId
                    LEFT JOIN home h
                    ON h.userID = u.id
                    LEFT JOIN offsetters o
                    WHERE o.id = u.id;
                """;
        return (List<Footprint>) jdbcTemplate.query(sql, new FootprintMapper(), id );
    }
}
