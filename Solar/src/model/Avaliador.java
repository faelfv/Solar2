package model;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.sql.*;
import service.FuncaoColaboradorService;
import service.SugestaoService;
import service.FuncaoSugestaoService;

public class Avaliador extends JFrame implements ActionListener{
 
	private static final long serialVersionUID = 1L;
//perfil
   private JLabel lpfNome, lpfCpf, lpfSexo, lpfTel, lpfEmail, lpfLogin, lpfSenha;
   private JTextField tpfNome, tpfCpf, tpfSexo, tpfTel, tpfEmail, tpfLogin;
   private JPasswordField tpfSenha;
   private JButton bpfLimpar, bpfCarregar;
   //mural
   private JTextField numId;
   private JButton bpmMural, bpmAprovar, bpmExcluir, bmuAtHistorico;
   private String carregaDados;
   private JPanel painelFundo;
   private JTable tabela;
   private JScrollPane rolagem;
   String[] coluna = {"Ocorrência","Título","Colaborador","Status","Dresc"};
   Object[][] dados;
   
   private FuncaoColaboradorService fColS;
   private FuncaoColaborador fCol;
   private FuncaoSugestao fSug;
   private FuncaoSugestaoService fSser;
   private Connection conn;
   String sLogin;
     
   public Avaliador(Connection conn){
      super("Avaliador");
      
      sLogin = System.getProperty("login","");
         
      //perfil
      lpfNome = new JLabel("Nome");
      lpfCpf = new JLabel("CPF:");
      lpfSexo = new JLabel("Sexo");
      lpfTel = new JLabel("Tel");
      lpfEmail = new JLabel("Email");
      lpfLogin = new JLabel("Login");
      lpfSenha = new JLabel("Senha");
      tpfNome = new JTextField(20);
      tpfNome.setEditable(false);
      tpfCpf = new JTextField(10);
      tpfCpf.setEditable(false);
      tpfSexo = new JTextField(2);
      tpfSexo.setEditable(false);
      tpfTel = new JTextField(10);
      tpfTel.setEditable(false);
      tpfEmail = new JTextField(20);
      tpfEmail.setEditable(false);
      tpfLogin = new JTextField(10);
      tpfSenha = new JPasswordField(10);
      tpfSenha.setEditable(false);
      bpfLimpar = new JButton("Limpar");
      bpfCarregar = new JButton("Carregar");
      
      //mural
      numId = new JTextField(10);
      dados = carregaDados(conn);
      tabela = new JTable(dados, coluna);
      rolagem = new JScrollPane(tabela);         
      bpmMural = new JButton("Acesso Mural");
      bpmAprovar = new JButton("Mudar Sugestão");
      bpmExcluir = new JButton("Excluir Sugestão");
      bmuAtHistorico = new JButton("Atualizar Histórico");
      
      this.conn = conn;
      
      Container caixa = getContentPane();
      caixa.setLayout(new BorderLayout());
      JTabbedPane pane = new JTabbedPane();
      caixa.add(pane, BorderLayout.CENTER);
      
      //paineis      
      JPanel perfil = new JPanel(new BorderLayout());
      JPanel mural = new JPanel(new BorderLayout());     
      
      pane.addTab("Perfil", perfil);
      pane.addTab("Mural", mural);
          
      //perfil
      JPanel pfCaixas = new JPanel(new GridLayout(4,1));
      JPanel pfPanel1 = new JPanel(new FlowLayout());
      JPanel pfPanel2 = new JPanel(new FlowLayout());
      JPanel pfPanel3 = new JPanel(new FlowLayout());
      JPanel pfPanel4 = new JPanel(new FlowLayout());
      JPanel pfBotao = new JPanel(new FlowLayout());
            
      perfil.add(pfCaixas, BorderLayout.CENTER);
      perfil.add(pfBotao, BorderLayout.SOUTH);
      
      pfCaixas.add(pfPanel1);
      pfCaixas.add(pfPanel2);
      pfCaixas.add(pfPanel3);
      pfCaixas.add(pfPanel4);
      pfPanel1.add(lpfNome);
      pfPanel1.add(tpfNome);
      pfPanel1.add(lpfSexo);
      pfPanel1.add(tpfSexo);
      pfPanel2.add(lpfCpf);
      pfPanel2.add(tpfCpf);
      pfPanel2.add(lpfTel);
      pfPanel2.add(tpfTel);
      pfPanel3.add(lpfEmail);
      pfPanel3.add(tpfEmail);
      pfPanel4.add(lpfLogin);
      pfPanel4.add(tpfLogin);
      pfPanel4.add(lpfSenha);
      pfPanel4.add(tpfSenha);
   
      pfBotao.add(bpfLimpar);
      pfBotao.add(bpfCarregar);
          
      //mural
      painelFundo = new JPanel(new GridLayout(1, 1));
      
      painelFundo.setSize(500, 120);      
      painelFundo.setVisible(true);
      painelFundo.add(rolagem); 
     
      JPanel pmBotao = new JPanel(new FlowLayout());
      
      mural.add(painelFundo, BorderLayout.CENTER);
      mural.add(pmBotao, BorderLayout.SOUTH);
      
      pmBotao.add(numId);
      pmBotao.add(bpmMural);
      pmBotao.add(bpmAprovar);
      pmBotao.add(bpmExcluir);
      pmBotao.add(bmuAtHistorico);
      
      //ação botões
      bpfLimpar.addActionListener(this);
      bpfCarregar.addActionListener(this);
      bpmMural.addActionListener(this);
      bpmAprovar.addActionListener(this);
      bpmExcluir.addActionListener(this);
      bmuAtHistorico.addActionListener(this);
                  
      //final janela
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(700,400);
      setVisible(true);
      setResizable(false);
      setLocationRelativeTo(null);	
   }
   
