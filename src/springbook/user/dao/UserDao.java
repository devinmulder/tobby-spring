package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import springbook.user.domain.User;

public class UserDao {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.jdbcContext = new JdbcContext();
        this.jdbcContext.setDataSource(dataSource);

        this.dataSource = dataSource;
    }

    private JdbcContext jdbcContext;

    public void add(final User user) throws SQLException {
        this.jdbcContext.executeSql_paramBind("insert into users(id, name, password) values(?,?,?)", user);
    }

    public User get(String id) throws SQLException {
        return this.jdbcContext.executeQuery_getUser("select * from users where id = ?", id);
    }

    public void deleteAll() throws SQLException {
        this.jdbcContext.executeSql("delete from users");
    }

    public int getCount() throws SQLException  {
        return this.jdbcContext.executeQuery_getCount("select count(*) from users");
    }
}
