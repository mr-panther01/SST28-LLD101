package movieTicketBookingSystem.models;

public class Movie {
    String id;
    String title;
    String language;
    int durationInMins;

    public Movie() {
    }

    public Movie(String id, String title, String language, int durationInMins) {
        this.id = id;
        this.title = title;
        this.language = language;
        this.durationInMins = durationInMins;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getDurationInMins() {
        return durationInMins;
    }

    public void setDurationInMins(int durationInMins) {
        this.durationInMins = durationInMins;
    }
}