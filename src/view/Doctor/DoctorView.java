package view.Doctor;

import controller.UserManager;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.TableRow;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Table;
import lib.uilib.widgets.base.VSpacer;
import model.users.Doctor;
import services.Navigator;
import view.View;
import view.Doctor.appointments.DoctorManageAppointmentsView;
import view.Doctor.availability.DoctorAvailabilityView;
import view.Doctor.availability.DoctorScheduleView;
import view.Doctor.records.DoctorPatientsUnderCareView;
import view.widgets.Title;

public class DoctorView extends View {
    private final UserManager userManager = UserManager.getInstance(UserManager.class);
    private final Doctor doctor = (Doctor) userManager.getActiveUser();

    @Override
    public String getViewName() {
        return("Doctor Overview");
    }

    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Welcome Dr. " + userManager.getActiveUser().getName()).paint(context);
        
        new Table(
            new TableRow("Doctor ID", "Name", "Date of Birth", "Gender", "Age", "Specialisation"),
            new TableRow(doctor.getDoctorId(), doctor.getName(), doctor.getDobString(), doctor.getGender().toString(), String.valueOf(doctor.getAge()), doctor.getSpecialisation().toString())
        ).paint(context);

        new VSpacer(1).paint(context);
        new Menu(
            new MenuOption("View Personal Schedule", () -> 
                Navigator.navigateTo(new DoctorScheduleView(doctor))),
                
            new MenuOption("Edit Personal Availability", () -> 
               Navigator.navigateTo(new DoctorAvailabilityView(doctor))),
            
            new MenuOption("Manage Appointments", () ->
                Navigator.navigateTo(new DoctorManageAppointmentsView(doctor))),

            new MenuOption("Manage Patient Medical Records", () ->
                Navigator.navigateTo(new DoctorPatientsUnderCareView(doctor))),
            
            new MenuOption("Log Out", () -> Navigator.pop())

        ).readOption(context);
    }
    
}
