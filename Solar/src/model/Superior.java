package model;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import service.FuncaoColaboradorService;
import service.FuncaoAvaliadorService;
import service.SugestaoService;

public class Superior extends JFrame implements ActionListener{
   
   
	private static final long serialVersionUID = 1L;
//colaborador
   private JLabel lclNome, lclCpf, lclSexo, lclTel, lclEmail, lclLogin, lclSenha;
   private JTextField tclNome, tclCpf, tclSexo, tclTel, tclEmail, tclLogin;
   private JPasswordField tclSenha;
   private JButton bclExcluir, bclProcurar, bclLimpar;
   //avaliador
   private JLabel lavNome, lavSetor, lavLogin, lavSenha, lavSenhax;
   private JTextField tavNome, tavSetor, tavLogin;
   private JPasswordField tavSenha, tavSenhax;
   private JButton bavExcluir,  bavInserir, bavProcurar, bavLimpar, bavAlterar;
   private Integer[] setorid;
   private JComboBox<Integer> cbId;
   //mural
   private JButton bmuMural, bmuAtHistorico;
   private String carregaDados;
   private JPanel painelFundo;
   private JTable tabela;
   private JScrollPane rolagem;
   String[] coluna = {"Ocorrência","Título","Setor","Avaliador","Colaborador","Status","Dresc"};
   Object[][] dados;
   
