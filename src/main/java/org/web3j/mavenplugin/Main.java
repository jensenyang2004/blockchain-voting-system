
//所有呼叫智能合約上的方法，名字都何以solidity撰寫的智能合約方法一樣
package org.web3j.mavenplugin;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.utils.Convert;
import org.web3j.tx.Transfer;

import contract.Election;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


public class Main {
		
	private final static BigInteger GAS_LIMIT=BigInteger.valueOf(6721975L);
	private final static BigInteger GAS_PRICE=BigInteger.valueOf(20000000000L);
		
	private static String CONTRACT_ADDRESS ="";
	
	private String INPUT_PRIVATE_KEY;
	private String contract_address = CONTRACT_ADDRESS ;
	//private String adminaddress = "";
	
	private boolean deploy = false;
	private boolean endvote = true;
	public boolean if_start = false; 
	public String font = "TimesRoman";
	
	boolean check = false;
	
	Font  f3  = new Font("TimesRoman",  Font.PLAIN, 22);
	ImageIcon icon = new ImageIcon("E:/web3j-maven-plugin-master/src/main/java/org/web3j/mavenplugin/ethereumlogo.png");
	Image img=icon.getImage();
	Image smallImage = img.getScaledInstance(60,96,Image.SCALE_DEFAULT);
	ImageIcon smallIcon = new ImageIcon(smallImage);
	
	JFrame mainframe = new JFrame();
	JFrame loginframe = new JFrame("登入頁面");
	JFrame adminframe = new JFrame("administration");
	JFrame resultframe = new JFrame("選舉結果");
	JFrame deployeframe = new JFrame("DEPLOY THE CONTRACT");
	JFrame testframe = new JFrame("test");
	
	JOptionPane pane = new JOptionPane("not real private key");
	JOptionPane pane1 = new JOptionPane("not real private key");
	
	JTextField privatekey = new JTextField();
	JTextField input = new JTextField();
	JTextField[] candidatefill = new JTextField[3];
	
	JPanel canva = new JPanel();
	
