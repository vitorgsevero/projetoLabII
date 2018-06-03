package model;


import java.time.LocalDate;
import java.util.Objects;

public class Clientes {

    private String cpfCliente;
    private String nomeCliente;
    private String emailCliente;
    private LocalDate dataNascimento;
    private String numConta;
    private double saldoConta = 0;
    private double valorTransferencia = 0;
    private int idCliente=0;


    public Clientes(String nomeCliente, String cpfCliente, String emailCliente, String numConta,  LocalDate dataNascimento) { //
        this.nomeCliente = nomeCliente;
        this.cpfCliente = cpfCliente;
        this.emailCliente = emailCliente;
        this.numConta = numConta;
        //this.saldoConta = saldoConta;
        this.dataNascimento = dataNascimento;
       // this.valorTransferencia = valorTransferencia;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

     @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Clientes other = (Clientes) obj;
        if (!Objects.equals(this.cpfCliente, other.cpfCliente)) {
            return false;
        }
        return true;
    }

    public String getNumConta() {
        return numConta;
    }

    public double getSaldoConta() {
        return saldoConta;
    }
    
    /**
     Criação do setSaldoConta para alterar o valor do saldo quando um depósito ou uma transferência é feita
     */
    public void setSaldoConta(double saldoConta) {
        this.saldoConta = saldoConta;
    }

    public double getValorTransferencia() {
        return valorTransferencia;
    }

    public void setValorTransferencia(double valorTransferencia) {
        this.valorTransferencia = valorTransferencia;
    }

    public LocalDate getDataNascimento() {
       return dataNascimento;
    }

    public void setId(int id) {
        this.idCliente = id;
    }

}
