/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.lpoo.clinica;

import javax.swing.JOptionPane;

/**
 *
 * @author Carlos
 */
public class ClinicaVeterinaria {
    
    private Controle controle;
    
    public ClinicaVeterinaria() {
        try {
            controle = new Controle();

            if (controle.conectarBD()) {
                controle.initComponents();
            } else {
                JOptionPane.showMessageDialog(null, "Não conectou no Banco de Dados!", "Banco de Dados", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar conectar no Banco de Dados: " + ex.getLocalizedMessage(), "Banco de Dados", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        
        new ClinicaVeterinaria();
    }
}
