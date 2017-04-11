package model;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

import java.text.ParseException;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import service.FuncaoColaboradorService;

public class CadastrarNovo extends JFrame implements ActionListener{

   
	private static final long serialVersionUID = 1L;
private JLabel lblNome, lblCpf, lblSexo, lblTel, lblEmail, lblLogin, lblSenha, lblSenhax, lblTit;
   private JTextField txtNome, txtEmail, txtLogin;
   private JFormattedTextField txtCpf, txtTel;
   private JComboBox<String> cmbSexo;
   private JPasswordField pswSenha, pswSenhax;
   private JButton btnEnviar, btnLimpar, btnCancel;
   private FuncaoColaborador fCol;
   private FuncaoColaboradorService fColS;
   private String[] sexo = {"M", "F"};
   
   public CadastrarNovo(Connection conn){
      super("Cadastrar Colaborador");
      
      MaskFormatter mascaraCpf = null;  
      MaskFormatter mascaraTel = null;
      
      try{
         mascaraCpf = new MaskFormatter("###.###.###-##");
         mascaraCpf.setPlaceholderCharacter('_');
         mascaraTel = new MaskFormatter("(###)#####-####");
         mascaraTel.setPlaceholderCharacter('_');
      }
      catch(ParseException excp){
         System.err.println("Erro na formatação: " + excp.getMessage());
      }
      
      lblNome = new JLabel("Nome Completo: ");
      lblCpf = new JLabel("CPF: ");
      lblSexo = new JLabel("Sexo: ");
      lblTel = new JLabel("Telefone: ");
      lblEmail = new JLabel("Email: ");
      lblLogin = new JLabel("Login: ");
      lblSenha = new JLabel("Senha: ");
      lblSenhax = new JLabel("Repita senha: ");  
      lblTit = new JLabel("Cadastrar Colaborador");    
      txtNome = new JTextField(35);
      txtCpf = new JFormattedTextField(mascaraCpf);
      txtTel = new JFormattedTextField(mascaraTel);
      txtEmail = new JTextField(40);
      txtLogin = new JTextField(10);
      pswSenha = new JPasswordField(7);
      pswSenhax = new JPasswordField(7);
      cmbSexo = new JComboBox<String>(sexo);
      btnEnviar = new JButton("Enviar");
      btnLimpar = new JButton("Limpar");
      btnCancel = new JButton("Cancelar");
      
      lblTit.setHorizontalAlignment(SwingConstants.CENTER);
      lblTit.setFont(new Font("Arial Black", Font.BOLD, 30));
      
      Container caixa = getContentPane();
      caixa.setLayout(new BorderLayout());
      
      JPanel pCentro = new JPanel(new GridLayout(5,1));
      JPanel pSul = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
      JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
      JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
      JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
      JPanel p4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
      JPanel p5 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
      caixa.add(pCentro, BorderLayout.CENTER);
      caixa.add(pSul, BorderLayout.SOUTH);
            
      pCentro.add(p1);
      pCentro.add(p2);
      pCentro.add(p3);
      pCentro.add(p4);
      pCentro.add(p5);
      
      p1.add(lblTit);
      p2.add(lblNome);
      p2.add(txtNome);
      p3.add(lblCpf);
      p3.add(txtCpf);
      p3.add(lblSexo);
      p3.add(cmbSexo);
      p3.add(lblTel);
      p3.add(txtTel);
      p4.add(lblEmail);
      p4.add(txtEmail);
      p5.add(lblLogin);
      p5.add(txtLogin);
      p5.add(lblSenha);
      p5.add(pswSenha);
      p5.add(lblSenhax);
      p5.add(pswSenhax);
       
      pSul.add(btnLimpar);
      pSul.add(btnEnviar);
      pSul.add(btnCancel);
      
      btnEnviar.addActionListener(this);
      btnLimpar.addActionListener(this);
      btnCancel.addActionListener(this);
      
      setSize(535,515);
      setVisible(true);
      setLocationRelativeTo(null);	      
      setResizable(false);
      dispose();         
   }
   
   @SuppressWarnings("deprecation")
public void actionPerformed(ActionEvent e){
      fCol = new FuncaoColaborador();
      fColS = new FuncaoColaboradorService();
      
      if(e.getSource() == btnEnviar){
         try{
            fCol.setNome(txtNome.getText());
            fCol.setCpf(txtCpf.getText());
            fCol.setSexo(cmbSexo.getSelectedItem().toString());
            fCol.setTel(txtTel.getText());
            fCol.setEmail(txtEmail.getText());
            fCol.setLogin(txtLogin.getText());
            fCol.setSenha(pswSenha.getText());
            fColS.criar(fCol);
         }
         catch (Exception e1) {
            JOptionPane.showMessageDialog(null, "Login já esta em uso.");
         } 
         dispose();     
      }
      
      if(e.getSource() == btnLimpar){
         txtNome.setText("");
         txtCpf.setText("");
         txtTel.setText("");
         txtEmail.setText("");
         txtLogin.setText("");
         pswSenha.setText("");
         pswSenhax.setText("");
      }
      else if(e.getSource() == btnCancel){
         dispose();
      }
   }
}