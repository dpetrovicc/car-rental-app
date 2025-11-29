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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import so.AbstractSO;

public class SOAddIznajmljivanje extends AbstractSO {

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
        
        PreparedStatement ps = DBBroker.getInstance().insert(ado);
        
        
        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        int iznajmljivanjeID = tableKeys.getInt(1);
        
        Iznajmljivanje novoIznajmljivanje = (Iznajmljivanje) ado;
        novoIznajmljivanje.setIznajmljivanjeID(iznajmljivanjeID);
        
        
        for (StavkaIznajmljivanja stavkaIznajmljivanja : novoIznajmljivanje.getStavkeIznajmljivanja()) {
            stavkaIznajmljivanja.setIznajmljivanje(novoIznajmljivanje); 
            
            DBBroker.getInstance().insert(stavkaIznajmljivanja);
        }
        
    }

}
