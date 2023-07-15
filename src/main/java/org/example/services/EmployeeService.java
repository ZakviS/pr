package org.example.services;

import org.aspectj.apache.bcel.classfile.Module;
import org.example.Entity.*;
import org.example.Repository.*;
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
    @Autowired
    public EmployeeRepo employeeRepo;
    @Autowired
    public SalaryRepo salaryRepo;
    @Autowired
    public AllowanceRepo allowanceRepo;
    @Autowired
    public PremiumRepo premiumRepo;

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



        public boolean isPositionExist(String name){
            Position positions = positionRepo.findByName(name);
            if(positions == null){
                return false;
            } else return true;
        }

        public boolean isEmployeeExist(String surname){
            Employee employee = employeeRepo.findBySurname(surname);
            if(employee == null){
                return false;
            } else return true;
        }

        public void saveEmployee(Employee employee,Salary salary,String nameOfPosition){
            if(isPositionExist(nameOfPosition)){
                Position positions = positionRepo.findByName(nameOfPosition);
                salary.setEmployee(employee);
                employee.setPosition(positions);
                employeeRepo.save(employee);
                salaryRepo.save(salary);
            } else {
                Position position = Position.builder()
                        .name(nameOfPosition)
                        .build();
                salary.setEmployee(employee);
                employee.setPosition(position);
                positionRepo.save(position);
                employeeRepo.save(employee);
                salaryRepo.save(salary);
            }
        }

    public void saveEmployee(Employee employee,Position position){

            employee.setPosition(position);
            positionRepo.save(position);
            employeeRepo.save(employee);
    }

    public void saveEmployee(Employee employee, Salary salary, Premium premium, String nameOfPosition){

        if(isPositionExist(nameOfPosition)){
            Position positions = positionRepo.findByName(nameOfPosition);
            salary.setEmployee(employee);
            premium.setEmployee(employee);
            employee.setPosition(positions);
            premiumRepo.save(premium);
            employeeRepo.save(employee);
            salaryRepo.save(salary);
        } else {
            Position position = Position.builder()
                    .name(nameOfPosition)
                    .build();
            salary.setEmployee(employee);
            employee.setPosition(position);
            positionRepo.save(position);
            employeeRepo.save(employee);
            salaryRepo.save(salary);
        }
    }

    public void saveEmployee(Employee employee, Salary salary, Allowance allowance, String nameOfPosition){

        if(isPositionExist(nameOfPosition)){
            Position positions = positionRepo.findByName(nameOfPosition);
            salary.setEmployee(employee);
            allowance.setEmployee(employee);
            employee.setPosition(positions);
            allowanceRepo.save(allowance);
            employeeRepo.save(employee);
            salaryRepo.save(salary);
        } else {
            Position position = Position.builder()
                    .name(nameOfPosition)
                    .build();
            salary.setEmployee(employee);
            employee.setPosition(position);
            positionRepo.save(position);
            employeeRepo.save(employee);
            salaryRepo.save(salary);
        }
    }

    public void saveEmployee(Employee employee,Salary salary,Allowance allowance, Premium premium,String nameOfPosition){

        if(isPositionExist(nameOfPosition)){
            Position positions = positionRepo.findByName(nameOfPosition);
            salary.setEmployee(employee);
            allowance.setEmployee(employee);
            premium.setEmployee(employee);
            employee.setPosition(positions);
            allowanceRepo.save(allowance);
            premiumRepo.save(premium);
            employeeRepo.save(employee);
            salaryRepo.save(salary);
        } else {
            Position position = Position.builder()
                    .name(nameOfPosition)
                    .build();
            salary.setEmployee(employee);
            employee.setPosition(position);
            positionRepo.save(position);
            employeeRepo.save(employee);
            salaryRepo.save(salary);
        }
    }

    public void update(String surname,Salary salary,String nameOfPosition){
            Long id = employeeRepo.findBySurname(surname).getId();
            Employee employee = employeeRepo.getReferenceById(id);
            saveEmployee(employee,salary,nameOfPosition);
    }

    public void delete(Long id){
            employeeRepo.deleteById(id);
    }

}
