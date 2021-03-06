package seedu.address.model;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs {

    private GuiSettings guiSettings;
    private Path inventoryListFilePath = Paths.get("data" , "inventorylist.xml");
    private Path transactionListFilePath = Paths.get("data", "transactionlist.xml");

    public UserPrefs() {
        setGuiSettings(500, 500, 0, 0);
    }

    public GuiSettings getGuiSettings() {
        return guiSettings == null ? new GuiSettings() : guiSettings;
    }

    public void updateLastUsedGuiSetting(GuiSettings guiSettings) {
        this.guiSettings = guiSettings;
    }

    public void setGuiSettings(double width, double height, int x, int y) {
        guiSettings = new GuiSettings(width, height, x, y);
    }

    public Path getInventoryListFilePath() {
        return inventoryListFilePath;
    }

    public void setInventoryListFilePath(Path inventoryListFilePath) {
        this.inventoryListFilePath = inventoryListFilePath;
    }

    public Path getTransactionListFilePath() {
        return transactionListFilePath;
    }

    public void setTransactionListFilePath(Path transactionListFilePath) {
        this.transactionListFilePath = transactionListFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return Objects.equals(guiSettings, o.guiSettings)
                && Objects.equals(inventoryListFilePath, o.inventoryListFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, inventoryListFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings.toString());
        sb.append("\nLocal data file location : " + inventoryListFilePath);
        return sb.toString();
    }

}
