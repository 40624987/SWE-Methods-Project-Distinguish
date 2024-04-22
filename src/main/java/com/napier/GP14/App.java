package com.napier.GP14;

import java.sql.*;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        // Extract COUNTRY information
        ArrayList<Country> countries = a.getAllCountries();

        // Extract city information
        ArrayList<City> cities = a.getAllCities();

        // world population
        //int totalWorldPopulation = a.getTotalWorldPopulation();
        //System.out.println("Total world population: " + totalWorldPopulation);

        // continent population
        //String continentName = "Europe"; // Example continent name, you can get it from user input
        //int totalContinentPopulation = a.getTotalContinentPopulation(continentName);
        //System.out.println("Total population of " + continentName + ": " + totalContinentPopulation);

        // region population
        //String regionName = "Caribbean"; // Example region name, you can get it from user input
        //int totalRegionPopulation = a.getTotalRegionPopulation(regionName);
        //System.out.println("Total population of " + regionName + ": " + totalRegionPopulation);

        // country population
        //String countryName = "Nigeria"; // Example country name, you can get it from user input
        //int totalCountryPopulation = a.getTotalCountryPopulation(countryName);
        //System.out.println("Total population of " + countryName + ": " + totalCountryPopulation);

        //district population
        //String districtName = "Rio de Janeiro"; // Example district name, you can get it from user input
        //int totalDistrictPopulation = a.getTotalDistrictPopulation(districtName);
        //System.out.println("Total population of " + districtName + ": " + totalDistrictPopulation);

        // city population
        String cityName = "New York"; // Example city name, you can get it from user input
        int cityPopulation = a.getCityPopulation(cityName);
        System.out.println("Population of " + cityName + ": " + cityPopulation);












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
                cou.Code = rset.getString("Code");
                cou.Name = rset.getString("Name");
                cou.Continent = rset.getString("Continent");
                cou.Region = rset.getString("Region");
                cou.Population = rset.getInt("Population");
                cou.Capital = rset.getString("Capital");
                countries.add(cou);
            }
            return countries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
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







        public int getTotalWorldPopulation() {
            int totalPopulation = 0;
            try {
                // Create an SQL statement
                Statement stmt = con.createStatement();
                // Create string for SQL statement
                String strSelect = "SELECT SUM(Population) AS TotalPopulation FROM city";
                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                // Retrieve the total population
                if (rset.next()) {
                    totalPopulation = rset.getInt("TotalPopulation");
                }
            } catch (SQLException e) {
                System.out.println("Failed to get total world population: " + e.getMessage());
            }
            return totalPopulation;
        }

    public int getTotalContinentPopulation(String continentName) {
        int totalPopulation = 0;
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT SUM(city.Population) AS TotalPopulation FROM city JOIN country ON city.CountryCode = country.Code WHERE country.Continent = '" + continentName + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Retrieve the total population
            if (rset.next()) {
                totalPopulation = rset.getInt("TotalPopulation");
            }
        } catch (SQLException e) {
            System.out.println("Failed to get total population of " + continentName + ": " + e.getMessage());
        }
        return totalPopulation;
    }

    public int getTotalRegionPopulation(String regionName) {
        int totalPopulation = 0;
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT SUM(city.Population) AS TotalPopulation FROM city JOIN country ON city.CountryCode = country.Code WHERE country.Region = '" + regionName + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Retrieve the total population
            if (rset.next()) {
                totalPopulation = rset.getInt("TotalPopulation");
            }
        } catch (SQLException e) {
            System.out.println("Failed to get total population of " + regionName + ": " + e.getMessage());
        }
        return totalPopulation;
    }

    public int getTotalCountryPopulation(String countryName) {
        int totalPopulation = 0;
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT SUM(city.Population) AS TotalPopulation FROM city JOIN country ON city.CountryCode = country.Code WHERE country.Name = '" + countryName + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Retrieve the total population
            if (rset.next()) {
                totalPopulation = rset.getInt("TotalPopulation");
            }
        } catch (SQLException e) {
            System.out.println("Failed to get total population of " + countryName + ": " + e.getMessage());
        }
        return totalPopulation;
    }

    public int getTotalDistrictPopulation(String districtName) {
        int totalPopulation = 0;
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT SUM(city.Population) AS TotalPopulation FROM city WHERE city.District = '" + districtName + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Retrieve the total population
            if (rset.next()) {
                totalPopulation = rset.getInt("TotalPopulation");
            }
        } catch (SQLException e) {
            System.out.println("Failed to get total population of " + districtName + ": " + e.getMessage());
        }
        return totalPopulation;
    }

    public int getCityPopulation(String cityName) {
        int cityPopulation = 0;
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT Population FROM city WHERE Name = '" + cityName + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Retrieve the city population
            if (rset.next()) {
                cityPopulation = rset.getInt("Population");
            }
        } catch (SQLException e) {
            System.out.println("Failed to get population of " + cityName + ": " + e.getMessage());
        }
        return cityPopulation;
    }




}







