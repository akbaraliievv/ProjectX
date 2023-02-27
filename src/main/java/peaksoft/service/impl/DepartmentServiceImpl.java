package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entities.Department;
import peaksoft.entities.Hospital;
import peaksoft.repository.DepartmentRepository;
import peaksoft.repository.DoctorRepository;
import peaksoft.repository.HospitalRepository;
import peaksoft.service.DepartmentService;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final HospitalRepository hospitalRepository;


    @Override
    public List<Department> findAll(Long hospitalId) {
        return departmentRepository.findAll(hospitalId);
    }



    @Override
    public void save(Long hospitalId, Department department) {
        Hospital hospital = hospitalRepository.getById(hospitalId);
        for (Department dep : departmentRepository.findAll(hospital.getId())) {
            if (dep.getName().equals(department.getName())) {
            }
        }
        hospital.addDepartment(department);
        department.setHospital(hospital);
        departmentRepository.save(department);
    }


    @Override
    public Department getById(Long departmentId) {
        return departmentRepository.getById(departmentId);
    }

    @Override
    public void update(Long departmentId, Department newDepartment) {
        departmentRepository.update(departmentId,newDepartment);
    }

    @Override
    public void deleteById(Long id, Long hospitalId) {
        departmentRepository.deleteById(id, hospitalId);
    }






}

