package model;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class TelaLogin extends JFrame implements ActionListener, MouseListener, KeyListener{
   
	private static final long serialVersionUID = 1L;
private JLabel lblLogin, lblSenha, lblRecuperar, lblImagem;
   private JTextField txtLogin;
   private JPasswordField pswSenha;
   private JButton btnEntrar, btnCadastro;
   private JComboBox<String> tLogin;
   private Connection conn;
   private ValidaColaborador vCol;
   private ValidaAvaliador vAva;
   String[] lista = {"Colaborador", "Avaliador", "Superior"};
   
   public TelaLogin(Connection conn){
   
      super("Solar");
   
      lblLogin = new JLabel ("Login");   
      lblSenha = new JLabel("Senha");
      lblRecuperar = new JLabel("<HTML><U>Esqueceu sua senha?</U></HTML>");
      lblImagem = new JLabel(new ImageIcon("images/logo1.png"));
      tLogin = new JComboBox<String>(lista);
      txtLogin = new JTextField(20);
      pswSenha = new JPasswordField(20);      
      btnCadastro =  new JButton("Cadastrar");
      btnEntrar = new JButton("Entrar");
     
      lblLogin.setFont(new Font("Arial Black", Font.BOLD, 20));
      lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
      lblSenha.setFont(new Font("Arial Black", Font.BOLD, 20));
      lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
      lblRecuperar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));     
      txtLogin.setHorizontalAlignment(SwingConstants.CENTER);
      pswSenha.setHorizontalAlignment(SwingConstants.CENTER);
          
      this.conn = conn;
      
      Container caixa = getContentPane();
      caixa.setLayout(new BorderLayout());
            
      JPanel pCentro = new JPanel(new GridLayout(1,2));
      JPanel pImagem = new JPanel(new FlowLayout());
      JPanel pCaixas = new JPanel(new GridLayout(5,1,10,10));
      JPanel pSul = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
   
      caixa.add(pCentro, BorderLayout.CENTER);
      caixa.add(pSul, BorderLayout.SOUTH);
           
      pCentro.add(pImagem);
      pCentro.add(pCaixas);
      pImagem.add(lblImagem);
      pCaixas.add(tLogin);
      pCaixas.add(lblLogin);
      pCaixas.add(txtLogin);
      pCaixas.add(lblSenha);
      pCaixas.add(pswSenha);
      pSul.add(lblRecuperar);
      pSul.add(btnCadastro);
      pSul.add(btnEntrar);
            
      btnEntrar.addActionListener(this);
      btnCadastro.addActionListener(this);
      lblRecuperar.addMouseListener(this);
      txtLogin.addKeyListener(this);
      pswSenha.addKeyListener(this);
      
      setSize(550,300);
      setLocationRelativeTo(null);	
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
      setResizable(false);
   }
   
   @SuppressWarnings("deprecation")
public void actionPerformed(ActionEvent e){
      //primeiro verifica combobox depois o login
      if(e.getSource() == btnEntrar){   
         if(tLogin.getSelectedItem().equals("Superior")){
            if(txtLogin.getText().equals("admin") && pswSenha.getText().equals("admin123")){
               new Superior(conn).setVisible(true);
               dispose();
            }
            else{
               JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos");
               txtLogin.setText("");
               pswSenha.setText("");
            }
         }
         else if(tLogin.getSelectedItem().equals("Avaliador")){
            vAva = new ValidaAvaliador();
            boolean resposta = vAva.consultar(conn, txtLogin.getText(), pswSenha.getText());
            if (resposta == true) {
               System.setProperty("login",txtLogin.getText());
               new Avaliador(conn).setVisible(true);
               dispose();  
            }
            else {
               JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos");
               txtLogin.setText("");
               pswSenha.setText("");
            }
         }    
         else{ 
            vCol = new ValidaColaborador();                                 
            boolean resposta = vCol.consultar(conn, txtLogin.getText(), pswSenha.getText());
            if (resposta == true) {        
               System.setProperty("login",txtLogin.getText()); 
               new Colaborador(conn).setVisible(true);
               dispose();  
            }
            else {
               JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos");
               txtLogin.setText("");
               pswSenha.setText("");
            }
         }
      }
      else if(e.getSource() == btnCadastro){
         new CadastrarNovo(conn).setVisible(true);   
      }
   }
   
   //mouseEvent deve iniciar todas
   public void mousePressed(MouseEvent e){}
   public void mouseEntered(MouseEvent e){}
   public void mouseExited(MouseEvent e){}
   public void mouseReleased(MouseEvent e){}
   public void mouseClicked(MouseEvent e){
      new RecuperarSenha(conn).setVisible(true);
   }
   
   //keyEvent deve iniciar todas
   public void keyReleased(KeyEvent e){}
   public void keyTyped(KeyEvent e){}
   @SuppressWarnings("deprecation")
public void keyPressed(KeyEvent e){
      if(e.getKeyCode() == KeyEvent.VK_ENTER){
         if(tLogin.getSelectedItem().equals("Superior")){
            if(txtLogin.getText().equals("admin") && pswSenha.getText().equals("admin123")){
               new Superior(conn).setVisible(true);
               dispose();
            }
         }
         else if(tLogin.getSelectedItem().equals("Avaliador")){
            vAva = new ValidaAvaliador();                                  
            boolean resposta = vAva.consultar(conn, txtLogin.getText(), pswSenha.getText());
            if (resposta == true) {
               System.setProperty("login",txtLogin.getText());
               new Avaliador(conn).setVisible(true);
               dispose();  
            }
            else {
               JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos");
               txtLogin.setText("");
               pswSenha.setText("");
            }
         }    
         else{ 
            vCol = new ValidaColaborador();                                  
            boolean resposta = vCol.consultar(conn, txtLogin.getText(), pswSenha.getText());
            if (resposta == true) {
               System.setProperty("login",txtLogin.getText()); 
               new Colaborador(conn).setVisible(true);
               dispose();  
            }
            else {
               JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos");
               txtLogin.setText("");
               pswSenha.setText("");
            }
         }
      }
   }
}