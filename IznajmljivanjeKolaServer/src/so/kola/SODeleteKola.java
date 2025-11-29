/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.kola;

import so.posl_partner.*;
import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Kola;
import domain.PoslPartner;
import so.AbstractSO;


public class SODeleteKola extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Kola)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Kola!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }

}
