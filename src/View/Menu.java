package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.MenuBar;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.time.temporal.JulianFields;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import Banco.FuncoesBanco;
import Banco.InfoImagem;
import MetodosOrdenacao.Ordenar;

public class Menu {
	private JFrame[] Janelas = new JFrame[2];
	private String[] NomesJanelas = { "Principal", "Ver imagem" };
	private JButton[] BotoesOrdenar = new JButton[2];
	private String[] NomesBotoes = { "Ordenar", "Resetar Tabela" };
	private JPanel[] Container = new JPanel[4];
	private DefaultTableModel ModelTabela;
	private String[] ColunasTabela = { "id", "nome", "tamanho" };
	private String[] ComboOpcoes = { "Selection shot", "Bubble sort", "Insertion sort" };
	private JComboBox<String> Combo = new JComboBox<>(ComboOpcoes);
	private FuncoesBanco Banco = new FuncoesBanco();
	private JTable tabela;

	public static void main(String args[]) {
		try {
			new Menu();
		} catch (InstantiationError error) {
			error.printStackTrace();
		}
	}

	public Menu() {
		/* Obtendo linhas da tabela */
		ArrayList<InfoImagem> Imagens = Banco.ObterImagens();
		/* Obtendo Largura e tamanho da tela */
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();

		/* Instanciado Objetos */
		for (int i = 0; i < Janelas.length; i++) {
			this.Janelas[i] = new JFrame(NomesJanelas[i]);
			this.Janelas[i].setSize(800, 600);
		}
		for (int i = 0; i < BotoesOrdenar.length; i++) {
			this.BotoesOrdenar[i] = new JButton(NomesBotoes[i]); /* Nomes botoes */
			this.BotoesOrdenar[i].setPreferredSize(new Dimension((int) (d.height * 0.25), (int) (d.width * 0.03)));
			this.BotoesOrdenar[i].addActionListener(new OuvinteBotoes());
		}

		for (int i = 0; i < Container.length; i++) {
			this.Container[i] = new JPanel();
		}
		/* Criando Cabecalho */
		ImageIcon logo = new ImageIcon(this.getClass().getResource("/View/Logo.png")); /* Obtendo Imagem do logo */
		logo.setImage(logo.getImage().getScaledInstance((int) (d.height * 0.25), (int) (d.width * 0.05), 0));
		JLabel NomeAPS = new JLabel("APS-Estrutura de dados"); /* Criando cabecalho */
		NomeAPS.setFont(new Font("Serif", Font.PLAIN,
				(int) (d.height * 0.05))); /* Definindo Fonte e calculando pela largura da tela */
		NomeAPS.setForeground(Color.WHITE);
		this.Container[0].setLayout(new FlowLayout(FlowLayout.LEFT));
		this.Container[0].setBackground(new Color(220, 20, 60));
		this.Container[0].add(new JLabel(logo));
		this.Container[0].add(NomeAPS);
		/* Criando Footer */
		this.Container[1].setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel Footer = new JLabel("Todos os direitos reservados");
		Footer.setFont(new Font("Serif", Font.PLAIN,
				(int) (d.height * 0.05))); /* Definindo Fonte e calculando pela largura da tela */
		Footer.setForeground(Color.WHITE);
		this.Container[1].add(Footer);
		this.Container[1].setBackground(new Color(220, 20, 60));
		/* Criando Tabela Central */
		this.ModelTabela = new DefaultTableModel();
		for (int i = 0; i < ColunasTabela.length; i++) {
			this.ModelTabela.addColumn(ColunasTabela[i]);
		}
		for (InfoImagem uniqueimage : Imagens) {
			Object[] linha = { uniqueimage.getId(), uniqueimage.getNome(), uniqueimage.getTamanho() };
			this.ModelTabela.addRow(linha);
		}
		tabela = new JTable(this.ModelTabela);
		tabela.addMouseListener(new EventoClickTabela());
		JScrollPane ScrollTabela = new JScrollPane(tabela);
		/* Criando botoes e JcomboBox */
		this.Container[3].setBackground(Color.WHITE);
		this.Combo.setPreferredSize(new Dimension((int) (d.width * 0.15), (int) (d.height * 0.04)));
		this.Container[3].add(this.Combo);
		for (int i = 0; i < BotoesOrdenar.length; i++) {
			this.Container[3].add(BotoesOrdenar[i]);
		}
		this.Container[2].setBorder(BorderFactory.createEmptyBorder((int) (d.width * 0.01), (int) (d.height * 0.01),
				(int) (d.width * 0.01), (int) (d.height * 0.01)));
		this.Container[2].setLayout(new BorderLayout());
		this.Container[2].add(ScrollTabela, BorderLayout.PAGE_START);
		this.Container[2].add(this.Container[3], BorderLayout.CENTER);
		this.Container[2].setBackground(Color.white);
		/* Criando Container Principal */
		this.Janelas[0].setVisible(true);
		this.Janelas[0].setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.Janelas[0].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.Janelas[0].setLayout(new BorderLayout());
		/* Criando Container Principal */
		this.Janelas[0].add(this.Container[0], BorderLayout.PAGE_START);
		this.Janelas[0].add(this.Container[1], BorderLayout.PAGE_END);
		this.Janelas[0].add(this.Container[2], BorderLayout.CENTER);
	}

