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
        // TODO
    }

    @Override
    public void update(Author author) {
        // TODO
    }

    @Override
    public Author get(int id) {
        // TODO
        return null;
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
