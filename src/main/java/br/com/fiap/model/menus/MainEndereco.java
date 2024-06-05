package br.com.fiap.model.menus;

import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import br.com.fiap.beans.Endereco;
import br.com.fiap.controller.GerenciadorEndereco;


public class MainEndereco {

	private GerenciadorEndereco gerenciadorEndereco;

	public MainEndereco() throws SQLException, ClassNotFoundException {
		this.gerenciadorEndereco = new GerenciadorEndereco();
	}

	
	public void showMenu() throws SQLException {
		String[] options = { "Adicionar Endereço", "Deletar Endereço", "Atualizar Endereço", "Listar Endereços",
				"Sair" };
		int selectedOptionIndex;
		do {
			UIManager.put("OptionPane.minimumSize", new Dimension(500, 50));
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("SanSerif", Font.ROMAN_BASELINE, 18)));
			selectedOptionIndex = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Menu de Endereços",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

			switch (selectedOptionIndex) {
			case 0: {
				String fkUsuario = JOptionPane.showInputDialog(null, "Informe o e-mail do usuário:");
				String cidade = JOptionPane.showInputDialog(null, "Informe a cidade:");
				String rua = JOptionPane.showInputDialog(null, "Informe a rua:");
				String uf = JOptionPane.showInputDialog(null, "Informe o estado (UF):");
				Endereco endereco = new Endereco(fkUsuario, cidade, rua, uf);
				if (gerenciadorEndereco.adicionarEndereco(endereco)) {
					JOptionPane.showMessageDialog(null, "Endereço adicionado com sucesso!", null,
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao adicionar endereço", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
			case 1: {
				// Deletar Endereço
				String email = JOptionPane.showInputDialog(null,
						"Informe o e-mail do usuário para deletar o endereço:");
				if (gerenciadorEndereco.deletarEndereco(email)) {
					JOptionPane.showMessageDialog(null, "Endereço deletado com sucesso!", null,
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao deletar endereço", "Erro", JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
			case 2: {
				// Atualizar Endereço
				String fkUsuario = JOptionPane.showInputDialog(null, "Informe o e-mail do usuário:");
				String cidade = JOptionPane.showInputDialog(null, "Informe a nova cidade:");
				String rua = JOptionPane.showInputDialog(null, "Informe a nova rua:");
				String uf = JOptionPane.showInputDialog(null, "Informe o novo estado (UF):");
				Endereco endereco = new Endereco(fkUsuario, cidade, rua, uf);
				if (gerenciadorEndereco.atualizarEndereco(endereco)) {
					JOptionPane.showMessageDialog(null, "Endereço atualizado com sucesso!", null,
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao atualizar endereço", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
			case 3: {
				// Listar Endereços
				List<Endereco> enderecos = gerenciadorEndereco.listaEndereco();
				StringBuilder enderecoText = new StringBuilder();
				for (Endereco endereco : enderecos) {
					enderecoText.append(endereco.toString()).append("\n");
				}
				JOptionPane.showMessageDialog(null, enderecoText.toString(), null, JOptionPane.INFORMATION_MESSAGE);
				break;
			}
			case 4: {
				// Sair
				int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (confirmacao == JOptionPane.YES_OPTION) {
					gerenciadorEndereco.fecharConexao();
					System.exit(0);
				}
				break;
			}
			default:
				break;
			}
		} while (selectedOptionIndex != 4);
	}
	public static void main(String[] args) throws ClassNotFoundException {
		try {
			MainEndereco main = new MainEndereco();
			main.showMenu();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
}