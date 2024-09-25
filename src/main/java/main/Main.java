package main;

import beans.Department;
import beans.Employee;
import hibernateutils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        System.out.println(session);

        try {

            Department department1 = new Department();
            department1.setName("AIIT");

            Department department2 = new Department();
            department2.setName("MBBA");


            Employee emp1 = new Employee();
            emp1.setAddr("Danapur");
            emp1.setName("Keshri1");
            List<Department> emp1Depart = new ArrayList<>();
            emp1Depart.add(department1);
            emp1Depart.add(department2);
            emp1.setDepartment(emp1Depart);

            Employee emp2 = new Employee();
            emp2.setName("Keshri2");
            emp2.setAddr("Patna");
            List<Department> emp2Depart = new ArrayList<>();
            emp2Depart.add(department2);
            emp2.setDepartment(emp2Depart);



            /*session.persist(emp1);
            session.persist(emp2);
*/


            Employee employee=(Employee) session.get(Employee.class,Integer.parseInt("1"));
            List<Department> empDepart=employee.getDepartment();

            for (Department dep:
                empDepart ) {
                System.out.println(dep.getName());
            }


            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }


    }
}
