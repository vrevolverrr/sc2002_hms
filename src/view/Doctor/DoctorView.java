package view.Doctor;

import controller.interfaces.IUserManager;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.TableRow;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Table;
import lib.uilib.widgets.base.VSpacer;
import model.users.Doctor;
import services.Navigator;
import services.ServiceLocator;
import view.View;
import view.Doctor.appointments.DoctorManageAppointmentsView;
import view.Doctor.availability.DoctorAvailabilityView;
import view.Doctor.availability.DoctorScheduleView;
import view.Doctor.records.DoctorPatientsUnderCareView;
import view.widgets.Title;

/**
 * {@link DoctorView} is a {@link View} that displays an overview of the doctor's information and options.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-10
 */
public class DoctorView extends View {

    /**
     * Default constructor for the {@link DoctorView} class.
     */
    public DoctorView() {}
    
    /**
     * Instance of {@link UserManager} used to manage users.
     */
    private final IUserManager userManager = ServiceLocator.getService(IUserManager.class);

    /**
     * Instance of {@link Doctor} that is currentlty active.
     */
    

    /**
     * Returns the name of the view.
     *
     * @return a {@link String} representing the view name, "Doctor Overview".
     */
    @Override
    public String getViewName() {
        return("Doctor Overview");
    }

    /**
     * Renders the doctor's main overview page, including:
     * <ul>
     *   <li>Breadcrumb navigation</li>
     *   <li>Welcome message with the doctor's name</li>
     *   <li>A table showing the doctor's personal details</li>
     *   <li>A menu with options to navigate to different views, such as managing appointments, availability, or patient records.</li>
     * </ul>
     * <p>
     * The options available in the menu allow the doctor to manage their appointments, view their schedule,
     * set their availability, and manage patient medical records.
     * </p>
     */    
    @Override
    public void render() {
        final Doctor doctor = (Doctor) userManager.getActiveUser();

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
