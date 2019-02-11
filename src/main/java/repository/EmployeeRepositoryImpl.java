package repository;

import domain.entities.Employee;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final EntityManager entityManager;

    @Inject
    public EmployeeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public Employee save(Employee entity) {

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
        return entity;
    }

    public Employee findById(String id) {
        this.entityManager.getTransaction().begin();
        Employee entity = this.entityManager
                .createQuery("SELECT e FROM employee e WHERE e.id = " + id, Employee.class)
                .getSingleResult();
        this.entityManager.getTransaction().commit();

        return entity;
    }

    public List<Employee> findAll() {
        this.entityManager.getTransaction().begin();
        List<Employee> products = this.entityManager
                .createQuery("SELECT e FROM employee e", Employee.class)
                .getResultList();
        this.entityManager.getTransaction().commit();

        return products;
    }

    @Override
    public void remove(String id) {
        this.entityManager.getTransaction().begin();
        this.entityManager.createQuery("DELETE FROM employee e WHERE e.id = :id")
                .setParameter("id", id).executeUpdate();
        this.entityManager.getTransaction().commit();
    }
}
