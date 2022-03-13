/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Table(name = "tb_fornecedor")
@DiscriminatorValue("Fo")
public class Fornecedor extends Pessoa{
    
    @Column(nullable = false, length = 14)
    private String cnpj;
    
    @Column(nullable = false, length = 9)
    private String ie;
    
    public Fornecedor(){
        
    }
    
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }
     
}
