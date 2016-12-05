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
    public int eventDay;
    public int eventMonth;
    public int eventYear;
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


    public int getEventDay() { return eventDay; }

    public void setEventDay(int day) { this.eventDay = day; }


    public int getEventMonth() { return eventMonth; }

    public void setEventMonth(int month) { this.eventMonth = month; }


    public int getEventYear() { return eventYear; }

    public void setEventYear(int year) { this.eventYear = year; }


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

    public void setParticipantCount() {
        this.participantCount = participantCount + 1;
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
