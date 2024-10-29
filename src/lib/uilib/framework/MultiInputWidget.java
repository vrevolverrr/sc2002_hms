package lib.uilib.framework;

import java.util.function.*;

public interface MultiInputWidget {
    public void readAll(BuildContext context, Predicate<String[]> predicate, String failedMessage);
} 