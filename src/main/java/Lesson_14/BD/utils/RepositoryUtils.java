package Lesson_14.BD.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RepositoryUtils {
    public static Integer getNullableInteger (ResultSet rs, String colName)  throws SQLException {
        int colValue = rs.getInt(colName);
        return rs.wasNull() ? null : colValue;
    }

    public static Long getNullableLong (ResultSet rs, String colName) throws SQLException{
        long colValue = rs.getLong(colName);
        return rs.wasNull() ? null : colValue;
    }

    public static Float getNullableFloat (ResultSet rs, String colName) throws SQLException{
        float colValue = rs.getFloat(colName);
        return rs.wasNull() ? null : colValue;
    }
}
