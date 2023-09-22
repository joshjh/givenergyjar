package dev.joshjh;

import org.json.JSONArray;
import org.json.JSONObject;

public class EventData {
    int length = 0;
    int active_events = 0;
    Event[] Events;

    public class Event {
        String event;
        String startTime;
        String endTime;
        boolean isActive;
        
        public Event(JSONObject JSONObject) {
            this.event = JSONObject.getString("event");
            this.startTime = JSONObject.getString("start_time");
            this.endTime = JSONObject.getString("end_time");
            this.isActive = (this.endTime == null) ? true : false;
            System.out.println("new event created");
        }

        @Override
        public String toString() {
            return (String.format("event: %s, StartTime: %s, EndTime: %s, Active: %b", this.event, this.startTime, this.endTime, this.isActive));

        }
    }

    public EventData(JSONObject Events_JSONObject) {
        JSONArray JSONArray = Events_JSONObject.getJSONArray("data");
        if (JSONArray.length() != 0) {
            this.length = JSONArray.length();
            this.Events = new Event[JSONArray.length()];
            for (int x=0; x < JSONArray.length(); x++) {
                this.Events[x] = new Event(JSONArray.getJSONObject(x));
                if (this.Events[x].endTime == null) {active_events++;}
            }
        }
    }

    public int get_length() {return length;}

    public int get_active_events() {return active_events;}

    public Event[] getEvents() {return Events;}

    public Event[] getActiveEvents() {
        Event[] activeEvents = new Event[active_events];
        int event_head =0;
        for (Event event:Events) {
            if (event.isActive) {
                activeEvents[event_head] = event;
                event_head++;
            }
        }
        return activeEvents;
    }

    public void printEvents() {
        if (get_length() > 0) {
            for (Event event:getEvents()) {
                System.out.println(event);
            }
        }
    }
}


