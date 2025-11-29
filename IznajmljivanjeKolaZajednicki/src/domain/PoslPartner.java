/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PoslPartner extends AbstractDomainObject {

    private int partnerID;
    private String ime;
    private String prezime;
    private String email;
    private String telefon;
    private Mesto mesto;

    public PoslPartner(int partnerID, String ime, String prezime, String email, String telefon, Mesto mesto) {
        this.partnerID = partnerID;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.telefon = telefon;
        this.mesto = mesto;
    }

    public PoslPartner() {
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String nazivTabele() {
        return " PoslPartner ";
    }

    @Override
    public String alijas() {
        return " pp ";
    }

    @Override
    public String join() {
        return " JOIN MESTO M ON (M.MESTOID = PP.MESTOID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            Mesto m = new Mesto(rs.getInt("MestoID"),
                    rs.getString("naziv"));

            PoslPartner pp = new PoslPartner(rs.getInt("PoslPartnerID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Email"), rs.getString("Telefon"), m);

            lista.add(pp);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, Email, Telefon, MestoID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', "
                + "'" + email + "', '" + telefon + "', " + mesto.getMestoID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " email = '" + email + "', telefon = '" + telefon + "', "
                + "mestoID = " + mesto.getMestoID();
    }

    @Override
    public String uslov() {
        return " poslPartnerID = " + partnerID;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public int getPartnerID() {
        return partnerID;
    }

    public void setPartnerID(int partnerID) {
        this.partnerID = partnerID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

}
