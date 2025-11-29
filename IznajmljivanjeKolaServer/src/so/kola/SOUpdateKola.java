/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.kola;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Kola;
import java.util.ArrayList;
import so.AbstractSO;


public class SOUpdateKola extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Kola)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Kola!");
        }

        Kola k = (Kola) ado;

        if (k.getCenaPoDanu() < 10 || k.getCenaPoDanu() > 1000) {
            throw new Exception("Cena pok danu mora biti izmedju 10€ i 1000€!");
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }

}
