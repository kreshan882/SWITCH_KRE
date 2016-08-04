package com.epic.mfn.xml;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.epic.mfn.connect.HostConnection;
import com.epic.mfn.init.InitConfigValue;

/**
 * This class is using to read xml values while les is starting
 * 
 * @author Kapila shantha rajapaksha
 * 
 */
public class InitXmlReader {

	private static NodeList nList = null;

	private static NodeList nList1 = null;

	private static Element elem = null;

	/**
	 * this method read the values of serverconf.xml
	 * 
	 * @throws Exception
	 */
	public static void readSConfigValues() throws Exception {

		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(new File(InitConfigValue.SCONFIGPATH
				+ "Sconfig.xml"));

		doc.getDocumentElement().normalize();
		NodeList listOfSPRMs = doc.getElementsByTagName("SCONFIG");

		Node firstSPRMNode = listOfSPRMs.item(0);

		if (firstSPRMNode.getNodeType() == Node.ELEMENT_NODE) {

			Element firstSPRAMElement = (Element) firstSPRMNode;

			// Getting run port
			nList = firstSPRAMElement.getElementsByTagName("PORT");
			elem = (Element) nList.item(0);
			nList1 = elem.getChildNodes();
			InitConfigValue.RUNPORT = Integer.parseInt(((Node) nList1.item(0)).getNodeValue().trim());
			doEmpty();

			// Getting run mode
			nList = firstSPRAMElement.getElementsByTagName("RUNMODE");
			elem = (Element) nList.item(0);
			nList1 = elem.getChildNodes();
			InitConfigValue.RUNMODE = Integer.parseInt(((Node) nList1.item(0)).getNodeValue().trim());
			doEmpty();

			// Getting log file name
			nList = firstSPRAMElement.getElementsByTagName("LOGFILENAME");
			elem = (Element) nList.item(0);
			nList1 = elem.getChildNodes();
			InitConfigValue.LOGFILENAME = ((Node) nList1.item(0)).getNodeValue().trim();
			doEmpty();

			// Getting log level
			nList = firstSPRAMElement.getElementsByTagName("LOGLEVEL");
			elem = (Element) nList.item(0);
			nList1 = elem.getChildNodes();
			InitConfigValue.LOGLEVEL = Integer.parseInt(((Node) nList1.item(0)).getNodeValue().trim());
			doEmpty();

			// Getting console level
			nList = firstSPRAMElement.getElementsByTagName("CONSOLELEVEL");
			elem = (Element) nList.item(0);
			nList1 = elem.getChildNodes();
			InitConfigValue.CONSOLELEVEL = Integer.parseInt(((Node) nList1.item(0)).getNodeValue().trim());
			doEmpty();

			// Getting header size
			nList = firstSPRAMElement.getElementsByTagName("HEADERSIZE");
			elem = (Element) nList.item(0);
			nList1 = elem.getChildNodes();
			InitConfigValue.HEADERSIZE = Integer.parseInt(((Node) nList1.item(0)).getNodeValue().trim());
			doEmpty();

			// Getting packet size
			nList = firstSPRAMElement.getElementsByTagName("PACKETSIZE");
			elem = (Element) nList.item(0);
			nList1 = elem.getChildNodes();
			InitConfigValue.PACKETSIZE = Integer.parseInt(((Node) nList1.item(0)).getNodeValue().trim());
			doEmpty();

			// Getting timeout value
			nList = firstSPRAMElement.getElementsByTagName("PROCESSINGTIMEOUT");
			elem = (Element) nList.item(0);
			nList1 = elem.getChildNodes();
			InitConfigValue.PROCESSINGTIMEOUT = Integer.parseInt(((Node) nList1.item(0)).getNodeValue().trim());
			doEmpty();

			// Getting max thread pool size
			nList = firstSPRAMElement.getElementsByTagName("POOLMAXTHREAD");
			elem = (Element) nList.item(0);
			nList1 = elem.getChildNodes();
			InitConfigValue.THREADPOOLMAXSIZE = Integer.parseInt(((Node) nList1.item(0)).getNodeValue().trim());
			doEmpty();

			// Getting min thread pool size
			nList = firstSPRAMElement.getElementsByTagName("POOLMINTHREAD");
			elem = (Element) nList.item(0);
			nList1 = elem.getChildNodes();
			InitConfigValue.THREADPOOLMINSIZE = Integer.parseInt(((Node) nList1.item(0)).getNodeValue().trim());
			doEmpty();

			// Getting min queue size
			nList = firstSPRAMElement.getElementsByTagName("POOLMAXQUEQUE");
			elem = (Element) nList.item(0);
			nList1 = elem.getChildNodes();
			InitConfigValue.THREADPOOLQUEQUSIZE = Integer.parseInt(((Node) nList1.item(0)).getNodeValue().trim());
			doEmpty();

			// Getting back log thread pool size
			nList = firstSPRAMElement.getElementsByTagName("POOLBACKLOG");
			elem = (Element) nList.item(0);
			nList1 = elem.getChildNodes();
			InitConfigValue.THREADPOOLBACKLOGSIZE = Integer.parseInt(((Node) nList1.item(0)).getNodeValue().trim());
			doEmpty();

			// Getting processing class name
			nList = firstSPRAMElement.getElementsByTagName("PROCESSCLASS");
			elem = (Element) nList.item(0);
			nList1 = elem.getChildNodes();
			InitConfigValue.PRCESSCLASS = ((Node) nList1.item(0)).getNodeValue().trim();
			doEmpty();

			// Getting db username
			nList = firstSPRAMElement.getElementsByTagName("DBUSERNAME");
			elem = (Element) nList.item(0);
			nList1 = elem.getChildNodes();
			InitConfigValue.DBUSERNAME = ((Node) nList1.item(0)).getNodeValue().trim();
			doEmpty();

			// Getting db password
			nList = firstSPRAMElement.getElementsByTagName("DBPASSWORD");
			elem = (Element) nList.item(0);
			nList1 = elem.getChildNodes();
			InitConfigValue.DBPASSWORD = ((Node) nList1.item(0)).getNodeValue().trim();
			doEmpty();

			// Getting db driver
			nList = firstSPRAMElement.getElementsByTagName("DBDRIVER");
			elem = (Element) nList.item(0);
			nList1 = elem.getChildNodes();
			InitConfigValue.DBDRIVER = ((Node) nList1.item(0)).getNodeValue().trim();
			doEmpty();

			// Getting db url
			nList = firstSPRAMElement.getElementsByTagName("DBURL");
			elem = (Element) nList.item(0);
			nList1 = elem.getChildNodes();
			InitConfigValue.DBURL = ((Node) nList1.item(0)).getNodeValue().trim();
			doEmpty();

			// Getting db max con size
			nList = firstSPRAMElement.getElementsByTagName("DBMAXCON");
			elem = (Element) nList.item(0);
			nList1 = elem.getChildNodes();
			InitConfigValue.MAXCON = Integer.parseInt(((Node) nList1.item(0)).getNodeValue().trim());
			doEmpty();

			// Getting db max pool size
			nList = firstSPRAMElement.getElementsByTagName("DBMAXPOOL");
			elem = (Element) nList.item(0);
			nList1 = elem.getChildNodes();
			InitConfigValue.MAXPOOL = Integer.parseInt(((Node) nList1.item(0)).getNodeValue().trim());
			doEmpty();

			// Getting db connection timeout
			nList = firstSPRAMElement
					.getElementsByTagName("DBCONNECTIONTIMEOUT");
			elem = (Element) nList.item(0);
			nList1 = elem.getChildNodes();
			InitConfigValue.DBCONNECTIONTIMEOUT = Integer.parseInt(((Node) nList1.item(0)).getNodeValue().trim());
			doEmpty();

			// Getting so timeout
			nList = firstSPRAMElement.getElementsByTagName("SOTIMEOUT");
			elem = (Element) nList.item(0);
			nList1 = elem.getChildNodes();
			InitConfigValue.SOTIMEOUT = Integer.parseInt(((Node) nList1.item(0)).getNodeValue().trim());
			doEmpty();

			// Getting db expir timeout
			nList = firstSPRAMElement.getElementsByTagName("DBEXPIRTIMEOUT");
			elem = (Element) nList.item(0);
			nList1 = elem.getChildNodes();
			InitConfigValue.DBCONEXPIRTIMEOUT = Integer.parseInt(((Node) nList1.item(0)).getNodeValue().trim());
			doEmpty();
			/*
			nList = firstSPRAMElement.getElementsByTagName("DASHBORDSTATUS");
			elem = (Element) nList.item(0);
			nList1 = elem.getChildNodes();
			InitConfigValue.DASHBORDSTATUS = Integer.parseInt(((Node) nList1.item(0)).getNodeValue().trim());
			doEmpty();
			
			nList = firstSPRAMElement.getElementsByTagName("DASHBORDIP");
			elem = (Element) nList.item(0);
			nList1 = elem.getChildNodes();
			InitConfigValue.DASHBORDIP = ((Node) nList1.item(0)).getNodeValue().trim();
			doEmpty();
			
			nList = firstSPRAMElement.getElementsByTagName("DASHBORDPORT");
			elem = (Element) nList.item(0);
			nList1 = elem.getChildNodes();
			InitConfigValue.DASHBORDPORT = Integer.parseInt(((Node) nList1.item(0)).getNodeValue().trim());
			doEmpty();
			
			nList = firstSPRAMElement.getElementsByTagName("DASHBORDTIMEOUT");
			elem = (Element) nList.item(0);
			nList1 = elem.getChildNodes();
			InitConfigValue.DASHBORDTIMEOUT = Integer.parseInt(((Node) nList1.item(0)).getNodeValue().trim());
			doEmpty();
			*/
		}
	}

