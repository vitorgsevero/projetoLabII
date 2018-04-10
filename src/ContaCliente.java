
public class ContaCliente {

    private int numConta = 0;
    private double saldoConta = 0;

    ContaCliente(int numConta, double saldoConta) {
        this.numConta = numConta;
        this.saldoConta = saldoConta;
    }

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public double getSaldoConta() {
        return saldoConta;
    }

    public void setSaldoConta(double saldoConta) {
        this.saldoConta = saldoConta;
    }

}
