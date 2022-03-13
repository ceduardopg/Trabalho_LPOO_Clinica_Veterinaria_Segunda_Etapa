package br.edu.ifsul.lpoo.clinica.gui;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author Carlos
 */
public class JFramePrincipal extends JFrame implements WindowListener{

    
    public CardLayout cardLayout;
    
    public JPanel painel;//painel.
    
    
    public JFramePrincipal(){
        
        initComponents();        
        
    }
    
    
    private void initComponents(){
        //customização do JFrame
        
        this.setTitle("Sistema para CRUD - Clinica Veterinária"); //seta o título do jframe
        
        this.setMinimumSize(new Dimension(1280,720)); //tamanho minimo quando for reduzido.
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // por padrão abre maximizado.
        
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );// finaliza o processo quando o frame é fechado.  
        
        //this.addWindowListener(this);//adiciona o listener no frame
        
        cardLayout = new CardLayout();//iniciando o gerenciador de layout para esta JFrame
        painel = new JPanel();//inicializacao
                
        painel.setLayout(cardLayout);//definindo o cardLayout para o paineldeFundo
                
        this.add(painel);  //adiciona no JFrame o paineldeFundo
                
    }
    
    public void addTela(JPanel p, String nome){   
        
            painel.add(p, nome); //adiciona uma "carta no baralho".
    }

    public void showTela(String nome){
        
            cardLayout.show(painel, nome); //localiza a "carta no baralho" e mostra.
    }
    
    @Override
    public void windowOpened(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent we) {
        System.out.println("Fechando o jframe ..");
    }

    @Override
    public void windowClosed(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
