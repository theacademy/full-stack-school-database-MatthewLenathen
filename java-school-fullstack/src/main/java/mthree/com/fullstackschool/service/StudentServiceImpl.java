package mthree.com.fullstackschool.service;

import mthree.com.fullstackschool.dao.StudentDao;
import mthree.com.fullstackschool.model.Course;
import mthree.com.fullstackschool.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentServiceInterface {

    //YOUR CODE STARTS HERE
    private final StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }
    //YOUR CODE ENDS HERE

    public List<Student> getAllStudents() {
        //YOUR CODE STARTS HERE
        return studentDao.getAllStudents();
        //YOUR CODE ENDS HERE
    }

    public Student getStudentById(int id) {
        //YOUR CODE STARTS HERE
        try {
            return studentDao.findStudentById(id);
        } catch (DataAccessException ex) {
            Student s = new Student();
            s.setStudentFirstName("Student Not Found");
            s.setStudentLastName("Student Not Found");
            return s;
        }
        //YOUR CODE ENDS HERE
    }

    public Student addNewStudent(Student student) {
        //YOUR CODE STARTS HERE
        // similar to courseService, if blank or null set to the spec values
        if (student.getStudentFirstName() == null || student.getStudentFirstName().isBlank()
                || student.getStudentLastName() == null || student.getStudentLastName().isBlank()) {

            student.setStudentFirstName("First Name blank, student NOT added");
            student.setStudentLastName("Last Name blank, student NOT added");
            return student;
        }
        // only create if it passes the check
        return studentDao.createNewStudent(student);
        //YOUR CODE ENDS HERE
    }

    public Student updateStudentData(int id, Student student) {
        //YOUR CODE STARTS HERE
        // same as above but just id
        if (id != student.getStudentId()) {
            student.setStudentFirstName("IDs do not match, student not updated");
            student.setStudentLastName("IDs do not match, student not updated");
            return student;
        }
        studentDao.updateStudent(student);
        return student;
        //YOUR CODE ENDS HERE
    }

    public void deleteStudentById(int id) {
        //YOUR CODE STARTS HERE
        studentDao.deleteStudent(id);
        //YOUR CODE ENDS HERE
    }

    public void deleteStudentFromCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE
        Student s = getStudentById(studentId);

        if (s.getStudentFirstName().equals("Student Not Found")) {
            System.out.println("Student not found");
        } else {
            try {
                studentDao.deleteStudentFromCourse(studentId, courseId);
                System.out.println("Student: " + studentId + " deleted from course: " + courseId);
            } catch (DataAccessException ex) {
                System.out.println("Course not found");
            }
        }
        //YOUR CODE ENDS HERE
    }

    public void addStudentToCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE
        Student s = getStudentById(studentId);

        if (s.getStudentFirstName().equals("Student Not Found")) {
            System.out.println("Student not found");
        } else {
            try {
                studentDao.addStudentToCourse(studentId, courseId);
                System.out.println("Student: " + studentId + " added to course: " + courseId);
            } catch (DataAccessException ex) {
                // can differentiate between no course/already enrolled by checking the exception msg
                String msg = ex.getMessage().toLowerCase();
                if (msg.contains("foreign key")) {
                    System.out.println("Course not found");
                } else {
                    System.out.println("Student: " + studentId + " already enrolled in course: " + courseId);
                }
            }
        }
        //YOUR CODE ENDS HERE
    }
}