   public void actionPerformed(ActionEvent e){
      if(e.getSource() == bpfLimpar){  
         tpfNome.setText("");
         tpfCpf.setText("");
         tpfSexo.setText("");
         tpfTel.setText("");
         tpfEmail.setText("");
         tpfLogin.setText("");
         tpfSenha.setText("");   
      }
      else if(e.getSource() == bpfCarregar){
         fColS = new FuncaoColaboradorService();
    	 fCol = new FuncaoColaborador();
         fCol.setLogin(tpfLogin.getText());
         fColS.carregar(carregaDados);
         tpfNome.setText(fCol.getNome());
         tpfSexo.setText(fCol.getSexo().toString());
         tpfCpf.setText(fCol.getCpf());
         tpfTel.setText(fCol.getTel());
         tpfEmail.setText(fCol.getEmail());
         tpfSenha.setText(fCol.getSenha());
      }
      else if(e.getSource() == bpmMural){
         new Mural(conn).setVisible(true);
         dispose();
      }
      else if(e.getSource() == bpmAprovar){
         if(numId.getText().equals("N")){
            fSug = new FuncaoSugestao();
            fSser = new FuncaoSugestaoService();
            fSug.setId(Integer.parseInt(numId.getText()));
            fSser.aprovar((FuncaoSugestao) conn);
         }
         else{
            fSug = new FuncaoSugestao();
            fSser = new FuncaoSugestaoService();
            fSug.setId(Integer.parseInt(numId.getText()));
            fSser.negar((FuncaoSugestao)conn);
         }
      }
      else if(e.getSource() == bpmExcluir){
         fSug = new FuncaoSugestao();
         fSser = new FuncaoSugestaoService();
         fSug.setId(Integer.parseInt(numId.getText()));
         fSser.excluir(getDefaultCloseOperation());
      }
      else if(e.getSource() == bmuAtHistorico){
         //atualiza historico
         painelFundo.remove(rolagem);
         //instancia uma nova JTable e uma nova barra de rolagem, pois nao da para mudar o conteudo da antiga
         tabela = new JTable(dados, coluna);
         rolagem = new JScrollPane(tabela);
         painelFundo.setSize(500, 120);      
         painelFundo.setVisible(true);
         dados = carregaDados(conn); 
         //coloca de volta
         painelFundo.add(rolagem);
         validate();
         repaint(); 
      }
   }
   
   //carrega dados para tabela
   public String[][] carregaDados(Connection conn){
      Sugestao sugestao = new Sugestao();
      SugestaoService sSer = new SugestaoService();
      sugestao.setAval(sLogin);
      ArrayList<Sugestao> lista = sSer.carregarAvaliador(sugestao);
      
      String[][] saida = new String[lista.size()][coluna.length];
      for(int i = 0; i < lista.size(); i++){
         sugestao = lista.get(i);
         saida[i][0] = sugestao.getId()+"";
         saida[i][1] = sugestao.getTitulo()+"";
         saida[i][2] = sugestao.getColab()+"";
         saida[i][3] = sugestao.getStatus()+"";
         saida[i][4] = sugestao.getDescricao()+"";
      }
      return saida;
   }
}