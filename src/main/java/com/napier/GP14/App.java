package com.napier.GP14;

import java.sql.*;
import java.util.ArrayList;

public class App
{
    public static void main(String[] args) {
        // Create new Application and connect to database
        App a = new App();

        if (args.length < 1) {
            a.connect("localhost:33060", 10000);
        } else {
            a.connect(args[0], Integer.parseInt(args[1]));
        }

        // Extract COUNTRY information
        ArrayList<Country> countries = a.getAllCountries();

        //Extract city information
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
        //String cityName = "New York"; // Example city name, you can get it from user input
        //int cityPopulation = a.getCityPopulation(cityName);
        //System.out.println("Population of " + cityName + ": " + cityPopulation);

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

        //List of N cities in a District where N is provided by the user
        //System.out.println(
        //    "===============================\n" +
        //             "Cities in Districts in order of population\n"+
        //    "City    Country    District    Population" );
        //      a.printCitiesInDistrictOrderN(cities, countries, "Rio de Janeiro", 5  );



        //List of N cities in a Continents where N is provided by the user
        //System.out.println(
        //       "===============================\n" +
        //               "Cities in Continents in order of population\n"+
        //               "City    Country    District    Population" );
        //a.printCitiesInContinentOrderN(cities, countries, "Africa", 7  );

        //List of N cities in a Country where N is provided by the user
        //System.out.println(
        //        "===============================\n" +
        //              "Cities in Country in order of population\n"+
        //              "City    Country    District    Population" );
        //a.printCitiesInCountryOrderN(cities, countries, "Nigeria", 3);

        //List of N cities in a Region where N is provided by the user
        //System.out.println(
        //               "Cities in Region in order of population\n"+
        //               "City    Country    District    Population" );
        // a.printCitiesInRegionOrderN(cities, countries, "Western Africa", 10);

        //List of top populated N cities in the world where N is provided by the user
        //System.out.println(
         //       "===============================\n" +
          //              "Cities in World in order of population\n"+
          //              "City    Country    District    Population" );

        //a.printCitiesInOrderN(cities, countries, 5);

        //List of all Countries in order of descending population
        //System.out.println("Code  Name  Continent  Region  Population  Capital\n--------------------------------------------------");
        //a.printCountriesinOrder(countries);

        //List of the top N populated countries in the world where N is provided by the user.
        //System.out.println("Code  Name  Continent  Region  Population  Capital\n--------------------------------------------------");
        //a.printCountriesinOrderN(countries, 10);

        //List of all the countries in a continent organised by largest population to smallest.
        //System.out.println("Code  Name  Continent  Region  Population  Capital\n--------------------------------------------------");
        //a.printCountriesInContinentOrdered(countries, "Europe");

        //List of all the countries in a region organised by largest population to smallest.
        //System.out.println("Code  Name  Continent  Region  Population  Capital\n--------------------------------------------------");
        //a.printCountriesInRegionOrdered(countries, "Western Africa");

        // List of the top N populated countries in a continent where N is provided by the user.
        //System.out.println("Code  Name  Continent  Region  Population  Capital\n--------------------------------------------------");
        //a.printCountriesinContinentsOrderN(countries, "Europe", 10);

        // List of the top N populated countries in a region where N is provided by the user.
        //System.out.println("Code  Name  Continent  Region  Population  Capital\n--------------------------------------------------");
        //a.printCountriesInRegionOrderN(countries, "Eastern Europe", 5);














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
    public void connect(String location, int delay) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        boolean shouldWait = false;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                if (shouldWait) {
                    // Wait a bit for db to start
                    Thread.sleep(delay);
                }

                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location
                                + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());

                // Let's wait before attempting to reconnect
                shouldWait = true;
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


