package org.example.services;

import org.aspectj.apache.bcel.classfile.Module;
import org.example.Entity.Employee;
import org.example.Entity.Position;
import org.example.Entity.Salary;
import org.example.Repository.PositionRepo;
//import org.example.Util.HibernateUtil;
//import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    public PositionRepo positionRepo;

//    public boolean isEmployeeExist(Employee employee){
//
//        var sessionFactory = HibernateUtil.buildSessionFactory();
//        var session = sessionFactory.openSession();
//        {
//            String queryString = "from Employee e where e.name" + "= :name" + " AND " + "e.surname" + "= :surname" + " and " + "e.phoneNumber" + "= :number" + " and " + "e.email" + "= :email";
//            Query queryObject = session.createQuery(queryString);
//            queryObject.setParameter("surname", employee.getSurname());
//            queryObject.setParameter("name", employee.getName());
//            queryObject.setParameter("number", employee.getPhoneNumber());
//            queryObject.setParameter("email", employee.getEmail());
//            List<Position> list = queryObject.list();
//            if(list.isEmpty()) return false;
//            else return true;
//
//        }
//    }
//
//    public boolean isExistPosition(String nameOfPosition){
//
//        var sessionFactory = HibernateUtil.buildSessionFactory();
//        var session = sessionFactory.openSession();
//        {
//            String queryString = "from Position where name" + "= :value";
//            Query queryObject = session.createQuery(queryString);
//            queryObject.setParameter("value", nameOfPosition);
//            List<Position> list = queryObject.list();
//            if(list.isEmpty()) return false;
//            else return true;
//        }
//    }
//
//    public Long idOfPosition(String nameOfPosition){
//        var sessionFactory = HibernateUtil.buildSessionFactory();
//        var session = sessionFactory.openSession();
//        {
//            String queryString = "from Position where name" + "= :value";
//            Query queryObject = session.createQuery(queryString);
//            queryObject.setParameter("value", nameOfPosition);
//            List<Position> list = queryObject.list();
//            Long id = list.get(0).getId();
//            return id;
//        }
//    }
//
//
//    public void saveEmployee(Employee employee, Salary salary, String nameOfPosition) {
//
//        try (var sessionFactory = HibernateUtil.buildSessionFactory();
//             var session = sessionFactory.openSession()) {
//            session.beginTransaction();
//
//
//            System.out.println(isEmployeeExist(employee));
//
//            if(isExistPosition(nameOfPosition)){
//                var position = session.get(Position.class,idOfPosition(nameOfPosition));
//                position.addEmployee(employee);
//                salary.setEmployee(employee);
//                session.save(salary);
//                session.save(employee);
//
//            } else {
//                Position position = Position.builder()
//                        .name(nameOfPosition)
//                        .build();
//                session.save(position);
//                position.addEmployee(employee);
//                salary.setEmployee(employee);
//                session.save(salary);
//                session.saveOrUpdate(employee);
//            }
//
//
//            session.getTransaction().commit();
//        }
//    }


        public boolean isPositionExist(){
            return true;
        }

        public void saveEmployee(){
            Position positions = positionRepo.getById(1L);
            //List<Position> positions = positionRepo.getById(1L);
            System.out.println(positions);
        }

}
