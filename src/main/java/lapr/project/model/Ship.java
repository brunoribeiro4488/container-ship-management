package lapr.project.model;


import lapr.project.utils.bst.BST;

import java.util.Objects;

/**
 * Class Ship.
 */
public class Ship implements Comparable<Ship> {

    /**
     * The ship's mmsi.
     */
    private final  String mmsi;

    /**
     * The ship's name.
     */
    private final String name;

    /**
     * The ship's imo.
     */
    private final String imo;

    /**
     * The ship's call sign.
     */
    private final String callsign;

    /**
     * The ship's type.
     */
    private final String type;

    /**
     * The ship's length.
     */
    private final String length;

    /**
     * The ship's width.
     */
    private final String width;

    /**
     * The ship's draft.
     */
    private final String draft;

    /**
     * The ship's bst of ship positions.
     */
    private final BST<ShipPosition> sp;


    /**
     * Builds a Ship receiving all its parameters.
     *
     * @param mmsi ship's mmsi
     * @param name ship's name
     * @param imo ship's imo
     * @param callsign ship's call sign
     * @param type ship's type
     * @param length ship's length
     * @param width ship's width
     * @param draft ship's draft
     */
    public Ship(String mmsi, String name, String imo, String callsign, String type, String length, String width, String draft) {
        mmsiValidation(mmsi);
        nameValidation(name);
        imoValidation(imo);
        callSignValidation(callsign);
        vesselTypeValidation(type);
        lengthValidation(length);
        widthValidation(width);
        draftValidation(draft);
        this.mmsi = mmsi;
        this.name = name;
        this.imo = imo;
        this.callsign = callsign;
        this.type = type;
        this.length = length;
        this.width = width;
        this.draft = draft;
        this.sp = new BST<>();
    }

    /**
     * Get's the Ship's mmsi.
     *
     * @return Ship's mmsi
     */
    public String getMmsi() {
        return mmsi;
    }

    /**
     * Get's the Ship's imo.
     *
     * @return Ship's imo
     */
    public String getImo() {
        return imo;
    }

    /**
     * Get's the Ship's call sign.
     *
     * @return Ship's call sign
     */
    public String getCallsign() {
        return callsign;
    }

    /**
     * Get's the Ship's bst of ship positions.
     *
     * @return Ship's bst of ship positions
     */
    public BST<ShipPosition> getSp() {
        return sp;
    }

    /**
     * Get's the Ship's name.
     *
     * @return Ship's name
     */
    public String getName(){
        return name;
    }

    /**
     * Get's the Ship's type.
     *
     * @return Ship's type
     */
    public String getType(){
        return type;
    }

    /**
     * Get's the Ship's length.
     *
     * @return Ship's length
     */
    public String getLength(){
        return length;
    }

    /**
     * Get's the Ship's width.
     *
     * @return Ship's width
     */
    public String getWidth(){
        return width;
    }

    /**
     * Get's the Ship's draft.
     *
     * @return Ship's draft
     */
    public String getDraft(){
        return draft;
    }


    /**
     * Verifies if there is an object equals to the @param object.
     *
     * @param o
     *
     * @return true if it founds an object equals to the @param.
     * @return false if there is not any object equals to the @param.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return Objects.equals(mmsi, ship.mmsi) || Objects.equals(imo, ship.imo) || Objects.equals(callsign, ship.callsign);
    }

    /**
     * returns the hash code of the ship.
     *
     * @return ship hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(mmsi, imo, callsign);
    }

    /**
     * Compares the ship imo with the @param.
     *
     * @param o
     * @return -1, if its lower
     * @return 0, if its equal
     * @return 1, if its bigger
     */
    public int compareToIMO(String o){
        int i=o.compareTo(this.getImo());
        if(i<0){
            return -1;
        }else if(i>0){
            return 1;
        }else{
            return 0;
        }
    }

