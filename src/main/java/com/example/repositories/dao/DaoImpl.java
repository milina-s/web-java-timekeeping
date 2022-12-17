package com.example.repositories.dao;


import com.example.exeptions.SQLOperationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class DaoImpl<T> implements Dao<T> {
    private static final Logger logger = LogManager.getLogger(DaoImpl.class);
    protected final Connection connection;
    protected final String tableName;

    public DaoImpl(Connection connection, String tableName) {
        this.connection = connection;
        this.tableName = tableName;
    }

    public abstract T buildItem(ResultSet rs) throws SQLException;

    @Override
    public List<T> findAll() {
        List<T> items = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM " + tableName;
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                T item = buildItem(rs);
                items.add(item);
            }
            logger.info("Operation 'findAll' for table=" + tableName + " was successful.");
        } catch (SQLException e) {
            throw new SQLOperationException("Couldn't find items in table=" + tableName);
        }
        return items;
    }

    @Override
    public T findById(Long id) {
        T item = null;
        String sql = "SELECT * FROM " + tableName + " WHERE id = " + id;
        List<T> res = findAllBy(sql);
        if (res.size() < 1)
            throw new SQLOperationException("Couldn't find item with id=" + id + " in table=" + tableName);
        item = res.get(0);
        if (res.size() > 1)
            logger.info("There are many items with id=" + id + " in table=" + tableName + ". The first one has been selected.");
        if (item == null)
            throw new SQLOperationException("Couldn't find item with id=" + id + " in table=" + tableName);
        return item;
    }

//    public Optional<T> fidById(Long id) {
//        T item = null;
//        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
//        try {
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setLong(1, id);
//            ResultSet rs = statement.executeQuery(sql);
//            while (rs.next()) {
//                item = this.buildItem(rs);
//            }
//            rs.close();
//            statement.close();
//            logger.info("Operation 'findById' with id=" + id + " for table=" + tableName + " was successful.");
//        } catch (SQLException e) {
//            throw new SQLOperationException("Couldn't find item with id=" + id + " in table=" + tableName);
//        }
//        return Optional.ofNullable(item);
//    }

    protected List<T> findAllBy(String sql) {
        List<T> items = new ArrayList<>();
        try {
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                T item = buildItem(rs);
                items.add(item);
            }
            logger.info("Operation 'findBy' for table=" + tableName + " was successful.");
        } catch (SQLException e) {
            throw new SQLOperationException("Couldn't findBy items in table=" + tableName);
        }
        return items;
    }

    protected Optional<T> findBy(String sql) {
        T item = null;
        try {
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                item = buildItem(rs);
            }
            logger.info("Operation 'findBy' for table=" + tableName + " was successful.");
        } catch (SQLException e) {
            throw new SQLOperationException("Couldn't findBy items in table=" + tableName);
        }
        return Optional.ofNullable(item);
    }

    public abstract String getInsertSQLQuery();

    public abstract void updateInsertSQLQuery(PreparedStatement ps, T item) throws SQLException;

    @Override
    public void create(T t) {
        String sql = getInsertSQLQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            updateInsertSQLQuery(statement, t);
            statement.execute();
            logger.info("Operation 'create' for table=" + tableName + " was successful.");
        } catch (SQLException e) {
            throw new SQLOperationException("Couldn't create item in table=" + tableName);
        }
    }

    public abstract String getUpdateSQLQuery();

    public abstract void updateUpdateSQLQuery(PreparedStatement ps, T newItem, T oldItem) throws SQLException;

    @Override
    public void update(Long id, T newItem) {
        String sql = getUpdateSQLQuery();
        T oldItem = findById(id);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            updateUpdateSQLQuery(statement, newItem, oldItem);
            statement.executeUpdate();
            logger.info("Operation 'update' with id=" + id + " for table=" + tableName + " was successful.");
        } catch (SQLException e) {
            throw new SQLOperationException("Couldn't update item with id=" + id + " in table=" + tableName);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM " + tableName + " WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new SQLOperationException("Couldn't delete item with id=" + id + " in table=" + tableName);
        }
    }

}
