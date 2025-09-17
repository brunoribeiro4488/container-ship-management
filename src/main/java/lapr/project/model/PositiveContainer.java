package lapr.project.model;

public class PositiveContainer {
    String outerWalls = "Stainless steel";
    String middleLayers = "Polyurethane foam";
    String interiorWalls = "Soft-board wood";
    double condutivityOuterWalls;
    double condutivityMiddleLayers;
    double condutivityInteriorWalls;
    double thickness = 0.1;
    double temperature = 7;

    public PositiveContainer(){
        this.condutivityInteriorWalls=0.08;
        this.condutivityOuterWalls=15;
        this.condutivityMiddleLayers=0.030;
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
