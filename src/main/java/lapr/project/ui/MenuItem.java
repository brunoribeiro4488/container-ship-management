package lapr.project.ui;

import java.util.Objects;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class MenuItem {
    private final String description;
    private final Runnable ui;

    public MenuItem(String description,  Runnable ui)
    {
        if (Objects.isNull(ui))
            throw new IllegalArgumentException("MenuItem does not support a null UI.");

        this.description = description;
        this.ui = ui;
    }


    public void run()
    {
        this.ui.run();
    }

    public boolean hasDescription(String description)
    {
        return this.description.equals(description);
    }

    public String toString()
    {
        return this.description;
    }

}
