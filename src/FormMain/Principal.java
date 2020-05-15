/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FormMain;

//import static FormMain.Zona.*;
import conexaoBD.conexaoBaseDados;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.CheckBox;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Dell
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    int x, y;
    conexaoBaseDados cbd = new conexaoBaseDados();

    String imgPath = null;

    public Principal() {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        cbd.Conectar();
        cbd.MostrarTabelasDaBasedados();
        ShowData();

        Timer timer = new Timer(1000, new hora());
        timer.start();
        setColor(PaginaIn);

        // MenuIcon();  
    }

    /* public void Percentagem(){
        
        try{
            cbd.pesquisar = cbd.conexao.createStatement();
            
            String sql = " SELECT * FROM cliente ";
            cbd.resultado = cbd.pesquisar.executeQuery(sql);
            
            int soma=0,percentagem=0;
            
             int  valor=0,total=0;             
             total = Integer.parseInt( TAbenificiario.getText());
            loadingbar.setMaximum(total);
             for(int i=0;i<=5;i++){
              // Thread.sleep(200);
               //loadingnum.setText(Integer.toString(i)+"%");
                //   valor =total -i;
               loadingbar.setValue(i);
               if(i>=7){
                  JOptionPane.showMessageDialog(null,"Conectado com sucesso"); 
               }
            }
            
            /*do{
                int cont=cbd.resultado.getInt("ano_Nascimento");
                cont=1; 
           
                soma=soma+cont;
                   
                int valor=0,totals=82;
                for(int i=0;i<=30;i++){
                        // Thread.sleep(1);
                        valor=totals-i;
                        //loadingnum.setText(Integer.toString(i)+" Vagas Ocupadas");
                        loadingbar.setValue(i);
                        //disponivel.setText(valor+ " Vagas Disponíveis");
                        if(i>10){

                           loadingbar.setForeground(Color.red);
                           //loadingnum.setText(Integer.toString(i)+" Vagas Ocupadas. ");
                           //disponivel.setText(valor+ " adminVagas Disponíveis");
                        }
                    }
            }while(cbd.resultado.next());*/
 /*  }catch(Exception e){
            JOptionPane.showMessageDialog(null,""+e);
        }
    }*/
    public void MenuIcon() {
        lblregisto.setVisible(false);
        PaginaIn.setVisible(false);
        lblgrouparentesco.setVisible(false);
        lblListaMenu.setVisible(false);
        jLabel4.setVisible(false);
        jLabel11.setVisible(false);
        jLabel24.setVisible(false);
        jLabel25.setVisible(false);
        jLabel35.setVisible(false);
        jLabel37.setVisible(false);
        lblSatisfacaoBenificio.setVisible(false);
        lblMenuAdminitrador.setVisible(false);
    }

    public void LimparCamposRegisto() {
        txtnome.setText("");
        txtapelido.setText("");
        txtlocal.setText("");
        txtramo.setText("");
        txtmae.setText("");
        txtbi.setText("");
        txtpai.setText("");
        txtDistrito.setText("");
        txtLocalidade.setText("");
        txtLocalidade.setText("");
        txtemail.setText("");
        txtcontacto.setText("");
        txtramo.setText("");
        txtProfissao.setText("");
        txtLocalTrabalho.setText("");
        txtEntidadeOndeTrabalho.setText("");
        patenteMilitar.setText("");
        jCheckBoxBairro.setSelected(false);
        femenino.setSelected(false);
        masculino.setSelected(false);
        jPanelBairro.setVisible(false);
        Activo.setSelected(false);
        Contratado.setSelected(false);
        Reformado.setSelected(false);
        jCheckBoxPatente.setSelected(false);
    }

    public ImageIcon ResizeImage(String ImagePath, byte[] pic) {
        ImageIcon MyImage = null;
        if (ImagePath != null) {
            MyImage = new ImageIcon(ImagePath);
        } else {
            MyImage = new ImageIcon(pic);
        }
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(lbl_Image.getWidth(), lbl_Image.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    public void listabeneficios() {
        try {
            cbd.pesquisar = cbd.conexao.createStatement();

            String sql = " SELECT * FROM cliente , contatos c where bi=c.bi_contacto ORDER BY nome";
            cbd.resultado = cbd.pesquisar.executeQuery(sql);

            DefaultTableModel tm = (DefaultTableModel) jTableBenificiario.getModel();
            tm.setRowCount(0);

            int soma = 0;

            while (cbd.resultado.next()) {

                soma = soma + 1;

                Object o[] = {soma, cbd.resultado.getString("nome"), cbd.resultado.getString("apelido"), cbd.resultado.getString("dia_Nascimento") + " " + cbd.resultado.getString("ano_Nascimento"), cbd.resultado.getString("estado_civil"), cbd.resultado.getString("sexo"), cbd.resultado.getString("bi") + cbd.resultado.getString("letra"), cbd.resultado.getString("c.contacto"), cbd.resultado.getString("pai"), cbd.resultado.getString("mae"), cbd.resultado.getString("pais"), cbd.resultado.getString("provincia"), cbd.resultado.getString("distrito"), cbd.resultado.getString("localidade"), cbd.resultado.getString("hora"), cbd.resultado.getString("data"),};
                tm.addRow(o);
            }

            TotalListaBenificiario.setText("Total de Benificiários: " + soma + ".");
            TAbenificiario.setText("" + soma + ".");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "" + e);
        }
    }

    public void listasatisfacaoBenicicario() {
        try {
            cbd.pesquisar = cbd.conexao.createStatement();

            String sql = " SELECT * FROM satisfacao_benificario";
            cbd.resultado = cbd.pesquisar.executeQuery(sql);

            DefaultTableModel tm = (DefaultTableModel) jTableSatisfacao.getModel();
            tm.setRowCount(0);

            int soma = 0;
            int totalValor = 0;

            while (cbd.resultado.next()) {

                int num = cbd.resultado.getInt("valor");
                num = 1;
                soma = soma + num;
                int valor = cbd.resultado.getInt("valor");
                totalValor = totalValor + valor;

                Object o[] = {soma, cbd.resultado.getString("codigo_agre_famil"), cbd.resultado.getString("nome"), cbd.resultado.getString("benificio"), cbd.resultado.getString("descricao"), cbd.resultado.getString("valor"), cbd.resultado.getString("operador"), cbd.resultado.getString("data"), cbd.resultado.getString("horas"), cbd.resultado.getString("numeracao")};
                tm.addRow(o);
            }

            jLabelValor.setText("Valor Total: " + totalValor + " Mtn.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "" + e);
        }
    }

    public void ListaAgregadoFamiliar() {
        try {
            cbd.pesquisar = cbd.conexao.createStatement();

            String sql = " SELECT * FROM agregadofamiliar, cliente c where c.bi=af_bi ";
            cbd.resultado = cbd.pesquisar.executeQuery(sql);

            DefaultTableModel tm = (DefaultTableModel) jTableAgredadoFamiliar.getModel();
            tm.setRowCount(0);

            int soma = 0;

            while (cbd.resultado.next()) {

                int num = cbd.resultado.getInt("codigo");
                num = 1;
                soma = soma + num;

                Object o[] = {soma, cbd.resultado.getString("codigo"), cbd.resultado.getString("nome"), cbd.resultado.getString("apelido"), cbd.resultado.getString("grau_parentesco"), cbd.resultado.getString("sexo"), cbd.resultado.getString("af_bi") + cbd.resultado.getString("c.letra"), cbd.resultado.getString("c.nome") + " " + cbd.resultado.getString("c.apelido"), cbd.resultado.getString("ocupacao_parente"), cbd.resultado.getString("dia_Nascimento") + " " + cbd.resultado.getString("data_nascimento"), cbd.resultado.getString("data")};
                tm.addRow(o);
            }

            totalAgregado.setText("Total de Benificiários: " + soma + ".");
            TAagregadoFamiliar.setText("" + soma + ".");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "" + e);
        }
    }

    public void ListaLocalTrabalhoMilitar() {
        try {
            cbd.pesquisar = cbd.conexao.createStatement();
            String sql = ("select * from cliente,militar m where bi=m.bi_militar");
            cbd.resultado = cbd.pesquisar.executeQuery(sql);

            DefaultTableModel tm = (DefaultTableModel) jTableLocalTrabalho.getModel();
            tm.setRowCount(0);

            int soma = 0;

            while (cbd.resultado.next()) {

                int num = cbd.resultado.getInt("m.codigo");
                num = 1;
                soma = soma + num;

                Object o[] = {soma, cbd.resultado.getString("nome") + " " + cbd.resultado.getString("apelido"), cbd.resultado.getString("m.patente"), cbd.resultado.getString("m.grau"), cbd.resultado.getString("m.entidade_onde_trabalho"), cbd.resultado.getString("m.local_trabalho"), cbd.resultado.getString("m.ramo_fadm"), cbd.resultado.getString("m.profissao"), cbd.resultado.getString("m.data_incoporacao"), cbd.resultado.getString("m.local")};
                tm.addRow(o);
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "" + erro);
        }
    }

    public void ListaLocalTrabalhoFunicioanrio() {
        try {
            cbd.pesquisar = cbd.conexao.createStatement();
            String sql = ("select * from cliente,funcionario f where bi=f.bi_funcionario");
            cbd.resultado = cbd.pesquisar.executeQuery(sql);

            DefaultTableModel tm = (DefaultTableModel) jTableFuncionario.getModel();
            tm.setRowCount(0);

            int soma = 0;

            while (cbd.resultado.next()) {

                int num = cbd.resultado.getInt("f.codigo");
                num = 1;
                soma = soma + num;

                Object o[] = {soma, cbd.resultado.getString("nome") + " " + cbd.resultado.getString("apelido"), cbd.resultado.getString("f.situacao"), cbd.resultado.getString("f.entidade_onde_trabalha"), cbd.resultado.getString("f.local_trabalho"), cbd.resultado.getString("f.profissao"), cbd.resultado.getString("f.data_admissao"), cbd.resultado.getString("f.local")};
                tm.addRow(o);
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "" + erro);
        }
    }

    public void ListaTabelaQuotizacao() {
        try {
            cbd.pesquisar = cbd.conexao.createStatement();
            String sql = ("select * from quotizacao, cliente b where b.bi=bi_benificiario");
            cbd.resultado = cbd.pesquisar.executeQuery(sql);

            DefaultTableModel tm = (DefaultTableModel) jTableQuotizacao.getModel();
            tm.setRowCount(0);

            int soma = 0;

            while (cbd.resultado.next()) {

                int num = cbd.resultado.getInt("ano");
                num = 1;
                soma = soma + num;

                Object o[] = {soma, cbd.resultado.getString("b.nome") + " " + cbd.resultado.getString("b.apelido"), cbd.resultado.getString("janeiro"), cbd.resultado.getString("fevereiro"), cbd.resultado.getString("março"), cbd.resultado.getString("abril"), cbd.resultado.getString("maio"), cbd.resultado.getString("junho"), cbd.resultado.getString("julho"), cbd.resultado.getString("agosto"), cbd.resultado.getString("setembro"), cbd.resultado.getString("outubro"), cbd.resultado.getString("novembro"), cbd.resultado.getString("dezembro"), cbd.resultado.getString("ano"), cbd.resultado.getString("bi_benificiario")};
                tm.addRow(o);
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "" + erro);
        }
    }
    
    public void ListaJoia(){
          try {
            cbd.pesquisar = cbd.conexao.createStatement();
            String sql = ("select * from joia , cliente b where b.bi=bi_benificiario");
            cbd.resultado = cbd.pesquisar.executeQuery(sql);

            DefaultTableModel tm = (DefaultTableModel) jTableJoia.getModel();
            tm.setRowCount(0);

            int soma = 0;

            while (cbd.resultado.next()) {

                int num = cbd.resultado.getInt("data");
                num = 1;
                soma = soma + num;

                Object o[] = {soma, cbd.resultado.getString("b.nome") + " " + cbd.resultado.getString("b.apelido"), cbd.resultado.getString("joia"), cbd.resultado.getString("bi_benificiario"), cbd.resultado.getString("data"),cbd.resultado.getString("data_registo")};
                tm.addRow(o);
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "" + erro);
        }
    }

    public void AcessarSistema() {
        try {
            cbd.pesquisar = cbd.conexao.createStatement();

            String sql = " SELECT * FROM senhas WHERE username like'%" + txtuser.getText() + "%' and password like'%" + txtsenha.getText() + "%' ";
            cbd.resultado = cbd.pesquisar.executeQuery(sql);
            cbd.resultado.first();

            if (txtuser.getText().equals("") || txtsenha.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Campo Vazio!");
            } else if (txtuser.getText().equals(cbd.resultado.getString("username")) && txtsenha.getText().equals(cbd.resultado.getString("password"))) {
                PaginaInicial.setVisible(true);
                login.setVisible(false);
                lblregisto.setVisible(true);
                PaginaIn.setVisible(true);
                lblgrouparentesco.setVisible(true);
                lblListaMenu.setVisible(true);
                jLabel4.setVisible(true);
                jLabel11.setVisible(true);
                jLabel24.setVisible(true);
                jLabel25.setVisible(true);
                jLabel35.setVisible(true);
                jLabel37.setVisible(true);
                lblSatisfacaoBenificio.setVisible(true);
                lblMenuAdminitrador.setVisible(true);
            } else {

            }
        } catch (Exception erro) {
            txtsenha.setForeground(Color.red);
            txtuser.setForeground(Color.red);

        }
    }

    public void ShowData() {
        Date d = new Date();
        SimpleDateFormat formatar = new SimpleDateFormat("yyyy/MM/dd");
        data.setText(formatar.format(d));
    }

    public void NumeroTotalFemenino() {
        try {
            cbd.executaSQL("select * from  agregadofamiliar where sexo='Femenino'");
            cbd.resultado.first();

            int soma = 0;

            do {
                if (cbd.resultado.getString("sexo") == null) {
                    soma = 0;
                } else {
                    soma = soma + 1;
                }
            } while (cbd.resultado.next());
            TotalFemenino.setText("" + soma);
        } catch (Exception erro) {
        }
    }

    public void NumeroTotalSolterio() {
        try {
            cbd.executaSQL("select * from cliente where estado_civil='Solteiro'");
            cbd.resultado.first();

            int soma = 0;

            do {
                if (cbd.resultado.getString("estado_civil") == null) {
                    soma = 0;
                } else {
                    soma = soma + 1;
                }
            } while (cbd.resultado.next());
            jLabelTotalSolteriro.setText("" + soma);
        } catch (Exception erro) {

        }
    }

    public void NumeroTotalDivorciado() {
        try {
            cbd.executaSQL("select * from cliente where estado_civil='Divorciado'");
            cbd.resultado.first();

            int soma = 0;

            do {
                if (cbd.resultado.getString("estado_civil") == null) {
                    soma = 0;
                } else {
                    soma = soma + 1;
                }
            } while (cbd.resultado.next());
            jLabelTotalDivorciado.setText("" + soma);
        } catch (Exception erro) {
        }
    }

    public void NumeroTotalCasado() {
        try {
            cbd.executaSQL("select * from cliente where estado_civil='Casado'");
            cbd.resultado.first();

            int soma = 0;

            do {
                if (cbd.resultado.getString("estado_civil") == null) {
                    soma = 0;
                } else {
                    soma = soma + 1;
                }
            } while (cbd.resultado.next());
            jLabelTotalSolteriro1.setText("" + soma);
        } catch (Exception erro) {

        }
    }

    public void AnosdeNascimente() {

        Date d = new Date();
        SimpleDateFormat formatar = new SimpleDateFormat("yyyy");
        datanasc.setText(formatar.format(d));

        int datass = Integer.parseInt(datanasc.getText());

        for (int i = 1930; i <= datass; i++) {
            jComboBoxAnosNasc.addItem("" + i);
            jComboBoxAnoIcorporacao.addItem("" + i);
            jComboBoxAnosNascAF.addItem("" + i);
            jComboBoxAnoQuota2.addItem(""+i);
        }
    }

    public void NumeroTotalMasculino() {
        try {
            cbd.executaSQL("select * from  agregadofamiliar where sexo='Masculino'");
            cbd.resultado.first();

            int soma = 0;

            do {
                if (cbd.resultado.getString("sexo") == null) {
                    soma = 0;
                } else {
                    soma = soma + 1;
                }
            } while (cbd.resultado.next());
            TotalMasculinoAF.setText("" + soma);
        } catch (Exception erro) {
        }
    }

    public void NumeroTotalMilitar() {
        try {
            cbd.executaSQL("select * from  militar");
            cbd.resultado.first();

            int soma = 0;
            do {
                if (cbd.resultado.getString("bi_militar") == null) {
                    soma = 0;
                } else {
                    soma = soma + 1;
                }
            } while (cbd.resultado.next());
            jLabelTotalMilitar.setText("" + soma);

        } catch (Exception erro) {
        }
    }

    public void NumeroTotalQuandroPermanente() {
        try {
            cbd.executaSQL("select * from  militar where grau='Quadro Permanente'");
            cbd.resultado.first();

            int soma = 0;

            do {
                if (cbd.resultado.getString("grau") == null) {
                    soma = 0;
                } else {
                    soma = soma + 1;
                }
            } while (cbd.resultado.next());
            jLabelTotalQuadroPensionista.setText("" + soma);
        } catch (Exception erro) {
        }
    }

    public void NumeroTotalPensionista() {
        try {
            cbd.executaSQL("select * from  militar where grau='Pensionista'");
            cbd.resultado.first();

            int soma = 0;

            do {
                if (cbd.resultado.getString("grau") == null) {
                    soma = 0;
                } else {
                    soma = soma + 1;
                }
            } while (cbd.resultado.next());
            jLabelTotalPensionista.setText("" + soma);
        } catch (Exception erro) {
        }
    }

    public void NumeroTotalReserva() {
        try {
            cbd.executaSQL("select * from  militar where grau='Reserva'");
            cbd.resultado.first();

            int soma = 0;

            do {
                if (cbd.resultado.getString("grau") == null) {
                    soma = 0;
                } else {
                    soma = soma + 1;
                }
            } while (cbd.resultado.next());
            jLabelTotalReserva.setText("" + soma);
        } catch (Exception erro) {
        }
    }

    public void NumeroTotalReforma() {
        try {
            cbd.executaSQL("select * from  militar where grau='Reforma'");
            cbd.resultado.first();

            int soma = 0;

            do {
                if (cbd.resultado.getString("grau") == null) {
                    soma = 0;
                } else {
                    soma = soma + 1;
                }
            } while (cbd.resultado.next());
            jLabelTotalReforma.setText("" + soma);
        } catch (Exception erro) {
        }
    }

    public void NumeroTotalFuncionario() {
        try {
            cbd.executaSQL("select * from  funcionario");
            cbd.resultado.first();

            int soma = 0;

            do {
                if (cbd.resultado.getString("bi_funcionario") == null) {
                    soma = 0;
                } else {
                    soma = soma + 1;
                }
            } while (cbd.resultado.next());
            jLabelTotalFfuncionario.setText("" + soma);
        } catch (Exception erro) {
        }
    }

    public void NumeroTotalFuncionarioActivo() {
        try {
            cbd.executaSQL("select * from  funcionario where situacao='Activo'");
            cbd.resultado.first();

            int soma = 0;

            do {
                if (cbd.resultado.getString("situacao") == null) {
                    soma = 0;
                } else {
                    soma = soma + 1;
                }
            } while (cbd.resultado.next());
            jLabelTotalFactivo.setText("" + soma);
        } catch (Exception erro) {
        }
    }

    public void NumeroTotalFuncionarioContratado() {
        try {
            cbd.executaSQL("select * from  funcionario where situacao='Contratado'");
            cbd.resultado.first();

            int soma = 0;

            do {
                if (cbd.resultado.getString("situacao") == null) {
                    soma = 0;
                } else {
                    soma = soma + 1;
                }
            } while (cbd.resultado.next());
            jLabelTotalFContratado.setText("" + soma);
        } catch (Exception erro) {
        }
    }

    public void NumeroTotalFuncionarioReformado() {
        try {
            cbd.executaSQL("select * from  funcionario where situacao='Reformado'");
            cbd.resultado.first();

            int soma = 0;

            do {
                if (cbd.resultado.getString("situacao") == null) {
                    soma = 0;
                } else {
                    soma = soma + 1;
                }
            } while (cbd.resultado.next());
            jLabelTotalFreformado.setText("" + soma);
        } catch (Exception erro) {
        }
    }

    public void NumeroTotalMasculinoBenificiario() {
        try {
            cbd.executaSQL("select * from cliente where sexo='Masculino'");
            cbd.resultado.first();

            int soma = 0;

            do {

                if (cbd.resultado.getString("sexo") == null) {
                    soma = 0;
                } else {
                    soma = soma + 1;
                }
            } while (cbd.resultado.next());
            TotalMasculinoBenifi.setText("" + soma);

            int valor = 0, total = 0;

            total = Integer.parseInt(TAbenificiario.getText());
            loadingbar.setMaximum(total);

            for (int i = 0; i <= soma; i++) {
                // Thread.sleep(200);
                //loadingnum.setText(Integer.toString(i)+"%");
                loadingbar.setValue(i);
                if (i >= 7) {
                    //JOptionPane.showMessageDialog(null,"Conectado com sucesso"); xxxxxxxxxxxxxxxxxxxxxxxxxAnalise
                }
            }
            float percento = (soma * 100) / total;
            percentagemM.setText("" + percento + "%");
        } catch (Exception erro) {
        }
    }

    public void nome() {
        try {
            cbd.pesquisar = cbd.conexao.createStatement();

            String sql = " select * from agregadofamiliar where codigo='" + txtcodigoAF.getText() + "'";
            cbd.resultado = cbd.pesquisar.executeQuery(sql);

            cbd.resultado.first();

            NomeAf.setText(cbd.resultado.getString("nome") + " " + cbd.resultado.getString("apelido"));

        } catch (Exception erro) {
            NomeAf.setText("");
        }
    }

    public void NumeroTotalFemeninoBenificiario() {
        try {
            cbd.executaSQL("select * from cliente where sexo='Femenino'");
            cbd.resultado.first();

            int soma = 0;

            do {
                if (cbd.resultado.getString("sexo") == null) {
                    soma = 0;
                } else {
                    soma = soma + 1;
                }
            } while (cbd.resultado.next());
            TotalFemeninoBenif.setText("" + soma);

            int valor = 0, total = 0;
            total = Integer.parseInt(TAbenificiario.getText());
            jProgressBarF.setMaximum(total);
            for (int i = 0; i <= soma; i++) {
                // Thread.sleep(200);
                //loadingnum.setText(Integer.toString(i)+"%");
                //   valor =total -i;
                jProgressBarF.setValue(i);
                if (i >= 7) {
                    //************************************************************************analise
                }

            }

            float percento = (soma * 100) / total;
            percentagemF.setText("" + percento + "%");
        } catch (Exception erro) {
        }
    }

    public void listaHerdeiro() {
        cbd.executaSQL("select * from herdeiro where  bi_benificiar like'%" + txtbibeneficiario.getText() + "%'");
        try {
            int soma = 0;
            DefaultListModel lst = new DefaultListModel();
            while (cbd.resultado.next()) {
                soma = soma + 1;
                lst.addElement(" " + cbd.resultado.getString("nome") + " | " + cbd.resultado.getString("grau_parentesco"));
                // lst.addElement(cbd.resultado.getString("contacto"));
                lst.addElement("");
                lst.addElement("");
            }
            jListHerdeiros.setModel(lst);

            //lblShowAFquantidade.setText("" + soma);
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Nome nao Localizado na Lista Telfonica! " + erro);
        }
    }

    public void listaFamila() {
        cbd.executaSQL("select * from agregadofamiliar where af_bi like'%" + txtbibeneficiario.getText() + "%'");

        try {
            int soma = 0;
            DefaultListModel lst = new DefaultListModel();
            while (cbd.resultado.next()) {
                soma = soma + 1;
                lst.addElement(" " + cbd.resultado.getString("nome") + " " + cbd.resultado.getString("apelido") + " | " + cbd.resultado.getString("codigo") + " | " + cbd.resultado.getString("grau_parentesco"));
                // lst.addElement(cbd.resultado.getString("contacto"));
                lst.addElement("");
                lst.addElement("");
            }
            ListaFamilarUnicaPessoa.setModel(lst);
            jLNF.setText("Nº de Gregado Familiar:");
            lblShowAFquantidade.setText("" + soma);
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Nome nao Localizado na Lista Telfonica! " + erro);
        }
    }

    public void setColor(JLabel lbl) {
        lbl.setBackground(new Color(255, 255, 255));
        lbl.setForeground(new Color(0, 153, 51));
    }

    public void resetColor(JLabel lbl) {
        lbl.setForeground(new Color(255, 255, 255));
        lbl.setBackground(new Color(0, 153, 51));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sexo = new javax.swing.ButtonGroup();
        quotizacaoOpetion = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        PaginaIn = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblregisto = new javax.swing.JLabel();
        lblgrouparentesco = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lblListaMenu = new javax.swing.JLabel();
        lblMenuAdminitrador = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        lblSatisfacaoBenificio = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabelShowFoto = new javax.swing.JLabel();
        jLabeNomeFoto = new javax.swing.JLabel();
        data = new javax.swing.JLabel();
        lblMenuQuatizacao = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        PaginaInicial = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        txtShownome = new javax.swing.JLabel();
        txtPnome = new javax.swing.JLabel();
        txtPramo = new javax.swing.JLabel();
        txtShowramo = new javax.swing.JLabel();
        txtShowunidade = new javax.swing.JLabel();
        txtPunidade = new javax.swing.JLabel();
        txtPbi = new javax.swing.JLabel();
        txtShowbi = new javax.swing.JLabel();
        txtShowcontacto = new javax.swing.JLabel();
        txtPcontacto = new javax.swing.JLabel();
        txtPsexo = new javax.swing.JLabel();
        txtShowsexo = new javax.swing.JLabel();
        txtShowhora = new javax.swing.JLabel();
        txtPhora = new javax.swing.JLabel();
        txtPdata = new javax.swing.JLabel();
        txtShowdata = new javax.swing.JLabel();
        txtTitle = new javax.swing.JLabel();
        txtShowdataInscricao = new javax.swing.JLabel();
        txtPdataIncricao = new javax.swing.JLabel();
        logout = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel120 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        txtPesBI = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel122 = new javax.swing.JLabel();
        registo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabelNomeRegisto = new javax.swing.JLabel();
        txtnome = new javax.swing.JTextField();
        jLabelApelidoRegisto = new javax.swing.JLabel();
        txtapelido = new javax.swing.JTextField();
        txtramo = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabelLocal = new javax.swing.JLabel();
        txtlocal = new javax.swing.JTextField();
        jLabelBi = new javax.swing.JLabel();
        txtbi = new javax.swing.JTextField();
        jLabelTitulo = new javax.swing.JLabel();
        btngravar = new javax.swing.JButton();
        btnLimparRegisto = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        femenino = new javax.swing.JRadioButton();
        masculino = new javax.swing.JRadioButton();
        jLabelSexo = new javax.swing.JLabel();
        txtcontacto = new javax.swing.JTextField();
        jLabelContacto = new javax.swing.JLabel();
        horas = new javax.swing.JLabel();
        jCLetra = new javax.swing.JComboBox<>();
        lbl_Image = new javax.swing.JLabel();
        jbtnSelecaoFoto = new javax.swing.JButton();
        jLabel56 = new javax.swing.JLabel();
        jLabelNasDia = new javax.swing.JLabel();
        jComboBoxDiaNasc = new javax.swing.JComboBox<>();
        jLabelNasMes = new javax.swing.JLabel();
        jComboBoxMesNasc = new javax.swing.JComboBox<>();
        jLabelNasAno = new javax.swing.JLabel();
        jComboBoxAnosNasc = new javax.swing.JComboBox<>();
        jLabelEstadoCivil = new javax.swing.JLabel();
        jComboBoxEstadoCivil = new javax.swing.JComboBox<>();
        jLabelPai = new javax.swing.JLabel();
        txtpai = new javax.swing.JTextField();
        txtmae = new javax.swing.JTextField();
        jLabelMae = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabelProvinciaBI = new javax.swing.JLabel();
        jLabelDistritoBI = new javax.swing.JLabel();
        txtDistrito = new javax.swing.JTextField();
        txtLocalidade = new javax.swing.JTextField();
        jComboBoxProvincia = new javax.swing.JComboBox<>();
        jLabelLocalidadeBI = new javax.swing.JLabel();
        jPanelFuncioanrio = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        Activo = new javax.swing.JCheckBox();
        Contratado = new javax.swing.JCheckBox();
        Reformado = new javax.swing.JCheckBox();
        jPanelMilitar = new javax.swing.JPanel();
        jLabelPatente = new javax.swing.JLabel();
        jCheckBoxPatente = new javax.swing.JCheckBox();
        jCheckBoxQuadro = new javax.swing.JCheckBox();
        jCheckBoxReserva = new javax.swing.JCheckBox();
        jCheckBoxPensionista = new javax.swing.JCheckBox();
        patenteMilitar = new javax.swing.JTextField();
        jCheckBoxReforma = new javax.swing.JCheckBox();
        datanasc = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        jLabelEOT = new javax.swing.JLabel();
        txtEntidadeOndeTrabalho = new javax.swing.JTextField();
        jLabelLtrabalha = new javax.swing.JLabel();
        txtLocalTrabalho = new javax.swing.JTextField();
        jLabelProfissao = new javax.swing.JLabel();
        txtProfissao = new javax.swing.JTextField();
        jComboBoxMesNascIA = new javax.swing.JComboBox<>();
        jComboBoxDiaNascIA = new javax.swing.JComboBox<>();
        jLabelDIdia = new javax.swing.JLabel();
        jLabelDImes = new javax.swing.JLabel();
        jLabelDIAno = new javax.swing.JLabel();
        jComboBoxAnoIcorporacao = new javax.swing.JComboBox<>();
        jComboBoxPais = new javax.swing.JComboBox<>();
        AlertPrimarykey = new javax.swing.JLabel();
        AlertPrimaryKeyBI = new javax.swing.JLabel();
        jCheckBoxBairro = new javax.swing.JCheckBox();
        jPanelBairro = new javax.swing.JPanel();
        jLabelEnderencoBairro = new javax.swing.JLabel();
        txtmorada = new javax.swing.JTextField();
        txtbairro = new javax.swing.JTextField();
        jLabelBairro = new javax.swing.JLabel();
        txtBlocalidade = new javax.swing.JTextField();
        jLabelBlocalidade = new javax.swing.JLabel();
        jLabelBdistrito = new javax.swing.JLabel();
        txtBdistrito = new javax.swing.JTextField();
        jLabelBprovincia = new javax.swing.JLabel();
        jComboBoxBProvincia = new javax.swing.JComboBox<>();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabelMsgErro = new javax.swing.JLabel();
        jcBaseDados = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jLabelInvalidoLetraBI = new javax.swing.JLabel();
        login = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        txtuser = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtsenha = new javax.swing.JPasswordField();
        btnacesso = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jSeparator15 = new javax.swing.JSeparator();
        jSeparator16 = new javax.swing.JSeparator();
        SaticacaoBenifi = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel40 = new javax.swing.JLabel();
        jComboBoxBenificaio = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextdescricao = new javax.swing.JTextArea();
        jComboOperador = new javax.swing.JComboBox<>();
        jLabel41 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        txtcodigoAF = new javax.swing.JTextField();
        jLabelTitleCodigo = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        NomeAf = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabelTitleCodigo1 = new javax.swing.JLabel();
        AgregadoFamiliar = new javax.swing.JPanel();
        jLabel88 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel28 = new javax.swing.JLabel();
        txtbibeneficiario = new javax.swing.JTextField();
        jLabelGrauParentesco = new javax.swing.JLabel();
        jCBoxGraudeparentesco = new javax.swing.JComboBox<>();
        femeninoA = new javax.swing.JRadioButton();
        masculinoA = new javax.swing.JRadioButton();
        jLabelBsexoAF = new javax.swing.JLabel();
        jLabelBapleidoAF = new javax.swing.JLabel();
        txtapelidofamiliar = new javax.swing.JTextField();
        btnAgregadoFamiliar = new javax.swing.JButton();
        jLabelBnomeAF = new javax.swing.JLabel();
        txtnomebenificiario = new javax.swing.JTextField();
        VerAgregado = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jSlistaF = new javax.swing.JScrollPane();
        ListaFamilarUnicaPessoa = new javax.swing.JList<>();
        txtcodigo = new javax.swing.JTextField();
        jLabelBcodigoAF = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jComboBoxDiaNascAF = new javax.swing.JComboBox<>();
        jLabelBdiaAF = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jComboBoxMesNascAF = new javax.swing.JComboBox<>();
        jLabelBmesAF = new javax.swing.JLabel();
        jLabelBanoAF = new javax.swing.JLabel();
        jComboBoxAnosNascAF = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        lblShowbenificiario = new javax.swing.JLabel();
        lblShowAFcontacto = new javax.swing.JLabel();
        lblShowbenificiario1 = new javax.swing.JLabel();
        jLabelnome = new javax.swing.JLabel();
        jLabelcontacto = new javax.swing.JLabel();
        jLabelbi = new javax.swing.JLabel();
        lblShowAFquantidade = new javax.swing.JLabel();
        lentraBi = new javax.swing.JLabel();
        jLNF = new javax.swing.JLabel();
        AgregadoFamiliarfoto = new javax.swing.JLabel();
        BIAF = new javax.swing.JLabel();
        ocupacao = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListHerdeiros = new javax.swing.JList<>();
        jLabel43 = new javax.swing.JLabel();
        jSeparator20 = new javax.swing.JSeparator();
        jPanel11 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        AlertPrimaryKeyCodigo = new javax.swing.JLabel();
        jLabelAlertPrimaryKey = new javax.swing.JLabel();
        jCheckBoxAdicionarHer = new javax.swing.JCheckBox();
        ListaPainel = new javax.swing.JPanel();
        jLabel86 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        benificiarioLista = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableBenificiario = new javax.swing.JTable();
        TotalListaBenificiario = new javax.swing.JLabel();
        VerAgregado1Lista = new javax.swing.JLabel();
        jTextFieldPesquisarB = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jLabelVazioB = new javax.swing.JLabel();
        jButtonApagarBenifficario = new javax.swing.JButton();
        jLabelApagarBenificiario = new javax.swing.JLabel();
        jComboBoxPesquiarBINome = new javax.swing.JComboBox<>();
        agregadoFamiliarLista = new javax.swing.JPanel();
        jLabelAf = new javax.swing.JLabel();
        totalAgregado = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableAgredadoFamiliar = new javax.swing.JTable();
        jTextFieldPesquisarAFL = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jLabelVazioLAF = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jComboBoxAF = new javax.swing.JComboBox<>();
        jButtonApagarAF = new javax.swing.JButton();
        jPanelSatisfacaoBeni = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableSatisfacao = new javax.swing.JTable();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel54 = new javax.swing.JLabel();
        jLabelValor = new javax.swing.JLabel();
        jTextFieldPesquisaNomeSB = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jLabelVazioSB = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jButtonApagarSB = new javax.swing.JButton();
        jLabelApagarDI = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jPanelOrganizacao = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jSeparator27 = new javax.swing.JSeparator();
        jComboBoxTrabalho = new javax.swing.JComboBox<>();
        jPanel12 = new javax.swing.JPanel();
        jPanelPaginaTrabalhoInfo = new javax.swing.JPanel();
        jScrollPaneMilitar = new javax.swing.JScrollPane();
        jTableLocalTrabalho = new javax.swing.JTable();
        jScrollPaneFuncionario = new javax.swing.JScrollPane();
        jTableFuncionario = new javax.swing.JTable();
        jLabelListaTrabalho = new javax.swing.JLabel();
        jLabelSaveLocalTrabalho = new javax.swing.JLabel();
        jLabelTotalTrabalhoMilitarunico = new javax.swing.JLabel();
        jTextFieldPesquisarLLT = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jLabelBiFotoSave = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jPanelQuotizacao = new javax.swing.JPanel();
        jSeparator28 = new javax.swing.JSeparator();
        jLabel44 = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        jLabel130 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        txtvalorQuota = new javax.swing.JTextField();
        jComboBoxMesQuota = new javax.swing.JComboBox<>();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jButtonPagarQuota = new javax.swing.JButton();
        jComboBoxMesQuota1 = new javax.swing.JComboBox<>();
        jLabel60 = new javax.swing.JLabel();
        txtBI = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jPanelJoia = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableQuotizacao = new javax.swing.JTable();
        jPanelQuota = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableJoia = new javax.swing.JTable();
        jComboBoxAnoQuota2 = new javax.swing.JComboBox<>();
        jCheckBoxPagarJoia = new javax.swing.JCheckBox();
        jLabel61 = new javax.swing.JLabel();
        jTextFieldValorJoia = new javax.swing.JTextField();
        jLabelBTNPagar = new javax.swing.JLabel();
        VerAgregado1 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabelNomeQuo = new javax.swing.JLabel();
        jLabelJoiaPAga = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabelFotoQuota = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabelBIQuot = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabelContactoQuot = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        Quota = new javax.swing.JRadioButton();
        jRadioJoia = new javax.swing.JRadioButton();
        jTextFieldPesquisarJQ = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jLabelTituloQ1 = new javax.swing.JLabel();
        jLabelEmpty = new javax.swing.JLabel();
        AdministradorPanel = new javax.swing.JPanel();
        jLabelAdminTitulo = new javax.swing.JLabel();
        jLabelAdminIcon = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        TotalMasculinoBenifi = new javax.swing.JLabel();
        TotalFemeninoBenif = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        TotalFemenino = new javax.swing.JLabel();
        TotalMasculinoAF = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabelTotalSolteriro = new javax.swing.JLabel();
        jLabelTotalSolteriro1 = new javax.swing.JLabel();
        jLabelTotalDivorciado = new javax.swing.JLabel();
        jSeparator22 = new javax.swing.JSeparator();
        jSeparator23 = new javax.swing.JSeparator();
        jLabel82 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabelTotalMilitar = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabelTotalQuadroPensionista = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabelTotalReserva = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        jLabelTotalReforma = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        jLabelTotalPensionista = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        jLabelTotalFfuncionario = new javax.swing.JLabel();
        jLabelTotalFactivo = new javax.swing.JLabel();
        jLabel127 = new javax.swing.JLabel();
        jLabelTotalFContratado = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        jLabelTotalFreformado = new javax.swing.JLabel();
        jSeparator24 = new javax.swing.JSeparator();
        TAagregadoFamiliar = new javax.swing.JLabel();
        TAbenificiario = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator25 = new javax.swing.JSeparator();
        lblTotalAgF1 = new javax.swing.JLabel();
        jSeparator26 = new javax.swing.JSeparator();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jLabelBeneficio = new javax.swing.JLabel();
        jLabelMultTotalASatisfacao = new javax.swing.JLabel();
        lblTotalAgF = new javax.swing.JLabel();
        jLabelTotalSBenf = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jSeparator29 = new javax.swing.JSeparator();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jBApagarTodo = new javax.swing.JButton();
        jCheckDelete = new javax.swing.JCheckBox();
        jCheckDeleteAF = new javax.swing.JCheckBox();
        jLabel87 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        loadingbar = new javax.swing.JProgressBar();
        jProgressBarF = new javax.swing.JProgressBar();
        percentagemM = new javax.swing.JLabel();
        percentagemF = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel95 = new javax.swing.JLabel();
        jSeparator17 = new javax.swing.JSeparator();
        jSeparator18 = new javax.swing.JSeparator();
        jSeparator19 = new javax.swing.JSeparator();
        jSeparator21 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Serviços Sociais das Forças Armadas de Desefa de Moçambique");
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel2.setBackground(new java.awt.Color(0, 153, 51));

        jLabel9.setFont(new java.awt.Font("Sylfaen", 1, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("SSFADM");

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));

        PaginaIn.setBackground(new java.awt.Color(0, 153, 51));
        PaginaIn.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        PaginaIn.setForeground(new java.awt.Color(255, 255, 255));
        PaginaIn.setText("Pagina Inicial");
        PaginaIn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PaginaIn.setOpaque(true);
        PaginaIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PaginaInMouseClicked(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-casinha-de-cachorro-filled-25 (1).png"))); // NOI18N

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-salvar-como-filled-30.png"))); // NOI18N

        lblregisto.setBackground(new java.awt.Color(0, 153, 51));
        lblregisto.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        lblregisto.setForeground(new java.awt.Color(255, 255, 255));
        lblregisto.setText("Inscrição Beneficiário");
        lblregisto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblregisto.setOpaque(true);
        lblregisto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblregistoMouseClicked(evt);
            }
        });

        lblgrouparentesco.setBackground(new java.awt.Color(0, 153, 51));
        lblgrouparentesco.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        lblgrouparentesco.setForeground(new java.awt.Color(255, 255, 255));
        lblgrouparentesco.setText("Agregado Familiar");
        lblgrouparentesco.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblgrouparentesco.setOpaque(true);
        lblgrouparentesco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblgrouparentescoMouseClicked(evt);
            }
        });

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-responsável-legal-filled-30.png"))); // NOI18N

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-enviar-lista-quente-filled-35.png"))); // NOI18N

        lblListaMenu.setBackground(new java.awt.Color(0, 153, 51));
        lblListaMenu.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        lblListaMenu.setForeground(new java.awt.Color(255, 255, 255));
        lblListaMenu.setText("Lista ");
        lblListaMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblListaMenu.setOpaque(true);
        lblListaMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblListaMenuMouseClicked(evt);
            }
        });

        lblMenuAdminitrador.setBackground(new java.awt.Color(0, 153, 51));
        lblMenuAdminitrador.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        lblMenuAdminitrador.setForeground(new java.awt.Color(255, 255, 255));
        lblMenuAdminitrador.setText("Administrador");
        lblMenuAdminitrador.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMenuAdminitrador.setOpaque(true);
        lblMenuAdminitrador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMenuAdminitradorMouseClicked(evt);
            }
        });

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-settings-filled-35.png"))); // NOI18N

        lblSatisfacaoBenificio.setBackground(new java.awt.Color(0, 153, 51));
        lblSatisfacaoBenificio.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        lblSatisfacaoBenificio.setForeground(new java.awt.Color(255, 255, 255));
        lblSatisfacaoBenificio.setText("Satisfaç. Benificiário");
        lblSatisfacaoBenificio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSatisfacaoBenificio.setOpaque(true);
        lblSatisfacaoBenificio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSatisfacaoBenificioMouseClicked(evt);
            }
        });

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-cesto-de-compras-2-40 (1).png"))); // NOI18N

        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel55.setText("Serviços Socias das Forças Armadas");

        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel63.setText(" De Defesa De Moçambique");

        jLabelShowFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelShowFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabeNomeFoto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabeNomeFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        data.setForeground(new java.awt.Color(255, 255, 255));
        data.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblMenuQuatizacao.setBackground(new java.awt.Color(0, 153, 51));
        lblMenuQuatizacao.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        lblMenuQuatizacao.setForeground(new java.awt.Color(255, 255, 255));
        lblMenuQuatizacao.setText("Quotização");
        lblMenuQuatizacao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMenuQuatizacao.setOpaque(true);
        lblMenuQuatizacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMenuQuatizacaoMouseClicked(evt);
            }
        });

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-hipoteca-40.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblregisto))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(PaginaIn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel25)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblListaMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel24)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblgrouparentesco, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblSatisfacaoBenificio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel36)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblMenuQuatizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel35)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lblMenuAdminitrador, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabelShowFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
            .addComponent(jLabeNomeFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel55)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel63)
                .addGap(3, 3, 3)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PaginaIn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(lblregisto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblgrouparentesco, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblListaMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSatisfacaoBenificio, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMenuQuatizacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMenuAdminitrador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addComponent(jLabelShowFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabeNomeFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 250, 730);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.CardLayout());

        PaginaInicial.setBackground(new java.awt.Color(255, 255, 255));

        jLabel19.setFont(new java.awt.Font("Sylfaen", 1, 30)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 153, 51));
        jLabel19.setText("Pesquisa:");

        jSeparator5.setForeground(new java.awt.Color(0, 153, 51));

        txtShownome.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N

        txtPnome.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N

        txtPramo.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N

        txtShowramo.setFont(new java.awt.Font("Sylfaen", 1, 17)); // NOI18N

        txtShowunidade.setFont(new java.awt.Font("Sylfaen", 1, 17)); // NOI18N

        txtPunidade.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N

        txtPbi.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N

        txtShowbi.setFont(new java.awt.Font("Sylfaen", 1, 17)); // NOI18N

        txtShowcontacto.setFont(new java.awt.Font("Sylfaen", 1, 17)); // NOI18N

        txtPcontacto.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N

        txtPsexo.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N

        txtShowsexo.setFont(new java.awt.Font("Sylfaen", 1, 17)); // NOI18N

        txtShowhora.setFont(new java.awt.Font("Sylfaen", 1, 17)); // NOI18N

        txtPhora.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N

        txtPdata.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N

        txtShowdata.setFont(new java.awt.Font("Sylfaen", 1, 17)); // NOI18N

        txtTitle.setFont(new java.awt.Font("Sylfaen", 1, 20)); // NOI18N

        txtShowdataInscricao.setFont(new java.awt.Font("Sylfaen", 1, 17)); // NOI18N

        txtPdataIncricao.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N

        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-door-sensor-alarmed-filled-40.png"))); // NOI18N
        logout.setText("sair");
        logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
        });

        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel85.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-fechar-janela-35.png"))); // NOI18N
        jLabel85.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel85.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel85MouseClicked(evt);
            }
        });

        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel90.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-redimensionar-quatro-sentidos-20.png"))); // NOI18N
        jLabel90.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        jLabel90.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel90MouseDragged(evt);
            }
        });
        jLabel90.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel90MousePressed(evt);
            }
        });

        jLabel13.setText("1 - Não devem ser indicadas as particularidades de ligação A, E, DA, DE, DO, DAS, DOS.");

        jLabel17.setText("2 - Na morada, para economia de espaços, utilize as seguintes ABREVIATURAS:");

        jLabel102.setText("• AV – Avenida;");

        jLabel103.setText("• BL- Bloco;");

        jLabel104.setText("• BR – Bairro;");

        jLabel105.setText("• CV – Cave;");

        jLabel106.setText("• EST – Estrada;");

        jLabel107.setText("• LG- Largo;");

        jLabel108.setText("• PR – Praça;");

        jLabel109.setText("• R – Rua.");

        jLabel110.setText("• ROT – Rotunda;");

        jLabel111.setText("• PRT – Praceta;");

        jLabel112.setText("• TV – Travessa;");

        jLabel113.setText("•\tPQ – Parque;");

        jLabel114.setText("• VIV – Vivenda;");

        jLabel115.setForeground(new java.awt.Color(0, 153, 51));
        jLabel115.setText("OBSERVAÇÕES:");

        jLabel116.setForeground(new java.awt.Color(255, 0, 0));
        jLabel116.setText("3 -  Anexo fotocopias de:");

        jLabel117.setForeground(new java.awt.Color(255, 0, 0));
        jLabel117.setText("a. Certificado de Nascimento;");

        jLabel118.setForeground(new java.awt.Color(255, 0, 0));
        jLabel118.setText("b. Bilhete de Identidade Civil ou militar ou;");

        jLabel119.setForeground(new java.awt.Color(255, 0, 0));
        jLabel119.setText("c. Cédula Pessoal do Beneficiário.");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel17)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel102)
                                    .addComponent(jLabel103)
                                    .addComponent(jLabel104)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel106, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel105, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel107)
                                    .addComponent(jLabel114))
                                .addGap(71, 71, 71)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel111)
                                    .addComponent(jLabel110)
                                    .addComponent(jLabel109)
                                    .addComponent(jLabel108)
                                    .addComponent(jLabel112)
                                    .addComponent(jLabel113)))
                            .addComponent(jLabel115)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel118, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel117)
                            .addComponent(jLabel119, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel116)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel115)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel102)
                    .addComponent(jLabel108))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel103)
                    .addComponent(jLabel109))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel104)
                    .addComponent(jLabel110))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel105)
                    .addComponent(jLabel111))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel106)
                    .addComponent(jLabel113))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel107)
                    .addComponent(jLabel112))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel114)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel116)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel117)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel118)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel119)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel120.setForeground(new java.awt.Color(255, 0, 0));
        jLabel120.setText("IMPORTANCIA:");

        jLabel121.setForeground(new java.awt.Color(255, 0, 0));
        jLabel121.setText("A Concessão de benifício fica dependente da Inscrição do Beneficiário ");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel121))
                    .addComponent(jLabel120))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel120)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel121)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        txtPesBI.setBackground(new java.awt.Color(240, 240, 240));
        txtPesBI.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        txtPesBI.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPesBI.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));
        txtPesBI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesBIActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 153, 51));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Pesquisar");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel122.setText("<<<<  Inseira BI do Benificiario");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtPesBI, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel122)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel122)
                .addComponent(txtPesBI))
        );

        javax.swing.GroupLayout PaginaInicialLayout = new javax.swing.GroupLayout(PaginaInicial);
        PaginaInicial.setLayout(PaginaInicialLayout);
        PaginaInicialLayout.setHorizontalGroup(
            PaginaInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaginaInicialLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(logout)
                .addGap(35, 35, 35)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel85)
                .addGap(23, 23, 23))
            .addGroup(PaginaInicialLayout.createSequentialGroup()
                .addComponent(jSeparator5)
                .addContainerGap())
            .addGroup(PaginaInicialLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PaginaInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PaginaInicialLayout.createSequentialGroup()
                        .addComponent(txtShowdata, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPdata)
                        .addGap(163, 163, 163))
                    .addGroup(PaginaInicialLayout.createSequentialGroup()
                        .addGroup(PaginaInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PaginaInicialLayout.createSequentialGroup()
                                .addComponent(txtShowunidade, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPunidade))
                            .addComponent(txtTitle)
                            .addGroup(PaginaInicialLayout.createSequentialGroup()
                                .addComponent(txtShowramo, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPramo))
                            .addGroup(PaginaInicialLayout.createSequentialGroup()
                                .addComponent(txtShownome, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPnome)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(PaginaInicialLayout.createSequentialGroup()
                .addGroup(PaginaInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PaginaInicialLayout.createSequentialGroup()
                        .addComponent(txtShowsexo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPsexo))
                    .addGroup(PaginaInicialLayout.createSequentialGroup()
                        .addComponent(txtShowhora, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPhora))
                    .addGroup(PaginaInicialLayout.createSequentialGroup()
                        .addComponent(txtShowdataInscricao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPdataIncricao))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PaginaInicialLayout.createSequentialGroup()
                        .addComponent(txtShowbi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPbi))
                    .addGroup(PaginaInicialLayout.createSequentialGroup()
                        .addComponent(txtShowcontacto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPcontacto)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PaginaInicialLayout.setVerticalGroup(
            PaginaInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaginaInicialLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PaginaInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaginaInicialLayout.createSequentialGroup()
                        .addGroup(PaginaInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12))
                    .addComponent(logout, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PaginaInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtShownome, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPnome, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PaginaInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtShowramo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPramo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PaginaInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtShowunidade, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPunidade, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PaginaInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtShowbi, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPbi, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PaginaInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtShowcontacto, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPcontacto, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PaginaInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtShowsexo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPsexo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(PaginaInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PaginaInicialLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PaginaInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtShowhora, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPhora, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PaginaInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtShowdata, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPdata, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PaginaInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtShowdataInscricao, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPdataIncricao, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaginaInicialLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))))
        );

        jPanel1.add(PaginaInicial, "card2");

        registo.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Sylfaen", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 51));
        jLabel1.setText("Boletim de Inscrição de Beneficiário");

        jSeparator4.setForeground(new java.awt.Color(0, 153, 51));

        jLabelNomeRegisto.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabelNomeRegisto.setText("Nome:");

        txtnome.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        txtnome.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtnome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));
        txtnome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnomeKeyReleased(evt);
            }
        });

        jLabelApelidoRegisto.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabelApelidoRegisto.setText("Apelido:");

        txtapelido.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        txtapelido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtapelido.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));
        txtapelido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtapelidoKeyReleased(evt);
            }
        });

        txtramo.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        txtramo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtramo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));

        jLabel14.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabel14.setText("Ramo das FADM:");

        jLabelLocal.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabelLocal.setText("Local:");

        txtlocal.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        txtlocal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtlocal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));
        txtlocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtlocalActionPerformed(evt);
            }
        });
        txtlocal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtlocalKeyReleased(evt);
            }
        });

        jLabelBi.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabelBi.setText("BI:");

        txtbi.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        txtbi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtbi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));
        txtbi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbiKeyReleased(evt);
            }
        });

        jLabelTitulo.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabelTitulo.setText("Data de Incorporação / Admissão:");

        btngravar.setBackground(new java.awt.Color(0, 153, 51));
        btngravar.setForeground(new java.awt.Color(255, 255, 255));
        btngravar.setText("Registar");
        btngravar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btngravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngravarActionPerformed(evt);
            }
        });

        btnLimparRegisto.setBackground(new java.awt.Color(0, 204, 204));
        btnLimparRegisto.setForeground(new java.awt.Color(255, 255, 255));
        btnLimparRegisto.setText("Limpar");
        btnLimparRegisto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimparRegisto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparRegistoActionPerformed(evt);
            }
        });

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-salvar-como-filled-40 (1).png"))); // NOI18N

        femenino.setBackground(new java.awt.Color(255, 255, 255));
        sexo.add(femenino);
        femenino.setText("Femenino");

        masculino.setBackground(new java.awt.Color(255, 255, 255));
        sexo.add(masculino);
        masculino.setText("Masculino");
        masculino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masculinoActionPerformed(evt);
            }
        });

        jLabelSexo.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabelSexo.setText("Sexo:");

        txtcontacto.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        txtcontacto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcontacto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));
        txtcontacto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcontactoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcontactoKeyTyped(evt);
            }
        });

        jLabelContacto.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabelContacto.setText("Contacto:");

        horas.setForeground(new java.awt.Color(255, 255, 255));

        jCLetra.setBackground(new java.awt.Color(51, 153, 0));
        jCLetra.setForeground(new java.awt.Color(255, 255, 255));
        jCLetra.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" }));
        jCLetra.setOpaque(false);

        lbl_Image.setBackground(new java.awt.Color(255, 255, 255));
        lbl_Image.setForeground(new java.awt.Color(0, 153, 51));
        lbl_Image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-administrador-masculino-100.png"))); // NOI18N
        lbl_Image.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Foto do Benificiário", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        lbl_Image.setOpaque(true);

        jbtnSelecaoFoto.setBackground(new java.awt.Color(255, 255, 255));
        jbtnSelecaoFoto.setForeground(new java.awt.Color(0, 153, 51));
        jbtnSelecaoFoto.setText("Selecionar a Foto");
        jbtnSelecaoFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));
        jbtnSelecaoFoto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnSelecaoFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSelecaoFotoActionPerformed(evt);
            }
        });

        jLabel56.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabel56.setText("Data de Nascimento:");

        jLabelNasDia.setText("Dia");

        jComboBoxDiaNasc.setBackground(new java.awt.Color(51, 153, 0));
        jComboBoxDiaNasc.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxDiaNasc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "----", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        jComboBoxDiaNasc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxDiaNascActionPerformed(evt);
            }
        });

        jLabelNasMes.setText("Mes");

        jComboBoxMesNasc.setBackground(new java.awt.Color(51, 153, 0));
        jComboBoxMesNasc.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxMesNasc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "----", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" }));
        jComboBoxMesNasc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMesNascActionPerformed(evt);
            }
        });

        jLabelNasAno.setText("Ano");

        jComboBoxAnosNasc.setBackground(new java.awt.Color(51, 153, 0));
        jComboBoxAnosNasc.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxAnosNasc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAnosNascActionPerformed(evt);
            }
        });

        jLabelEstadoCivil.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabelEstadoCivil.setText("Estado Civil:");

        jComboBoxEstadoCivil.setBackground(new java.awt.Color(51, 153, 0));
        jComboBoxEstadoCivil.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxEstadoCivil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "----", "Solteiro", "Casado", "Divorciado" }));
        jComboBoxEstadoCivil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxEstadoCivilActionPerformed(evt);
            }
        });

        jLabelPai.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabelPai.setText("Pai:");

        txtpai.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        txtpai.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtpai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));
        txtpai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpaiKeyReleased(evt);
            }
        });

        txtmae.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        txtmae.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtmae.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));
        txtmae.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtmaeKeyReleased(evt);
            }
        });

        jLabelMae.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabelMae.setText("Mãe:");

        jLabel64.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabel64.setText("País:");

        jLabelProvinciaBI.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabelProvinciaBI.setText("Província:");

        jLabelDistritoBI.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabelDistritoBI.setText("Distrito:");

        txtDistrito.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        txtDistrito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDistrito.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));
        txtDistrito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDistritoKeyReleased(evt);
            }
        });

        txtLocalidade.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        txtLocalidade.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLocalidade.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));
        txtLocalidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLocalidadeKeyReleased(evt);
            }
        });

        jComboBoxProvincia.setBackground(new java.awt.Color(51, 153, 0));
        jComboBoxProvincia.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxProvincia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "----", "Maputo (Matola)", "Maputo (cidade)", "Gaza", "Inhambane", "Sofala", "Manica", "Tete", "Zambézia", "Nampula", "Cabo Delgado", "Niassa" }));
        jComboBoxProvincia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxProvinciaActionPerformed(evt);
            }
        });

        jLabelLocalidadeBI.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabelLocalidadeBI.setText("Localidade:");

        jPanelFuncioanrio.setBackground(new java.awt.Color(0, 204, 204));

        jLabel69.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabel69.setText("Funcionário:");

        Activo.setBackground(new java.awt.Color(0, 204, 204));
        Activo.setText("Activo");
        Activo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActivoActionPerformed(evt);
            }
        });

        Contratado.setBackground(new java.awt.Color(0, 204, 204));
        Contratado.setText("Contratado");
        Contratado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContratadoActionPerformed(evt);
            }
        });

        Reformado.setBackground(new java.awt.Color(0, 204, 204));
        Reformado.setText("Reformado");
        Reformado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReformadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelFuncioanrioLayout = new javax.swing.GroupLayout(jPanelFuncioanrio);
        jPanelFuncioanrio.setLayout(jPanelFuncioanrioLayout);
        jPanelFuncioanrioLayout.setHorizontalGroup(
            jPanelFuncioanrioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFuncioanrioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFuncioanrioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Contratado)
                    .addComponent(jLabel69)
                    .addGroup(jPanelFuncioanrioLayout.createSequentialGroup()
                        .addComponent(Activo)
                        .addGap(18, 18, 18)
                        .addComponent(Reformado)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanelFuncioanrioLayout.setVerticalGroup(
            jPanelFuncioanrioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFuncioanrioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFuncioanrioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Activo)
                    .addComponent(Reformado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Contratado)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelMilitar.setBackground(new java.awt.Color(225, 220, 220));

        jLabelPatente.setText("Patente:");

        jCheckBoxPatente.setBackground(new java.awt.Color(225, 220, 220));
        jCheckBoxPatente.setText("Militar");
        jCheckBoxPatente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxPatenteMouseClicked(evt);
            }
        });
        jCheckBoxPatente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxPatenteActionPerformed(evt);
            }
        });

        jCheckBoxQuadro.setBackground(new java.awt.Color(225, 220, 220));
        jCheckBoxQuadro.setText("Quadro Permanente");
        jCheckBoxQuadro.setEnabled(false);
        jCheckBoxQuadro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxQuadroActionPerformed(evt);
            }
        });

        jCheckBoxReserva.setBackground(new java.awt.Color(225, 220, 220));
        jCheckBoxReserva.setText("Reserva");
        jCheckBoxReserva.setEnabled(false);
        jCheckBoxReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxReservaActionPerformed(evt);
            }
        });

        jCheckBoxPensionista.setBackground(new java.awt.Color(225, 220, 220));
        jCheckBoxPensionista.setText("Pensionista");
        jCheckBoxPensionista.setEnabled(false);
        jCheckBoxPensionista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxPensionistaActionPerformed(evt);
            }
        });

        patenteMilitar.setBackground(new java.awt.Color(225, 220, 220));
        patenteMilitar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        patenteMilitar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));
        patenteMilitar.setEnabled(false);
        patenteMilitar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                patenteMilitarMouseClicked(evt);
            }
        });
        patenteMilitar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                patenteMilitarKeyReleased(evt);
            }
        });

        jCheckBoxReforma.setBackground(new java.awt.Color(225, 220, 220));
        jCheckBoxReforma.setText("Reforma");
        jCheckBoxReforma.setEnabled(false);
        jCheckBoxReforma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxReformaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMilitarLayout = new javax.swing.GroupLayout(jPanelMilitar);
        jPanelMilitar.setLayout(jPanelMilitarLayout);
        jPanelMilitarLayout.setHorizontalGroup(
            jPanelMilitarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMilitarLayout.createSequentialGroup()
                .addGroup(jPanelMilitarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelMilitarLayout.createSequentialGroup()
                        .addComponent(jCheckBoxPatente, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelPatente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(patenteMilitar))
                    .addGroup(jPanelMilitarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelMilitarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelMilitarLayout.createSequentialGroup()
                                .addComponent(jCheckBoxQuadro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBoxReserva))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelMilitarLayout.createSequentialGroup()
                                .addComponent(jCheckBoxReforma)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBoxPensionista)))
                        .addGap(0, 30, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelMilitarLayout.setVerticalGroup(
            jPanelMilitarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMilitarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMilitarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxPatente)
                    .addComponent(patenteMilitar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPatente, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMilitarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxReserva)
                    .addComponent(jCheckBoxQuadro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelMilitarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxPensionista)
                    .addComponent(jCheckBoxReforma))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        datanasc.setForeground(new java.awt.Color(255, 255, 255));

        jLabel70.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabel70.setText("Email:");

        txtemail.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        txtemail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtemail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));
        txtemail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtemailKeyReleased(evt);
            }
        });

        jLabelEOT.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabelEOT.setText("Entidade Onde Trabalha:");

        txtEntidadeOndeTrabalho.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        txtEntidadeOndeTrabalho.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEntidadeOndeTrabalho.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));
        txtEntidadeOndeTrabalho.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEntidadeOndeTrabalhoKeyReleased(evt);
            }
        });

        jLabelLtrabalha.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabelLtrabalha.setText("Local de Trabalho:");

        txtLocalTrabalho.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        txtLocalTrabalho.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLocalTrabalho.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));
        txtLocalTrabalho.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLocalTrabalhoKeyReleased(evt);
            }
        });

        jLabelProfissao.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabelProfissao.setText("Profissão:");

        txtProfissao.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        txtProfissao.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtProfissao.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));
        txtProfissao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProfissaoKeyReleased(evt);
            }
        });

        jComboBoxMesNascIA.setBackground(new java.awt.Color(51, 153, 0));
        jComboBoxMesNascIA.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxMesNascIA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "----", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" }));
        jComboBoxMesNascIA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMesNascIAActionPerformed(evt);
            }
        });

        jComboBoxDiaNascIA.setBackground(new java.awt.Color(51, 153, 0));
        jComboBoxDiaNascIA.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxDiaNascIA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "----", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        jComboBoxDiaNascIA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxDiaNascIAActionPerformed(evt);
            }
        });

        jLabelDIdia.setText("Dia");

        jLabelDImes.setText("Mes");

        jLabelDIAno.setText("Ano");

        jComboBoxAnoIcorporacao.setBackground(new java.awt.Color(51, 153, 0));
        jComboBoxAnoIcorporacao.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxAnoIcorporacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAnoIcorporacaoActionPerformed(evt);
            }
        });

        jComboBoxPais.setBackground(new java.awt.Color(51, 153, 0));
        jComboBoxPais.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxPais.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Moçambique" }));

        AlertPrimarykey.setForeground(new java.awt.Color(255, 0, 0));

        AlertPrimaryKeyBI.setForeground(new java.awt.Color(255, 0, 0));

        jCheckBoxBairro.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxBairro.setText("Adicionar bairro");
        jCheckBoxBairro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxBairroMouseClicked(evt);
            }
        });
        jCheckBoxBairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxBairroActionPerformed(evt);
            }
        });

        jPanelBairro.setBackground(new java.awt.Color(255, 255, 255));
        jPanelBairro.setLayout(null);

        jLabelEnderencoBairro.setBackground(new java.awt.Color(204, 204, 204));
        jLabelEnderencoBairro.setFont(new java.awt.Font("Sylfaen", 1, 15)); // NOI18N
        jLabelEnderencoBairro.setForeground(new java.awt.Color(255, 255, 255));
        jLabelEnderencoBairro.setText("Endereço:");
        jPanelBairro.add(jLabelEnderencoBairro);
        jLabelEnderencoBairro.setBounds(40, 30, 80, 18);

        txtmorada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtmorada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtmorada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtmoradaKeyReleased(evt);
            }
        });
        jPanelBairro.add(txtmorada);
        txtmorada.setBounds(128, 30, 250, 20);

        txtbairro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtbairro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtbairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbairroActionPerformed(evt);
            }
        });
        txtbairro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbairroKeyReleased(evt);
            }
        });
        jPanelBairro.add(txtbairro);
        txtbairro.setBounds(104, 60, 270, 20);

        jLabelBairro.setBackground(new java.awt.Color(204, 204, 204));
        jLabelBairro.setFont(new java.awt.Font("Sylfaen", 1, 15)); // NOI18N
        jLabelBairro.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBairro.setText("Bairro:");
        jPanelBairro.add(jLabelBairro);
        jLabelBairro.setBounds(40, 60, 48, 21);

        txtBlocalidade.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBlocalidade.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtBlocalidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBlocalidadeKeyReleased(evt);
            }
        });
        jPanelBairro.add(txtBlocalidade);
        txtBlocalidade.setBounds(130, 90, 250, 20);

        jLabelBlocalidade.setBackground(new java.awt.Color(204, 204, 204));
        jLabelBlocalidade.setFont(new java.awt.Font("Sylfaen", 1, 15)); // NOI18N
        jLabelBlocalidade.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBlocalidade.setText("Localidade:");
        jPanelBairro.add(jLabelBlocalidade);
        jLabelBlocalidade.setBounds(40, 90, 81, 21);

        jLabelBdistrito.setBackground(new java.awt.Color(204, 204, 204));
        jLabelBdistrito.setFont(new java.awt.Font("Sylfaen", 1, 15)); // NOI18N
        jLabelBdistrito.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBdistrito.setText("Distrito:");
        jPanelBairro.add(jLabelBdistrito);
        jLabelBdistrito.setBounds(40, 120, 59, 20);

        txtBdistrito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBdistrito.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtBdistrito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBdistritoKeyReleased(evt);
            }
        });
        jPanelBairro.add(txtBdistrito);
        txtBdistrito.setBounds(113, 120, 270, 20);

        jLabelBprovincia.setBackground(new java.awt.Color(204, 204, 204));
        jLabelBprovincia.setFont(new java.awt.Font("Sylfaen", 1, 15)); // NOI18N
        jLabelBprovincia.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBprovincia.setText("Província:");
        jPanelBairro.add(jLabelBprovincia);
        jLabelBprovincia.setBounds(40, 150, 71, 21);

        jComboBoxBProvincia.setBackground(new java.awt.Color(51, 153, 0));
        jComboBoxBProvincia.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxBProvincia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "----", "Maputo (Matola)", "Maputo (cidade)", "Gaza", "Inhambane", "Sofala", "Manica", "Tete", "Zambézia", "Nampula", "Cabo Delgado", "Niassa" }));
        jComboBoxBProvincia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxBProvinciaActionPerformed(evt);
            }
        });
        jPanelBairro.add(jComboBoxBProvincia);
        jComboBoxBProvincia.setBounds(120, 150, 116, 20);

        jLabel83.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Untitles.png"))); // NOI18N
        jPanelBairro.add(jLabel83);
        jLabel83.setBounds(-10, 0, 450, 210);

        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel84.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-fechar-janela-35.png"))); // NOI18N
        jLabel84.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel84.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel84MouseClicked(evt);
            }
        });

        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-redimensionar-quatro-sentidos-20.png"))); // NOI18N
        jLabel33.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        jLabel33.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel33MouseDragged(evt);
            }
        });
        jLabel33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel33MousePressed(evt);
            }
        });

        jLabelMsgErro.setForeground(new java.awt.Color(255, 0, 0));

        jcBaseDados.setBackground(new java.awt.Color(0, 153, 0));
        jcBaseDados.setForeground(new java.awt.Color(255, 255, 255));

        jLabel20.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        jLabel20.setText("Base de Dados: ");

        jLabelInvalidoLetraBI.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout registoLayout = new javax.swing.GroupLayout(registo);
        registo.setLayout(registoLayout);
        registoLayout.setHorizontalGroup(
            registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(datanasc)
                .addGap(28, 28, 28)
                .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registoLayout.createSequentialGroup()
                        .addComponent(jSeparator4)
                        .addContainerGap())
                    .addGroup(registoLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(horas, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel84)
                        .addGap(37, 37, 37))))
            .addGroup(registoLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registoLayout.createSequentialGroup()
                        .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(registoLayout.createSequentialGroup()
                                .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(registoLayout.createSequentialGroup()
                                        .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtapelido, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabelApelidoRegisto))
                                        .addGap(18, 18, 18)
                                        .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelEstadoCivil)
                                            .addComponent(jComboBoxEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(registoLayout.createSequentialGroup()
                                                .addComponent(femenino)
                                                .addGap(2, 2, 2)
                                                .addComponent(masculino))
                                            .addComponent(jLabelSexo)))
                                    .addComponent(txtnome, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(registoLayout.createSequentialGroup()
                                        .addComponent(jLabelNomeRegisto)
                                        .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(registoLayout.createSequentialGroup()
                                                .addGap(203, 203, 203)
                                                .addComponent(jLabelNasDia)
                                                .addGap(4, 4, 4)
                                                .addComponent(jComboBoxDiaNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(4, 4, 4)
                                                .addComponent(jLabelNasMes)
                                                .addGap(4, 4, 4)
                                                .addComponent(jComboBoxMesNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(4, 4, 4)
                                                .addComponent(jLabelNasAno)
                                                .addGap(4, 4, 4)
                                                .addComponent(jComboBoxAnosNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(registoLayout.createSequentialGroup()
                                                .addGap(175, 175, 175)
                                                .addComponent(jLabel56))))
                                    .addGroup(registoLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jcBaseDados, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_Image, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registoLayout.createSequentialGroup()
                                        .addComponent(jbtnSelecaoFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29))))
                            .addGroup(registoLayout.createSequentialGroup()
                                .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtpai, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelPai))
                                .addGap(18, 18, 18)
                                .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelMae)
                                    .addComponent(txtmae, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(registoLayout.createSequentialGroup()
                                        .addComponent(jLabelBi)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(AlertPrimaryKeyBI)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabelInvalidoLetraBI))
                                    .addGroup(registoLayout.createSequentialGroup()
                                        .addComponent(txtbi, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jCLetra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(164, 185, Short.MAX_VALUE))
                            .addGroup(registoLayout.createSequentialGroup()
                                .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(registoLayout.createSequentialGroup()
                                        .addComponent(jLabel64)
                                        .addGap(122, 122, 122))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registoLayout.createSequentialGroup()
                                        .addComponent(jComboBoxPais, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBoxProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(registoLayout.createSequentialGroup()
                                        .addComponent(jLabelProvinciaBI)
                                        .addGap(49, 49, 49)
                                        .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabelDistritoBI))
                                        .addGap(18, 18, 18)
                                        .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelLocalidadeBI)
                                            .addComponent(txtLocalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBoxBairro)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(103, 103, 103))
                    .addGroup(registoLayout.createSequentialGroup()
                        .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(registoLayout.createSequentialGroup()
                                .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtemail)
                                    .addGroup(registoLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(registoLayout.createSequentialGroup()
                                                .addComponent(jLabelDIdia)
                                                .addGap(4, 4, 4)
                                                .addComponent(jComboBoxDiaNascIA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(4, 4, 4)
                                                .addComponent(jLabelDImes)
                                                .addGap(4, 4, 4)
                                                .addComponent(jComboBoxMesNascIA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(4, 4, 4)
                                                .addComponent(jLabelDIAno)
                                                .addGap(4, 4, 4)
                                                .addComponent(jComboBoxAnoIcorporacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(registoLayout.createSequentialGroup()
                                                .addComponent(btngravar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnLimparRegisto)))
                                        .addGap(0, 64, Short.MAX_VALUE))
                                    .addGroup(registoLayout.createSequentialGroup()
                                        .addComponent(jLabel70)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabelMsgErro)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(AlertPrimarykey)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelContacto)
                                    .addGroup(registoLayout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(txtcontacto, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(22, 22, 22)
                                .addComponent(jPanelBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(registoLayout.createSequentialGroup()
                                .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(registoLayout.createSequentialGroup()
                                        .addComponent(jLabelTitulo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelLocal)
                                            .addComponent(txtlocal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(registoLayout.createSequentialGroup()
                                        .addComponent(jPanelMilitar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanelFuncioanrio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEntidadeOndeTrabalho, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelEOT)
                                    .addComponent(txtLocalTrabalho, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelLtrabalha))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(registoLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabelProfissao))
                                    .addComponent(jLabel14)
                                    .addComponent(txtramo, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtProfissao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(95, Short.MAX_VALUE))))
        );
        registoLayout.setVerticalGroup(
            registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registoLayout.createSequentialGroup()
                .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registoLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(datanasc, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)
                            .addGroup(registoLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(horas, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel84, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcBaseDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(registoLayout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(txtnome, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(registoLayout.createSequentialGroup()
                                        .addComponent(jLabelNomeRegisto, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3))
                                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(registoLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabelNasDia))
                                    .addGroup(registoLayout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(jComboBoxDiaNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(registoLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabelNasMes))
                                    .addGroup(registoLayout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(jComboBoxMesNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(registoLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabelNasAno))
                                    .addGroup(registoLayout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(jComboBoxAnosNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, 18)
                        .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(registoLayout.createSequentialGroup()
                                .addComponent(jLabelApelidoRegisto, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(txtapelido, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(registoLayout.createSequentialGroup()
                                .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(registoLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jComboBoxEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(registoLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(femenino)
                                            .addComponent(masculino))))
                                .addGap(4, 4, 4))))
                    .addGroup(registoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_Image, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnSelecaoFoto)))
                .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelPai, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, registoLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AlertPrimaryKeyBI, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelMae, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelBi, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelInvalidoLetraBI))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtpai, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmae, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtbi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCLetra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(registoLayout.createSequentialGroup()
                        .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(registoLayout.createSequentialGroup()
                            .addComponent(jLabelProvinciaBI, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBoxProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBoxPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registoLayout.createSequentialGroup()
                            .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelDistritoBI, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelLocalidadeBI, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtLocalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jCheckBoxBairro)))))
                .addGap(15, 15, 15)
                .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registoLayout.createSequentialGroup()
                        .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(registoLayout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtramo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelProfissao, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtProfissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(registoLayout.createSequentialGroup()
                                .addComponent(jLabelEOT, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEntidadeOndeTrabalho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelLtrabalha, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtLocalTrabalho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registoLayout.createSequentialGroup()
                        .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanelMilitar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelFuncioanrio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelMsgErro, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AlertPrimarykey, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcontacto, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(registoLayout.createSequentialGroup()
                                .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxDiaNascIA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxMesNascIA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxAnoIcorporacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(registoLayout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelDIdia)
                                            .addComponent(jLabelDImes)
                                            .addComponent(jLabelDIAno)))))
                            .addGroup(registoLayout.createSequentialGroup()
                                .addComponent(jLabelLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtlocal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addGroup(registoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btngravar)
                            .addComponent(btnLimparRegisto, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(68, 68, Short.MAX_VALUE))
        );

        jPanel1.add(registo, "card3");

        login.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setBackground(new java.awt.Color(0, 153, 102));
        jLabel7.setFont(new java.awt.Font("Sylfaen", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("LOGIN SSFADM:");

        jSeparator1.setBackground(new java.awt.Color(0, 153, 102));
        jSeparator1.setForeground(new java.awt.Color(0, 153, 102));

        jLabel3.setFont(new java.awt.Font("Sylfaen", 0, 20)); // NOI18N
        jLabel3.setText("Nome:");

        txtuser.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        txtuser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtuser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 102)));
        txtuser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtuserMouseClicked(evt);
            }
        });
        txtuser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtuserActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Sylfaen", 0, 20)); // NOI18N
        jLabel6.setText("Senha:");

        txtsenha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtsenha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtsenha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 102)));
        txtsenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtsenhaMouseClicked(evt);
            }
        });

        btnacesso.setBackground(new java.awt.Color(0, 153, 51));
        btnacesso.setForeground(new java.awt.Color(255, 255, 255));
        btnacesso.setText("Acessar");
        btnacesso.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnacesso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnacessoActionPerformed(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(0, 153, 102));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-administrador-masculino-filled-30.png"))); // NOI18N

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-cadeado-2-30.png"))); // NOI18N

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-visível-30.png"))); // NOI18N
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jSeparator15.setForeground(new java.awt.Color(0, 153, 51));

        jSeparator16.setBackground(new java.awt.Color(204, 204, 204));
        jSeparator16.setForeground(new java.awt.Color(0, 153, 51));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginLayout.createSequentialGroup()
                .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginLayout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(loginLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 914, Short.MAX_VALUE)
                            .addComponent(jSeparator15))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(loginLayout.createSequentialGroup()
                .addContainerGap(310, Short.MAX_VALUE)
                .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(loginLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtuser, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(loginLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(loginLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtsenha, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)))
                            .addGroup(loginLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(btnacesso, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1)
                .addGap(428, 428, 428))
            .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(loginLayout.createSequentialGroup()
                    .addGap(151, 151, 151)
                    .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel7)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(381, Short.MAX_VALUE)))
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginLayout.createSequentialGroup()
                .addContainerGap(173, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtuser, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtsenha, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14)
                .addComponent(btnacesso, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(165, 165, 165)
                .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95))
            .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(loginLayout.createSequentialGroup()
                    .addGap(85, 85, 85)
                    .addComponent(jLabel7)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(551, Short.MAX_VALUE)))
        );

        jPanel1.add(login, "card4");

        SaticacaoBenifi.setBackground(new java.awt.Color(255, 255, 255));

        jLabel38.setFont(new java.awt.Font("Sylfaen", 1, 30)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 153, 51));
        jLabel38.setText("Satisfação de Benificiário ");

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-cesto-de-compras-2-40 (2).png"))); // NOI18N

        jSeparator6.setForeground(new java.awt.Color(0, 153, 51));

        jLabel40.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabel40.setText("Descrição:");

        jComboBoxBenificaio.setBackground(new java.awt.Color(0, 153, 51));
        jComboBoxBenificaio.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxBenificaio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "------------------", "Oculos ", "Subsidio de Luto", "Subsidio de Estudos", "Emprestimo", "Outros" }));
        jComboBoxBenificaio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxBenificaioActionPerformed(evt);
            }
        });

        jTextdescricao.setColumns(20);
        jTextdescricao.setRows(5);
        jScrollPane2.setViewportView(jTextdescricao);

        jComboOperador.setBackground(new java.awt.Color(0, 153, 153));
        jComboOperador.setForeground(new java.awt.Color(255, 255, 255));
        jComboOperador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "------------------", "OsvaldoGuambe", " " }));

        jLabel41.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabel41.setText("Operador:");

        jButton7.setBackground(new java.awt.Color(51, 204, 0));
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Registar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(0, 204, 255));
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Limpar");

        jLabel42.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabel42.setText("Benificio:");

        txtcodigoAF.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        txtcodigoAF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcodigoAF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));
        txtcodigoAF.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtcodigoAF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcodigoAFKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcodigoAFKeyTyped(evt);
            }
        });

        jLabelTitleCodigo.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabelTitleCodigo.setText("Codigo do Agre. Familiar:");

        txtValor.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        txtValor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtValor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));
        txtValor.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel53.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabel53.setText("Valor:");

        NomeAf.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        NomeAf.setForeground(new java.awt.Color(0, 153, 51));

        jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel89.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-fechar-janela-35.png"))); // NOI18N
        jLabel89.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel89.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel89MouseClicked(evt);
            }
        });

        jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel91.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-redimensionar-quatro-sentidos-20.png"))); // NOI18N
        jLabel91.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        jLabel91.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel91MouseDragged(evt);
            }
        });
        jLabel91.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel91MousePressed(evt);
            }
        });

        jLabel101.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        jLabel101.setText("Metical");

        jComboBox1.setBackground(new java.awt.Color(0, 153, 51));
        jComboBox1.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---------------------", "Benificiário", "Agregado Familiar" }));

        jLabelTitleCodigo1.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabelTitleCodigo1.setText("Escolha o Grau");

        javax.swing.GroupLayout SaticacaoBenifiLayout = new javax.swing.GroupLayout(SaticacaoBenifi);
        SaticacaoBenifi.setLayout(SaticacaoBenifiLayout);
        SaticacaoBenifiLayout.setHorizontalGroup(
            SaticacaoBenifiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SaticacaoBenifiLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(SaticacaoBenifiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SaticacaoBenifiLayout.createSequentialGroup()
                        .addGroup(SaticacaoBenifiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator6)
                            .addGroup(SaticacaoBenifiLayout.createSequentialGroup()
                                .addGroup(SaticacaoBenifiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelTitleCodigo)
                                    .addComponent(jLabel42)
                                    .addComponent(jComboBoxBenificaio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 770, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(SaticacaoBenifiLayout.createSequentialGroup()
                        .addComponent(jLabel39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel89)
                        .addGap(39, 39, 39))
                    .addGroup(SaticacaoBenifiLayout.createSequentialGroup()
                        .addGroup(SaticacaoBenifiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTitleCodigo1)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(SaticacaoBenifiLayout.createSequentialGroup()
                                .addComponent(jButton7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton8))
                            .addComponent(jLabel41)
                            .addComponent(jLabel40)
                            .addComponent(jComboOperador, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(SaticacaoBenifiLayout.createSequentialGroup()
                                .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel53)
                            .addComponent(NomeAf)
                            .addComponent(txtcodigoAF, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        SaticacaoBenifiLayout.setVerticalGroup(
            SaticacaoBenifiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SaticacaoBenifiLayout.createSequentialGroup()
                .addGroup(SaticacaoBenifiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SaticacaoBenifiLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(SaticacaoBenifiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(SaticacaoBenifiLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(SaticacaoBenifiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel89))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jLabelTitleCodigo1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(NomeAf, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelTitleCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(txtcodigoAF, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxBenificaio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(SaticacaoBenifiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel101, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboOperador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(SaticacaoBenifiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addContainerGap(105, Short.MAX_VALUE))
        );

        jPanel1.add(SaticacaoBenifi, "card7");

        AgregadoFamiliar.setBackground(new java.awt.Color(255, 255, 255));
        AgregadoFamiliar.setForeground(new java.awt.Color(0, 153, 51));
        AgregadoFamiliar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel88.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-fechar-janela-35.png"))); // NOI18N
        jLabel88.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel88.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel88MouseClicked(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Sylfaen", 1, 30)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 153, 51));
        jLabel26.setText("Agregado Familiar");

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-salvar-como-filled-40 (1).png"))); // NOI18N

        jSeparator7.setForeground(new java.awt.Color(0, 153, 51));

        jLabel28.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabel28.setText("BI do Benificário:");

        txtbibeneficiario.setBackground(new java.awt.Color(0, 153, 0));
        txtbibeneficiario.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        txtbibeneficiario.setForeground(new java.awt.Color(255, 255, 255));
        txtbibeneficiario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtbibeneficiario.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 204), 1, true));
        txtbibeneficiario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabelGrauParentesco.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabelGrauParentesco.setText("Grau de Parentesco:");

        jCBoxGraudeparentesco.setBackground(new java.awt.Color(51, 153, 0));
        jCBoxGraudeparentesco.setForeground(new java.awt.Color(255, 255, 255));
        jCBoxGraudeparentesco.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "******", "Pai", "Mae", "Esposa", "Esposo", "Irmão", "Irmã", "Filho", "Filha", "Primo", "Prima", "Neto", "Neta", "Sobrino ", "Sobrinha", "Avô", "Avó", " ", " " }));
        jCBoxGraudeparentesco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBoxGraudeparentescoActionPerformed(evt);
            }
        });

        femeninoA.setBackground(new java.awt.Color(255, 255, 255));
        sexo.add(femeninoA);
        femeninoA.setText("Femenino");
        femeninoA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                femeninoAActionPerformed(evt);
            }
        });

        masculinoA.setBackground(new java.awt.Color(255, 255, 255));
        sexo.add(masculinoA);
        masculinoA.setText("Masculino");
        masculinoA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masculinoAActionPerformed(evt);
            }
        });

        jLabelBsexoAF.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabelBsexoAF.setText("Sexo:");

        jLabelBapleidoAF.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabelBapleidoAF.setText("Apelido:");

        txtapelidofamiliar.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        txtapelidofamiliar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtapelidofamiliar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));
        txtapelidofamiliar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtapelidofamiliarKeyReleased(evt);
            }
        });

        btnAgregadoFamiliar.setBackground(new java.awt.Color(0, 153, 51));
        btnAgregadoFamiliar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregadoFamiliar.setText("Gravar");
        btnAgregadoFamiliar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregadoFamiliar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregadoFamiliarActionPerformed(evt);
            }
        });

        jLabelBnomeAF.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabelBnomeAF.setText("Nome:");

        txtnomebenificiario.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        txtnomebenificiario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtnomebenificiario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));
        txtnomebenificiario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtnomebenificiario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnomebenificiarioKeyReleased(evt);
            }
        });

        VerAgregado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-visível-30.png"))); // NOI18N
        VerAgregado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        VerAgregado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VerAgregadoMouseClicked(evt);
            }
        });

        jSeparator8.setForeground(new java.awt.Color(0, 153, 51));

        jButton1.setBackground(new java.awt.Color(0, 204, 204));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Limpar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jSlistaF.setBackground(new java.awt.Color(0, 153, 51));
        jSlistaF.setOpaque(false);

        ListaFamilarUnicaPessoa.setBackground(new java.awt.Color(0, 204, 204));
        ListaFamilarUnicaPessoa.setForeground(new java.awt.Color(255, 255, 255));
        jSlistaF.setViewportView(ListaFamilarUnicaPessoa);

        txtcodigo.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        txtcodigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));
        txtcodigo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtcodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcodigoActionPerformed(evt);
            }
        });
        txtcodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcodigoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcodigoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcodigoKeyTyped(evt);
            }
        });

        jLabelBcodigoAF.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabelBcodigoAF.setText("Codigo:");

        jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel92.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-redimensionar-quatro-sentidos-20.png"))); // NOI18N
        jLabel92.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        jLabel92.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel92MouseDragged(evt);
            }
        });
        jLabel92.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel92MousePressed(evt);
            }
        });

        jComboBoxDiaNascAF.setBackground(new java.awt.Color(51, 153, 0));
        jComboBoxDiaNascAF.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxDiaNascAF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "----", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        jComboBoxDiaNascAF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxDiaNascAFActionPerformed(evt);
            }
        });

        jLabelBdiaAF.setText("Dia");

        jLabel98.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabel98.setText("Data de Nascimento:");

        jComboBoxMesNascAF.setBackground(new java.awt.Color(51, 153, 0));
        jComboBoxMesNascAF.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxMesNascAF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "----", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" }));
        jComboBoxMesNascAF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMesNascAFActionPerformed(evt);
            }
        });

        jLabelBmesAF.setText("Mes");

        jLabelBanoAF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBanoAF.setText("Ano");

        jComboBoxAnosNascAF.setBackground(new java.awt.Color(51, 153, 0));
        jComboBoxAnosNascAF.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxAnosNascAF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAnosNascAFActionPerformed(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        lblShowbenificiario.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N

        lblShowAFcontacto.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N

        lblShowbenificiario1.setFont(new java.awt.Font("Sylfaen", 1, 24)); // NOI18N
        lblShowbenificiario1.setForeground(new java.awt.Color(0, 153, 51));

        jLabelnome.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N

        jLabelcontacto.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N

        lblShowAFquantidade.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N

        jLNF.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N

        AgregadoFamiliarfoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AgregadoFamiliarfoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));

        BIAF.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N

        ocupacao.setBackground(new java.awt.Color(0, 153, 0));
        ocupacao.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        ocupacao.setForeground(new java.awt.Color(255, 255, 255));
        ocupacao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ocupacao.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));
        ocupacao.setOpaque(true);

        jListHerdeiros.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane3.setViewportView(jListHerdeiros);

        jLabel43.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        jLabel43.setText("Herderio:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabelnome, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblShowbenificiario, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblShowbenificiario1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(13, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLNF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(BIAF)
                                .addGap(5, 5, 5)
                                .addComponent(jLabelbi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lentraBi))
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabelcontacto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblShowAFcontacto))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblShowAFquantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ocupacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(AgregadoFamiliarfoto, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(156, 156, 156))))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblShowbenificiario1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelnome, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblShowbenificiario, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BIAF, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelbi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lentraBi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblShowAFcontacto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelcontacto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(AgregadoFamiliarfoto, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(ocupacao, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)))
                .addGap(6, 6, 6)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLNF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblShowAFquantidade, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jSeparator20.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("Nota:");

        jLabel15.setText("- Para cadastrar um membro familiar, é necessaria que tenha um benificiário registado. ");

        jLabel16.setForeground(new java.awt.Color(255, 0, 0));
        jLabel16.setText("- Lembre-se de criar um código de 6 digitos para o membro familiar.");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addGap(20, 20, 20))
        );

        AlertPrimaryKeyCodigo.setForeground(new java.awt.Color(255, 0, 0));

        jLabelAlertPrimaryKey.setForeground(new java.awt.Color(255, 0, 0));

        jCheckBoxAdicionarHer.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxAdicionarHer.setText("Adicionar Herdeiro");
        jCheckBoxAdicionarHer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxAdicionarHerMouseClicked(evt);
            }
        });
        jCheckBoxAdicionarHer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxAdicionarHerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AgregadoFamiliarLayout = new javax.swing.GroupLayout(AgregadoFamiliar);
        AgregadoFamiliar.setLayout(AgregadoFamiliarLayout);
        AgregadoFamiliarLayout.setHorizontalGroup(
            AgregadoFamiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AgregadoFamiliarLayout.createSequentialGroup()
                .addGap(422, 422, 422)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(AgregadoFamiliarLayout.createSequentialGroup()
                .addGroup(AgregadoFamiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AgregadoFamiliarLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel27)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(305, 305, 305)
                        .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel88))
                    .addGroup(AgregadoFamiliarLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(AgregadoFamiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 979, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(AgregadoFamiliarLayout.createSequentialGroup()
                                .addGroup(AgregadoFamiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28)
                                    .addGroup(AgregadoFamiliarLayout.createSequentialGroup()
                                        .addComponent(txtbibeneficiario, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(VerAgregado))
                                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelBnomeAF)
                                    .addComponent(txtnomebenificiario, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelBapleidoAF)
                                    .addComponent(txtapelidofamiliar, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(AgregadoFamiliarLayout.createSequentialGroup()
                                        .addComponent(jLabelGrauParentesco)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelBsexoAF))
                                    .addGroup(AgregadoFamiliarLayout.createSequentialGroup()
                                        .addComponent(jCBoxGraudeparentesco, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(68, 68, 68)
                                        .addComponent(femeninoA))
                                    .addGroup(AgregadoFamiliarLayout.createSequentialGroup()
                                        .addComponent(jLabelBcodigoAF)
                                        .addGap(108, 108, 108)
                                        .addComponent(masculinoA))
                                    .addComponent(jLabel98)
                                    .addGroup(AgregadoFamiliarLayout.createSequentialGroup()
                                        .addComponent(jLabelBdiaAF)
                                        .addGap(4, 4, 4)
                                        .addComponent(jComboBoxDiaNascAF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabelBmesAF)
                                        .addGap(4, 4, 4)
                                        .addComponent(jComboBoxMesNascAF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabelBanoAF, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBoxAnosNascAF, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(AgregadoFamiliarLayout.createSequentialGroup()
                                        .addComponent(btnAgregadoFamiliar)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jCheckBoxAdicionarHer))
                                    .addGroup(AgregadoFamiliarLayout.createSequentialGroup()
                                        .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(AlertPrimaryKeyCodigo)
                                        .addGap(16, 16, 16)
                                        .addComponent(jLabelAlertPrimaryKey)))
                                .addGap(9, 9, 9)
                                .addComponent(jSeparator20, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addGroup(AgregadoFamiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(AgregadoFamiliarLayout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jSlistaF, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(10, 10, 10))
        );
        AgregadoFamiliarLayout.setVerticalGroup(
            AgregadoFamiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AgregadoFamiliarLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(AgregadoFamiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AgregadoFamiliarLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel27))
                    .addGroup(AgregadoFamiliarLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel88))
                .addGap(6, 6, 6)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(AgregadoFamiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AgregadoFamiliarLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(AgregadoFamiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtbibeneficiario, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(AgregadoFamiliarLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(VerAgregado, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabelBnomeAF, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txtnomebenificiario, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jLabelBapleidoAF, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(txtapelidofamiliar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(AgregadoFamiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelGrauParentesco, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelBsexoAF, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(AgregadoFamiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCBoxGraudeparentesco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(AgregadoFamiliarLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(femeninoA)))
                        .addGap(8, 8, 8)
                        .addGroup(AgregadoFamiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelBcodigoAF, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(masculinoA))
                        .addGap(5, 5, 5)
                        .addGroup(AgregadoFamiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AgregadoFamiliarLayout.createSequentialGroup()
                                .addGroup(AgregadoFamiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelAlertPrimaryKey, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(11, 11, 11)
                                .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(AgregadoFamiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(AgregadoFamiliarLayout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(jLabelBdiaAF))
                                    .addGroup(AgregadoFamiliarLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jComboBoxDiaNascAF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(AgregadoFamiliarLayout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(jLabelBmesAF))
                                    .addGroup(AgregadoFamiliarLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(AgregadoFamiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(AgregadoFamiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabelBanoAF, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jComboBoxAnosNascAF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jComboBoxMesNascAF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addGroup(AgregadoFamiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAgregadoFamiliar)
                                    .addComponent(jButton1)
                                    .addComponent(jCheckBoxAdicionarHer)))
                            .addComponent(AlertPrimaryKeyCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator20, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(AgregadoFamiliarLayout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jSlistaF, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(AgregadoFamiliar, "card5");

        ListaPainel.setBackground(new java.awt.Color(255, 255, 255));
        ListaPainel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel86.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel86.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-fechar-janela-35.png"))); // NOI18N
        jLabel86.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel86.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel86MouseClicked(evt);
            }
        });
        ListaPainel.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(961, 11, -1, -1));

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setForeground(new java.awt.Color(0, 51, 51));
        jButton4.setText("Benificiario");
        jButton4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        ListaPainel.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 77, 28));

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setForeground(new java.awt.Color(0, 153, 51));
        jButton5.setText("Agregado Familiar");
        jButton5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        ListaPainel.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 131, 28));

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setForeground(new java.awt.Color(0, 204, 102));
        jButton6.setText("Satisfação do Beneficiário");
        jButton6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 102)));
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        ListaPainel.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, 179, 28));

        jSeparator9.setForeground(new java.awt.Color(0, 153, 51));
        ListaPainel.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 73, 991, 12));

        jPanel3.setBackground(new java.awt.Color(0, 153, 51));
        jPanel3.setLayout(new java.awt.CardLayout());

        benificiarioLista.setBackground(new java.awt.Color(0, 204, 204));
        benificiarioLista.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Sylfaen", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Lista Benificiário:");
        benificiarioLista.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 19, 199, 30));

        jSeparator10.setForeground(new java.awt.Color(255, 255, 255));
        benificiarioLista.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 55, 971, -1));

        jTableBenificiario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nº", "Nome", "Apelido", "Data de Nascemento", "Estado Civil", "Sexo", "BI", "Contacto", "Pai", "Mãe", "País", "Província", "Distrito", "Localidade", "Hora", "Data", "Apagar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true, true, true, true, true, true, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableBenificiario.setGridColor(new java.awt.Color(0, 204, 204));
        jTableBenificiario.setOpaque(false);
        jTableBenificiario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableBenificiarioMouseClicked(evt);
            }
        });
        jTableBenificiario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTableBenificiarioKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTableBenificiario);
        if (jTableBenificiario.getColumnModel().getColumnCount() > 0) {
            jTableBenificiario.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTableBenificiario.getColumnModel().getColumn(0).setMaxWidth(50);
            jTableBenificiario.getColumnModel().getColumn(6).setPreferredWidth(100);
            jTableBenificiario.getColumnModel().getColumn(8).setPreferredWidth(50);
            jTableBenificiario.getColumnModel().getColumn(14).setPreferredWidth(35);
            jTableBenificiario.getColumnModel().getColumn(16).setMaxWidth(55);
        }

        benificiarioLista.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 63, 971, 454));

        TotalListaBenificiario.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        TotalListaBenificiario.setForeground(new java.awt.Color(255, 255, 255));
        benificiarioLista.add(TotalListaBenificiario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 523, -1, 35));

        VerAgregado1Lista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-visível-40.png"))); // NOI18N
        VerAgregado1Lista.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        VerAgregado1Lista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VerAgregado1ListaMouseClicked(evt);
            }
        });
        benificiarioLista.add(VerAgregado1Lista, new org.netbeans.lib.awtextra.AbsoluteConstraints(926, 27, -1, 22));

        jTextFieldPesquisarB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldPesquisarB.setBorder(null);
        jTextFieldPesquisarB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldPesquisarBKeyReleased(evt);
            }
        });
        benificiarioLista.add(jTextFieldPesquisarB, new org.netbeans.lib.awtextra.AbsoluteConstraints(305, 21, 167, 23));

        jButton10.setBackground(new java.awt.Color(0, 153, 153));
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Pesquisar");
        jButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        benificiarioLista.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(482, 21, -1, -1));

        jLabelVazioB.setForeground(new java.awt.Color(255, 51, 0));
        benificiarioLista.add(jLabelVazioB, new org.netbeans.lib.awtextra.AbsoluteConstraints(575, 21, -1, 20));

        jButtonApagarBenifficario.setBackground(new java.awt.Color(255, 51, 0));
        jButtonApagarBenifficario.setForeground(new java.awt.Color(255, 255, 255));
        jButtonApagarBenifficario.setText("Apagar");
        jButtonApagarBenifficario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonApagarBenifficario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonApagarBenifficarioActionPerformed(evt);
            }
        });
        benificiarioLista.add(jButtonApagarBenifficario, new org.netbeans.lib.awtextra.AbsoluteConstraints(904, 523, -1, -1));

        jLabelApagarBenificiario.setForeground(new java.awt.Color(0, 204, 204));
        jLabelApagarBenificiario.setText("jLabel36");
        benificiarioLista.add(jLabelApagarBenificiario, new org.netbeans.lib.awtextra.AbsoluteConstraints(701, 532, -1, -1));

        jComboBoxPesquiarBINome.setBackground(new java.awt.Color(0, 153, 51));
        jComboBoxPesquiarBINome.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxPesquiarBINome.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione:", "Nome", "BI" }));
        jComboBoxPesquiarBINome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPesquiarBINomeActionPerformed(evt);
            }
        });
        benificiarioLista.add(jComboBoxPesquiarBINome, new org.netbeans.lib.awtextra.AbsoluteConstraints(213, 22, -1, 20));

        jPanel3.add(benificiarioLista, "card2");

        agregadoFamiliarLista.setBackground(new java.awt.Color(0, 204, 51));

        jLabelAf.setFont(new java.awt.Font("Sylfaen", 1, 24)); // NOI18N
        jLabelAf.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAf.setText("Lista Agregado Familiar:");

        totalAgregado.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        totalAgregado.setForeground(new java.awt.Color(255, 255, 255));

        jSeparator11.setForeground(new java.awt.Color(255, 255, 255));

        jTableAgredadoFamiliar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nº", "Código", "Nome", "Apelido", "Grau De Parentesco", "Sexo", "BI do Parente", "Parente", "Ocupação do Parente", "Data de Nascimento", "Data", "Apagar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableAgredadoFamiliar.setGridColor(new java.awt.Color(0, 153, 51));
        jTableAgredadoFamiliar.setOpaque(false);
        jTableAgredadoFamiliar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAgredadoFamiliarMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTableAgredadoFamiliar);
        if (jTableAgredadoFamiliar.getColumnModel().getColumnCount() > 0) {
            jTableAgredadoFamiliar.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTableAgredadoFamiliar.getColumnModel().getColumn(0).setMaxWidth(50);
            jTableAgredadoFamiliar.getColumnModel().getColumn(6).setPreferredWidth(100);
            jTableAgredadoFamiliar.getColumnModel().getColumn(11).setMaxWidth(50);
        }

        jTextFieldPesquisarAFL.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldPesquisarAFL.setBorder(null);
        jTextFieldPesquisarAFL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldPesquisarAFLKeyReleased(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(0, 153, 153));
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Pesquisar");
        jButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabelVazioLAF.setForeground(new java.awt.Color(204, 0, 0));

        jLabel34.setForeground(new java.awt.Color(0, 204, 51));
        jLabel34.setText("jLabel34");

        jComboBoxAF.setBackground(new java.awt.Color(0, 153, 51));
        jComboBoxAF.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxAF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione:", "Código do Agre. Familiar", "Nome do Agre. Familiar", "Nome do Benificiário" }));
        jComboBoxAF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAFActionPerformed(evt);
            }
        });

        jButtonApagarAF.setBackground(new java.awt.Color(255, 51, 0));
        jButtonApagarAF.setForeground(new java.awt.Color(255, 255, 255));
        jButtonApagarAF.setText("Apagar");
        jButtonApagarAF.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonApagarAF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonApagarAFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout agregadoFamiliarListaLayout = new javax.swing.GroupLayout(agregadoFamiliarLista);
        agregadoFamiliarLista.setLayout(agregadoFamiliarListaLayout);
        agregadoFamiliarListaLayout.setHorizontalGroup(
            agregadoFamiliarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(agregadoFamiliarListaLayout.createSequentialGroup()
                .addGroup(agregadoFamiliarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(agregadoFamiliarListaLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(totalAgregado)
                        .addGap(413, 413, 413)
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonApagarAF))
                    .addGroup(agregadoFamiliarListaLayout.createSequentialGroup()
                        .addGroup(agregadoFamiliarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(agregadoFamiliarListaLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabelAf, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jComboBoxAF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(jTextFieldPesquisarAFL, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jButton11))
                            .addGroup(agregadoFamiliarListaLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 970, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(agregadoFamiliarListaLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 971, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(9, 9, 9))
        );
        agregadoFamiliarListaLayout.setVerticalGroup(
            agregadoFamiliarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(agregadoFamiliarListaLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(agregadoFamiliarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelAf, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(agregadoFamiliarListaLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(agregadoFamiliarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxAF, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldPesquisarAFL, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton11))))
                .addGap(6, 6, 6)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(agregadoFamiliarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(agregadoFamiliarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(totalAgregado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(agregadoFamiliarListaLayout.createSequentialGroup()
                            .addGap(4, 4, 4)
                            .addComponent(jLabel34)))
                    .addGroup(agregadoFamiliarListaLayout.createSequentialGroup()
                        .addComponent(jButtonApagarAF)
                        .addGap(7, 7, 7))))
        );

        jPanel3.add(agregadoFamiliarLista, "card3");

        jPanelSatisfacaoBeni.setBackground(new java.awt.Color(0, 204, 153));
        jPanelSatisfacaoBeni.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableSatisfacao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nº", "Codigo Agreg _Familiar", "Nome", "Benificio", "Descrição", "valor", "Operador", "Data", "Horas", "ID", "Apagar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableSatisfacao.setGridColor(new java.awt.Color(0, 204, 102));
        jTableSatisfacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableSatisfacaoMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTableSatisfacao);
        if (jTableSatisfacao.getColumnModel().getColumnCount() > 0) {
            jTableSatisfacao.getColumnModel().getColumn(0).setMaxWidth(50);
            jTableSatisfacao.getColumnModel().getColumn(9).setMaxWidth(50);
            jTableSatisfacao.getColumnModel().getColumn(10).setMaxWidth(50);
        }

        jPanelSatisfacaoBeni.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 63, 970, 456));

        jSeparator14.setForeground(new java.awt.Color(255, 255, 255));
        jPanelSatisfacaoBeni.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 55, 970, -1));

        jLabel54.setFont(new java.awt.Font("Sylfaen", 1, 24)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("Lista Satisfação do Benificário:");
        jPanelSatisfacaoBeni.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 348, 38));

        jLabelValor.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabelValor.setForeground(new java.awt.Color(255, 255, 255));
        jPanelSatisfacaoBeni.add(jLabelValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 525, -1, 33));

        jTextFieldPesquisaNomeSB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldPesquisaNomeSB.setBorder(null);
        jTextFieldPesquisaNomeSB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldPesquisaNomeSBKeyReleased(evt);
            }
        });
        jPanelSatisfacaoBeni.add(jTextFieldPesquisaNomeSB, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 170, 23));

        jButton9.setBackground(new java.awt.Color(0, 153, 153));
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Pesquisar");
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanelSatisfacaoBeni.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 20, -1, -1));

        jLabelVazioSB.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        jLabelVazioSB.setForeground(new java.awt.Color(204, 0, 0));
        jPanelSatisfacaoBeni.add(jLabelVazioSB, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 20, -1, 30));

        jComboBox3.setBackground(new java.awt.Color(0, 153, 51));
        jComboBox3.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione:", "Oculos ", "Subsidio de Luto", "Subsidio de Estudos", "Emprestimo", "Outros" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        jPanelSatisfacaoBeni.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 530, 153, -1));

        jButtonApagarSB.setBackground(new java.awt.Color(255, 51, 0));
        jButtonApagarSB.setForeground(new java.awt.Color(255, 255, 255));
        jButtonApagarSB.setText("Apagar");
        jButtonApagarSB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonApagarSB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonApagarSBActionPerformed(evt);
            }
        });
        jPanelSatisfacaoBeni.add(jButtonApagarSB, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 530, -1, -1));

        jLabelApagarDI.setForeground(new java.awt.Color(0, 204, 153));
        jLabelApagarDI.setText("jLabel36");
        jPanelSatisfacaoBeni.add(jLabelApagarDI, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 530, -1, -1));

        jComboBox4.setBackground(new java.awt.Color(0, 153, 0));
        jComboBox4.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione:", "Benificiario", "Agregado Familiar" }));
        jPanelSatisfacaoBeni.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 130, -1));

        jPanel3.add(jPanelSatisfacaoBeni, "card4");

        jPanelOrganizacao.setBackground(new java.awt.Color(0, 153, 153));
        jPanelOrganizacao.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel31.setFont(new java.awt.Font("Sylfaen", 1, 24)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Lista do Local de Trabalho: ");
        jPanelOrganizacao.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 17, 311, 30));
        jPanelOrganizacao.add(jSeparator27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 57, 971, 10));

        jComboBoxTrabalho.setBackground(new java.awt.Color(0, 153, 102));
        jComboBoxTrabalho.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxTrabalho.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione:", "Militar", "Funcionário" }));
        jComboBoxTrabalho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTrabalhoActionPerformed(evt);
            }
        });
        jPanelOrganizacao.add(jComboBoxTrabalho, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 21, 166, -1));

        jPanel12.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout jPanelPaginaTrabalhoInfoLayout = new javax.swing.GroupLayout(jPanelPaginaTrabalhoInfo);
        jPanelPaginaTrabalhoInfo.setLayout(jPanelPaginaTrabalhoInfoLayout);
        jPanelPaginaTrabalhoInfoLayout.setHorizontalGroup(
            jPanelPaginaTrabalhoInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 971, Short.MAX_VALUE)
        );
        jPanelPaginaTrabalhoInfoLayout.setVerticalGroup(
            jPanelPaginaTrabalhoInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 456, Short.MAX_VALUE)
        );

        jPanel12.add(jPanelPaginaTrabalhoInfo, "card4");

        jTableLocalTrabalho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nº", "Nome", "Patente", "Grau", "Entidade onde Trabalha", "Local de Trabalho", "Ramo das FADM", "Profissão", "Data de Incorporação", "Local"
            }
        ));
        jTableLocalTrabalho.setGridColor(new java.awt.Color(0, 153, 153));
        jTableLocalTrabalho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableLocalTrabalhoMouseClicked(evt);
            }
        });
        jScrollPaneMilitar.setViewportView(jTableLocalTrabalho);
        if (jTableLocalTrabalho.getColumnModel().getColumnCount() > 0) {
            jTableLocalTrabalho.getColumnModel().getColumn(0).setMaxWidth(50);
            jTableLocalTrabalho.getColumnModel().getColumn(6).setResizable(false);
        }

        jPanel12.add(jScrollPaneMilitar, "card2");

        jTableFuncionario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nº", "Nome", "Situação", "Entidade onde Trabalha", "Local de Trabalho", "Profissão", "Data de Admissão", "Local"
            }
        ));
        jTableFuncionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableFuncionarioMouseClicked(evt);
            }
        });
        jScrollPaneFuncionario.setViewportView(jTableFuncionario);
        if (jTableFuncionario.getColumnModel().getColumnCount() > 0) {
            jTableFuncionario.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jPanel12.add(jScrollPaneFuncionario, "card3");

        jPanelOrganizacao.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 73, -1, -1));

        jLabelListaTrabalho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-lista-de-ingredientes-40.png"))); // NOI18N
        jLabelListaTrabalho.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelListaTrabalho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelListaTrabalhoMouseClicked(evt);
            }
        });
        jPanelOrganizacao.add(jLabelListaTrabalho, new org.netbeans.lib.awtextra.AbsoluteConstraints(771, 11, -1, 36));

        jLabelSaveLocalTrabalho.setForeground(new java.awt.Color(255, 255, 255));
        jPanelOrganizacao.add(jLabelSaveLocalTrabalho, new org.netbeans.lib.awtextra.AbsoluteConstraints(817, 24, -1, 15));

        jLabelTotalTrabalhoMilitarunico.setFont(new java.awt.Font("Sylfaen", 1, 15)); // NOI18N
        jLabelTotalTrabalhoMilitarunico.setForeground(new java.awt.Color(255, 255, 255));
        jPanelOrganizacao.add(jLabelTotalTrabalhoMilitarunico, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 535, -1, 23));

        jTextFieldPesquisarLLT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldPesquisarLLT.setBorder(null);
        jPanelOrganizacao.add(jTextFieldPesquisarLLT, new org.netbeans.lib.awtextra.AbsoluteConstraints(501, 20, 126, 23));

        jButton12.setBackground(new java.awt.Color(0, 102, 102));
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("Pesquisar");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanelOrganizacao.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(633, 20, -1, -1));

        jPanel3.add(jPanelOrganizacao, "card5");

        ListaPainel.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 91, 990, 570));

        jLabelBiFotoSave.setForeground(new java.awt.Color(255, 255, 255));
        ListaPainel.add(jLabelBiFotoSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(291, 6, -1, 19));

        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel94.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-redimensionar-quatro-sentidos-20.png"))); // NOI18N
        jLabel94.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        jLabel94.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel94MouseDragged(evt);
            }
        });
        jLabel94.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel94MousePressed(evt);
            }
        });
        ListaPainel.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(915, 17, 40, 29));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setForeground(new java.awt.Color(0, 153, 153));
        jButton2.setText("Organicação");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        ListaPainel.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, 108, 28));

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-enviar-lista-quente-40.png"))); // NOI18N
        ListaPainel.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 53, -1));

        jPanel1.add(ListaPainel, "card6");

        jPanelQuotizacao.setBackground(new java.awt.Color(255, 255, 255));

        jSeparator28.setForeground(new java.awt.Color(0, 153, 51));

        jLabel44.setFont(new java.awt.Font("Sylfaen", 1, 30)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 153, 51));
        jLabel44.setText("Quotização");

        jLabel129.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel129.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-fechar-janela-35.png"))); // NOI18N
        jLabel129.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel129.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel129MouseClicked(evt);
            }
        });

        jLabel130.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel130.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-redimensionar-quatro-sentidos-20.png"))); // NOI18N
        jLabel130.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        jLabel130.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel130MouseDragged(evt);
            }
        });
        jLabel130.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel130MousePressed(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(0, 153, 51));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("___");
        jLabel45.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txtvalorQuota.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        txtvalorQuota.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtvalorQuota.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));
        txtvalorQuota.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtvalorQuota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtvalorQuotaActionPerformed(evt);
            }
        });
        txtvalorQuota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtvalorQuotaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtvalorQuotaKeyTyped(evt);
            }
        });

        jComboBoxMesQuota.setBackground(new java.awt.Color(51, 153, 0));
        jComboBoxMesQuota.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxMesQuota.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione:", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" }));
        jComboBoxMesQuota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMesQuotaActionPerformed(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabel58.setText("Mes:");

        jLabel59.setText("Metical");

        jButtonPagarQuota.setBackground(new java.awt.Color(0, 204, 153));
        jButtonPagarQuota.setForeground(new java.awt.Color(255, 255, 255));
        jButtonPagarQuota.setText("Pagar");
        jButtonPagarQuota.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonPagarQuota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPagarQuotaActionPerformed(evt);
            }
        });

        jComboBoxMesQuota1.setBackground(new java.awt.Color(51, 153, 0));
        jComboBoxMesQuota1.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxMesQuota1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione:", "BI", "Codigo ID" }));
        jComboBoxMesQuota1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMesQuota1ActionPerformed(evt);
            }
        });

        jLabel60.setFont(new java.awt.Font("Sylfaen", 0, 24)); // NOI18N
        jLabel60.setText("Pagamento de Quota:");

        txtBI.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        txtBI.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBI.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));
        txtBI.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtBI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBIKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBIKeyTyped(evt);
            }
        });

        jPanel13.setLayout(new java.awt.CardLayout());

        jTableQuotizacao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nº", "Nome", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembo", "Outubro", "Novembro", "Dezembro", "Ano", "BI Benificiário"
            }
        ));
        jTableQuotizacao.setGridColor(new java.awt.Color(0, 204, 204));
        jScrollPane6.setViewportView(jTableQuotizacao);
        if (jTableQuotizacao.getColumnModel().getColumnCount() > 0) {
            jTableQuotizacao.getColumnModel().getColumn(0).setMaxWidth(40);
            jTableQuotizacao.getColumnModel().getColumn(4).setMaxWidth(50);
            jTableQuotizacao.getColumnModel().getColumn(5).setMaxWidth(50);
            jTableQuotizacao.getColumnModel().getColumn(6).setMaxWidth(50);
            jTableQuotizacao.getColumnModel().getColumn(7).setMaxWidth(50);
            jTableQuotizacao.getColumnModel().getColumn(8).setMaxWidth(50);
            jTableQuotizacao.getColumnModel().getColumn(9).setMaxWidth(50);
            jTableQuotizacao.getColumnModel().getColumn(14).setMaxWidth(45);
        }

        javax.swing.GroupLayout jPanelJoiaLayout = new javax.swing.GroupLayout(jPanelJoia);
        jPanelJoia.setLayout(jPanelJoiaLayout);
        jPanelJoiaLayout.setHorizontalGroup(
            jPanelJoiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJoiaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 970, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelJoiaLayout.setVerticalGroup(
            jPanelJoiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJoiaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel13.add(jPanelJoia, "card4");

        jTableJoia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nº", "Nome", "Joia", "BI", "Data", "Data Registo"
            }
        ));
        jTableJoia.setGridColor(new java.awt.Color(0, 153, 0));
        jScrollPane7.setViewportView(jTableJoia);
        if (jTableJoia.getColumnModel().getColumnCount() > 0) {
            jTableJoia.getColumnModel().getColumn(0).setMaxWidth(50);
            jTableJoia.getColumnModel().getColumn(2).setMaxWidth(50);
            jTableJoia.getColumnModel().getColumn(4).setMaxWidth(50);
        }

        javax.swing.GroupLayout jPanelQuotaLayout = new javax.swing.GroupLayout(jPanelQuota);
        jPanelQuota.setLayout(jPanelQuotaLayout);
        jPanelQuotaLayout.setHorizontalGroup(
            jPanelQuotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 970, Short.MAX_VALUE)
            .addGroup(jPanelQuotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelQuotaLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 970, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanelQuotaLayout.setVerticalGroup(
            jPanelQuotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 264, Short.MAX_VALUE)
            .addGroup(jPanelQuotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelQuotaLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel13.add(jPanelQuota, "card4");

        jComboBoxAnoQuota2.setBackground(new java.awt.Color(51, 153, 0));
        jComboBoxAnoQuota2.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxAnoQuota2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAnoQuota2ActionPerformed(evt);
            }
        });

        jCheckBoxPagarJoia.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxPagarJoia.setText("Pagar Joia");
        jCheckBoxPagarJoia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxPagarJoiaActionPerformed(evt);
            }
        });

        jLabel61.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabel61.setText("Ano:");

        jTextFieldValorJoia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldValorJoia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));

        jLabelBTNPagar.setDisplayedMnemonic('d');
        jLabelBTNPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-dólar-americano-28.png"))); // NOI18N
        jLabelBTNPagar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelBTNPagar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBTNPagarMouseClicked(evt);
            }
        });

        VerAgregado1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-visível-30.png"))); // NOI18N
        VerAgregado1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        VerAgregado1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VerAgregado1MouseClicked(evt);
            }
        });

        jPanel14.setBackground(new java.awt.Color(0, 153, 0));

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Nome:");

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("Joia:");

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(255, 255, 255));
        jLabel62.setText("Data:");

        jLabelNomeQuo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelNomeQuo.setForeground(new java.awt.Color(255, 255, 255));

        jLabelJoiaPAga.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelJoiaPAga.setForeground(new java.awt.Color(255, 255, 255));

        jLabel67.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(255, 255, 255));

        jLabelFotoQuota.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelFotoQuota.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(255, 255, 255));
        jLabel66.setText("BI:");

        jLabelBIQuot.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelBIQuot.setForeground(new java.awt.Color(255, 255, 255));

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(255, 255, 255));
        jLabel68.setText("Cont.:");

        jLabelContactoQuot.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelContactoQuot.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel48)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelJoiaPAga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel62)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel66)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelBIQuot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel68)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelContactoQuot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelFotoQuota, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel46)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelNomeQuo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelNomeQuo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelJoiaPAga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelBIQuot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabelContactoQuot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(1, 1, 1)))
                        .addGap(34, 34, 34))
                    .addComponent(jLabelFotoQuota, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel65.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabel65.setText("Valor:");

        Quota.setBackground(new java.awt.Color(255, 255, 255));
        quotizacaoOpetion.add(Quota);
        Quota.setText("Quota");
        Quota.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Quota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuotaActionPerformed(evt);
            }
        });

        jRadioJoia.setBackground(new java.awt.Color(255, 255, 255));
        quotizacaoOpetion.add(jRadioJoia);
        jRadioJoia.setText("Joia");
        jRadioJoia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioJoia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioJoiaActionPerformed(evt);
            }
        });

        jTextFieldPesquisarJQ.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldPesquisarJQ.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTextFieldPesquisarJQ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldPesquisarJQKeyReleased(evt);
            }
        });

        jButton13.setBackground(new java.awt.Color(0, 204, 51));
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setText("Pesquisar");
        jButton13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jLabelTituloQ1.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N

        jLabelEmpty.setForeground(new java.awt.Color(255, 0, 0));
        jLabelEmpty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanelQuotizacaoLayout = new javax.swing.GroupLayout(jPanelQuotizacao);
        jPanelQuotizacao.setLayout(jPanelQuotizacaoLayout);
        jPanelQuotizacaoLayout.setHorizontalGroup(
            jPanelQuotizacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelQuotizacaoLayout.createSequentialGroup()
                .addGroup(jPanelQuotizacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelQuotizacaoLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel44)
                        .addGap(639, 639, 639)
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel130, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel129))
                    .addGroup(jPanelQuotizacaoLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanelQuotizacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelQuotizacaoLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanelQuotizacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel60)
                                    .addComponent(jComboBoxMesQuota1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanelQuotizacaoLayout.createSequentialGroup()
                                        .addComponent(txtBI, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4)
                                        .addComponent(VerAgregado1))
                                    .addGroup(jPanelQuotizacaoLayout.createSequentialGroup()
                                        .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBoxAnoQuota2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelQuotizacaoLayout.createSequentialGroup()
                                        .addComponent(jCheckBoxPagarJoia)
                                        .addGap(2, 2, 2)
                                        .addComponent(jTextFieldValorJoia, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabelBTNPagar))
                                    .addGroup(jPanelQuotizacaoLayout.createSequentialGroup()
                                        .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtvalorQuota, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel59))
                                    .addGroup(jPanelQuotizacaoLayout.createSequentialGroup()
                                        .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBoxMesQuota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelQuotizacaoLayout.createSequentialGroup()
                                        .addGap(42, 42, 42)
                                        .addComponent(jButtonPagarQuota)))
                                .addGap(232, 232, 232)
                                .addGroup(jPanelQuotizacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanelQuotizacaoLayout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(jLabelTituloQ1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanelQuotizacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabelEmpty, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jTextFieldPesquisarJQ, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton13))
                                    .addGroup(jPanelQuotizacaoLayout.createSequentialGroup()
                                        .addGap(116, 116, 116)
                                        .addComponent(Quota)
                                        .addGap(10, 10, 10)
                                        .addComponent(jRadioJoia))))
                            .addComponent(jSeparator28, javax.swing.GroupLayout.PREFERRED_SIZE, 969, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(185, 185, 185))
        );
        jPanelQuotizacaoLayout.setVerticalGroup(
            jPanelQuotizacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelQuotizacaoLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanelQuotizacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelQuotizacaoLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelQuotizacaoLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel130, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel129))
                .addGap(6, 6, 6)
                .addComponent(jSeparator28, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(jPanelQuotizacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelQuotizacaoLayout.createSequentialGroup()
                        .addComponent(jLabel60)
                        .addGap(6, 6, 6)
                        .addComponent(jComboBoxMesQuota1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(jPanelQuotizacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelQuotizacaoLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(txtBI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(VerAgregado1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelQuotizacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxPagarJoia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldValorJoia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelBTNPagar))
                        .addGap(13, 13, 13)
                        .addGroup(jPanelQuotizacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxAnoQuota2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel61))))
                .addGap(18, 18, 18)
                .addGroup(jPanelQuotizacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxMesQuota, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelQuotizacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelQuotizacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Quota)
                        .addComponent(jRadioJoia))
                    .addGroup(jPanelQuotizacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel65)
                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtvalorQuota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelQuotizacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPagarQuota)
                    .addComponent(jTextFieldPesquisarJQ, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13)
                    .addComponent(jLabelTituloQ1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jLabelEmpty)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jPanel1.add(jPanelQuotizacao, "card9");

        AdministradorPanel.setBackground(new java.awt.Color(255, 255, 255));
        AdministradorPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelAdminTitulo.setFont(new java.awt.Font("Sylfaen", 1, 30)); // NOI18N
        jLabelAdminTitulo.setForeground(new java.awt.Color(0, 153, 51));
        jLabelAdminTitulo.setText("Administrador ");
        AdministradorPanel.add(jLabelAdminTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 33, 240, 28));

        jLabelAdminIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-configurações-40.png"))); // NOI18N
        AdministradorPanel.add(jLabelAdminIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 21, -1, -1));

        jSeparator12.setForeground(new java.awt.Color(0, 153, 51));
        AdministradorPanel.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 67, 970, 10));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel47.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabel47.setText("Sexo");
        jPanel4.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 40, -1));

        jLabel57.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        jLabel57.setText("Masculino:");
        jPanel4.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        jLabel49.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        jLabel49.setText("Masculino:");
        jPanel4.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 34, -1, -1));

        jLabel50.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        jLabel50.setText("Femenino:");
        jPanel4.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));
        jPanel4.add(TotalMasculinoBenifi, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 34, -1, 22));
        jPanel4.add(TotalFemeninoBenif, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 60, -1, 22));

        jLabel52.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        jLabel52.setText("Femenino:");
        jPanel4.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, -1));
        jPanel4.add(TotalFemenino, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, -1, 22));
        jPanel4.add(TotalMasculinoAF, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, -1, 22));

        jSeparator13.setBackground(new java.awt.Color(0, 153, 51));
        jSeparator13.setForeground(new java.awt.Color(0, 153, 51));
        jPanel4.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 990, 12));

        jLabel77.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        jLabel77.setText("Solteiro:");
        jPanel4.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, -1, -1));

        jLabel78.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        jLabel78.setText("Casado:");
        jPanel4.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, -1, -1));

        jLabel79.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        jLabel79.setText("Divorciado:");
        jPanel4.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, -1, -1));

        jLabel80.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabel80.setText("Sexo");
        jPanel4.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 2, -1, 26));

        jLabel81.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabel81.setText("Estado Civil");
        jPanel4.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, -1, 26));
        jPanel4.add(jLabelTotalSolteriro, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, -1, 22));
        jPanel4.add(jLabelTotalSolteriro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, -1, 22));
        jPanel4.add(jLabelTotalDivorciado, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, -1, 22));

        jSeparator22.setForeground(new java.awt.Color(0, 153, 153));
        jSeparator22.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel4.add(jSeparator22, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, -7, 10, 190));

        jSeparator23.setForeground(new java.awt.Color(0, 153, 153));
        jSeparator23.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel4.add(jSeparator23, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, -7, 10, 190));

        jLabel82.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabel82.setText("Ocupação");
        jPanel4.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, -1, 26));

        jLabel97.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabel97.setText("Militar:");
        jPanel4.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, -1, -1));
        jPanel4.add(jLabelTotalMilitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, -1, 22));

        jLabel99.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        jLabel99.setText("Quadro Permanente:");
        jPanel4.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, -1, -1));
        jPanel4.add(jLabelTotalQuadroPensionista, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, -1, 22));

        jLabel100.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        jLabel100.setText("Reserva:");
        jPanel4.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, -1, -1));
        jPanel4.add(jLabelTotalReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, -1, 22));

        jLabel123.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        jLabel123.setText("Reforma:");
        jPanel4.add(jLabel123, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, -1, -1));
        jPanel4.add(jLabelTotalReforma, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 120, -1, 22));

        jLabel124.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        jLabel124.setText("Pensionista:");
        jPanel4.add(jLabel124, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, -1, -1));
        jPanel4.add(jLabelTotalPensionista, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 150, -1, 22));

        jLabel125.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        jLabel125.setText("Activo:");
        jPanel4.add(jLabel125, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, -1, -1));

        jLabel126.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabel126.setText("Funcionário:");
        jPanel4.add(jLabel126, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, -1, -1));
        jPanel4.add(jLabelTotalFfuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 30, -1, 22));
        jPanel4.add(jLabelTotalFactivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, -1, 22));

        jLabel127.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        jLabel127.setText("Contradado");
        jPanel4.add(jLabel127, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, -1, -1));
        jPanel4.add(jLabelTotalFContratado, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 90, -1, 22));

        jLabel128.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        jLabel128.setText("Reformado:");
        jPanel4.add(jLabel128, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 110, -1, -1));
        jPanel4.add(jLabelTotalFreformado, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, -1, 22));

        jSeparator24.setForeground(new java.awt.Color(0, 153, 153));
        jSeparator24.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel4.add(jSeparator24, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 10, 180));

        TAagregadoFamiliar.setFont(new java.awt.Font("Sylfaen", 1, 32)); // NOI18N
        TAagregadoFamiliar.setForeground(new java.awt.Color(0, 204, 51));
        jPanel4.add(TAagregadoFamiliar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, -1, 40));

        TAbenificiario.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        TAbenificiario.setForeground(new java.awt.Color(0, 153, 153));
        TAbenificiario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel4.add(TAbenificiario, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 60, -1, 38));

        jLabel10.setFont(new java.awt.Font("Sylfaen", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 153, 153));
        jLabel10.setText("Total:");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 60, -1, -1));

        jSeparator25.setForeground(new java.awt.Color(0, 204, 51));
        jSeparator25.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel4.add(jSeparator25, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 10, 110));

        lblTotalAgF1.setFont(new java.awt.Font("Sylfaen", 1, 32)); // NOI18N
        lblTotalAgF1.setForeground(new java.awt.Color(0, 204, 51));
        lblTotalAgF1.setText("Total:");
        jPanel4.add(lblTotalAgF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, -1, -1));

        jSeparator26.setBackground(new java.awt.Color(0, 153, 51));
        jSeparator26.setForeground(new java.awt.Color(0, 153, 51));
        jSeparator26.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel4.add(jSeparator26, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 190, 10, 170));

        jLabel29.setBackground(new java.awt.Color(0, 204, 51));
        jLabel29.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Estatistica do Agregado Familiar:");
        jLabel29.setOpaque(true);
        jPanel4.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 194, 360, 20));

        jLabel30.setBackground(new java.awt.Color(0, 204, 153));
        jLabel30.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Satisfação de Benificiarios:");
        jLabel30.setOpaque(true);
        jPanel4.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 194, 310, 20));

        jComboBox2.setBackground(new java.awt.Color(0, 204, 153));
        jComboBox2.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "----", "Oculos ", "Subsidio de Luto", "Subsidio de Estudos", "Emprestimo", "Outros" }));
        jComboBox2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox2MouseClicked(evt);
            }
        });
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jPanel4.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 220, -1, -1));

        jLabel22.setText("Selecione:");
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 220, -1, 20));

        jLabelBeneficio.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        jLabelBeneficio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel4.add(jLabelBeneficio, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 250, 230, 20));

        jLabelMultTotalASatisfacao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel4.add(jLabelMultTotalASatisfacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 270, 230, 20));

        lblTotalAgF.setFont(new java.awt.Font("Sylfaen", 1, 32)); // NOI18N
        lblTotalAgF.setForeground(new java.awt.Color(0, 204, 153));
        lblTotalAgF.setText("Total:");
        jPanel4.add(lblTotalAgF, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 300, -1, 40));

        jLabelTotalSBenf.setFont(new java.awt.Font("Sylfaen", 1, 36)); // NOI18N
        jLabelTotalSBenf.setForeground(new java.awt.Color(0, 204, 153));
        jPanel4.add(jLabelTotalSBenf, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 300, -1, 40));

        jLabel71.setBackground(new java.awt.Color(153, 204, 0));
        jLabel71.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(255, 255, 255));
        jLabel71.setText("Total de Joias:");
        jLabel71.setOpaque(true);
        jPanel4.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 194, 200, 20));

        jSeparator29.setBackground(new java.awt.Color(0, 153, 51));
        jSeparator29.setForeground(new java.awt.Color(0, 153, 51));
        jSeparator29.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel4.add(jSeparator29, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 190, 10, 170));

        jLabel72.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        jLabel72.setText("Valor Total :");
        jPanel4.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 280, -1, -1));

        jLabel73.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        jLabel73.setText("Pago:");
        jPanel4.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 220, -1, -1));

        jLabel74.setFont(new java.awt.Font("Sylfaen", 0, 16)); // NOI18N
        jLabel74.setText("Em Falta:");
        jPanel4.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 250, -1, -1));

        AdministradorPanel.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 970, 360));

        jLabel21.setBackground(new java.awt.Color(0, 153, 153));
        jLabel21.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText(" Estatistica de Benificiarios:");
        jLabel21.setOpaque(true);
        AdministradorPanel.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 970, 26));

        jBApagarTodo.setBackground(new java.awt.Color(255, 255, 255));
        jBApagarTodo.setForeground(new java.awt.Color(204, 0, 0));
        jBApagarTodo.setText("Apagar");
        jBApagarTodo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        jBApagarTodo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBApagarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBApagarTodoActionPerformed(evt);
            }
        });
        AdministradorPanel.add(jBApagarTodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 650, 162, 30));

        jCheckDelete.setBackground(new java.awt.Color(255, 255, 255));
        jCheckDelete.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckDelete.setForeground(new java.awt.Color(255, 0, 0));
        jCheckDelete.setText("Apagar Todos os dados do Benificiário");
        jCheckDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckDeleteActionPerformed(evt);
            }
        });
        AdministradorPanel.add(jCheckDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 600, -1, -1));

        jCheckDeleteAF.setBackground(new java.awt.Color(255, 255, 255));
        jCheckDeleteAF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckDeleteAF.setForeground(new java.awt.Color(255, 0, 0));
        jCheckDeleteAF.setText("Apagar Todos os dados do Agregado Familiar");
        jCheckDeleteAF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckDeleteAFActionPerformed(evt);
            }
        });
        AdministradorPanel.add(jCheckDeleteAF, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 620, -1, -1));

        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel87.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-fechar-janela-35.png"))); // NOI18N
        jLabel87.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel87.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel87MouseClicked(evt);
            }
        });
        AdministradorPanel.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, -1, -1));

        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel93.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-redimensionar-quatro-sentidos-20.png"))); // NOI18N
        jLabel93.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        jLabel93.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel93MouseDragged(evt);
            }
        });
        jLabel93.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel93MousePressed(evt);
            }
        });
        AdministradorPanel.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 10, 40, 40));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        loadingbar.setBackground(new java.awt.Color(255, 255, 255));
        loadingbar.setForeground(new java.awt.Color(0, 204, 204));
        loadingbar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mascilino", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 204, 204))); // NOI18N

        jProgressBarF.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBarF.setForeground(new java.awt.Color(0, 153, 51));
        jProgressBarF.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Femenino", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 153, 51))); // NOI18N

        jLabel96.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabel96.setText("Grafico:");

        jLabel51.setFont(new java.awt.Font("Sylfaen", 1, 16)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(0, 153, 0));
        jLabel51.setText("Benificiarios:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(loadingbar, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                            .addComponent(jProgressBarF, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(73, 73, 73)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(percentagemF, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(percentagemM, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel96)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel96)
                            .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(loadingbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(percentagemM, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(percentagemF, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jProgressBarF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        AdministradorPanel.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, -1, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 153, 51));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("___");
        jLabel23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AdministradorPanel.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 20, 30, 20));

        jPanel1.add(AdministradorPanel, "card8");

        getContentPane().add(jPanel1);
        jPanel1.setBounds(260, 10, 1040, 700);

        jPanel5.setBackground(new java.awt.Color(0, 153, 51));

        jLabel95.setForeground(new java.awt.Color(255, 255, 255));
        jLabel95.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel95.setText("Sistema de Inscrição e Satisfação de Benificiário  ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel95, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel5);
        jPanel5.setBounds(860, 710, 430, 20);
        getContentPane().add(jSeparator17);
        jSeparator17.setBounds(260, 0, 500, 10);
        getContentPane().add(jSeparator18);
        jSeparator18.setBounds(260, 720, 590, 10);

        jSeparator19.setBackground(new java.awt.Color(0, 153, 51));
        getContentPane().add(jSeparator19);
        jSeparator19.setBounds(770, 0, 530, 10);

        jSeparator21.setBackground(new java.awt.Color(0, 153, 51));
        jSeparator21.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator21);
        jSeparator21.setBounds(1310, 10, 10, 720);

        setSize(new java.awt.Dimension(1319, 740));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtuserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtuserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtuserActionPerformed

    private void btnacessoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnacessoActionPerformed
        // TODO add your handling code here:
        AcessarSistema();
        setColor(PaginaIn);
    }//GEN-LAST:event_btnacessoActionPerformed

    private void txtsenhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtsenhaMouseClicked
        // TODO add your handling code here:
        txtsenha.setForeground(Color.black);
        txtuser.setForeground(Color.black);
    }//GEN-LAST:event_txtsenhaMouseClicked

    private void lblregistoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblregistoMouseClicked
        // TODO add your handling code here:
        //patenteMilitar.setVisible(false);
        jLabelShowFoto.setVisible(false);;
        jCheckBoxBairro.setSelected(false);
        setColor(lblregisto);
        resetColor(PaginaIn);
        resetColor(lblgrouparentesco);
        resetColor(lblListaMenu);
        resetColor(lblSatisfacaoBenificio);
        resetColor(lblMenuAdminitrador);
        resetColor(lblMenuQuatizacao);
        jPanelBairro.setVisible(false);
        registo.setVisible(true);
        PaginaInicial.setVisible(false);
        AgregadoFamiliar.setVisible(false);
        ListaPainel.setVisible(false);
        SaticacaoBenifi.setVisible(false);
        AdministradorPanel.setVisible(false);
        btngravar.setEnabled(false);
        jPanelQuotizacao.setVisible(false);
        AnosdeNascimente();
    }//GEN-LAST:event_lblregistoMouseClicked

    private void PaginaInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PaginaInMouseClicked
        // TODO add your handling code here:
        jLabelShowFoto.setVisible(false);
        setColor(PaginaIn);
        resetColor(lblregisto);
        resetColor(lblgrouparentesco);
        resetColor(lblListaMenu);
        resetColor(lblSatisfacaoBenificio);
        resetColor(lblMenuAdminitrador);
        resetColor(lblMenuQuatizacao);
        PaginaInicial.setVisible(true);
        registo.setVisible(false);
        AgregadoFamiliar.setVisible(false);
        ListaPainel.setVisible(false);
        SaticacaoBenifi.setVisible(false);
        AdministradorPanel.setVisible(false);
        jPanelQuotizacao.setVisible(false);
    }//GEN-LAST:event_PaginaInMouseClicked

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
        // TODO add your handling code here:
        ListaPainel.setVisible(false);
        PaginaInicial.setVisible(false);
        registo.setVisible(false);
        SaticacaoBenifi.setVisible(false);
        login.setVisible(true);
        txtsenha.setText("");
        txtuser.setText("");
        txtsenha.setForeground(Color.black);
        txtuser.setForeground(Color.black);
        MenuIcon();

    }//GEN-LAST:event_logoutMouseClicked

    private void txtuserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtuserMouseClicked
        // TODO add your handling code here:
        txtuser.setForeground(Color.black);
        txtsenha.setForeground(Color.black);
    }//GEN-LAST:event_txtuserMouseClicked

    private void masculinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masculinoActionPerformed
        // TODO add your handling code here:
        if (masculino.isSelected() == true) {
            jLabelSexo.setForeground(Color.black);
        }
    }//GEN-LAST:event_masculinoActionPerformed

    private void btngravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngravarActionPerformed
        // TODO add your handling code here:
        try {

            String sexo;
            if (femenino.isSelected()) {
                sexo = "Femenino";
            } else {
                sexo = "Masculino";
            }

            String situacao = "";
            if (Reformado.isSelected()) {
                situacao = "Reformado";
            } else if (Contratado.isSelected()) {
                situacao = "Contratado";
            } else if (Activo.isSelected()) {
                situacao = "Activo";
            }

            String grau = "";
            if (jCheckBoxReserva.isSelected()) {
                grau = "Reserva";
            } else if (jCheckBoxReforma.isSelected()) {
                grau = "Reforma";
            } else if (jCheckBoxQuadro.isSelected()) {
                grau = "Quadro Permanente";
            } else if (jCheckBoxPensionista.isSelected()) {
                grau = "Pensionista";
            }

            if (jCLetra.getSelectedItem().equals("--")) {
                JOptionPane.showMessageDialog(null, "Preencha todo o Campo Vazio!");
            } else if (txtnome.getText().equals("")) {
                jLabelNomeRegisto.setForeground(Color.red);
            } else if (txtapelido.getText().equals("")) {
                jLabelApelidoRegisto.setForeground(Color.red);
            } else if (txtpai.getText().equals("")) {
                jLabelPai.setForeground(Color.red);
            } else if (txtmae.getText().equals("")) {
                jLabelMae.setForeground(Color.red);
            } else if (txtbi.getText().equals("")) {
                jLabelBi.setForeground(Color.red);
            } else if (jComboBoxDiaNasc.getSelectedItem().equals("----")) {
                jLabelNasDia.setForeground(Color.red);
            } else if (jComboBoxMesNasc.getSelectedItem().equals("----")) {
                jLabelNasMes.setForeground(Color.red);
            } else if (jComboBoxAnosNasc.getSelectedItem().equals("1930")) {
                jLabelNasAno.setForeground(Color.red);
            } else if (jComboBoxEstadoCivil.getSelectedItem().equals("----")) {
                jLabelEstadoCivil.setForeground(Color.red);
            } else if (femenino.isSelected() == false && masculino.isSelected() == false) {
                jLabelSexo.setForeground(Color.red);
            } else if (jComboBoxProvincia.getSelectedItem().equals("----")) {
                jLabelProvinciaBI.setForeground(Color.red);
            } else if (txtDistrito.getText().equals("")) {
                jLabelDistritoBI.setForeground(Color.red);
            } else if (txtLocalidade.getText().equals("")) {
                jLabelLocalidadeBI.setForeground(Color.red);
            } else if (jCheckBoxPatente.isSelected() == false && Activo.isSelected() == false && Reformado.isSelected() == false && Contratado.isSelected() == false) {
                JOptionPane.showMessageDialog(null, "Ocupação não foi selecinada: MILITAR ou Funciaonario.");
            } else if (txtEntidadeOndeTrabalho.getText().equals("")) {
                jLabelEOT.setForeground(Color.red);
            } else if (txtLocalTrabalho.getText().equals("")) {
                jLabelLtrabalha.setForeground(Color.red);
            } else if (txtProfissao.getText().equals("")) {
                jLabelProfissao.setForeground(Color.red);
            } else if (txtcontacto.getText().equals("")) {
                jLabelContacto.setForeground(Color.red);
            } else if (jComboBoxDiaNascIA.getSelectedItem().equals("----")) {
                jLabelDIdia.setForeground(Color.red);
            } else if (jComboBoxMesNascIA.getSelectedItem().equals("----")) {
                jLabelDImes.setForeground(Color.red);
            } else if (jComboBoxAnoIcorporacao.getSelectedItem().equals("1930")) {
                jLabelDIAno.setForeground(Color.red);
            } else if (txtlocal.getText().equals("")) {
                jLabelLocal.setForeground(Color.red);
            } else if (jCheckBoxBairro.isSelected() == false) {
                jCheckBoxBairro.setForeground(Color.red);
            } else if (txtmorada.getText().equals("")) {
                jPanelBairro.setVisible(true);
                jLabelEnderencoBairro.setForeground(Color.red);
            } else if (txtbairro.getText().equals("")) {
                jPanelBairro.setVisible(true);
                jLabelBairro.setForeground(Color.red);
            } else if (txtBlocalidade.getText().equals("")) {
                jPanelBairro.setVisible(true);
                jLabelBlocalidade.setForeground(Color.red);
            } else if (txtBdistrito.getText().equals("")) {
                jPanelBairro.setVisible(true);
                jLabelBdistrito.setForeground(Color.red);
            } else if (jComboBoxBProvincia.getSelectedItem().equals("----")) {
                jPanelBairro.setVisible(true);
                jLabelBprovincia.setForeground(Color.red);
            } else {
                PreparedStatement inserir = cbd.conexao.prepareStatement("INSERT INTO cliente(nome,apelido,dia_Nascimento,ano_Nascimento,estado_civil,sexo,bi,letra,pai,mae,pais,provincia,distrito,localidade,hora,data,foto,email) VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                InputStream img = new FileInputStream(new File(imgPath));

                inserir.setString(1, txtnome.getText());
                inserir.setString(2, txtapelido.getText());
                inserir.setString(3, (String) jComboBoxDiaNasc.getSelectedItem() + " " + (String) jComboBoxMesNasc.getSelectedItem());
                inserir.setString(4, (String) jComboBoxAnosNasc.getSelectedItem());
                inserir.setString(5, (String) jComboBoxEstadoCivil.getSelectedItem());
                inserir.setString(6, sexo.toString());
                inserir.setString(7, txtbi.getText());
                inserir.setString(8, (String) jCLetra.getSelectedItem());
                inserir.setString(9, txtpai.getText());
                inserir.setString(10, txtmae.getText());
                inserir.setString(11, (String) jComboBoxPais.getSelectedItem());
                inserir.setString(12, (String) jComboBoxProvincia.getSelectedItem());
                inserir.setString(13, txtDistrito.getText());
                inserir.setString(14, txtLocalidade.getText());
                inserir.setString(15, horas.getText());
                inserir.setString(16, data.getText());
                inserir.setBlob(17, img);
                inserir.setString(18, txtemail.getText());
                inserir.executeUpdate();

                PreparedStatement inserircontatos = cbd.conexao.prepareStatement("INSERT INTO contatos (contacto,bi_contacto) VALUE (?,?)");
                inserircontatos.setString(1, txtcontacto.getText());
                inserircontatos.setString(2, txtbi.getText());
                inserircontatos.executeUpdate();

                if (jCheckBoxBairro.isSelected()) {
                    PreparedStatement inserirbairros = cbd.conexao.prepareStatement("INSERT INTO bairro (bi_bairro,morada,bairro,localidade,distrito,provincia) VALUE (?,?,?,?,?,?)");

                    inserirbairros.setString(1, txtbi.getText());
                    inserirbairros.setString(2, txtmorada.getText());
                    inserirbairros.setString(3, txtbairro.getText());
                    inserirbairros.setString(4, txtBlocalidade.getText());
                    inserirbairros.setString(5, txtBdistrito.getText());
                    inserirbairros.setString(6, (String) jComboBoxBProvincia.getSelectedItem());

                    inserirbairros.executeUpdate();
                } else {

                }

                if (Activo.isSelected() || Contratado.isSelected() || Reformado.isSelected()) {
                    PreparedStatement inserirfuncionario = cbd.conexao.prepareStatement("INSERT INTO funcionario (situacao,entidade_onde_trabalha,local_trabalho,profissao,data_admissao,local,bi_funcionario) VALUE (?,?,?,?,?,?,?)");

                    inserirfuncionario.setString(1, situacao.toString());
                    inserirfuncionario.setString(2, txtEntidadeOndeTrabalho.getText());
                    inserirfuncionario.setString(3, txtLocalTrabalho.getText());
                    inserirfuncionario.setString(4, txtProfissao.getText());
                    inserirfuncionario.setString(5, (String) jComboBoxDiaNascIA.getSelectedItem() + " " + (String) jComboBoxMesNascIA.getSelectedItem() + " " + (String) jComboBoxAnoIcorporacao.getSelectedItem());
                    inserirfuncionario.setString(6, txtlocal.getText());
                    inserirfuncionario.setString(7, txtbi.getText());
                    inserirfuncionario.executeUpdate();
                } else if (jCheckBoxPatente.isSelected()) {
                    PreparedStatement inserirMilitar = cbd.conexao.prepareStatement("INSERT INTO militar (patente,grau,entidade_onde_trabalho,local_trabalho,ramo_fadm,profissao,data_incoporacao,local,bi_militar) VALUE (?,?,?,?,?,?,?,?,?)");

                    inserirMilitar.setString(1, patenteMilitar.getText());
                    inserirMilitar.setString(2, grau.toString());
                    inserirMilitar.setString(3, txtEntidadeOndeTrabalho.getText());
                    inserirMilitar.setString(4, txtLocalTrabalho.getText());
                    inserirMilitar.setString(5, txtramo.getText());
                    inserirMilitar.setString(6, txtProfissao.getText());
                    inserirMilitar.setString(7, (String) jComboBoxDiaNascIA.getSelectedItem() + " " + (String) jComboBoxMesNascIA.getSelectedItem() + " " + (String) jComboBoxAnoIcorporacao.getSelectedItem());
                    inserirMilitar.setString(8, txtlocal.getText());
                    inserirMilitar.setString(9, txtbi.getText());
                    inserirMilitar.executeUpdate();
                }

                LimparCamposRegisto();
                Dialog l = new Dialog();
                l.setVisible(true);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro na Inseção dos Dados!" + erro);
            JanelaMensagem erros = new JanelaMensagem();
            erros.setVisible(true);
        }
    }//GEN-LAST:event_btngravarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            cbd.executaSQL("SELECT * FROM cliente WHERE bi like'%" + txtPesBI.getText() + "%'");
            cbd.resultado.first();

            txtTitle.setText("Dados Do Benificiário:");
            txtShownome.setText("Nome completo:");
            txtPnome.setText(cbd.resultado.getString("nome") + " " + cbd.resultado.getString("apelido") + ".");
            txtShowramo.setText("Ramo:");
            txtPramo.setText(cbd.resultado.getString("ramo") + ".");
            txtShowunidade.setText("Unidade:");
            txtPunidade.setText(cbd.resultado.getString("unidade") + ".");
            txtShowbi.setText("BI:");
            txtPbi.setText(cbd.resultado.getString("bi") + ".");
            txtShowcontacto.setText("Contacto:");
            txtPcontacto.setText(cbd.resultado.getString("contacto") + ".");
            txtShowsexo.setText("Sexo:");
            txtPsexo.setText(cbd.resultado.getString("sexo") + ".");
            txtShowhora.setText("Horas:");
            txtPhora.setText(cbd.resultado.getString("hora") + ".");
            txtShowdata.setText("Data:");
            txtPdata.setText(cbd.resultado.getString("data") + ".");
            txtShowdataInscricao.setText("Ano de Inscrição:");
            txtPdataIncricao.setText(cbd.resultado.getString("anoinscricao") + ".");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Ocorreu um Erro Durante a Pesquisa dos Dados!");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void lblgrouparentescoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblgrouparentescoMouseClicked
        // TODO add your handling code here: 
        jLabelShowFoto.setVisible(false);;
        setColor(lblgrouparentesco);
        resetColor(PaginaIn);
        resetColor(lblregisto);
        resetColor(lblListaMenu);
        resetColor(lblSatisfacaoBenificio);
        resetColor(lblMenuAdminitrador);
        resetColor(lblMenuQuatizacao);
        registo.setVisible(false);
        PaginaInicial.setVisible(false);
        ListaPainel.setVisible(false);
        AgregadoFamiliar.setVisible(true);
        SaticacaoBenifi.setVisible(false);
        AdministradorPanel.setVisible(false);
        ocupacao.setVisible(false);
        jCheckBoxAdicionarHer.setVisible(false);
        jCheckBoxAdicionarHer.setSelected(false);
        jPanelQuotizacao.setVisible(false);
        AnosdeNascimente();
    }//GEN-LAST:event_lblgrouparentescoMouseClicked

    private void lblListaMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblListaMenuMouseClicked
        // TODO add your handling code here:
        listabeneficios();
        VerAgregado1Lista.setVisible(false);
        ListaPainel.setVisible(true);
        registo.setVisible(false);
        PaginaInicial.setVisible(false);
        AgregadoFamiliar.setVisible(false);
        SaticacaoBenifi.setVisible(false);
        AdministradorPanel.setVisible(false);
        jButtonApagarBenifficario.setVisible(false);
        jPanelQuotizacao.setVisible(false);
        setColor(lblListaMenu);
        resetColor(PaginaIn);
        resetColor(lblregisto);
        resetColor(lblgrouparentesco);
        resetColor(lblSatisfacaoBenificio);
        resetColor(lblMenuAdminitrador);
        resetColor(lblMenuQuatizacao);

    }//GEN-LAST:event_lblListaMenuMouseClicked

    private void masculinoAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masculinoAActionPerformed
        // TODO add your handling code here:
        if (masculinoA.isSelected() == true) {
            jLabelBsexoAF.setForeground(Color.black);
        }
    }//GEN-LAST:event_masculinoAActionPerformed

    private void VerAgregadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VerAgregadoMouseClicked
        // TODO add your handling code here:
        cbd.executaSQL("select * from cliente, contatos where bi like'%" + txtbibeneficiario.getText() + "%'");
        if (txtbibeneficiario.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "O Campo BI Beneficiário está Vazio!");
        } else {
            try {
                cbd.resultado.first();
                AgregadoFamiliarfoto.setIcon(ResizeImage(null, cbd.resultado.getBytes("foto")));
                lblShowbenificiario1.setText("Dados do Benificiário:");
                BIAF.setText("BI:");
                jLabelbi.setText(cbd.resultado.getString("bi"));
                lentraBi.setText(cbd.resultado.getString("letra"));
                txtbibeneficiario.setText(cbd.resultado.getString("bi"));
                jLabelnome.setText("Nome:");
                lblShowbenificiario.setText(cbd.resultado.getString("nome") + " " + cbd.resultado.getString("apelido"));
                jLabelcontacto.setText("Contacto:");
                lblShowAFcontacto.setText(cbd.resultado.getString("contacto"));

                try {
                    cbd.executaSQL("SELECT * FROM funcionario WHERE bi_funcionario=" + txtbibeneficiario.getText() + "");
                    cbd.resultado.next();
                    String nomeEm = cbd.resultado.getString("entidade_onde_trabalha");
                    nomeEm = "Funcionário";
                    ocupacao.setText(nomeEm);
                    ocupacao.setVisible(true);

                } catch (SQLException erro) {
                    cbd.executaSQL("SELECT * FROM militar k WHERE k.bi_militar=" + txtbibeneficiario.getText() + "");
                    cbd.resultado.next();
                    ocupacao.setVisible(true);
                    ocupacao.setText("Militar");
                }
                listaFamila();
                listaHerdeiro();
                jCheckBoxAdicionarHer.setVisible(true);
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, "Dados Não Localizado!" + erro);
            }
        }

    }//GEN-LAST:event_VerAgregadoMouseClicked

    private void btnAgregadoFamiliarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregadoFamiliarActionPerformed
        // TODO add your handling code here:
        String sexo;

        if (femeninoA.isSelected()) {
            sexo = "Femenino";
        } else {
            sexo = "Masculino";
        }

        if (txtbibeneficiario.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha todo o Campo Vazio!");
        } else if (txtnomebenificiario.getText().equals("")) {
            jLabelBnomeAF.setForeground(Color.red);
        } else if (txtapelidofamiliar.getText().equals("")) {
            jLabelBapleidoAF.setForeground(Color.red);
        } else if (jCBoxGraudeparentesco.getSelectedItem().equals("******")) {
            jLabelGrauParentesco.setForeground(Color.red);
        } else if (txtcodigo.getText().equals("")) {
            jLabelBcodigoAF.setForeground(Color.red);
        } else if (femeninoA.isSelected() == false && masculinoA.isSelected() == false) {
            jLabelBsexoAF.setForeground(Color.red);
        } else if (jComboBoxDiaNascAF.getSelectedItem().equals("----")) {
            jLabelBdiaAF.setForeground(Color.red);
        } else if (jComboBoxMesNascAF.getSelectedItem().equals("----")) {
            jLabelBmesAF.setForeground(Color.red);
        } else if (jComboBoxAnosNascAF.getSelectedItem().equals("1930")) {
            jLabelBanoAF.setForeground(Color.red);
        } else {
            try {
                PreparedStatement inserirFamlG = cbd.conexao.prepareStatement("INSERT INTO agregadofamiliar (codigo,nome,apelido,grau_parentesco,sexo,af_bi,ocupacao_parente,dia_Nascimento,data_nascimento,data) VALUE (?,?,?,?,?,?,?,?,?,?)");
                inserirFamlG.setString(1, txtcodigo.getText());
                inserirFamlG.setString(2, txtnomebenificiario.getText());
                inserirFamlG.setString(3, txtapelidofamiliar.getText());
                inserirFamlG.setString(4, (String) jCBoxGraudeparentesco.getSelectedItem());
                inserirFamlG.setString(5, sexo.toString());
                inserirFamlG.setString(6, txtbibeneficiario.getText());
                inserirFamlG.setString(7, ocupacao.getText());
                inserirFamlG.setString(8, (String) jComboBoxDiaNascAF.getSelectedItem() + " " + (String) jComboBoxMesNascAF.getSelectedItem());
                inserirFamlG.setString(9, (String) jComboBoxAnosNascAF.getSelectedItem());
                inserirFamlG.setString(10, data.getText());

                inserirFamlG.executeUpdate();

                Dialog l = new Dialog();
                l.setVisible(true);

                listaFamila();
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, "" + erro);

            }
        }

    }//GEN-LAST:event_btnAgregadoFamiliarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        int qtd = Integer.parseInt(txtbibeneficiario.getText());

        qtd = 1;

        String r = (new Integer(qtd)).toString();

        //   lblShowAFramo.setText("fgd"+r);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        listabeneficios();
        benificiarioLista.setVisible(true);
        agregadoFamiliarLista.setVisible(false);
        jPanelSatisfacaoBeni.setVisible(false);
        jPanelOrganizacao.setVisible(false);
        jButtonApagarBenifficario.setVisible(false);
        VerAgregado1Lista.setVisible(false);
        jButton4.setBackground(new java.awt.Color(0, 204, 204));
        jButton4.setForeground(Color.white);
        jButton5.setBackground(Color.white);
        jButton5.setForeground(new java.awt.Color(0, 153, 51));
        jButton6.setBackground(Color.white);
        jButton6.setForeground(new java.awt.Color(0, 204, 102));
        jButton2.setBackground(Color.white);
        jButton2.setForeground(new java.awt.Color(0, 153, 153));
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        ListaAgregadoFamiliar();
        benificiarioLista.setVisible(false);
        agregadoFamiliarLista.setVisible(true);
        jPanelSatisfacaoBeni.setVisible(false);
        jPanelOrganizacao.setVisible(false);
        jButtonApagarAF.setVisible(false);
        jButton5.setBackground(new java.awt.Color(0, 153, 51));
        jButton5.setForeground(Color.white);
        jButton4.setBackground(Color.white);
        jButton4.setForeground(new java.awt.Color(0, 204, 204));
        jButton6.setBackground(Color.white);
        jButton6.setForeground(new java.awt.Color(0, 204, 102));
        jButton2.setBackground(Color.white);
        jButton2.setForeground(new java.awt.Color(0, 153, 153));
    }//GEN-LAST:event_jButton5ActionPerformed

    private void lblMenuAdminitradorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMenuAdminitradorMouseClicked
        // TODO add your handling code here:
        jLabelShowFoto.setVisible(false);
        AdministradorPanel.setVisible(true);
        SaticacaoBenifi.setVisible(false);
        ListaPainel.setVisible(false);
        PaginaInicial.setVisible(false);
        registo.setVisible(false);
        AgregadoFamiliar.setVisible(false);
        jBApagarTodo.setVisible(false);
        jPanelQuotizacao.setVisible(false);
        NumeroTotalSolterio();
        NumeroTotalDivorciado();
        NumeroTotalCasado();
        listabeneficios();
        ListaAgregadoFamiliar();
        NumeroTotalFemenino();
        NumeroTotalMasculino();
        NumeroTotalMasculinoBenificiario();
        NumeroTotalFemeninoBenificiario();
        NumeroTotalMilitar();
        NumeroTotalQuandroPermanente();
        NumeroTotalPensionista();
        NumeroTotalReserva();
        NumeroTotalReforma();
        NumeroTotalFuncionario();
        NumeroTotalFuncionarioActivo();
        NumeroTotalFuncionarioContratado();
        NumeroTotalFuncionarioReformado();
        TotalSatifacao();
        setColor(lblMenuAdminitrador);
        resetColor(lblSatisfacaoBenificio);
        resetColor(lblListaMenu);
        resetColor(lblgrouparentesco);
        resetColor(lblregisto);
        resetColor(PaginaIn);
        resetColor(lblMenuQuatizacao);

    }//GEN-LAST:event_lblMenuAdminitradorMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        listasatisfacaoBenicicario();
        jPanelSatisfacaoBeni.setVisible(true);
        benificiarioLista.setVisible(false);
        agregadoFamiliarLista.setVisible(false);
        jPanelOrganizacao.setVisible(false);
        jButtonApagarSB.setVisible(false);
        jButton6.setBackground(new java.awt.Color(0, 204, 102));
        jButton6.setForeground(Color.white);
        jButton5.setBackground(Color.white);
        jButton5.setForeground(new java.awt.Color(0, 153, 51));
        jButton4.setBackground(Color.white);
        jButton4.setForeground(new java.awt.Color(0, 204, 204));
        jButton2.setBackground(Color.white);
        jButton2.setForeground(new java.awt.Color(0, 153, 153));
    }//GEN-LAST:event_jButton6ActionPerformed

    private void lblSatisfacaoBenificioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSatisfacaoBenificioMouseClicked
        // TODO add your handling code here:
        jLabelShowFoto.setVisible(false);;
        SaticacaoBenifi.setVisible(true);
        ListaPainel.setVisible(false);
        PaginaInicial.setVisible(false);
        registo.setVisible(false);
        AgregadoFamiliar.setVisible(false);
        AdministradorPanel.setVisible(false);
        jPanelQuotizacao.setVisible(false);
        setColor(lblSatisfacaoBenificio);
        resetColor(PaginaIn);
        resetColor(lblregisto);
        resetColor(lblgrouparentesco);
        resetColor(lblListaMenu);
        resetColor(lblMenuAdminitrador);
        resetColor(lblMenuQuatizacao);
    }//GEN-LAST:event_lblSatisfacaoBenificioMouseClicked

    private void jCheckDeleteAFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckDeleteAFActionPerformed
        // TODO add your handling code here:
        if (jCheckDeleteAF.isSelected()) {
            // jBApagarTodoAF.setVisible(true);
            jBApagarTodo.setVisible(true);
            jCheckDelete.setSelected(false);
            jBApagarTodo.setText("Apagar Agregado Familiar");

        } else {
            // jBApagarTodoAF.setVisible(false);
            jBApagarTodo.setVisible(false);

        }
    }//GEN-LAST:event_jCheckDeleteAFActionPerformed

    private void jCheckDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckDeleteActionPerformed
        // TODO add your handling code here:
        if (jCheckDelete.isSelected()) {
            jBApagarTodo.setVisible(true);
            jCheckDeleteAF.setSelected(false);
            jBApagarTodo.setText("Apagar Benificiario");
        } else {
            jBApagarTodo.setVisible(false);
        }
    }//GEN-LAST:event_jCheckDeleteActionPerformed

    private void jBApagarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBApagarTodoActionPerformed
        // TODO add your handling code here:
        if (jBApagarTodo.getText() == "Apagar Benificiario") {
            int p = JOptionPane.showConfirmDialog(null, "Deseja Apagar Todos os Dados de Benificiários?", "Apagar", JOptionPane.YES_NO_OPTION);
            if (p == 0) {
                try {
                    PreparedStatement pst = cbd.conexao.prepareStatement("delete from cliente");
                    pst.execute();
                    listabeneficios();
                    ListaAgregadoFamiliar();
                    jCheckDelete.setSelected(false);
                    jBApagarTodo.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Dados Apagados com sucesso!");
                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null, "Erro no Sql!" + erro);
                }
            }
        } else if (jBApagarTodo.getText() == "Apagar Agregado Familiar") {
            int p = JOptionPane.showConfirmDialog(null, "Deseja Apagar Todos os Dados do Agregado Familiar?", "Apagar", JOptionPane.YES_NO_OPTION);
            if (p == 0) {
                try {
                    PreparedStatement pst = cbd.conexao.prepareStatement("delete from agregadofamiliar");
                    pst.execute();
                    listabeneficios();
                    ListaAgregadoFamiliar();
                    jCheckDeleteAF.setSelected(false);
                    jBApagarTodo.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Dados Apagados com sucesso!");
                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null, "Erro no Sql!" + erro);
                }
            }
        }
    }//GEN-LAST:event_jBApagarTodoActionPerformed

    private void txtcodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodigoActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        nome();
        if (txtcodigoAF.getText().equals("")) {
            jLabelTitleCodigo.setForeground(Color.red);
        } else if (jComboBoxBenificaio.getSelectedItem().equals("------------------") || jComboOperador.getSelectedItem().equals("------------------")) {
            JOptionPane.showMessageDialog(null, "Preencha todo o Campo Vazio!");
        } else {
            try {
                PreparedStatement inserirFamlG = cbd.conexao.prepareStatement("INSERT INTO satisfacao_benificario (codigo_agre_famil,nome,benificio,descricao,valor,operador,data,horas) VALUE (?,?,?,?,?,?,?,?)");
                inserirFamlG.setString(1, txtcodigoAF.getText());
                inserirFamlG.setString(2, NomeAf.getText());
                inserirFamlG.setString(3, (String) jComboBoxBenificaio.getSelectedItem());
                inserirFamlG.setString(4, jTextdescricao.getText());
                inserirFamlG.setString(5, txtValor.getText());
                inserirFamlG.setString(6, (String) jComboOperador.getSelectedItem());
                inserirFamlG.setString(7, data.getText());
                inserirFamlG.setString(8, horas.getText());

                inserirFamlG.executeUpdate();

                JOptionPane.showMessageDialog(null, "Satisfação Registada com sucesso!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void txtcodigoAFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodigoAFKeyTyped
        // TODO add your handling code here:       

    }//GEN-LAST:event_txtcodigoAFKeyTyped

    private void jComboBoxBenificaioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxBenificaioActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jComboBoxBenificaioActionPerformed

    private void txtlocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtlocalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtlocalActionPerformed

    private void jbtnSelecaoFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSelecaoFotoActionPerformed
        // TODO add your handling code here:
        try {
            JFileChooser file = new JFileChooser();
            file.setCurrentDirectory(new File(System.getProperty("user.home")));

            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png", "jpeg");
            file.addChoosableFileFilter(filter);
            int result = file.showSaveDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = file.getSelectedFile();
                String path = selectedFile.getAbsolutePath();
                lbl_Image.setIcon(ResizeImage(path, null));
                imgPath = path;

            } else if (result == JFileChooser.CANCEL_OPTION) {
                System.out.print("No File Selected");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jbtnSelecaoFotoActionPerformed

    private void patenteMilitarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patenteMilitarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_patenteMilitarMouseClicked

    private void jCheckBoxPatenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxPatenteMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jCheckBoxPatenteMouseClicked

    private void jCheckBoxPatenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxPatenteActionPerformed
        // TODO add your handling code here:
        if (jCheckBoxPatente.isSelected()) {
            patenteMilitar.setEnabled(true);
            patenteMilitar.setBackground(Color.white);
            Activo.setEnabled(false);
            Contratado.setEnabled(false);
            Reformado.setEnabled(false);
            jLabelTitulo.setText("Data de Incorporação:");

            jCheckBoxReserva.setEnabled(true);
            jCheckBoxReforma.setEnabled(true);
            jCheckBoxPensionista.setEnabled(true);
            jCheckBoxQuadro.setEnabled(true);

            // showPatenteField.setSelected(true);
        } else {
            patenteMilitar.setEnabled(false);
            patenteMilitar.setBackground(Color.gray);
            Activo.setEnabled(true);
            Contratado.setEnabled(true);
            Reformado.setEnabled(true);
            jLabelTitulo.setText("Data de Incorporação / Admissão:");
            jCheckBoxReserva.setEnabled(false);
            jCheckBoxReforma.setEnabled(false);
            jCheckBoxPensionista.setEnabled(false);
            jCheckBoxQuadro.setEnabled(false);
            patenteMilitar.setText("");
            jCheckBoxReserva.setSelected(false);
            jCheckBoxReforma.setSelected(false);
            jCheckBoxPensionista.setSelected(false);
            jCheckBoxQuadro.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBoxPatenteActionPerformed

    private void jTableBenificiarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableBenificiarioMouseClicked
        // TODO add your handling code here:admin   
        jLabelShowFoto.setVisible(true);
        VerAgregado1Lista.setVisible(true);
        TableModel tabelalinha = jTableBenificiario.getModel();
        int index = jTableBenificiario.getSelectedRow();

        jLabelBiFotoSave.setText("" + tabelalinha.getValueAt(index, 6));

        String bifoto = jLabelBiFotoSave.getText();
        bifoto = bifoto.substring(0, bifoto.length() - 1);

        cbd.executaSQL("SELECT * FROM cliente WHERE bi=" + bifoto);
        try {

            if (cbd.resultado.next()) {
                jLabelShowFoto.setIcon(ResizeImage(null, cbd.resultado.getBytes("foto")));
                jLabeNomeFoto.setText(cbd.resultado.getString("nome"));
            } else {
                JOptionPane.showMessageDialog(null, "Image not Found");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        if (tabelalinha.getValueAt(index, 16).equals(true)) {
            jButtonApagarBenifficario.setVisible(true);
            jLabelApagarBenificiario.setText("" + bifoto);
        } else {
            jButtonApagarBenifficario.setVisible(false);
            jLabelApagarBenificiario.setText("");
        }

    }//GEN-LAST:event_jTableBenificiarioMouseClicked

    private void jTableBenificiarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableBenificiarioKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableBenificiarioKeyPressed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        if (jCheckBox1.isSelected()) {
            txtsenha.setEchoChar((char) 0);
        } else {
            txtsenha.setEchoChar('*');
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void txtcontactoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcontactoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcontactoKeyTyped

    private void txtcontactoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcontactoKeyReleased
        // TODO add your handling code here:
        jLabelContacto.setForeground(Color.black);
        try {
            cbd.executaSQL("select * from contatos where contacto like'%" + txtcontacto.getText() + "%'");
            cbd.resultado.first();

            if (cbd.resultado.getString("contacto").equals(txtcontacto.getText())) {
                AlertPrimarykey.setText("Esse Contacto já Existe!");
                btngravar.setEnabled(false);
                txtemail.setEnabled(false);
                txtbi.setEnabled(false);
                // txtcodigopostal.setEnabled(false); 
                jCheckBoxBairro.setEnabled(false);
            } else {
                btngravar.setEnabled(true);
                txtemail.setEnabled(true);
                txtbi.setEnabled(true);
                //txtcodigopostal.setEnabled(true); 
                jCheckBoxBairro.setEnabled(true);
                AlertPrimarykey.setText("");
            }
        } catch (Exception e) {
        }

        try {
            int i = Integer.parseInt(txtcontacto.getText());

        } catch (Exception e) {
            AlertPrimarykey.setText("Invalido!");
            btngravar.setEnabled(false);
        }
    }//GEN-LAST:event_txtcontactoKeyReleased

    private void txtbiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbiKeyReleased
        // TODO add your handling code here:
        jLabelBi.setForeground(Color.black);
        try {
            cbd.executaSQL("select * from cliente where bi like'%" + txtbi.getText() + "%'");
            cbd.resultado.first();

            if (cbd.resultado.getString("bi").equals(txtbi.getText())) {
                btngravar.setEnabled(false);
                AlertPrimaryKeyBI.setText("Esse Número de BI já Existe Registado!");
                jLabelInvalidoLetraBI.setText("");
                txtemail.setEnabled(false);
                txtcontacto.setEnabled(false);
                // txtcodigopostal.setEnabled(false); 
                jCheckBoxBairro.setEnabled(false);
            } else {
                btngravar.setEnabled(true);
                AlertPrimaryKeyBI.setText("");
                txtemail.setEnabled(true);
                txtcontacto.setEnabled(true);
                // txtcodigopostal.setEnabled(true);
                jCheckBoxBairro.setEnabled(true);
            }
        } catch (Exception e) {
        }

        try {
            double i = Double.parseDouble(txtbi.getText());
            jLabelInvalidoLetraBI.setText("");
            btngravar.setEnabled(true);
        } catch (Exception e) {
            jLabelInvalidoLetraBI.setText("Invalido!");
            btngravar.setEnabled(false);
        }
    }//GEN-LAST:event_txtbiKeyReleased

    private void btnLimparRegistoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparRegistoActionPerformed
        // TODO add your handling code here:
        LimparCamposRegisto();

    }//GEN-LAST:event_btnLimparRegistoActionPerformed

    private void txtcodigoAFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodigoAFKeyReleased
        // TODO add your handling code here:
        try {
            cbd.pesquisar = cbd.conexao.createStatement();

            String sql = " select * from agregadofamiliar where codigo='" + txtcodigoAF.getText() + "'";
            cbd.resultado = cbd.pesquisar.executeQuery(sql);

            cbd.resultado.first();

            NomeAf.setText(cbd.resultado.getString("nome") + "" + cbd.resultado.getString("apelido"));

        } catch (Exception erro) {
            NomeAf.setText("");
        }
        jLabelTitleCodigo.setForeground(Color.black);
    }//GEN-LAST:event_txtcodigoAFKeyReleased

    private void txtnomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnomeKeyReleased
        // TODO add your handling code here:
        jLabelNomeRegisto.setForeground(Color.black);
    }//GEN-LAST:event_txtnomeKeyReleased

    private void txtapelidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtapelidoKeyReleased
        // TODO add your handling code here:
        jLabelApelidoRegisto.setForeground(Color.black);
    }//GEN-LAST:event_txtapelidoKeyReleased

    private void jCheckBoxBairroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxBairroMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jCheckBoxBairroMouseClicked

    private void txtbairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbairroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbairroActionPerformed

    private void VerAgregado1ListaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VerAgregado1ListaMouseClicked
        // TODO add your handling code here:
        String bifoto = jLabelBiFotoSave.getText();
        bifoto = bifoto.substring(0, bifoto.length() - 1);

        try {
            cbd.executaSQL("SELECT * FROM cliente, bairro c, contatos t WHERE bi=" + bifoto + " and bi=c.bi_bairro and bi=t.bi_contacto");

            if (cbd.resultado.next()) {
                // jLabelShowFoto.setIcon(ResizeImage(null, cbd.resultado.getBytes("foto")));
                Zona ver = new Zona();
                ver.fotoeditar.setIcon(ResizeImage(null, cbd.resultado.getBytes("foto")));
                ver.editarnome.setText(this.cbd.resultado.getString("nome") + " " + cbd.resultado.getString("apelido"));
                ver.editardataaniver.setText(this.cbd.resultado.getString("dia_Nascimento") + " " + cbd.resultado.getString("ano_Nascimento"));
                ver.editarestadocivil.setText(this.cbd.resultado.getString("estado_civil"));
                ver.editarsexo.setText(this.cbd.resultado.getString("sexo"));
                ver.editarbi.setText(this.cbd.resultado.getString("bi") + "" + cbd.resultado.getString("letra"));
                ver.EditarContacto.setText(this.cbd.resultado.getString("t.contacto"));
                ver.editarpai.setText(this.cbd.resultado.getString("pai"));
                ver.editarmae.setText(this.cbd.resultado.getString("mae"));
                ver.editarpais.setText(this.cbd.resultado.getString("pais"));
                ver.editarprovincia.setText(this.cbd.resultado.getString("provincia"));
                ver.editardistrito.setText(this.cbd.resultado.getString("distrito"));
                ver.editarlocalidade.setText(this.cbd.resultado.getString("localidade"));
                ver.editarDataRegistada.setText(this.cbd.resultado.getString("data"));
                ver.editarhoras.setText(this.cbd.resultado.getString("hora"));
                //bairro show

                ver.editarbairro.setText(this.cbd.resultado.getString("c.bairro"));
                ver.editarmorada.setText(this.cbd.resultado.getString("c.Morada"));
                ver.editarBlocalidade.setText(this.cbd.resultado.getString("c.Localidade"));
                ver.editarBdistrito.setText(this.cbd.resultado.getString("c.distrito"));
                ver.editarBprovincia.setText(this.cbd.resultado.getString("c.provincia"));

                try {
                    cbd.executaSQL("SELECT * FROM militar k WHERE k.bi_militar=" + bifoto + " ");
                    cbd.resultado.last();
                    ver.editarformado.setText("Militar:");
                    ver.editarendtidadetrabalha.setText("Patente:");
                    ver.lblpatente.setText("Patente: " + this.cbd.resultado.getString("k.patente"));
                    ver.Vistashowsituacao.setText("Situação: " + this.cbd.resultado.getString("k.grau"));
                    ver.editarendtidadetrabalha.setText(this.cbd.resultado.getString("k.entidade_onde_trabalho"));
                    ver.editarLocalTrabalho.setText(this.cbd.resultado.getString("k.local_trabalho"));
                    ver.editarRamoFADM.setText(this.cbd.resultado.getString("k.ramo_fadm"));
                    ver.editarprofissao.setText(this.cbd.resultado.getString("k.profissao"));
                    ver.editarIA.setText(("Data de Incorporação:"));
                    ver.ediatarshowdataIA.setText(this.cbd.resultado.getString("k.data_incoporacao"));
                    ver.editarshowlocaltrabalho.setText(this.cbd.resultado.getString("k.local"));
                } catch (SQLException ero) {
                    cbd.executaSQL("SELECT * FROM funcionario WHERE bi_funcionario=" + bifoto + "");
                    cbd.resultado.first();
                    ver.editarformado.setText("Funcionario/a");
                    ver.lblpatente.setText("Situação: " + this.cbd.resultado.getString("situacao"));
                    ver.editarendtidadetrabalha.setText(this.cbd.resultado.getString("entidade_onde_trabalha"));
                    ver.editarLocalTrabalho.setText(this.cbd.resultado.getString("local_trabalho"));
                    ver.editarshowramo.setText(("Local de Emissão:"));
                    ver.editarRamoFADM.setText(this.cbd.resultado.getString("local"));
                    ver.editarprofissao.setText(this.cbd.resultado.getString("profissao"));
                    ver.editarIA.setText(("Data de Admissão:"));
                    ver.ediatarshowdataIA.setText(this.cbd.resultado.getString("data_admissao"));
                    ver.editarshowlocaltrabalho.setVisible(false);
                    ver.setlocal.setVisible(false);
                }

                /*   DefaultListModel lst = new DefaultListModel();
            
               do{  
                   if(cbd.resultado.getString("t.bi_contacto").equals(bifoto)){
                        lst.addElement(" "+cbd.resultado.getString("t.bi_contacto")); 
                   }
 
                          
                }while(cbd.resultado.next());
              
            jListTelefonica.setModel(lst)
              
                 */
                ver.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, "Falha na Busca desse Dado!");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }//GEN-LAST:event_VerAgregado1ListaMouseClicked

    private void jCheckBoxBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxBairroActionPerformed
        // TODO add your handling code here:
        jCheckBoxBairro.setForeground(Color.black);
        if (jCheckBoxBairro.isSelected()) {
            jPanelBairro.setVisible(true);
            txtmorada.setText("");
            txtbairro.setText("");
            txtBlocalidade.setText("");
            txtBdistrito.setText("");
            // txtcodigopostal.setText(""); 
            btngravar.setEnabled(true);
        } else {
            jPanelBairro.setVisible(false);
            btngravar.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBoxBairroActionPerformed

    private void jLabel84MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel84MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel84MouseClicked

    private void jLabel33MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MouseDragged
        // TODO add your handling code here:
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_jLabel33MouseDragged

    private void jLabel33MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MousePressed
        // TODO add your handling code here:
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLabel33MousePressed

    private void jLabel85MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel85MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel85MouseClicked

    private void jLabel86MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel86MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel86MouseClicked

    private void jLabel87MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel87MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel87MouseClicked

    private void jLabel90MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel90MouseDragged
        // TODO add your handling code here:
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_jLabel90MouseDragged

    private void jLabel90MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel90MousePressed
        // TODO add your handling code here:
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLabel90MousePressed

    private void jLabel91MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel91MouseDragged
        // TODO add your handling code here:
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_jLabel91MouseDragged

    private void jLabel91MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel91MousePressed
        // TODO add your handling code here:
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLabel91MousePressed

    private void jLabel92MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel92MouseDragged
        // TODO add your handling code here:
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_jLabel92MouseDragged

    private void jLabel92MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel92MousePressed
        // TODO add your handling code here:
        x = evt.getX();
        y = evt.getY();;
    }//GEN-LAST:event_jLabel92MousePressed

    private void jLabel93MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel93MouseDragged
        // TODO add your handling code here:
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_jLabel93MouseDragged

    private void jLabel93MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel93MousePressed
        // TODO add your handling code here:
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLabel93MousePressed

    private void jLabel94MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel94MouseDragged
        // TODO add your handling code here:
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_jLabel94MouseDragged

    private void jLabel94MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel94MousePressed
        // TODO add your handling code here:
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLabel94MousePressed

    private void ActivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActivoActionPerformed
        // TODO add your handling code here:
        if (Activo.isSelected()) {
            Reformado.setSelected(false);
            Contratado.setSelected(false);
            txtramo.setText("Campo Invalido ao Fuciconario");
            txtramo.setEnabled(false);
            jCheckBoxPatente.setEnabled(false);
            jCheckBoxReserva.setEnabled(false);
            jCheckBoxPensionista.setEnabled(false);
            jCheckBoxReforma.setEnabled(false);
            jCheckBoxQuadro.setEnabled(false);
            jLabelTitulo.setText("Amissão:");
            jCheckBoxReserva.setSelected(false);
            jCheckBoxReforma.setSelected(false);
            jCheckBoxPensionista.setSelected(false);
            jCheckBoxQuadro.setSelected(false);
        } else {
            jCheckBoxPatente.setEnabled(true);
            jCheckBoxReserva.setEnabled(true);
            jCheckBoxPensionista.setEnabled(true);
            jCheckBoxReforma.setEnabled(true);
            jCheckBoxQuadro.setEnabled(true);
            txtramo.setEnabled(true);
            txtramo.setText("");
            jLabelTitulo.setText("Data de Icorporação / Admissão:");
            jCheckBoxReserva.setEnabled(false);
            jCheckBoxReforma.setEnabled(false);
            jCheckBoxPensionista.setEnabled(false);
            jCheckBoxQuadro.setEnabled(false);
        }
    }//GEN-LAST:event_ActivoActionPerformed

    private void ReformadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReformadoActionPerformed
        // TODO add your handling code here:
        if (Reformado.isSelected()) {
            Activo.setSelected(false);
            txtramo.setEnabled(false);
            txtramo.setText("Campo Invalido ao Fuciconario");
            Contratado.setSelected(false);
            jCheckBoxPatente.setEnabled(false);
            jCheckBoxReserva.setEnabled(false);
            jCheckBoxPensionista.setEnabled(false);
            jCheckBoxReforma.setEnabled(false);
            jCheckBoxQuadro.setEnabled(false);
            jLabelTitulo.setText("Amissão:");
            jCheckBoxReserva.setSelected(false);
            jCheckBoxReforma.setSelected(false);
            jCheckBoxPensionista.setSelected(false);
            jCheckBoxQuadro.setSelected(false);
        } else {
            jCheckBoxPatente.setEnabled(true);
            jCheckBoxReserva.setEnabled(true);
            jCheckBoxPensionista.setEnabled(true);
            jCheckBoxReforma.setEnabled(true);
            jCheckBoxQuadro.setEnabled(true);
            txtramo.setEnabled(true);
            txtramo.setText("");
            jLabelTitulo.setText("Data de Icorporação / Admissão:");

            jCheckBoxReserva.setEnabled(false);
            jCheckBoxReforma.setEnabled(false);
            jCheckBoxPensionista.setEnabled(false);
            jCheckBoxQuadro.setEnabled(false);
        }
    }//GEN-LAST:event_ReformadoActionPerformed

    private void ContratadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContratadoActionPerformed
        // TODO add your handling code here:
        if (Contratado.isSelected()) {
            txtramo.setEnabled(false);
            txtramo.setText("Campo Invalido ao Fuciconario");
            Reformado.setSelected(false);
            Activo.setSelected(false);
            jCheckBoxPatente.setEnabled(false);
            jCheckBoxReserva.setEnabled(false);
            jCheckBoxPensionista.setEnabled(false);
            jCheckBoxReforma.setEnabled(false);
            jCheckBoxQuadro.setEnabled(false);
            jLabelTitulo.setText("Amissão:");
            jCheckBoxReserva.setSelected(false);
            jCheckBoxReforma.setSelected(false);
            jCheckBoxPensionista.setSelected(false);
            jCheckBoxQuadro.setSelected(false);
        } else {
            jCheckBoxPatente.setEnabled(true);
            jCheckBoxReserva.setEnabled(true);
            jCheckBoxPensionista.setEnabled(true);
            jCheckBoxReforma.setEnabled(true);
            jCheckBoxQuadro.setEnabled(true);
            txtramo.setEnabled(true);
            txtramo.setText("");
            jLabelTitulo.setText("Data de Icorporação / Admissão:");
            jCheckBoxReserva.setEnabled(false);
            jCheckBoxReforma.setEnabled(false);
            jCheckBoxPensionista.setEnabled(false);
            jCheckBoxQuadro.setEnabled(false);
        }
    }//GEN-LAST:event_ContratadoActionPerformed

    private void jCheckBoxReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxReservaActionPerformed
        // TODO add your handling code here:
        if (jCheckBoxReserva.isSelected()) {
            jCheckBoxReforma.setSelected(false);
            jCheckBoxPensionista.setSelected(false);
            jCheckBoxQuadro.setSelected(false);
        } else {
            /* jCheckBoxReforma.setEnabled(true);
           jCheckBoxPensionista.setEnabled(true);
           jCheckBoxQuadro.setEnabled(true); */
        }
    }//GEN-LAST:event_jCheckBoxReservaActionPerformed

    private void jCheckBoxReformaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxReformaActionPerformed
        // TODO add your handling code here:
        if (jCheckBoxReforma.isSelected()) {
            jCheckBoxReserva.setSelected(false);
            jCheckBoxPensionista.setSelected(false);
            jCheckBoxQuadro.setSelected(false);
        } else {
            /* jCheckBoxReforma.setEnabled(true);
           jCheckBoxPensionista.setEnabled(true);
           jCheckBoxQuadro.setEnabled(true); */
        }
    }//GEN-LAST:event_jCheckBoxReformaActionPerformed

    private void jCheckBoxPensionistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxPensionistaActionPerformed
        // TODO add your handling code here:
        if (jCheckBoxPensionista.isSelected()) {
            jCheckBoxReserva.setSelected(false);
            jCheckBoxReforma.setSelected(false);
            jCheckBoxQuadro.setSelected(false);
        } else {
            /* jCheckBoxReforma.setEnabled(true);
           jCheckBoxPensionista.setEnabled(true);
           jCheckBoxQuadro.setEnabled(true); */
        }
    }//GEN-LAST:event_jCheckBoxPensionistaActionPerformed

    private void jCheckBoxQuadroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxQuadroActionPerformed
        // TODO add your handling code here:
        if (jCheckBoxQuadro.isSelected()) {
            jCheckBoxReserva.setSelected(false);
            jCheckBoxReforma.setSelected(false);
            jCheckBoxPensionista.setSelected(false);
        } else {
            /* jCheckBoxReforma.setEnabled(true);
           jCheckBoxPensionista.setEnabled(true);
           jCheckBoxQuadro.setEnabled(true); */
        }
    }//GEN-LAST:event_jCheckBoxQuadroActionPerformed

    private void txtemailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtemailKeyReleased
        // TODO add your handling code here:

        /*try {
            cbd.executaSQL("select * from emails where email like'%" + txtemail.getText() + "%'");
            cbd.resultado.first();

            if (cbd.resultado.getString("email").equals(txtemail.getText())) {
                btngravar.setEnabled(false);
                jLabelMsgErro.setText("Esse Email já Existe!");
                txtcontacto.setEnabled(false);
                txtbi.setEnabled(false);
                //  txtcodigopostal.setEnabled(false);
                jCheckBoxBairro.setEnabled(false);
            } else {
                btngravar.setEnabled(true);
                jLabelMsgErro.setText("");
                txtcontacto.setEnabled(true);
                txtbi.setEnabled(true);
                //  txtcodigopostal.setEnabled(true);
                jCheckBoxBairro.setEnabled(true);
            }

        } catch (Exception e) {
        }*/
    }//GEN-LAST:event_txtemailKeyReleased

    private void jLabel89MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel89MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel89MouseClicked

    private void jLabel88MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel88MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel88MouseClicked

    private void txtcodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodigoKeyReleased
        // TODO add your handling code here:        
        try {
            cbd.executaSQL("select * from agregadofamiliar where codigo like'%" + txtcodigo.getText() + "%'");
            cbd.resultado.next();
            if (cbd.resultado.getString("codigo").equals(txtcodigo.getText())) {
                jLabelAlertPrimaryKey.setText("Esse codigo já Existe!");
                btnAgregadoFamiliar.setEnabled(false);
            } else {
                btnAgregadoFamiliar.setEnabled(true);
                jLabelAlertPrimaryKey.setText("");
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null,"Caracter invalido!");
        }

        try {
            int i = Integer.parseInt(txtcodigo.getText());
            AlertPrimaryKeyCodigo.setText("");
            btnAgregadoFamiliar.setEnabled(true);
        } catch (Exception e) {
            AlertPrimaryKeyCodigo.setText("Invalido!");
            btnAgregadoFamiliar.setEnabled(false);
        }
        jLabelBcodigoAF.setForeground(Color.black);

    }//GEN-LAST:event_txtcodigoKeyReleased

    private void txtnomebenificiarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnomebenificiarioKeyReleased
        // TODO add your handling code here:
        jLabelBnomeAF.setForeground(Color.black);
    }//GEN-LAST:event_txtnomebenificiarioKeyReleased

    private void txtapelidofamiliarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtapelidofamiliarKeyReleased
        // TODO add your handling code here:
        jLabelBapleidoAF.setForeground(Color.black);
    }//GEN-LAST:event_txtapelidofamiliarKeyReleased

    private void jComboBoxDiaNascAFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxDiaNascAFActionPerformed
        // TODO add your handling code here:
        if (jComboBoxDiaNascAF.getSelectedItem() != ("----")) {
            jLabelBdiaAF.setForeground(Color.black);
        }/*else }else */
    }//GEN-LAST:event_jComboBoxDiaNascAFActionPerformed

    private void jComboBoxMesNascAFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMesNascAFActionPerformed
        // TODO add your handling code here:
        if (jComboBoxMesNascAF.getSelectedItem() != ("----")) {
            jLabelBmesAF.setForeground(Color.black);
        }
    }//GEN-LAST:event_jComboBoxMesNascAFActionPerformed

    private void jComboBoxAnosNascAFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAnosNascAFActionPerformed
        // TODO add your handling code here:
        if (jComboBoxAnosNascAF.getSelectedItem() != ("1930")) {
            jLabelBanoAF.setForeground(Color.black);
        }
    }//GEN-LAST:event_jComboBoxAnosNascAFActionPerformed

    private void jCBoxGraudeparentescoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBoxGraudeparentescoActionPerformed
        // TODO add your handling code here:
        if (jCBoxGraudeparentesco.getSelectedItem() != ("******")) {
            jLabelGrauParentesco.setForeground(Color.black);
        }
    }//GEN-LAST:event_jCBoxGraudeparentescoActionPerformed

    private void femeninoAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_femeninoAActionPerformed
        // TODO add your handling code here:
        if (femeninoA.isSelected() == true) {
            jLabelBsexoAF.setForeground(Color.black);
        }
    }//GEN-LAST:event_femeninoAActionPerformed

    private void txtpaiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpaiKeyReleased
        // TODO add your handling code here
        jLabelPai.setForeground(Color.black);
    }//GEN-LAST:event_txtpaiKeyReleased

    private void txtmaeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmaeKeyReleased
        // TODO add your handling code here:
        jLabelMae.setForeground(Color.black);
    }//GEN-LAST:event_txtmaeKeyReleased

    private void txtDistritoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDistritoKeyReleased
        // TODO add your handling code here:
        jLabelDistritoBI.setForeground(Color.black);
    }//GEN-LAST:event_txtDistritoKeyReleased

    private void txtLocalidadeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocalidadeKeyReleased
        // TODO add your handling code here:
        jLabelLocalidadeBI.setForeground(Color.black);
    }//GEN-LAST:event_txtLocalidadeKeyReleased

    private void txtEntidadeOndeTrabalhoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntidadeOndeTrabalhoKeyReleased
        // TODO add your handling code here:
        jLabelEOT.setForeground(Color.black);
    }//GEN-LAST:event_txtEntidadeOndeTrabalhoKeyReleased

    private void txtLocalTrabalhoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocalTrabalhoKeyReleased
        // TODO add your handling code here:
        jLabelLtrabalha.setForeground(Color.black);
    }//GEN-LAST:event_txtLocalTrabalhoKeyReleased

    private void txtProfissaoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProfissaoKeyReleased
        // TODO add your handling code here:
        jLabelProfissao.setForeground(Color.black);
    }//GEN-LAST:event_txtProfissaoKeyReleased

    private void txtlocalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtlocalKeyReleased
        // TODO add your handling code here:
        jLabelLocal.setForeground(Color.black);
    }//GEN-LAST:event_txtlocalKeyReleased

    private void txtmoradaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmoradaKeyReleased
        // TODO add your handling code here:
        jLabelEnderencoBairro.setForeground(Color.white);
    }//GEN-LAST:event_txtmoradaKeyReleased

    private void txtbairroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbairroKeyReleased
        // TODO add your handling code here:
        jLabelBairro.setForeground(Color.white);
    }//GEN-LAST:event_txtbairroKeyReleased

    private void txtBlocalidadeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBlocalidadeKeyReleased
        // TODO add your handling code here:
        jLabelBlocalidade.setForeground(Color.white);
    }//GEN-LAST:event_txtBlocalidadeKeyReleased

    private void txtBdistritoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBdistritoKeyReleased
        // TODO add your handling code here:
        jLabelBdistrito.setForeground(Color.white);
    }//GEN-LAST:event_txtBdistritoKeyReleased

    private void jComboBoxBProvinciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxBProvinciaActionPerformed
        // TODO add your handling code here:
        if (jComboBoxBProvincia.getSelectedItem() != "----") {
            jLabelBprovincia.setForeground(Color.white);
        }
    }//GEN-LAST:event_jComboBoxBProvinciaActionPerformed

    private void jComboBoxProvinciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxProvinciaActionPerformed
        // TODO add your handling code here:
        if (jComboBoxProvincia.getSelectedItem() != "----") {
            jLabelProvinciaBI.setForeground(Color.black);
        }
    }//GEN-LAST:event_jComboBoxProvinciaActionPerformed

    private void jComboBoxEstadoCivilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxEstadoCivilActionPerformed
        // TODO add your handling code here:
        if (jComboBoxEstadoCivil.getSelectedItem() != "----") {
            jLabelEstadoCivil.setForeground(Color.black);
        }
    }//GEN-LAST:event_jComboBoxEstadoCivilActionPerformed

    private void jComboBoxDiaNascActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxDiaNascActionPerformed
        // TODO add your handling code here:
        if (jComboBoxDiaNasc.getSelectedItem() != "----") {
            jLabelNasDia.setForeground(Color.black);
        }
    }//GEN-LAST:event_jComboBoxDiaNascActionPerformed

    private void jComboBoxMesNascActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMesNascActionPerformed
        // TODO add your handling code here:
        if (jComboBoxMesNasc.getSelectedItem() != "----") {
            jLabelNasMes.setForeground(Color.black);
        }
    }//GEN-LAST:event_jComboBoxMesNascActionPerformed

    private void jComboBoxAnosNascActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAnosNascActionPerformed
        // TODO add your handling code here:
        if (jComboBoxAnosNasc.getSelectedItem() != "----") {
            jLabelNasAno.setForeground(Color.black);
        }
    }//GEN-LAST:event_jComboBoxAnosNascActionPerformed

    private void jComboBoxDiaNascIAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxDiaNascIAActionPerformed
        // TODO add your handling code here:
        if (jComboBoxDiaNascIA.getSelectedItem() != "----") {
            jLabelDIdia.setForeground(Color.black);
        }
    }//GEN-LAST:event_jComboBoxDiaNascIAActionPerformed

    private void jComboBoxMesNascIAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMesNascIAActionPerformed
        // TODO add your handling code here:
        if (jComboBoxMesNascIA.getSelectedItem() != "----") {
            jLabelDImes.setForeground(Color.black);
        }
    }//GEN-LAST:event_jComboBoxMesNascIAActionPerformed

    private void jComboBoxAnoIcorporacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAnoIcorporacaoActionPerformed
        // TODO add your handling code here:
        if (jComboBoxAnoIcorporacao.getSelectedItem() != "----") {
            jLabelDIAno.setForeground(Color.black);
        }
    }//GEN-LAST:event_jComboBoxAnoIcorporacaoActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
        if (jComboBox2.getSelectedItem() == "Oculos ") {
            jLabelBeneficio.setText("Oculos:");
            try {
                cbd.executaSQL("select * from satisfacao_benificario where benificio='Oculos '");
                cbd.resultado.first();

                int soma = 0;

                do {
                    if (cbd.resultado.getString("benificio") == null) {
                        soma = 0;
                    } else {
                        soma = soma + 1;
                    }
                } while (cbd.resultado.next());
                jLabelMultTotalASatisfacao.setText("" + soma);
            } catch (Exception erro) {
                //JOptionPane.showMessageDialog(null, erro);
            }
        } else if (jComboBox2.getSelectedItem() == "Subsidio de Estudos") {
            jLabelBeneficio.setText("Subsidio de Estudos:");
            TotalSatisfacaoItem();
        } else if (jComboBox2.getSelectedItem() == "Subsidio de Luto") {
            jLabelBeneficio.setText("Subsidio de Luto:");
            TotalSatisfacaoItem();
        } else if (jComboBox2.getSelectedItem() == "Emprestimo") {
            jLabelBeneficio.setText("Emprestimo:");
            TotalSatisfacaoItem();
        } else if (jComboBox2.getSelectedItem() == "Outros") {
            jLabelBeneficio.setText("Outros:");
            TotalSatisfacaoItem();
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    public void TotalSatisfacaoItem() {
        try {
            cbd.executaSQL("select * from satisfacao_benificario where benificio='" + jComboBox2.getSelectedItem() + "' ");
            cbd.resultado.first();

            int soma = 0;

            do {
                if (cbd.resultado.getString("benificio") == null) {
                    soma = 0;
                } else {
                    soma = soma + 1;
                }
            } while (cbd.resultado.next());
            jLabelMultTotalASatisfacao.setText("" + soma);
        } catch (Exception erro) {
            jLabelMultTotalASatisfacao.setText("0");
        }
    }

    public void TotalSatifacao() {
        try {
            cbd.executaSQL("select * from satisfacao_benificario");
            cbd.resultado.first();

            int soma = 0;

            do {
                if (cbd.resultado.getString("benificio") == null) {
                    soma = 0;
                } else {
                    soma = soma + 1;
                }
            } while (cbd.resultado.next());
            jLabelTotalSBenf.setText("" + soma + ".");
        } catch (Exception erro) {
            jLabelTotalSBenf.setText("0");
        }
    }

    private void jComboBox2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox2MouseClicked
        // TODO add your handling code here
    }//GEN-LAST:event_jComboBox2MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        ListaLocalTrabalhoMilitar();
        jPanelOrganizacao.setVisible(true);
        benificiarioLista.setVisible(false);
        agregadoFamiliarLista.setVisible(false);
        jPanelSatisfacaoBeni.setVisible(false);
        jLabelListaTrabalho.setVisible(false);
        jLabelSaveLocalTrabalho.setVisible(false);
        jButton6.setBackground(Color.white);
        jButton6.setForeground(new java.awt.Color(0, 204, 102));
        jButton5.setBackground(Color.white);
        jButton5.setForeground(new java.awt.Color(0, 153, 51));
        jButton2.setBackground(new java.awt.Color(0, 153, 153));
        jButton2.setForeground(Color.white);
        jButton4.setBackground(Color.white);
        jButton4.setForeground(new java.awt.Color(0, 204, 204));
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBoxTrabalhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTrabalhoActionPerformed
        // TODO add your handling code here:
        if (jComboBoxTrabalho.getSelectedItem() == "Militar") {
            ListaLocalTrabalhoMilitar();
            jScrollPaneMilitar.setVisible(true);
            jScrollPaneFuncionario.setVisible(false);
            jLabelListaTrabalho.setVisible(false);
            jLabelSaveLocalTrabalho.setVisible(false);
            jPanelPaginaTrabalhoInfo.setVisible(false);
            jComboBoxTrabalho.setBackground(new java.awt.Color(0, 153, 102));
        } else if (jComboBoxTrabalho.getSelectedItem() == "Funcionário") {
            ListaLocalTrabalhoFunicioanrio();
            jScrollPaneMilitar.setVisible(false);
            jScrollPaneFuncionario.setVisible(true);
            jLabelListaTrabalho.setVisible(false);
            jLabelSaveLocalTrabalho.setVisible(false);
            jPanelPaginaTrabalhoInfo.setVisible(false);
            jComboBoxTrabalho.setBackground(new java.awt.Color(0, 153, 102));
        } else {
            jPanelPaginaTrabalhoInfo.setVisible(true);
            jScrollPaneMilitar.setVisible(false);
            jScrollPaneFuncionario.setVisible(false);
            jLabelListaTrabalho.setVisible(false);
            jLabelSaveLocalTrabalho.setVisible(false);
        }
    }//GEN-LAST:event_jComboBoxTrabalhoActionPerformed

    private void jTableLocalTrabalhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableLocalTrabalhoMouseClicked
        // TODO add your handling code here:
        jLabelListaTrabalho.setVisible(true);
        jLabelSaveLocalTrabalho.setVisible(true);
        TableModel tabelalinhaTrabalho = jTableLocalTrabalho.getModel();
        int index = jTableLocalTrabalho.getSelectedRow();

        jLabelSaveLocalTrabalho.setText("" + tabelalinhaTrabalho.getValueAt(index, 5));

        String local_Trabalho = jLabelSaveLocalTrabalho.getText();
    }//GEN-LAST:event_jTableLocalTrabalhoMouseClicked

    private void jLabelListaTrabalhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelListaTrabalhoMouseClicked
        // TODO add your handling code here:
        if (jComboBoxTrabalho.getSelectedItem() == "Militar") {
            try {
                cbd.pesquisar = cbd.conexao.createStatement();
                String sql = ("select * from cliente,militar m where bi=m.bi_militar and m.local_trabalho='" + jLabelSaveLocalTrabalho.getText() + "' ");
                cbd.resultado = cbd.pesquisar.executeQuery(sql);

                DefaultTableModel tm = (DefaultTableModel) jTableLocalTrabalho.getModel();
                tm.setRowCount(0);

                int soma = 0;

                while (cbd.resultado.next()) {

                    int num = cbd.resultado.getInt("m.codigo");
                    num = 1;
                    soma = soma + num;

                    Object o[] = {soma, cbd.resultado.getString("nome") + " " + cbd.resultado.getString("apelido"), cbd.resultado.getString("m.patente"), cbd.resultado.getString("m.grau"), cbd.resultado.getString("m.entidade_onde_trabalho"), cbd.resultado.getString("m.local_trabalho"), cbd.resultado.getString("m.ramo_fadm"), cbd.resultado.getString("m.profissao"), cbd.resultado.getString("m.data_incoporacao"), cbd.resultado.getString("m.local")};
                    tm.addRow(o);
                }
                jLabelTotalTrabalhoMilitarunico.setText("Total " + jLabelSaveLocalTrabalho.getText() + " : " + soma);
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, "" + erro);
            }
        } else if (jComboBoxTrabalho.getSelectedItem() == "Funcionário") {
            try {
                cbd.pesquisar = cbd.conexao.createStatement();
                String sql = ("select * from cliente, funcionario f where bi=f.bi_funcionario and f.entidade_onde_trabalha='" + jLabelSaveLocalTrabalho.getText() + "' ");
                cbd.resultado = cbd.pesquisar.executeQuery(sql);

                DefaultTableModel tm = (DefaultTableModel) jTableFuncionario.getModel();
                tm.setRowCount(0);

                int soma = 0;

                while (cbd.resultado.next()) {

                    int num = cbd.resultado.getInt("f.codigo");
                    num = 1;
                    soma = soma + num;

                    Object o[] = {soma, cbd.resultado.getString("nome") + " " + cbd.resultado.getString("apelido"), cbd.resultado.getString("f.situacao"), cbd.resultado.getString("f.entidade_onde_trabalha"), cbd.resultado.getString("f.local_trabalho"), cbd.resultado.getString("f.profissao"), cbd.resultado.getString("f.data_admissao"), cbd.resultado.getString("f.local")};
                    tm.addRow(o);
                }
                jLabelTotalTrabalhoMilitarunico.setText("Total " + jLabelSaveLocalTrabalho.getText() + " : " + soma);
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, "" + erro);
            }
        }


    }//GEN-LAST:event_jLabelListaTrabalhoMouseClicked

    private void jTableFuncionarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableFuncionarioMouseClicked
        // TODO add your handling code here:
        jLabelListaTrabalho.setVisible(true);
        jLabelSaveLocalTrabalho.setVisible(true);
        TableModel tabelalinhaTrabalho = jTableFuncionario.getModel();
        int index = jTableFuncionario.getSelectedRow();

        jLabelSaveLocalTrabalho.setText("" + tabelalinhaTrabalho.getValueAt(index, 3));

        String local_Trabalho = jLabelSaveLocalTrabalho.getText();
    }//GEN-LAST:event_jTableFuncionarioMouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here: 
        if (jTextFieldPesquisaNomeSB.getText().equals("")) {
            jLabelVazioSB.setText("Preencha o Campo Vazio!");
        } else {
            try {
                cbd.pesquisar = cbd.conexao.createStatement();
                String sql = " select * from satisfacao_benificario where nome like '%" + jTextFieldPesquisaNomeSB.getText() + "%' ";
                cbd.resultado = cbd.pesquisar.executeQuery(sql);

                DefaultTableModel tm = (DefaultTableModel) jTableSatisfacao.getModel();
                tm.setRowCount(0);

                int soma = 0;
                int totalValor = 0;

                while (cbd.resultado.next()) {

                    int num = cbd.resultado.getInt("valor");
                    num = 1;
                    soma = soma + num;
                    int valor = cbd.resultado.getInt("valor");
                    totalValor = totalValor + valor;

                    Object o[] = {soma, cbd.resultado.getString("codigo_agre_famil"), cbd.resultado.getString("nome"), cbd.resultado.getString("benificio"), cbd.resultado.getString("descricao"), cbd.resultado.getString("valor"), cbd.resultado.getString("operador"), cbd.resultado.getString("data"), cbd.resultado.getString("horas"), cbd.resultado.getString("numeracao")};
                    tm.addRow(o);
                }

                jLabelValor.setText("Valor Total: " + totalValor + " Mtn.");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "" + e);
            }
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jTextFieldPesquisaNomeSBKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPesquisaNomeSBKeyReleased
        // TODO add your handling code here:
        jLabelVazioSB.setText("");
    }//GEN-LAST:event_jTextFieldPesquisaNomeSBKeyReleased

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        if (jComboBoxPesquiarBINome.getSelectedItem() == "Nome") {
            if (jTextFieldPesquisarB.getText().equals("")) {
                jLabelVazioB.setText("Preencha o Campo Vazio!");
            } else {
                try {
                    cbd.pesquisar = cbd.conexao.createStatement();

                    String sql = " SELECT * FROM cliente, contatos c where nome like'%" + jTextFieldPesquisarB.getText() + "%' and bi=c.bi_contacto";
                    cbd.resultado = cbd.pesquisar.executeQuery(sql);

                    DefaultTableModel tm = (DefaultTableModel) jTableBenificiario.getModel();
                    tm.setRowCount(0);

                    int soma = 0;

                    while (cbd.resultado.next()) {

                        soma = soma + 1;

                        Object o[] = {soma, cbd.resultado.getString("nome"), cbd.resultado.getString("apelido"), cbd.resultado.getString("dia_Nascimento") + " " + cbd.resultado.getString("ano_Nascimento"), cbd.resultado.getString("estado_civil"), cbd.resultado.getString("sexo"), cbd.resultado.getString("bi") + cbd.resultado.getString("letra"), cbd.resultado.getString("c.contacto"), cbd.resultado.getString("pai"), cbd.resultado.getString("mae"), cbd.resultado.getString("pais"), cbd.resultado.getString("provincia"), cbd.resultado.getString("distrito"), cbd.resultado.getString("localidade"), cbd.resultado.getString("hora"), cbd.resultado.getString("data"),};
                        tm.addRow(o);
                    }

                    TotalListaBenificiario.setText("Total de Benificiários: " + soma + ".");
                    TAbenificiario.setText("" + soma + ".");

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "" + e);
                }
            }
        } else if (jComboBoxPesquiarBINome.getSelectedItem() == "BI") {
            if (jTextFieldPesquisarB.getText().equals("")) {
                jLabelVazioB.setText("Preencha o Campo Vazio!");
            } else {
                try {
                    cbd.pesquisar = cbd.conexao.createStatement();

                    String sql = " SELECT * FROM cliente, contatos c where bi like'%" + jTextFieldPesquisarB.getText() + "%' and bi=c.bi_contacto";
                    cbd.resultado = cbd.pesquisar.executeQuery(sql);

                    DefaultTableModel tm = (DefaultTableModel) jTableBenificiario.getModel();
                    tm.setRowCount(0);

                    int soma = 0;

                    while (cbd.resultado.next()) {

                        soma = soma + 1;

                        Object o[] = {soma, cbd.resultado.getString("nome"), cbd.resultado.getString("apelido"), cbd.resultado.getString("dia_Nascimento") + " " + cbd.resultado.getString("ano_Nascimento"), cbd.resultado.getString("estado_civil"), cbd.resultado.getString("sexo"), cbd.resultado.getString("bi") + cbd.resultado.getString("letra"), cbd.resultado.getString("c.contacto"), cbd.resultado.getString("pai"), cbd.resultado.getString("mae"), cbd.resultado.getString("pais"), cbd.resultado.getString("provincia"), cbd.resultado.getString("distrito"), cbd.resultado.getString("localidade"), cbd.resultado.getString("hora"), cbd.resultado.getString("data"),};
                        tm.addRow(o);
                    }

                    TotalListaBenificiario.setText("Total de Benificiários: " + soma + ".");
                    TAbenificiario.setText("" + soma + ".");

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "" + e);
                }
            }
        } else {
            jLabelVazioB.setText("Selecione o Tipo de Dados!");
        }


    }//GEN-LAST:event_jButton10ActionPerformed

    private void jTextFieldPesquisarBKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPesquisarBKeyReleased
        // TODO add your handling code here:
        jLabelVazioB.setText("");
    }//GEN-LAST:event_jTextFieldPesquisarBKeyReleased

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        if (jComboBoxAF.getSelectedItem() == "Nome do Agre. Familiar") {
            if (jTextFieldPesquisarAFL.getText().equals("")) {
                jLabelVazioLAF.setText("Peencha o Campo Vazio!");
            } else {
                try {
                    cbd.pesquisar = cbd.conexao.createStatement();

                    String sql = " SELECT * FROM agregadofamiliar a, cliente c where c.bi=af_bi and a.nome like'%" + jTextFieldPesquisarAFL.getText() + "%' ";
                    cbd.resultado = cbd.pesquisar.executeQuery(sql);

                    DefaultTableModel tm = (DefaultTableModel) jTableAgredadoFamiliar.getModel();
                    tm.setRowCount(0);

                    int soma = 0;

                    while (cbd.resultado.next()) {

                        int num = cbd.resultado.getInt("a.codigo");
                        num = 1;
                        soma = soma + num;

                        Object o[] = {soma, cbd.resultado.getString("a.codigo"), cbd.resultado.getString("a.nome"), cbd.resultado.getString("a.apelido"), cbd.resultado.getString("a.grau_parentesco"), cbd.resultado.getString("a.sexo"), cbd.resultado.getString("a.af_bi") + cbd.resultado.getString("c.letra"), cbd.resultado.getString("c.nome") + " " + cbd.resultado.getString("c.apelido"), cbd.resultado.getString("a.ocupacao_parente"), cbd.resultado.getString("a.dia_Nascimento") + " " + cbd.resultado.getString("a.data_nascimento"), cbd.resultado.getString("a.data")};
                        tm.addRow(o);
                    }

                    totalAgregado.setText("Total de Benificiários: " + soma + ".");
                    TAagregadoFamiliar.setText("" + soma + ".");

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "" + e);
                }
            }
        } else if (jComboBoxAF.getSelectedItem() == "Nome do Benificiário") {
            if (jTextFieldPesquisarAFL.getText().equals("")) {
                jLabelVazioLAF.setText("Peencha o Campo Vazio!");
            } else {
                try {
                    cbd.pesquisar = cbd.conexao.createStatement();

                    String sql = " SELECT * FROM agregadofamiliar a, cliente c where c.bi=af_bi and c.nome like'%" + jTextFieldPesquisarAFL.getText() + "%' ";
                    cbd.resultado = cbd.pesquisar.executeQuery(sql);

                    DefaultTableModel tm = (DefaultTableModel) jTableAgredadoFamiliar.getModel();
                    tm.setRowCount(0);

                    int soma = 0;

                    while (cbd.resultado.next()) {

                        int num = cbd.resultado.getInt("a.codigo");
                        num = 1;
                        soma = soma + num;

                        Object o[] = {soma, cbd.resultado.getString("a.codigo"), cbd.resultado.getString("a.nome"), cbd.resultado.getString("a.apelido"), cbd.resultado.getString("a.grau_parentesco"), cbd.resultado.getString("a.sexo"), cbd.resultado.getString("a.af_bi") + cbd.resultado.getString("c.letra"), cbd.resultado.getString("c.nome") + " " + cbd.resultado.getString("c.apelido"), cbd.resultado.getString("a.ocupacao_parente"), cbd.resultado.getString("a.dia_Nascimento") + " " + cbd.resultado.getString("a.data_nascimento"), cbd.resultado.getString("a.data")};
                        tm.addRow(o);
                    }

                    totalAgregado.setText("Total de Benificiários: " + soma + ".");
                    TAagregadoFamiliar.setText("" + soma + ".");

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "" + e);
                }
            }
        } else {
            jLabelVazioLAF.setText("Selecione o Tipo de Dado!");
        }

    }//GEN-LAST:event_jButton11ActionPerformed

    private void jTextFieldPesquisarAFLKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPesquisarAFLKeyReleased
        // TODO add your handling code here:
        jLabelVazioLAF.setText("");
    }//GEN-LAST:event_jTextFieldPesquisarAFLKeyReleased

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        if (jTextFieldPesquisarLLT.getText().equals("")) {
        } else {
            if (jComboBoxTrabalho.getSelectedItem() == "Militar") {

                try {
                    cbd.pesquisar = cbd.conexao.createStatement();
                    String sql = ("select * from cliente,militar m where bi=m.bi_militar and m.local_trabalho like'%" + jTextFieldPesquisarLLT.getText() + "%' ");
                    cbd.resultado = cbd.pesquisar.executeQuery(sql);

                    DefaultTableModel tm = (DefaultTableModel) jTableLocalTrabalho.getModel();
                    tm.setRowCount(0);

                    int soma = 0;

                    while (cbd.resultado.next()) {

                        int num = cbd.resultado.getInt("m.codigo");
                        num = 1;
                        soma = soma + num;

                        Object o[] = {soma, cbd.resultado.getString("nome") + " " + cbd.resultado.getString("apelido"), cbd.resultado.getString("m.patente"), cbd.resultado.getString("m.grau"), cbd.resultado.getString("m.entidade_onde_trabalho"), cbd.resultado.getString("m.local_trabalho"), cbd.resultado.getString("m.ramo_fadm"), cbd.resultado.getString("m.profissao"), cbd.resultado.getString("m.data_incoporacao"), cbd.resultado.getString("m.local")};
                        tm.addRow(o);
                    }
                    jLabelTotalTrabalhoMilitarunico.setText("Total " + jLabelSaveLocalTrabalho.getText() + " : " + soma);
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, "" + erro);
                }
            } else if (jComboBoxTrabalho.getSelectedItem() == "Funcionário") {
                try {
                    cbd.pesquisar = cbd.conexao.createStatement();
                    String sql = ("select * from cliente, funcionario f where bi=f.bi_funcionario and f.entidade_onde_trabalha like'%" + jTextFieldPesquisarLLT.getText() + "%' ");
                    cbd.resultado = cbd.pesquisar.executeQuery(sql);

                    DefaultTableModel tm = (DefaultTableModel) jTableFuncionario.getModel();
                    tm.setRowCount(0);

                    int soma = 0;

                    while (cbd.resultado.next()) {

                        int num = cbd.resultado.getInt("f.codigo");
                        num = 1;
                        soma = soma + num;

                        Object o[] = {soma, cbd.resultado.getString("nome") + " " + cbd.resultado.getString("apelido"), cbd.resultado.getString("f.situacao"), cbd.resultado.getString("f.entidade_onde_trabalha"), cbd.resultado.getString("f.local_trabalho"), cbd.resultado.getString("f.profissao"), cbd.resultado.getString("f.data_admissao"), cbd.resultado.getString("f.local")};
                        tm.addRow(o);
                    }
                    jLabelTotalTrabalhoMilitarunico.setText("Total " + jLabelSaveLocalTrabalho.getText() + " : " + soma);
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, "" + erro);
                }
            } else if (jComboBoxTrabalho.getSelectedItem() == "Selecione") {
                jComboBoxTrabalho.setBackground(Color.red);
            }
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
        if (jComboBox3.getSelectedItem().equals("Oculos ")) {
            try {
                cbd.pesquisar = cbd.conexao.createStatement();
                String sql = " select * from satisfacao_benificario where benificio like '" + jComboBox3.getSelectedItem() + "' ";
                cbd.resultado = cbd.pesquisar.executeQuery(sql);

                DefaultTableModel tm = (DefaultTableModel) jTableSatisfacao.getModel();
                tm.setRowCount(0);

                int soma = 0;
                int totalValor = 0;

                while (cbd.resultado.next()) {

                    int num = cbd.resultado.getInt("valor");
                    num = 1;
                    soma = soma + num;
                    int valor = cbd.resultado.getInt("valor");
                    totalValor = totalValor + valor;

                    Object o[] = {soma, cbd.resultado.getString("codigo_agre_famil"), cbd.resultado.getString("nome"), cbd.resultado.getString("benificio"), cbd.resultado.getString("descricao"), cbd.resultado.getString("valor"), cbd.resultado.getString("operador"), cbd.resultado.getString("data"), cbd.resultado.getString("horas"), cbd.resultado.getString("numeracao")};
                    tm.addRow(o);
                }

                jLabelValor.setText("Valor Total: " + totalValor + " Mtn.");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "" + e);
            }
        } else if (jComboBox3.getSelectedItem().equals("Subsidio de Luto")) {
            try {
                cbd.pesquisar = cbd.conexao.createStatement();
                String sql = " select * from satisfacao_benificario where benificio like '" + jComboBox3.getSelectedItem() + "' ";
                cbd.resultado = cbd.pesquisar.executeQuery(sql);

                DefaultTableModel tm = (DefaultTableModel) jTableSatisfacao.getModel();
                tm.setRowCount(0);

                int soma = 0;
                int totalValor = 0;

                while (cbd.resultado.next()) {

                    int num = cbd.resultado.getInt("valor");
                    num = 1;
                    soma = soma + num;
                    int valor = cbd.resultado.getInt("valor");
                    totalValor = totalValor + valor;

                    Object o[] = {soma, cbd.resultado.getString("codigo_agre_famil"), cbd.resultado.getString("nome"), cbd.resultado.getString("benificio"), cbd.resultado.getString("descricao"), cbd.resultado.getString("valor"), cbd.resultado.getString("operador"), cbd.resultado.getString("data"), cbd.resultado.getString("horas"), cbd.resultado.getString("numeracao")};
                    tm.addRow(o);
                }

                jLabelValor.setText("Valor Total: " + totalValor + " Mtn.");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "" + e);
            }
        } else if (jComboBox3.getSelectedItem().equals("Subsidio de Estudos")) {
            try {
                cbd.pesquisar = cbd.conexao.createStatement();
                String sql = " select * from satisfacao_benificario where benificio like '" + jComboBox3.getSelectedItem() + "' ";
                cbd.resultado = cbd.pesquisar.executeQuery(sql);

                DefaultTableModel tm = (DefaultTableModel) jTableSatisfacao.getModel();
                tm.setRowCount(0);

                int soma = 0;
                int totalValor = 0;

                while (cbd.resultado.next()) {

                    int num = cbd.resultado.getInt("valor");
                    num = 1;
                    soma = soma + num;
                    int valor = cbd.resultado.getInt("valor");
                    totalValor = totalValor + valor;

                    Object o[] = {soma, cbd.resultado.getString("codigo_agre_famil"), cbd.resultado.getString("nome"), cbd.resultado.getString("benificio"), cbd.resultado.getString("descricao"), cbd.resultado.getString("valor"), cbd.resultado.getString("operador"), cbd.resultado.getString("data"), cbd.resultado.getString("horas"), cbd.resultado.getString("numeracao")};
                    tm.addRow(o);
                }

                jLabelValor.setText("Valor Total: " + totalValor + " Mtn.");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "" + e);
            }
        } else if (jComboBox3.getSelectedItem().equals("Emprestimo")) {
            try {
                cbd.pesquisar = cbd.conexao.createStatement();
                String sql = " select * from satisfacao_benificario where benificio like '" + jComboBox3.getSelectedItem() + "' ";
                cbd.resultado = cbd.pesquisar.executeQuery(sql);

                DefaultTableModel tm = (DefaultTableModel) jTableSatisfacao.getModel();
                tm.setRowCount(0);

                int soma = 0;
                int totalValor = 0;

                while (cbd.resultado.next()) {

                    int num = cbd.resultado.getInt("valor");
                    num = 1;
                    soma = soma + num;
                    int valor = cbd.resultado.getInt("valor");
                    totalValor = totalValor + valor;

                    Object o[] = {soma, cbd.resultado.getString("codigo_agre_famil"), cbd.resultado.getString("nome"), cbd.resultado.getString("benificio"), cbd.resultado.getString("descricao"), cbd.resultado.getString("valor"), cbd.resultado.getString("operador"), cbd.resultado.getString("data"), cbd.resultado.getString("horas"), cbd.resultado.getString("numeracao")};
                    tm.addRow(o);
                }

                jLabelValor.setText("Valor Total: " + totalValor + " Mtn.");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "" + e);
            }
        } else if (jComboBox3.getSelectedItem().equals("Outros")) {
            try {
                cbd.pesquisar = cbd.conexao.createStatement();
                String sql = " select * from satisfacao_benificario where benificio like '" + jComboBox3.getSelectedItem() + "' ";
                cbd.resultado = cbd.pesquisar.executeQuery(sql);

                DefaultTableModel tm = (DefaultTableModel) jTableSatisfacao.getModel();
                tm.setRowCount(0);

                int soma = 0;
                int totalValor = 0;

                while (cbd.resultado.next()) {
                    int num = cbd.resultado.getInt("valor");
                    num = 1;
                    soma = soma + num;
                    int valor = cbd.resultado.getInt("valor");
                    totalValor = totalValor + valor;

                    Object o[] = {soma, cbd.resultado.getString("codigo_agre_famil"), cbd.resultado.getString("nome"), cbd.resultado.getString("benificio"), cbd.resultado.getString("descricao"), cbd.resultado.getString("valor"), cbd.resultado.getString("operador"), cbd.resultado.getString("data"), cbd.resultado.getString("horas"), cbd.resultado.getString("numeracao")};
                    tm.addRow(o);
                }

                jLabelValor.setText("Valor Total: " + totalValor + " Mtn.");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "" + e);
            }
        } else if (jComboBox3.getSelectedItem().equals("Selecione:")) {
            listasatisfacaoBenicicario();
        }
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void txtcodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodigoKeyTyped
        // TODO add your handling code here:           
    }//GEN-LAST:event_txtcodigoKeyTyped

    private void txtcodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodigoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodigoKeyPressed

    private void jTableAgredadoFamiliarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAgredadoFamiliarMouseClicked
        // TODO add your handling code here:
        TableModel tabelalinhadelete = jTableAgredadoFamiliar.getModel();
        int index = jTableAgredadoFamiliar.getSelectedRow();

        jLabelBiFotoSave.setText("" + tabelalinhadelete.getValueAt(index, 11));

        if (tabelalinhadelete.getValueAt(index, 11).equals(true)) {
            jLabel34.setText("" + tabelalinhadelete.getValueAt(index, 1));
            jButtonApagarAF.setVisible(true);
        } else {
            jLabel34.setText("");
            jButtonApagarAF.setVisible(false);
        }

    }//GEN-LAST:event_jTableAgredadoFamiliarMouseClicked

    private void jButtonApagarAFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonApagarAFActionPerformed
        // TODO add your handling code here:
        try {
            PreparedStatement pst = cbd.conexao.prepareStatement("delete from agregadofamiliar where codigo ='" + jLabel34.getText() + "'");
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dado Apagado com Sucesso!");
            ListaAgregadoFamiliar();
            jButtonApagarAF.setVisible(false);
        } catch (Exception erro) {
        }
    }//GEN-LAST:event_jButtonApagarAFActionPerformed

    private void jButtonApagarBenifficarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonApagarBenifficarioActionPerformed
        // TODO add your handling code here:
        try {
            PreparedStatement pst = cbd.conexao.prepareStatement("delete from cliente where bi ='" + jLabelApagarBenificiario.getText() + "'");
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dado Apagado com Sucesso!");
            listabeneficios();
            VerAgregado1Lista.setVisible(false);
            jButtonApagarBenifficario.setVisible(false);
        } catch (Exception erro) {
        }
    }//GEN-LAST:event_jButtonApagarBenifficarioActionPerformed

    private void jTableSatisfacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSatisfacaoMouseClicked
        // TODO add your handling code here:
        TableModel tabelalinhadelete = jTableSatisfacao.getModel();
        int index = jTableSatisfacao.getSelectedRow();

        if (tabelalinhadelete.getValueAt(index, 10).equals(true)) {
            jLabelApagarDI.setText("" + tabelalinhadelete.getValueAt(index, 9));
            jButtonApagarSB.setVisible(true);
        } else {
            jLabelApagarDI.setText("");
            jButtonApagarSB.setVisible(false);
        }
    }//GEN-LAST:event_jTableSatisfacaoMouseClicked

    private void jButtonApagarSBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonApagarSBActionPerformed
        // TODO add your handling code here:
        try {
            PreparedStatement pst = cbd.conexao.prepareStatement("delete from satisfacao_benificario where numeracao ='" + jLabelApagarDI.getText() + "'");
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dado Apagado com Sucesso!");
            listasatisfacaoBenicicario();
            jButtonApagarSB.setVisible(false);
        } catch (Exception erro) {
        }
    }//GEN-LAST:event_jButtonApagarSBActionPerformed

    private void jComboBoxPesquiarBINomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPesquiarBINomeActionPerformed
        // TODO add your handling code here:
        if (jComboBoxPesquiarBINome.getSelectedItem() == "Selecione:") {
            listabeneficios();
            jLabelVazioB.setText("");
        } else if (jComboBoxPesquiarBINome.getSelectedItem() == "Nome") {
            jLabelVazioB.setText("");
        } else if (jComboBoxPesquiarBINome.getSelectedItem() == "BI") {
            jLabelVazioB.setText("");
        }
    }//GEN-LAST:event_jComboBoxPesquiarBINomeActionPerformed

    private void jComboBoxAFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAFActionPerformed
        // TODO add your handling code here:   
        if (jComboBoxAF.getSelectedItem() == "Selecione:") {
            ListaAgregadoFamiliar();
            jLabelVazioLAF.setText("");
        } else if (jComboBoxAF.getSelectedItem() == "Nome do Agre. Familiar") {
            jLabelVazioLAF.setText("");
        } else if (jComboBoxAF.getSelectedItem() == "Nome do Benificiário") {
            jLabelVazioLAF.setText("");
        }
    }//GEN-LAST:event_jComboBoxAFActionPerformed

    private void patenteMilitarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_patenteMilitarKeyReleased
        // TODO add your handling code here:
        jLabelPatente.setForeground(Color.black);
    }//GEN-LAST:event_patenteMilitarKeyReleased

    private void lblMenuQuatizacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMenuQuatizacaoMouseClicked
        // TODO add your handling code here:
        jLabelShowFoto.setVisible(false);;
        resetColor(lblMenuAdminitrador);
        resetColor(lblSatisfacaoBenificio);
        resetColor(lblListaMenu);
        resetColor(lblgrouparentesco);
        resetColor(lblregisto);
        resetColor(PaginaIn);
        setColor(lblMenuQuatizacao);
        jPanelQuotizacao.setVisible(true);
        SaticacaoBenifi.setVisible(false);
        ListaPainel.setVisible(false);
        PaginaInicial.setVisible(false);
        registo.setVisible(false);
        AgregadoFamiliar.setVisible(false);
        AdministradorPanel.setVisible(false);
        jTextFieldValorJoia.setVisible(false);
        jLabelBTNPagar.setVisible(false);
        ListaTabelaQuotizacao();
        AnosdeNascimente();
    }//GEN-LAST:event_lblMenuQuatizacaoMouseClicked

    private void jCheckBoxAdicionarHerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxAdicionarHerActionPerformed
        // TODO add your handling code here:
        if (jCheckBoxAdicionarHer.isSelected() == false) {
            Herdeiro visualizar = new Herdeiro();
            visualizar.setVisible(false);
        } else {
            Herdeiro visualizar = new Herdeiro();
            visualizar.setVisible(true);
            visualizar.jLabelNomeBenificiarioH.setText(" (" + lblShowbenificiario.getText() + ")");
            visualizar.jLabelBIH.setText(txtbibeneficiario.getText());
        }
    }//GEN-LAST:event_jCheckBoxAdicionarHerActionPerformed

    private void jCheckBoxAdicionarHerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxAdicionarHerMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jCheckBoxAdicionarHerMouseClicked

    private void jLabel129MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel129MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel129MouseClicked

    private void jLabel130MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel130MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel130MouseDragged

    private void jLabel130MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel130MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel130MousePressed

    private void txtvalorQuotaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtvalorQuotaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtvalorQuotaKeyReleased

    private void txtvalorQuotaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtvalorQuotaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtvalorQuotaKeyTyped

    private void jComboBoxMesQuotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMesQuotaActionPerformed
        // TODO add your handling code here:
        if (jComboBoxMesQuota.getSelectedItem() == "Selecione:") {
            jLabelTituloQ1.setText("Joia:");

        } else if (jComboBoxMesQuota.getSelectedItem() == "Janeiro") {
            jLabelTituloQ1.setText("Valor:");
        }
    }//GEN-LAST:event_jComboBoxMesQuotaActionPerformed

    private void jComboBoxMesQuota1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMesQuota1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxMesQuota1ActionPerformed

    private void txtBIKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBIKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBIKeyReleased

    private void txtBIKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBIKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBIKeyTyped

    private void jButtonPagarQuotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPagarQuotaActionPerformed
        // TODO add your handling code here:
        try {
            cbd.pesquisar = cbd.conexao.createStatement();

            String sql = " SELECT * FROM quotizacao WHERE ano like '%" + jComboBoxAnoQuota2.getSelectedItem() + "%' and bi_benificiario like '%" + txtBI.getText() + "%' ";
            cbd.resultado = cbd.pesquisar.executeQuery(sql);
            cbd.resultado.first();

            if (jComboBoxAnoQuota2.getSelectedItem().equals("Selecione:") || txtBI.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Campo Vazio!");
            } else if (jComboBoxAnoQuota2.getSelectedItem().equals(cbd.resultado.getString("ano")) && txtBI.getText().equals(cbd.resultado.getString("bi_benificiario"))) {
                //JOptionPane.showMessageDialog(null, "Ja Existe!");
                String mes = String.valueOf(jComboBoxMesQuota.getSelectedItem());

                if (cbd.resultado.getString(mes) != (null)) {
                    // JOptionPane.showMessageDialog(null, "Ja Existe Esse Pagamento!");
                    DialogMessage payed = new DialogMessage();
                    payed.setVisible(true);
                    payed.titleS.setText("Esse Pagamento Já foi efectuado!");

                } else {
                    PreparedStatement pst = cbd.conexao.prepareStatement("update quotizacao set " + jComboBoxMesQuota.getSelectedItem() + "=? where ano like '%" + jComboBoxAnoQuota2.getSelectedItem() + "%' and bi_benificiario like '%" + txtBI.getText() + "%'");
                    pst.setString(1, txtvalorQuota.getText());
                    pst.execute();
                    Dialog pago = new Dialog();
                    pago.title.setText("Pago com Sucesso!");
                    pago.setVisible(true);
                    ListaTabelaQuotizacao();
                }
            }
        } catch (Exception erro) {
            try {
                PreparedStatement inserirHerdeiro = cbd.conexao.prepareStatement("INSERT INTO quotizacao (ano,bi_benificiario) VALUE (?,?)");
                inserirHerdeiro.setString(1, (String) jComboBoxAnoQuota2.getSelectedItem());
                inserirHerdeiro.setString(2, txtBI.getText());
                inserirHerdeiro.executeUpdate();

                PreparedStatement psts = cbd.conexao.prepareStatement("update quotizacao set " + jComboBoxMesQuota.getSelectedItem() + "=? where ano like '%" + jComboBoxAnoQuota2.getSelectedItem() + "%' and bi_benificiario like '%" + txtBI.getText() + "%' ");

                psts.setString(1, txtvalorQuota.getText());
                psts.execute();

                Dialog pago = new Dialog();
                pago.title.setText("Pago com Sucesso!");
                pago.setVisible(true);
                ListaTabelaQuotizacao();
            } catch (Exception erros) {
                JOptionPane.showMessageDialog(null, erro);
            }

        }
    }//GEN-LAST:event_jButtonPagarQuotaActionPerformed

    private void jComboBoxAnoQuota2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAnoQuota2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxAnoQuota2ActionPerformed

    private void jLabelBTNPagarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBTNPagarMouseClicked
        // TODO add your handling code here:
        if (txtBI.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Vazio!");
        } else if (jTextFieldValorJoia.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Vazio!");
        } else if (jComboBoxAnoQuota2.getSelectedItem().equals("Selecione:")) {
            JOptionPane.showMessageDialog(null, "Vazio!");
        } else {
            try {
                PreparedStatement inserirJoia = cbd.conexao.prepareStatement("INSERT INTO joia (joia,bi_benificiario,data,data_registo) VALUE (?,?,?,?)");
                inserirJoia.setString(1, jTextFieldValorJoia.getText());
                inserirJoia.setString(2, txtBI.getText());
                inserirJoia.setString(3, (String) jComboBoxAnoQuota2.getSelectedItem());
                inserirJoia.setString(4, data.getText());

                inserirJoia.executeUpdate();
                JOptionPane.showMessageDialog(null, "Sucesso!");
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro);
            }
        }


    }//GEN-LAST:event_jLabelBTNPagarMouseClicked

    private void txtvalorQuotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtvalorQuotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtvalorQuotaActionPerformed

    private void jCheckBoxPagarJoiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxPagarJoiaActionPerformed
        // TODO add your handling code here:
        if (jCheckBoxPagarJoia.isSelected()) {
            jTextFieldValorJoia.setVisible(true);
            jLabelBTNPagar.setVisible(true);
            jComboBoxMesQuota.setEnabled(false);
            txtvalorQuota.setEnabled(false);
            jButtonPagarQuota.setEnabled(false);
        } else {
            jTextFieldValorJoia.setVisible(false);
            jLabelBTNPagar.setVisible(false);
            //jComboBoxMesQuota.setEnabled(true);
            //txtvalorQuota.setEnabled(true);
            //jButtonPagarQuota.setEnabled(true);
            
             jComboBoxMesQuota.setEnabled(false);
            txtvalorQuota.setEnabled(false);
            jButtonPagarQuota.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBoxPagarJoiaActionPerformed

    public void JoiaNao(){
        try {
            cbd.executaSQL("select * from cliente c where c.bi like'%" + txtBI.getText() + "%' ");
            cbd.resultado.first();
            jLabelFotoQuota.setIcon(ResizeImage(null, cbd.resultado.getBytes("c.foto")));
            jLabelNomeQuo.setText(cbd.resultado.getString("c.nome") + " " + cbd.resultado.getString("c.apelido"));
            jLabelJoiaPAga.setText("Não foi Paga!");
            jLabelJoiaPAga.setForeground(new java.awt.Color(204,51,0));
            jLabelBIQuot.setText(cbd.resultado.getString("c.bi") + cbd.resultado.getString("c.letra"));
            jLabelContactoQuot.setText(cbd.resultado.getString("t.contacto"));
          
        } catch (Exception erro) {
            
        }
    }
    
    private void VerAgregado1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VerAgregado1MouseClicked
        // TODO add your handling code here:
        cbd.executaSQL("select * from cliente c, contatos t, joia where bi_benificiario like'%" + txtBI.getText() + "%' and bi_benificiario=c.bi and t.bi_contacto=c.bi ");
        if (txtBI.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "O Campo BI Beneficiário está Vazio!");
        } else {
            try {
                cbd.resultado.first();
                jLabelFotoQuota.setIcon(ResizeImage(null, cbd.resultado.getBytes("c.foto")));
                jLabelNomeQuo.setText(cbd.resultado.getString("c.nome") + " " + cbd.resultado.getString("c.apelido"));
                jLabelJoiaPAga.setText(cbd.resultado.getString("joia") + " MZN");
                jLabelBIQuot.setText(cbd.resultado.getString("c.bi") + cbd.resultado.getString("c.letra"));
                txtBI.setText(cbd.resultado.getString("c.bi"));
                jLabelContactoQuot.setText(cbd.resultado.getString("t.contacto"));
                jLabelJoiaPAga.setForeground(Color.black);
                jLabel67.setText(cbd.resultado.getString("data"));
                jCheckBoxPagarJoia.setEnabled(false);
                jTextFieldValorJoia.setVisible(false);
                jLabelBTNPagar.setVisible(false);
                jComboBoxMesQuota.setEnabled(true);
                txtvalorQuota.setEnabled(true);
                jButtonPagarQuota.setEnabled(true);
            } catch (Exception erro) {
                JoiaNao();
                jCheckBoxPagarJoia.setEnabled(true);
                jComboBoxMesQuota.setEnabled(false);
                txtvalorQuota.setEnabled(false);
                jButtonPagarQuota.setEnabled(false);
            }
        }
    }//GEN-LAST:event_VerAgregado1MouseClicked

    private void txtPesBIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesBIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesBIActionPerformed

    private void jRadioJoiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioJoiaActionPerformed
        // TODO add your handling code here:
        if(jRadioJoia.isSelected()){
            ListaJoia();
            jPanelQuota.setVisible(true);
            jPanelJoia.setVisible(false);
            jLabelTituloQ1.setText("Joia:");
        }
    }//GEN-LAST:event_jRadioJoiaActionPerformed

    private void QuotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuotaActionPerformed
        // TODO add your handling code here:
        if(Quota.isSelected()){
            jPanelQuota.setVisible(false);
            jPanelJoia.setVisible(true);
            jLabelTituloQ1.setText("Quota:");
        }
    }//GEN-LAST:event_QuotaActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        if (Quota.isSelected()) {
            if (jTextFieldPesquisarJQ.getText().equals("")) {
                jLabelEmpty.setText("Preencha o Campo Vazio!");
            } else {
                try {
                    cbd.pesquisar = cbd.conexao.createStatement();

                    String sql = " select * from quotizacao, cliente b where b.nome like'%" + jTextFieldPesquisarJQ.getText() + "%' and  b.bi=bi_benificiario";
                    cbd.resultado = cbd.pesquisar.executeQuery(sql);

                    DefaultTableModel tm = (DefaultTableModel) jTableQuotizacao.getModel();
                    tm.setRowCount(0);

                    int soma = 0;

                    while (cbd.resultado.next()) {

                          int num = cbd.resultado.getInt("ano");
                        num = 1;
                        soma = soma + num;

                        Object o[] = {soma, cbd.resultado.getString("b.nome") + " " + cbd.resultado.getString("b.apelido"), cbd.resultado.getString("janeiro"), cbd.resultado.getString("fevereiro"), cbd.resultado.getString("março"), cbd.resultado.getString("abril"), cbd.resultado.getString("maio"), cbd.resultado.getString("junho"), cbd.resultado.getString("julho"), cbd.resultado.getString("agosto"), cbd.resultado.getString("setembro"), cbd.resultado.getString("outubro"), cbd.resultado.getString("novembro"), cbd.resultado.getString("dezembro"), cbd.resultado.getString("ano"), cbd.resultado.getString("bi_benificiario")};
                        tm.addRow(o);
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "" + e);
                }
            }        
        }else if (jRadioJoia.isSelected()) {
            if (jTextFieldPesquisarJQ.getText().equals("")) {
                jLabelEmpty.setText("Preencha o Campo Vazio!");
            } else {
                try {
                    cbd.pesquisar = cbd.conexao.createStatement();

                    String sql = "select * from joia , cliente b where b.nome like'%" + jTextFieldPesquisarJQ.getText() + "%' and  b.bi=bi_benificiario";
                    cbd.resultado = cbd.pesquisar.executeQuery(sql);

                    DefaultTableModel tm = (DefaultTableModel) jTableJoia.getModel();
                    tm.setRowCount(0);

                    int soma = 0;

                    while (cbd.resultado.next()) {

                        int num = cbd.resultado.getInt("data");
                        num = 1;
                        soma = soma + num;

                        Object o[] = {soma, cbd.resultado.getString("b.nome") + " " + cbd.resultado.getString("b.apelido"), cbd.resultado.getString("joia"), cbd.resultado.getString("bi_benificiario"), cbd.resultado.getString("data"), cbd.resultado.getString("data_registo")};
                        tm.addRow(o);
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "" + e);
                }
            }        
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jTextFieldPesquisarJQKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPesquisarJQKeyReleased
        // TODO add your handling code here:
        jLabelEmpty.setText("");
    }//GEN-LAST:event_jTextFieldPesquisarJQKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox Activo;
    private javax.swing.JPanel AdministradorPanel;
    private javax.swing.JPanel AgregadoFamiliar;
    private javax.swing.JLabel AgregadoFamiliarfoto;
    private javax.swing.JLabel AlertPrimaryKeyBI;
    private javax.swing.JLabel AlertPrimaryKeyCodigo;
    private javax.swing.JLabel AlertPrimarykey;
    private javax.swing.JLabel BIAF;
    private javax.swing.JCheckBox Contratado;
    private javax.swing.JList<String> ListaFamilarUnicaPessoa;
    private javax.swing.JPanel ListaPainel;
    private javax.swing.JLabel NomeAf;
    private javax.swing.JLabel PaginaIn;
    private javax.swing.JPanel PaginaInicial;
    private javax.swing.JRadioButton Quota;
    private javax.swing.JCheckBox Reformado;
    private javax.swing.JPanel SaticacaoBenifi;
    private javax.swing.JLabel TAagregadoFamiliar;
    private javax.swing.JLabel TAbenificiario;
    private javax.swing.JLabel TotalFemenino;
    private javax.swing.JLabel TotalFemeninoBenif;
    private javax.swing.JLabel TotalListaBenificiario;
    private javax.swing.JLabel TotalMasculinoAF;
    private javax.swing.JLabel TotalMasculinoBenifi;
    private javax.swing.JLabel VerAgregado;
    private javax.swing.JLabel VerAgregado1;
    private javax.swing.JLabel VerAgregado1Lista;
    private javax.swing.JPanel agregadoFamiliarLista;
    private javax.swing.JPanel benificiarioLista;
    private javax.swing.JButton btnAgregadoFamiliar;
    private javax.swing.JButton btnLimparRegisto;
    private javax.swing.JButton btnacesso;
    private javax.swing.JButton btngravar;
    private javax.swing.JLabel data;
    private javax.swing.JLabel datanasc;
    private javax.swing.JRadioButton femenino;
    private javax.swing.JRadioButton femeninoA;
    private javax.swing.JLabel horas;
    private javax.swing.JButton jBApagarTodo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButtonApagarAF;
    private javax.swing.JButton jButtonApagarBenifficario;
    private javax.swing.JButton jButtonApagarSB;
    private javax.swing.JButton jButtonPagarQuota;
    private javax.swing.JComboBox<String> jCBoxGraudeparentesco;
    private javax.swing.JComboBox<String> jCLetra;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBoxAdicionarHer;
    private javax.swing.JCheckBox jCheckBoxBairro;
    private javax.swing.JCheckBox jCheckBoxPagarJoia;
    private javax.swing.JCheckBox jCheckBoxPatente;
    private javax.swing.JCheckBox jCheckBoxPensionista;
    private javax.swing.JCheckBox jCheckBoxQuadro;
    private javax.swing.JCheckBox jCheckBoxReforma;
    private javax.swing.JCheckBox jCheckBoxReserva;
    private javax.swing.JCheckBox jCheckDelete;
    private javax.swing.JCheckBox jCheckDeleteAF;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBoxAF;
    private javax.swing.JComboBox<String> jComboBoxAnoIcorporacao;
    private javax.swing.JComboBox<String> jComboBoxAnoQuota2;
    private javax.swing.JComboBox<String> jComboBoxAnosNasc;
    private javax.swing.JComboBox<String> jComboBoxAnosNascAF;
    private javax.swing.JComboBox<String> jComboBoxBProvincia;
    private javax.swing.JComboBox<String> jComboBoxBenificaio;
    private javax.swing.JComboBox<String> jComboBoxDiaNasc;
    private javax.swing.JComboBox<String> jComboBoxDiaNascAF;
    private javax.swing.JComboBox<String> jComboBoxDiaNascIA;
    private javax.swing.JComboBox<String> jComboBoxEstadoCivil;
    private javax.swing.JComboBox<String> jComboBoxMesNasc;
    private javax.swing.JComboBox<String> jComboBoxMesNascAF;
    private javax.swing.JComboBox<String> jComboBoxMesNascIA;
    private javax.swing.JComboBox<String> jComboBoxMesQuota;
    private javax.swing.JComboBox<String> jComboBoxMesQuota1;
    private javax.swing.JComboBox<String> jComboBoxPais;
    private javax.swing.JComboBox<String> jComboBoxPesquiarBINome;
    private javax.swing.JComboBox<String> jComboBoxProvincia;
    private javax.swing.JComboBox<String> jComboBoxTrabalho;
    private javax.swing.JComboBox<String> jComboOperador;
    private javax.swing.JLabel jLNF;
    private javax.swing.JLabel jLabeNomeFoto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JLabel jLabelAdminIcon;
    private javax.swing.JLabel jLabelAdminTitulo;
    private javax.swing.JLabel jLabelAf;
    private javax.swing.JLabel jLabelAlertPrimaryKey;
    private javax.swing.JLabel jLabelApagarBenificiario;
    private javax.swing.JLabel jLabelApagarDI;
    private javax.swing.JLabel jLabelApelidoRegisto;
    private javax.swing.JLabel jLabelBIQuot;
    private javax.swing.JLabel jLabelBTNPagar;
    private javax.swing.JLabel jLabelBairro;
    private javax.swing.JLabel jLabelBanoAF;
    private javax.swing.JLabel jLabelBapleidoAF;
    private javax.swing.JLabel jLabelBcodigoAF;
    private javax.swing.JLabel jLabelBdiaAF;
    private javax.swing.JLabel jLabelBdistrito;
    private javax.swing.JLabel jLabelBeneficio;
    private javax.swing.JLabel jLabelBi;
    private javax.swing.JLabel jLabelBiFotoSave;
    private javax.swing.JLabel jLabelBlocalidade;
    private javax.swing.JLabel jLabelBmesAF;
    private javax.swing.JLabel jLabelBnomeAF;
    private javax.swing.JLabel jLabelBprovincia;
    private javax.swing.JLabel jLabelBsexoAF;
    private javax.swing.JLabel jLabelContacto;
    private javax.swing.JLabel jLabelContactoQuot;
    private javax.swing.JLabel jLabelDIAno;
    private javax.swing.JLabel jLabelDIdia;
    private javax.swing.JLabel jLabelDImes;
    private javax.swing.JLabel jLabelDistritoBI;
    private javax.swing.JLabel jLabelEOT;
    private javax.swing.JLabel jLabelEmpty;
    private javax.swing.JLabel jLabelEnderencoBairro;
    private javax.swing.JLabel jLabelEstadoCivil;
    private javax.swing.JLabel jLabelFotoQuota;
    private javax.swing.JLabel jLabelGrauParentesco;
    private javax.swing.JLabel jLabelInvalidoLetraBI;
    private javax.swing.JLabel jLabelJoiaPAga;
    private javax.swing.JLabel jLabelListaTrabalho;
    private javax.swing.JLabel jLabelLocal;
    private javax.swing.JLabel jLabelLocalidadeBI;
    private javax.swing.JLabel jLabelLtrabalha;
    private javax.swing.JLabel jLabelMae;
    private javax.swing.JLabel jLabelMsgErro;
    private javax.swing.JLabel jLabelMultTotalASatisfacao;
    private javax.swing.JLabel jLabelNasAno;
    private javax.swing.JLabel jLabelNasDia;
    private javax.swing.JLabel jLabelNasMes;
    private javax.swing.JLabel jLabelNomeQuo;
    private javax.swing.JLabel jLabelNomeRegisto;
    private javax.swing.JLabel jLabelPai;
    private javax.swing.JLabel jLabelPatente;
    private javax.swing.JLabel jLabelProfissao;
    private javax.swing.JLabel jLabelProvinciaBI;
    private javax.swing.JLabel jLabelSaveLocalTrabalho;
    private javax.swing.JLabel jLabelSexo;
    private javax.swing.JLabel jLabelShowFoto;
    private javax.swing.JLabel jLabelTitleCodigo;
    private javax.swing.JLabel jLabelTitleCodigo1;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelTituloQ1;
    private javax.swing.JLabel jLabelTotalDivorciado;
    private javax.swing.JLabel jLabelTotalFContratado;
    private javax.swing.JLabel jLabelTotalFactivo;
    private javax.swing.JLabel jLabelTotalFfuncionario;
    private javax.swing.JLabel jLabelTotalFreformado;
    private javax.swing.JLabel jLabelTotalMilitar;
    private javax.swing.JLabel jLabelTotalPensionista;
    private javax.swing.JLabel jLabelTotalQuadroPensionista;
    private javax.swing.JLabel jLabelTotalReforma;
    private javax.swing.JLabel jLabelTotalReserva;
    private javax.swing.JLabel jLabelTotalSBenf;
    private javax.swing.JLabel jLabelTotalSolteriro;
    private javax.swing.JLabel jLabelTotalSolteriro1;
    private javax.swing.JLabel jLabelTotalTrabalhoMilitarunico;
    private javax.swing.JLabel jLabelValor;
    private javax.swing.JLabel jLabelVazioB;
    private javax.swing.JLabel jLabelVazioLAF;
    private javax.swing.JLabel jLabelVazioSB;
    public static javax.swing.JLabel jLabelbi;
    private javax.swing.JLabel jLabelcontacto;
    private javax.swing.JLabel jLabelnome;
    private javax.swing.JList<String> jListHerdeiros;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelBairro;
    private javax.swing.JPanel jPanelFuncioanrio;
    private javax.swing.JPanel jPanelJoia;
    private javax.swing.JPanel jPanelMilitar;
    private javax.swing.JPanel jPanelOrganizacao;
    private javax.swing.JPanel jPanelPaginaTrabalhoInfo;
    private javax.swing.JPanel jPanelQuota;
    private javax.swing.JPanel jPanelQuotizacao;
    private javax.swing.JPanel jPanelSatisfacaoBeni;
    private javax.swing.JProgressBar jProgressBarF;
    private javax.swing.JRadioButton jRadioJoia;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPaneFuncionario;
    private javax.swing.JScrollPane jScrollPaneMilitar;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator23;
    private javax.swing.JSeparator jSeparator24;
    private javax.swing.JSeparator jSeparator25;
    private javax.swing.JSeparator jSeparator26;
    private javax.swing.JSeparator jSeparator27;
    private javax.swing.JSeparator jSeparator28;
    private javax.swing.JSeparator jSeparator29;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JScrollPane jSlistaF;
    private javax.swing.JTable jTableAgredadoFamiliar;
    private javax.swing.JTable jTableBenificiario;
    private javax.swing.JTable jTableFuncionario;
    private javax.swing.JTable jTableJoia;
    private javax.swing.JTable jTableLocalTrabalho;
    private javax.swing.JTable jTableQuotizacao;
    private javax.swing.JTable jTableSatisfacao;
    private javax.swing.JTextField jTextFieldPesquisaNomeSB;
    private javax.swing.JTextField jTextFieldPesquisarAFL;
    private javax.swing.JTextField jTextFieldPesquisarB;
    private javax.swing.JTextField jTextFieldPesquisarJQ;
    private javax.swing.JTextField jTextFieldPesquisarLLT;
    private javax.swing.JTextField jTextFieldValorJoia;
    private javax.swing.JTextArea jTextdescricao;
    private javax.swing.JButton jbtnSelecaoFoto;
    public static javax.swing.JComboBox<String> jcBaseDados;
    private javax.swing.JLabel lblListaMenu;
    private javax.swing.JLabel lblMenuAdminitrador;
    private javax.swing.JLabel lblMenuQuatizacao;
    private javax.swing.JLabel lblSatisfacaoBenificio;
    private javax.swing.JLabel lblShowAFcontacto;
    private javax.swing.JLabel lblShowAFquantidade;
    private javax.swing.JLabel lblShowbenificiario;
    private javax.swing.JLabel lblShowbenificiario1;
    private javax.swing.JLabel lblTotalAgF;
    private javax.swing.JLabel lblTotalAgF1;
    private javax.swing.JLabel lbl_Image;
    private javax.swing.JLabel lblgrouparentesco;
    private javax.swing.JLabel lblregisto;
    private javax.swing.JLabel lentraBi;
    private javax.swing.JProgressBar loadingbar;
    private javax.swing.JPanel login;
    private javax.swing.JLabel logout;
    private javax.swing.JRadioButton masculino;
    private javax.swing.JRadioButton masculinoA;
    private javax.swing.JLabel ocupacao;
    private javax.swing.JTextField patenteMilitar;
    private javax.swing.JLabel percentagemF;
    private javax.swing.JLabel percentagemM;
    private javax.swing.ButtonGroup quotizacaoOpetion;
    private javax.swing.JPanel registo;
    private javax.swing.ButtonGroup sexo;
    private javax.swing.JLabel totalAgregado;
    private javax.swing.JTextField txtBI;
    private javax.swing.JTextField txtBdistrito;
    private javax.swing.JTextField txtBlocalidade;
    private javax.swing.JTextField txtDistrito;
    private javax.swing.JTextField txtEntidadeOndeTrabalho;
    private javax.swing.JTextField txtLocalTrabalho;
    private javax.swing.JTextField txtLocalidade;
    private javax.swing.JLabel txtPbi;
    private javax.swing.JLabel txtPcontacto;
    private javax.swing.JLabel txtPdata;
    private javax.swing.JLabel txtPdataIncricao;
    private javax.swing.JTextField txtPesBI;
    private javax.swing.JLabel txtPhora;
    private javax.swing.JLabel txtPnome;
    private javax.swing.JLabel txtPramo;
    private javax.swing.JTextField txtProfissao;
    private javax.swing.JLabel txtPsexo;
    private javax.swing.JLabel txtPunidade;
    private javax.swing.JLabel txtShowbi;
    private javax.swing.JLabel txtShowcontacto;
    private javax.swing.JLabel txtShowdata;
    private javax.swing.JLabel txtShowdataInscricao;
    private javax.swing.JLabel txtShowhora;
    private javax.swing.JLabel txtShownome;
    private javax.swing.JLabel txtShowramo;
    private javax.swing.JLabel txtShowsexo;
    private javax.swing.JLabel txtShowunidade;
    private javax.swing.JLabel txtTitle;
    private javax.swing.JTextField txtValor;
    private javax.swing.JTextField txtapelido;
    private javax.swing.JTextField txtapelidofamiliar;
    private javax.swing.JTextField txtbairro;
    private javax.swing.JTextField txtbi;
    private javax.swing.JTextField txtbibeneficiario;
    private javax.swing.JTextField txtcodigo;
    private javax.swing.JTextField txtcodigoAF;
    private javax.swing.JTextField txtcontacto;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtlocal;
    private javax.swing.JTextField txtmae;
    private javax.swing.JTextField txtmorada;
    private javax.swing.JTextField txtnome;
    private javax.swing.JTextField txtnomebenificiario;
    private javax.swing.JTextField txtpai;
    private javax.swing.JTextField txtramo;
    private javax.swing.JPasswordField txtsenha;
    private javax.swing.JTextField txtuser;
    private javax.swing.JTextField txtvalorQuota;
    // End of variables declaration//GEN-END:variables

    class hora implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Calendar now = Calendar.getInstance();
            horas.setText(String.format("%1$tH:%1$tM", now));
        }
    }
}
