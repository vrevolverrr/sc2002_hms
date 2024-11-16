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

public class AdminManageDatabaseView extends View {

    @Override
    public String getViewName() {
        return "Manage Database";
    }

    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Manage Database").paint(context);
        new VSpacer(1).paint(context);

        new Menu(
            new MenuOption("Clear Database", this::clearDatabase),
            new MenuOption("Load from file", this::loadFromFile)
        ).readOption(context);
    }
    
    private void clearDatabase() {
    }

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

        // From here on out the Database class takes over for debug printing
        
        if (status[0]) {
            new Pause("Succesfully loaded data. Press any key to go back.").pause(context);
        } else {
            new Pause("Failed loading data. Press any key to go back.").pause(context);
        }

        Navigator.pop();
    }
}
