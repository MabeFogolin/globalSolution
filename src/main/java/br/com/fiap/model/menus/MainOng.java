package br.com.fiap.model.menus;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.fiap.beans.Ong;
import br.com.fiap.controller.GerenciadorOng;


public class MainOng {

	private GerenciadorOng gerenciadorOng;

	public MainOng() throws ClassNotFoundException, SQLException {
		this.gerenciadorOng = new GerenciadorOng();
	}

	public void showMenu() throws SQLException {
		String[] options = { "Adicionar ONG", "Deletar ONG", "AtualizarONG", "Listar ONG's", "Sair" };
		int selectedOptionIndex;
		do {
			selectedOptionIndex = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Menu de ONG's",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

			switch (selectedOptionIndex) {
			case 0: {
				String ongNome = JOptionPane.showInputDialog(null, "Informe o nome da ONG:");
				String ongCnpj = JOptionPane.showInputDialog(null, "Informe o CNPJ da ONG:");
				int ongQtdFunc = Integer
						.parseInt(JOptionPane.showInputDialog(null, "Informe a quantidade de funcionários desta ONG:"));
				String uf = JOptionPane.showInputDialog(null, "Informe a UF da ONG:");
				String cidade = JOptionPane.showInputDialog(null, "Informe a cidade da ONG:");
				int fkOceanis = 000001;
				int tamanhoUF = uf.length();

				if (tamanhoUF == 2) {
					Ong ong = new Ong(ongNome, ongCnpj, ongQtdFunc, uf, cidade, fkOceanis);
					if (gerenciadorOng.adicionarOng(ong)) {
						JOptionPane.showMessageDialog(null, "ONG adicionada com sucesso!", null,
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao adicionar ONG", "Erro", JOptionPane.ERROR_MESSAGE);
					}
					break;
				} else {
					JOptionPane.showMessageDialog(null,
							"Erro ao adicionar a ONG, a UF não possui o tamanho correto, lembrando que são dois caracteres",
							"Erro", JOptionPane.ERROR_MESSAGE);
				}
				break;

			}
			case 1: {
				String cnpj = JOptionPane.showInputDialog(null, "Informe o CNPJ da ONG para deletar:");
				if (gerenciadorOng.deletarOng(cnpj)) {
					JOptionPane.showMessageDialog(null, "ONG deletada com sucesso!", null,
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao deletar ONG", "Erro", JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
			case 2: {
				String ongCnpj = JOptionPane.showInputDialog(null, "Informe o CNPJ da ONG a ser atualizada:");
				String ongNome = JOptionPane.showInputDialog(null, "Informe o novo nome da ONG:");
				int ongQtdFunc = Integer.parseInt(
						JOptionPane.showInputDialog(null, "Informe a nova quantidade de funcionários desta ONG:"));
				String uf = JOptionPane.showInputDialog(null, "Informe a nova UF da ONG:");
				String cidade = JOptionPane.showInputDialog(null, "Informe a nova cidade da ONG:");
				int fkOceanis = 00001;
				int tamanhoUF = uf.length();

				if (tamanhoUF == 2) {
					Ong ong = new Ong(ongNome, ongCnpj, ongQtdFunc, uf, cidade, fkOceanis);
					if (gerenciadorOng.atualizarOng(ong)) {
						JOptionPane.showMessageDialog(null, "ONG atualizada com sucesso!", null,
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao atualizar ONG", "Erro", JOptionPane.ERROR_MESSAGE);
					}
					break;
				} else {
					JOptionPane.showMessageDialog(null,
							"Erro ao adicionar a ONG, a UF não possui o tamanho correto, lembrando que são dois caracteres",
							"Erro", JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
			case 3: {
				List<Ong> ongs = gerenciadorOng.listarOngs();
				StringBuilder ongsText = new StringBuilder();
				for (Ong ong : ongs) {
					ongsText.append(ong.toString()).append("\n");
				}
				JOptionPane.showMessageDialog(null, ongsText.toString(), null, JOptionPane.INFORMATION_MESSAGE);
				break;
			}
			case 4: {
				int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (confirmacao == JOptionPane.YES_OPTION) {
					gerenciadorOng.fecharConexao();
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
			MainOng main = new MainOng();
			main.showMenu();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
