package br.edu.ifsul.lpoo.clinica.model.dao;

import br.edu.ifsul.lpoo.clinica.model.Consulta;
import br.edu.ifsul.lpoo.clinica.model.Pessoa;
import br.edu.ifsul.lpoo.clinica.model.Pet;
import java.util.List;

/**
 *
 * @author Carlos
 */
public interface InterfacePersistencia {
    
    public Boolean conexaoAberta();
    
    public void fecharConexao();
    
    public Object find(Class c, Object id) throws Exception;
    
    public void persist(Object o) throws Exception;
    
    public void remover(Object o) throws Exception;
    
    public List<Consulta> listConsulta() throws Exception;
    
    public List<Pet> listPet() throws Exception;
    
    public Pessoa doLogin(String cpf, String senha) throws Exception;
       
}
