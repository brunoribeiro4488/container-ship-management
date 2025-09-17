package lapr.project.model;

public class Border {

    private final String country1;
    private final String country2;

    public Border(String country1, String country2) {
        this.country1 = country1;
        this.country2 = country2;
    }

    public String getCountry1() {
        return country1;
    }

    public String getCountry2() {
        return country2;
    }

}
