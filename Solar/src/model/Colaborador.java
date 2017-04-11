package model;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.sql.*;
import service.FuncaoColaboradorService;
import service.SugestaoService;
import service.FuncaoSugestaoService;

public class Colaborador extends JFrame implements ActionListener, ItemListener{
   
   
	private static final long serialVersionUID = 1L;
//perfil
   private JLabel lpfNome, lpfCpf, lpfSexo, lpfTel, lpfEmail, lpfLogin, lpfSenha, lpfSenhax; 
   private JTextField tpfNome, tpfCpf, tpfSexo, tpfTel, tpfEmail, tpfLogin;
   private JPasswordField tpfSenha, tpfSenhax;
   private JButton bpfAtualizar, bpfCarregar;
   private JComboBox<Integer> cbId;
   private Integer[] setorid;
   //postar
   private JLabel lptTit, lptSetor, lptDescr, lptNome;
   private JTextField tptTit, tptNome;
   private JTextArea tptDescr;
   private JButton bptPostar, bptLimpar;
   //mural
   private JButton bmuMural, bmuAtHistorico;
   private String carregaDados;
   private JPanel painelFundo;
   private JTable tabela;
   private JScrollPane rolagem;
   String[] coluna = {"Ocorrência","Título","Setor","Avaliador","Status","Dresc"};
   Object[][] dados;

   private FuncaoColaboradorService fColS;
   private FuncaoColaborador fCol;
   private FuncaoSugestao fSug;
   private FuncaoSugestaoService fSser;
   private Connection conn;
   String sLogin;   
  
   public Colaborador(Connection conn){
      super("Colaborador");
      
      sLogin = System.getProperty("login","");
      
      //perfil
      lpfNome = new JLabel("Nome");
      lpfCpf = new JLabel("CPF:");
      lpfSexo = new JLabel("Sexo");
      lpfTel = new JLabel("Tel");
      lpfEmail = new JLabel("Email");
      lpfLogin = new JLabel("Login");
      lpfSenha = new JLabel("Nova Senha");
      lpfSenhax = new JLabel("Confirme senha");
      tpfNome = new JTextField(20);
      tpfNome.setEditable(false);
      tpfCpf = new JTextField(10);
      tpfCpf.setEditable(false);
      tpfSexo = new JTextField(2);
      tpfSexo.setEditable(false);
      tpfTel = new JTextField(10);
      tpfEmail = new JTextField(20);
      tpfLogin = new JTextField(sLogin,10);
      tpfLogin.setEditable(false);
      tpfSenha = new JPasswordField(10);
      tpfSenhax = new JPasswordField(10);
      bpfAtualizar = new JButton("Atualizar");
      bpfCarregar = new JButton("Carregar");
      
      //postar 
      lptTit = new JLabel("Título");
      lptSetor = new JLabel("Setor");
      lptDescr = new JLabel("Descrição");
      lptNome = new JLabel("Setor");
      tptNome = new JTextField(20);
      tptNome.setEditable(false);
      tptTit = new JTextField(20);
      tptDescr = new JTextArea(10,35);
      tptDescr.setLineWrap(true);
      setorid = carregaSetores(conn);
      cbId = new JComboBox<Integer>(setorid);
      bptPostar = new JButton("Postar");
      bptLimpar = new JButton("Limpar");
      
      //mural
      dados = carregaDados(conn);
      tabela = new JTable(dados, coluna);
      rolagem = new JScrollPane(tabela);   
      bmuMural = new JButton("Acesso Mural");
      bmuAtHistorico = new JButton("Atualizar Histórico");
            
      this.conn = conn;
      
      Container caixa = getContentPane();
      caixa.setLayout(new BorderLayout());
      JTabbedPane pane = new JTabbedPane();
      caixa.add(pane, BorderLayout.CENTER);
      
      //paineis      
      JPanel perfil = new JPanel(new BorderLayout());
      JPanel postar = new JPanel(new BorderLayout()); 
      JPanel mural = new JPanel(new BorderLayout());     
      
      pane.addTab("Perfil", perfil);
      pane.addTab("Postar", postar);
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
      pfPanel4.add(lpfSenhax);
      pfPanel4.add(tpfSenhax);
      pfBotao.add(bpfAtualizar);
      pfBotao.add(bpfCarregar);
          
      //postar
      JPanel ptCaixas = new JPanel(new GridLayout(3,1));
      JPanel ptBotao = new JPanel(new FlowLayout());
      JPanel c1 = new JPanel(new FlowLayout()); 
      JPanel c2 = new JPanel(new FlowLayout());
      JPanel c3 = new JPanel(new FlowLayout());
            
      postar.add(ptBotao, BorderLayout.SOUTH);
      postar.add(ptCaixas, BorderLayout.CENTER);
      
      ptBotao.add(bptLimpar);
      ptBotao.add(bptPostar);
      ptCaixas.add(c1);
      ptCaixas.add(c2);
      ptCaixas.add(c3);
      
      c1.add(lptTit);
      c1.add(tptTit);
      c2.add(lptSetor);
      c2.add(cbId);
      c2.add(lptNome);
      c2.add(tptNome);
      c3.add(lptDescr);
      c3.add(tptDescr);
                  
                  
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
      
   
      //ação botões
      bptPostar.addActionListener(this);
      bptLimpar.addActionListener(this);
      bpfAtualizar.addActionListener(this);
      bpfCarregar.addActionListener(this);
      cbId.addItemListener(this);           
      bmuMural.addActionListener(this);
      bmuAtHistorico.addActionListener(this);
      
      //final janela
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
      setSize(700,400);      
      setResizable(false);
      setLocationRelativeTo(null);	
   }
   
