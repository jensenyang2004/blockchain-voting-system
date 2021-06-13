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
	
	private final static String PRIVATE_KEY="7f6b15a2ce704d348a750b23e9b3b4ce0e117517c3db79a38b32573676d0be23";
	
	private final static BigInteger GAS_LIMIT=BigInteger.valueOf(6721975L);
	private final static BigInteger GAS_PRICE=BigInteger.valueOf(20000000000L);
	
	private final static String RECIPIENT="0x767e33A42e85256CB7252227C85C5a6e881448A0";
	
	private final static String CONTRACT_ADDRESS ="0x06AC7868d57f907Dd9BDC11E8e92c3DfcE9F10f1";
	
	private String INPUT_PRIVATE_KEY;
	private String contract_address = CONTRACT_ADDRESS ;
	
	private boolean deploy = true;
	private boolean endvote = true;
	public boolean if_start = false; 
	
	JFrame mainframe = new JFrame();
	JFrame loginframe = new JFrame("login page");
	JFrame adminframe = new JFrame("administration");
	JFrame resultframe = new JFrame("final ballot result");
	JFrame deployeframe = new JFrame("final ballot result");
	
	JOptionPane pane = new JOptionPane("not real private key");
	JOptionPane pane1 = new JOptionPane("not real private key");
	
	JTextField privatekey = new JTextField();
	JTextField input = new JTextField();
	
	JButton confirm = new JButton("confirm");
	JButton showfinalresult = new JButton("FINAL RESULT");
	JButton startballot = new JButton("start the ballot");
	JButton endballot = new JButton("end the ballot");
	JButton[] voteButton = new JButton[3];
	JButton deployeButton = new JButton("DEPLOY CONTRACT");
