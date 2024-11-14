package lib.uilib.widgets.base;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.Widget;
import lib.uilib.framework.enums.Alignment;
import lib.uilib.framework.enums.Border;
import lib.uilib.widgets.layout.Column;
import services.Navigator;

public class Breadcrumbs extends Widget{

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
