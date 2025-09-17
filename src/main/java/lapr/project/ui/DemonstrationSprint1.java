//package lapr.project.ui;
//
//import lapr.project.controller.*;
//import lapr.project.model.Company;
//
//import java.io.File;
//import java.io.IOException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//
//public class DemonstrationSprint1 {
//
//    private static final String DATA = "31/12/2020";
//
//    public static void main(String[] args) throws IOException, ParseException {
//        Company company = App.getInstance().getCompany();
//        ImportFileController importController = new ImportFileController(company);
//
//        System.out.println("Loading...");
//        File f = new File("sships.csv");
//        importController.read(f);
//
//
//        SearchShipController searchShipController = new SearchShipController();
//        searchShipController.getShipByIMO("IMO9193305");
//        searchShipController.getShipByCallSign("CQAG7");
//        searchShipController.getShipByMMSI("255806239");
//
//        MessagesOrganizedController messagesOrganizedController = new MessagesOrganizedController(company);
//        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
//        messagesOrganizedController.getMessagesOrganized(format1.parse(DATA));
//        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
//        messagesOrganizedController.getMessagesOrganized(format1.parse(DATA), format.parse("31/12/2020 05:00"));
//
//        MakeSummaryController summaryController = new MakeSummaryController(company);
//        summaryController.createSummary("IMO9193305");
//        summaryController.createSummary("CQAG7");
//        summaryController.createSummary("255806239");
//
//        ListAllShipsInformationController informationController = new ListAllShipsInformationController(company);
//        informationController.getInformationAllShips();
//
//        TopShipsWithTravelledDistanceController topShipsController = new TopShipsWithTravelledDistanceController(company);
//        topShipsController.getTopShipsWithTravelledDistance(format1.parse(DATA), format.parse("31/12/2020 05:00"), 3);
//
//        ShipPairController shipPairController = new ShipPairController(company);
//        shipPairController.pairOfShips();
//
//    }
//}
