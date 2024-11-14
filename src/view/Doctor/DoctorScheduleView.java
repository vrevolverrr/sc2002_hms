package view.Doctor;

import java.util.ArrayList;
import java.util.List;

import controller.UserManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.enums.Alignment;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Table;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.layout.Align;
import view.View;
import model.users.Doctor;
import services.Navigator;
import controller.DoctorManager;

public class DoctorScheduleView extends View {
    
    UserManager userManager = UserManager.getInstance(UserManager.class);

// PART 0: DEFINING MY FUNCTIONS FOR DOCTOR VIEW
    public String getViewName() {
        return("DoctorScheduleView");
    }

    private void returnToMenu(){
        Navigator.navigateTo(new DoctorView());
    }

    @Override
    public void render() {
        BuildContext context = new BuildContext(100, 10);

    // PART 1: PRINT AVAILABILITY UX IN TABLE FORMAT

    // Title Section
        new Align(Alignment.CENTER, new Text(" [ My Schedule ] ", TextStyle.BOLD)).paint(context);

        // Initialize rows with the header row
        List<TableRow> rows = new ArrayList<>();
        rows.add(new TableRow("No.", "Date", "Time", "Status", "Patient"));

        // Render the table
        new Table(rows.toArray(new TableRow[0])).paint(context);
/*
    // PART 1: PRINT SCHEDULE UX IN CALENDAR FORMAT    
        // Title Section
        new Align(Alignment.CENTER, new Text(" [ My Schedule ] ", TextStyle.BOLD)).paint(context);
          
        // Define time slots
        String[] timeSlots = {
            "9:00am", "9:30am", "10:00am", "10:30am", "11:00am", "11:30am",
            "12:00pm", "12:30pm", "1:00pm", "1:30pm", "2:00pm", "2:30pm", 
            "3:00pm", "3:30pm", "4:00pm", "4:30pm", "5:00pm", "5:30pm"
        };

        // Initialize rows with the header row
        List<TableRow> rows = new ArrayList<>();
        rows.add(new TableRow("Time", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"));

        // Add time slot rows
        for (String time : timeSlots) {
            if (time.equals("12:00pm") || time.equals("12:30pm") || time.equals("1:00pm") || time.equals("1:30pm")) {
                // Fill lunch slots
                rows.add(new TableRow(time, "LUNCH", "LUNCH", "LUNCH", "LUNCH", "LUNCH"));
            } else {
                // Empty slots for other time periods
                rows.add(new TableRow(time, "", "", "", "", ""));
            }
        }

        // Render the table
        new Table(rows.toArray(new TableRow[0])).paint(context);
 */ 

    // PART 2: PRINT OPTIONS TO ADD OR REMOVE AVAILABILITY
    final int[] validChoice = {-1};
        
        while(validChoice[0] != 1){
            new Menu(
                // TO DO: create these two functions
                new MenuOption("Add availability", () -> {
                    this.addAvailability();; validChoice[0] = 1;}),

                new MenuOption("Remove availability", () -> {
                    this.removeAvailability();; validChoice[0] = 1;}),

                new MenuOption("Return to menu", () -> {
                    this.returnToMenu();; validChoice[0] = 1;})

            ).readOption(context);

            if(validChoice[0] != 1){
                new Text("Invalid choice. Please select a valid option.", TextStyle.BOLD).paint(context);
                View.gotoPrevNthLine(2);
                View.clearLines(1);
            }
        }
    }
}
