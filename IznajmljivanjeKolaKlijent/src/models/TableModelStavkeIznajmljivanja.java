/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import domain.StavkaIznajmljivanja;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class TableModelStavkeIznajmljivanja extends AbstractTableModel {

    private ArrayList<StavkaIznajmljivanja> lista;
    private String[] kolone = {"Rb", "Kola", "Datum od", "Datum do", "Broj dana", "Iznos"};
    private int rb;

    public TableModelStavkeIznajmljivanja() {
        lista = new ArrayList<>();
    }

    public TableModelStavkeIznajmljivanja(ArrayList<StavkaIznajmljivanja> stavkeIznajmljivanja) {
        lista = stavkeIznajmljivanja;
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
        StavkaIznajmljivanja si = lista.get(row);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        switch (column) {
            case 0:
                return si.getRb();
            case 1:
                return si.getKola().getMarka() + " " + si.getKola().getModel();
            case 2:
                return sdf.format(si.getDatumOd());
            case 3:
                return sdf.format(si.getDatumDo());
            case 4:
                return si.getBrojDana();
            case 5:
                return si.getIznos() + "€";

            default:
                return null;
        }
    }

    public void dodajStavku(StavkaIznajmljivanja si) {
        rb = lista.size();
        si.setRb(++rb);
        lista.add(si);
        fireTableDataChanged();
    }

    public void obrisiStavku(int row) {
        lista.remove(row);

        rb = 0;
        for (StavkaIznajmljivanja stavkaIznajmljivanja : lista) {
            stavkaIznajmljivanja.setRb(++rb);
        }

        fireTableDataChanged();
    }

    public double vratiUkupanIznos() {
        double ukupanIznos = 0;

        for (StavkaIznajmljivanja stavkaIznajmljivanja : lista) {
            ukupanIznos += stavkaIznajmljivanja.getIznos();
        }

        return ukupanIznos;
    }

    public ArrayList<StavkaIznajmljivanja> getLista() {
        return lista;
    }

}
