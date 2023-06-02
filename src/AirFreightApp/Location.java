/* CPCS324 Project Part 2

 Group members: 
    1- Razan Alamri
    2- Khloud Alsofyani
    3- Leen Ba Galaql
    4- Shatha Binmahfouz
 */
package AirFreightApp;

import GraphFramework.Vertex;

//Location is a subclass of Vertex, it inherits all attributes, operations & relationships. 
public class Location extends Vertex {

    // Data F
    private String city;

    public Location(int id) {
        super(id);

    }

    public Location() {
        super();
    }

    /*
     * Override method that responsible for displaying
     * the information of the class attributes
     */
    @Override
    public String displyInfo() {
        city = "Loc." + getLabel() + ":City" + getPosition();
        return city;
    }

}
