package model;


import java.time.LocalDateTime;
import util.DateTimeUtil;

public class OperacaoMonetizacao {

    private String statusCliente;
    private LocalDateTime dataHoraOperacao;

    public void realizarDeposito() {

        try {
            
        } catch (Exception e) {
        }
        
    }

    public void realizarTransferencia() {

    }

    public void visualizarSaldo() {

        /*System.out.println("Número da conta: " + cc.getNumConta());
         System.out.println("Saldo: " + cc.getSaldoConta());*/
    }

    public void setStatusCliente(String statusCliente) {
        this.statusCliente = statusCliente;
    }

    public String getStatusCliente() {
        return statusCliente;
    }

}