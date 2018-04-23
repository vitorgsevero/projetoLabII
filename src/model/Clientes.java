package model;


import java.util.ArrayList;
import java.util.Objects;

public class Clientes {

    private String cpfCliente;
    private String nomeCliente;
    private String emailCliente;
    private String numConta;
    private double saldoConta = 0;


    public Clientes(String nomeCliente, String cpfCliente, String emailCliente, String numConta, double saldoConta) {
        this.nomeCliente = nomeCliente;
        this.cpfCliente = cpfCliente;
        this.emailCliente = emailCliente;
        this.numConta = numConta;
        this.saldoConta = saldoConta;
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

}
