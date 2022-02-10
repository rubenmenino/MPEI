// Base retirada de: https://stackoverflow.com/questions/26156310/how-can-i-limit-the-check-box-selected
// Resposta do utilizador "camickr" de 2 de Outubro de 2014

// Mudanças efetuadas por: Eduardo Coelho (88867) e Ruben Menino (89185)

package ProjetoMPEI_88867_89185.MPEI;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import javax.swing.*;

public class MenuProjeto
{
	// Vari�vel para limitar o n�mero de bot�es que se pode escolher (nosso caso 2)
	static int k = 0;
	
	// Variaveis usadas para a comparação de duas obras de Saramago
	static int escolha1 = 0;
	static int escolha2 = 0;
	
	// Criação de Paths para textos de Saramago
	static Path Saramago1 = Paths.get("ensaioSobreACegueira.txt");
	static Path Saramago2 = Paths.get("itermitenciasDaMorte.txt");
	static Path Saramago3 = Paths.get("memorialDoConvento.txt");
	static Path Saramago4 = Paths.get("homemDuplicado.txt");
	static Path Saramago5 = Paths.get("ensaioSobreALucidez.txt");
			
	// Criação de Paths para textos de outros escritores
	static Path Obra1 = Paths.get("canto1.txt");
	static Path Obra2 = Paths.get("canto3.txt");
	static Path Obra3 = Paths.get("canto10.txt");
	static Path Obra4 = Paths.get("OsMaias.txt");
	static Path Obra5 = Paths.get("CidadeEAsSerras.txt");
	
	// Criação de Path para ficheiro Resultados.txt
	static Path resultados = Paths.get("RESULTADOS.txt");
	
	
    private Set<GroupButtonModel> models = new HashSet<GroupButtonModel>();
    private int groupSize;
    static private ButtonModel[] butoes = new GroupButtonModel[10];

    public MenuProjeto(int groupSize)
    {
        this.groupSize = groupSize;
    }

    public void register(JCheckBox checkBox, int i)
    {
        ButtonModel groupModel = new GroupButtonModel();
        groupModel.setSelected ( checkBox.getModel().isSelected() );
        checkBox.setModel( groupModel );
        butoes[i] = groupModel;
    }


    private class GroupButtonModel extends JToggleButton.ToggleButtonModel
    {    	
        @Override
        public void setSelected(boolean selected)
        {
            if (!selected)
            {
                models.remove( this );
                super.setSelected( selected );
                return;
            }

            //  Check number of currently selected check boxes

            if (models.size() == groupSize)
            {
            	// Guardar os .txt escolhidos em ArrayList<String> usando o metodo readAllLines
				ArrayList<String> texto1 = null;
				try {
					texto1 = lerFicheiro(escolha1);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
				ArrayList<String> texto2 = null;
				try {
					texto2 = lerFicheiro(escolha2);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				// Guarda todo o texto numa string e remove os espaços em branco, usando o metodo concatenar()
				String string1 = concatenar(texto1);
				String string2 = concatenar(texto2);
				
				// Criar minhash com 20 hashfunctions
				MinHash minhash = new MinHash(20);

				// Dividir ambas as strings em shingles de 4
				Set<String> set1 = Shingles.criarShingle(string1, 4);
				Set<String> set2 = Shingles.criarShingle(string2, 4);
				
				// Similaridade entre os dois textos
				JFrame alerta = new JFrame("Resultado:");
				JOptionPane.showMessageDialog(alerta, "\nSimilaridade entre "+nomeLivro(escolha1)+" e "+nomeLivro(escolha2)+": "+minhash.similaridadeJaccard(set1, set2));
						
				k = 0;
				for(int i=0 ; i<10 ; i++) {
					butoes[i].setSelected(false);
				}
			}
            else
            {	
            	if(k==0) {
            		for(int i=0 ; i<10 ; i++) {
            			if(this.equals(butoes[i])) {
            				escolha1 = i;
            				break;
            			}
            		}
            	}else{
            		for(int i=0 ; i<10 ; i++) {
            			if(this.equals(butoes[i])) {
            				escolha2 = i;
            				break;
            			}
            		}
            	}
            	k++;
                models.add( this );
                super.setSelected( selected );
            }

        }
    }

    private static void createAndShowGUI()
    {
        JPanel panel = new JPanel(new GridLayout(5,2));
        MenuProjeto group = new MenuProjeto(2);

        JCheckBox checkBox0 = new JCheckBox( "Ensaio Sobre A Cegueira" );
        panel.add( checkBox0 );
        group.register( checkBox0 , 0 );
        JCheckBox checkBox1 = new JCheckBox( "Itermitencias da Morte" );
        panel.add( checkBox1 );
        group.register( checkBox1 , 1);
        JCheckBox checkBox2 = new JCheckBox( "Memorial do Convento" );
        panel.add( checkBox2 );
        group.register( checkBox2 , 2);
        JCheckBox checkBox3 = new JCheckBox( "Homem Duplicado" );
        panel.add( checkBox3 );
        group.register( checkBox3 , 3);
        JCheckBox checkBox4 = new JCheckBox( "Ensaio Sobre A Lucidez" );
        panel.add( checkBox4 );
        group.register( checkBox4 , 4);
        JCheckBox checkBox5 = new JCheckBox( "Canto1 d'Os Lusiadas" );
        panel.add( checkBox5 );
        group.register( checkBox5 , 5);
        JCheckBox checkBox6 = new JCheckBox( "Canto3 d'Os Lusiadas" );
        panel.add( checkBox6 );
        group.register( checkBox6 , 6);
        JCheckBox checkBox7 = new JCheckBox( "Canto10 d'Os Lusiadas" );
        panel.add( checkBox7 );
        group.register( checkBox7 , 7);
        JCheckBox checkBox8 = new JCheckBox( "Os Maias" );
        panel.add( checkBox8 );
        group.register( checkBox8 , 8);
        JCheckBox checkBox9 = new JCheckBox( "Cidade e as serras" );
        panel.add( checkBox9 );
        group.register( checkBox9 , 9);
        
        
        JPanel SUL = new JPanel(new FlowLayout());
        JButton calcular = new JButton("CALCULAR");
        calcular.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Guardar os .txt escolhidos em ArrayList<String> usando o metodo readAllLines
				ArrayList<String> texto1 = null;
				try {
					texto1 = lerFicheiro(escolha1);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
				ArrayList<String> texto2 = null;
				try {
					texto2 = lerFicheiro(escolha2);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// Guarda todo o texto numa string e remove os espaços em branco, usando o metodo concatenar()
				String string1 = concatenar(texto1);
				String string2 = concatenar(texto2);
				
				// Criar minhash com 20 hashfunctions
				MinHash minhash = new MinHash(20);

				// Dividir ambas as strings em shingles de 4
				Set<String> set1 = Shingles.criarShingle(string1, 4);
				Set<String> set2 = Shingles.criarShingle(string2, 4);
				
				// Mostrar a similaridade entre os dois textos
				JFrame alerta = new JFrame("CALCULAR:");
				JOptionPane.showMessageDialog(alerta, "\nSimilaridade entre "+nomeLivro(escolha1)+" e "+nomeLivro(escolha2)
																+": "+minhash.similaridadeJaccard(set1, set2));
				
				k = 0;
				for(int i=0 ; i<10 ; i++) {
					butoes[i].setSelected(false);
				}
        	}
        });
        
        JButton result = new JButton("RESULTADOS");
        result.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ArrayList<String> results = null;
        		try {
					results = (ArrayList<String>) Files.readAllLines(resultados);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
        		JFrame alerta = new JFrame("RESULTADOS:");
        		JTextArea texto = new JTextArea();
        		for(String string : results.toArray(new String[0])) {
        			texto.append(string + "\n");
        		}
        		alerta.add(texto);
        		alerta.setSize(500,400);
        		alerta.setVisible(true);
        	}
        });
        
