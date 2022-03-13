package br.edu.ifsul.lpoo.clinica;

import br.edu.ifsul.lpoo.clinica.gui.JFramePrincipal;
import br.edu.ifsul.lpoo.clinica.gui.JMenuBarHome;
import br.edu.ifsul.lpoo.clinica.gui.JPanelHome;
//import br.edu.ifsul.lpoo.clinica.gui.JPanelHome;
import br.edu.ifsul.lpoo.clinica.gui.autenticacao.JPanelAutenticacao;
import br.edu.ifsul.lpoo.clinica.gui.consulta.JPanelConsulta;
import br.edu.ifsul.lpoo.clinica.gui.pet.JPanelPet;
import br.edu.ifsul.lpoo.clinica.model.Pessoa;
import br.edu.ifsul.lpoo.clinica.model.dao.PersistenciaJDBC;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos
 */
public class Controle {
    
    private PersistenciaJDBC conexaoJDBC;
    
    private JFramePrincipal frame; // frame principal da minha aplicação gráfica
    
    private JPanelAutenticacao pnlAutenticacao; //painel para a autenticacao do Jogador.
    
    private JMenuBarHome menuBar; //menu principal
    
    private JPanelHome pnlHome; // paine de boas vindas (home)
    
    private JPanelConsulta pnlConsulta;
    
    private JPanelPet pnlPet;
    
    //construtor.
    public Controle(){
                        
    }
    
    public boolean conectarBD() throws Exception {
        
            conexaoJDBC = new PersistenciaJDBC();

            if(getConexaoJDBC()!= null){
                return getConexaoJDBC().conexaoAberta();
            }

            return false;
    }   
    
    public void fecharBD(){
        
        System.out.println("Fechando conexao com o Banco de Dados");
        getConexaoJDBC().fecharConexao();
    }
    
    public void initComponents(){
    
        
        //inicia a interface gráfica.
        //"caminho feliz" : passo 5
        
        frame = new JFramePrincipal();
  
        pnlAutenticacao = new JPanelAutenticacao(this);
        
        menuBar = new JMenuBarHome(this);
        
        pnlHome = new JPanelHome(this);
        
        pnlConsulta = new JPanelConsulta(this);
        
        pnlPet = new JPanelPet(this);
        
        frame.addTela(pnlAutenticacao, "tela_autenticacao");//carta 1
        frame.addTela(pnlHome, "tela_home");//carta 2
        frame.addTela(pnlConsulta, "tela_consulta");
        frame.addTela(pnlPet, "tela_pet");
        
        frame.showTela("tela_autenticacao"); // apreseta a carta cujo nome é "tela_autenticacao"
        
        frame.setVisible(true); // torna visível o jframe
                
        
    }
    
    public void autenticar(String cpf, String senha) {
        
        try{

            Pessoa p =  getConexaoJDBC().doLogin(cpf, senha);
            
            if(p != null){

                JOptionPane.showMessageDialog(pnlAutenticacao, "Usuário "+p.getNome()+" autenticado com sucesso!", "Autenticação", JOptionPane.INFORMATION_MESSAGE);

                frame.setJMenuBar(menuBar);//adiciona o menu de barra no frame
                frame.showTela("tela_home");//muda a tela para o painel de boas vindas (home)

            }else{

                JOptionPane.showMessageDialog(pnlAutenticacao, "Dados inválidos!", "Autenticação", JOptionPane.INFORMATION_MESSAGE);
            }

        }catch(Exception e){

            JOptionPane.showMessageDialog(pnlAutenticacao, "Erro ao executar a autenticação no Banco de Dados!", "Autenticação", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public void showTela(String nomeTela){
        
        if(nomeTela.equals("tela_consulta")){
            pnlConsulta.getListagem().populaTable();
        }else if(nomeTela.equals("tela_pet")){
            pnlPet.getListagem().populaTable();
        }
        frame.showTela(nomeTela);
    }

    public PersistenciaJDBC getConexaoJDBC() {
        return conexaoJDBC;
    }
            
    
    
}