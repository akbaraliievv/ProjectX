package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entities.Appointment;
import peaksoft.entities.Hospital;
import peaksoft.entities.Patient;
import peaksoft.repository.AppointmentRepository;
import peaksoft.repository.HospitalRepository;
import peaksoft.repository.PatientRepository;
import peaksoft.service.PatientService;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;
    @Override
    public List<Patient> findAll(Long id) {
        return patientRepository.findAll(id);
    }

    @Override
    public Patient getById(Long patientId) {
        return patientRepository.getById(patientId);
    }

    @Override
    public void update(Long patientId,Patient newPatient) {
        patientRepository.update(patientId,newPatient);
    }

    @Override
    public void save(Long hospitalId, Patient patient) {
        Hospital hospitalById = hospitalRepository.getById(hospitalId);
        hospitalById.addPatient(patient);
        patient.setHospital(hospitalById);
        patientRepository.save(patient);
    }

    @Override
    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }
}
