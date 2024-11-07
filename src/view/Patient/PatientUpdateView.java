package view.Patient;

import controller.UserManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.enums.Alignment;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.layout.Align;
import model.Patient;
import model.enums.Gender;
import view.View;


public class PatientUpdateView extends View {

    UserManager userManager = UserManager.getInstance(UserManager.class);

    Scanner sc = new Scanner(System.in);

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
            new MenuOption("Name", () -> this.updateName()),
            new MenuOption("Date of birth", () -> this.updateDob()),
            new MenuOption("Gender", () -> this.updateGender()),
            new MenuOption("Contact number", () -> this.updateNumber()),
            new MenuOption("Email address", () -> this.updateEmail())
        ).readOption(context);

        System.out.println("Update completed.");
    }

    public void updateName(){
        Patient patient = (Patient) userManager.getActiveUser();
        System.out.println("Please enter your new name:");
        String name = sc.nextLine();
        patient.setName(name);
        System.out.println("Name is updated successfully.");
    }

    public void updateDob(){
        Patient patient = (Patient) userManager.getActiveUser();
        System.out.println("Please enter your new birthday(dd/MM/yyyy):");
        String dobString = sc.nextLine();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dob = LocalDate.parse(dobString, formatter);
            patient.setDob(dob);
            System.out.println("Date of birth updated successfully.");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please try again.");
        }
    }

    public void updateGender(){
        Patient patient = (Patient) userManager.getActiveUser();
        System.out.println("Please enter your gender:");
        String genderInput = sc.nextLine().toUpperCase();
        try {
            Gender gender = Gender.valueOf(genderInput);
            patient.setGender(gender);
            System.out.println("Gender updated successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid gender. Please enter 'MALE' or 'FEMALE'.");
        }
    }

        public void updateNumber(){
        Patient patient = (Patient) userManager.getActiveUser();
        System.out.println("Please enter your new contact number:");
        String number = sc.nextLine();
        patient.setPhoneNumber(number);
        System.out.println("Contact number updated successfully.");
    }

        public void updateEmail(){
        Patient patient = (Patient) userManager.getActiveUser();
        System.out.println("Please enter your new email address:");
        String email = sc.nextLine();
        patient.setEmailAddress(email);
        System.out.println("Email address updated successfully.");
    }

}