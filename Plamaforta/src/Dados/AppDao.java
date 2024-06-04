/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dados;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 * @author enzod
 */
public class AppDao {
    Connection conectado;
    ResultSet resultado;
    PreparedStatement st;
    ResultSet usuario;

    
    private void conectar()throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        conectado = DriverManager.getConnection("jdbc:mysql://localhost:3306/plamaforta", "root", "p@$$w0rd");
    }
    
    public ResultSet entrar(String cargo, String senha, String id, String cnpj ) throws SQLException, ClassNotFoundException{
        conectar();
        if(cargo.equals("Cliente")){
            st = conectado.prepareStatement("SELECT * FROM cliente where cnpj = ? and senha = ?");
            st.setString(1, cnpj);
            st.setString(2, senha);
            resultado = st.executeQuery();//Executa o SELECT (busca), o comando select selciona o usário desejado no banco de dados
            return resultado;
        }
        else if(cargo.equals("Funcionário")){
            st = conectado.prepareStatement("SELECT * FROM funcionario where id_funcionario = ? and senha = ?");
            st.setString(1, id);
            st.setString(2, senha);
            resultado = st.executeQuery();
            return resultado;
        }
        else{
            st = conectado.prepareStatement("SELECT * FROM Adm where id_funcionario = ? and senha = ?");
            st.setString(1, id);
            st.setString(2, senha);
            resultado = st.executeQuery();
            return resultado;
        }
    }

    public void cadastrarUsuario(String cnpj, String email, String senha, String nome) throws ClassNotFoundException, SQLException {
        conectar();
        st = conectado.prepareStatement("INSERT INTO cliente values(?, ?, ?, ?, ?, ?)");
        st.setString(1, cnpj); 
        st.setString(2, nome);
        st.setString(3, senha);
        st.setString(4, email);
        st.setString(5, " ");
        st.setInt(6, 0);
        st.executeUpdate();
    }

    public void cadastrarFuncionario(String nome, String senha) throws ClassNotFoundException, SQLException {
        conectar();
        st = conectado.prepareStatement("INSERT INTO funcionario(nome, senha) values(?,?)");
        st.setString(1, nome);
        st.setString(2, senha);
        st.executeUpdate();    
    }
    
    public void alterarInfoCliente(String cnpj, String nome, String email, String senha) throws ClassNotFoundException, SQLException{
        conectar();
        st = conectado.prepareStatement("UPDATE cliente SET senha = ?, nome = ?, email = ?  WHERE cnpj = ?");
        st.setString(1, senha);
        st.setString(2, nome);
        st.setString(3, email);
        st.setString(4, cnpj);
        st.executeUpdate();
    }
    
    public void excluirCliente(String cnpj)throws ClassNotFoundException, SQLException{
        conectar();
        st = conectado.prepareStatement("DELETE from Cliente WHERE cnpj = ?");
        st.setString(1, cnpj);
        st.executeUpdate();
    }
   
    public ResultSet carregarDadosCliente(String u)throws ClassNotFoundException, SQLException {
        conectar();
        if(u.equals("")){
            st = conectado.prepareStatement("SELECT cnpj,nome,email FROM Cliente");
            return resultado = st.executeQuery();
        } 
        st = conectado.prepareStatement("SELECT cnpj,nome,email FROM Cliente WHERE cnpj like ?");
        st.setString(1, "%" + u + "%");

        return resultado = st.executeQuery();
    }
    
    public ResultSet carregarDadosFuncionario(String u)throws ClassNotFoundException, SQLException {
        conectar();
        if(u.equals("")){
            st = conectado.prepareStatement("SELECT id_funcionario,nome FROM funcionario");
            return resultado = st.executeQuery();
        } 
        st = conectado.prepareStatement("SELECT id_funcionario,nome FROM funcionario where id_funcionario = ?");
        st.setString(1, u);

        return resultado = st.executeQuery();
    }

    public void cadastrarMensagem(String cnpj, String mensagem) throws ClassNotFoundException, SQLException {
        conectar();
        st = conectado.prepareStatement("update consultoria set mensagem = ? where fk_cnpj = ?");
        st.setString(1, mensagem);
        st.setString(2, cnpj);
        st.executeUpdate();  
    }

    public void excluirFuncionario(int id) throws ClassNotFoundException, SQLException {
        conectar();
        PreparedStatement st = conectado.prepareStatement("DELETE FROM funcionario WHERE id_funcionario = ?");
        st.setInt(1, id);
        st.executeUpdate();
    }

    public void criarPublicacao(String cnpj, String mensagem, int visualizacoes) throws ClassNotFoundException, SQLException {
        conectar();
        st = conectado.prepareStatement("update Cliente set mensagem_publi = ?, visualisacoes = ? WHERE cnpj = ?");
        st.setString(1, mensagem);
        st.setInt(2, visualizacoes);
        st.setString(3, cnpj);
        st.executeUpdate();
    }

    public void ClienteDeletaConta(String cnpj) throws ClassNotFoundException, SQLException {
        conectar();
        PreparedStatement st = conectado.prepareStatement("DELETE FROM cliente WHERE cnpj = ?");
        st.setString(1, cnpj);
        st.executeUpdate();
    }

    public void AlterarFuncionario(String nome, String senha, int id_certo) throws ClassNotFoundException, SQLException {
        conectar();
        st = conectado.prepareStatement("UPDATE funcionario SET senha = ?, nome = ? WHERE id_funcionario = ?");
        st.setString(1, senha);
        st.setString(2, nome);
        st.setInt(3, id_certo);
        st.executeUpdate();
    }

    public ResultSet carregarDadosConsultoria(String u) throws ClassNotFoundException, SQLException {
        conectar();
        if(u.equals("")){
            st = conectado.prepareStatement("SELECT * FROM consultoria");
            return resultado = st.executeQuery();
        } 
        st = conectado.prepareStatement("SELECT * FROM consultoria where fk_cnpj = ?");
        st.setString(1, u);
        return resultado = st.executeQuery();
    }

    public ResultSet carregarDadosStatus(String u) throws ClassNotFoundException, SQLException {
        conectar();
        if(u.equals("")){
            st = conectado.prepareStatement("SELECT cnpj, nome, visualisacoes, mensagem_publi FROM cliente");
            return resultado = st.executeQuery();
        } 
        st = conectado.prepareStatement("SELECT cnpj, nome, visualisacoes, mensagem_publi FROM cliente where cnpj = ?");
        st.setString(1, u);
        return resultado = st.executeQuery();
    }
    

}
