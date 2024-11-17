package view.Patient.update;

import java.util.ArrayList;
import java.util.List;

import controller.interfaces.IPatientManager;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.TextInputField;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.EnumeratedTable;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.enums.Gender;
import model.users.Patient;
import services.ServiceLocator;
import utils.InputValidators;
import utils.UpdatableField;
import view.View;
import view.widgets.Title;

/**
 * View for updating patient details.
 */
public final class PatientUpdateDetailsView extends View {
    /**
     * Manager for handling patient-related operations.
     */
    private final IPatientManager patientManager = ServiceLocator.getService(IPatientManager.class);

    /**
     * The patient whose details are being updated.
     * @param patient The patient whose details are being updated.
     */
    private final Patient patient;

    /**
     * Constructs a new view for updating patient details.
     * @param patient The patient whose details are being updated.
     */
    public PatientUpdateDetailsView(Patient patient) {
        this.patient = patient;
    }

    /**
     * Returns the name of the view.
     * @return The name of the view.
     */
    @Override
    public String getViewName() {
        return "Update Patient Details";
    }

    /**
     * Renders the view to update patient details.
     * The view shows a table with the patient's details and prompts the user to choose a field to update.
     */
    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Update Patient Details").paint(context);

        List<UpdatableField> updatableFields = new ArrayList<UpdatableField>();
        updatableFields.add(new UpdatableField(new TableRow("Name", patient.getName()), this::updateName));
        updatableFields.add(new UpdatableField(new TableRow("Age", String.valueOf(patient.getAge())), this::updateAge));
        updatableFields.add(new UpdatableField(new TableRow("Password", patient.getPassword()), this::updatePassword));
        updatableFields.add(new UpdatableField(new TableRow("Gender", patient.getGender().toString()), this::updateGender));
        updatableFields.add(new UpdatableField(new TableRow("Birthday", patient.getDob().toString()), this::updateBirthday));
        updatableFields.add(new UpdatableField(new TableRow("Email Address", patient.getEmailAddress()), this::updateEmail));
        updatableFields.add(new UpdatableField(new TableRow("Phone Number", patient.getPhoneNumber()), this::updatePhoneNumber));

        TableRow[] detailRows = updatableFields.stream().map(UpdatableField::getRecord).toArray(TableRow[]::new);
        EnumeratedTable.headerless(detailRows).paint(context);
        new VSpacer(1).paint(context);

        TextInputField updateField = new TextInputField(
            String.format("Choose field to update (1-%d)", detailRows.length));

        new TextInput(updateField).read(context, "Choose a valid field to update.", input -> 
            InputValidators.validateRange(input, detailRows.length));

        new VSpacer(1).paint(context);
        updatableFields.get(updateField.getOption()).update();

        repaint();
    }

    /**
     * Updates the patient's name.
     */
    private void updateName() {
        TextInputField nameField = new TextInputField("Enter your new name");
        new TextInput(nameField).read(context, "Enter a non-empty name.", input -> !input.trim().isEmpty());

        patient.setName(nameField.getValue());
        patientManager.updatePatient(patient);
    }

    /**
     * Updates the patient's age.
     */
    private void updateAge() {
        TextInputField ageField = new TextInputField("Enter the your new age");
        new TextInput(ageField).read(context, "Enter a valid age.", input -> InputValidators.validateAge(input));

        patient.setAge(ageField.getAge());
        patientManager.updatePatient(patient);
    }

    /**
     * Updates the patient's password.
     */
    private void updatePassword() {
        TextInputField passwordField = new TextInputField("Enter the your new password");
        new TextInput(passwordField).read(context, "Enter a valid password.", input -> !input.isEmpty());

        patient.setPassword(passwordField.getValue());
        patientManager.updatePatient(patient);
    }

    /**
     * Updates the patient's gender.
     */
    private void updateGender() {
        final Gender[] gender = {Gender.MALE};

        new Text("Enter the your new gender:", TextStyle.BOLD).paint(context);
        new Menu(
            new MenuOption("Male", () -> gender[0] = Gender.MALE),
            new MenuOption("Female", () -> gender [0]= Gender.FEMALE)
        ).readOption(context);

        patient.setGender(gender[0]);
        patientManager.updatePatient(patient);
    }

    /**
     * Updates the patient's birthday.
     */
    private void updateBirthday() {
        TextInputField birthdayField = new TextInputField("Enter the your new birthday (yyyy-MM-dd)");
        new TextInput(birthdayField).read(context, "Enter a valid date.", input -> InputValidators.validateDate(input, "yyyy-MM-dd"));

        patient.setDob(birthdayField.getDate("yyyy-MM-dd"));
        patientManager.updatePatient(patient);
    }

    /**
     * Updates the patient's email address.
     */
    private void updateEmail() {
        TextInputField emailField = new TextInputField("Enter the your new email address");
        new TextInput(emailField).read(context, "Enter a valid email address.", input -> InputValidators.validateEmail(input));

        patient.setEmailAddress(emailField.getValue());
        patientManager.updatePatient(patient);
    }

    /**
     * Updates the patient's phone number.
     */
    private void updatePhoneNumber() {
        TextInputField phoneNumberField = new TextInputField("Enter the your new phone number");
        new TextInput(phoneNumberField).read(context, "Enter a valid phone number.", input -> InputValidators.validatePhoneNumber(input));

        patient.setPhoneNumber(phoneNumberField.getValue());
        patientManager.updatePatient(patient);
    }
}
