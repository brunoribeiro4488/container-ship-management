package lapr.project.model;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SummaryTest {

    @Test
    public void testToString() {
        Summary summary = new Summary("210950000", "VARAMO", new Date(999999999), new Date(999999999), "10 horas", 14,15.2, 13.3,12.5,10.8,"8.9554569","45.2484659","9.54946","41.454684",855.0,200.0);
        String expected = String.format("Summary of Ship %s%n%nShip Code = %s%nVessel Name = %s%nStart Base Date Time = %s%nEnd Base Date Time = %s%nTotal Movement Time = %s%nTotal Movements = %d%nMax SOG = %f%nMean SOG = %.2f%nMax COG = %f%nMean COG = %.2f%nDeparture Latitude = %s%nDeparture Longitude = %s%nArrival Latitude = %s%nArrival Longitude = %s%nTravelled Distance = %f Km%nDelta Distance = %f Km", "VARAMO","210950000", "VARAMO", new Date(999999999), new Date(999999999), "10 horas", 14,15.2, 13.3,12.5,10.8,"8.9554569","45.2484659","9.54946","41.454684",855.0,200.0);
        String actual = summary.toString();
        String notExpected = ("Summary of Ship TUSTUMENA\n" +
                "Ship Code= 210950000\n" +
                "Vessel Name= VARAMO\n"  +
                "Start Base Date Time= " + new Date(999999999) + "\n"+
                "End Base Date Time= "  + new Date(999999999) + "\n" +
                "Total Movement Time= 10 horas\n"+
                "Total Movements= 14\n" +
                "Max SOG= " + 15.2 +"\n" +
                "Mean SOG= " + 13.3 +"\n" +
                "Max COG= " + 12.5 +"\n" +
                "Mean COG= " + 10.8 +"\n" +
                "Departure Latitude= " + 8.9554569 +"\n" +
                "Departure Longitude= " + 45.2484659 +"\n" +
                "Arrival Latitude= " + 9.54946 +"\n" +
                "Arrival Longitude= " + 41.454684 +"\n" +
                "Travel Distance= " + 855.0 +"\n" +
                "Delta Distance= " + 200.0);
        Assertions.assertEquals(expected, actual);
        Assertions.assertNotEquals(notExpected, actual);
    }

    @Test
    public void testGetVesselName(){
        Summary summary = new Summary("210950000", "VARAMO", new Date(999999999), new Date(999999999), "10 horas", 14,15.2, 13.3,12.5,10.8,"8.9554569","45.2484659","9.54946","41.454684",855.0,200.0);
        String expected = "VARAMO";
        String notExpected = "ARCTIC SEA";
        String actual = summary.getVesselName();

        Assertions.assertEquals(expected, actual);
        Assertions.assertNotEquals(notExpected, actual);
    }

    @Test
    void testEquals() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        Date date1 = format.parse("30/12/2020 17:03");
        Date date2 = format.parse("31/12/2020 17:03");

        Summary summary1 = new Summary("210950000","VARAMO",date1,date2,"12",2,12.5,12.5,2.4,2.4,"42.92236",
                "-66.97243","42.92236","-66.97243",0.0,0.0);

        Summary summary2 = new Summary("210950001","VARAMO",date1,date2,"12",2,12.5,12.5,2.4,2.4,"42.92236",
                "-66.97243","42.92236","-66.97243",0.0,0.0);
        Ship ship = new Ship("210950000","VARAMO","IMO9355044","C7SQ2","70","166","25","9.5");
        Summary summary3 = summary1;
        Summary summary4 = null;
        Summary summary5 = new Summary("210950001","VARAMO",date1,date2,"12",2,12.5,12.5,2.4,2.4,"42.92236",
                "-66.97243","42.92236","-66.97243",0.0,0.0);

        boolean expected = true;
        Assert.assertEquals(false, summary1.equals(summary2));
        Assert.assertEquals(expected, summary2.equals(summary5));
        Assert.assertEquals(expected, summary1.equals(summary3));
        Assert.assertFalse(summary1.equals(ship));
        Assert.assertFalse(summary1.equals(summary4));










    }
}