package peaksoft.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hospitals")
@NoArgsConstructor
@Getter
@Setter
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hospital_id_gen")
    @SequenceGenerator(name = "hospital_id_gen", sequenceName = "hospital_id_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String address;
    private String img;
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Doctor> doctors;
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Patient> patients;
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Department> departments;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Appointment> appointments;

    public void addDoctor(Doctor doctor){
        if (doctors==null){
            doctors=new ArrayList<>();
        }
        doctors.add(doctor);
    }
    public void addPatient(Patient patient){
        if (patients==null){
            patients=new ArrayList<>();
        }
        patients.add(patient);
    }
    public void addDepartment(Department department){
        if (departments==null){
            departments=new ArrayList<>();
        }
        departments.add(department);
    }
    public void addAppointment(Appointment appointment) {
        if (appointments==null){
            appointments=new ArrayList<>();
        }
        appointments.add(appointment);
    }
}

