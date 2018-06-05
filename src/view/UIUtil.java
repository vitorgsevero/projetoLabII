package view;

import util.Console;

public class UIUtil {

    public static final String SIM = "SIM";
    public static final String NAO = "NAO";
    
    public static void mostrarErro(Object msgErro) {
        System.err.println(msgErro);
    }
    
    public static boolean getConfirmacao(Object msg) {
        String confirmacao = "NAO";
        do {
            confirmacao = Console.scanString(msg+"(SIM/NAO)");
            if (confirmacao.equalsIgnoreCase(SIM)) {
                return true;
            }
            else if(confirmacao.equalsIgnoreCase(NAO)){
                return false;
            }
            else{
                System.out.println("A opção informada é inválida!");
            }                
        }while(confirmacao.equalsIgnoreCase(SIM) || 
                confirmacao.equalsIgnoreCase(NAO));
        return false;
    } 
    
}
