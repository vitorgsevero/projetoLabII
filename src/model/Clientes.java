package model;


import java.util.ArrayList;
import java.util.Objects;

public class Clientes {

    private String cpfCliente;
    private String nomeCliente;
    private String emailCliente;


    public Clientes(String nomeCliente, String cpfCliente, String emailCliente) {
        this.nomeCliente = nomeCliente;
        this.cpfCliente = cpfCliente;
        this.emailCliente = emailCliente;
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

}
