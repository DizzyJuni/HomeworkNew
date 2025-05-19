package Lesson_13.task1;
import java.sql.*;
public class Main {

    private static final String Insert_Table_Student_SQL = "Insert into student (name, age, scholarship, group_id) values (?,?,?,?)";
    private static final String Select_Table_Student_SQL = "Select * from student;";
    private static final String Select_By_ID_Age_And_Scholarship_SQL = "Select * from student where group_id = (?) and age < (?) and scholarship > (?);";
    private static final String Update_Table_Student_Group_4_SQL = "Update student set group_id = 3 where name = ?;";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");// Загрузка драйвера
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test_db", "test_user", "test_password")) {
            Statement statement = connection.createStatement();
            statement.execute("Drop table if exists student");
            statement.execute("Create table if not exists student (id bigserial primary key, name text not null, age int not null, scholarship numeric (10,2), group_id bigint) ");
            statement.execute("Drop table if exists faculty Cascade");
            statement.execute("Create table if not exists faculty(id bigserial primary key, name text)");
            statement.execute("Drop table if exists groupstudent");
            statement.execute("Create table if not exists groupstudent (id bigserial primary key, name text, faculty_id bigint)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test_db", "test_user", "test_password")) {
            PreparedStatement ps = connection.prepareStatement(Insert_Table_Student_SQL);
            ps.setString(1, "Том");
            ps.setLong(2, 21);
            ps.setDouble(3, 130.2);
            ps.setLong(4, 2);
            ps.addBatch();

            ps.setString(1, "Джери");
            ps.setLong(2, 18);
            ps.setDouble(3, 150.9);
            ps.setLong(4, 2);
            ps.addBatch();

            ps.setString(1, "Волк");
            ps.setLong(2, 34);
            ps.setDouble(3, 141.9);
            ps.setLong(4, 3);
            ps.addBatch();

            ps.setString(1, "Заяц");
            ps.setLong(2, 19);
            ps.setDouble(3, 178.4);
            ps.setLong(4, 3);
            ps.addBatch();

            ps.executeBatch();

            ResultSet rs = connection.prepareStatement(Select_Table_Student_SQL).executeQuery();
            while (rs.next()) {
                String student = rs.getString("name");
                System.out.println(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("------------");

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test_db", "test_user", "test_password")) {
            PreparedStatement ps = connection.prepareStatement(Select_By_ID_Age_And_Scholarship_SQL);
            ps.setLong(1, 3);
            ps.setLong(2, 20);
            ps.setDouble(3, 100.0);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String student = rs.getString("name");
                System.out.println(student);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test_db", "test_user", "test_password")) {
            PreparedStatement ps = connection.prepareStatement(Update_Table_Student_Group_4_SQL);
            ps.setString(1, "Джери");
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("--------------");

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test_db", "test_user", "test_password")) {
            PreparedStatement ps = connection.prepareStatement(Select_By_ID_Age_And_Scholarship_SQL);
            ps.setLong(1, 3);
            ps.setLong(2, 20);
            ps.setDouble(3, 100.0);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String student = rs.getString("name");
                System.out.println(student);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
