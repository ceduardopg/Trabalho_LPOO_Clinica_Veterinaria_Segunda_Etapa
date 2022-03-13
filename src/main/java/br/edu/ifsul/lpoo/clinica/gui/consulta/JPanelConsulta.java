package br.edu.ifsul.lpoo.clinica.gui.consulta;

import br.edu.ifsul.lpoo.clinica.Controle;
import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author Carlos
 */
public class JPanelConsulta extends JPanel {
    
    private CardLayout cardLayout;
    private Controle controle;
    
    private JPanelConsultaListagem listagem;
    private JPanelConsultaFormulario formulario;
    
    
    public JPanelConsulta(Controle controle){
        
        this.controle = controle;
        initComponents();
    }
    
    private void initComponents(){
        
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        
        listagem = new JPanelConsultaListagem(this, controle);
        formulario = new JPanelConsultaFormulario(this, controle);
                
        this.add(getListagem(), "tela_consulta_listagem");
        this.add(formulario, "tela_consulta_formulario");
        
        cardLayout.show(this, "tela_consulta_listagem");
        
    }
    
    public void showTela(String nomeTela){
                                
        if(nomeTela.equals("tela_consulta_listagem")){
            
            listagem.populaTable();
            
        }else if(nomeTela.equals("tela_consulta_formulario")){
            
            getFormulario().populaComboPet();
        }
        
        cardLayout.show(this, nomeTela);
    }

    public Controle getControle() {
        return controle;
    }
    
    public JPanelConsultaFormulario getFormulario() {
        return formulario;
    }

    public JPanelConsultaListagem getListagem() {
        return listagem;
    }
        
}