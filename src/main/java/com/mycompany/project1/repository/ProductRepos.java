package com.mycompany.project1.repository;

import com.mycompany.project1.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductRepos {
    private final JdbcTemplate jdbcTemplate;

    public ProductRepos(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    private static final String defineType = "select type from products where id=?";
  //  private static final String getAllByType="select id from products where type=?";


    public String getType(Long id) {
        String type = jdbcTemplate.query(defineType, (rs, rowNum) -> new String((rs.getString("type"))), id).get(0);
        return type;
    }


}
