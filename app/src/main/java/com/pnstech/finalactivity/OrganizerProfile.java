package com.pnstech.finalactivity;

public class OrganizerProfile {
    private String name;
    private String teamName;
    private String profilePic;


    public OrganizerProfile() {}

    public OrganizerProfile(String name, String teamName, String profilePic)
    {
        this.name = name;
        this.teamName = teamName;
        this.profilePic = profilePic;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

}
