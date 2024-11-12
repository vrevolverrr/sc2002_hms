package view.Admin;

import controller.AdminManager;
import controller.UserManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.TextInputField;
import lib.uilib.framework.enums.Alignment;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.layout.Align;
import services.Navigator;
import view.View;
import model.enums.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AdminHospitalStaffView extends View{
 
    UserManager userManager = UserManager.getInstance(UserManager.class);

    public AdminHospitalStaffView() {
    }
    
    @Override
    public String getViewName() {
        return "View and Manage Hospital Staff";
    }

    @Override
    public void render() {
        BuildContext context = new BuildContext(100, 10);

        new Align(Alignment.CENTER, new Text(" [ View and Manage Hospital Staff ] ", TextStyle.BOLD)).paint(context);

        new Menu(
            new MenuOption("View Hospital Staff", () -> {
                this.viewStaff();;
            }),
            new MenuOption("Add New Staff", () -> {
                this.promptAddStaff();;
            }),
            new MenuOption("Update Existing Staff", () -> {
                this.promptUpdateStaff();;
            }),
            new MenuOption("Remove Staff", () -> {
                this.promptRemoveStaff();;
            }),
            new MenuOption("Exit this view", () -> {
                this.exitView();;;
            })
        ).readOption(context);


    }

    private void exitView(){
        Navigator.navigateTo(new AdminView());
    }

    private void promptRemoveStaff(){

        BuildContext context = new BuildContext(100, 10);

        new Align(Alignment.CENTER, new Text(" [ Remove Staff ] ", TextStyle.BOLD)).paint(context);

        TextInputField staffId = new TextInputField("Please enter the staff's ID: ");
        new TextInput(staffId).read(context, input -> input != null && !input.trim().isEmpty());

        if (!AdminManager.removeStaff(staffId.getValue())) {
            new Text("Error: Staff ID not found or removal failed.", TextStyle.BOLD).paint(context);
        }
        else {
            new Text("Staff removed successfully.", TextStyle.BOLD).paint(context);
        }

    }

    private void promptUpdateStaff(){
        BuildContext context = new BuildContext(100, 10);

        new Align(Alignment.CENTER, new Text(" [ Update Staff ] ", TextStyle.BOLD)).paint(context);

        TextInputField staffId = new TextInputField("Please enter the staff's ID: ");
        new TextInput(staffId).read(context, (input) -> true);
        boolean validStaffId = false;

        
        while (!validStaffId) {
            new TextInput(staffId).read(context, input -> input != null && !input.trim().isEmpty());

            if (AdminManager.searchStaffById(staffId.getValue()).isEmpty()) {
                new Text("Error: Staff not found! Please enter a valid ID.", TextStyle.BOLD).paint(context);
                View.gotoPrevNthLine(1);
                View.clearLines(1);
            } else {
                validStaffId = true;
            }
        }

        int choice = this.printUpdateStaffMenu();

        switch (choice) {
            case 1:
                TextInputField newName = new TextInputField("Please enter the staff's new name");
                new TextInput(newName).read(context, (input) -> true);
                AdminManager.updateStaff(staffId, choice, newName);
                break;
            case 2:
                TextInputField newPassword = new TextInputField("Please enter the staff's new password");
                new TextInput(newPassword).read(context, (input) -> true);
                AdminManager.updateStaff(staffId, choice, newPassword);
                break;
            case 3:
                LocalDate newBirthday = this.promptBirthday();
                AdminManager.updateStaff(staffId, choice, newBirthday);
                break;
            case 4:
                TextInputField newPhoneNumber = new TextInputField("Please enter the staff's new phone number");
                new TextInput(newPhoneNumber).read(context, (input) -> true);
                AdminManager.updateStaff(staffId, choice, newPhoneNumber);
                break;
            case 5:
                TextInputField newEmail = new TextInputField("Please enter the staff's new email address");
                new TextInput(newEmail).read(context, (input) -> true);
                AdminManager.updateStaff(staffId, choice, newEmail);
                break;
            case 6:
                TextInputField newAge = new TextInputField("Please enter the staff's age");
                new TextInput(newAge).read(context, (input) -> true);
                AdminManager.updateStaff(staffId, choice, newAge);
                break;
            }
        }


        private int printUpdateStaffMenu(){
            BuildContext context = new BuildContext(100, 10);
            new Align(Alignment.CENTER, new Text(" [ Please choose the information that you want to update ] ", TextStyle.BOLD)).paint(context);

            final int[] choice = {-1};
            new Menu(
                new MenuOption("Name", () -> choice[0] = 1),
                new MenuOption("Password", () -> choice[0] = 2),
                new MenuOption("Birthday", () -> choice[0] = 3),
                new MenuOption("Phone Number", () -> choice[0] = 4),
                new MenuOption("Email Address", () -> choice[0] = 5),
                new MenuOption("Age", () -> choice[0] = 6)
            ).readOption(context);

            return choice[0];
    }

    private void viewStaff(){
        BuildContext context = new BuildContext(100, 10);

        new Align(Alignment.CENTER, new Text(" [ Select a staff listing option ] ", TextStyle.BOLD)).paint(context);

        new Menu(
            new MenuOption("Print by the role", () -> {
                AdminManager.printStaff(1);
            }),
            new MenuOption("Print by gender", () -> {
                AdminManager.printStaff(2);
            }),
            new MenuOption("Print by the age", () -> {
                AdminManager.printStaff(3);
            })
        ).readOption(context);


    }

    private void promptAddStaff(){
        BuildContext context = new BuildContext(100, 10);

        new Align(Alignment.CENTER, new Text(" [ Add New Staff ] ", TextStyle.BOLD)).paint(context);

        TextInputField staffId = new TextInputField("Please enter the staff's ID");
        new TextInput(staffId).read(context, (input) -> true);
        TextInputField name = new TextInputField("Please enter the staff's name");
        this.validateString(name, context);
        TextInputField password = new TextInputField("Plese enter the password");
        new TextInput(password).read(context, (input) -> true);
        UserRole userRole = this.promptRole();
        Gender gender = this.promptGender();
        LocalDate dob = this.promptBirthday();
        TextInputField phoneNumber = new TextInputField("Please enter the phone number");
        new TextInput(staffId).read(context, (input) -> true);
        new TextInput(phoneNumber).read(context, (input) -> true);
        TextInputField emailAddress = new TextInputField("Please enter the email address");
        new TextInput(emailAddress).read(context, (input) -> true);
        this.validateEmail(emailAddress, context);
        TextInputField age = new TextInputField("Please enter the staff's age");
        new TextInput(age).read(context, (input) -> true);
        this.validateAge(age, context);

        AdminManager.addStaff(staffId.getValue(), name.getValue(), password.getValue(), userRole.getValue(), gender.getValue(), dob, phoneNumber.getValue(), emailAddress.getValue(), age.getValue());


    }

    private LocalDate promptBirthday(){
        BuildContext context = new BuildContext(100, 10);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        TextInputField birthdayInput = new TextInputField("Please enter the staff's birthday (YYYY-MM-DD)");

        while (true) {
            new TextInput(birthdayInput).read(context, input -> true);
            String input = birthdayInput.getValue();

            try {
                LocalDate birthday = LocalDate.parse(input, dateFormatter);
                return birthday;
            } catch (DateTimeParseException e) {
                new Text("Invalid date format. Please use YYYY-MM-DD.", TextStyle.BOLD).paint(context);
                View.gotoPrevNthLine(1);
                View.clearLines(1);
            }
        }

        
    }

    private UserRole promptRole(){
        final UserRole[] userRole = {null};
        BuildContext context = new BuildContext(100, 10);

        while (userRole[0] == null) { 
            new Align(Alignment.START, new Text("Please enter the staff's role (1-3) ", TextStyle.BOLD)).paint(context);

            new Menu(
                new MenuOption("Doctor", () -> userRole[0] = UserRole.DOCTOR),
                new MenuOption("Pharmacist", () -> userRole[0] = UserRole.PHARMACIST),
                new MenuOption("Admin", () -> userRole[0] = UserRole.ADMIN)
            ).readOption(context);
        }

        return userRole[0];
        
    }
    
    private Gender promptGender(){

        final Gender[] gender = {null};
        BuildContext context = new BuildContext(100, 10);

        while (gender[0] == null) { 
            new Align(Alignment.START, new Text("Please enter the staff's gender (1-2) ", TextStyle.BOLD)).paint(context);

            new Menu(
                new MenuOption("Male", () -> gender[0] = Gender.MALE),
                new MenuOption("Female", () -> gender[0] = Gender.FEMALE)
            ).readOption(context);
        }

        return gender[0];

    }


    private boolean validateString(TextInputField inputField, BuildContext context) {

        final boolean[] validInput = {false}; 
    
        while (!validInput[0]) {
            new TextInput(inputField).read(context, (input) -> {
                if (input.matches("[a-zA-Z]+")) {
                    validInput[0] = true;
                    return true;
                } else {
                    new Text("Error: Please enter a valid input containing only letters.", TextStyle.BOLD).paint(context);
                    View.gotoPrevNthLine(1);  // Move back up after printing the error message
                    return false;  // Return false to trigger re-prompting for input
                }
            });
        }
    
        return validInput[0];
    }

    /*private boolean validatePhoneNumber(TextInputField inputField, BuildContext context) {
        final boolean[] validInput = {false}; 
    
        while (!validInput[0]) {
            new TextInput(inputField).read(context, (input) -> {
                if (input.matches("\\d+")) {  
                    View.clearLine();  
                    validInput[0] = true;
                    return true;
                } else {
                    new Text("Error: Please enter a valid phone number containing only digits.", TextStyle.BOLD).paint(context);
                    View.gotoPrevNthLine(2);  
                    return false;
                }
            });
        }
    
        return validInput[0];
    }
    */
    

    private boolean validateEmail(TextInputField inputField, BuildContext context) {
        final boolean[] validInput = {false}; 
    
        while (!validInput[0]) {
            new TextInput(inputField).read(context, (input) -> {
                if (input.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
                    validInput[0] = true;
                    return true;
                } else {
                    new Text("Error: Please enter a valid email address.", TextStyle.BOLD).paint(context);
                    View.gotoPrevNthLine(2);  
                    return false;
                }
            });
        }
    
        return validInput[0];
    }

    private boolean validateAge(TextInputField inputField, BuildContext context) {
        final boolean[] validInput = {false}; 
    
        while (!validInput[0]) {
            new TextInput(inputField).read(context, (input) -> {
                try {
                    int age = Integer.parseInt(input);
                    if (age >= 18 && age <= 100) {
                        View.clearLine();  // Clear error message if valid
                        validInput[0] = true;
                        return true;
                    } else {
                        new Text("Error: Please enter a valid age between 18 and 100.", TextStyle.BOLD).paint(context);
                        View.gotoPrevNthLine(2);
                        return false;
                    }
                } catch (NumberFormatException e) {
                    new Text("Error: Please enter a numeric age.", TextStyle.BOLD).paint(context);
                    View.gotoPrevNthLine(2);
                    return false;
                }
            });
        }
    
        return validInput[0];
    }
    
    
    


}
