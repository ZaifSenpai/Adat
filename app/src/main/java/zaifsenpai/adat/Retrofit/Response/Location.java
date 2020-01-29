
package zaifsenpai.adat.Retrofit.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("title")
    @Expose
    private Object title;
    @SerializedName("name")
    @Expose
    private Object name;
    @SerializedName("city")
    @Expose
    private Object city;
    @SerializedName("country")
    @Expose
    private Object country;
    @SerializedName("position")
    @Expose
    private Position position;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Location() {
    }

    /**
     * 
     * @param country
     * @param city
     * @param name
     * @param position
     * @param title
     */
    public Location(Object title, Object name, Object city, Object country, Position position) {
        super();
        this.title = title;
        this.name = name;
        this.city = city;
        this.country = country;
        this.position = position;
    }

    public Object getTitle() {
        return title;
    }

    public void setTitle(Object title) {
        this.title = title;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getCity() {
        return city;
    }

    public void setCity(Object city) {
        this.city = city;
    }

    public Object getCountry() {
        return country;
    }

    public void setCountry(Object country) {
        this.country = country;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

}
