package view.Doctor;

import controller.UserManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.enums.Alignment;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Table;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.base.VSpacer;
import lib.uilib.widgets.layout.Align;
import model.users.Patient;
import model.users.Doctor;
import services.Navigator;
import view.View;

public class DoctorView extends View {

    UserManager userManager = UserManager.getInstance(UserManager.class);

    private void viewAndUpdatePatientMedicalRecord(){
        Navigator.navigateTo(new PatientMedicalRecordView());
    }

    private void viewDoctorPersonalSchedule(){
        Navigator.navigateTo(new DoctorScheduleView());
    }

    private void setDoctorPersonalAvailability(){
        Navigator.navigateTo(new DoctorAvailabilityView());
    }

    private void viewandUpdateDoctorUpcomingAppointments(){
        Navigator.navigateTo(new DoctorUpcomingAppointmentsView);
    }

    private void logOut(){
        Navigator.navigateTo(new LoginView());
    }

    @Override
    public String getViewName() {
        return("AdminView");
    }

    @Override
    public void render() {

        BuildContext context = new BuildContext(100, 10);

        final int[] validChoice = {-1};
        
        while(validChoice[0] != 1){
            new Menu(
                new MenuOption("View and Update Patient Medical Record", () -> {
                    this.viewAndUpdatePatientMedicalRecord();; validChoice[0] = 1;}),

                new MenuOption("View Personal Schedule", () -> {
                    this.viewDoctorPersonalSchedule();; validChoice[0] = 1;}),

                new MenuOption("Edit Personal Availability", () -> {
                    this.setDoctorPersonalAvailability();; validChoice[0] = 1;}),

                new MenuOption("View Upcoming Appointments", () -> {
                    this.viewandUpdateDoctorUpcomingAppointments();; validChoice[0] = 1;}),

                new MenuOption("Update Appointment Outcome", () -> {
                    this.viewAndUpdatePatientMedicalRecord();; validChoice[0] = 1;}),
                
                new MenuOption("Log Out", () -> {
                    this.logOut();; validChoice[0] = 1;})

            ).readOption(context);

            if(validChoice[0] != 1){
                new Text("Invalid choice. Please select a valid option.", TextStyle.BOLD).paint(context);
                View.gotoPrevNthLine(2);
                View.clearLines(1);
            }
        }
    }
    
}
