/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import controller.ServerController;
import domain.Iznajmljivanje;
import domain.Kola;
import domain.PoslPartner;
import domain.Zaposleni;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;
import transfer.util.Operation;


public class ThreadClient extends Thread {

    private Socket socket;

    ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request request = (Request) in.readObject();
                Response response = handleRequest(request);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request request) {
        Response response = new Response(null, null, ResponseStatus.Success);
        try {
            switch (request.getOperation()) {
                case Operation.ADD_IZNAJMLJIVANJE:
                    ServerController.getInstance().addIznajmljivanje((Iznajmljivanje) request.getData());
                    break;
                case Operation.ADD_POSL_PARTNER:
                    ServerController.getInstance().addPoslPartner((PoslPartner) request.getData());
                    break;
                case Operation.ADD_KOLA:
                    ServerController.getInstance().addKola((Kola) request.getData());
                    break;
                case Operation.DELETE_IZNAJMLJIVANJE:
                    ServerController.getInstance().deleteIznajmljivanje((Iznajmljivanje) request.getData());
                    break;
                case Operation.DELETE_POSL_PARTNER:
                    ServerController.getInstance().deletePoslPartner((PoslPartner) request.getData());
                    break;
                case Operation.DELETE_KOLA:
                    ServerController.getInstance().deleteKola((Kola) request.getData());
                    break;
                case Operation.UPDATE_IZNAJMLJIVANJE:
                    ServerController.getInstance().updateIznajmljivanje((Iznajmljivanje) request.getData());
                    break;
                case Operation.UPDATE_POSL_PARTNER:
                    ServerController.getInstance().updatePoslPartner((PoslPartner) request.getData());
                    break;
                case Operation.UPDATE_KOLA:
                    ServerController.getInstance().updateKola((Kola) request.getData());
                    break;
                case Operation.GET_ALL_IZNAJMLJIVANJE:
                    response.setData(ServerController.getInstance().getAllIznajmljivanje((PoslPartner) request.getData()));
                    break;
                case Operation.GET_ALL_MESTO:
                    response.setData(ServerController.getInstance().getAllMesto());
                    break;
                case Operation.GET_ALL_POSL_PARTNER:
                    response.setData(ServerController.getInstance().getAllPoslPartner());
                    break;
                case Operation.GET_ALL_KOLA:
                    response.setData(ServerController.getInstance().getAllKola());
                    break;
                case Operation.GET_ALL_STAVKA_IZNAJMLJIVANJA:
                    response.setData(ServerController.getInstance().getAllStavkaIznajmljivanja((Kola) request.getData()));
                    break;
                case Operation.LOGIN:
                    Zaposleni zaposleni = (Zaposleni) request.getData();
                    Zaposleni zap = ServerController.getInstance().login(zaposleni);
                    response.setData(zap);
                    break;
                case Operation.LOGOUT:
                    Zaposleni ulogovani = (Zaposleni) request.getData();
                    ServerController.getInstance().logout(ulogovani);
                    break;
                default:
                    return null;
            }
        } catch (Exception ex) {
            response.setResponseStatus(ResponseStatus.Error);
            response.setException(ex);
        }
        return response;
    }

}