    /**
     * Compares the ship call sign with the @param.
     *
     * @param o
     * @return -1, if its lower
     * @return 0, if its equal
     * @return 1, if its bigger
     */
    public int compareToCallSign(String o){
        int i=o.compareTo(this.getCallsign());
        if(i<0){
            return -1;
        }else if(i>0){
            return 1;
        }else{
            return 0;
        }
    }

    /**
     * Compares the ship mmsi with the @param.
     *
     * @param o
     * @return -1, if its lower
     * @return 0, if its equal
     * @return 1, if its bigger
     */
    public int compareToMMSI(String o) {
        int i=o.compareTo(this.getMmsi());
        if(i<0){
            return -1;
        }else if(i>0){
            return 1;
        }else{
            return 0;
        }
    }

    /**
     * verifies if the ship mmsi is valid
     *
     * @param mmsi
     */
    private void mmsiValidation(String mmsi){
        try {
            Integer.parseInt(mmsi);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The mmsi must be numeric.");
        }
        if (mmsi.length()!=9){
            throw  new IllegalArgumentException("The mmsi must have 9 numbers.");
        }
    }

    /**
     * verifies if the ship name is valid
     *
     * @param name
     */
    private void nameValidation(String name){
        if (Objects.equals(name, ""))throw new IllegalArgumentException("Name can't be empty.");
    }

    /**
     * verifies if the ship imo is valid
     *
     * @param imo
     */
    private void imoValidation(String imo){
        imo = imo.replace("IMO","");
        try {
            Integer.parseInt(imo);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The IMO must be numeric.");
        }
        if (imo.length()!=7){
            throw  new IllegalArgumentException("The IMO must have 7 numbers.");
        }
    }

    /**
     * verifies if the ship call sign is valid
     *
     * @param callsign
     */
    private void callSignValidation(String callsign){
        if (Objects.equals(callsign, ""))throw new IllegalArgumentException("CallSign can't be empty.");
    }

    /**
     * verifies if the ship type is valid
     *
     * @param type
     */
    private void vesselTypeValidation(String type){
        if (Objects.equals(type, ""))throw new IllegalArgumentException("Type can't be blank.");
        try {
            Integer.parseInt(type);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The VesselType must be numeric.");
        }
    }

    /**
     * verifies if the ship length is valid
     *
     * @param length
     */
    private void lengthValidation(String length){
        if (Objects.equals(length, ""))throw new IllegalArgumentException("Length can't be empty.");
        try {
            Integer.parseInt(length);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The length must be numeric.");
        }
    }

    /**
     * verifies if the ship width is valid
     *
     * @param width
     */
    private void widthValidation(String width){
        if (Objects.equals(width, ""))throw new IllegalArgumentException("Width can't be empty.");
        try {
            Integer.parseInt(width);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The Width must be numeric.");
        }
    }

    /**
     * verifies if the ship draft is valid
     *
     * @param draft
     */
    private void draftValidation(String draft){
        if (Objects.equals(draft, ""))throw new IllegalArgumentException("Draft can't be empty.");
        try {
            Double.parseDouble(draft);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("The draft must be numeric.");
        }
    }

    /**
     * Compares the ship mmsi with the @param mmsi.
     *
     * @param o
     * @return -1, if its lower
     * @return 0, if its equal
     * @return 1, if its bigger
     */
    @Override
    public int compareTo(Ship o) {
        int i=o.getMmsi().compareTo(this.getMmsi());
        if(i<0){
            return -1;
        }else if(i>0){
            return 1;
        }else{
            return 0;
        }
    }

    /**
     * Returns the textual description of the ship.
     *
     * @return ship characteristics.
     */
    @Override
    public String toString() {
        return "Ship{" +
                "mmsi='" + mmsi + '\'' +
                ", name='" + name + '\'' +
                ", imo='" + imo + '\'' +
                ", callsign='" + callsign + '\'' +
                ", type='" + type + '\'' +
                ", length='" + length + '\'' +
                ", width='" + width + '\'' +
                ", draft='" + draft + '\'' +
                '}'+"\n";
    }
}