   private Connection conn;
   private FuncaoAvaliador fAval;
   private FuncaoColaborador fCol;
   private FuncaoColaboradorService fColS;
   private FuncaoAvaliadorService fAvalS;
   public Superior(Connection conn){
      super("Superior");  
      
      //colaborador
      lclNome = new JLabel("Nome");
      lclCpf = new JLabel("CPF:");
      lclSexo = new JLabel("Sexo");
      lclTel = new JLabel("Tel");
      lclEmail = new JLabel("Email");
      lclLogin = new JLabel("Login");
      lclSenha = new JLabel("Senha");
      tclNome = new JTextField(20);
      tclNome.setEditable(false);
      tclCpf = new JTextField(10);
      tclCpf.setEditable(false);
      tclSexo = new JTextField(2);
      tclSexo.setEditable(false);
      tclTel = new JTextField(10);
      tclTel.setEditable(false);
      tclEmail = new JTextField(20);
      tclEmail.setEditable(false);
      tclLogin = new JTextField(10);
      tclSenha = new JPasswordField(10);
      tclSenha.setEditable(false);
      bclExcluir = new JButton("Excluir");
      bclProcurar = new JButton("Procurar");
      bclLimpar = new JButton("Limpar");
   
      //avaliador
      lavNome = new JLabel("Nome");
      lavSetor = new JLabel("Setor");
      lavLogin = new JLabel("Login");
      lavSenha = new JLabel("Senha");
      lavSenhax = new JLabel("Confirma");
      tavNome = new JTextField(10);
      setorid = carregaSetores(conn);
      cbId = new JComboBox<Integer>(setorid);
      tavSetor = new JTextField(10);
      tavSetor.setEditable(false);
      tavLogin = new JTextField(10);
      tavSenha = new JPasswordField(10);
      tavSenhax = new JPasswordField(10);
      bavExcluir = new JButton("Excluir");
      bavInserir = new JButton("Inserir");
      bavProcurar = new JButton("Procurar");
      bavLimpar = new JButton("Limpar");
      bavAlterar = new JButton("Alterar");
      
      //mural
      dados = carregaDados(conn);
      tabela = new JTable(dados, coluna);
      rolagem = new JScrollPane(tabela);   
      bmuMural = new JButton("Acesso Mural");
      bmuAtHistorico = new JButton("Atualizar Histórico");
      
      //paineis   
      JPanel colaborador = new JPanel(new BorderLayout());   
      JPanel avaliador = new JPanel(new BorderLayout());     
      JPanel mural = new JPanel(new BorderLayout());     
      
      Container caixa = getContentPane();
      caixa.setLayout(new BorderLayout());
      JTabbedPane pane = new JTabbedPane();
      caixa.add(pane, BorderLayout.CENTER);
                  
      pane.addTab("Colaborador", colaborador);
      pane.addTab("Avaliador", avaliador);
      pane.addTab("Mural", mural);
        
      this.conn = conn;            
      
      //Colaborador
      JPanel pcCaixas = new JPanel(new GridLayout(4,1));
      JPanel pcPanel1 = new JPanel(new FlowLayout());
      JPanel pcPanel2 = new JPanel(new FlowLayout());
      JPanel pcPanel3 = new JPanel(new FlowLayout());
      JPanel pcPanel4 = new JPanel(new FlowLayout());
      JPanel pcBotao = new JPanel(new FlowLayout());
            
      colaborador.add(pcCaixas, BorderLayout.CENTER);
      colaborador.add(pcBotao, BorderLayout.SOUTH);
      
      pcCaixas.add(pcPanel1);
      pcCaixas.add(pcPanel2);
      pcCaixas.add(pcPanel3);
      pcCaixas.add(pcPanel4);
      pcPanel2.add(lclNome);
      pcPanel2.add(tclNome);
      pcPanel2.add(lclSexo);
      pcPanel2.add(tclSexo);
      pcPanel3.add(lclCpf);
      pcPanel3.add(tclCpf);
      pcPanel3.add(lclTel);
      pcPanel3.add(tclTel);
      pcPanel4.add(lclEmail);
      pcPanel4.add(tclEmail);
      pcPanel1.add(lclLogin);
      pcPanel1.add(tclLogin);
      pcPanel1.add(lclSenha);
      pcPanel1.add(tclSenha);
      
      pcBotao.add(bclExcluir);
      pcBotao.add(bclProcurar);
      pcBotao.add(bclLimpar);
         
      //Avaliador
      JPanel paCaixas = new JPanel(new GridLayout(4,1));
      JPanel paPanel1 = new JPanel(new FlowLayout());
      JPanel paPanel2 = new JPanel(new FlowLayout());
      JPanel paPanel3 = new JPanel(new FlowLayout());
      JPanel paPanel4 = new JPanel(new FlowLayout());
      JPanel paBotao = new JPanel(new FlowLayout());
            
      avaliador.add(paCaixas, BorderLayout.CENTER);
      avaliador.add(paBotao, BorderLayout.SOUTH);
      
      paCaixas.add(paPanel1);
      paCaixas.add(paPanel2);
      paCaixas.add(paPanel3);
      paCaixas.add(paPanel4);
      paPanel1.add(lavNome);
      paPanel1.add(tavNome);
      paPanel2.add(lavSetor);
      paPanel2.add(cbId);
      paPanel2.add(tavSetor);
      paPanel3.add(lavLogin);
      paPanel3.add(tavLogin);
      paPanel4.add(lavSenha);
      paPanel4.add(tavSenha);
      paPanel4.add(lavSenhax);
      paPanel4.add(tavSenhax);
      
      paBotao.add(bavExcluir);
      paBotao.add(bavLimpar);
      paBotao.add(bavProcurar);
      paBotao.add(bavInserir);
      paBotao.add(bavAlterar);
       
      //mural 
      painelFundo = new JPanel(new GridLayout(1, 1));
      
      painelFundo.setSize(500, 120);      
      painelFundo.setVisible(true);
      painelFundo.add(rolagem); 
     
      JPanel muBotao = new JPanel(new FlowLayout());
      
      mural.add(painelFundo, BorderLayout.CENTER);
      mural.add(muBotao, BorderLayout.SOUTH);
        
      muBotao.add(bmuMural);
      muBotao.add(bmuAtHistorico);
      
      //botoes
      bclExcluir.addActionListener(this);
      bclProcurar.addActionListener(this);
      bclLimpar.addActionListener(this);
      bavExcluir.addActionListener(this);
      bavLimpar.addActionListener(this);
      bavProcurar.addActionListener(this);
      bavInserir.addActionListener(this);
      bavAlterar.addActionListener(this);
      bmuMural.addActionListener(this);
      bmuAtHistorico.addActionListener(this);
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(700,400);
      setVisible(true);
      setLocationRelativeTo(null);	
   }
   
