/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.PoslPartner;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


public class TableModelPoslPartneri extends AbstractTableModel implements Runnable {

    private ArrayList<PoslPartner> lista;
    private String[] kolone = {"ID", "Ime", "Prezime", "Email", "Telefon"};
    private String parametar = "";

    public TableModelPoslPartneri() {
        try {
            lista = ClientController.getInstance().getAllPoslPartner();
        } catch (Exception ex) {
            Logger.getLogger(TableModelPoslPartneri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        PoslPartner pp = lista.get(row);

        switch (column) {
            case 0:
                return pp.getPartnerID();
            case 1:
                return pp.getIme();
            case 2:
                return pp.getPrezime();
            case 3:
                return pp.getEmail();
            case 4:
                return pp.getTelefon();
            case 5:
                return pp.getMesto();

            default:
                return null;
        }
    }

    public PoslPartner getSelectedPoslPartner(int row) {
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TableModelPoslPartneri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = ClientController.getInstance().getAllPoslPartner();
            if (!parametar.equals("")) {
                ArrayList<PoslPartner> novaLista = new ArrayList<>();
                for (PoslPartner pp : lista) {
                    if (pp.getIme().toLowerCase().contains(parametar.toLowerCase())
                            || pp.getPrezime().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(pp);
                    }
                }
                lista = novaLista;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
