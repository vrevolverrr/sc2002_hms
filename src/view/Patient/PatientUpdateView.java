package view.Patient;

import controller.UserManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.TextInputField;
import lib.uilib.framework.enums.Alignment;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.MultiTextInput;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.layout.Align;
import model.Patient;
import model.enums.Gender;
import view.View;

public class PatientUpdateView extends View {
    UserManager userManager = UserManager.getInstance(UserManager.class);

    @Override
    public String getViewName() {
        return "Update Patient Details";
    }

    @Override
    public void render() {
        BuildContext context = new BuildContext(100, 10);

        new Align(Alignment.CENTER, new Text(" [ Update Patient Details ] ", TextStyle.BOLD)).paint(context);

        System.out.println("Choose the details you want to update:");

        new Menu(
            new MenuOption("Name", () -> this.updateName(context)),
            new MenuOption("Date of birth", () -> this.updateDob(context)),
            new MenuOption("Gender", () -> this.updateGender(context)),
            new MenuOption("Contact number", () -> this.updateNumber(context)),
            new MenuOption("Email address", () -> this.updateEmail(context))
        ).readOption(context);

        System.out.println("Update completed.");
    }

    private void updateName(BuildContext context) {
        Patient patient = (Patient) userManager.getActiveUser();
        TextInputField nameField = new TextInputField("New Name");
        
        new MultiTextInput(nameField).readAll(
            context,
            (String[] values) -> {
                String name = values[0].trim();
                if (name.isEmpty()) {
                    return false;
                }
                patient.setName(name);
                System.out.println("Name is updated successfully.");
                return true;
            },
            "Name cannot be empty. Please try again."
        );
    }

    private void updateDob(BuildContext context) {
        Patient patient = (Patient) userManager.getActiveUser();
        TextInputField dobField = new TextInputField("New Date of Birth (dd/MM/yyyy)");
        
        new MultiTextInput(dobField).readAll(
            context,
            (String[] values) -> {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate dob = LocalDate.parse(values[0], formatter);
                    
                    if (dob.isAfter(LocalDate.now())) {
                        System.out.println("Error: Date of birth cannot be in the future.");
                        return false;
                    }
                    
                    patient.setDob(dob);
                    System.out.println("Date of birth updated successfully.");
                    return true;
                } catch (DateTimeParseException e) {
                    return false;
                }
            },
            "Invalid date format. Please use dd/MM/yyyy format."
        );
    }

    private void updateGender(BuildContext context) {
        Patient patient = (Patient) userManager.getActiveUser();
        TextInputField genderField = new TextInputField("New Gender (MALE/FEMALE)");
        
        new MultiTextInput(genderField).readAll(
            context,
            (String[] values) -> {
                try {
                    Gender gender = Gender.valueOf(values[0].toUpperCase());
                    patient.setGender(gender);
                    System.out.println("Gender updated successfully.");
                    return true;
                } catch (IllegalArgumentException e) {
                    return false;
                }
            },
            "Invalid gender. Please enter 'MALE' or 'FEMALE'."
        );
    }

    private void updateNumber(BuildContext context) {
        Patient patient = (Patient) userManager.getActiveUser();
        TextInputField numberField = new TextInputField("New Contact Number");
        
        new MultiTextInput(numberField).readAll(
            context,
            (String[] values) -> {
                String number = values[0].trim();
                // Simple validation for phone number (can be enhanced based on requirements)
                if (!number.matches("^\\+?[0-9]{8,15}$")) {
                    return false;
                }
                patient.setPhoneNumber(number);
                System.out.println("Contact number updated successfully.");
                return true;
            },
            "Invalid phone number format. Please enter 8-15 digits, optionally starting with '+'."
        );
    }

    private void updateEmail(BuildContext context) {
        Patient patient = (Patient) userManager.getActiveUser();
        TextInputField emailField = new TextInputField("New Email Address");
        
        new MultiTextInput(emailField).readAll(
            context,
            (String[] values) -> {
                String email = values[0].trim();
                // Simple email validation (can be enhanced based on requirements)
                if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                    return false;
                }
                patient.setEmailAddress(email);
                System.out.println("Email address updated successfully.");
                return true;
            },
            "Invalid email address format. Please try again."
        );
    }
}