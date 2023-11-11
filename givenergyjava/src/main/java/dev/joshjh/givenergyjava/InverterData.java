package dev.joshjh.givenergyjava;

import org.json.JSONArray;
import org.json.JSONObject;

public class InverterData {
    private int temperature;
    private int power;
    private double output_voltage;
    private double output_frequency;
    private int eps_power;
    private JSONObject InverterDataJSON;
    private JSONObject SolarDataJSON;
    private int solarPower;
    private int numArrays;
    private JSONArray solarArrayData;

    public InverterData(SystemData sysdataobject) {
        InverterDataJSON = sysdataobject.getJSONObject("data").getJSONObject("inverter");
        SolarDataJSON = sysdataobject.getJSONObject("data").getJSONObject("solar");
        temperature = InverterDataJSON.getInt("temperature");
        power = InverterDataJSON.getInt("power");
        output_frequency = InverterDataJSON.getDouble("output_frequency");
        output_voltage = InverterDataJSON.getDouble("output_voltage");
        eps_power = InverterDataJSON.getInt("eps_power");
        solarPower = SolarDataJSON.getInt("power");
        numArrays = SolarDataJSON.getJSONArray("arrays").length();
        solarArrayData = SolarDataJSON.getJSONArray("arrays");
    }

    public int getTemperature() {return this.temperature;}

    public int getPower() {return this.power;}

    public int getEpsPower() {return this.eps_power;}

    public double getOutputVoltage() {return this.output_voltage;}

    public double getOutputFrequency() {return this.output_frequency;}

    public int getSolarPower() {return solarPower;}

    public int getNumSolarArrays() {return numArrays;}

    public String getArrayData() {
        return String.format("Number of Arrays: %d, ArrayData: %s", getNumSolarArrays(), solarArrayData.toString());
    }

    @Override
    
    public String toString() {return String.format("Inverter Data: temperature: %d, power: %d, output_freq: %.2f, output_volts: %.2f, eps_power: %d", this.temperature, this.power, this.output_frequency, this.output_voltage, this.eps_power);}


}
