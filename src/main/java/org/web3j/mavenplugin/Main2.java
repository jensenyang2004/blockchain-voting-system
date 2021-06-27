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


		
		
		private void main() throws Exception {
			Web3j web3j=Web3j.build(new HttpService("http://localhost:7545"));
			Credentials credential = getCredentialsFromPrivateKey(PRIVATE_KEY);
			String contractaddress = deployContract(web3j,credential);
			System.out.println("contract has deployed");
			System.out.println("contract address: "+contractaddress);
		}
		private Credentials getCredentialsFromPrivateKey(String privatekey) {
			return Credentials.create(privatekey);
		}
		private String deployContract(Web3j web3j, Credentials credentials) throws Exception {
			return Deploycontract.deploy(web3j, credentials,GAS_PRICE,GAS_LIMIT).send().getContractAddress();
		}
		private Deploycontract loadContract(String contractaddress,Web3j web3j,Credentials credentials) {
			return Deploycontract.load(contractaddress, web3j, credentials, GAS_PRICE,GAS_LIMIT);
		}
		public static void main(String[] args) throws Exception {
			Main2 m = new Main2();
			m.main();
		}
	
}
