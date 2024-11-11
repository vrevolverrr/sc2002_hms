package view.Doctor;

import controller.UserManager;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.TableRow;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Table;
import lib.uilib.widgets.base.VSpacer;
import model.users.Doctor;
import services.Navigator;
import view.View;
import view.Doctor.appointments.DoctorManageAppointmentsView;
import view.widgets.Title;

public class DoctorView extends View {
    private final UserManager userManager = UserManager.getInstance(UserManager.class);
    private final Doctor doctor = (Doctor) userManager.getActiveUser();

    @Override
    public String getViewName() {
        return("Admin");
    }
    
    private void handleManageMedicalRecords(){
        // Navigator.navigateTo(new PatientMedicalRecordView());
    }

    private void handleViewPersonalSchedule(){
        // Navigator.navigateTo(new DoctorScheduleView());
    }

    private void handlePersonalAvailability(){
        // Navigator.navigateTo(new DoctorAvailabilityView());
    }

    private void handleManageAppointments(){
        Navigator.navigateTo(new DoctorManageAppointmentsView(doctor));
    }

    @Override
    public void render() {
        new Title("Welcome Dr. " + userManager.getActiveUser().getName()).paint(context);
        
        new Table(
            new TableRow("Doctor ID", "Name", "Date of Birth", "Gender", "Age", "Specialisation"),
            new TableRow(doctor.getDoctorId(), doctor.getName(), doctor.getDobString(), doctor.getGender().getValue(), String.valueOf(doctor.getAge()), doctor.getSpecialisation().toString())
        ).paint(context);

        new VSpacer(1).paint(context);
        new Menu(
            new MenuOption("Edit Personal Availability", () -> 
               this.handlePersonalAvailability()),
            
            new MenuOption("Manage Appointments", () ->
               this.handleManageAppointments()),

            new MenuOption("Manage Patient Medical Records", () ->
                this.handleManageMedicalRecords()),

            new MenuOption("View Personal Schedule", () -> 
                this.handleViewPersonalSchedule()),
            
            new MenuOption("Log Out", () -> Navigator.pop())

        ).readOption(context);

    }
    
}
