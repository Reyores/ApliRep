package com.projet.aplirep;

import com.magasin.Magasin;

import java.util.ArrayList;
import java.util.List;

public class Banque {

    List<CompteBancaire> lComptesBancaires;

    public Banque() {
        lComptesBancaires=new ArrayList<>();
    }

    public Banque(List<CompteBancaire> lComptesBancaires) {
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
    boolean estSolvable(String id, double sommeAPayer){
        boolean res=false;
        if(verifierIden(id)){
            CompteBancaire compteBancaire=lComptesBancaires.get(getIndexCompteBancaire(id));
            if (compteBancaire.getSomme()>=sommeAPayer)
                res=true;
        }
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

}
