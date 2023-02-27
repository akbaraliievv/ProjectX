package peaksoft.service;

import peaksoft.entities.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> findAll(Long id);

    Patient getById(Long patientId);

    void update(Long patientId,Patient newPatient);

    void save(Long hospitalId, Patient patient);

    void deleteById(Long id);
}
