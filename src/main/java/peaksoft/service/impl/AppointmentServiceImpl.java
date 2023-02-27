package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entities.Appointment;
import peaksoft.entities.Hospital;
import peaksoft.repository.*;
import peaksoft.service.AppointmentService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DepartmentRepository departmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;


    @Override
    public List<Appointment> findAll(Long id) {
        return appointmentRepository.findAll(id);
    }

    @Override
    public Appointment getById(Long appointmentId) {
        return appointmentRepository.getById(appointmentId);
    }

    @Override
    public void update(Long appointmentId, Appointment newAppointment) {
        appointmentRepository.update(appointmentId, newAppointment);
    }

    @Override
    public Appointment save(Long hospitalId, Appointment appointment) {
        Hospital hospital= hospitalRepository.getById(hospitalId);
        Appointment newAppointment=new Appointment();
        newAppointment.setDate(appointment.getDate());
        newAppointment.setId(appointment.getId());

        newAppointment.setDoctor(doctorRepository.getById(appointment.getDoctorId()));
        newAppointment.setDepartment(departmentRepository.getById(appointment.getDepartmentId()));
        newAppointment.setPatient(patientRepository.getById(appointment.getPatientId()));
        hospital.addAppointment(newAppointment);
        return appointmentRepository.save(newAppointment);
    }

    @Override
    public void deleteById(Long hospitalId, Long appointmentId) {
        Appointment appointment = appointmentRepository.getById(appointmentId);
        Hospital hospital = hospitalRepository.getById(hospitalId);
        appointment.getDoctor().setAppointments(null);
        appointment.getPatient().setAppointments(null);
        hospital.getAppointments().remove(appointment);
        appointmentRepository.deleteById(hospital, appointmentId);
    }


}