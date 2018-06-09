package dao.BD;

import dao.ProdutoDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Produtos;

public class ProdutoDaoBD implements ProdutoDAO {

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
            if (comando != null) {
                comando.close();
            }
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao encerrar a conexao");
            throw new BDException(ex);

        }
    }

    @Override
    public void cadastrarProdutos(Produtos produto) {
        
    int idProduto = 1;
        
     try {
            String sql = "INSERT INTO produto (cod_produto, nome_produto, preco_produto) "
                    + "VALUES (?,?,?)";

            //método conectar para obter o id
            conectarObtendoId(sql);

            comando.setString(1, produto.getCodProduto());
            comando.setString(2, produto.getNomeProduto());
            comando.setDouble(3, produto.getPrecoProduto());

            comando.executeUpdate();

            //Obtém o resultSet para pegar o id
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                //seta o id para o objeto
                idProduto = resultado.getInt(1);
                produto.setIdProduto(idProduto);
            } else {
                System.err.println("Erro: Não foi possível gerar o ID!");
                throw new BDException("O ID não foi gerado!");
            }

        } catch (SQLException ex) {
            System.err.println("Erro: Problema ao salvar paciente no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            encerrarConexao();
        }

        
        
        
        
    }

    @Override
    public void removerProdutos(Produtos pruduto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizarDados(Produtos pruduto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Produtos> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Produtos procurarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Produtos procurarPorCpf(String codProduto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Produtos> listarPorNome(String nomeProduto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Produtos> listarPorMaiorPreco(double precoProduto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
