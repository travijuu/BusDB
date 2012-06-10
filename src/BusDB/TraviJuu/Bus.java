package BusDB.TraviJuu;

import java.text.DateFormat;

/**
 * Created by IntelliJ IDEA.
 * User: Travego
 * Date: 13.01.2012
 * Time: 00:35
 * To change this template use File | Settings | File Templates.
 */
public class Bus {
    private int BUS_ID;
    private String BUS_PLATE, BUS_BRAND, BUS_MODEL, BUS_COMPANY, BUS_REGISTER_DATE;
    private int BUS_CAPACITY, BUS_YEAR;

    public Bus(String BUS_PLATE, String BUS_BRAND, String BUS_MODEL, String BUS_COMPANY, String BUS_REGISTER_DATE, int BUS_CAPACITY, int BUS_YEAR) {
        this.BUS_PLATE = BUS_PLATE;
        this.BUS_BRAND = BUS_BRAND;
        this.BUS_MODEL = BUS_MODEL;
        this.BUS_COMPANY = BUS_COMPANY;
        this.BUS_REGISTER_DATE = BUS_REGISTER_DATE;
        this.BUS_CAPACITY = BUS_CAPACITY;
        this.BUS_YEAR = BUS_YEAR;
    }

    public int getBUS_ID() {
        return BUS_ID;
    }

    public String getBUS_PLATE() {
        return BUS_PLATE;
    }

    public String getBUS_BRAND() {
        return BUS_BRAND;
    }

    public String getBUS_MODEL() {
        return BUS_MODEL;
    }

    public String getBUS_COMPANY() {
        return BUS_COMPANY;
    }

    public int getBUS_CAPACITY() {
        return BUS_CAPACITY;
    }

    public int getBUS_YEAR() {
        return BUS_YEAR;
    }

    public String getBUS_REGISTER_DATE() {
        return BUS_REGISTER_DATE;
    }
}
