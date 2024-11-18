package lib.uilib.widgets.base;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.Widget;
import lib.uilib.framework.enums.Alignment;
import lib.uilib.framework.enums.Border;
import lib.uilib.widgets.layout.Column;
import services.Navigator;


/**
 * A widget that displays the breadcrumbs of the current view.
 * The breadcrumbs are displayed as a text widget.
 * 
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public class Breadcrumbs extends Widget{

    /**
     * Default constructor for the Breadcrumbs class.
     */
    public Breadcrumbs() {}

    /**
     * Builds the breadcrumbs widget.
     * 
     * @param context The build context.
     * @return The breadcrumbs widget.
     */
    @Override
    public String build(BuildContext context) {
        return new Column(
            new Container.Builder(
            new Text(Navigator.getBreadcrumbs()))
            .setWidth(context.getWidth())
            .setHeight(2)
            .setVerticalAlignment(Alignment.CENTER)
            .setHorizontalAlignment(Alignment.CENTER)
            .setBorder(Border.DOUBLE)
            .buildContainer(context),

            new VSpacer(1)

        ).build(context);
    }
    
}
