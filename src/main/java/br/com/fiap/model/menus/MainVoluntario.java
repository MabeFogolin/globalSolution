package br.com.fiap.model.menus;

import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import br.com.fiap.beans.Voluntario;
import br.com.fiap.controller.GerenciadorVoluntario;


public class MainVoluntario {

	private GerenciadorVoluntario gerenciadorVoluntario;

	public MainVoluntario() throws ClassNotFoundException, SQLException {
		this.gerenciadorVoluntario = new GerenciadorVoluntario();
	}

	public void showMenu() throws SQLException {
		String[] options = { "Adicionar Voluntário", "Deletar Voluntário", "Atualizar Voluntário", "Listar Voluntários",
				"Sair" };
		int selectedOptionIndex;
		do {
			selectedOptionIndex = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Menu de Voluntário",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

			switch (selectedOptionIndex) {
			case 0: {

				String volCpf = JOptionPane.showInputDialog(null, "Informe o CPF do voluntário:");
				String volId = JOptionPane.showInputDialog(null, "Informe o ID do voluntário:");
				String volNome = JOptionPane.showInputDialog(null, "Informe o nome do voluntário:");
				String volSobrenome = JOptionPane.showInputDialog(null, "Informe o sobrenome do voluntário:");
				long volTel = Long.parseLong(JOptionPane.showInputDialog(null, "Informe o telefone do voluntário:"));
				String fkOng = JOptionPane.showInputDialog(null,
						"Informe o CNPJ da ONG em que o voluntário está cadastrado:");
				String cpfValidado = gerenciadorVoluntario.cadastrarCpf(volCpf);

				if (cpfValidado != null) {
					Voluntario voluntario = new Voluntario(volId, volCpf, volNome, volSobrenome, volTel, null, fkOng);
					if (gerenciadorVoluntario.adicionarVoluntario(voluntario)) {
						JOptionPane.showMessageDialog(null, "Voluntário adicionado com sucesso!", null,
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao adicionar o voluntário", "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
					break;
				} else {
					JOptionPane.showMessageDialog(null,
							"Erro ao adicionar o voluntário, o CPF não possui a quantidade de dígitos correta", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
				break;

			}

			case 1: {
				String volCpf = JOptionPane.showInputDialog(null, "Informe o email do usuário para deletar:");
				int tamanhoCpf = volCpf.length();
				if (tamanhoCpf == 14) {
					if (gerenciadorVoluntario.deletarVoluntario(volCpf)) {
						JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso!", null,
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao deletar usuário", "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
					break;
				} else {
					JOptionPane.showMessageDialog(null,
							"Erro ao excluir o voluntário, o CPF não possui a quantidade de dígitos correta", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
				break;

			}
			case 2: {
				String volCpf = JOptionPane.showInputDialog(null, "Informe o CPF do voluntário para atualizar:");
				String volNome = JOptionPane.showInputDialog(null, "Informe o novo nome do voluntário:");
				String volSobrenome = JOptionPane.showInputDialog(null, "Informe o novo sobrenome do voluntário:");
				long volTel = Long
						.parseLong(JOptionPane.showInputDialog(null, "Informe o novo telefone do voluntário:"));
				String fkOng = JOptionPane.showInputDialog(null,
						"Informe o CNPJ da nova ONG em que o voluntário está cadastrado:");
				int tamanhoCpf = volCpf.length();

				if (tamanhoCpf == 14) {
					Voluntario voluntario = new Voluntario(volCpf, volNome, volSobrenome, volTel, fkOng);
					if (gerenciadorVoluntario.atualizarVoluntario(voluntario)) {
						JOptionPane.showMessageDialog(null, "Voluntário atualizado com sucesso!", null,
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao deletar voluntário", "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
					break;
				} else {
					JOptionPane.showMessageDialog(null,
							"Erro ao atualizar o voluntário, o CPF não possui a quantidade de dígitos correta", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
				break;

			}

			case 3: {
				UIManager.put("OptionPane.minimumSize", new Dimension(500, 50));
				UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("SanSerif", Font.ROMAN_BASELINE, 18)));
				List<Voluntario> voluntarios = gerenciadorVoluntario.listarVoluntarios();
				StringBuilder voluntariosText = new StringBuilder();
				for (Voluntario voluntario : voluntarios) {
					voluntariosText.append(voluntario.toString()).append("\n");
				}
				JOptionPane.showMessageDialog(null, voluntariosText.toString(), null, JOptionPane.INFORMATION_MESSAGE);
				break;
			}
			case 4: {
				int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (confirmacao == JOptionPane.YES_OPTION) {
					gerenciadorVoluntario.fecharConexao();
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
			MainVoluntario main = new MainVoluntario();
			main.showMenu();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}


