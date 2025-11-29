/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.util;


public interface Operation {

    public static final int LOGIN = 0;
    public static final int LOGOUT = 1;

    public static final int ADD_POSL_PARTNER = 2;
    public static final int DELETE_POSL_PARTNER = 3;
    public static final int UPDATE_POSL_PARTNER = 4;
    public static final int GET_ALL_POSL_PARTNER = 5;

    public static final int ADD_KOLA = 6;
    public static final int DELETE_KOLA = 7;
    public static final int UPDATE_KOLA = 8;
    public static final int GET_ALL_KOLA = 9;

    public static final int ADD_IZNAJMLJIVANJE = 10;
    public static final int DELETE_IZNAJMLJIVANJE = 11;
    public static final int UPDATE_IZNAJMLJIVANJE = 12;
    public static final int GET_ALL_IZNAJMLJIVANJE = 13;

    public static final int GET_ALL_STAVKA_IZNAJMLJIVANJA = 14;

    public static final int GET_ALL_MESTO = 15;

}
