package lapr.project.model;

public class Country {
    private final String continent;
    private final String alpha2Code;
    private final String alpha3Code;
    private final String countryCountry;
    private final String population;
    private final String capital;
    private final String latitude;
    private final String longitude;

    public Country(String continent, String alpha2Code, String alpha3Code, String country, String population, String capital, String latitude, String longitude) {
        this.continent = continent;
        this.alpha2Code = alpha2Code;
        this.alpha3Code = alpha3Code;
        this.countryCountry = country;
        this.population = population;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getContinent() {
        return continent;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public String getCountry() {
        return countryCountry;
    }

    public String getPopulation() {
        return population;
    }

    public String getCapital() {
        return capital;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String toString(){
        return String.format("Country %s",this.getCountry());
    }

}
