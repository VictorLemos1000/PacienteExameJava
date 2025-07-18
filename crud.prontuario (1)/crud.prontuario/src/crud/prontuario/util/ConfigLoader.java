package crud.prontuario.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {

	private final static String ARQUIVO = "config.properties";
	private static Properties PROP = new Properties(); 
	
	static {
		load(ARQUIVO); // Carregamento automático ao inicializar o arquivo
	}
	
	public static void load(String arquivo) {
		try {
			// Try-with-resources é um gerenciamento automático para recursos stream.
			FileInputStream fis = new FileInputStream(arquivo);
			PROP = new Properties();
			PROP.load(fis);
		} catch (IOException e) {
			// TODO: handle exception
			System.err.println("\n Esso ao ler o Arquivo de configuração.");
			e.printStackTrace();
		}
	}
	
	// método para retornar o usuário do BD.
	public static String getValor(String chave) {
		return PROP.getProperty(chave);
	}
	
	// método para retornar a porta e o valor dela no BD.
//	public static String getValor(String chave, String valorPadrão) {
//		return PROP.getProperty(chave, valorPadrão);
//	}
	
	/*
	 * O método funciona da seguinte forma:
	 * 	retorna true se o houver alguma propriedade em PROP;
	 * 	retorna false se não houver propriedades em PROP.
	 * 
	 * OBS: se não hovesse o operador de negação os
	 * valores booleandos estariam invertidos.
	 */
//	public static boolean isConfigLoaded() {
//		if (ConfigLoader.isConfigLoaded()) {
//			System.out.println(" Configurações prontas para uso.");
//		} else {
//			System.err.println(" Há problemas nas configurações");
//		}
//		return !PROP.isEmpty();
//	}	
}