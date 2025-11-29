/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.posl_partner;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.PoslPartner;
import so.AbstractSO;


public class SODeletePoslPartner extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof PoslPartner)) {
            throw new Exception("Prosledjeni objekat nije instanca klase PoslPartner!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }

}
