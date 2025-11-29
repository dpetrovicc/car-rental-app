/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Iznajmljivanje extends AbstractDomainObject {

    private int iznajmljivanjeID;
    private String opis;
    private double ukupanIznos;
    private PoslPartner poslPartner;
    private Zaposleni zaposleni;
    private ArrayList<StavkaIznajmljivanja> stavkeIznajmljivanja;

    public Iznajmljivanje(int iznajmljivanjeID, String opis, double ukupanIznos, PoslPartner poslPartner, Zaposleni zaposleni, ArrayList<StavkaIznajmljivanja> stavkeIznajmljivanja) {
        this.iznajmljivanjeID = iznajmljivanjeID;
        this.opis = opis;
        this.ukupanIznos = ukupanIznos;
        this.poslPartner = poslPartner;
        this.zaposleni = zaposleni;
        this.stavkeIznajmljivanja = stavkeIznajmljivanja;
    }

    public Iznajmljivanje() {
    }

    @Override
    public String nazivTabele() {
        return " Iznajmljivanje ";
    }

    @Override
    public String alijas() {
        return " i ";
    }

    @Override
    public String join() {
        return " JOIN POSLPARTNER PP ON (I.POSLPARTNERID = PP.POSLPARTNERID)\n"
                + "JOIN MESTO M ON (M.MESTOID = PP.MESTOID)\n"
                + "JOIN ZAPOSLENI Z ON (Z.ZAPOSLENIID = I.ZAPOSLENIID)";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Zaposleni z = new Zaposleni(rs.getInt("ZaposleniID"),
                    rs.getString("z.Ime"), rs.getString("z.Prezime"),
                    rs.getString("KorisnickoIme"), rs.getString("Lozinka"));

            Mesto m = new Mesto(rs.getInt("MestoID"),
                    rs.getString("naziv"));

            PoslPartner pp = new PoslPartner(rs.getInt("PoslPartnerID"),
                    rs.getString("pp.Ime"), rs.getString("pp.Prezime"),
                    rs.getString("Email"), rs.getString("Telefon"), m);

            Iznajmljivanje i = new Iznajmljivanje(rs.getInt("iznajmljivanjeID"),
                    rs.getString("opis"), rs.getDouble("ukupanIznos"),
                    pp, z, null);

            lista.add(i);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (opis, ukupanIznos, PoslPartnerID, ZaposleniID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + opis + "', " + ukupanIznos + ", "
                + " " + poslPartner.getPartnerID() + ", " + zaposleni.getZaposleniID() + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " opis = '" + opis + "', ukupanIznos = " + ukupanIznos + " ";
    }

    @Override
    public String uslov() {
        return " iznajmljivanjeID = " + iznajmljivanjeID;
    }

    @Override
    public String uslovZaSelect() {
        if (poslPartner != null) {
            return " WHERE PP.POSLPARTNERID = " + poslPartner.getPartnerID()
                    + " ORDER BY I.iznajmljivanjeID";
        }
        return "";
    }

    public int getIznajmljivanjeID() {
        return iznajmljivanjeID;
    }

    public void setIznajmljivanjeID(int iznajmljivanjeID) {
        this.iznajmljivanjeID = iznajmljivanjeID;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public PoslPartner getPoslPartner() {
        return poslPartner;
    }

    public void setPoslPartner(PoslPartner poslPartner) {
        this.poslPartner = poslPartner;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public ArrayList<StavkaIznajmljivanja> getStavkeIznajmljivanja() {
        return stavkeIznajmljivanja;
    }

    public void setStavkeIznajmljivanja(ArrayList<StavkaIznajmljivanja> stavkeIznajmljivanja) {
        this.stavkeIznajmljivanja = stavkeIznajmljivanja;
    }

}
