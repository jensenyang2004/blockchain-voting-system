package org.web3j.mavenplugin;

import java.math.BigInteger;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import contract.Deploycontract;
import contract.Editedelection;

public class Main2 {
	
		private final static BigInteger GAS_LIMIT=BigInteger.valueOf(6721975L);
		private final static BigInteger GAS_PRICE=BigInteger.valueOf(20000000000L);
		private final static String PRIVATE_KEY = "fe5c720ac19c820c2584ac937bc1264cf31e35f07fc0e5e1df38e185e339f820";
		//母合約只會被發布一次，在投票系統發布前，母合約就必須被發佈在區塊鏈上，而發布時必須使用管理者私鑰，理論上這個檔案在母合約發布後會刪除，管理者私鑰才不會外流

		private void main() throws Exception {
			Web3j web3j=Web3j.build(new HttpService("http://localhost:7545"));
			//與區塊練取得連線，此處因使用的是localhost上的測試鏈，所以會連接到127.0.0.1的位置
			Credentials credential = getCredentialsFromPrivateKey(PRIVATE_KEY);
			//透過私鑰取得帳戶資訊，類似解鎖帳號以呼叫區塊鏈上的智能合約
			String contractaddress = deployContract(web3j,credential);
			//發布合約並回傳合約地址，合約地址將被寫入投票系統的java程式中，向前面說得，此檔將會被刪除
			
		}
		private Credentials getCredentialsFromPrivateKey(String privatekey) {
			return Credentials.create(privatekey);
			//呼叫區塊鏈以取得帳號存取權
		}
		private String deployContract(Web3j web3j, Credentials credentials) throws Exception {
			return Deploycontract.deploy(web3j, credentials,GAS_PRICE,GAS_LIMIT).send().getContractAddress();
			//發布合約
		}
		public static void main(String[] args) throws Exception {
			Main2 m = new Main2();
			m.main();
		}
	
}
