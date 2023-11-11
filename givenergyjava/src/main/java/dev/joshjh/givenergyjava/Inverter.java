package dev.joshjh.givenergyjava;

import java.io.IOException;
import java.net.URI;

import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.Header;
import org.json.JSONObject;

public class Inverter extends GivEnergyAPI implements InverterInterface{
    protected final static String inverterSerialFile = "inverterSerial.txt";
    protected final static String inverterSerial = Inverter.loadFileValue(inverterSerialFile);
    public static URI inverterURI = GivEnergyAPI.baseUri.resolve(URI.create("inverter/" + inverterSerial +"/"));
      

    public Inverter() {
        System.out.println("Instance of Inverter created");

    }

    @Override
    public JSONObject getPresets() throws IOException{
        URI getInverterSettingURI = Inverter.inverterURI.resolve(URI.create(String.format("presets")));
        System.out.println(getInverterSettingURI.toString());
        Request request = GivEnergyAPI.createGetRequest(getInverterSettingURI).addHeader(GivEnergyAPI.headerAuthorization).addHeader(GivEnergyAPI.headerAccept).addHeader(GivEnergyAPI.headerContentType);
        System.out.println(" request structure:" + request.toString());
        Response response = request.execute();
        return new JSONObject(response.returnContent().asString());
    }

    @Override
    public boolean setPreset(String preset) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPreset'");
    }

    @Override
    public JSONObject getSettingsList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSettingsList'");
    }

    @Override
    public JSONObject getSetting(int settingID) throws IOException {
        URI getInverterSettingURI = Inverter.inverterURI.resolve(URI.create(String.format("settings/%s/read", settingID)));
        System.out.println(getInverterSettingURI.toString());
        Request request = GivEnergyAPI.createPostRequest(getInverterSettingURI);
        System.out.println(" request structure:" + request.toString());
        Response response = request.execute();
        return new JSONObject(response.returnContent().asString());

    }

    @Override
    public boolean setSetting(int settingID, int value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setSetting'");
    }

    @Override
    public boolean setSetting(int settingID, String value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setSetting'");
    }

    @Override
    public boolean setSetting(int settingID, boolean value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setSetting'");
    }

    @Override
    public MeterData getMeterData() throws IOException{
        URI getInverterMeterDataURI = Inverter.inverterURI.resolve(URI.create(String.format("meter-data/latest")));
        Request request = GivEnergyAPI.createGetRequest(getInverterMeterDataURI);
        Response response = request.execute();
        JSONObject JSONObject = new JSONObject(response.returnContent().asString());
        return new MeterData(JSONObject);
    }

    @Override
    public SystemData getSystemData() throws IOException{
        URI getInverterSystemDataURI = Inverter.inverterURI.resolve(URI.create(String.format("system-data/latest")));
        System.out.println(getInverterSystemDataURI.toString());
        Request request = GivEnergyAPI.createGetRequest(getInverterSystemDataURI);
        System.out.println(" request structure:" + request.toString());
        Response response = request.execute();
        JSONObject JSONObject = new JSONObject(response.returnContent().asString());
        return new SystemData(JSONObject);
    }

    @Override
    public EventData getEventData() throws IOException {
        URI getInverterEventDataURI = Inverter.inverterURI.resolve(URI.create(String.format("events?&cleared=1")));
        System.out.println(getInverterEventDataURI.toString());
        Request request = GivEnergyAPI.createGetRequest(getInverterEventDataURI).bodyString("{\"Cleared\": true}", ContentType.APPLICATION_JSON);
        System.out.println(" request structure:" + request.toString());
        Response response = request.execute();
        JSONObject JSONObject = new JSONObject(response.returnContent().asString());
        return new EventData(JSONObject);
    }

    @Override
    public InverterState getInverterHealth() throws IOException{
        if (getEventData().get_active_events() == 0) {
            return InverterInterface.InverterState.STATE_HEALTHY;
        }
        return InverterState.STATE_FAULT;
    }
    
    
}
