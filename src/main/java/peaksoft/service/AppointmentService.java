package peaksoft.service;

import peaksoft.entities.Appointment;

import java.util.List;

public interface AppointmentService {
    List<Appointment> findAll(Long id);

    Appointment getById(Long appointmentId);

    void update(Long appointmentId,Appointment newAppointment);

    Appointment save(Long hospitalId, Appointment appointment);

    void deleteById(Long hospitalId, Long appointmentId);
}
