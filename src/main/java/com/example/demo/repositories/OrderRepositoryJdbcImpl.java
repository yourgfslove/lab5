package com.example.demo.repositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.EmptyResultDataAccessException;
import com.example.demo.models.Order;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Profile;
import java.util.List;
import java.util.Optional;
@Repository
@Profile("jdbc") // Активируется с профилем "jdbc"
public class OrderRepositoryJdbcImpl implements OrderRepository {
    private final JdbcTemplate jdbcTemplate;

    public OrderRepositoryJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Order> findByCode(String code) {
        String sql = "SELECT * FROM orders WHERE code = ?";
        try {
            Order order = jdbcTemplate.queryForObject(sql, 
                (rs, rowNum) -> new Order(
                    rs.getString("code"),
                    rs.getString("name"),
                    rs.getString("description")
                ),
                code);
            return Optional.ofNullable(order);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Order add(Order order) {
        String sql = "INSERT INTO orders (code, name, description) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, order.getCode(), order.getName(), order.getDescription());
        return order;
    }

    @Override
    public List<Order> findAll() {
        String sql = "SELECT * FROM orders";
        return jdbcTemplate.query(sql, 
            (rs, rowNum) -> new Order(
                rs.getString("code"),
                rs.getString("name"),
                rs.getString("description")
            ));
    }

    @Override
    public boolean deleteByCode(String code) {
        String sql = "DELETE FROM orders WHERE code = ?";
        return jdbcTemplate.update(sql, code) > 0;
    }
}