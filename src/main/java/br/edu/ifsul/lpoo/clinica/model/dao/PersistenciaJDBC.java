package br.edu.ifsul.lpoo.clinica.model.dao;

import br.edu.ifsul.lpoo.clinica.model.Cliente;
import br.edu.ifsul.lpoo.clinica.model.Consulta;
import br.edu.ifsul.lpoo.clinica.model.Medico;
import br.edu.ifsul.lpoo.clinica.model.Pessoa;
import br.edu.ifsul.lpoo.clinica.model.Pet;
import br.edu.ifsul.lpoo.clinica.model.Raca;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Carlos
 */
public class PersistenciaJDBC implements InterfacePersistencia {
    
    private final String DRIVER = "org.postgresql.Driver";
    private final String USER = "postgres";
    private final String SENHA = "123456";
    public static final String URL = "jdbc:postgresql://localhost:5432/clinica";
    private Connection con = null;

    public PersistenciaJDBC () throws Exception {
        Class.forName(DRIVER);
        System.out.println("Tentando estabelecer conexao JDBC com : "+URL+" ...");
            
        this.con = (Connection) DriverManager.getConnection(URL, USER, SENHA); 
        
    }
    
    
    @Override
    public Boolean conexaoAberta() {
        try {
            if(con != null)
                return !con.isClosed();
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return false;
        
    }

    @Override
    public void fecharConexao() {        
        try{                               
            this.con.close();//fecha a conexao.
            System.out.println("Fechou conexao JDBC");
        }catch(SQLException e){            
            e.printStackTrace();
        } 
        
    }
        
    @Override
    public Pessoa doLogin(String cpf, String senha) throws Exception {
        Pessoa pessoa = null;

        PreparedStatement ps
                = this.con.prepareStatement("select p.nome, p.cpf, p.senha from tb_pessoa p where p.cpf = ? and p.senha = ? ");

        ps.setString(1, cpf);
        ps.setString(2, senha);

        ResultSet rs = ps.executeQuery();//o ponteiro do REsultSet inicialmente está na linha -1

        if (rs.next()) {//se a matriz (ResultSet) tem uma linha

            pessoa = new Pessoa();
            pessoa.setNome(rs.getString("nome"));
            pessoa.setCpf(rs.getString("cpf"));
        }

        ps.close();
        return pessoa;      
    }

    @Override
    public Object find(Class c, Object id) throws Exception {
        if(c == Consulta.class){
            
            PreparedStatement ps = this.con.prepareStatement("SELECT c.id, "
                    + "c.data, c.data_retorno, c.observacao, c.valor, c.medico_id, pe.nome, c.pet_id, p.nome "
                    + "FROM tb_consulta c, tb_pet p, tb_medico m, tb_pessoa pe "
                    + "WHERE p.id = c.pet_id "
                    + "AND m.cpf = c.medico_id "
                    + "AND m.cpf = pe.cpf "
                    + "AND c.id = ?");
            //PreparedStatement ps = this.con.prepareStatement("SELECT * FROM tb_consulta WHERE id = ?");
            ps.setInt(1, Integer.parseInt(id.toString()));
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
            
                Consulta con = new Consulta();
                con.setId(rs.getInt("id"));
                
                Calendar dt = Calendar.getInstance();
                dt.setTimeInMillis(rs.getDate("data").getTime());                        
                con.setData(dt);
                
                Calendar dtret = Calendar.getInstance();
                dtret.setTimeInMillis(rs.getDate("data_retorno").getTime());     
                
                con.setData_retorno(dtret);
                con.setObservacao(rs.getString("observacao"));
                con.setValor(rs.getFloat("valor"));
                
                Medico m = new Medico();
                m.setCpf(rs.getString("cpf"));
                m.setNome("nome");
                
                Pet p = new Pet();
                p.setId(rs.getInt("pet_id"));
                p.setNome("nome");
                ps.close();
                
                return con;                
            }
            
        }else if(c == Pet.class){
            PreparedStatement ps = this.con.prepareStatement("SELECT p.id, "
                    + "p.data_nascimento, p.nome, p.observacao, p.cliente_id, pe.nome as cliente, p.raca_id, r.nome as racanome"
                    + "FROM tb_pet p, tb_cliente c, tb_raca r, tb_pessoa pe "
                    + "WHERE c.cpf = p.cliente_id "
                    + "AND r.id =  p.raca_id "
                    + "AND p.cliente_id = pe.cpf "
                    + "AND p.id = ?");
            ps.setInt(1, Integer.parseInt(id.toString()));
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
            
                Pet p = new Pet();
                p.setId(rs.getInt("id"));
                
                Calendar dtnasc = Calendar.getInstance();
                dtnasc.setTimeInMillis(rs.getDate("data_nascimento").getTime()); 
                p.setData_nascimento(dtnasc);
                
                p.setNome(rs.getString("nome"));
                p.setObservacao(rs.getString("observacao"));
                
                Cliente cli = new Cliente();
                cli.setCpf(rs.getString("cpf"));
                cli.setNome(rs.getString("cliente"));
                
                Raca ra = new Raca();
                ra.setId(rs.getInt("raca_id"));
                ra.setNome(rs.getString("racanome"));
                ps.close();
                
                return p;         
            }        
        }
        
