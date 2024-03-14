package com.napier.GP14;

import java.sql.*;
import java.util.ArrayList;

public class App
{
    public static void main(String[] args) {


        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();


        // Extract COUNTRY information
        ArrayList<Country> countries = a.getAllCountries();

        //List of all Countries in order of descending population
        //a.printCountriesinOrder(countries);

        //List of the top N populated countries in the world where N is provided by the user.
        //a.printCountriesinOrderN(countries, 10);

        //List of all the countries in a continent organised by largest population to smallest.
        //a.printCountriesInContinentOrdered(countries, "Africa");

        //List of all the countries in a region organised by largest population to smallest.
        //a.printCountriesInRegionOrdered(countries, "Western Africa");

        // List of the top N populated countries in a continent where N is provided by the user.
        //a.printCountriesinContinentsOrderN(countries, "Europe", 10);

        // List of the top N populated countries in a region where N is provided by the user.
        a.printCountriesInRegionOrderN(countries, "Eastern Europe", 5);








        // Disconnect from database
        a.disconnect();

    }

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 100;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(5000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    public void displayCountry(Country cou) {
        if (cou != null) {
            System.out.println(
                    cou.Code + " "
                    + cou.Name + " "
                    + cou.Continent + " "
                    + cou.Region + " "
                    + cou.Population + " "
                    + cou.Capital);
        }
    }

    public ArrayList<Country> getAllCountries() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Code, Name, Continent, Region, Population, Capital "
                            + "FROM country" + " "
                            + "ORDER BY Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract employee information
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next()) {
                Country cou = new Country();
                cou.Code = rset.getString("country.Code");
                cou.Name = rset.getString("country.Name");
                cou.Continent = rset.getString("country.Continent");
                cou.Region = rset.getString("country.Region");
                cou.Population = rset.getInt("country.Population");
                cou.Capital = rset.getString("country.Capital");
                countries.add(cou);
            }
            return countries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    public void printCountriesinOrder(ArrayList<Country> countries){
         // Loop over all employees in the list
        for (Country cou : countries) {
            System.out.println(
                    cou.Code + " "
                            + cou.Name + " "
                            + cou.Continent + " "
                            + cou.Region + " "
                            + cou.Population + " "
                            + cou.Capital);
        }
    }

    public void printCountriesinOrderN(ArrayList<Country> countries, int n){
         // Loop over all employees in the list
        int i;
        i=0;
        // end loop when i goes past number specified
        for (Country cou : countries) {
            if(i >= n){
                break;
            }
            System.out.println(
                    cou.Code + " "
                            + cou.Name + " "
                            + cou.Continent + " "
                            + cou.Region + " "
                            + cou.Population + " "
                            + cou.Capital);
            i++;
        }
    }


    public void printCountriesInContinentOrdered(ArrayList<Country> countries, String continent){

        for (Country cou : countries) {
            if (cou.Continent .equals(continent)){
                System.out.println(
                        cou.Code + " "
                                + cou.Name + " "
                                + cou.Continent + " "
                                + cou.Region + " "
                                + cou.Population + " "
                                + cou.Capital);
            }
        }
    }

    public void printCountriesInRegionOrdered(ArrayList<Country> countries, String region){

        for (Country cou : countries) {
            if (cou.Region .equals(region)){
                System.out.println(
                        cou.Code + " "
                                + cou.Name + " "
                                + cou.Continent + " "
                                + cou.Region + " "
                                + cou.Population + " "
                                + cou.Capital);
            }
        }
    }

    public void printCountriesinContinentsOrderN(ArrayList<Country> countries, String continent, int n){
        // Loop over all employees in the list
        int i;
        i=0;
        // end loop when i goes past number specified
        for (Country cou : countries) {
            if(i >= n){
                break;
            }

            //print countries in the specified continent
            if(cou.Continent .equals(continent)){
            System.out.println(
                    cou.Code + " "
                            + cou.Name + " "
                            + cou.Continent + " "
                            + cou.Region + " "
                            + cou.Population + " "
                            + cou.Capital);
                i++;
            }

        }
    }

    public void printCountriesInRegionOrderN(ArrayList<Country> countries, String region, int n){
        // Loop over all employees in the list
        int i;
        i=0;
        // end loop when i goes past number specified
        for (Country cou : countries) {
            if(i >= n){
                break;
            }

            //print countries in the specified continent
            if(cou.Region .equals(region)){
                System.out.println(
                        cou.Code + " "
                                + cou.Name + " "
                                + cou.Continent + " "
                                + cou.Region + " "
                                + cou.Population + " "
                                + cou.Capital);
                i++;
            }

        }
    }

}