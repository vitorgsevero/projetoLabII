package model;


import java.util.ArrayList;
import util.Console;
import view.ClienteUI;
import view.ProdutoUI;
import view.OperacaoMonetizacaoUI;

public class Menu {

    public void abrirMenuPrincipal() {

        ClienteUI cui = new ClienteUI();
        ProdutoUI pui = new ProdutoUI();
        OperacaoMonetizacaoUI omUI =  new OperacaoMonetizacaoUI(); 

        int op = 0;

        do {

          /*try {*/
                op = Console.scanInt("\nBem-vindo ao Menu Principal! Informe uma opção: \n1) Clientes \n2) Produtos \n3) Operações de Monetização \n0) Sair do programa ");

                switch (op) {

                    case 1:
                        cui.menuCliente();
                        break;

                    case 2:
                        pui.menuProdutos();
                        break;

                    case 3:
                        omUI.menuOperacoes();
                        break;

                    case 0:
                        System.out.println("Saindo do programa...");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }

           /* } catch (Exception e) {
                System.out.println("Não foi possível acessar as opções do Menu! Por favor, informe uma opção válida.");
                System.out.println("Erro: " + e.getMessage());
            }*/

        } while (0 != op);

    }
}
