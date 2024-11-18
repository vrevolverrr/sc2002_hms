package view.Admin.database;

import java.util.List;

import lib.uilib.framework.MenuOption;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Pause;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.base.VSpacer;
import services.Navigator;
import utils.Database;
import utils.Database.LoadableFile;
import view.View;
import view.widgets.Title;

/**
 * The {@link AdminManageDatabaseView} class is responsible for providing an interface
 * to manage the database from the admin's perspective. It allows the admin to load
 * data from a file into the application.

 * The class extends {@link View} and implements the rendering logic for the 
 * "Manage Database" section of the application.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-10-29
 */
public class AdminManageDatabaseView extends View {

    /**
     * Returns the name of the view, which is "Manage Database".
     * 
     * @return The name of the view.
     */
    @Override
    public String getViewName() {
        return "Manage Database";
    }

    /**
     * Renders the "Manage Database" view, which includes:
     * <ul>
     * <li>Breadcrumb navigation</li>
     * <li>Title for "Manage Database"</li>
     * <li>A menu with an option to load data from a file</li>
     * </ul>
     * 
     * This method initializes and paints the user interface components for 
     * managing the database.
     */
    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Manage Database").paint(context);
        new VSpacer(1).paint(context);

        new Menu(
            new MenuOption("Load from file", this::loadFromFile)
        ).readOption(context);
    }

    /**
     * Handles the loading of data from a file. It displays a list of available
     * loadable files, allows the user to choose a file, and attempts to load the
     * selected file into the database.
     * 
     * <p>If the load operation is successful, a success message is displayed. If
     * the load operation fails, an error message is shown. After the operation is
     * complete, the user can press any key to go back to the previous view.</p>
     */
    private void loadFromFile() {
        clearLines(7);
        new VSpacer(1).paint(context);
        new Title("Load from file").paint(context);
        new VSpacer(1).paint(context);

        new Title("Choose a file to load from").paint(context);
        new VSpacer(1).paint(context);
        boolean[] status = {false};

        List<LoadableFile> loadableFiles = Database.getLoadableFiles();

        if (loadableFiles.isEmpty()) {
            new Text("No files from current directory to load from.").paint(context);
            new Text("Possible file names: " + String.join(", ", Database.candidateFileNames), TextStyle.BOLD).paint(context);
            new Pause("Press any key to go back.").pause(context);
            repaint();
            return;
        }

        new Menu(
            loadableFiles.stream()
                .map(file -> new MenuOption(file.getFileName(), () -> status[0] = Database.load(file)))
                .toArray(MenuOption[]::new)
        ).readOption(context);
        
        if (status[0]) {
            new Pause("Succesfully loaded data. Press any key to go back.").pause(context);
        } else {
            new Pause("Failed loading data. Press any key to go back.").pause(context);
        }

        Navigator.pop();
    }
}
