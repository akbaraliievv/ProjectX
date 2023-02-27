package peaksoft.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Table(name = "appointments")
@NoArgsConstructor
@Getter
@Setter
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "appointment_id_gen")
    @SequenceGenerator(name = "appointment_id_gen",sequenceName = "appointments_id_seq",allocationSize = 1)
    private Long id;
    private LocalDate date;
    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST},fetch = FetchType.EAGER)
    private Patient patient;
    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST},fetch = FetchType.EAGER)
    private Doctor doctor;
    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST},fetch = FetchType.EAGER)
    private Department department;

    @Transient
    private Long patientId;
    @Transient
    private Long doctorId;
    @Transient
    private Long departmentId;
}
