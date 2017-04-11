package model;
import javax.swing.*;

import service.FuncaoColaboradorService;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RecuperarSenha extends JFrame implements ActionListener{
 
	private static final long serialVersionUID = 1L;
	private JLabel lblSenha, lblSenhax, lblLogin;
   private JButton btnConfirmar, btnLimpar;
   private JPasswordField pswSenha, pswSenhax;
   private JTextField txtLogin;
   private FuncaoColaborador fCol;
   private FuncaoColaboradorService fColS;
      
   public RecuperarSenha(Connection conn){
      
      super("RecuperarSenha");
      
      lblLogin = new JLabel("Login: ");
      lblLogin.setFont(new Font("Arial Black", Font.BOLD, 20));
      lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
      lblSenha = new JLabel("Nova senha: ");
      lblSenha.setFont(new Font("Arial Black", Font.BOLD, 20));
      lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
      lblSenhax = new JLabel("Confirme: ");
      lblSenhax.setFont(new Font("Arial Black", Font.BOLD, 20));
      lblSenhax.setHorizontalAlignment(SwingConstants.CENTER);
      
      txtLogin = new JTextField(10);
      txtLogin.setHorizontalAlignment(SwingConstants.CENTER);
      pswSenha = new JPasswordField(10);
      pswSenha.setHorizontalAlignment(SwingConstants.CENTER);
      pswSenhax = new JPasswordField(10);
      pswSenhax.setHorizontalAlignment(SwingConstants.CENTER);
      
      btnConfirmar = new JButton("Salvar");
      btnLimpar = new JButton("Limpar");
      
      Container caixa = getContentPane();
      caixa.setLayout(new BorderLayout());
      
      JPanel pSul = new JPanel(new FlowLayout());
      JPanel pCentro = new JPanel(new GridLayout(3,2,10,10));
      
      caixa.add(pSul, BorderLayout.SOUTH);
      caixa.add(pCentro, BorderLayout.CENTER);
      
      pSul.add(btnLimpar);
      pSul.add(btnConfirmar);
      pCentro.add(lblLogin);
      pCentro.add(txtLogin);
      pCentro.add(lblSenha);
      pCentro.add(pswSenha);
      pCentro.add(lblSenhax);
      pCentro.add(pswSenhax);
      
      btnConfirmar.addActionListener(this);
      btnLimpar.addActionListener(this);
      
      setSize(400,200);
      setLocationRelativeTo(null);
      setVisible(true);
      setResizable(false);
      dispose();
   }
   
   @SuppressWarnings("deprecation")
public void actionPerformed(ActionEvent e){
      if(e.getSource() == btnConfirmar){                 
         if(pswSenha.getText().equals(pswSenhax.getText())){
        	fColS = new FuncaoColaboradorService();
        	fCol = new FuncaoColaborador();
            fCol.setLogin(txtLogin.getText());
            fCol.setSenha(pswSenha.getText());
            
            try{
               fColS.recuperar(fCol);
               JOptionPane.showMessageDialog(null, "Senha alterada com sucesso.");
               dispose(); 
            }
            catch (Exception e1) {
               JOptionPane.showMessageDialog(null, "Erro.");
            } 
         }
         else
            JOptionPane.showMessageDialog(null, "As senhas não conferem.");         
      }         
      else if(e.getSource() == btnLimpar){
         txtLogin.setText("");
         pswSenha.setText("");
         pswSenhax.setText("");
      }
   }   
}