	private static void doEmpty() {
		nList1 = null;
		nList = null;
		elem = null;
	}

	public static ArrayList<HostConnection> readHostConfigValues()
			throws Exception {

		ArrayList<HostConnection> hostConnectons = new ArrayList<HostConnection>();

		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(new File(InitConfigValue.SCONFIGPATH+ "Host.xml"));

		doc.getDocumentElement().normalize();
		NodeList listOfSPRMs = doc.getElementsByTagName("HOST_LIST");

		Node firstNode = listOfSPRMs.item(0);
		Node firstChild = firstNode.getFirstChild();

		do {
			if (firstChild.getNodeType() == Node.ELEMENT_NODE) {
				HostConnection hostConnection = new HostConnection();

				if (firstNode.getNodeType() == Node.ELEMENT_NODE) {
					Element firstSPRAMElement = (Element) firstChild;

					nList = firstSPRAMElement.getElementsByTagName("HOST_NAME");
					elem = (Element) nList.item(0);
					nList1 = elem.getChildNodes();
					hostConnection.setHostName(((Node) nList1.item(0)).getNodeValue().trim());
					
					nList = firstSPRAMElement.getElementsByTagName("HOST_STATUS");
					elem = (Element) nList.item(0);
					nList1 = elem.getChildNodes();
					if (Integer.parseInt(((Node) nList1.item(0)).getNodeValue().trim()) == 1)
					hostConnection.setActive(true);
					
					nList = firstSPRAMElement.getElementsByTagName("CONNECTION_MODE");
					elem = (Element) nList.item(0);
					nList1 = elem.getChildNodes();
					if (Integer.parseInt(((Node) nList1.item(0)).getNodeValue().trim()) == 1)
						hostConnection.setPermenetConnectionMode(true);
					
					nList = firstSPRAMElement.getElementsByTagName("HOST_IP");
					elem = (Element) nList.item(0);
					nList1 = elem.getChildNodes();
					hostConnection.setHostIp(((Node) nList1.item(0)).getNodeValue().trim());
					
					nList = firstSPRAMElement.getElementsByTagName("HOST_PORT");
					elem = (Element) nList.item(0);
					nList1 = elem.getChildNodes();
					hostConnection.setHostPort(Integer.parseInt(((Node) nList1.item(0)).getNodeValue().trim()));
					
					nList = firstSPRAMElement.getElementsByTagName("CONNECTION_TIMEOUT");
					elem = (Element) nList.item(0);
					nList1 = elem.getChildNodes();
					hostConnection.setTimeOut(Integer.parseInt(((Node) nList1.item(0)).getNodeValue().trim()));

					hostConnectons.add(hostConnection);
				}
			}
			firstChild = firstChild.getNextSibling();
		} while (firstChild != null);
		return hostConnectons;
	}

}
