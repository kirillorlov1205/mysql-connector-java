import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBTest {

    private DBConnection dbConnection = new DBConnection();

    @BeforeClass
    public void connect() {
        dbConnection.connect();
    }

    @AfterClass
    public void closeConnect() {
        dbConnection.close();
    }

    @Test
    public void verifyInsertQueryTest() {
        int resultSet = dbConnection.executeQuery("insert into students (name, city_id) values ('Donny', 2)");
        DBConnection.printRowEffected("INSERT", resultSet);
    }

    @Test
    public void verifySelectQueryTest() {
        try {
            ResultSet resultSet = dbConnection.select("*", "students");
            DBConnection.writeSelectResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void verifyDeleteQueryTest() {
        int resultSet = dbConnection.executeQuery("delete from students where name = 'Donny'");
        DBConnection.printRowEffected("DELETE", resultSet);
    }

    @Test
    public void verifyUpdateQueryTest() {
        int resultSet = dbConnection.executeQuery("update students set name = 'Mario' where id = 1");
        DBConnection.printRowEffected("UPDATE", resultSet);
    }
}
