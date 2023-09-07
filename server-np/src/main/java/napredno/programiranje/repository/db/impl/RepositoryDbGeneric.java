package napredno.programiranje.repository.db.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

import napredno.programiranje.domain.GenericEntity;
import napredno.programiranje.repository.db.DbConnectionFactory;
import napredno.programiranje.repository.db.DbRepository;

public class RepositoryDbGeneric implements DbRepository<GenericEntity, Object>{

	@Override
    public long add(GenericEntity param) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder();
            
            sb.append("INSERT INTO ")
                    .append(param.getTableName())
                    .append("(")
                    .append(param.getInsertColumns())
                    .append(")")
                    .append(" VALUES (")
                    .append(param.getInsertValues())
                    .append(")");
            String sql = sb.toString();
//            System.out.println(sql);
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rsKeys = statement.getGeneratedKeys();
            long id = -1;
            if (rsKeys.next()) {
                id = rsKeys.getLong(1);
                param.setId(id);
            }
            rsKeys.close();
            statement.close();
            return id;
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public List<GenericEntity> getAll(GenericEntity param) throws Exception {
        List<GenericEntity> generics = new ArrayList<>();
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        Statement statement = connection.createStatement();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ").append(param.getTableName()).append(param.getJoinText());
        
        String sql = sb.toString();
//        System.out.println(sql);
        ResultSet rs = statement.executeQuery(sql);
        int i = 0;
        while (rs.next()) {
//            System.out.println("int i:" + ++i);
            generics.add(param.getEntity(rs));
        }
//        System.out.println("repository.db.impl.DbRepositoryGeneric.getAll() list:" + generics);
        statement.close();
        return generics;
    }

    @Override
    public int edit(GenericEntity param) throws Exception {
        try {

            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder();
            
            sb.append("UPDATE ").append(param.getTableName()).append(" SET ").append(param.getUpdateValues()).append(" WHERE ").append(param.getID());
            String sql = sb.toString();
//            System.out.println(sql);
            int number = statement.executeUpdate(sql);

            statement.close();
            return number;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Update item DB error: \n" + ex.getMessage());
        }
    }

    @Override
    public int delete(GenericEntity param) throws Exception {
        try {

            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder();
            
            sb.append("DELETE FROM ").append(param.getTableName()).append(" WHERE ").append(param.getID());
            String sql = sb.toString();
//            System.out.println(sql);
            int number = statement.executeUpdate(sql);

            statement.close();
            return number;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Delete item DB error: \n" + ex.getMessage());
        }
    }

    @Override
    public GenericEntity getOne(GenericEntity param) throws Exception {
        GenericEntity genericEntity = null;

        Connection connection = DbConnectionFactory.getInstance().getConnection();
        Statement statement = connection.createStatement();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ").append(param.getTableName()).append(param.getJoinText()).append(" WHERE ").append(param.getID());
        String sql = sb.toString();
//        System.out.println(sql);
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            genericEntity = param.getEntity(rs);
        }
        statement.close();

        return genericEntity;
    }

    @Override
    public List<GenericEntity> getAllByCriteria(GenericEntity param, Object criteria) throws Exception {
        List<GenericEntity> generics = new ArrayList<>();
        Pair<String, String> p = criteria instanceof Pair ? (Pair) criteria : new Pair<String,String>("id","-1");
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        Statement statement = connection.createStatement();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ").append(param.getTableName())
                .append(param.getJoinText()).append(" WHERE ")
                .append(p.getKey()).append("=").append(p.getValue());
        String sql = sb.toString();
//        System.out.println("repository.db.impl.DbRepositoryGeneric.getAllByCriteria(), sql je:" + sql);
        ResultSet rs = statement.executeQuery(sql);
        int i = 0;
        while (rs.next()) {
            System.out.println("int i:" + ++i);
            generics.add(param.getEntity(rs));
        }
//        System.out.println("repository.db.impl.DbRepositoryGeneric.getAllByCriteria(), lista je:" + generics);
        statement.close();
        return generics;
    }
}
