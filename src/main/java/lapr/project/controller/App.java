package lapr.project.controller;

import lapr.project.model.Company;


/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class App {
    private final Company company;

    public App()
    {
        this.company = new Company();
    }

    public Company getCompany()
    {
        return this.company;
    }


    // Extracted from https://www.javaworld.com/article/2073352/core-java/core-java-simply-singleton.html?page=2
    private static App singleton = null;

    public static App getInstance()
    {
        if(singleton == null)
        {
            synchronized(App.class)
            {
                singleton = new App();
            }
        }
        return singleton;
    }
}
