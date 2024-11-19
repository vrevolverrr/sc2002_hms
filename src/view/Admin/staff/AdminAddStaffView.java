package view.Admin.staff;

import java.util.Arrays;

import controller.interfaces.IStaffManager;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.TextInputField;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Pause;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.enums.Gender;
import model.enums.Specialisation;
import model.enums.UserRole;
import services.Navigator;
import services.ServiceLocator;
import utils.InputValidators;
import view.View;
import view.widgets.Title;

/**
 * This view allows the admin to add a new staff member.
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public class AdminAddStaffView extends View {
    /**
     * An instance of the {@link IStaffManager} interface. Used to manage staff operations.
     */
    private IStaffManager staffManager = ServiceLocator.getService(IStaffManager.class);

    /**
     * Gets the name of the view for the breadcrumbs.
     * @return the name of the view.
     */
    @Override
    public String getViewName() {
       return "Add Staff";
    }

    /**
     * Renders the view, allowing the admin to add a new staff member.
     */
    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Add Staff").paint(context);

        TextInputField name = new TextInputField("Enter the staff's name");
        new TextInput(name).read(context, "Enter a non-empty staff name.", input -> !input.trim().isEmpty());
        new VSpacer(1).paint(context);

        TextInputField password = new TextInputField("Enter the staff's password");
        new TextInput(password).read(context, "Enter a valid password.", input -> !input.trim().isEmpty());
        new VSpacer(1).paint(context);
        
        final UserRole userRole = this.promptRole();
        new VSpacer(1).paint(context);

        final Gender gender = this.promptGender();
        new VSpacer(1).paint(context);

        TextInputField birthday = new TextInputField("Enter the staff's birthday (YYYY-MM-DD)");
        new TextInput(birthday).read(context, "Invalid date format. Please use YYYY-MM-DD.", 
            input -> InputValidators.validateDate(input, "yyyy-MM-dd"));
        new VSpacer(1).paint(context);
        
        TextInputField phoneNumber = new TextInputField("Enter the staff's phone number");
        new TextInput(phoneNumber).read(context, "Invalid phone number.", input -> InputValidators.validatePhoneNumber(input));
        new VSpacer(1).paint(context);
        
        TextInputField emailAddress = new TextInputField("Enter the staff's email address");
        new TextInput(emailAddress).read(context, "Invalid email address.", input -> InputValidators.validateEmail(input));
        new VSpacer(1).paint(context);
        
        TextInputField age = new TextInputField("Enter the staff's age");
        new TextInput(age).read(context, "Invalid age. Range is between 0 to 150.", input -> InputValidators.validateAge(input));
        new VSpacer(1).paint(context);

        switch (userRole) {
            case DOCTOR:
                Specialisation specialisation = this.promptSpecialisation();
                staffManager.addDoctor(name.getValue(), age.getAge(), password.getValue(), gender, 
                    birthday.getDate("yyyy-MM-dd"),emailAddress.getValue(), phoneNumber.getValue(), specialisation);
                break;
            
            case PHARMACIST:
                staffManager.addPharmacist(name.getValue(), age.getAge(), password.getValue(), gender, 
                    birthday.getDate("yyyy-MM-dd"),emailAddress.getValue(), phoneNumber.getValue());
                break;
            
            case ADMIN:
                staffManager.addAdmin(name.getValue(), age.getAge(), password.getValue(), 
                    gender, birthday.getDate("yyyy-MM-dd"), emailAddress.getValue(), phoneNumber.getValue());
                break;

            default:
                break;
        }

        new Text("Staff added successfully.", TextStyle.BOLD).paint(context);
        Pause.goBack().pause(context);
        Navigator.pop();
    }

    /**
     * Prompts the admin to select the gender of the new staff member.
     * @return the selected gender.
     */
    private Gender promptGender() {
        final Gender[] gender = {Gender.MALE};

        new Text("Enter the staff's gender:", TextStyle.BOLD).paint(context);
        new Menu(
            new MenuOption("Male", () -> gender[0] = Gender.MALE),
            new MenuOption("Female", () -> gender [0]= Gender.FEMALE)
        ).readOption(context);

        return gender[0];
    }

    /**
     * Prompts the admin to select the role of the new staff member.
     * @return the selected role.
     */
    private UserRole promptRole() {
        final UserRole[] userRole = {null};
        new Text("Enter the staff's role:", TextStyle.BOLD).paint(context);

        new Menu(
            Arrays.stream(UserRole.values()).filter(role -> role != UserRole.PATIENT)
                .map(role -> new MenuOption(role.getValue(), () -> userRole[0] = role)).toArray(MenuOption[]::new)
        ).readOption(context);

        return userRole[0];
    }
    
    /**
     * Prompts the admin to select the specialisation of the new staff member if they are a doctor.
     * @return the selected specialisation.
     */
    private Specialisation promptSpecialisation() {
        Specialisation[] specialisation = {null};
        new Text("Enter the staff's specialisation:", TextStyle.BOLD).paint(context);
        
        new Menu(
            Arrays.stream(Specialisation.values()).map(spz -> new MenuOption(spz.getValue(), 
                () -> specialisation[0] = spz)).toArray(MenuOption[]::new)
        ).readOption(context);

        return specialisation[0];
    }
}
