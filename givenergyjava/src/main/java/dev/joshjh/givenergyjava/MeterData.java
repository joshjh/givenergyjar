package dev.joshjh.givenergyjava;

import org.json.JSONObject;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class MeterData {
    private String time;
    private double today_solar;
    private double today_grid_import;
    private double today_grid_export;
    private double today_battery_charge;
    private double today_battery_discharge;
    private double today_consumption;
    private double total_solar;
    private double total_grid_import;
    private double total_grid_export;
    private double total_battery_charge;
    private double total_battery_discharge;
    private double total_consumption;
    private ZoneId zoneID = ZoneId.systemDefault();

    public MeterData(JSONObject JSONObject) {
        time = JSONObject.getJSONObject("data").getString("time");
        JSONObject today_JSONObject = JSONObject.getJSONObject("data").getJSONObject("today");
        today_solar = today_JSONObject.getDouble("solar");
        today_grid_import = today_JSONObject.getJSONObject("grid").getDouble("import");
        today_grid_export= today_JSONObject.getJSONObject("grid").getDouble("export");
        today_battery_charge = today_JSONObject.getJSONObject("battery").getDouble("charge");
        today_battery_discharge = today_JSONObject.getJSONObject("battery").getDouble("discharge");
        today_consumption = today_JSONObject.getDouble("consumption");
        JSONObject total_JSONObject = JSONObject.getJSONObject("data").getJSONObject("total");
        total_solar = total_JSONObject.getDouble("solar");
        total_grid_import = total_JSONObject.getJSONObject("grid").getDouble("import");
        total_grid_export = total_JSONObject.getJSONObject("grid").getDouble("export");
        total_battery_charge= total_JSONObject.getJSONObject("battery").getDouble("charge");
        total_battery_discharge= total_JSONObject.getJSONObject("battery").getDouble("discharge");
        total_consumption = total_JSONObject.getDouble("consumption");
    }

    public String meterReadingTime() {return LocalDateTime.ofInstant(Instant.parse(time), zoneID).toString();}
    public LocalDateTime meterReadingLocalTime() {
        LocalDateTime meterLocalDateTime = LocalDateTime.ofInstant(Instant.parse(time), zoneID);
        return (meterLocalDateTime);
    }
    public double solarToday() {return today_solar;}
    public double gridImportToday() {return today_grid_import;}
    public double gridExportToday() {return today_grid_export;}
    public double batteryChargeToday() {return today_battery_charge;}
    public double batteryDischargeToday() {return today_battery_discharge;}
    public double consumptionToday() {return today_consumption;}
    public double solarTotal() {return total_solar;}
    public double gridTotalExport() {return total_grid_export;}
    public double gridTotalImport() {return total_grid_import;}
    public double batteryTotalCharge() {return total_battery_charge;}
    public double batteryTotalDischarge() {return total_battery_discharge;}
    public double consumptionTotal() {return total_consumption;}

    @Override
    public String toString() {
        return (String.format("Today Solar: %.2f, Today Grid_Import: %.2f, Today Grid_Export: %.2f, Last Meter Reading: %s", solarToday(), gridImportToday(), gridExportToday(), meterReadingTime()));
    }

}
