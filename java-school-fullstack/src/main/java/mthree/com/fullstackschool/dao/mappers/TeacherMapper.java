package mthree.com.fullstackschool.dao.mappers;

import mthree.com.fullstackschool.model.Teacher;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherMapper implements RowMapper<Teacher> {
    @Override
    public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
        //YOUR CODE STARTS HERE
        Teacher tch = new Teacher();
        tch.setTeacherId(rs.getInt("tid"));
        tch.setDept(rs.getString("dept"));
        tch.setTeacherFName(rs.getString("tFName"));
        tch.setTeacherLName(rs.getString("tLName"));
        return tch;
        //YOUR CODE ENDS HERE
    }
}
