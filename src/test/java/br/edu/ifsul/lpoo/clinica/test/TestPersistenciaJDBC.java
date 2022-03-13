package br.edu.ifsul.lpoo.clinica.test;

import br.edu.ifsul.lpoo.clinica.model.Cliente;
import br.edu.ifsul.lpoo.clinica.model.Consulta;
import br.edu.ifsul.lpoo.clinica.model.Medico;
import br.edu.ifsul.lpoo.clinica.model.Pet;
import br.edu.ifsul.lpoo.clinica.model.Raca;
import br.edu.ifsul.lpoo.clinica.model.dao.PersistenciaJDBC;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author Carlos
 */
public class TestPersistenciaJDBC {
    
    //@Test
    public void testarConexao() throws Exception{
        PersistenciaJDBC persistencia = new PersistenciaJDBC();
        
        if(persistencia.conexaoAberta()){
            System.out.println("Conexao com o BD aberta utilizando JDBC");
            persistencia.fecharConexao();
        }else{
            System.out.println("Não abriu conexão via JDBC");
        }
    }

    //@Test
    public void testListPersistenciaConsulta() throws Exception {
        
        PersistenciaJDBC persistencia = new PersistenciaJDBC();
        if(persistencia.conexaoAberta()){
            
            List<Consulta> lista = persistencia.listConsulta();
            
            if(!lista.isEmpty()){
            
                for(Consulta c : lista){

                    System.out.println("Id: "+c.getId()+
                            "\nObservação: "+c.getObservacao()+
                            "\nValor: "+c.getValor());
                    
                    c.setObservacao("Conteúdo");
                    persistencia.persist(c);
                    System.out.println("Alterou a observação para: "+c.getObservacao());
                    persistencia.remover(c);
                }
            }else{
                
                System.out.println("Não encontrou a consulta");
                
                Consulta con = new Consulta();
                con.setObservacao("Nada");   
                con.setValor(30.0f);
                Medico m = new Medico();
                m.setCpf("99999999999");
                con.setMedico(m);
                Pet p = new Pet();
                p.setId(2);
                con.setPet(p);
                persistencia.persist(con);               
                System.out.println("Cadastrou a consulta "+con.getId());
                
            }
            
            persistencia.fecharConexao();
        }else{
            System.out.println("Nao abriu a conexao com o BD via JDBC");
        }
    }
    
    //@Test
    public void testListPersistenciaPet() throws Exception {
        
        PersistenciaJDBC persistencia = new PersistenciaJDBC();
        if(persistencia.conexaoAberta()){
            
            List<Pet> lista = persistencia.listPet();
            
            if(!lista.isEmpty()){
            
                for(Pet p : lista){

                    System.out.println("Id: "+p.getId()+
                            "\nNome: "+p.getNome()+
                            "\nObservação: "+p.getObservacao());
                    
                    p.setObservacao("Conteúdo");
                    persistencia.persist(p);
                    System.out.println("Alterou a observação para: "+p.getObservacao());
                    persistencia.remover(p);
                }
            }else{
                
                System.out.println("Não encontrou o pet");
                
                Pet pet = new Pet();
                pet.setNome("Bob");
                pet.setObservacao("Nada");   
                
                Cliente c = new Cliente();
                c.setCpf("99999999998");
                pet.setCliente(c);
                Raca r = new Raca();
                r.setId(1);
                pet.setRaca(r);
                persistencia.persist(pet);               
                System.out.println("Cadastrou o pet "+pet.getId());
                
            }
            
            persistencia.fecharConexao();
        }else{
            System.out.println("Nao abriu a conexao com o BD via JDBC");
        }
    }
}
