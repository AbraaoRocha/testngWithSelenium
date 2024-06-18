package testng.pages;

public class tests {

	public static void main(String[] args) {
        String original = "$1,199.00";
        char letraParaRemover = '$';
        String virgula = ",";
        
        // Converte o caractere para uma string
        String strParaRemover = Character.toString(letraParaRemover);

        // Usa o método replace para substituir a letra pela string vazia
        String resultado = original.replace(strParaRemover, "");

        System.out.println("Original: " + original);
        System.out.println("Resultado: " + resultado);
        
        
        String newValue = resultado.replace(virgula, "");
        double valor = Double.parseDouble(newValue);
        
        System.out.println(valor);
        System.out.println(valor *20);
    }
	
	
	public static String getValor(String value) {
		
		String newValue = value.replace(",", "");
		return newValue;
	}
	
}
