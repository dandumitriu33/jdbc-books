package com.codecool.books.model;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoJDBC implements AuthorDao {
    private DataSource dataSource;

    public AuthorDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Author author) {
        Connection conn = null;
        Statement stmt = null;
        final int TEST_ID = 51;

        try{
            conn = dataSource.getConnection();
            stmt = conn.createStatement();

            String sql = "INSERT INTO author (first_name, last_name, birth_date) VALUES (\'" + author.getFirstName() + "\', \'" +author.getLastName()+ "\', \'" + (Date) author.getBirthDate()+ "\')";
            stmt.executeUpdate(sql);


            stmt.close();
            conn.close();
        }
        catch (SQLException se) {
            se.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }

    }

    @Override
    public void update(Author author) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement("UPDATE author SET first_name=?, last_name=?, birth_date=? WHERE id=?");
            pstmt.setString(1, author.getFirstName());
            pstmt.setString(2, author.getLastName());
            pstmt.setDate(3, author.getBirthDate());
            pstmt.setInt(4, author.getId());
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        }
        catch (SQLException se) {
            se.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                if(pstmt!=null)
                    pstmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }

    }

    @Override
    public Author get(int id) {
        Connection conn = null;
        Statement stmt = null;
        Author temp = null;

        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();

            String sql = "SELECT * FROM author";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                int idFromDB = rs.getInt("id");

                if (idFromDB==id) {
                    String first = rs.getString("first_name");
                    String last = rs.getString("last_name");
                    Date birthDate = rs.getDate("birth_date");
                    temp = new Author(first, last, birthDate);
                    temp.setId(idFromDB);
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }

        return temp;
    }

    @Override
    public List<Author> getAll() throws SQLException {
        List<Author> result = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();

            String sql = "SELECT * FROM author";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                int id = rs.getInt("id");
                String first = rs.getString("first_name");
                String last = rs.getString("last_name");
                Date birthDate = rs.getDate("birth_date");

                Author temp = new Author(first, last, birthDate);
                temp.setId(id);
                result.add(temp);
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return result;
    }
}
