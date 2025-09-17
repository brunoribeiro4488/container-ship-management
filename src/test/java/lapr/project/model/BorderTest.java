package lapr.project.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BorderTest {

    @Test
    void getCountry1() {
        Country c1 = new Country("Europe","PT","PRT","Portugal","10.31","Lisbon","38.71666667","-9.133333");
        Border b = new Border("Portugal","Spain");
        assertEquals(b.getCountry1(), c1.getCountry());
    }

    @Test
    void getCountry2() {
        Country c2 = new Country("Europe","ES","ESP","Spain","46.53","Madrid","40.4","-3.683333");
        Border b = new Border("Portugal","Spain");
        assertEquals(b.getCountry2(), c2.getCountry());
    }
}