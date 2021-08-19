package org.web3j.mavenplugin;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.DbGetString;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.utils.Convert;
import org.web3j.tx.Transfer;

import contract.Editedelection;
import contract.Deploycontract;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Main1 extends Thread{
	private final static BigInteger GAS_LIMIT=BigInteger.valueOf(6721975L);
	private final static BigInteger GAS_PRICE=BigInteger.valueOf(20000000000L);
	private final static String DEPLOYCONTRACT_ADDRESS = "0x5514cf4599cf3db59e481f478b6d777428fa7d49";
	//母合約地址，系統運行前就被加入程式碼中
		
	private static String CONTRACT_ADDRESS ="";
	//用以記錄子合約的地址
	
	private String INPUT_PRIVATE_KEY;
	private String contract_address = CONTRACT_ADDRESS ;
	
	public boolean if_start = false; 
	public String font = "TimesRoman";
	boolean check = false;
	
	Font  f3  = new Font("TimesRoman",  Font.PLAIN, 22);
	Font  f4  = new Font("TimesRoman",  Font.PLAIN, 22);
	ImageIcon icon = new ImageIcon("E:/web3j-maven-plugin-master/src/main/java/org/web3j/mavenplugin/ethereumlogo.png");
	ImageIcon loadicon = new ImageIcon("E:\\web3j-maven-plugin-master\\src\\main\\java\\org\\web3j\\mavenplugin\\solidity\\200.gif");
	Image img=icon.getImage();
	Image smallImage = img.getScaledInstance(60,96,Image.SCALE_DEFAULT);
	ImageIcon smallIcon = new ImageIcon(smallImage);
	
	JFrame loginframe = new JFrame("登入頁面");
	JFrame deployeframe = new JFrame("DEPLOY THE CONTRACT");
	
	JOptionPane pane = new JOptionPane("not real private key");
	JOptionPane pane1 = new JOptionPane("not real private key");
	
	JTextField inputaddress = new JTextField();
	JTextField input = new JTextField();
	JTextField[] candidatefill = new JTextField[3];
	
	JPasswordField  privatekey = new JPasswordField();
	
	JPanel loginP = new JPanel();
	JPanel mainP = new JPanel();
	JPanel adminP = new JPanel();
	JPanel resultP = new JPanel();
	JPanel loadP = new JPanel();
	
	JButton confirm = new JButton("確認");
	JButton showfinalresult = new JButton("顯示選舉結果");
	JButton startballot = new JButton("start the ballot");
	JButton endballot = new JButton("end the ballot");
	JButton[] voteButton = new JButton[3];
	JButton deployeButton = new JButton("DEPLOY CONTRACT");
	JButton refresh = new JButton("REFRESH");	
	
	JLabel l = new JLabel("投票頁面");
	JLabel login = new JLabel("version 11.01");
	//開發食用來標記版本
	JLabel[] candidate = new JLabel[3];
	JLabel[] candidateadmin = new JLabel[3];
	JLabel[] candidateresult = new JLabel[4];
	JLabel systemlogo = new JLabel(smallIcon);
	JLabel loadL = new JLabel(loadicon);
	JLabel loginsign = new JLabel("登入投票系統");
	
	
	Main1() throws Exception{
    	
		loginframe.setBounds(0, 0, 500,700 );
		loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginframe.setVisible(true);
		loginframe.setLayout(null);
		loginframe.setResizable(false);
		
		deployeframe.setBounds(0, 0, 500,700 );
		deployeframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		deployeframe.setVisible(false);
		deployeframe.setLayout(null);
		deployeframe.setResizable(true);
		
		loginP.setBounds(0, 0, 500, 700);
		loginP.setVisible(true);
		loginP.setLayout(null);
		loginP.setBackground(Color.white);
		loginframe.add(loginP);
		
		mainP.setBounds(0, 0, 500, 700);
		mainP.setVisible(false);
		mainP.setLayout(null);
		mainP.setBackground(Color.white);
		loginframe.add(mainP);
		
		adminP.setBounds(0, 0, 500, 700);
		adminP.setVisible(false);
		adminP.setLayout(null);
		adminP.setBackground(Color.white);
		loginframe.add(adminP);
		
		resultP.setBounds(0, 0, 500, 700);
		resultP.setVisible(false);
		resultP.setLayout(null);
		resultP.setBackground(Color.white);
		loginframe.add(resultP);
		
		loadP.setBounds(0, 0, 500, 700);
		loadP.setVisible(false);
		loadP.setLayout(null);
		loadP.setBackground(Color.white);
		loginframe.add(loadP);
		
		privatekey.setVisible(true);
		privatekey.setBounds(150,360,200,30);
		privatekey.setBackground(Color.LIGHT_GRAY);
		loginP.add(privatekey);
		
		inputaddress.setVisible(true);
		inputaddress.setBounds(150,300,200,30);
		inputaddress.setBackground(Color.LIGHT_GRAY);
		loginP.add(inputaddress);
		
		input.setVisible(true);
		input.setBounds(135,500,200,50);
		
		confirm.setBounds(200,450,100,30);
		confirm.setFont(new Font("微軟正黑體",Font.PLAIN, 14));
		confirm.setLayout(null);
		confirm.setVisible(true);
		confirm.addActionListener(actionco);
		confirm.setBackground(Color.LIGHT_GRAY);
		loginP.add(confirm);
		
		showfinalresult.setBounds(170,575,150,30);
		showfinalresult.setLayout(null);
		showfinalresult.setVisible(true);
		showfinalresult.addActionListener(actionco);
		showfinalresult.setBackground(Color.LIGHT_GRAY);
		showfinalresult.setFont(new Font("微軟正黑體",Font.PLAIN,14));
		mainP.add(showfinalresult);
		
		startballot.setBounds(155,500,150,30);
		startballot.setVisible(true);
		startballot.addActionListener(actionco);
		startballot.setBackground(Color.LIGHT_GRAY);
		adminP.add(startballot);
		
		endballot.setBounds(155,550,150,30);
		endballot.setVisible(true);
		endballot.addActionListener(actionco);
		endballot.setBackground(Color.LIGHT_GRAY);
		adminP.add(endballot);
		
		deployeButton.setBounds(155,550,180,100);
		deployeButton.setVisible(true);
		deployeButton.addActionListener(actionco);
		deployeButton.setBackground(Color.LIGHT_GRAY);
		deployeframe.add(deployeButton);
		
		refresh.setBounds(155,600,150,30);
		refresh.setVisible(true);
		refresh.addActionListener(actionco);
		refresh.setBackground(Color.LIGHT_GRAY);
		adminP.add(refresh);
		
		l.setBounds(175, 50, 200, 80);
		l.setVisible(true);
		l.setFont(new Font("微軟正黑體",Font.BOLD,30));
		mainP.add(l);
		
		login.setBounds(0,0,300,20);
		login.setVisible(true);
		login.setFont(new Font(font,Font.PLAIN,12));
		login.setBackground(Color.BLUE);
		loginP.add(login);
		
		loginsign.setBounds(180,220,150,50);
		loginsign.setVisible(true);
		loginsign.setFont(new Font("微軟正黑體",Font.PLAIN,22));
		loginsign.setBackground(Color.BLUE);
		loginP.add(loginsign);
		
		systemlogo.setBounds(220,120,60,96);
		systemlogo.setVisible(true);
		loginP.add(systemlogo);
		
		loadL.setBounds(150,170,200,200);
		loadL.setVisible(true);
		loadP.add(loadL);
		
		for(int i=0;i<3;i++) {
			int temp=i*50;
			candidate[i] = new JLabel(" ");
			candidate[i].setBounds(120,210+temp,350,80);
			candidate[i].setFont(f3);
			mainP.add(candidate[i]);
		}
		for(int i=0;i<3;i++) {
			int temp=i*50;
			candidateadmin[i] = new JLabel(" ");
			candidateadmin[i].setBounds(150,150+temp,350,80);
			candidateadmin[i].setFont(f3);
			adminP.add(candidateadmin[i]);
		}
		for(int i=0;i<4;i++) {
			int temp=i*50;
			candidateresult[i] = new JLabel(" ");
			candidateresult[i].setBounds(100,150+temp,350,80);
			candidateresult[i].setFont(f3);
			resultP.add(candidateresult[i]);
		}
		for(int i=0;i<3;i++) {
			int temp = i*50;
			voteButton[i] = new JButton("vote");
			voteButton[i].setVisible(true);
			voteButton[i].setBounds(320,240+temp,100,30);
			voteButton[i].addActionListener(actionco);
			voteButton[i].setBackground(Color.LIGHT_GRAY);
			mainP.add(voteButton[i]);
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
	private String getAddress() throws Exception {//從母合約取得子合約地址
		Web3j web3j=Web3j.build(new HttpService("http://localhost:7545"));
		Deploycontract deploycontract = loadContract2(DEPLOYCONTRACT_ADDRESS,web3j,getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
		return deploycontract.electioncontract().send();
	}
	
	private void Main(String name1,String name2,String name3) throws Exception { //發布投票合約(子合約)
		Web3j web3j=Web3j.build(new HttpService("http://localhost:7545"));
		
		Credentials credentials = getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY);
		
		Deploycontract deploycontract = loadContract2(DEPLOYCONTRACT_ADDRESS,web3j,credentials);
		
		deploycontract.depoy_contract().send();
     	
     		contract_address = getAddress();
		JOptionPane.showMessageDialog(null, contract_address, "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
     		Editedelection election = loadContract(contract_address,web3j,credentials);
     	
     		input(election,name1,name2,name3);
     	
	}
	private void VOTE_END() {
		Web3j web3j = Web3j.build(new HttpService("http://localhost:7545"));
		EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST, contract_address);
		web3j.ethLogFlowable(filter).subscribe(log->      System.out.println(log.toString()));
	}
	private String owner(Editedelection election) throws Exception { //回傳管理者的地址，再登入時用來確認登入者輸入的地址是否為管理者的
		return election.owner().send();
	}
	private void vote(Web3j web3j,String privatekey,String index) throws Exception {//投票功能
		int index_ = Integer.parseInt(index);
		Editedelection election= loadContract(contract_address, web3j,getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
		election.vote(BigInteger.valueOf(index_)).send();
	}
	private void input(Editedelection election,String name1,String name2,String name3) throws Exception {//管理者發布合約時輸入候選人姓名
		election.input(name1).send();
		election.input(name2).send();
		election.input(name3).send();
	}
	private void finalresult(Web3j web3j) throws Exception {//顯示出選舉結果
		Editedelection election= loadContract(contract_address, web3j, getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
		for(int i=0;i<3;i++) {
			String result = election.finalresult(BigInteger.valueOf(i)).send().toString();
			//呼叫合約中舊有的回傳候選人票數方法
			String nominee = election.showN(BigInteger.valueOf(i)).send().toString();
			candidateresult[i+1].setText(i+1+"."+nominee+" "+result+"votes");
		}
	}
	private void winner(Web3j web3j,Editedelection election) throws Exception{//顯示選舉獲勝者
		String winner = election.winner().send().toString();
		candidateresult[0].setText("winner is : "+winner);
	}
	private void showN(Editedelection election) throws Exception{//此方法用來將候選人資料顯示在投票者和管理者的視窗內
		for (int i=0;i<3;i++) {
			int temp=i+1;
			String  nominee = election.showN(BigInteger.valueOf(i)).send().toString();
			candidate[i].setText(temp+"."+nominee);
			candidateadmin[i].setText(temp+"."+nominee);
	    } 
	}
	public boolean ballotend() throws Exception {//管理者結束投票
			Web3j web3j=Web3j.build(new HttpService("http://localhost:7545"));
			Editedelection election = loadContract(contract_address, web3j,getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
			return election.ballotend().send();
	} 
	
	public void run() { //此方法非常重要，城市開始時他將不斷向區塊鏈網路確認投票是否已經開始，才不會出現投票已經結束投票者系統卻以為投票還沒開始的情況
		//此方法使用執行序，已讓投票系統其他功能運作下，他能夠以背景程式的形式不斷執行
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			if(check) {
				Web3j web3j=Web3j.build(new HttpService("http://localhost:7545"));
				Editedelection election = loadContract(contract_address, web3j,getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
				try {
					if(election.ballotend().send()==true) {
						if_start=false;
					}else{
						if_start=true;
						System.out.print("load sucess");
						break;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {}
		}
	}
	private Credentials getCredentialsFromPrivateKey(String privatekey) {//使用私鑰來取得帳戶資料
		return Credentials.create(privatekey);
	}
	private String deployContract2(Web3j web3j, Credentials credentials) throws Exception {//發布投票合約
		return Deploycontract.deploy(web3j, credentials,GAS_PRICE,GAS_LIMIT).send().getContractAddress();
	}
	private Editedelection loadContract(String contractaddress,Web3j web3j,Credentials credentials) {//透過合約地址取得與合約的聯繫
		return Editedelection.load(contractaddress, web3j, credentials, GAS_PRICE,GAS_LIMIT);
	}
	private Deploycontract loadContract2(String contractaddress,Web3j web3j,Credentials credentials) {//透過合約地址取得與合約的聯繫
		return Deploycontract.load(contractaddress, web3j, credentials, GAS_PRICE,GAS_LIMIT);
	}
	public ActionListener actionco = new ActionListener() {//使用者介面的事件頃聽器
		public void actionPerformed(ActionEvent e) {
			Web3j web3j=Web3j.build(new HttpService("http://localhost:7545"));
			if(e.getSource()==confirm) {//登入畫面的確認鈕
				INPUT_PRIVATE_KEY=privatekey.getText();
				try {
					contract_address = getAddress();
				} catch (Exception e3) {
					// TODO Auto-generated catch block+
					e3.printStackTrace();
					JOptionPane.showMessageDialog(null, "can't get the contract address", "ERROR", JOptionPane.ERROR_MESSAGE);
					//當合約還沒發布
				}
				try {
					Credentials credentialtest = getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY);
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "ACCOUNT NOT EXIST", "ERROR", JOptionPane.ERROR_MESSAGE);
					//如果登入者所輸入的私鑰不存在於區塊鏈上
				}
				Credentials credential = getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY);
				if(credential.getAddress().toLowerCase().equals(inputaddress.getText().toLowerCase())){
					check=true;
					try {
						Editedelection electionlogin = loadContract(contract_address,web3j, getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
						loginP.setVisible(false);
						if(credential.getAddress().equals(owner(electionlogin))) {
							deployeframe.setVisible(true);
							try {
								Editedelection election = loadContract(contract_address,web3j, getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
								showN(election);
								loadP.setVisible(false);
								adminP.setVisible(true);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}else {
							INPUT_PRIVATE_KEY=privatekey.getText();
							if(check) {
							}
							try {
								Editedelection election = loadContract(contract_address,web3j, getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
								owner(election);
								showN(election);
								loadP.setVisible(false);
								mainP.setVisible(true);
							} catch (Exception e1) {
									// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "CAN'T LOAD CONTRACT", "ERROR", JOptionPane.ERROR_MESSAGE);
						deployeframe.setVisible(true);
						e1.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(null, "INCORRECT PASSWORD", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}else if(e.getSource()==showfinalresult) {
				Editedelection election = loadContract(contract_address,web3j, getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
				try {
						boolean temp = ballotend();
						if(temp==true) {
							if(if_start==false) {
								JOptionPane.showMessageDialog(null, "BALLOT HAVEN'T START", "ERROR", JOptionPane.ERROR_MESSAGE);
							}else {
								mainP.setVisible(false);
								resultP.setVisible(true);
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
							}
						}else {
							if_start=true;
							JOptionPane.showMessageDialog(null, "BALLOT HAVEN'T END", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "I HAVE NO IDEA WHY THIS FAIL", "ERROR", JOptionPane.ERROR_MESSAGE);
					e2.printStackTrace();
				}
			}else if(e.getSource()==startballot) {
				Editedelection election = loadContract(contract_address,web3j, getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
				try {
					election.startballot().send();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if_start = true;
				//System.out.println("Start ballot");
			}else if(e.getSource()==endballot) {
				Editedelection election = loadContract(contract_address,web3j, getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
				try {
					election.endballot().send();
				} catch (Exception e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				adminP.setVisible(false);
				resultP.setVisible(true);
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
				Editedelection election = loadContract(contract_address,web3j, getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
				try {
					if(election.ballotend().send()==true) {
						if(if_start==true)
						JOptionPane.showMessageDialog(null, "BALLOT HAS ENDED", "ERROR", JOptionPane.ERROR_MESSAGE);
						else
						JOptionPane.showMessageDialog(null, "BALLOT HASN'T START", "ERROR", JOptionPane.ERROR_MESSAGE);
					}else {
					}
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					vote(web3j, INPUT_PRIVATE_KEY,"0");
					//VOTE_END();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(e.getSource()==voteButton[1]) {
				Editedelection election = loadContract(contract_address,web3j, getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
				try {
					if(election.ballotend().send()==true) {
						if(if_start==true)
							JOptionPane.showMessageDialog(null, "BALLOT HAS ENDED", "ERROR", JOptionPane.ERROR_MESSAGE);
							else
							JOptionPane.showMessageDialog(null, "BALLOT HASN'T START", "ERROR", JOptionPane.ERROR_MESSAGE);
					}else {
					}
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					vote(web3j, INPUT_PRIVATE_KEY,"1");
					//VOTE_END();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(e.getSource()==voteButton[2]) {
				Editedelection election = loadContract(contract_address,web3j, getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
				try {
					if(election.ballotend().send()==true) {
						if(if_start==true)
							JOptionPane.showMessageDialog(null, "BALLOT HAS ENDED", "ERROR", JOptionPane.ERROR_MESSAGE);
							else
							JOptionPane.showMessageDialog(null, "BALLOT HASN'T START", "ERROR", JOptionPane.ERROR_MESSAGE);
					}else {
					}
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					vote(web3j, INPUT_PRIVATE_KEY,"2");
					//VOTE_END();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(e.getSource()==deployeButton) {
				try {
					Main(candidatefill[0].getText(),candidatefill[1].getText(),candidatefill[2].getText());
					JOptionPane.showMessageDialog(null, "DEPLOY SUCESS", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
					deployeframe.setVisible(false);
					//部屬合約地址寫進檔案中
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "DEPLOY FAIL","ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}else if(e.getSource()==refresh) {
				Editedelection election = loadContract(contract_address,web3j, getCredentialsFromPrivateKey(INPUT_PRIVATE_KEY));
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
//Deploycontract
