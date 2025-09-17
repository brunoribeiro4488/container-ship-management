//package lapr.project.ui;
//
//import lapr.project.controller.*;
//import lapr.project.data.ConnectionFactory;
//import lapr.project.data.DatabaseConnection;
//import lapr.project.data.ShipStoreDB;
//import lapr.project.model.Company;
//
//import java.io.File;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.text.ParseException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class DemonstrationSprint2 {
//
//    private static final String MMSI="123456789";
//    public static void main(String[] args) throws IOException, ParseException, SQLException {
//        Company company = App.getInstance().getCompany();
//        DatabaseConnection databaseConnection = null;
//        try {
//            databaseConnection = ConnectionFactory.getInstance()
//                    .getDatabaseConnection();
//        } catch (IOException exception) {
//            Logger.getLogger(ShipStoreDB.class.getName())
//                    .log(Level.SEVERE, null, exception);
//        }
//
//        //US201
//        ImportPortController importPortController = new ImportPortController(company);
//        System.out.println("Loading...");
//        File f = new File("sports.csv");
//        importPortController.read(f);
//
//        //US202
//        FindPortController fpController = new FindPortController();
//        fpController.getClosestPort("C4SQ2","21/02/2021 00:01:01");
//
//        //US204
//        CurrentSituationController csController = new CurrentSituationController();
//        csController.currentSituation(databaseConnection,"12345678910","1234");
//
//        //US205
//        GetOffLoadContainersController offloadController = new GetOffLoadContainersController();
//        offloadController.getContainersToOffLoadOnNextPort(databaseConnection,MMSI);
//
//        //US206
//        GetLoadContainersController loadController = new GetLoadContainersController();
//        loadController.getContainersToLoadOnNextPort(databaseConnection,MMSI);
//
//        //US207
//        CMTransportedController cmtController = new CMTransportedController();
//        cmtController.cmTransported(databaseConnection,"2021");
//
//        //US208
//        OccupancyRateController orController = new OccupancyRateController();
//        orController.occupancyRate(databaseConnection,MMSI,"12345");
//
//        //US209
//        OccupancyRateMomentController ormController = new OccupancyRateMomentController();
//        ormController.occupancyRateMoment(databaseConnection,MMSI,"2021-02-21 00:02:01");
//
//        //US210
//        ShipsAvailableController saController = new ShipsAvailableController(company);
//        saController.getShipsAvailable();
//
//    }
//}
