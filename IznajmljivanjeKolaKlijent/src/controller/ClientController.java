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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import session.Session;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;
import transfer.util.Operation;



public class ClientController {

    private static ClientController instance;

    private ClientController() {
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public Zaposleni login(Zaposleni zaposleni) throws Exception {
        return (Zaposleni) sendRequest(Operation.LOGIN, zaposleni);
    }

    public void logout(Zaposleni ulogovani) throws Exception {
        sendRequest(Operation.LOGOUT, ulogovani);
    }

    public void addPoslPartner(PoslPartner poslPartner) throws Exception {
        sendRequest(Operation.ADD_POSL_PARTNER, poslPartner);
    }
    
    public void addKola(Kola kola) throws Exception {
        sendRequest(Operation.ADD_KOLA, kola);
    }
    

    public void addIznajmljivanje(Iznajmljivanje iznajmljivanje) throws Exception {
        sendRequest(Operation.ADD_IZNAJMLJIVANJE, iznajmljivanje);
    }

    public void deletePoslPartner(PoslPartner poslPartner) throws Exception {
        sendRequest(Operation.DELETE_POSL_PARTNER, poslPartner);
    }
    
    public void deleteKola(Kola kola) throws Exception {
        sendRequest(Operation.DELETE_KOLA, kola);
    }

    public void deleteIznajmljivanje(Iznajmljivanje iznajmljivanje) throws Exception {
        sendRequest(Operation.DELETE_IZNAJMLJIVANJE, iznajmljivanje);
    }

    public void updatePoslPartner(PoslPartner poslPartner) throws Exception {
        sendRequest(Operation.UPDATE_POSL_PARTNER, poslPartner);
    }
    
    public void updateKola(Kola kola) throws Exception {
        sendRequest(Operation.UPDATE_KOLA, kola);
    }

    public void updateIznajmljivanje(Iznajmljivanje iznajmljivanje) throws Exception {
        sendRequest(Operation.UPDATE_IZNAJMLJIVANJE, iznajmljivanje);
    }

    public ArrayList<PoslPartner> getAllPoslPartner() throws Exception {
        return (ArrayList<PoslPartner>) sendRequest(Operation.GET_ALL_POSL_PARTNER, null);
    }
    
    public ArrayList<Kola> getAllKola() throws Exception {
        return (ArrayList<Kola>) sendRequest(Operation.GET_ALL_KOLA, null);
    }

    public ArrayList<Iznajmljivanje> getAllIznajmljivanje(PoslPartner pp) throws Exception {
        return (ArrayList<Iznajmljivanje>) sendRequest(Operation.GET_ALL_IZNAJMLJIVANJE, pp);
    }

    public ArrayList<Mesto> getAllMesto() throws Exception {
        return (ArrayList<Mesto>) sendRequest(Operation.GET_ALL_MESTO, null);
    }

    public ArrayList<StavkaIznajmljivanja> getAllStavkaIznajmljivanja(Kola kola) throws Exception {
        return (ArrayList<StavkaIznajmljivanja>) sendRequest(Operation.GET_ALL_STAVKA_IZNAJMLJIVANJA, kola);
    }

    private Object sendRequest(int operation, Object data) throws Exception {
        Request request = new Request(operation, data);

        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(request);

        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response response = (Response) in.readObject();

        if (response.getResponseStatus().equals(ResponseStatus.Error)) {
            throw response.getException();
        } else {
            return response.getData();
        }

    }

}
