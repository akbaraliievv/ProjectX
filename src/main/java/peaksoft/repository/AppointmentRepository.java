package peaksoft.repository;

import peaksoft.entities.Appointment;
import peaksoft.entities.Hospital;

import java.util.List;

public interface AppointmentRepository {
    List<Appointment> findAll(Long id);

    Appointment getById(Long appointmentId);

    void update(Long appointmentId, Appointment appointment);

    Appointment save(Appointment appointment);

    void deleteById(Hospital hospital, Long appointmentId);
}
