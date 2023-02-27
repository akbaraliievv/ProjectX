package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entities.Department;
import peaksoft.repository.DepartmentRepository;

import java.util.List;
@RequiredArgsConstructor
@Transactional
@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Department> findAll(Long hospitalId) {
        return entityManager.createQuery("select d from Department d join d.hospital h where h.id = :id",
                Department.class).setParameter("id",hospitalId).getResultList();
    }


    @Override
    @Transactional
    public void save(Department newDepartment) {
        entityManager.merge(newDepartment);
    }

    @Override
    public Department getById(Long departmentId) {
        return entityManager.find(Department.class, departmentId);
    }

    @Override
    public void update(Long departmentId, Department department) {
        Department department1 = entityManager.find(Department.class, departmentId);
        department1.setName(department.getName());
        entityManager.merge(department);
    }

    @Override
    public void deleteById(Long id, Long hospitalId) {
        Department department = entityManager.find(Department.class, id);
        department.setHospital(null);
        department.setDoctors(null);
        entityManager.remove(department);


    }
}