	class OuvinteBotoes implements ActionListener {
		private FuncoesBanco Banco = new FuncoesBanco();
		private Ordenar Ordenacao = new Ordenar();

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == BotoesOrdenar[0]) {
				ModelTabela.setRowCount(0);
				ArrayList<InfoImagem> Imagens = Banco.ObterImagens();
				ArrayList<InfoImagem> Ordenado = new ArrayList<>();
				switch (Combo.getSelectedItem().toString()) {
				case "Selection shot":
					long Inicio = System.nanoTime();
					Ordenado = Ordenacao.SelectionSort(Imagens);
					String Mesangem = "Tempo Total: " + (System.nanoTime() - Inicio) + " Milisegundos";
					for (InfoImagem Order : Ordenado) {
						Object[] linha = { Order.getId(), Order.getNome(), Order.getTamanho() };
						ModelTabela.addRow(linha);
					}
					JOptionPane.showMessageDialog(null, Mesangem);
					break;
				case "Bubble sort":
					Inicio = System.nanoTime();
					Ordenado = Ordenacao.BubbleShort(Imagens);
					Mesangem = "Tempo Total: " + (System.nanoTime() - Inicio) + " Milisegundos";
					for (InfoImagem Order : Ordenado) {
						Object[] linha = { Order.getId(), Order.getNome(), Order.getTamanho() };
						ModelTabela.addRow(linha);
					}
					JOptionPane.showMessageDialog(null, Mesangem);
					break;
				case "Insertion sort":
					Inicio = System.nanoTime();
					Ordenado = Ordenacao.InsertionShot(Imagens);
					Mesangem = "Tempo Total: " + (System.nanoTime() - Inicio) + " Milisegundos";
					for (InfoImagem Order : Ordenado) {
						Object[] linha = { Order.getId(), Order.getNome(), Order.getTamanho() };
						ModelTabela.addRow(linha);
					}
					JOptionPane.showMessageDialog(null, Mesangem);
					break;
				default:
					break;
				}
			}
			if (e.getSource() == BotoesOrdenar[1]) {
				ArrayList<InfoImagem> Imagens = Banco.ObterImagens();
				if (ModelTabela.getRowCount() > 0) {
					ModelTabela.setRowCount(0);
					for (InfoImagem Order : Imagens) {
						Object[] linha = { Order.getId(), Order.getNome(), Order.getTamanho() };
						ModelTabela.addRow(linha);
					}
				}
			}
		}
	}

	class EventoClickTabela extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			Janelas[1] = new JFrame(NomesJanelas[1]);
			int IndexID = tabela.getSelectedRow();
			int id = (int) tabela.getValueAt(IndexID, 0);
			InfoImagem imagem = Banco.ObterImagem(id);
			ImageIcon ByteToImage = new ImageIcon(imagem.getImagem());
			Janelas[1].setVisible(true);
			Janelas[1].setSize(800,800);
			Janelas[1].setLocationRelativeTo(null);
			Janelas[1].add(new JLabel(ByteToImage));
			Janelas[1] = null;
			System.gc();
		}
	}
}
