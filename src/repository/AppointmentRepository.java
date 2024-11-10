package repository;

import model.appointments.Appointment;

public class AppointmentRepository extends BaseRepository<Appointment> {
    final static String FILENAME = "appointments.dat";

    public AppointmentRepository() {
        super(FILENAME);
    }}
