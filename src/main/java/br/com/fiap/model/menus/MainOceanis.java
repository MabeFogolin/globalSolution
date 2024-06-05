package br.com.fiap.model.menus;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

import br.com.fiap.beans.Oceanis;
import br.com.fiap.controller.GerenciadorOceanis;


public class MainOceanis {

	private GerenciadorOceanis gerenciadorOceanis;

	public MainOceanis() throws SQLException, ClassNotFoundException {
		this.gerenciadorOceanis = new GerenciadorOceanis();
	}

	public void showMenu() throws SQLException {
		String[] options = { "Adicionar Oceanis", "Deletar Oceanis", "Atualizar Oceanis", "Listar Oceanis", "Sair" };
		int selectedOptionIndex;
		do {
			selectedOptionIndex = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Oceanis",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

			switch (selectedOptionIndex) {
			case 0: {
				int idMenu = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o ID do menu:"));
				String ufAcesso = JOptionPane.showInputDialog(null, "Informe a UF de acesso:");
				int tamanhoUF = ufAcesso.length();

				if (tamanhoUF == 2) {
					Oceanis oceanis = new Oceanis(idMenu, ufAcesso);
					if (gerenciadorOceanis.adicionarOceanis(oceanis)) {
						JOptionPane.showMessageDialog(null, "Menu Oceanis adicionado com sucesso!", null,
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao adicionar o menu", "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
					break;
				} else {
					JOptionPane.showMessageDialog(null,
							"Erro ao adicionar o menu, a UF não possui o tamanho correto, lembrando que ão dois caracteres",
							"Erro", JOptionPane.ERROR_MESSAGE);
				}
				break;

			}

			case 1: {
				int idMenu = Integer
						.parseInt(JOptionPane.showInputDialog(null, "Informe o ID do menu a ser deletado:"));
				if (gerenciadorOceanis.deletarOceanis(idMenu)) {
					JOptionPane.showMessageDialog(null, "Menu Oceanis deletado com sucesso!", null,
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao deletar menu Oceanis", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
			case 2: {
				int idMenu = Integer
						.parseInt(JOptionPane.showInputDialog(null, "Informe o ID do menu a ser atualizado:"));
				String ufAcesso = JOptionPane.showInputDialog(null, "Informe a nova UF correspondente ao menu: ");
				Oceanis oceanis = new Oceanis(idMenu, ufAcesso);
				if (gerenciadorOceanis.atualizarOceanis(oceanis)) {
					JOptionPane.showMessageDialog(null, "Menu Oceanis atualizado com sucesso!", null,
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao atualizar menu Oceanis", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
			case 3: {
				List<Oceanis> menusOceanis = gerenciadorOceanis.listarOceanis();
				StringBuilder oceanisText = new StringBuilder();
				for (Oceanis oceanis : menusOceanis) {
					oceanisText.append(oceanis.toString()).append("\n");
				}
				JOptionPane.showMessageDialog(null, oceanisText.toString(), null, JOptionPane.INFORMATION_MESSAGE);
				break;
			}
			case 4: {
				int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (confirmacao == JOptionPane.YES_OPTION) {
					gerenciadorOceanis.fecharConexao();
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
			MainOceanis main = new MainOceanis();
			main.showMenu();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
