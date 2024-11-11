package view.Admin;

import controller.UserManager;
import lib.uilib.framework.MenuOption;
import lib.uilib.widgets.base.Menu;
import services.Navigator;
import view.View;
import view.Admin.staff.AdminAddStaffView;
import view.Admin.staff.AdminAllStaffView;
import view.widgets.Title;
    
public class AdminManageStaffView extends View{
    UserManager userManager = UserManager.getInstance(UserManager.class);

    @Override
    public String getViewName() {
        return "Manage Hospital Staff";
    }

    @Override
    public void render() {
        new Title("Manage Hospital Staff").paint(context);

        new Menu(
            new MenuOption("View All Staff", () -> Navigator.navigateTo(new AdminAllStaffView())),
            new MenuOption("Add New Staff", () -> Navigator.navigateTo(new AdminAddStaffView())),
            // new MenuOption("Update Existing Staff", () -> this.promptUpdateStaff()),
            // new MenuOption("Remove Staff", () -> this.promptRemoveStaff()),
            new MenuOption("Back", () -> Navigator.pop())
        ).readOption(context);
    }

    // private void promptRemoveStaff(){

    //     BuildContext context = new BuildContext(100, 10);

    //     new Align(Alignment.CENTER, new Text(" [ Remove Staff ] ", TextStyle.BOLD)).paint(context);

    //     TextInputField staffId = new TextInputField("Please enter the staff's ID: ");
    //     new TextInput(staffId).read(context, input -> input != null && !input.trim().isEmpty());

    //     if (!AdminManager.removeStaff(staffId.getValue())) {
    //         new Text("Error: Staff ID not found or removal failed.", TextStyle.BOLD).paint(context);
    //     }
    //     else {
    //         new Text("Staff removed successfully.", TextStyle.BOLD).paint(context);
    //     }

    // }

    // private void promptUpdateStaff(){
    //     BuildContext context = new BuildContext(100, 10);

    //     new Align(Alignment.CENTER, new Text(" [ Update Staff ] ", TextStyle.BOLD)).paint(context);

    //     TextInputField staffId = new TextInputField("Please enter the staff's ID: ");
    //     boolean validStaffId = false;

        
    //     while (!validStaffId) {
    //         new TextInput(staffId).read(context, input -> input != null && !input.trim().isEmpty());

    //         if (AdminManager.searchStaffById(staffId.getValue()).isEmpty()) {
    //             new Text("Error: Staff not found! Please enter a valid ID.", TextStyle.BOLD).paint(context);
    //             View.gotoPrevNthLine(2);
    //             View.clearLines(1);
    //         } else {
    //             validStaffId = true;
    //         }
    //     }

    //     //printUpdateStaffMenu() only returns valid choices
    //     int choice = this.printUpdateStaffMenu();

    //     switch (choice) {
    //         case 1:
    //             TextInputField newName = new TextInputField("Please enter the staff's new name: ");
    //             AdminManager.updateStaff(staffId, choice, newName);
    //             break;
    //         case 2:
    //             TextInputField newPassword = new TextInputField("Please enter the staff's new password: ");
    //             AdminManager.updateStaff(staffId, choice, newPassword);
    //             break;
    //         case 3:
    //             LocalDate newBirthday = this.promptBirthday();
    //             AdminManager.updateStaff(staffId, choice, newBirthday);
    //             break;
    //         case 4:
    //             TextInputField newPhoneNumber = new TextInputField("Please enter the staff's new phone number: ");
    //             AdminManager.updateStaff(staffId, choice, newPhoneNumber);
    //             break;
    //         case 5:
    //             TextInputField newEmail = new TextInputField("Please enter the staff's new email address: ");
    //             AdminManager.updateStaff(staffId, choice, newEmail);
    //             break;
    //         case 6:
    //             TextInputField newAge = new TextInputField("Please enter the staff's age: ");
    //             AdminManager.updateStaff(staffId, choice, newAge);
    //             break;
    //         }
    //     }


    //     private int printUpdateStaffMenu(){
    //         BuildContext context = new BuildContext(100, 10);
    //         new Align(Alignment.CENTER, new Text(" [ Please choose the information that you want to update ] ", TextStyle.BOLD)).paint(context);

    //         final int[] choice = {-1};
    //         new Menu(
    //             new MenuOption("Name", () -> choice[0] = 1),
    //             new MenuOption("Password", () -> choice[0] = 2),
    //             new MenuOption("Birthday", () -> choice[0] = 3),
    //             new MenuOption("Phone Number", () -> choice[0] = 4),
    //             new MenuOption("Email Address", () -> choice[0] = 5),
    //             new MenuOption("Age", () -> choice[0] = 6)
    //         ).readOption(context);

    //         return choice[0];
    // }
}
