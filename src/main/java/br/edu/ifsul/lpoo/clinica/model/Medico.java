package br.edu.ifsul.lpoo.clinica.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Carlos
 */

@Entity
@Table(name = "tb_medico")
@DiscriminatorValue("M")
public class Medico extends Pessoa{
    
    @Column(nullable = false)
    private String numero_crmv;
    
    public Medico(){
        
    }
    
    public String getNumero_crmv() {
        return numero_crmv;
    }

    public void setNumero_crmv(String numero_crmv) {
        this.numero_crmv = numero_crmv;
    }
    
    
}
