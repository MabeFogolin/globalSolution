package br.com.fiap.main;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.http.client.ClientProtocolException;

import br.com.fiap.beans.EnderecoViaCep;
import br.com.fiap.beans.Usuario;
import br.com.fiap.services.ViacepService;

public class TesteViacepApi {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		Usuario usuario = new Usuario();
        usuario.setUserNome("João");
        usuario.setUserIdade(30);
        usuario.setUserEmail("joao@example.com");
        usuario.setUserSenha("senha123");
        usuario.setUserCep(04424030);
        usuario.setFkOceanis(1);
		
        System.out.println(String.valueOf(usuario.getUserCep()));
        
	ViacepService viaCep = new ViacepService();
		
		EnderecoViaCep endereco = viaCep.getEnderecoViaCep((String.valueOf(usuario.getUserCep())));
		
		JOptionPane.showMessageDialog(null, endereco, null, JOptionPane.ERROR_MESSAGE);
		
		System.out.println(endereco);
		
		System.err.println("TESTE");
	}
}