        return null;
    }
    
    @Override
    public void persist(Object o) throws Exception {
        if(o instanceof Consulta){
            
            Consulta c = (Consulta) o;
            
            if(c.getId() == null){ 
                
                //insert.
                PreparedStatement ps = this.con.prepareStatement("INSERT INTO tb_consulta "
                        + "(id, data, data_retorno, observacao, valor, medico_id, pet_id) "
                        + "VALUES (nextval('seq_consulta_id'), now(), now(), ?, ?, ?, ?)");         
                //ps.setTimestamp(1, new Timestamp(c.getData().getTimeInMillis()));
                ps.setString(1, c.getObservacao());
                ps.setFloat(2, c.getValor());
                ps.setString(3, c.getMedico().getCpf());
                ps.setInt(4, c.getPet().getId());
                ps.execute();
                            
                
            }else{
                
                //update.
                PreparedStatement ps = this.con.prepareStatement("UPDATE tb_consulta SET "
                        + "observacao = ?, valor = ?, medico_id = ?, pet_id = ? "
                        + "WHERE id = ?");
                ps.setString(1, c.getObservacao());
                ps.setFloat(2, c.getValor());
                ps.setString(3, c.getMedico().getCpf());
                ps.setInt(4, c.getPet().getId());
                ps.setInt(5, c.getId());
                ps.execute();
                
                ps.execute();//executa o comando.
            }
            
            
        }else if (o instanceof Pet){
                
            Pet p = (Pet) o;
            
            if(p.getId() == null){ 
                
                //insert.
                PreparedStatement ps = this.con.prepareStatement("INSERT INTO tb_pet "
                        + "(id, data_nascimento, nome, observacao, cliente_id, raca_id) "
                        + "VALUES (nextval('seq_pet_id'), now(), ?, ?, ?, ?)");
                         
                //ps.setTimestamp(1, new Timestamp(p.getData_nascimento().getTimeInMillis()));
                ps.setString(1, p.getNome());
                ps.setString(2, p.getObservacao());
                ps.setString(3, p.getCliente().getCpf());
                ps.setInt(4, p.getRaca().getId());
                ps.execute();
                            
                
            }else{
                
                //update.
                PreparedStatement ps = this.con.prepareStatement("UPDATE tb_pet SET "
                        + "nome = ?, observacao = ?, cliente_id = ?, raca_id = ? "
                        + "WHERE id = ?");
                ps.setString(1, p.getNome());
                ps.setString(2, p.getObservacao());
                ps.setString(3, p.getCliente().getCpf());
                ps.setInt(4, p.getRaca().getId());
                ps.setInt(5, p.getId());
                ps.execute();
                
                ps.execute();//executa o comando.
            }
                
        }
    }

    @Override
    public void remover(Object o) throws Exception {
        if(o instanceof Consulta){

            Consulta c = (Consulta) o;
            
            PreparedStatement ps = this.con.prepareStatement("DELETE FROM tb_consulta WHERE id = ?");
            ps.setInt(1, c.getId());            
            ps.execute();            
            
        }else if(o instanceof Pet){
            
            Pet p = (Pet) o;
            
            PreparedStatement ps = this.con.prepareStatement("DELETE FROM tb_pet WHERE id = ?");
            ps.setInt(1, p.getId());            
            ps.execute();
            
        }   
    }

    @Override
    public List<Consulta> listConsulta() throws Exception {
        List<Consulta> lista = null;
                        
        PreparedStatement ps = this.con.prepareStatement("SELECT c.id, "
                    + "c.data, c.data_retorno, c.observacao, c.valor, c.medico_id, pe.nome as nomedico, c.pet_id, p.nome "
                    + "FROM tb_consulta c, tb_pet p, tb_medico m, tb_pessoa pe "
                    + "WHERE p.id = c.pet_id "
                    + "AND m.cpf = c.medico_id "
                    + "AND m.cpf = pe.cpf "
                    + "ORDER BY id asc");
        
        ResultSet rs = ps.executeQuery(); 
        lista = new ArrayList<>();
        while(rs.next()){
            
            Consulta con = new Consulta();
            con.setId(rs.getInt("id"));

            Calendar dt = Calendar.getInstance();
            dt.setTimeInMillis(rs.getDate("data").getTime());
            con.setData(dt);

            Calendar dtret = Calendar.getInstance();
            dtret.setTimeInMillis(rs.getDate("data_retorno").getTime());

            con.setData_retorno(dtret);
            con.setObservacao(rs.getString("observacao"));
            con.setValor(rs.getFloat("valor"));

            Medico m = new Medico();
            m.setCpf(rs.getString("medico_id"));
            m.setNome(rs.getString("nomedico"));

            Pet p = new Pet();
            p.setId(rs.getInt("pet_id"));
            p.setNome(rs.getString("nome"));
            
            con.setMedico(m);
            con.setPet(p);
            lista.add(con);//adiciona na lista o objetivo que contem as informações de um determinada linha do ResultSet.
            
        }                
        return lista;
    }

    @Override
    public List<Pet> listPet() throws Exception {
        
        List<Pet> lista = null;
                        
        PreparedStatement ps = this.con.prepareStatement("SELECT p.id, "
                    + "p.data_nascimento, p.nome, p.observacao, p.cliente_id, pe.nome as cliente, p.raca_id, r.nome as racanome "
                    + "FROM tb_pet p, tb_cliente c, tb_raca r, tb_pessoa pe "
                    + "WHERE c.cpf = p.cliente_id "
                    + "AND r.id =  p.raca_id "
                    + "AND p.cliente_id = pe.cpf "
                    + "ORDER BY id asc");
        
        ResultSet rs = ps.executeQuery();//executa a query            
        lista = new ArrayList<>();
        while(rs.next()){
            
            Pet p = new Pet();
            p.setId(rs.getInt("id"));
                
            Calendar dtnasc = Calendar.getInstance();
            dtnasc.setTimeInMillis(rs.getDate("data_nascimento").getTime());
            p.setData_nascimento(dtnasc);

            p.setNome(rs.getString("nome"));
            p.setObservacao(rs.getString("observacao"));

            Cliente cli = new Cliente();
            cli.setCpf(rs.getString("cliente_id"));
            cli.setNome(rs.getString("cliente"));

            Raca ra = new Raca();
            ra.setId(rs.getInt("raca_id"));
            ra.setNome(rs.getString("racanome"));

            p.setCliente(cli);
            p.setRaca(ra);
            lista.add(p);//adiciona na lista o objetivo que contem as informações de um determinada linha do ResultSet.
            
        }                
        return lista;
    }


}