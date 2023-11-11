package dev.joshjh.givenergyjava;

import org.json.JSONObject;

public class BatteryData {
    private JSONObject JSONBatteryData;
    private int percent;
    private int power;
    private int temperature;

    public BatteryData(SystemData system_data) {
        this.JSONBatteryData = system_data.getJSONObject("data").getJSONObject("battery");
        this.percent = JSONBatteryData.getInt("percent");
        this.temperature = JSONBatteryData.getInt("temperature");
        this.power = JSONBatteryData.getInt("power");
    }
    
    public int getPercent() {return this.percent;}

    public int getTemperature() {return this.temperature;}

    public int getPower() {return this.power;}

    @Override
    public String toString() {
        return String.format("Percent: %d, temperature %d, power %d", this.percent, this.temperature, this.power);
    }
}
