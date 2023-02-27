package peaksoft.service;

import peaksoft.entities.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> findAll(Long hospitalId);

    void save(Long id, Department department);

    Department getById(Long departmentId);

    void update(Long departmentId,Department newDepartment);


    void deleteById(Long id,Long hospitalId);
}
