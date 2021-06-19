
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

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


public class Main {
	
	private final static String PRIVATE_KEY="92c1a9a1e5efc3872b0d4778066d49572b5835bf1447c289700866e5bad676be";
	//此測試鏈中預設管理者的私鑰
	
	private final static BigInteger GAS_LIMIT=BigInteger.valueOf(6721975L);
	private final static BigInteger GAS_PRICE=BigInteger.valueOf(20000000000L);
	//部屬合約時會設定gas的價格和限制，是乙太坊避免重複交易等區塊練攻擊
	
	private final static String RECIPIENT="0x767e33A42e85256CB7252227C85C5a6e881448A0";
	
	private final static String CONTRACT_ADDRESS ="0x4A12B16C4F25181544D69a0f93A8F54b604d1413";
	//預設的合約地址
	
	private String INPUT_PRIVATE_KEY;
	//儲存登入者私鑰的字串
	
	private String contract_address = CONTRACT_ADDRESS ;
	//儲存合約發布後的地址
	
	private boolean deploy = false;
	private boolean endvote = true;
	public boolean if_start = false; 
	public String font = "TimesRoman";
	
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
	
	
	JLabel l = new JLabel("投票頁面");
	JLabel login = new JLabel("version 6.14");
	JLabel[] candidate = new JLabel[3];
	JLabel[] candidateadmin = new JLabel[3];
	JLabel[] candidateresult = new JLabel[4];
	JLabel systemlogo = new JLabel(smallIcon);
	JLabel loginsign = new JLabel("登入投票系統");
	
	
	Main(){
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
		
		login.setBounds(0,0,100,20);
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
	/**
	 * @throws Exception
	 */
	private void Main(String name1,String name2,String name3) throws Exception {
		Web3j web3j=Web3j.build(new HttpService("http://localhost:7545"));
		
		Credentials credentials = getCredentialsFromPrivateKey(PRIVATE_KEY);
		
		//String ContractAddress=deployContract(web3j,credentials);
     		contract_address = deployContract(web3j, credentials);
     		Election election = loadContract(contract_address,web3j, getCredentialsFromPrivateKey(PRIVATE_KEY));
     		
     		input(election,name1,name2,name3);
     	
	}
	private void owner(Election election) throws Exception {
		String owneraddress=election.owner().send();
		System.out.println(owneraddress);
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
			Election manager = loadContract(contract_address, web3j,getCredentialsFromPrivateKey(PRIVATE_KEY));
			return manager.ballotend().send();
			
	} 
	public void renew() throws Exception {
		Web3j web3j=Web3j.build(new HttpService("http://localhost:7545"));
		Election election = loadContract(contract_address, web3j,getCredentialsFromPrivateKey(PRIVATE_KEY));
		while(true) {
			boolean temp = election.ballotend().send();
			if(temp=false) {
				if_start = true;
				break;
			}else {
				continue;
			}
		}
	}
	private void transferEthereum(Web3j web3j, Credentials credentials) throws Exception{
		TransactionManager transactionManager =new RawTransactionManager(
				web3j,
				getCredentialsFromPrivateKey(PRIVATE_KEY)
				
		);
		Transfer transfer =new Transfer (web3j, transactionManager);
		
		TransactionReceipt transactionReceipt =  transfer.sendFunds(
						RECIPIENT,
						BigDecimal.ONE,
						Convert.Unit.ETHER,
						GAS_PRICE,
						GAS_LIMIT
				).send();
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
			Election election = loadContract(contract_address,web3j, getCredentialsFromPrivateKey(PRIVATE_KEY));
			Credentials manager = getCredentialsFromPrivateKey(PRIVATE_KEY);
			if(e.getSource()==confirm) {
				if(privatekey.getText().equals(PRIVATE_KEY)) {
					adminframe.setVisible(true);
					loginframe.setVisible(false);
					if(deploy==false)
					deployeframe.setVisible(true);
					INPUT_PRIVATE_KEY=privatekey.getText();
					try {
						showN(election);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					INPUT_PRIVATE_KEY=privatekey.getText();
					boolean check = true;
					try {
						Credentials credential = getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY);
						Election electioncli = loadContract(contract_address,web3j, getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
					}catch(Exception e1){
						//System.out.println("error privatekey");
						pane.showMessageDialog(null, "ACCOUNT NOT EXIST", "ERROR", pane.ERROR_MESSAGE);
						check=false;
					}
					if(check) {
						loginframe.setVisible(false);
						mainframe.setVisible(true);
					}
					try {
						showN(election);
					} catch (Exception e1) {
							// TODO Auto-generated catch block
						e1.printStackTrace();
					}
//					try {
//						renew(web3j,election);
//					} catch (Exception e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
				}
			}else if(e.getSource()==showfinalresult) {
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
				try {
					election.startballot().send();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if_start = true;
				//System.out.println("Start ballot");
			}else if(e.getSource()==endballot) {
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
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					pane1.showMessageDialog(null, "DEPLOY FAIL","ERROR", pane1.ERROR_MESSAGE);
				}
			}else if(e.getSource()==refresh) {
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
