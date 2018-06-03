package dao.BD;

import java.util.List;
import model.Clientes;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Clientes;
import dao.ClienteDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;


public class ClienteDaoBD implements ClienteDAO{
    
    private Connection conexao;
    private PreparedStatement comando;
    
     public Connection conectar(String sql) throws SQLException {
        conexao = ConnectionFactory.getConnection();
        comando = conexao.prepareStatement(sql);
        return conexao;
    }
     
    public void conectarObtendoId(String sql) throws SQLException {
        conexao = ConnectionFactory.getConnection();
        comando = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    } 
    
    public void encerrarConexao() {
        try {
            if (comando!=null) {
                comando.close();
            }
            if (conexao!=null) {
                conexao.close();
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao encerrar a conexao");
            throw new BDException(ex);

        }

    }  
    
    @Override
    public void cadastrarClientes(Clientes cliente) {
               int id = 0;
        try {
            String sql = "INSERT INTO cliente (cpf, nome, datanascimento) "
                    + "VALUES (?,?,?)";

            //Foi criado um novo método conectar para obter o id
            conectarObtendoId(sql);
            comando.setString(1, cliente.getCpfCliente());
            comando.setString(2, cliente.getNomeCliente());
            //Trabalhando com data: convertendo LocalDate -> Date
            Date dataSql = Date.valueOf(cliente.getDataNascimento());
            comando.setDate(3, dataSql);
            comando.executeUpdate();
            //Obtém o resultSet para pegar o id
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                //seta o id para o objeto
                id = resultado.getInt(1);
                cliente.setId(id);
            }
            else{
                System.err.println("Erro: Não foi possível gerar o ID!");
                throw new BDException("O ID não foi gerado!");
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar paciente no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            encerrarConexao();
        }

    }

    @Override
    public void removerClientes(Clientes cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizarDados(Clientes cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Clientes> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Clientes procurarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Clientes procurarPorRg(String rg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Clientes> listarPorNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
