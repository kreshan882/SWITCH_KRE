package com.epic.mfn.xml;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.epic.mfn.init.InitConfigValue;
import com.epic.mfn.util.TxnKeyWords;
//import com.epic.nlfsim.Transaction;
//import com.epic.nlfsim.SecurityProcessing;

public class XmlReader {
	
	
	public static void TxnLimitReader( TxnKeyWords keyWd)throws Exception{
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(new File(InitConfigValue.SCONFIGPATH
				+ "TransactionLimits.xml"));
		//Document doc = docBuilder.parse(new File( "/opt/epicplc/sconfig/TransactionLimits.xml"));

		doc.getDocumentElement().normalize();
		boolean TerminalFound = false ;
		NodeList ListOfCompanyLimit 	= doc.getElementsByTagName("Company");
		NodeList ListOfTerminalLimit	= doc.getElementsByTagName("Terminal");
		System.out.println("Company :" + keyWd.getCOMPANY() ) ;
		System.out.println("Tid     :" + keyWd.getTID() ) ;
		for (int i = 0; i < ListOfTerminalLimit.getLength(); i++)
		{
		      Node node = ListOfTerminalLimit.item(i);
		      if (node.getNodeType() == Node.ELEMENT_NODE)
		      {                 
		             if(((Element) node).hasChildNodes())
		             {
		                 NodeList nl = node.getChildNodes();
		                 for(int j=0; j<nl.getLength(); j++)
		                 {
		                	 
		                     Node nd = nl.item(j);
		                     System.out.println("AAAAA:"+j+"    B:"+nd.getTextContent());
		                     if( j == 1){
		                    	if(nd.getTextContent().trim().equals(keyWd.getCOMPANY() )){
		                    		//CompanyMatched = true ;
		                     	}else{
		                     		break ;
		                     	}
		                     }
		                     else if( j == 3 ){
		                    	 if( ! nd.getTextContent().equals(keyWd.getTID()) ){
		                    		 break ;
		                    	 }
		                     }else if( j == 5 ){
		                    	 keyWd.setXML_PER_BATCH_LIMIT(nd.getTextContent()) ;
		                    	 TerminalFound = true ;
		                    	 System.out.println("Terminal Found.........") ;
		                     }
		                     
		                     
		                     //System.out.println( i + "--" + j + "--" + nd.getTextContent());
		                 }
		             } 
		       }
		}
		if( ! TerminalFound ){
			
			for (int i = 0; i < ListOfCompanyLimit.getLength(); i++)
			{
			      Node node = ListOfCompanyLimit.item(i);
			      if (node.getNodeType() == Node.ELEMENT_NODE)
			      {                 
			             if(((Element) node).hasChildNodes())
			             {
			                 NodeList nl = node.getChildNodes();
			                 
			                 for(int j=0; j<nl.getLength(); j++)
			                 {
			                     Node nd = nl.item(j);
			                     System.out.println("BBBB:"+j+"    B:"+nd.getTextContent());
			                     if( j == 1){
			                    	if(nd.getTextContent().equals(keyWd.getCOMPANY() )){
			                    		//CompanyMatched = true ;
			                     	}else{
			                     		break ;
			                     	}
			                     }
			                     else if( j == 3 ){
			                    	 keyWd.setXML_PER_BATCH_LIMIT(nd.getTextContent()) ;
			                    	 TerminalFound = true ;
			                    	 System.out.println("Set Company Per Batch Limit For Terminal") ;
			                     }
			                     //System.out.println( i + "--" + j + "--" + nd.getTextContent());
			                 }
			             }
			       }
			}
		}
		if( ! TerminalFound ){
			keyWd.setRESPONSECODE("P1");
			keyWd.setERROR_DESCRIPTION("per batch lim not defined ") ;
			
		}
		TerminalFound = false ;
		//System.out.println(a.getFirstChild().getNodeValue()) ;
	}

	

}
