// package view.Doctor;

// import controller.UserManager;
// import lib.uilib.framework.BuildContext;
// import lib.uilib.framework.MenuOption;
// import lib.uilib.framework.enums.TextStyle;
// import lib.uilib.widgets.base.Menu;
// import lib.uilib.widgets.base.Text;
// import services.Navigator;
// import view.LoginView;
// import view.View;

// public class DoctorAvailabilityMenu extends View {

//     UserManager userManager = UserManager.getInstance(UserManager.class);

//     private void viewSchedule(){
//         Navigator.navigateTo(new DoctorScheduleView());
//     }

//     private void viewDoctorPersonalSchedule(){
//         Navigator.navigateTo(new DoctorScheduleView());
//     }

//     private void setDoctorPersonalAvailability(){
//         Navigator.navigateTo(new DoctorAvailabilityView());
//     }

//     private void viewandUpdateDoctorUpcomingAppointments(){
//         Navigator.navigateTo(new DoctorUpcomingAppointmentsView);
//     }

//     private void logOut(){
//         Navigator.navigateTo(new LoginView());
//     }

//     @Override
//     public String getViewName() {
//         return("DoctorAvailabilityView");
//     }

//     @Override
//     public void render() {

//         BuildContext context = new BuildContext(100, 10);

//         new Menu(
//             new MenuOption("View Schedule", () ->
//                 this.viewSchedule()),

//             new MenuOption("Add Availability", () -> 
//                 this.addAvailability()),

//             new MenuOption("Remove Availability", () ->
//                 this.removeAvailability()),

//             new MenuOption("Exit Availability Menu", () ->
//                 this.exitAvailability())

//         ).readOption(context);
//     }

// }
