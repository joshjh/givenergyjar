package dev.joshjh;
import java.io.IOException;

import org.json.*;

public interface InverterInterface {

    public enum InverterState {STATE_HEALTHY, STATE_FAULT }
    
    JSONObject getPresets() throws IOException;
    boolean setPreset(String preset) throws IOException;
    JSONObject getSettingsList() throws IOException;
    JSONObject getSetting(int settingID) throws IOException;
    boolean setSetting(int settingID, int value) throws IOException;
    boolean setSetting(int settingID, String value) throws IOException;
    boolean setSetting(int settingID, boolean value) throws IOException;
    MeterData getMeterData() throws IOException;
    SystemData getSystemData() throws IOException;
    EventData getEventData() throws IOException;
    InverterState getInverterHealth() throws IOException;


}
