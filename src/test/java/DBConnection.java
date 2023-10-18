import java.sql.*;

public class DBConnection {
    private Connection connect = null;
    private Statement statement = null;

    public void connect() {
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=Test123!&useSSL=" +
                    "true&serverTimezone=GMT");
            statement = connect.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet select(String columns, String tableName) {
        try {
            return statement.executeQuery(String.format("select %s from %s", columns, tableName));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int executeQuery(String query) {
        try {
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void writeSelectResultSet(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            System.out.println("-------SELECT------");
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String city_id = resultSet.getString("city_id");
            System.out.println("ID: " + id);
            System.out.println("NAME: " + name);
            System.out.println("CITY_ID: " + city_id);
        }
    }

    public static void printRowEffected(String queryType, int rowEffected) {
        System.out.println(String.format("-------%s------", queryType));
        System.out.println("Row effected: " + rowEffected);
    }

    public void close() {
        try {
            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception ignored) {
        }
    }
}
