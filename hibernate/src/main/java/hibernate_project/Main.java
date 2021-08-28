package hibernate_project;

import hibernate_project.db.HibernateUTIL;
import hibernate_project.entity.Student;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {


        Session session = HibernateUTIL.getSessionFactory().openSession();
        session.beginTransaction();

        logger.debug("start");

        Student student = new Student();
        student.setFirstName("Name");
        student.setLastName("Last name 1");
        student.setEmail("email 1");

        Student student1 = new Student();
        student1.setFirstName("Name1");
        student1.setLastName("Last name 2");
        student1.setEmail("email 2");

        session.save(student);
        session.save(student1);

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> rootEntity = cq.from(Student.class);
        CriteriaQuery<Student> all = cq.select(rootEntity);

        TypedQuery<Student> allQuery = session.createQuery(all);
        List<Student> allStudents = allQuery.getResultList();
        for (Student st : allStudents) {
            System.out.println(String.format("Student [id=%d, name=%s, last name=%s, email=%s\n",
                    st.getId(), st.getFirstName(), st.getLastName(), st.getEmail()));
        }

        session.getTransaction().commit();
        HibernateUTIL.shutdown();

    }
}
