package movieTicketBookingSystem.models;
import java.util.List;

public class Theatre {
    String id;
    String name;
    String cityId;
    List<Screen> screens;

    public Theatre() {
    }

    public Theatre(String id, String name, String cityId, List<Screen> screens) {
        this.id = id;
        this.name = name;
        this.cityId = cityId;
        this.screens = screens;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public List<Screen> getScreens() {
        return screens;
    }

    public void setScreens(List<Screen> screens) {
        this.screens = screens;
    }
}