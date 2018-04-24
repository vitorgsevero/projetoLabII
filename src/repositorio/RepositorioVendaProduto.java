package repositorio;

import java.util.ArrayList;
import java.util.List;
import model.VendaProduto;


public class RepositorioVendaProduto {

    private List<VendaProduto> vendaProduto;
    private static RepositorioVendaProduto instance = null;

    private RepositorioVendaProduto () {
        vendaProduto = new ArrayList<VendaProduto>();
    }

    public static RepositorioVendaProduto getInstance() {
        if (instance == null) {
            instance = new RepositorioVendaProduto();
        }
        return instance;
    }

    public boolean add(VendaProduto venda) {
        return (vendaProduto.add(venda));
    }

    public boolean estaVazio() {
        return vendaProduto.isEmpty();
    }

    public List<VendaProduto> getVendaProduto() {
        return vendaProduto;
    }

    public void buscarVendaProduto(String codVenda) { 

        for (VendaProduto vendaProduto : RepositorioVendaProduto.getInstance().getVendaProduto()) {
            if (vendaProduto.getCodVenda().equalsIgnoreCase(codVenda)) {

                System.out.println("\n------------------------");
                System.out.println("Código da Venda: " + vendaProduto.getCodVenda());
                System.out.println("Quantidade de produtos: " + vendaProduto.getQtdeProdutos());
                System.out.println("Total da venda: " + vendaProduto.getTotalVenda());
                System.out.println("------------------------");
            } else {
                System.out.println("Nenhum cliente encontrado...");
            }

        }

    }


}