	JButton confirm = new JButton("確認");
	JButton showfinalresult = new JButton("顯示選舉結果");
	JButton startballot = new JButton("start the ballot");
	JButton endballot = new JButton("end the ballot");
	JButton[] voteButton = new JButton[3];
	JButton deployeButton = new JButton("DEPLOY CONTRACT");
	JButton refresh = new JButton("REFRESH");
//	JButton testButton = new JButton("system testing");
	
	
	JLabel l = new JLabel("投票頁面");
	JLabel login = new JLabel("version 8.01 developing version");
	JLabel[] candidate = new JLabel[3];
	JLabel[] candidateadmin = new JLabel[3];
	JLabel[] candidateresult = new JLabel[4];
	JLabel systemlogo = new JLabel(smallIcon);
	JLabel loginsign = new JLabel("登入投票系統");
	
	
	Main() throws IOException{
		
		File folder = new File("E://Jensen.inc");
		folder.mkdir();
		
		File addressfile =new File("E:\\Jensen.inc\\contractaddress.txt");
		if (addressfile.createNewFile()) {
	        System.out.println("File created: " + addressfile.getName());
	      } else {}
		FileReader file =new FileReader("E:\\Jensen.inc\\contractaddress.txt");
		BufferedReader br = new BufferedReader(file);
		while (br.ready()) {
    		contract_address=br.readLine();
    	}
    	file.close();
    	System.out.print("contract address: "+contract_address);

		mainframe.setBounds(0, 0, 500,700 );
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.setVisible(false);
		mainframe.setLayout(null);
		mainframe.setResizable(true);
		
		loginframe.setBounds(0, 0, 500,700 );
		//loginframe.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginframe.setVisible(true);
		loginframe.setLayout(null);
		loginframe.setResizable(false);
		
		deployeframe.setBounds(0, 0, 500,700 );
		deployeframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//if(deploy==false)
		deployeframe.setVisible(false);
		deployeframe.setLayout(null);
		deployeframe.setResizable(true);
		
		adminframe.setBounds(0, 0, 500,700 );
		adminframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		adminframe.setVisible(false);
		adminframe.setLayout(null);
		adminframe.setResizable(true);
		
		resultframe.setBounds(0, 0, 500,700 );
		resultframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		resultframe.setVisible(false);
		resultframe.setLayout(null);
		resultframe.setResizable(true);
		
		privatekey.setVisible(true);
		privatekey.setBounds(150,300,200,30);
		privatekey.setBackground(Color.white);
		loginframe.add(privatekey);
		
		input.setVisible(true);
		input.setBounds(135,500,200,50);
		//mainframe.add(input);
		
		confirm.setBounds(200,400,100,30);
		confirm.setFont(new Font("微軟正黑體",Font.PLAIN, 14));
		confirm.setLayout(null);
		confirm.setVisible(true);
		confirm.addActionListener(actionco);
		confirm.setBackground(Color.LIGHT_GRAY);
		loginframe.add(confirm);
		
		showfinalresult.setBounds(170,575,150,30);
		showfinalresult.setLayout(null);
		showfinalresult.setVisible(true);
		showfinalresult.addActionListener(actionco);
		showfinalresult.setBackground(Color.LIGHT_GRAY);
		showfinalresult.setFont(new Font("微軟正黑體",Font.PLAIN,14));
		mainframe.add(showfinalresult);
		
		startballot.setBounds(155,500,150,30);
		startballot.setVisible(true);
		startballot.addActionListener(actionco);
		startballot.setBackground(Color.LIGHT_GRAY);
		adminframe.add(startballot);
		
		endballot.setBounds(155,550,150,30);
		endballot.setVisible(true);
		endballot.addActionListener(actionco);
		endballot.setBackground(Color.LIGHT_GRAY);
		adminframe.add(endballot);
		
		deployeButton.setBounds(155,550,180,100);
		deployeButton.setVisible(true);
		deployeButton.addActionListener(actionco);
		deployeButton.setBackground(Color.LIGHT_GRAY);
		deployeframe.add(deployeButton);
		
		refresh.setBounds(155,600,150,30);
		refresh.setVisible(true);
		refresh.addActionListener(actionco);
		refresh.setBackground(Color.LIGHT_GRAY);
		adminframe.add(refresh);
		
		l.setBounds(175, 50, 200, 80);
		l.setVisible(true);
		l.setFont(new Font("微軟正黑體",Font.BOLD,30));
		mainframe.add(l);
		
		login.setBounds(0,0,300,20);
		login.setVisible(true);
		login.setFont(new Font(font,Font.PLAIN,12));
		login.setBackground(Color.BLUE);
		loginframe.add(login);
		
		loginsign.setBounds(180,220,150,50);
		loginsign.setVisible(true);
		loginsign.setFont(new Font("微軟正黑體",Font.PLAIN,22));
		loginsign.setBackground(Color.BLUE);
		loginframe.add(loginsign);
		
		if(icon==null) {
			pane1.showMessageDialog(null, "BALLOT HAVEN'T END", "ERROR", pane1.ERROR_MESSAGE);
		}
		systemlogo.setBounds(220,120,60,96);
		systemlogo.setVisible(true);
		loginframe.add(systemlogo);
		
		for(int i=0;i<3;i++) {
			int temp=i*50;
			candidate[i] = new JLabel(" ");
			candidate[i].setBounds(120,210+temp,200,80);
			candidate[i].setFont(f3);
			mainframe.add(candidate[i]);
			//adminframe.add(candidate[i]);
		}
		for(int i=0;i<3;i++) {
			int temp=i*50;
			candidateadmin[i] = new JLabel(" ");
			candidateadmin[i].setBounds(150,150+temp,200,80);
			candidateadmin[i].setFont(f3);
			adminframe.add(candidateadmin[i]);
			//adminframe.add(candidate[i]);
		}
		for(int i=0;i<4;i++) {
			int temp=i*50;
			candidateresult[i] = new JLabel(" ");
			candidateresult[i].setBounds(100,150+temp,350,80);
			candidateresult[i].setFont(f3);
			resultframe.add(candidateresult[i]);
		}
		for(int i=0;i<3;i++) {
			int temp = i*50;
			voteButton[i] = new JButton("vote");
			voteButton[i].setVisible(true);
			voteButton[i].setBounds(320,240+temp,100,30);
			voteButton[i].addActionListener(actionco);
			voteButton[i].setBackground(Color.LIGHT_GRAY);
			mainframe.add(voteButton[i]);
		}
		for(int i=0;i<3;i++) {
			int temp = i*80;
			candidatefill[i]=new JTextField();
			candidatefill[i].setVisible(true);
			candidatefill[i].setBounds(100,240+temp,300,50);
			candidatefill[i].setBackground(Color.white);
			deployeframe.add(candidatefill[i]);
		}
	}
	private void printWeb3Version(Web3j web3j) {
		Web3ClientVersion web3ClientVersion=null;
		try {
			web3ClientVersion = web3j.web3ClientVersion().send();
		}catch(IOException e) {
			e.printStackTrace();
		}
		String web3ClientVersionstring = web3ClientVersion.getWeb3ClientVersion();
		System.out.println("Web3 client version"+web3ClientVersionstring);
		
	}
	/**
	 * @throws Exception
	 */
//	private void sendMessage() {
//		Web3j web3j=Web3j.build(new HttpService("http://localhost:7545"));
//		web3j.ethSign(adminaddress, CONTRACT_ADDRESS);
//	}
	//傳送合約地址
	private void Main(String name1,String name2,String name3) throws Exception {
		Web3j web3j=Web3j.build(new HttpService("http://localhost:7545"));
		
		Credentials credentials = getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY);
		
