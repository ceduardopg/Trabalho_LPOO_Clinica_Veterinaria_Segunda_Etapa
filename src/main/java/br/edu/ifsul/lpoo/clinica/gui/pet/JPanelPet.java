package br.edu.ifsul.lpoo.clinica.gui.pet;

import br.edu.ifsul.lpoo.clinica.Controle;
import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author Carlos
 */
public class JPanelPet extends JPanel{
    private CardLayout cardLayout;
    private Controle controle;
    
    private JPanelPetListagem listagem;
    private JPanelPetFormulario formulario;
    
    
    public JPanelPet(Controle controle){
        
        this.controle = controle;
        initComponents();
    }
    
    private void initComponents(){
        
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        
        listagem = new JPanelPetListagem(this, controle);
        formulario = new JPanelPetFormulario(this, controle);
                
        this.add(getListagem(), "tela_pet_listagem");
        this.add(formulario, "tela_pet_formulario");
        
        cardLayout.show(this, "tela_pet_listagem");
        
    }
    
    public void showTela(String nomeTela){
                                
        if(nomeTela.equals("tela_pet_listagem")){            
            listagem.populaTable();      
        }
        
        cardLayout.show(this, nomeTela);
    }

    public Controle getControle() {
        return controle;
    }
    
    public JPanelPetFormulario getFormulario() {
        return formulario;
    }

    public JPanelPetListagem getListagem() {
        return listagem;
    }
}
