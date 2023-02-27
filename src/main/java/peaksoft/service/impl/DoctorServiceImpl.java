package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entities.Department;
import peaksoft.entities.Doctor;
import peaksoft.entities.Hospital;
import peaksoft.repository.DoctorRepository;
import peaksoft.repository.HospitalRepository;
import peaksoft.service.DoctorService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;
    @Override
    public List<Doctor> findAll(Long id) {
        return doctorRepository.getAllDoctors(id);
    }

    @Override
    public void save(Long hospitalId, Doctor doctor) {
        Hospital hospital = hospitalRepository.getById(hospitalId);
        hospital.addDoctor(doctor);
        doctor.setHospital(hospital);
        doctorRepository.save(doctor);
    }

    @Override
    public Doctor getById(Long doctorId) {
        return doctorRepository.getById(doctorId);
    }

    @Override
    public void update(Long doctorId,Doctor newDoctor) {
        doctorRepository.update(doctorId, newDoctor);
    }

    @Override
    public void assignDoctor(Long departmentId, Long doctorId) {
        doctorRepository.assignDoctor(departmentId, doctorId);
    }

    @Override
    public void deleteById(Long id) {
        doctorRepository.deleteById(id);
    }
}


