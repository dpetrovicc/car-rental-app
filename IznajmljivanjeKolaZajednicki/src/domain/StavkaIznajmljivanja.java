/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class StavkaIznajmljivanja extends AbstractDomainObject {

    private Iznajmljivanje iznajmljivanje;
    private int rb;
    private Date datumOd;
    private Date datumDo;
    private int brojDana;
    private double cenaPoDanu;
    private double iznos;
    private Kola kola;

    public StavkaIznajmljivanja(Iznajmljivanje iznajmljivanje, int rb, Date datumOd, Date datumDo, int brojDana, double cenaPoDanu, double iznos, Kola kola) {
        this.iznajmljivanje = iznajmljivanje;
        this.rb = rb;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.brojDana = brojDana;
        this.cenaPoDanu = cenaPoDanu;
        this.iznos = iznos;
        this.kola = kola;
    }

    public StavkaIznajmljivanja() {
    }

    @Override
    public String nazivTabele() {
        return " StavkaIznajmljivanja ";
    }

    @Override
    public String alijas() {
        return " si ";
    }

    @Override
    public String join() {
        return " JOIN IZNAJMLJIVANJE I ON (I.IZNAJMLJIVANJEID = SI.IZNAJMLJIVANJEID)\n"
                + "JOIN POSLPARTNER PP ON (I.POSLPARTNERID = PP.POSLPARTNERID)\n"
                + "JOIN MESTO M ON (M.MESTOID = PP.MESTOID)\n"
                + "JOIN ZAPOSLENI Z ON (Z.ZAPOSLENIID = I.ZAPOSLENIID)\n"
                + "JOIN KOLA K ON (K.KOLAID = SI.KOLAID)";
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

            Kola k = new Kola(rs.getInt("KolaID"),
                    rs.getString("Marka"), rs.getString("Model"),
                    rs.getString("Opis"), rs.getDouble("CenaPoDanu"));

            StavkaIznajmljivanja si = new StavkaIznajmljivanja(i, rs.getInt("rb"),
                    rs.getDate("datumOd"), rs.getDate("datumDo"), rs.getInt("brojDana"),
                    rs.getDouble("cenaPoDanu"), rs.getDouble("iznos"), k);

            lista.add(si);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (iznajmljivanjeID, rb, datumOd, datumDo, brojDana, cenaPoDanu, iznos, kolaID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + iznajmljivanje.getIznajmljivanjeID() + ", " + rb + ", "
                + "'" + new java.sql.Date(datumOd.getTime()) + "', "
                + "'" + new java.sql.Date(datumDo.getTime()) + "', "
                + brojDana + ", " + cenaPoDanu + ", " + iznos + ", " + kola.getKolaID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return " iznajmljivanjeID = " + iznajmljivanje.getIznajmljivanjeID();
    }

    @Override
    public String uslovZaSelect() {
        if(iznajmljivanje != null){
            return " WHERE I.IZNAJMLJIVANJEID = " + iznajmljivanje.getIznajmljivanjeID();
        }
        return " WHERE K.KOLAID = " + kola.getKolaID();
    }

    public Iznajmljivanje getIznajmljivanje() {
        return iznajmljivanje;
    }

    public void setIznajmljivanje(Iznajmljivanje iznajmljivanje) {
        this.iznajmljivanje = iznajmljivanje;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public int getBrojDana() {
        return brojDana;
    }

    public void setBrojDana(int brojDana) {
        this.brojDana = brojDana;
    }

    public double getCenaPoDanu() {
        return cenaPoDanu;
    }

    public void setCenaPoDanu(double cenaPoDanu) {
        this.cenaPoDanu = cenaPoDanu;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public Kola getKola() {
        return kola;
    }

    public void setKola(Kola kola) {
        this.kola = kola;
    }

}
