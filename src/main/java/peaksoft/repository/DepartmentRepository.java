package peaksoft.repository;

import peaksoft.entities.Department;

import java.util.List;

public interface DepartmentRepository {
    List<Department> findAll(Long hospitalId);

    void save(Department newDepartment);

    Department getById(Long departmentId);

    void update(Long departmentId,Department department);


    void deleteById(Long id, Long hospitalId);
}