    public void displayCity(City cit) {
        if (cit != null) {
            System.out.println(
                    cit.Name + " "
                            + cit.CountryCode + " "
                            + cit.District + " "
                            + cit.Population + " ");
        } else {
            System.out.println("No city information available.");
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
    public void printCitiesInDistrictOrderN(ArrayList<City> cities, ArrayList<Country> countries, String district, int n){
        // Loop over all employees in the list
        int i = 0;

        // end loop when i goes past number specified
        for (City cit : cities) {
            if(i >= n){
                break;
            }

            //print cities in the specified district
            if (cit.District .equals(district)){
                for(Country cou: countries){
                    if(cou.Code .equals(cit.CountryCode)) {
                        System.out.println(
                                cit.Name + " "
                                        + cou.Name + " "
                                        + cit.District + " "
                                        + cit.Population + " ");

                        i++;
                        break;
                    }
                }
            }

        }
    }

    public void printCitiesInContinentOrderN(ArrayList<City> cities, ArrayList<Country> countries, String continent, int n) {
        // Initialize counter
        int i = 0;

        // Iterate over cities
        for (City cit : cities) {
            // Check if the limit is reached
            if (i >= n) {
                break;
            }

            // Check if the city belongs to the specified continent
            for (Country cou : countries) {
                if (cou.Continent.equals(continent) && cit.CountryCode.equals(cou.Code)) {
                    // Print city information
                    System.out.println(
                            cit.Name + " "
                                    + cou.Name + " "
                                    + cit.District + " "
                                    + cit.Population
                    );

                    // Increment the counter
                    i++;
                    break; // Break out of the inner loop once the city is printed
                }
            }
        }
    }

    public void printCitiesInCountryOrderN(ArrayList<City> cities, ArrayList<Country> countries, String country, int n) {
        // Initialize counter
        int count = 0;

        // Iterate over cities
        for (City cit : cities) {
            // Check if the limit is reached
            if (count >= n) {
                break;
            }

            // Check if the city belongs to the specified country
            for (Country cou : countries) {
                if (cou.Name.equals(country) && cit.CountryCode.equals(cou.Code)) {
                    // Print city information
                    System.out.println(
                            cit.Name + " "
                                    + cou.Name + " "
                                    + cit.District + " "
                                    + cit.Population
                    );

                    // Increment the counter
                    count++;

                    // Exit the loop once a city from the specified country is found
                    break;
                }
            }
        }
    }

    public void printCitiesInRegionOrderN(ArrayList<City> cities, ArrayList<Country> countries, String region, int n) {
        // Initialize counter
        int count = 0;

        // Iterate over cities
        for (City cit : cities) {
            // Check if the limit is reached
            if (count >= n) {
                break;
            }

            // Check if the city belongs to the specified region
            for (Country cou : countries) {
                if (cou.Region.equals(region) && cit.CountryCode.equals(cou.Code)) {
                    // Print city information
                    System.out.println(
                            cit.Name + " "
                                    + cou.Name + " "
                                    + cit.District + " "
                                    + cit.Population
                    );

                    // Increment the counter
                    count++;

                    // Exit the loop once a city from the specified region is found
                    break;
                }
            }
        }
    }

    public void printCitiesInOrderN(ArrayList<City> cities, ArrayList<Country> countries, int n) {
        // Initialize counter
        int count = 0;

        // Loop over all cities in the list
        for (City cit : cities) {
            // Check if the limit is reached
            if (count >= n) {
                break;
            }

            // Find the country to which the city belongs
            for (Country cou : countries) {
                if (cit.CountryCode.equals(cou.Code)) {
                    // Print city information
                    System.out.println(
                            cit.Name + " "
                                    + cou.Name + " "
                                    + cit.District + " "
                                    + cit.Population
                    );

                    // Increment the counter
                    count++;

                    // Exit the inner loop once the city is printed
                    break;
                }
            }
        }
    }

    public void printCountriesinOrder(ArrayList<Country> countries){
        // Loop over all countries in the list
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

            //print countries in the specified region
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








}
}