/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Iznajmljivanje;
import domain.Kola;
import domain.Mesto;
import domain.PoslPartner;
import domain.StavkaIznajmljivanja;
import domain.Zaposleni;
import java.util.ArrayList;
import so.iznajmljivanje.SOAddIznajmljivanje;
import so.iznajmljivanje.SODeleteIznajmljivanje;
import so.iznajmljivanje.SOGetAllIznajmljivanje;
import so.iznajmljivanje.SOUpdateIznajmljivanje;
import so.kola.SOAddKola;
import so.kola.SODeleteKola;
import so.kola.SOGetAllKola;
import so.kola.SOUpdateKola;
import so.login.SOLogin;
import so.mesto.SOGetAllMesto;
import so.posl_partner.SOAddPoslPartner;
import so.posl_partner.SODeletePoslPartner;
import so.posl_partner.SOGetAllPoslPartner;
import so.posl_partner.SOUpdatePoslPartner;
import so.stavka_iznajmljivanja.SOGetAllStavkaIznajmljivanja;


public class ServerController {

    private static ServerController instance;
    private ArrayList<Zaposleni> ulogovaniZaposleni = new ArrayList<>();

    private ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public ArrayList<Zaposleni> getUlogovaniZaposleni() {
        return ulogovaniZaposleni;
    }

    public void setUlogovaniZaposleni(ArrayList<Zaposleni> ulogovaniZaposleni) {
        this.ulogovaniZaposleni = ulogovaniZaposleni;
    }

    public Zaposleni login(Zaposleni zaposleni) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(zaposleni);
        return so.getUlogovani();
    }

    public void addPoslPartner(PoslPartner poslPartner) throws Exception {
        (new SOAddPoslPartner()).templateExecute(poslPartner);
    }

    public void addIznajmljivanje(Iznajmljivanje iznajmljivanje) throws Exception {
        (new SOAddIznajmljivanje()).templateExecute(iznajmljivanje);
    }
    
    public void addKola(Kola kola) throws Exception {
        (new SOAddKola()).templateExecute(kola);
    }

    public void deletePoslPartner(PoslPartner poslPartner) throws Exception {
        (new SODeletePoslPartner()).templateExecute(poslPartner);
    }

    public void deleteIznajmljivanje(Iznajmljivanje iznajmljivanje) throws Exception {
        (new SODeleteIznajmljivanje()).templateExecute(iznajmljivanje);
    }
    
    public void deleteKola(Kola kola) throws Exception {
        (new SODeleteKola()).templateExecute(kola);
    }

    public void updatePoslPartner(PoslPartner poslPartner) throws Exception {
        (new SOUpdatePoslPartner()).templateExecute(poslPartner);
    }

    public void updateIznajmljivanje(Iznajmljivanje iznajmljivanje) throws Exception {
        (new SOUpdateIznajmljivanje()).templateExecute(iznajmljivanje);
    }
    
    public void updateKola(Kola kola) throws Exception {
        (new SOUpdateKola()).templateExecute(kola);
    }

    public ArrayList<PoslPartner> getAllPoslPartner() throws Exception {
        SOGetAllPoslPartner so = new SOGetAllPoslPartner();
        so.templateExecute(new PoslPartner());
        return so.getLista();
    }

    public ArrayList<Iznajmljivanje> getAllIznajmljivanje(PoslPartner partner) throws Exception {
        SOGetAllIznajmljivanje so = new SOGetAllIznajmljivanje();
        
        Iznajmljivanje i = new Iznajmljivanje();
        i.setPoslPartner(partner);
        
        so.templateExecute(i);
        return so.getLista();
    }

    public ArrayList<Mesto> getAllMesto() throws Exception {
        SOGetAllMesto so = new SOGetAllMesto();
        so.templateExecute(new Mesto());
        return so.getLista();
    }

        public ArrayList<StavkaIznajmljivanja> getAllStavkaIznajmljivanja(Kola kola) throws Exception {
        SOGetAllStavkaIznajmljivanja so = new SOGetAllStavkaIznajmljivanja();
        
        StavkaIznajmljivanja si = new StavkaIznajmljivanja();
        si.setKola(kola);
        
        so.templateExecute(si);
        return so.getLista();
    }
    
    public ArrayList<Kola> getAllKola() throws Exception {
        SOGetAllKola so = new SOGetAllKola();
        so.templateExecute(new Kola());
        return so.getLista();
    }

    public void logout(Zaposleni ulogovani) {
        ulogovaniZaposleni.remove(ulogovani);
    }

}
