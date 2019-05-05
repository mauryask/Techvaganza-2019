package com.pnstech.finalactivity;

public class WorkshopProfile{
    private String workshopName;
    private String workshopDescription;
    private String workshopProfilePic;
    private String workshopVenue;
    private String workshopTiming;
    private String getLink;

    public WorkshopProfile()
    {
    }

    public WorkshopProfile(String workshopName, String workshopDescription, String workshopProfilePic, String workshopVenue, String workshopTiming, String getLink)
    {
        this.workshopName = workshopName;
        this.workshopDescription = workshopDescription;
        this.workshopProfilePic = workshopProfilePic;
        this.workshopVenue = workshopVenue;
        this.workshopTiming = workshopTiming;
        this.getLink = getLink;
    }

    public String getWorkshopName() {
        return workshopName;
    }

    public void setWorkshopName(String workshopName) {
        this.workshopName = workshopName;
    }

    public String getWorkshopDescription() {
        return workshopDescription;
    }

    public void setWorkshopDescription(String workshopDescription) {
        this.workshopDescription = workshopDescription;
    }

    public String getWorkshopProfilePic() {
        return workshopProfilePic;
    }

    public void setWorkshopProfilePic(String workshopProfilePic) {
        this.workshopProfilePic = workshopProfilePic;
    }


    public String getWorkshopVenue() {
        return workshopVenue;
    }

    public void setWorkshopVenue(String workshopVenue) {
        this.workshopVenue = workshopVenue;
    }

    public String getWorkshopTiming() {
        return workshopTiming;
    }

    public void setWorkshopTiming(String workshopTiming) {
        this.workshopTiming = workshopTiming;
    }

    public String getGetLink() {
        return getLink;
    }

    public void setGetLink(String getLink) {
        this.getLink = getLink;
    }
}