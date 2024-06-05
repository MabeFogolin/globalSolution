package br.com.fiap.model.menus;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.fiap.beans.Usuario;
import br.com.fiap.controller.GerenciadorUsuario;


public class MainUsuario {

	private GerenciadorUsuario gerenciadorUsuario;

	public MainUsuario() throws ClassNotFoundException, SQLException {
		this.gerenciadorUsuario = new GerenciadorUsuario();
	}

	public void showMenu() throws SQLException {
		String[] options = { "Adicionar Usuario", "Deletar Usuario", "Atualizar Usuario", "Listar Usuarios", "Sair" };
		int selectedOptionIndex;
		do {
			selectedOptionIndex = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Menu de Usuário",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

			switch (selectedOptionIndex) {
			case 0: {

				String userNome = JOptionPane.showInputDialog(null, "Informe o nome do usuário:");
				int userIdade = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a idade do usuário:"));
				String userEmail = JOptionPane.showInputDialog(null, "Informe o email do usuário:");
				String userSenha = JOptionPane.showInputDialog(null, "Informe a senha do usuário:");
				long userCep = Long.parseLong(JOptionPane.showInputDialog(null, "Informe o CEP do usuário:"));
				int fkOceanis = 1;

				if (userIdade >= 14) {
					Usuario user = new Usuario(userNome, userIdade, userEmail, userSenha, userCep, fkOceanis);
					if (gerenciadorUsuario.adicionarUsuario(user)) {
						JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso!", null,
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao adicionar o menu", "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
					break;
				} else {
					JOptionPane.showMessageDialog(null,
							"Erro ao adicionar o Usuário, o site não permite criação de usuários menores de 14 anos",
							"Erro", JOptionPane.ERROR_MESSAGE);
				}
				break;

			}

			case 1: {
				String userEmail = JOptionPane.showInputDialog(null, "Informe o email do usuário para deletar:");
				if (gerenciadorUsuario.deletarUsuario(userEmail)) {
					JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso!", null,
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao deletar usuário", "Erro", JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
			case 2: {
				String userEmail = JOptionPane.showInputDialog(null, "Informe o email do usuário para atualizar:");
				String userNome = JOptionPane.showInputDialog(null, "Informe o novo nome do usuário: ");
				int idade = Integer
						.parseInt(JOptionPane.showInputDialog(null, "Informe a nova idade a ser atualizada:"));
				long userCep = Long.parseLong(JOptionPane.showInputDialog(null, "Informe o novo CEP do usuário:"));

				Usuario user = new Usuario(userNome, idade, userEmail, userCep);
				if (gerenciadorUsuario.atualizarUsuario(user)) {
					JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!", null,
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao atualizar usuario", "Erro", JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
			case 3: {
				List<Usuario> usuarios = gerenciadorUsuario.listarUsuarios();
				StringBuilder usuariosText = new StringBuilder();
				for (Usuario usuario : usuarios) {
					usuariosText.append(usuario.toString()).append("\n");
				}
				JOptionPane.showMessageDialog(null, usuariosText.toString(), null, JOptionPane.INFORMATION_MESSAGE);
				break;
			}
			case 4: {
				int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (confirmacao == JOptionPane.YES_OPTION) {
					gerenciadorUsuario.fecharConexao();
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
			MainUsuario main = new MainUsuario();
			main.showMenu();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
