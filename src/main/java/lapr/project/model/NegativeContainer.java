package lapr.project.model;

public class NegativeContainer {
    String outerWalls = "Stainless steel";
    String middleLayers = "Glass wool";
    String interiorWalls = "Expanded cork";
    double condutivityOuterWalls;
    double condutivityMiddleLayers;
    double condutivityInteriorWalls;
    double thickness = 0.1;
    double temperature = -5;

    public NegativeContainer(){
        this.condutivityInteriorWalls=0.02225;
        this.condutivityOuterWalls=15;
        this.condutivityMiddleLayers=0.040;
    }

    public double getThermicalResistance(){
        double thermalResistanceOuterWalls = thickness/condutivityOuterWalls;
        double thermalResistanceMiddleLayers = thickness/condutivityMiddleLayers;
        double thermalResistanceInteriorWalls = thickness/condutivityInteriorWalls;
        return thermalResistanceInteriorWalls+thermalResistanceMiddleLayers+thermalResistanceOuterWalls;
    }

    public String getOuterWalls(){
        return outerWalls;
    }

    public String getInteriorWalls(){
        return interiorWalls;
    }

    public String getMiddleLayers(){
        return middleLayers;
    }

    public double getTemperature() { return temperature; }
}