		//String ContractAddress=deployContract(web3j,credentials);
     	contract_address = deployContract(web3j, credentials);
     	FileWriter fw = new FileWriter("E:\\Jensen.inc\\contractaddress.txt");
		fw.write(contract_address);
		fw.flush();
		fw.close();
     	Election election = loadContract(contract_address,web3j, getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
     	
     	input(election,name1,name2,name3);
     	
	}
	private String owner(Election election) throws Exception {
		return election.owner().send();
	}
	private void startballot(Election election) {
		election.startballot();
	}
	private void vote(Web3j web3j,String privatekey,String index) throws Exception {
		int index_ = Integer.parseInt(index);
		Election election= loadContract(contract_address, web3j,getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
		election.vote(BigInteger.valueOf(index_)).send();
	}
	private void input(Election election,String name1,String name2,String name3) throws Exception {
		election.input(name1).send();
		election.input(name2).send();
		election.input(name3).send();
	}
	private void finalresult(Web3j web3j) throws Exception {
		Election election= loadContract(contract_address, web3j, getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
		for(int i=0;i<3;i++) {
			String result = election.finalresult(BigInteger.valueOf(i)).send().toString();
			String nominee = election.showN(BigInteger.valueOf(i)).send().toString();
			candidateresult[i+1].setText(i+1+"."+nominee+" "+result+"votes");
		}
	}
	private void winner(Web3j web3j,Election election) throws Exception{
		String winner = election.winner().send().toString();
		System.out.println(winner);
		candidateresult[0].setText("winner is : "+winner);
	}
	private void nominee(Election election) throws Exception {
		String nomineeshow = election.nominee(BigInteger.valueOf(0)).send().toString();
		System.out.println(nomineeshow);
	}
	private void showN(Election election) throws Exception{
		for (int i=0;i<3;i++) {
			int temp=i+1;
			String  nominee = election.showN(BigInteger.valueOf(i)).send().toString();
			candidate[i].setText(temp+"."+nominee);
			candidateadmin[i].setText(temp+"."+nominee);
	        //System.out.println("nominee are: "+nominee);
	    } 
	}
	public boolean ballotend() throws Exception {
			Web3j web3j=Web3j.build(new HttpService("http://localhost:7545"));
			Election election = loadContract(contract_address, web3j,getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
			Election manager = loadContract(contract_address, web3j,getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
			return manager.ballotend().send();
			
	} 
	public void renew() throws Exception {
		while(true) {
			if(check) {
				Web3j web3j=Web3j.build(new HttpService("http://localhost:7545"));
				Election election = loadContract(contract_address, web3j,getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
				boolean temp = election.ballotend().send();
				if(temp=false) {
					if_start = true;
					break;
				}else {
					continue;
				}
			}
		}
	}
	private Credentials getCredentialsFromWallet() throws IOException, CipherException {
		return WalletUtils.loadCredentials(
				"passphrase","wallet/path"
		);
	}
	private Credentials getCredentialsFromPrivateKey(String privatekey) {
		return Credentials.create(privatekey);
	}
	
	private String deployContract(Web3j web3j, Credentials credentials) throws Exception {
		return Election.deploy(web3j, credentials,GAS_PRICE,GAS_LIMIT).send().getContractAddress();
	}
	private Election loadContract(String contractaddress,Web3j web3j,Credentials credentials) {
		return Election.load(contractaddress, web3j, credentials, GAS_PRICE,GAS_LIMIT);
	}
	public ActionListener actionco = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Web3j web3j=Web3j.build(new HttpService("http://localhost:7545"));
			//Credentials manager = getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY);
			if(e.getSource()==confirm) {
				INPUT_PRIVATE_KEY=privatekey.getText();
				check = true;
				try {
					Credentials credentialtest = getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY);
					try {
						Election election = loadContract(contract_address,web3j, getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
					}catch(Exception e2) {
						pane.showMessageDialog(null, "contract hasn't been deployed", "ERROR", pane.ERROR_MESSAGE);
					}
				}catch(Exception e1){
					pane.showMessageDialog(null, "ACCOUNT NOT EXIST", "ERROR", pane.ERROR_MESSAGE);
					check=false;
				}
				
				Credentials credential = getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY);
				System.out.print(credential.getAddress());
				//Election election = loadContract(contract_address,web3j, getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
				Election electionlogin = loadContract(contract_address,web3j, getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
				try {
					if(credential.getAddress().equals(owner(electionlogin))) {
						//System.out.print(credential.getAddress());
						adminframe.setVisible(true);
						loginframe.setVisible(false);
						if(deploy==false)
						//deployeframe.setVisible(true);
						INPUT_PRIVATE_KEY=privatekey.getText();
						try {
							Election election = loadContract(contract_address,web3j, getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
							showN(election);
							owner(election);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						//System.out.print(election.getAddress());
					}else {
						INPUT_PRIVATE_KEY=privatekey.getText();
						if(check) {
							loginframe.setVisible(false);
							mainframe.setVisible(true);
						}
						try {
							Election election = loadContract(contract_address,web3j, getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
							owner(election);
							showN(election);
						} catch (Exception e1) {
								// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						//System.out.println(credential.getAddress());
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
					e1.printStackTrace();
				}
			}else if(e.getSource()==showfinalresult) {
				Election election = loadContract(contract_address,web3j, getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
				Credentials manager = getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY);
				try {
					while(true) {
						boolean temp = ballotend();
						if(temp==true) {
							if(if_start==false) {
								pane1.showMessageDialog(null, "BALLOT HAVEN'T START", "ERROR", pane1.ERROR_MESSAGE);
								break;
							}else {
								loginframe.setVisible(false);
								resultframe.setVisible(true);
								try {
									finalresult(web3j);
								} catch (Exception e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								try {
									winner(web3j,election);
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								break;
							}
						}else {
							if_start=true;
							pane1.showMessageDialog(null, "BALLOT HAVEN'T END", "ERROR", pane1.ERROR_MESSAGE);
							break;
						}
					}
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					pane1.showMessageDialog(null, "I HAVE NO IDEA WHY THIS FAIL", "ERROR", pane1.ERROR_MESSAGE);
					e2.printStackTrace();
				}
					
			}else if(e.getSource()==startballot) {
				Election election = loadContract(contract_address,web3j, getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
				Credentials manager = getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY);
				try {
					election.startballot().send();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if_start = true;
				//System.out.println("Start ballot");
			}else if(e.getSource()==endballot) {
				Election election = loadContract(contract_address,web3j, getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
				Credentials manager = getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY);
				try {
					election.endballot().send();
				} catch (Exception e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				loginframe.setVisible(false);
				resultframe.setVisible(true);
				System.out.println("ballot end");
				try {
					finalresult(web3j);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					winner(web3j,election);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(e.getSource()==voteButton[0]) {
				Election election = loadContract(contract_address,web3j, getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
				Credentials manager = getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY);
				try {
					if(election.ballotend().send()==true) {
						pane1.showMessageDialog(null, "BALLOT HASN'T STARTED OR HAS ENDED", "ERROR", pane1.ERROR_MESSAGE);
					}else {
						if_start=true;
					}
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					vote(web3j, INPUT_PRIVATE_KEY,"0");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(e.getSource()==voteButton[1]) {
				Election election = loadContract(contract_address,web3j, getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
				Credentials manager = getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY);
				try {
					if(election.ballotend().send()==true) {
						pane1.showMessageDialog(null, "BALLOT HASN'T STARTED OR HAS ENDED", "ERROR", pane1.ERROR_MESSAGE);
					}else {
						if_start=true;
					}
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					vote(web3j, INPUT_PRIVATE_KEY,"1");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(e.getSource()==voteButton[2]) {
				Election election = loadContract(contract_address,web3j, getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
				Credentials manager = getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY);
				try {
					if(election.ballotend().send()==true) {
						pane1.showMessageDialog(null, "BALLOT HASN'T STARTED OR HAS ENDED", "ERROR", pane1.ERROR_MESSAGE);
					}else {
						if_start=true;
					}
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					vote(web3j, INPUT_PRIVATE_KEY,"2");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(e.getSource()==deployeButton) {
				try {
					Main(candidatefill[0].getText(),candidatefill[1].getText(),candidatefill[2].getText());
					//System.out.println(candidate[2].getText());
					deploy = true;
					//CONTRACT_ADDRESS = contract_address;
					pane1.showMessageDialog(null, "DEPLOY SUCESS", "INFORMATION", pane1.INFORMATION_MESSAGE);
					deployeframe.setVisible(false);
					
					//部屬合約地址寫進檔案中
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					pane1.showMessageDialog(null, "DEPLOY FAIL","ERROR", pane1.ERROR_MESSAGE);
				}
			}else if(e.getSource()==refresh) {
				Election election = loadContract(contract_address,web3j, getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
				Credentials manager = getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY);
				try {
					showN(election);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	};
}
