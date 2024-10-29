package lib.uilib.framework;

import java.util.function.*;

public interface InputWidget {
    public void read(BuildContext context, Predicate<String> predicate);
}
