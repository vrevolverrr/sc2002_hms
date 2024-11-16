package view.Admin.database;

import lib.uilib.framework.MenuOption;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.VSpacer;
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
        clearLines(2);
        new VSpacer(1).paint(context);
        new Title("Load from file").paint(context);
        new VSpacer(1).paint(context);

        Database

        new Title("Choose a file to load from").paint(context);
        new Menu(
            new MenuOption()
        ).readOption(context);
    }
}
