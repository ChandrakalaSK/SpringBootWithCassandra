package com.ineuron.AstraJavaDemo;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import java.nio.file.Paths;



public class ConnectDatabase {

    CqlSession session = null;

    // Connect with Astra DB
    public void connect(){
         session = CqlSession.builder()
 	            .withCloudSecureConnectBundle(Paths.get("C:\\Users\\chand\\Downloads\\secure-connect-ineuron.zip"))
 	            .withAuthCredentials("MgJDZnXLPgWYWICeLzbxkShb","WXfqZdLJhv8h-F0nmHA7oiuLM5lOuBWBAA8TnbsIMSSExy6qNMEl.Ss2InzT2KsPZMiNZ7T.UIElyCkxybWM6spLyg-FKiknse.0PU6ul.aguirgfHRhxN0YZXDKoBhT")
 	            .build();
    }

    public String getValue(String key) {

        if(session==null)
            connect();

        String value = "";

            // Execute the Query to fetch the value for a specific key
            // teluskokey is a keyspace and my_gadgets is the table
            ResultSet rs = session.execute("select value from nosqldemo.my_gadgets where key = '" + key + "'");
            Row row = rs.one();

            if (row != null) {
                value = row.getString("value");
            } else {
                System.out.println("An error occurred.");
            }

        return value;
    }
}

