
import java.io.IOException;
import java.net.URI;

import org.json.JSONArray;
import org.json.JSONObject;

import dev.joshjh.BatteryData;
import dev.joshjh.EventData;
import dev.joshjh.GivEnergyAPI;
import dev.joshjh.Inverter;
import dev.joshjh.InverterData;
import dev.joshjh.InverterInterface;
import dev.joshjh.MeterData;
import dev.joshjh.SystemData;

public class simpleAPItests {
    public static void main(String[] args) {
        System.out.println(Inverter.inverterURI.toString());
        InverterInterface inverter = new Inverter();
        try {
      
            SystemData system_data = inverter.getSystemData();
            InverterData inverter_data = new InverterData(system_data);
            BatteryData battery_data = new BatteryData(system_data);
            EventData event_data = inverter.getEventData();
            event_data.printEvents();
            System.out.println(battery_data);
            System.out.println(inverter_data);
            EventData.Event[] active_events = event_data.getActiveEvents();
            for (EventData.Event event:active_events) {System.out.println(event);}
            MeterData meter_data = inverter.getMeterData();
            System.out.println(meter_data);
            System.out.println(meter_data.meterReadingLocalTime().toString());
            System.out.println(inverter.getInverterHealth());
            System.out.println(inverter_data.getArrayData());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
