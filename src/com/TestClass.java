package com;

import java.util.List;

import com.amazonaws.services.rdsdata.AWSRDSData;
import com.amazonaws.services.rdsdata.AWSRDSDataClient;
import com.amazonaws.services.rdsdata.model.ExecuteStatementRequest;
import com.amazonaws.services.rdsdata.model.ExecuteStatementResult;
import com.amazonaws.services.rdsdata.model.Field;

public class TestClass {
		public static final String RESOURCE_ARN = "arn:aws:rds:ap-south-1:438413714164:cluster:testdb2";
		public static final String SECRET_ARN = "arn:aws:secretsmanager:ap-south-1:438413714164:secret:rds-db-credentials/cluster-MEONWBHUJDPSLV5Y2QWUXFMC3Q/rajeshr-pRI8ph";
	public static void main(String[] args) {
		AWSRDSData rdsData = AWSRDSDataClient.builder().build();
		
		ExecuteStatementRequest request = new ExecuteStatementRequest()
	            .withResourceArn(RESOURCE_ARN)
	            .withSecretArn(SECRET_ARN)
	            
	           // .withDatabase("cards")
	            .withContinueAfterTimeout(true)
	            .withSchema("cards")
	            .withSql("select * from card_cust_details");
	            

	    ExecuteStatementResult result = rdsData.executeStatement(request);

	    for (List<Field> fields: result.getRecords()) {
	    	long stringValue = fields.get(0).getLongValue();
	      long numberValue = fields.get(1).getLongValue();

	      System.out.println(String.format("Fetched row: string = %d, number = %d", stringValue, numberValue));
	    }
	    
	}

}
