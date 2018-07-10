package controller;

import java.awt.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Clientes;
import model.Principal;
import negocio.ClienteNegocio;
import negocio.NegocioException;
import util.DateUtil;
import util.PrintUtil;


/**
 * FXML Controller class
 *
 * @author User
 */
public class ClienteController implements Initializable {

    //TELA INICIAL COM A TABELA
    @FXML
    private VBox painelTabelaCliente;
    @FXML
    private TableView<Clientes> tableViewClientes;
    @FXML
    private TableColumn<Clientes, String> tableColumnCpf;
    @FXML
    private TableColumn<Clientes, String> tableColumnNome;
    @FXML
    //Formato de data dd/MM/yyyy
    private TableColumn<Clientes, String> tableColumnDataNascimento;

    //TELA DE CADASTRO
    @FXML
    private AnchorPane painelFormularioCliente;
    @FXML
    private TextField textFieldNomeCliente;
    @FXML
    private TextField textFieldCpfCliente;
    @FXML
    private TextField textFieldEmailCliente;
    @FXML
    private TextField textFieldSaldoContaCliente;
    @FXML
    private DatePicker datePickerDataNascimentoCliente;
    @FXML
    private TextField textFieldNumeroContaCliente;

    private int tela;
    private List<Clientes> listaClientes;
    private Clientes clienteSelecionado;

    private ObservableList<Clientes> observableListaClientes;
    private ClienteNegocio clienteNegocio;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteNegocio = new ClienteNegocio();

        if (tableViewClientes != null) {
            carregarTableViewClientes();
        }
    }

    private void carregarTableViewClientes() {
        tableColumnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        //Formato de data dd/MM/yyyy - precisa de um callback para converter o dado.
        tableColumnDataNascimento.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Clientes, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Clientes, String> cell) {
                final Clientes cliente = cell.getValue();
                final SimpleObjectProperty<String> simpleObject = new SimpleObjectProperty(
                        DateUtil.dateToString(cliente.getDataNascimento())
                );
                return simpleObject;
            }

        });

        listaClientes = clienteNegocio.listar();

        observableListaClientes = FXCollections.observableArrayList(listaClientes);
        tableViewClientes.setItems(observableListaClientes);
    }

    @FXML
    public void tratarBotaoCadastrar(ActionEvent event) throws IOException {
        clienteSelecionado = null;
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Principal.class.getResource("PainelFormularioCliente.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(painelTabelaCliente.getScene().getWindow());
        stage.showAndWait();
        carregarTableViewClientes();
    }

    @FXML
    public void tratarBotaoEditar(ActionEvent event) throws IOException {
        Clientes clienteSelec = tableViewClientes.getSelectionModel().getSelectedItem();
        if (clienteSelec != null) {
            FXMLLoader loader = new FXMLLoader(Principal.class.getResource("PainelFormularioCliente.fxml"));
            Parent root = (Parent) loader.load();

            ClienteController controller = (ClienteController) loader.getController();
            controller.setClienteSelecionado(clienteSelec);

            Stage dialogStage = new Stage();
            dialogStage.setScene(new Scene(root));
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(painelTabelaCliente.getScene().getWindow());
            dialogStage.showAndWait();
            carregarTableViewClientes();
        } else {
            PrintUtil.printMessageError("Precisa selecionar um cliente para esta opção");
        }
    }

    @FXML
    public void tratarBotaoRemover(ActionEvent event) throws IOException {
        Clientes clienteSelec = tableViewClientes.getSelectionModel().getSelectedItem();
        if (clienteSelec != null) {
            try {
                clienteNegocio.deletar(clienteSelec);
                this.carregarTableViewClientes();
            } catch (NegocioException ex) {
                PrintUtil.printMessageError(ex.getMessage());
            }
        } else {
            PrintUtil.printMessageError("Precisa selecionar um cliente para esta opcao");
        }
    }

    @FXML
    public void tratarBotaoSalvar(ActionEvent event) throws IOException {
        Stage stage = (Stage) painelFormularioCliente.getScene().getWindow();

        if (clienteSelecionado == null) //Se for cadastrar
        {
            try {
                clienteNegocio.salvar(new Clientes(
                        textFieldNomeCliente.getText(),
                        textFieldCpfCliente.getText(),
                        textFieldEmailCliente.getText(),
                        textFieldSaldoContaCliente.getText(),
                        datePickerDataNascimentoCliente.getValue(),
                        textFieldNumeroContaCliente.getText()
                ));
                stage.close();
            } catch (NegocioException ex) {
                PrintUtil.printMessageError(ex.getMessage());
            }

        } else //Se for editar
        {
            try {
                clienteSelecionado.setNomeCliente(textFieldNomeCliente.getText());
                clienteSelecionado.setDataNascimento(
                        datePickerDataNascimentoCliente.getValue()
                );
                clienteNegocio.atualizar(clienteSelecionado);
                stage.close();
            } catch (NegocioException ex) {
                PrintUtil.printMessageError(ex.getMessage());
            }

        }
    }

    @FXML
    public void tratarBotaoCancelar(ActionEvent event) throws IOException {
        Stage stage = (Stage) painelFormularioCliente.getScene().getWindow();
        stage.close();

    }

    public Clientes getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setClienteSelecionado(Clientes clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
        textFieldCpfCliente.setText(clienteSelecionado.getCpfCliente());
            textFieldCpfCliente.setEditable(false);
        textFieldNomeCliente.setText(clienteSelecionado.getNomeCliente());
        datePickerDataNascimentoCliente.setValue(clienteSelecionado.getDataNascimento());
    }
}
