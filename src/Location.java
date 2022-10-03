/**
 * Defines the five set gym locations.
 * @author Kennan Guan, Adwait Ganguly
 */
public enum Location {
        BRIDGEWATER("BRIDGEWATER","08807", "SOMERSET"),
        EDISON("EDISON", "08837", "MIDDLESEX"),
        FRANKLIN("FRANKLIN", "08873", "SOMERSET"),
        PISCATAWAY("PISCATAWAY", "08854", "MIDDLESEX"),
        SOMERVILLE("SOMERVILLE", "08876", "SOMERSET");

        private final String city;
        private final String zip;
        private final String county;

        /**
         * Constructor for Location given city, zip code, and county.
         * @param city the city where a gym is located.
         * @param zipCode the zip code where a gym is located.
         * @param county the county where a gym is located.
         */
        Location(String city, String zipCode, String county) {
                this.city = city;
                this.zip = zipCode;
                this.county = county;
        }

        /**
         * Getter method for zip code.
         * @return the zip code as an integer.
         */
        public int getZip() {
                return Integer.parseInt(zip);
        }

        /**
         * Getter method for the county of the gym location.
         * @return the county where the gym is located.
         */
        public String getCounty() {
                return county;
        }
        /**
         * Constructs a textual representation of the Location object.
         * @return a string representation of the location given as "[city], [zip code], [county]"
         */
        @Override
        public String toString() {
                return city + ", " + zip + ", " + county;
        }



}
