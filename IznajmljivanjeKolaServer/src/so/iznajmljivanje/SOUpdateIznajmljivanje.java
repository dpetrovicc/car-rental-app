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
import so.AbstractSO;


public class SOUpdateIznajmljivanje extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Iznajmljivanje)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Iznajmljivanje!");
        }

        Iznajmljivanje i = (Iznajmljivanje) ado;

        if (i.getStavkeIznajmljivanja().isEmpty()) {
            throw new Exception("Iznajmljivanje mora imati barem jednu stavku!");
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        
        DBBroker.getInstance().update(ado);
        Iznajmljivanje i = (Iznajmljivanje) ado;
        DBBroker.getInstance().delete(i.getStavkeIznajmljivanja().get(0));

        for (StavkaIznajmljivanja stavkaIznajmljivanja : i.getStavkeIznajmljivanja()) {
            DBBroker.getInstance().insert(stavkaIznajmljivanja);
        }

        
    }

}
