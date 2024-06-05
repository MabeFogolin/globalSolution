package br.com.fiap.model.menus;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

import br.com.fiap.beans.Funcionario;
import br.com.fiap.controller.GerenciadorFuncionario;


public class MainFuncionario {

	private GerenciadorFuncionario gerenciadorFuncionario;

	public MainFuncionario() throws ClassNotFoundException, SQLException {
		this.gerenciadorFuncionario = new GerenciadorFuncionario();
	}

	public void showMenu() throws SQLException {
		String[] options = { "Adicionar Funcionário", "Deletar Funcionário", "Atualizar Funcionário",
				"Listar Funcionários", "Sair" };
		int selectedOptionIndex;
		do {
			selectedOptionIndex = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Menu de Funcionários",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

			switch (selectedOptionIndex) {
			case 0: {
				String funcId = JOptionPane.showInputDialog(null, "Informe o ID do funcionário:");
				String funcCpf = JOptionPane.showInputDialog(null, "Informe o CPF do funcionário:");
				String funcNome = JOptionPane.showInputDialog(null, "Informe o nome do funcionário:");
				String funcSobrenome = JOptionPane.showInputDialog(null, "Informe o sobrenome do funcionário:");
				long funcTel = Long.parseLong(JOptionPane.showInputDialog(null, "Informe o telefone do funcionário:"));
				float funcSalario = Float
						.parseFloat(JOptionPane.showInputDialog(null, "Informe o salário do funcionário:"));
				int fkOceanis = 000001;
				Funcionario funcionario = new Funcionario(funcId, funcCpf, funcNome, funcSobrenome, funcTel,
						funcSalario, fkOceanis);
				if (gerenciadorFuncionario.adicionarFuncionario(funcionario)) {
					JOptionPane.showMessageDialog(null, "Funcionário adicionado com sucesso!", null,
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao adicionar funcionário", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
			case 1: {
				String funcCpf = JOptionPane.showInputDialog(null, "Informe o CPF do funcionário para deletar:");
				if (gerenciadorFuncionario.deletarFuncionario(funcCpf)) {
					JOptionPane.showMessageDialog(null, "Funcionário deletado com sucesso!", null,
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao deletar funcionário", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
			case 2: {
				String funcCpf = JOptionPane.showInputDialog(null, "Informe o CPF do funcionário a ser atualizado:");
				String funcId = JOptionPane.showInputDialog(null, "Informe o novo ID do funcionário:");
				String funcNome = JOptionPane.showInputDialog(null, "Informe o novo nome do funcionário:");
				String funcSobrenome = JOptionPane.showInputDialog(null, "Informe o novo sobrenome do funcionário:");
				long funcTel = Long
						.parseLong(JOptionPane.showInputDialog(null, "Informe o novo telefone do funcionário:"));
				float funcSalario = Float
						.parseFloat(JOptionPane.showInputDialog(null, "Informe o novo salário do funcionário:"));
				String email = JOptionPane.showInputDialog(null, "Informe o novo email do funcionário:");
				String senha = JOptionPane.showInputDialog(null, "Informe a nova senha do funcionário:");
				int fkOceanis = 00001;
				Funcionario funcionario = new Funcionario(funcId, funcCpf, funcNome, funcSobrenome, funcTel,
						funcSalario, email, senha, fkOceanis);
				if (gerenciadorFuncionario.atualizarFuncionario(funcionario)) {
					JOptionPane.showMessageDialog(null, "Funcionário atualizado com sucesso!", null,
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao atualizar funcionário", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
			case 3: {
				List<Funcionario> funcionarios = gerenciadorFuncionario.listarFuncionarios();
				StringBuilder funcionariosText = new StringBuilder();
				for (Funcionario funcionario : funcionarios) {
					funcionariosText.append(funcionario.toString()).append("\n");
				}
				JOptionPane.showMessageDialog(null, funcionariosText.toString(), null, JOptionPane.INFORMATION_MESSAGE);
				break;
			}
			case 4: {
				int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (confirmacao == JOptionPane.YES_OPTION) {
					gerenciadorFuncionario.fecharConexao();
					System.exit(0);
				}
				break;
			}
			default:
				break;
			}
		} while (selectedOptionIndex != 4);
	}

}
