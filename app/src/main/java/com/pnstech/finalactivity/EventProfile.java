package com.pnstech.finalactivity;

public class EventProfile {
    private String eventName;
    private String eventDescription;
    private String eventProfilePic;
    private String eventVenue;
    private String eventTiming;
    private String getLink;

    public EventProfile()
    {
    }

    public EventProfile(String eventName, String eventDescription, String eventProfilePic, String eventVenue, String eventTiming, String getLink)
    {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventProfilePic = eventProfilePic;

        this.eventVenue = eventVenue;
        this.eventTiming = eventTiming;
        this.getLink = getLink;
    }


    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventProfilePic() {
        return eventProfilePic;
    }

    public void setEventProfilePic(String eventProfilePic) {
        this.eventProfilePic = eventProfilePic;
    }


    public String getEventVenue() {
        return eventVenue;
    }

    public void setEventVenue(String eventVenue) {
        this.eventVenue = eventVenue;
    }

    public String getEventTiming() {
        return eventTiming;
    }

    public void setEventTiming(String eventTiming) {
        this.eventTiming = eventTiming;
    }

    public String getGetLink() {
        return getLink;
    }

    public void setGetLink(String getLink) {
        this.getLink = getLink;
    }
}