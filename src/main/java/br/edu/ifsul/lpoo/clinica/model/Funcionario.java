/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.lpoo.clinica.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 *
 * @author Carlos
 */

@Entity
@Table(name = "tb_funcionario")
@DiscriminatorValue("Fu")
public class Funcionario extends Pessoa{
    
    @Column(nullable = false, length = 11)
    private String numero_ctps;
    
    @Column(nullable = false, length = 11)
    private String numero_pis;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Cargo cargo;
    
    public Funcionario(){
        
    }
    
    public String getNumero_ctps() {
        return numero_ctps;
    }

    public void setNumero_ctps(String numero_ctps) {
        this.numero_ctps = numero_ctps;
    }

    public String getNumero_pis() {
        return numero_pis;
    }

    public void setNumero_pis(String numero_pis) {
        this.numero_pis = numero_pis;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    
    
}