   @SuppressWarnings("deprecation")
public void actionPerformed(ActionEvent e){
      if(e.getSource() == bpfAtualizar){ 
         //atualiza dados 
         if(tpfSenha.getText().equals(tpfSenhax.getText())){
        	fColS = new FuncaoColaboradorService();
        	fCol = new FuncaoColaborador();
            fCol.setLogin(tpfLogin.getText());
            fCol.setTel(tpfTel.getText());
            fCol.setEmail(tpfEmail.getText());
            fCol.setSenha(tpfSenha.getText());
            fColS.atualizar(fCol);
         }
         else{
            JOptionPane.showMessageDialog(null, "Senhas não correspondem");
         }
      }
      else if(e.getSource() == bpfCarregar){
         //carrega dados
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
         tpfSenhax.setText(tpfSenha.getText());
      }
      else if(e.getSource() == bptLimpar){
         //limpa campos postar
         tptTit.setText("");
         tptDescr.setText("");   
      }
      else if(e.getSource() == bptPostar){
         //posta sugestao
         fSug = new FuncaoSugestao();
         fSser = new FuncaoSugestaoService();
         fSug.setTitulo(tptTit.getText());
         fSug.setSetorid(cbId.getItemAt(cbId.getSelectedIndex()));
         fSug.setDescricao(tptDescr.getText());
         fSug.setColab(tpfLogin.getText());
         fSug.setSetor(tptNome.getText());
         fSser.incluir((FuncaoSugestao) conn);
         JOptionPane.showMessageDialog(null,"Mensagem enviada com sucesso.");
         tptTit.setText("");
         tptDescr.setText("");
      }
      else if(e.getSource() == bmuMural){
         //abre mural
         new Mural(conn).setVisible(true);
         dispose();
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
   
   //carrega id dos setores
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
  
   //ajusta nome do setor
   public void itemStateChanged(ItemEvent e){
      //sempre que muda o id selecionado na combobox este evento e gerado
      if(e.getStateChange() == ItemEvent.SELECTED){
         switch(cbId.getItemAt(cbId.getSelectedIndex())){
            case 1:
               tptNome.setText("Recursos Humanos");
               break;
            case 2:
               tptNome.setText("Patrimonio");
               break;
            case 3:
               tptNome.setText("Bolsas");
               break;
            case 4:
               tptNome.setText("Reitoria");
               break;
            case 5:
               tptNome.setText("Coordenação");
               break;
            case 6:
               tptNome.setText("Danos");
               break;
         }
      }
   } 

   //carrega dados para tabela
   public String[][] carregaDados(Connection conn){
      Sugestao sugestao = new Sugestao();
      SugestaoService sSer = new SugestaoService();
      sugestao.setColab(tpfLogin.getText());
      ArrayList<Sugestao> lista = sSer.carregarColaborador(sugestao);
      
      String[][] saida = new String[lista.size()][coluna.length];
      for(int i = 0; i < lista.size(); i++){
         sugestao = lista.get(i);
         saida[i][0] = sugestao.getId()+"";
         saida[i][1] = sugestao.getTitulo()+"";
         saida[i][2] = sugestao.getSetor()+"";
         saida[i][3] = sugestao.getAval()+"";
         saida[i][4] = sugestao.getStatus()+"";
         saida[i][5] = sugestao.getDescricao()+"";
      }
      return saida;
   }
}