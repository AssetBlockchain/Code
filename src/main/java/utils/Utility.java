package utils;

import java.util.Random;

import org.hyperledger.fabric.sdk.Chain;
import org.hyperledger.fabric.sdk.FileKeyValStore;
import org.hyperledger.fabric.sdk.KeyValStore;
import org.hyperledger.fabric.sdk.Member;
 

public class Utility {

	//--------------------------------------------------------------------------------------------------------------------
//	Defines the exported values to be used by other fields for connecting to peers or the app. These will be overwritten on app.js being run if Bluemix is being used or Network JSON is defined
//--------------------------------------------------------------------------------------------------------------------
//IP and port configuration
	public static final String apiIP = "http://127.0.0.1"; //IP of the peer attempting to be connected to. By default this is the first peer in the peers array.

//When using blockchain on bluemix, api_port_external and api_port_internal will be the same
public static final String api_port_external 	= "5000"; //port number used when calling api from outside of the vagrant environment
public static final String api_port_internal 	= "5000"; //port number used when calling api from inside vagrant environment - generally used for chaincode calling out to api
public static final String api_port_discovery 	= "30303"; //port number used for HFC

//IP and port configuration for the Certificate Authority. This is used for enrolling WebAppAdmin and creating all the user via HFC. Default values are for running Hyperledger locally.
public static final String ca_ip 	= "127.0.0.1"; 	//IP of the CA attempting to be connected to
public static final String ca_port 	= "50051"; 		//Discovery port of the Certificate Authority. Used for HFC

//Settings for the nodeJS application server
public static final String app_url 	= "http://localhost:80"; 	//Url of the NodeJS Server
public static final int app_port = 80; 						//Port that the NodeJS server is operating on

//Information about all peers in the network, currently only used for checking that chaincode has been deployed to all peers on startup
public static final String peers = "http://127.0.0.1";

//--------------------------------------------------------------------------------------------------------------------
//	User information - These credentials are used for HFC to enroll this user and then set them as the registrar to create new users.
//--------------------------------------------------------------------------------------------------------------------

public static final String registrar_name 		= "WebAppAdmin";
public static final String registrar_password 	= "1a9513992f";

//--------------------------------------------------------------------------------------------------------------------
//	HFC configuration - Defines what protocol to use for communication, bluemix certificate location and key store location
//--------------------------------------------------------------------------------------------------------------------

//Protocol used by HFC to communicate with blockchain peers and CA, need to change this manually.
public static final String hfc_protocol				= "grpcs";
public static final String certificate_file_name	= "us.blockchain.ibm.com.cert";
public static final String key_store_location		= "/tmp/keyValStore";

//--------------------------------------------------------------------------------------------------------------------
//	Chaincode
//--------------------------------------------------------------------------------------------------------------------
//Chaincode file location
public static final String vehicle = "https://github.com/IBM-Blockchain/car-lease-demo/Chaincode/vehicle_code/";

//Chaincode deployed names
public static final String vehicle_name = "2e436d1363bb9f7c00342fa3fe30eff2c303f06ed4a396a06416a5e1e488ead7edd99f337e31fc6692d785bc0d9a30e942f111caccae15e7c34de6c716f0d92a";

private static Object[] users;
	
public static String submitRequest(String userID, String id, String assetID, String functionName, String methodName)
{
	String requestBody="{ \"jsonrpc\": \"2.0\",\"method\": "+methodName+"\"params\": {\"type\": 1,\"chaincodeID\": {\"name\": "+Utility.vehicle_name+"},\"ctorMsg\": {\"function\": "+functionName+",\"args\": ["+assetID+"]},\"secureContext\":"+userID+"},\"id\": "+id+"}";
	return "";
}

public static String getAssetID()
{
	String assetID=createAssetID();
	while(isAssetIDExist(assetID))
	{
		assetID=createAssetID();
	}
	return assetID;
}
public static void registerUser()
{
	Chain chain = new Chain("chain1");
	KeyValStore keystore=new FileKeyValStore(key_store_location);
	chain.setKeyValStore(keystore);
	chain.setDeployWaitTime(60);

	//Retrieve the certificate if grpcs is being used
	if(hfc_protocol == "grpcs"){
		chain.set.setECDSAModeForGRPC(true);
		Object pem = fs.readFileSync(certificate_file_name);		
	}

	//chain.setMemberServicesUrl(hfc_protocol+"://"+ca_ip+":"+ca_port, {pem:pem});
	//chain.addPeer(configFile.config.hfc_protocol+"://"+api_ip+":"+configFile.config.api_port_discovery, {pem:pem});
	
	Member registrar = chain.enroll(registrar_name, registrar_password) ;
	chain.setRegistrar(registrar);
	
		
		//Start the process of registering and enrolling the demo participants with the CA
		addUser();
}
private static void addUser() {
	// TODO Auto-generated method stub

	String userAff = "00000";
	
	//mapping participant type to an integer which will be stored in the user's eCert as their affiliation. Dealerships and Leasees both map to the same affiliation as they are both seen as 'Private entities'
	switch (users[counter].type) {
			case "regulators": 
				userAff = "00001";
				break;
			case "manufacturers":
				userAff = "00002";
				break;
			case "dealerships":
				userAff = "00003";
				break;
			case "lease_companies":
				userAff = "00004";
				break;
			case "leasees":
				userAff = "00003";
				break;
			case "scrap_merchants":
				userAff = "00005";
				break;
	}
}

private static boolean isAssetIDExist(String assetID) {

	//String response =submitRequest(userID, id, assetID, "get_diamond_details", "Query");
	return false;
}

public static String createAssetID()
{
	String numbers = "1234567890";
	String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	String v5cID = "";
	
	Random rand = new Random();

	int  n = rand.nextInt(50) + 1;
	for(int i = 0; i < 7; i++)
	{
		Double num=Math.random() * numbers.length();
		v5cID += numbers.charAt(num.intValue());
	}
	Double num=Math.random() * numbers.length();
	
	v5cID = characters.charAt(num.intValue()) + v5cID;
	num=Math.random() * numbers.length();
	v5cID = characters.charAt(num.intValue()) + v5cID;
	return v5cID;
}
}
