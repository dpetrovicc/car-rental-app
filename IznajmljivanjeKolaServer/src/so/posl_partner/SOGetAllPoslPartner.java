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

public class SOGetAllPoslPartner extends AbstractSO {

    private ArrayList<PoslPartner> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof PoslPartner)) {
            throw new Exception("Prosledjeni objekat nije instanca klase PoslPartner!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> poslovniPartneri = DBBroker.getInstance().select(ado);
        lista = (ArrayList<PoslPartner>) (ArrayList<?>) poslovniPartneri;
    }

    public ArrayList<PoslPartner> getLista() {
        return lista;
    }

}
