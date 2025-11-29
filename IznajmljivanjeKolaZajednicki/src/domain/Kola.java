/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Kola extends AbstractDomainObject {

    private int kolaID;
    private String marka;
    private String model;
    private String opis;
    private double cenaPoDanu;

    public Kola(int kolaID, String marka, String model, String opis, double cenaPoDanu) {
        this.kolaID = kolaID;
        this.marka = marka;
        this.model = model;
        this.opis = opis;
        this.cenaPoDanu = cenaPoDanu;
    }

    public Kola() {
    }

    @Override
    public String toString() {
        return marka + " " + model + " (Cena po danu: " + cenaPoDanu + "€)";
    }

    @Override
    public String nazivTabele() {
        return " Kola ";
    }

    @Override
    public String alijas() {
        return " k ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Kola k = new Kola(rs.getInt("KolaID"),
                    rs.getString("Marka"), rs.getString("Model"),
                    rs.getString("Opis"), rs.getDouble("CenaPoDanu"));

            lista.add(k);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Marka, Model, Opis, CenaPoDanu) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + marka + "', '" + model + "', "
                + "'" + opis + "', " + cenaPoDanu + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " cenaPoDanu = " + cenaPoDanu + ", opis = '" + opis + "' ";
    }

    @Override
    public String uslov() {
        return " kolaID = " + kolaID;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public int getKolaID() {
        return kolaID;
    }

    public void setKolaID(int kolaID) {
        this.kolaID = kolaID;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCenaPoDanu() {
        return cenaPoDanu;
    }

    public void setCenaPoDanu(double cenaPoDanu) {
        this.cenaPoDanu = cenaPoDanu;
    }

}
