// package view.Admin;

// import controller.UserManager;
// import lib.uilib.framework.BuildContext;
// import lib.uilib.framework.MenuOption;
// import lib.uilib.widgets.base.Menu;
// import services.Navigator;
// import view.LoginView;
// import view.View;


// public class AdminView extends View {

//     UserManager userManager = UserManager.getInstance(UserManager.class);

//     private void viewAndManageStaff(){
//         Navigator.navigateTo(new AdminHospitalStaffView());
//     }

//     private void viewAppointmentsDetails(){
//         Navigator.navigateTo(new AdminAppointmentView());
//     }

//     private void viewandManageInventory(){
//         Navigator.navigateTo(new AdminInventoryView());
//     }

//     private void approveReplenishRequest(){
//         Navigator.navigateTo(new AdminReplenishmentRequestView());
//     }

//     private void logOut(){
//         Navigator.navigateTo(new LoginView());
//     }

//     @Override
//     public String getViewName() {
//         return("Admin");
//     }

//     @Override
//     public void render() {

//         BuildContext context = new BuildContext(100, 10);

//         new Menu(
//             new MenuOption("View and Manage Hospital Staff", () -> 
//                 this.viewAndManageStaff()),

//             new MenuOption("View Appoinments Details", () -> 
//                 this.viewAppointmentsDetails()),

//             new MenuOption("View and Manage Medication Inventory", () ->
//                 this.viewandManageInventory()),

//             new MenuOption("Approve Replenishment Request", () -> 
//                 this.approveReplenishRequest()),

//             new MenuOption("Log Out", () -> 
//                 this.logOut())

//         ).readOption(context);
//     }
    
// }
