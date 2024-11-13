package view.Admin.staff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.StaffManager;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.TextInputField;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.EnumeratedTable;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.enums.Gender;
import model.enums.Specialisation;
import model.enums.UserRole;
import model.users.Doctor;
import model.users.User;
import services.InputValidators;
import utils.UpdatableField;
import view.View;
import view.widgets.Title;

public class AdminUpdateStaffDetailsView extends View {
    private StaffManager staffManager = StaffManager.getInstance(StaffManager.class);
    private User staff;

    public AdminUpdateStaffDetailsView(User staff) {
        this.staff = staff;
    }

    @Override
    public String getViewName() {
        return "Update Staff";
    }

    @Override
    public void render() {
        new Title(String.format("Update Staff Details of %s", staff.getId())).paint(context);

        List<UpdatableField> updatableFields = new ArrayList<UpdatableField>();
        updatableFields.add(new UpdatableField(new TableRow("Name", staff.getName()), this::updateName));
        updatableFields.add(new UpdatableField(new TableRow("Age", String.valueOf(staff.getAge())), this::updateAge));
        updatableFields.add(new UpdatableField(new TableRow("Password", staff.getPassword()), this::updatePassword));
        updatableFields.add(new UpdatableField(new TableRow("Gender", staff.getGender().toString()), this::updateGender));
        updatableFields.add(new UpdatableField(new TableRow("Birthday", staff.getDob().toString()), this::updateBirthday));
        updatableFields.add(new UpdatableField(new TableRow("Email Address", staff.getEmailAddress()), this::updateEmail));
        updatableFields.add(new UpdatableField(new TableRow("Phone Number", staff.getPhoneNumber()), this::updatePhoneNumber));

        if (staff.getRole() == UserRole.DOCTOR) {
            updatableFields.add(new UpdatableField(new TableRow("Specialisation", ((Doctor) staff).getSpecialisation().toString()), this::updateSpecialisation));
        }

        TableRow[] detailRows = updatableFields.stream().map(UpdatableField::getRecord).toArray(TableRow[]::new);
        EnumeratedTable.headerless(detailRows).paint(context);
        new VSpacer(1).paint(context);

        TextInputField updateField = new TextInputField(
            String.format("Choose field to update (1-%d, 0 to go back)", detailRows.length));

        new TextInput(updateField).read(context, "Choose a valid field to update.", input -> 
            InputValidators.validateRange(input, detailRows.length));

        new VSpacer(1).paint(context);
        updatableFields.get(updateField.getOption()).update();

        repaint();
    }

    private void updateName() {
        TextInputField nameField = new TextInputField("Enter the staff's new name");
        new TextInput(nameField).read(context, "Enter a non-empty staff name.", input -> !input.trim().isEmpty());

        staff.setName(nameField.getValue());
        staffManager.updateStaff(staff);
    }

    private void updateAge() {
        TextInputField ageField = new TextInputField("Enter the staff's new age");
        new TextInput(ageField).read(context, "Enter a valid age.", input -> InputValidators.validateAge(input));

        staff.setAge(ageField.getAge());
        staffManager.updateStaff(staff);
    }

    private void updatePassword() {
        TextInputField passwordField = new TextInputField("Enter the staff's new password");
        new TextInput(passwordField).read(context, "Enter a valid password.", input -> !input.isEmpty());

        staff.setPassword(passwordField.getValue());
        staffManager.updateStaff(staff);
    }

    private void updateGender() {
        final Gender[] gender = {Gender.MALE};

        new Text("Enter the staff's new gender:", TextStyle.BOLD).paint(context);
        new Menu(
            new MenuOption("Male", () -> gender[0] = Gender.MALE),
            new MenuOption("Female", () -> gender [0]= Gender.FEMALE)
        ).readOption(context);

        staff.setGender(gender[0]);
        staffManager.updateStaff(staff);
    }

    private void updateBirthday() {
        TextInputField birthdayField = new TextInputField("Enter the staff's new birthday (yyyy-MM-dd)");
        new TextInput(birthdayField).read(context, "Enter a valid date.", input -> InputValidators.validateDate(input));

        staff.setDob(birthdayField.getDate("yyyy-MM-dd"));
        staffManager.updateStaff(staff);
    }

    private void updateEmail() {
        TextInputField emailField = new TextInputField("Enter the staff's new email address");
        new TextInput(emailField).read(context, "Enter a valid email address.", input -> InputValidators.validateEmail(input));

        staff.setEmailAddress(emailField.getValue());
        staffManager.updateStaff(staff);
    }

    private void updatePhoneNumber() {
        TextInputField phoneNumberField = new TextInputField("Enter the staff's new phone number");
        new TextInput(phoneNumberField).read(context, "Enter a valid phone number.", input -> InputValidators.validatePhoneNumber(input));

        staff.setPhoneNumber(phoneNumberField.getValue());
        staffManager.updateStaff(staff);
    }

    private void updateSpecialisation() {
        if (staff.getRole() != UserRole.DOCTOR) {
            return;
        }

        final Specialisation[] specialisation= {null};
        new Text("Enter the staff's specialisation:", TextStyle.BOLD).paint(context);

        new Menu(
            Arrays.stream(Specialisation.values()).map(spz -> new MenuOption(spz.getValue(), 
                () -> specialisation[0] = spz)).toArray(MenuOption[]::new)
        ).readOption(context);

        ((Doctor) staff).setSpecialisation(specialisation[0]);
        staffManager.updateStaff(staff);
    }
}
