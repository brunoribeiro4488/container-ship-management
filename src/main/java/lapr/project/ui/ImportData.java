package lapr.project.ui;

import lapr.project.controller.*;
import lapr.project.data.BorderStoreDB;
import lapr.project.data.CountryStoreDB;
import lapr.project.data.SeaDistanceStoreDB;
import lapr.project.model.Company;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class ImportData {
    public static void main(String[] args) throws IOException, ParseException {
        Company company = App.getInstance().getCompany();
        ImportFileController importController = new ImportFileController(company);
        ImportPortController portController = new ImportPortController(company);
        SeaDistanceStoreDB sdb = new SeaDistanceStoreDB();
        CountryStoreDB csdb = new CountryStoreDB();
        BorderStoreDB bsdb = new BorderStoreDB();
        System.out.println("Loading...");

        File f = new File("sships.csv");
        importController.read(f);

        System.out.println("1");

        File f4 = new File("countries.csv");
        csdb.readCountryFromFiletoDB(f4);

        System.out.println("2");

        File f2 = new File("bports.csv");
        portController.read(f2);

        System.out.println("3");

        File f3 = new File("borders.csv");
        bsdb.readBordersFromFileToDB(f3);

        System.out.println("4");

        File f5 = new File("seadists.csv");
        sdb.readfromFiletoDB(f5);
        System.out.println("5");
    }
}