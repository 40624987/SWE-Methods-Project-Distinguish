package com.napier.GP14;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class App
{
    public static void main(String[] args) {


        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();


        // Extract COUNTRY information
        ArrayList<Country> countries = a.getAllCountries();

        //Extract city information
        ArrayList<City> cities = a.getAllCities();

        //ArrayList<Country> Ncountries = a.getNCountries(10);

        //List of all Countries in order of descending population

        //a.printCountriesinOrder(countries);

        //List of all Cities in order of descending population
        //System.out.println(
         //       "===============================\n" +
         //              "All Cities in order of population\n"+
          //              "City    Country    District    Population" );
        //a.printCitiesinOrder(cities, countries);

        //List of all the cities in a continent organised by largest population to smallest.
        //System.out.println(
         //       "===============================\n" +
         //       "All cities in continent in order of population\n"+
         //       "City    Country    District    Population" );
        //a.printCitiesInContinentOrdered(cities, countries, "Africa");

        //List of all the cities in a region organised by largest population to smallest.

        //System.out.println(
          //     "===============================\n" +
          //           "All cities in region in order of population\n"+
         //       "City    Country    District    Population" );
       // a.printCitiesInRegionOrdered(cities, countries, "Western Africa");


        //List of all the cities in a country organised by largest population to smallest.
       //System.out.println(
       //        "===============================\n" +
        //               "Cities in Country in order of population\n"+
         //      "City    Country    District    Population" );

        //a.printCitiesInCountryOrdered(cities, countries, "Germany");

        //List of all the cities in a district organised by largest population to smallest.
        //System.out.println(
          //      "===============================\n" +
          //              "Cities in Districts in order of population\n"+
          //      "City    Country    District    Population" );
          //             a.printCitiesInDistrictOrdered(cities, countries, "Rio de Janeiro");








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

    public ArrayList<Country> getNCountries(int N) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Code, Name, Continent, Region, Population, Capital "
                            + "FROM country" + " "
                            + "ORDER BY Population DESC "
                            + "LIMIT " + N;
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
        // Print header
        //System.out.println(String.format("%-10s %-15s %-20s %-8s", "Emp No", "First Name", "Last Name", "Salary"));
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

    public ArrayList<City> getAllCities() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, District, city.Population, city.CountryCode "
                            + "FROM city, country "
                            + "WHERE country.Code = city.CountryCode "
                            + "ORDER BY Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next()) {
                City cit = new City();
                cit.Name = rset.getString("city.Name");
                cit.CountryCode = rset.getString("city.CountryCode");
                cit.District = rset.getString("city.District");
                cit.Population = rset.getInt("city.Population");

                cities.add(cit);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    public ArrayList<City> getNCities(int N) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, District, city.Population, city.CountryCode "
                            + "FROM city, country "
                            + "WHERE country.Code = city.CountryCode "
                            + "ORDER BY Population DESC "
                            + "LIMIT "+ N;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next()) {
                City cit = new City();
                cit.Name = rset.getString("city.Name");
                cit.CountryCode = rset.getString("city.CountryCode");
                cit.District = rset.getString("city.District");
                cit.Population = rset.getInt("city.Population");

                cities.add(cit);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    public void printCitiesinOrder(ArrayList<City> cities, ArrayList<Country> countries){
        // Loop over all cities in the list


            for (City cit : cities) {
                for (Country cou: countries) {
                    if (cit.CountryCode.equals(cou.Code)) {


                        System.out.println(
                                cit.Name + " "
                                        + cou.Name + " "
                                        + cit.District + " "
                                        + cit.Population + " ");

                    }
                }

        }
    }

    public void printCitiesInContinentOrdered(ArrayList<City> cities, ArrayList<Country> countries, String continent){

        for (City cit : cities) {
        for (Country cou: countries) {

        if (cou.Continent.equals(continent)) {
                    if(cit.CountryCode .equals(cou.Code)){
                        System.out.println(
                                cit.Name + " "
                                        + cou.Name + " "
                                        + cit.District + " "
                                        + cit.Population + " ");


                    }
                }

            }


        }
    }

    public void printCitiesInRegionOrdered(ArrayList<City> cities, ArrayList<Country> countries, String region){

        for (Country cou : countries) {
            if (cou.Region .equals(region)){
                for(City cit: cities){
                    if(cit.CountryCode .equals(cou.Code)){
                        System.out.println(
                                cit.Name + " "
                                        + cou.Name + " "
                                        + cit.District + " "
                                        + cit.Population + " "
                                       );
                    }
                }

            }
        }
    }

    public void printCitiesInCountryOrdered(ArrayList<City> cities, ArrayList<Country> countries, String country){

        for (Country cou : countries) {
            if (cou.Name.equals(country)){
                for(City cit: cities){
                    if(cit.CountryCode .equals(cou.Code)){
                        System.out.println(
                                cit.Name + " "
                                        + cou.Name + " "
                                        + cit.District + " "
                                        + cit.Population + " "
                        );
                    }
                }

            }
        }
    }


    public void printCitiesInDistrictOrdered(ArrayList<City> cities, ArrayList<Country> countries, String district){


        for (City cit : cities) {
            if (cit.District .equals(district)){
                for(Country cou: countries){
                    if(cou.Code .equals(cit.CountryCode))
                        System.out.println(
                                cit.Name + " "
                                        + cou.Name + " "
                                        + cit.District + " "
                                        + cit.Population + " ");
                    }
                }

            }
        }
    }

