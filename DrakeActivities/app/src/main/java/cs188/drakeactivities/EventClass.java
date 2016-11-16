package cs188.drakeactivities;

/**
 * Created by ColtinLux on 11/16/16.
 */

public class EventClass
{
    public String eventTitle;
    public String organizationUsername;
    public String eventTime;
    public int positionTime;
    public String eventDate;
    public double longitude;
    public double latitude;
    public String eventDescription;
    public int eventIcon;
    public int participantCount;
    public String eventCode;

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String title) {
        this.eventTitle = title;
    }


    public String getOrganizationUsername() {
        return organizationUsername;
    }

    public void setOrganizationUsername(String organizationUsername) { this.organizationUsername = organizationUsername; }


    public String getEventTime() { return eventTime; }

    public void setEventTime(String time) {
        this.eventTitle = time;
    }


    public int getPositionTime() { return positionTime; }

    public void setPositionTime(int time) {
        this.positionTime = time;
    }


    public String getEventDate() { return eventDate; }

    public void setEventDate(String date) { this.eventDate = date; }


    public double getLongitude() { return longitude; }

    public void setLongitude(double lon) {
        this.longitude = lon;
    }


    public double getLatitude() { return latitude; }

    public void setLatitude(double lat) {
        this.latitude = lat;
    }


    public String getEventDescription() { return eventDescription; }

    public void setEventDescription(String description) { this.eventDescription = description; }


    public int getParticipantCount() { return participantCount; }

    public void setParticipantCount(int count) {
        this.participantCount = count;
    }


    public String getEventCode() { return eventCode; }

    public void setEventCode(String code) {
        this.eventCode = code;
    }


    public int getEventIcon() { return eventIcon; }

    public void setEventIcon(int icon) {
        this.eventIcon = icon;
    }
}
