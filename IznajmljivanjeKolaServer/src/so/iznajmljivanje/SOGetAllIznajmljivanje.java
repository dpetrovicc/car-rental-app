/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.iznajmljivanje;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Iznajmljivanje;
import domain.StavkaIznajmljivanja;
import java.util.ArrayList;
import so.AbstractSO;


public class SOGetAllIznajmljivanje extends AbstractSO {

    private ArrayList<Iznajmljivanje> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Iznajmljivanje)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Iznajmljivanje!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        
        ArrayList<AbstractDomainObject> svaIznajmljivanja = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Iznajmljivanje>) (ArrayList<?>) svaIznajmljivanja;

        
        for (Iznajmljivanje iznajmljivanje : lista) {

            StavkaIznajmljivanja stavkaIznajmljivanja = new StavkaIznajmljivanja();
            stavkaIznajmljivanja.setIznajmljivanje(iznajmljivanje);
            
            ArrayList<StavkaIznajmljivanja> stavkeTrenutnogIznajmljivanja
                    = (ArrayList<StavkaIznajmljivanja>) (ArrayList<?>) DBBroker.getInstance().select(stavkaIznajmljivanja);
            
            iznajmljivanje.setStavkeIznajmljivanja(stavkeTrenutnogIznajmljivanja);
        }
    }

    public ArrayList<Iznajmljivanje> getLista() {
        return lista;
    }

}