//	JButton testButton = new JButton("system testing");
	
	
	JLabel l = new JLabel("vote page");
	JLabel login = new JLabel("VERSION 1.0");
	JLabel[] candidate = new JLabel[3];
	JLabel[] candidateadmin = new JLabel[3];
	JLabel[] candidateresult = new JLabel[4];
	
	
	Main(){
		
		mainframe.setBounds(0, 0, 500,700 );
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.setVisible(false);
		mainframe.setLayout(null);
		mainframe.setResizable(false);
		
		loginframe.setBounds(0, 0, 500,700 );
		loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginframe.setVisible(true);
		loginframe.setLayout(null);
		loginframe.setResizable(false);
		
		deployeframe.setBounds(0, 0, 500,700 );
		deployeframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if(deploy==false)
		deployeframe.setVisible(true);
		deployeframe.setLayout(null);
		deployeframe.setResizable(false);
		
		adminframe.setBounds(0, 0, 500,700 );
		adminframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		adminframe.setVisible(false);
		adminframe.setLayout(null);
		adminframe.setResizable(false);
		
		
		resultframe.setBounds(0, 0, 500,700 );
		resultframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		resultframe.setVisible(false);
		resultframe.setLayout(null);
		resultframe.setResizable(false);
		
		
		privatekey.setVisible(true);
		privatekey.setBounds(150,200,200,30);
		loginframe.add(privatekey);
		
		input.setVisible(true);
		input.setBounds(135,500,200,50);
		//mainframe.add(input);
		
		confirm.setBounds(200,300,100,30);
		confirm.setLayout(null);
		confirm.setVisible(true);
		confirm.addActionListener(actionco);
		loginframe.add(confirm);
		
		showfinalresult.setBounds(185,575,100,30);
		showfinalresult.setLayout(null);
		showfinalresult.setVisible(true);
		showfinalresult.addActionListener(actionco);
		mainframe.add(showfinalresult);
		
		startballot.setBounds(155,500,150,30);
		startballot.setVisible(true);
		startballot.addActionListener(actionco);
		adminframe.add(startballot);
		
		endballot.setBounds(155,550,150,30);
		endballot.setVisible(true);
		endballot.addActionListener(actionco);
		adminframe.add(endballot);
		
		deployeButton.setBounds(155,550,150,100);
		deployeButton.setVisible(true);
		deployeButton.addActionListener(actionco);
		deployeframe.add(deployeButton);
		
//		testButton.setVisible(true);
//		testButton.setBounds(185, 600, 100, 30);
//		testButton.addActionListener(actionco);
//		mainframe.add(testButton);
		
		l.setBounds(175, 50, 200, 80);
		l.setVisible(true);
		l.setFont(new Font("Cambria",Font.BOLD,30));
		mainframe.add(l);
		
		login.setBounds(150,50,300,80);
		login.setVisible(true);
		login.setFont(new Font("Cambria",Font.PLAIN,30));
		loginframe.add(login);
		
		for(int i=0;i<3;i++) {
			int temp=i*50;
			candidate[i] = new JLabel(" ");
			candidate[i].setBounds(150,210+temp,200,80);
			candidate[i].setFont(new Font("Cambria",Font.PLAIN,22));
			mainframe.add(candidate[i]);
			//adminframe.add(candidate[i]);
		}
		for(int i=0;i<3;i++) {
			int temp=i*50;
			candidateadmin[i] = new JLabel(" ");
			candidateadmin[i].setBounds(150,150+temp,200,80);
			candidateadmin[i].setFont(new Font("Cambria",Font.PLAIN,22));
			adminframe.add(candidateadmin[i]);
			//adminframe.add(candidate[i]);
		}
		for(int i=0;i<4;i++) {
			int temp=i*50;
			candidateresult[i] = new JLabel(" ");
			candidateresult[i].setBounds(100,150+temp,350,80);
			candidateresult[i].setFont(new Font("Cambria",Font.PLAIN,22));
			resultframe.add(candidateresult[i]);
		}
		for(int i=0;i<3;i++) {
			int temp = i*50;
			voteButton[i] = new JButton("vote");
			voteButton[i].setVisible(true);
			voteButton[i].setBounds(350,240+temp,100,30);
			voteButton[i].addActionListener(actionco);
			mainframe.add(voteButton[i]);
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
	private void Main() throws Exception {
		Web3j web3j=Web3j.build(new HttpService("http://localhost:7545"));
		
		Credentials credentials = getCredentialsFromPrivateKey(PRIVATE_KEY);
		
		//String ContractAddress=deployContract(web3j,credentials);
     	contract_address = deployContract(web3j, credentials);
     	Election election = loadContract(contract_address,web3j, getCredentialsFromPrivateKey(PRIVATE_KEY));
     	
     	input(election);
     	
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
	private void input(Election election) throws Exception {
		election.input("jensen").send();
		election.input("reacheranderson").send();
		election.input("barakobama").send();
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
	public boolean ballotend(Web3j web3j , boolean wether_start) throws Exception {
			Election election = loadContract(contract_address, web3j,getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
			Election manager = loadContract(contract_address, web3j,getCredentialsFromPrivateKey(PRIVATE_KEY));
			return manager.ballotend().send();
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
				}
			}else if(e.getSource()==showfinalresult) {
				try {
					while(true) {
						boolean temp = ballotend(web3j,if_start);
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
						pane1.showMessageDialog(null, "BALLOT HAVEN'T STARTED OR HAVE ENDED", "ERROR", pane1.ERROR_MESSAGE);
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
						pane1.showMessageDialog(null, "BALLOT HAVEN'T STARTED OR HAVE ENDED", "ERROR", pane1.ERROR_MESSAGE);
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
						pane1.showMessageDialog(null, "BALLOT HAVEN'T STARTED OR HAVE ENDED", "ERROR", pane1.ERROR_MESSAGE);
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
					Main();
					deploy = true;
					//CONTRACT_ADDRESS = contract_address;
					pane1.showMessageDialog(null, "DEPLOY SUCESS", "INFORMATION", pane1.INFORMATION_MESSAGE);
					deployeframe.setVisible(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					pane1.showMessageDialog(null, "DEPLOY FAIL","ERROR", pane1.ERROR_MESSAGE);
				}
			}
		}
	};
}
