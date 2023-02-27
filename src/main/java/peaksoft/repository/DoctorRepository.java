package peaksoft.repository;

import peaksoft.entities.Doctor;
import peaksoft.entities.Hospital;

import java.util.List;

public interface DoctorRepository {
    List<Doctor> getAllDoctors(Long id);

    void save(Doctor newDoctor);

    Doctor getById(Long doctorId);

    void update(Long doctorId,Doctor newDoctor);

    void assignDoctor(Long departmentId, Long doctorId);

    void deleteById(Long id);
}
