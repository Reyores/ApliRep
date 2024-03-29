package com.projet.aplirep;

import com.controllers.BanqueLauncher;
import com.magasin.Magasin;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Banque extends UnicastRemoteObject implements BanqueInterface{

    List<CompteBancaire> lComptesBancaires;

    public Banque() throws RemoteException {
        super();
        lComptesBancaires=new ArrayList<>();
        CompteBancaire cb1 = new CompteBancaire("1234",300);
        CompteBancaire cb2 = new CompteBancaire("4567",125.5);
        CompteBancaire cb3 = new CompteBancaire("7890",10.75);

        lComptesBancaires.add(cb1);
        lComptesBancaires.add(cb2);
        lComptesBancaires.add(cb3);

    }

    public Banque(List<CompteBancaire> lComptesBancaires) throws RemoteException {
        super();
        this.lComptesBancaires = lComptesBancaires;
    }

    public void ajouterCompteBancaire(CompteBancaire compteBancaire){
        lComptesBancaires.add(compteBancaire);
    }

    /*
    boolean validerPaiement(){
        return false;
    }*/

    /**
     * verifie la solvabilite d'un identifiant par rapport a la somme a payer
     * @param id
     * @param sommeAPayer
     * @return
     */
    @Override
    public boolean estSolvable(String id, double sommeAPayer){
        boolean res=false;
        if(verifierIden(id)){
            CompteBancaire compteBancaire=lComptesBancaires.get(getIndexCompteBancaire(id));
            if (compteBancaire.getSomme()>=sommeAPayer){
                res=true;
                compteBancaire.setSomme(compteBancaire.getSomme() - sommeAPayer);
            }
        }
        BanqueLauncher.updateAffichage();
        return res;
    }

    /**
     * verifie l'existence d'un compte bancaire
     * @param id identifiant CB
     * @return true s'il existe, false sinon
     */
    boolean verifierIden(String id){
        boolean res=false;
        for (CompteBancaire c: lComptesBancaires) {
            if (c.getId().equals(id)) {
                res = true;
                break;
            }
        }
        return res;
    }

    /**
     * return -1 si l'index n'est pas trouvé
     * @param id
     * @return
     */
    public int getIndexCompteBancaire(String id){
        int res = -1;
        try {
            res=lComptesBancaires.indexOf(getCompteBancaireId(id));
            return res;
        }catch (Exception e){
            return res;
        }
    }

    /**
     * return NULL si le compte n'existe pas
     * @param id
     * @return
     */
    public CompteBancaire getCompteBancaireId(String id){
        CompteBancaire res=null;
        if(verifierIden(id)) {
            for (CompteBancaire c : lComptesBancaires) {
                if (c.getId().equals(id)) {
                    res = c;
                    break;
                }
            }
        }
        return res;
    }

    public List<CompteBancaire> getAllComptes(){
        return lComptesBancaires;
    }

    public void update(){

    }

}
