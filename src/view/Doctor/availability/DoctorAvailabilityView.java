package view.Doctor.availability;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import controller.DoctorManager;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.TextInputField;
import lib.uilib.framework.Widget;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Table;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.availability.TimePeriod;
import model.users.Doctor;
import services.Navigator;
import utils.InputValidators;
import view.View;
import view.widgets.Title;

/**
 * {@link DoctorAvailabilityView} is a {@link View} that allows doctors to manage
 * their availability. Doctors can view their general and specific availability,
 * update their availability, or clear their availability for a specific day or date.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public class DoctorAvailabilityView extends View {
    /**
     * An instance of {@link DoctorManager} used to manage doctors.
     */
    private final DoctorManager doctorManager = DoctorManager.getInstance(DoctorManager.class);

    /**
     * The {@link Doctor} for whom the view is managing availability.
     */
    private final Doctor doctor;

    /**
     * Constructs a new {@link DoctorAvailabilityView} for a given doctor.
     *
     * @param doctor the {@link Doctor} whose availability is being managed.
     */
    public DoctorAvailabilityView(Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * Returns the name of the view.
     *
     * @return a {@link String} representing the view name, "Availability".
     */
    @Override
    public String getViewName() {
        return "Availability";
    }

    /**
     * Renders the "Availability" view for the doctor.
     * <p>
     * The rendering process includes:
     * <ol>
     * <li>Displaying the general availability of the doctor.</li>
     * <li>Displaying the specific availability of the doctor.</li>
     * <li>Providing options to update general availability, update specific availability, clear availability, or go back.</li>
     * </ol>
     */
    @Override
    public void render() {
        new Breadcrumbs().paint(context);

        new Title("General Availability").paint(context);
        buildGeneralAvailabilityTable().paint(context);
        new VSpacer(1).paint(context);

        new Title("Specific Availability").paint(context);
        buildSpecificAvailabilityTable().paint(context);

        new VSpacer(1).paint(context);

        new Menu(
            new MenuOption("Update General Availability", this::promptUpdateGeneralAvailability),
            new MenuOption("Update Specific Availability", this::promptUpdateSpecificAvailability),
            new MenuOption("Clear Availability", this::promptClearAvailability),
            new MenuOption("Back", () -> Navigator.pop())
        ).readOption(context);

        repaint();
    }

    /**
     * Prompts the doctor to update their general availability.
     */
    private void promptUpdateGeneralAvailability() {
        clearLines(6);
        new Text("Update General Availability", TextStyle.BOLD).paint(context);
        new VSpacer(1).paint(context);

        TextInputField dayField = new TextInputField("Enter the day of the week to update (1-7)");
        new TextInput(dayField).read(context, "Enter a valid day of the week.",
            (input) -> InputValidators.validateRange(input, 7));

        clearLine();
        new Text("Updating availability for " + DayOfWeek.of(dayField.getInt()), TextStyle.BOLD).paint(context);
        new VSpacer(1).paint(context);

        TextInputField startField = new TextInputField("Enter start time (HH:mm)");
        new TextInput(startField).read(context, "Enter a valid time in the 24 hour format.",
            (input) -> InputValidators.validateTime(input));

        TextInputField endField = new TextInputField("Enter the end time (HH:mm)");
        new TextInput(endField).read(context, "Enter a valid time in the 24 hour format.",
            (input) -> InputValidators.validateTime(input));

        doctorManager.setDoctorAvailability(doctor, 
            DayOfWeek.of(dayField.getInt()), new TimePeriod(startField.getTime(), endField.getTime()));
    }

    /**
     * Prompts the doctor to update their specific availability.
     */
    private void promptUpdateSpecificAvailability() {
        clearLines(6);
        new Text("Update Specific Availability", TextStyle.BOLD).paint(context);
        new VSpacer(1).paint(context);

        TextInputField dateField = new TextInputField("Enter a specific date to indicate availability (dd/mm/yy)");
        new TextInput(dateField).read(context, "Choose a valid date starting today.",
            (input) -> InputValidators.validateFutureDate(input));

        clearLine();
        new Text("Updating availability for " + dateField.getDate().
            format(DateTimeFormatter.ofPattern("dd/MM/yy")), TextStyle.BOLD).paint(context);   
        
        new VSpacer(1).paint(context);

        TextInputField startField = new TextInputField("Enter start time (HH:mm)");
        new TextInput(startField).read(context, "Enter a valid time in the 24 hour format.",
            (input) -> InputValidators.validateTime(input));

        TextInputField endField = new TextInputField("Enter end time (HH:mm)");
        new TextInput(endField).read(context, "Enter a valid time in the 24 hour format.",
            (input) -> InputValidators.validateTime(input));

        doctorManager.setDoctorAvailability(doctor, 
        dateField.getDate(), new TimePeriod(startField.getTime(), endField.getTime()));
    }

    /**
     * Prompts the doctor to clear their availability.
     */
    private void promptClearAvailability() {
        clearLines(6);
        new Text("Clear Availability", TextStyle.BOLD).paint(context);
        new VSpacer(1).paint(context);

        TextInputField daydateField = new TextInputField("Enter the day of the week (1-7) or specific date (dd/mm/yy) to clear");
        new TextInput(daydateField).read(context, "Enter a valid day of the week or specific date.",
            input -> InputValidators.validateFutureDate(input) || 
            InputValidators.validateRange(input, DayOfWeek.values().length));

        if (InputValidators.validateFutureDate(daydateField.getValue())) {
            LocalDate date = LocalDate.parse(daydateField.getValue(), DateTimeFormatter.ofPattern("dd/MM/yy"));
            doctorManager.clearDoctorAvailability(doctor, date);

        } else {
            DayOfWeek day = DayOfWeek.of(daydateField.getInt());
            doctorManager.clearDoctorAvailability(doctor, day);
        }        
    }

    /**
     * Builds a table displaying the general availability of the doctor.
     *
     * @return a {@link Widget} representing the general availability table.
     */
    private Widget buildGeneralAvailabilityTable() {
        final Map<DayOfWeek, TimePeriod> availability = doctor.getAvailability().getGeneralAvailability();

        return new Table(
            new TableRow("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"),
            new TableRow(Stream.of(DayOfWeek.values())
            .map((day -> availability.get(day).getFormattedPeriod()))
            .toArray(String[]::new))
        );
    }

    /**
     * Builds a table displaying the specific availability of the doctor.
     *
     * @return a {@link Widget} representing the specific availability table.
     */
    private Widget buildSpecificAvailabilityTable() {
        final Map<LocalDate, TimePeriod> availability = 
            new TreeMap<>(doctor.getAvailability().getSpecificAvailability());

        if (availability.isEmpty()) {
            return new Table(new TableRow("No specific availability set."));
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy (EEEE)");

        ArrayList<TableRow> rows = new ArrayList<TableRow>();
        rows.add(new TableRow("Date", "Availability"));

        availability.forEach((date, timePeriod) -> {
            rows.add(new TableRow(date.format(formatter), timePeriod.getFormattedPeriod()));
        });

        return new Table(rows.toArray(TableRow[]::new));
    }
}