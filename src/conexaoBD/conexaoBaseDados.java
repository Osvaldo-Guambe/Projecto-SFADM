/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexaoBD;

import FormMain.Principal;
import static FormMain.Principal.jcBaseDados;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class conexaoBaseDados {
    
    public Statement pesquisar;
    public ResultSet resultado;
    private final String url = "jdbc:mysql://localhost/ssfadmmesse";
    public Connection conexao;
    
    public void Conectar(){
        try{
            conexao = DriverManager.getConnection(url,"root","");            
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null,"Ocorreu um erro na conexão com a Base de Dados!"+erro);
        }
    }    
    
    public void executaSQL(String sql){
        try{
            pesquisar = conexao.createStatement(resultado.TYPE_SCROLL_INSENSITIVE,resultado.CONCUR_READ_ONLY);
            resultado = pesquisar.executeQuery(sql);
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null,"Erro de ExecutaSQL!\n Erro: "+erro);
        }
    }
    
    public Statement createStatement(){
        throw new UnsupportedOperationException("Ainda não Suporta");
    }
    
    public void MostrarTabelasDaBasedados(){
        try{            
            DatabaseMetaData meta = conexao.getMetaData();
        //ResultSet rs1 = meta.getTables(null, null, null,new String[] {"TABLE"});
            ResultSet rs2 = meta.getTables(null, null,"%", null);
        //System.out.println("One way of Listing Tables");
        //while (rs1.next()){
       //JOptionPane.showMessageDialog(null,rs1.getString("TABLE_NAME")); 
       // System.out.println(rs1.getString("TABLE_NAME"));
       // }
       // System.out.println("Another way of Listing Tables");
       // JOptionPane.showMessageDialog(null,rs1.getString("TABLE_NAME"));
            while(rs2.next()){
        //System.out.println(rs2.getString(3));  
            jcBaseDados.addItem(rs2.getString(3));      
            }      
        }catch(Exception erro){
           System.out.println(erro); 
           JOptionPane.showMessageDialog(null,erro);
          }
    }

}
