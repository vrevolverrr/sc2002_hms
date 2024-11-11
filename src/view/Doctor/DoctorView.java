package view.Doctor;

import controller.UserManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.MenuOption;
import lib.uilib.widgets.base.Menu;
import services.Navigator;
import view.View;

public class DoctorView extends View {
    UserManager userManager = UserManager.getInstance(UserManager.class);

    @Override
    public String getViewName() {
        return("Admin");
    }
    
    private void viewAndUpdatePatientMedicalRecord(){
        // Navigator.navigateTo(new PatientMedicalRecordView());
    }

    private void viewDoctorPersonalSchedule(){
        // Navigator.navigateTo(new DoctorScheduleView());
    }

    private void setDoctorPersonalAvailability(){
        // Navigator.navigateTo(new DoctorAvailabilityView());
    }

    private void viewandUpdateDoctorUpcomingAppointments(){
        Navigator.navigateTo(new DoctorManageAppointmentsView());
    }

    @Override
    public void render() {

        BuildContext context = new BuildContext(100, 50);

        new Menu(
            new MenuOption("View and Update Patient Medical Record", () ->
                this.viewAndUpdatePatientMedicalRecord()),

            new MenuOption("View Personal Schedule", () -> 
                this.viewDoctorPersonalSchedule()),

            new MenuOption("Edit Personal Availability", () -> 
                this.setDoctorPersonalAvailability()),

            new MenuOption("Manage sAppointments", () ->
                this.viewandUpdateDoctorUpcomingAppointments()),

            new MenuOption("Update Appointment Outcome", () -> 
                this.viewAndUpdatePatientMedicalRecord()),
            
            new MenuOption("Log Out", () -> Navigator.pop())

        ).readOption(context);

    }
    
}
