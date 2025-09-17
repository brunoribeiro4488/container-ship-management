package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountryTest {

    @Test
    void getContinent() {
        Country c1 = new Country("Europe","PT","PRT","Portugal","10.31","Lisbon","38.71666667","-9.133333");
        String result = "Europe";
        assertEquals(c1.getContinent(),result);
    }

    @Test
    void getAlpha2_Code() {
        Country c1 = new Country("Europe","PT","PRT","Portugal","10.31","Lisbon","38.71666667","-9.133333");
        String result = "PT";
        assertEquals(c1.getAlpha2Code(),result);
    }

    @Test
    void getAlpha3_Code() {
        Country c1 = new Country("Europe","PT","PRT","Portugal","10.31","Lisbon","38.71666667","-9.133333");
        String result = "PRT";
        assertEquals(c1.getAlpha3Code(),result);
    }

    @Test
    void getCountry() {
        Country c1 = new Country("Europe","PT","PRT","Portugal","10.31","Lisbon","38.71666667","-9.133333");
        String result = "Portugal";
        assertEquals(c1.getCountry(),result);
    }

    @Test
    void getPopulation() {
        Country c1 = new Country("Europe","PT","PRT","Portugal","10.31","Lisbon","38.71666667","-9.133333");
        String result = "10.31";
        assertEquals(c1.getPopulation(),result);
    }

    @Test
    void getCapital() {
        Country c1 = new Country("Europe","PT","PRT","Portugal","10.31","Lisbon","38.71666667","-9.133333");
        String result = "Lisbon";
        assertEquals(c1.getCapital(),result);
    }

    @Test
    void getLatitude() {
        Country c1 = new Country("Europe","PT","PRT","Portugal","10.31","Lisbon","38.71666667","-9.133333");
        String result = "38.71666667";
        assertEquals(c1.getLatitude(),result);
    }

    @Test
    void getLongitude() {
        Country c1 = new Country("Europe","PT","PRT","Portugal","10.31","Lisbon","38.71666667","-9.133333");
        String result = "-9.133333";
        assertEquals(c1.getLongitude(),result);
    }

    @Test
    void toStringTest(){
        Country c1 = new Country("Europe","PT","PRT","Portugal","10.31","Lisbon","38.71666667","-9.133333");
        String result= "Country Portugal";
        assertEquals(c1.toString(), result);
    }
}