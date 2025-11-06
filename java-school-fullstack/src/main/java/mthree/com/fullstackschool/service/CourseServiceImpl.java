package mthree.com.fullstackschool.service;

import mthree.com.fullstackschool.dao.CourseDao;
import mthree.com.fullstackschool.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseServiceInterface {

    //YOUR CODE STARTS HERE
    private final CourseDao courseDao;

    @Autowired
    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }
    //YOUR CODE ENDS HERE

    public List<Course> getAllCourses() {
        //YOUR CODE STARTS HERE
        return courseDao.getAllCourses();
        //YOUR CODE ENDS HERE
    }

    public Course getCourseById(int id) {
        //YOUR CODE STARTS HERE
        // Spec says to create a new course with these values if not found
        try {
            return courseDao.findCourseById(id);
        } catch (DataAccessException ex) {
            Course c = new Course();
            c.setCourseName("Course Not Found");
            c.setCourseDesc("Course Not Found");
            return c;
        }
        //YOUR CODE ENDS HERE
    }

    public Course addNewCourse(Course course) {
        //YOUR CODE STARTS HERE
        // Check if any name/desc are blank/null and set spec values, dont add if so
        if (course.getCourseName() == null || course.getCourseName().isBlank()
                || course.getCourseDesc() == null || course.getCourseDesc().isBlank()) {

            course.setCourseName("Name blank, course NOT added");
            course.setCourseDesc("Description blank, course NOT added");
            return course;
        }
        // we can add if we get a course with proper values
        return courseDao.createNewCourse(course);
        //YOUR CODE ENDS HERE
    }

    public Course updateCourseData(int id, Course course) {
        //YOUR CODE STARTS HERE
        // spec says if id doesnt match, set these values and dont update
        if (id != course.getCourseId()) {
            course.setCourseName("IDs do not match, course not updated");
            course.setCourseDesc("IDs do not match, course not updated");
            return course;
        }
        courseDao.updateCourse(course);
        return course;
        //YOUR CODE ENDS HERE
    }

    public void deleteCourseById(int id) {
        //YOUR CODE STARTS HERE
        courseDao.deleteCourse(id);
        System.out.println("Course ID: " + id + " deleted");
        //YOUR CODE ENDS HERE
    }
}
