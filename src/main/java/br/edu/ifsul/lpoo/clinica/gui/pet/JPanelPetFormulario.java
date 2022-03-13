/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.lpoo.clinica.gui.pet;

import br.edu.ifsul.lpoo.clinica.Controle;
import br.edu.ifsul.lpoo.clinica.model.Cliente;
import br.edu.ifsul.lpoo.clinica.model.Pet;
import br.edu.ifsul.lpoo.clinica.model.Raca;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos
 */
public class JPanelPetFormulario extends javax.swing.JPanel {

    private JPanelPet pnlPet;
    private Controle controle;
    
    private Pet pet;
    private SimpleDateFormat format;
    
    public JPanelPetFormulario(JPanelPet pnlPet, Controle controle) {
        this.pnlPet = pnlPet;
        this.controle = controle;
        initComponents();
        format = new SimpleDateFormat("dd/MM/yyyy");
    }
    
    public Pet getPet(){
        
        //validacao do formulario
         if(txfNome.getText().trim().length() > 0
                && txfObservacao.getText().trim().length() > 0
                && txfCliente.getText().trim().length() > 0
                && txfRaca.getText().trim().length() > 0){

            Pet p = new Pet();
            if (txfId.getText().trim().length() > 0) {
                p.setId(Integer.parseInt(txfId.getText().trim()));
            }
            p.setNome(txfNome.getText().trim());  
            p.setObservacao(txfObservacao.getText().trim());            

            Cliente c = new Cliente();
            c.setCpf(txfCliente.getText().trim());
            p.setCliente(c);
            
            Raca r = new Raca();
            r.setId(Integer.parseInt(txfRaca.getText().trim()));
            p.setRaca(r);

            return p;
         }

         return null;
    }
    
    public void setPet(Pet p){

        if(p == null){//se o parametro estiver nullo, limpa o formulario
            txfId.setText("");
            txfId.setEditable(false);
            txfNome.setText("");
            txfObservacao.setText("");
            txfCliente.setText("");
            txfRaca.setText("");
            pet = null;

        }else{
            pet = p;
            txfId.setText(pet.getId().toString());
            txfId.setEditable(false);
            txfNome.setText(pet.getNome());
            txfObservacao.setText(pet.getObservacao());
            txfCliente.setText(pet.getCliente().getCpf().toString());
            txfRaca.setText(pet.getRaca().getId().toString());         

        }

    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlSul = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnlCentro = new javax.swing.JPanel();
        lblId = new javax.swing.JLabel();
        txfId = new javax.swing.JTextField();
        lblNome = new javax.swing.JLabel();
        txfNome = new javax.swing.JTextField();
        lblObservacao = new javax.swing.JLabel();
        txfObservacao = new javax.swing.JTextField();
        lblCliente = new javax.swing.JLabel();
        txfCliente = new javax.swing.JTextField();
        lblRaca = new javax.swing.JLabel();
        txfRaca = new javax.swing.JTextField();

        setLayout(new java.awt.BorderLayout());

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        pnlSul.add(btnSalvar);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        pnlSul.add(btnCancelar);

        add(pnlSul, java.awt.BorderLayout.PAGE_END);

        pnlCentro.setLayout(new java.awt.GridBagLayout());

        lblId.setText("Id:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlCentro.add(lblId, gridBagConstraints);

        txfId.setColumns(2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlCentro.add(txfId, gridBagConstraints);

        lblNome.setText("Nome:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlCentro.add(lblNome, gridBagConstraints);

        txfNome.setColumns(15);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlCentro.add(txfNome, gridBagConstraints);

        lblObservacao.setText("Observação:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlCentro.add(lblObservacao, gridBagConstraints);

        txfObservacao.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlCentro.add(txfObservacao, gridBagConstraints);

        lblCliente.setText("CPF do Cliente:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlCentro.add(lblCliente, gridBagConstraints);

        txfCliente.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlCentro.add(txfCliente, gridBagConstraints);

        lblRaca.setText("Id da Raça:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlCentro.add(lblRaca, gridBagConstraints);

        txfRaca.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlCentro.add(txfRaca, gridBagConstraints);

        add(pnlCentro, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        Pet p = getPet();//recupera os dados do formulÃ¡rio

        if(p != null){

            try {

                pnlPet.getControle().getConexaoJDBC().persist(p);

                JOptionPane.showMessageDialog(this, "Pet armazenado!", "Salvar", JOptionPane.INFORMATION_MESSAGE);

                pnlPet.showTela("tela_pet_listagem");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar pet! : "+ex.getMessage(), "Salvar", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }

        }else{

            JOptionPane.showMessageDialog(this, "Preencha o formulário!", "Edição", JOptionPane.INFORMATION_MESSAGE);
        }
        
        pnlPet.showTela("tela_pet_listagem");
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        pnlPet.showTela("tela_pet_listagem");
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblObservacao;
    private javax.swing.JLabel lblRaca;
    private javax.swing.JPanel pnlCentro;
    private javax.swing.JPanel pnlSul;
    private javax.swing.JTextField txfCliente;
    private javax.swing.JTextField txfId;
    private javax.swing.JTextField txfNome;
    private javax.swing.JTextField txfObservacao;
    private javax.swing.JTextField txfRaca;
    // End of variables declaration//GEN-END:variables
}