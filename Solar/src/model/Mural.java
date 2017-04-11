package model;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.event.*;

import service.SetorService;

public class Mural extends JFrame implements ActionListener, ItemListener, ListSelectionListener{
   
	private static final long serialVersionUID = 1L;
//constantes criadas para configurar as dimensoes do JFrame e do JScrollPane
   final int LARGURA_TELA = 900;
   final int ALTURA_TELA = 600;
   final int LARGURA_SCROLL_PANE = LARGURA_TELA - 100;
   final int ALTURA_SCROLL_PANE = ALTURA_TELA - 110;
   
   private JTextField txtSetor, txtFone;
   private JLabel lblNome, lblId, lblFone;
   private JButton btnSair;
   private Connection conn;
   //setorid sera usado para criar o JComboBox
   private Integer[] setorid;
   //colunas e sugestoes serao usados para criar a JTable
   private String[] colunas = {"Ocorrência", "Titulo", "Setor","Avaliador","Colaborador","Status","Descricao"};
   private Object[][] sugestoes;
   private JComboBox<Integer> cbId;
   private JTable tabelaSugestoes;
   private JPanel pnlCentro;
   private Container caixa;
   private JScrollPane rolagem;
   
   public Mural(Connection conn){
      super("Consulta de Sugestões");
      
      lblId = new JLabel("N°");
      lblNome = new JLabel("Setor");
      lblFone = new JLabel("Telefone");
      txtSetor = new JTextField(20);
      txtSetor.setEditable(false);
      txtFone = new JTextField(10);
      txtFone.setEditable(false);
      btnSair = new JButton("Sair");
      setorid = carregaSetores(conn);
      cbId = new JComboBox<Integer>(setorid);
                        
      this.conn = conn;
      
      instanciaJTableEScrollPane();
      
      caixa = getContentPane();
      caixa.setLayout(new BorderLayout());
      
      JPanel pnlNorte = new JPanel(new FlowLayout());
      JPanel pnlSul = new JPanel(new FlowLayout());
      pnlCentro = new JPanel(new FlowLayout());
      
      pnlNorte.add(lblId);
      pnlNorte.add(cbId);
      pnlNorte.add(lblNome);
      pnlNorte.add(txtSetor);
      pnlNorte.add(lblFone);
      pnlNorte.add(txtFone);
      pnlSul.add(btnSair);
            
      pnlCentro.add(rolagem);
   
      btnSair.addActionListener(this);
      cbId.addItemListener(this);
      
      caixa.add(pnlNorte, BorderLayout.NORTH);
      caixa.add(pnlSul, BorderLayout.SOUTH);
      caixa.add(pnlCentro, BorderLayout.CENTER);
      
      setSize(LARGURA_TELA, ALTURA_TELA);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
      
   }
   
   public void actionPerformed(ActionEvent e){
   
      if(e.getSource() == btnSair){
         System.exit(0);
      }  
   }
   
   // metodo invocado quando muda o item selecionado na combobox
   public void itemStateChanged(ItemEvent e){
      //sempre que muda o id selecionado na combobox este evento e gerado
      if(e.getStateChange() == ItemEvent.SELECTED){
         caixa.remove(pnlCentro);
         pnlCentro.remove(rolagem);
         instanciaJTableEScrollPane(); 
         pnlCentro.add(rolagem);
         caixa.add(pnlCentro, BorderLayout.CENTER);
         validate();
         repaint();
      }
   }
   
   //metodo invocado quando um item da tabela e selecionado
   public void valueChanged(ListSelectionEvent e){
      if(e.getValueIsAdjusting()){
         String resultado = 
            tabelaSugestoes.getColumnName(6)+": "+
            tabelaSugestoes.getValueAt(tabelaSugestoes.getSelectedRow(),6);
            JOptionPane.showMessageDialog(this, resultado);
      }
   }
   
   public Integer[] carregaSetores(Connection conn){
      Departamento departamento = new Departamento();
      ArrayList<Setor> lista = departamento.buscarSetores(conn);
      Integer[] saida = new Integer[lista.size()];
      Setor setor;
      for(int i = 0; i < lista.size(); i++){
         setor = lista.get(i);
         saida[i] = setor.getIdSetor();
      }
      
      return saida;
   }
     
   public String[][] carregaDados(Connection conn){
      Setor setor = new Setor(cbId.getItemAt(cbId.getSelectedIndex()));
      SetorService service = new SetorService();
      service.carregarSugestoes(setor);;
	service.carregar(ALTURA_SCROLL_PANE);
      txtSetor.setText(setor.getNome());
      txtFone.setText(setor.getFone());
      ArrayList<Sugestao> lista = service.carregarSugestoes(setor);
      
      String[][] saida = new String[lista.size()][colunas.length];
      Sugestao sugestao;
      for(int i = 0; i < lista.size(); i++){
         sugestao = lista.get(i);
         saida[i][0] = sugestao.getId()+"";
         saida[i][1] = sugestao.getTitulo()+"";
         saida[i][2] = sugestao.getSetor()+"";
         saida[i][3] = sugestao.getAval()+""; 
         saida[i][4] = sugestao.getColab()+"";
         saida[i][5] = sugestao.getStatus()+"";
         saida[i][6] = sugestao.getDescricao()+"";
      }
      return saida;
   } 
   
   //metodo para centralizar a instanciacao da JTable e nao ficar repetindo codigo
   public void instanciaJTableEScrollPane(){
      sugestoes = carregaDados(conn);
      tabelaSugestoes = new JTable(sugestoes, colunas);
      tabelaSugestoes.getSelectionModel().addListSelectionListener(this);
      rolagem = new JScrollPane(tabelaSugestoes);
      rolagem.setPreferredSize(new Dimension(LARGURA_SCROLL_PANE, ALTURA_SCROLL_PANE));
   }
}