   @SuppressWarnings("deprecation")
public void actionPerformed(ActionEvent e){
      
      if(e.getSource() == bclExcluir){
    	 fColS = new FuncaoColaboradorService();
         fCol = new FuncaoColaborador();
         fCol.setLogin(tclLogin.getText());
         fColS.excluir(carregaDados);
         tclNome.setText("");
         tclSexo.setText("");
         tclCpf.setText("");
         tclTel.setText("");
         tclEmail.setText("");
         tclSenha.setText("");
         tclLogin.setText("");
      }
      else if(e.getSource() == bclProcurar){
         if(tclLogin.equals("")){
            JOptionPane.showMessageDialog(null, "Digite o login");
         }
         else{
        	fColS = new FuncaoColaboradorService();
            fCol = new FuncaoColaborador();
            fCol.setLogin(tclLogin.getText());
            fColS.carregar(carregaDados);
            tclNome.setText(fCol.getNome());
            tclSexo.setText(fCol.getSexo().toString());
            tclCpf.setText(fCol.getCpf());
            tclTel.setText(fCol.getTel());
            tclEmail.setText(fCol.getEmail());
            tclSenha.setText(fCol.getSenha());         
         }
      }
      else if(e.getSource() == bclLimpar){
         tclNome.setText("");
         tclSexo.setText("");
         tclCpf.setText("");
         tclTel.setText("");
         tclEmail.setText("");
         tclSenha.setText("");
         tclLogin.setText("");
      }
      else if(e.getSource() == bavExcluir){
         fAvalS = new FuncaoAvaliadorService();
    	 fAval = new FuncaoAvaliador();
         fAval.setLogin(tavLogin.getText());
         fAvalS.excluir(carregaDados);
         tavNome.setText("");
         tavSenha.setText("");
         tavSenhax.setText("");
         tavLogin.setText("");
      }
      else if(e.getSource() == bavLimpar){
         tavNome.setText("");
         tavSenha.setText("");
         tavLogin.setText("");
         tavSenhax.setText("");
      }
      else if(e.getSource() == bavInserir){
         try{
        	fAvalS = new FuncaoAvaliadorService();
            fAval = new FuncaoAvaliador();         
            fAval.setNome(tavNome.getText());
            fAval.setSetor(cbId.getItemAt(cbId.getSelectedIndex()));
            fAval.setLogin(tavLogin.getText());
            fAval.setSenha(tavSenha.getText());
            fAvalS.incluir(fAval);
            tavNome.setText("");
            tavSenha.setText("");
            tavLogin.setText("");
            tavSenhax.setText("");
         }
         catch (Exception e1) {
            JOptionPane.showMessageDialog(null, "Login já esta em uso.");
         }
      }
      else if(e.getSource() == bavProcurar){
         if(tavLogin.equals("")){
            JOptionPane.showMessageDialog(null, "Digite o login");
         }
         else{
        	fAvalS = new FuncaoAvaliadorService();
            fAval = new FuncaoAvaliador();
            fAval.setLogin(tavLogin.getText());
            fAvalS.carregar(carregaDados);
            tavNome.setText(fAval.getNome());
            tavSenha.setText(fAval.getSenha());         
            tavSenhax.setText(tavSenha.getText());
         }
      }
      else if(e.getSource() == bavAlterar){
         if(tavSenha.getText().equals(tavSenhax.getText())){
        	fAvalS = new FuncaoAvaliadorService();
            fAval = new FuncaoAvaliador();
            fAval.setNome(tavNome.getText());
            fAval.setSetor(cbId.getItemAt(cbId.getSelectedIndex()));
            fAval.setLogin(tavLogin.getText());
            fAval.setSenha(tavSenha.getText());
            fAvalS.atualizar(fAval);
         }
         else{
            JOptionPane.showMessageDialog(null, "Senhas não correspondem");
         }
      }
      else if(e.getSource() == bmuMural){
         new Mural(conn).setVisible(true);
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
   
   //carrega dados para tabela
   public String[][] carregaDados(Connection conn){
	   SugestaoService sSer = new SugestaoService();
	  Sugestao sugestao = new Sugestao();
      ArrayList<Sugestao> lista = sSer.carregarSuperior(sugestao);
      
      String[][] saida = new String[lista.size()][coluna.length];
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
}