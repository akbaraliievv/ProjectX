package peaksoft.service;

import peaksoft.entities.Department;
import peaksoft.entities.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> findAll(Long id);

    void save(Long hospitalId, Doctor doctor);

    Doctor getById(Long doctorId);

    void update(Long doctorId,Doctor newDoctor);

    void assignDoctor(Long departmentId, Long doctorId);

    void deleteById(Long id);

}
