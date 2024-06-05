package br.com.fiap.main;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.apache.http.client.ClientProtocolException;

import br.com.fiap.beans.Endereco;
import br.com.fiap.beans.EnderecoViaCep;
import br.com.fiap.beans.Usuario;
import br.com.fiap.controller.GerenciadorEndereco;
import br.com.fiap.controller.GerenciadorUsuario;
import br.com.fiap.services.ViacepService;

public class TesteViacepApi {
	public static void main(String[] args) throws ClientProtocolException, IOException, ClassNotFoundException, SQLException {
		
		Usuario usuario = new Usuario("João", 30, "joao&maria@example.com", "senha123", "04424030", 1);
        
        ViacepService viaCep = new ViacepService();
        
        System.out.println(usuario.getUserCep());
        
        EnderecoViaCep endereco = viaCep.getEnderecoViaCep(usuario.getUserCep());
		
		JOptionPane.showMessageDialog(null, endereco, null, JOptionPane.INFORMATION_MESSAGE);
		
		GerenciadorEndereco ge = new GerenciadorEndereco();
		GerenciadorUsuario gu = new GerenciadorUsuario();
		
		gu.adicionarUsuario(usuario);
		
		Endereco enderecoBanco = new Endereco(usuario.getUserEmail(), endereco.getLocalidade(), endereco.getLogradouro(), endereco.getUf());
		
		ge.adicionarEndereco(enderecoBanco);
		
		ge.fecharConexao();
		gu.fecharConexao();
		
		

	}
}
