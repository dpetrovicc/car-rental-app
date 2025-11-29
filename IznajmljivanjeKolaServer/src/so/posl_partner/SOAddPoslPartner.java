/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.posl_partner;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.PoslPartner;
import java.util.ArrayList;
import so.AbstractSO;


public class SOAddPoslPartner extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof PoslPartner)) {
            throw new Exception("Prosledjeni objekat nije instanca klase PoslPartner!");
        }

        PoslPartner pp = (PoslPartner) ado;

        ArrayList<PoslPartner> poslovniPartneri
                = (ArrayList<PoslPartner>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (PoslPartner poslPartner : poslovniPartneri) {
            if (poslPartner.getEmail().equals(pp.getEmail())) {
                throw new Exception("Poslovni partner sa tim emailom vec postoji!");
            }
            if (poslPartner.getTelefon().equals(pp.getTelefon())) {
                throw new Exception("Poslovni partner sa tim telefonom vec postoji!");
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }

}
