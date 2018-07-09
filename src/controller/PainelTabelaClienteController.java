package controller;

import java.awt.TextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import model.Clientes;
import view.ClienteUI;

/**
 * FXML Controller class
 *
 * @author User
 */
public class PainelTabelaClienteController implements Initializable {

    
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
    private TextField TextFieldNumeroContaCliente;
    
    private int tela;
    private List<Clientes> listaPacientes;
    
    private ClienteUI cliente;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cliente = new ClienteUI();
    }    
    
}