        SUL.add(calcular);
        SUL.add(result);
    	
        JFrame frame = new JFrame("Check Box Group");
        frame.setLayout(new BorderLayout());
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add( panel, BorderLayout.NORTH );
        frame.add( SUL, BorderLayout.SOUTH);
        frame.setLocationByPlatform( true );
        frame.pack();
        frame.setVisible( true );
    }

    public static String nomeLivro(int escolha) {
    	switch(escolha) {
			case 0:
				return "'Ensaio Sobre A Cegueira'";
			case 1:
				return "'Itermitencias Da Morte'";
			case 2:
				return "'Memorial do Convento'";
			case 3:
				return "'Homem Duplicado'";
			case 4:
				return "'Ensaio Sobre A Lucidez'";
			case 5:
				return "'Canto 1 d'Os Lusiadas'";
			case 6:
				return "'Canto 3 d'Os Lusiadas'";
			case 7:
				return "'Canto 10 d'Os Lusiadas'";
			case 8:
				return "'Os Maias'";
			case 9:
				return "'Cidade e as serras'";
			default: 
				return "'default'";
		}
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
 // Método que devolve ArrayList com todas as linhas do ficheiro .txt escolhido
 	public static ArrayList<String> lerFicheiro(int escolha) throws IOException{
 		switch(escolha) {
 			case 0:
 				return (ArrayList<String>) Files.readAllLines(Saramago1);
 			case 1:
 				return (ArrayList<String>) Files.readAllLines(Saramago2);
 			case 2:
 				return (ArrayList<String>) Files.readAllLines(Saramago3);
 			case 3:
 				return (ArrayList<String>) Files.readAllLines(Saramago4);
 			case 4:
 				return (ArrayList<String>) Files.readAllLines(Saramago5);
 			case 5:
 				return (ArrayList<String>) Files.readAllLines(Obra1);
 			case 6:
 				return (ArrayList<String>) Files.readAllLines(Obra2);
 			case 7:
 				return (ArrayList<String>) Files.readAllLines(Obra3);
 			case 8:
 				return (ArrayList<String>) Files.readAllLines(Obra4);
 			case 9:
 				return (ArrayList<String>) Files.readAllLines(Obra5);
 			default: 
 				return (ArrayList<String>) Files.readAllLines(Saramago1);
 		}
 	}
 	
 	// Método para colocar todas as linhas numa só string, e remover os espaços em branco
 	public static String concatenar(ArrayList<String> lista) {
 		String s = "";		
 		for(String frase : lista) {
 			s = s + frase;
 		}
 		s.replaceAll("\\s+","");
 		return s;
 	}
 	
 	////////////////////////////////////////////////////////////////////////////////
    
    
    public static void main(String[] args) throws IOException
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                createAndShowGUI();
            }
        });
    }
}
