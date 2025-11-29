/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Kola;
import domain.PoslPartner;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


public class TableModelKola extends AbstractTableModel implements Runnable {

    private ArrayList<Kola> lista;
    private String[] kolone = {"ID", "Marka", "Model", "Opis", "CenaPoDanu"};
    private String parametar = "";

    public TableModelKola() {
        try {
            lista = ClientController.getInstance().getAllKola();
        } catch (Exception ex) {
            Logger.getLogger(TableModelKola.class.getName()).log(Level.SEVERE, null, ex);
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
        Kola k = lista.get(row);

        switch (column) {
            case 0:
                return k.getKolaID();
            case 1:
                return k.getMarka();
            case 2:
                return k.getModel();
            case 3:
                return k.getOpis();
            case 4:
                return k.getCenaPoDanu();

            default:
                return null;
        }
    }

    public Kola getSelectedKola(int row) {
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
            Logger.getLogger(TableModelKola.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = ClientController.getInstance().getAllKola();
            if (!parametar.equals("")) {
                ArrayList<Kola> novaLista = new ArrayList<>();
                for (Kola k : lista) {
                    if (k.getMarka().toLowerCase().contains(parametar.toLowerCase())
                            || k.getModel().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(k);
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
