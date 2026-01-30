package frc.robot;

import java.util.ArrayList;

import edu.wpi.first.util.sendable.SendableBuilder;

public class Selector {

    private int currentSelection;
    private String currentSelectionName;
    private ArrayList<String> selections;
    private String filterCriteria = "";

    /**
     * Basic constructor.
     */
    public Selector() {
        this.currentSelection = 0;
        this.currentSelectionName = "";
        this.selections = new ArrayList<String>();
    }

    /**
     * Constructor with a predefined list passed in as a parameter.
     * @param selections
     */
    public Selector(ArrayList<String> selections) {
        this();
        this.selections = selections;
    }

    public Selector

    public void initSendable(SendableBuilder builder) {
        builder.addStringProperty("Current Selection", this::getCurrentSelectionName, null);
        builder.addStringProperty("Fliter String", () -> {return this.filterCriteria;}, null);
    }
}
