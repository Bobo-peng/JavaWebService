package com.service;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.service.entity.Student;

public class StudentService {
	
	public List<Student> GetStudentInfo(String Name)
	{
		List<Student> stulist = new ArrayList<Student>(); 
		   try {  
				    String url ="http://localhost:9999/WebService.asmx";
	       	        String namespace = "http://tempuri.org/";
			        Service service = new Service();  
			        Call call = (Call) service.createCall(); 
			        QName qname = new QName(namespace, "GetdbData"); //���������ռ����Ҫ���õķ�����
			        call.setOperationName(qname);
			        call.setTargetEndpointAddress(url);  
			        call.setSOAPActionURI(namespace + "GetdbData");
			        call.addParameter(new QName(namespace,"Name"), org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);// �ӿڵĲ���  
			        call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);//���÷�������
			        String result = (String) call.invoke(new Object[] {Name});  
			        System.out.println("���: \n" + result);  
				    stulist = PreaseXml(result);
                } 
	          catch (Exception e) {  
	            System.err.println(e.toString());  
	    	}
		return stulist;

        }
	//����Xml
	private List<Student> PreaseXml(String resxml)
	{
		List<Student> tlist = new ArrayList<Student>(); 
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();  
            StringReader sr = new StringReader(resxml);
            InputSource is = new InputSource(sr);
            Document document = builder.parse(is);
            //��ȡ����Root���нڵ�ļ���
            NodeList rootList = document.getElementsByTagName("Root");
            //ͨ��NodeList��getLength����
            int rootCnt = rootList.getLength();
            for(int i = 0; i < rootCnt; i++){
                Node root = rootList.item(i);
                NodeList childNodes = root.getChildNodes();
                //����childNodes��ȡÿ���ڵ�Ľڵ����ͽڵ�ֵ
                for(int j = 1; j < childNodes.getLength();){
                	Student mlist=new Student(); 
                    if(childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
                        //��ȡ�ڵ�Ľڵ���
                        System.out.print("��"+ (j+1) + "�ڵ㣺"+childNodes.item(j).getNodeName()+"  ");
                        //��ȡ���ͽڵ�Ľڵ�ֵ
                        System.out.println("�ڵ�ֵ��" + childNodes.item(j).getTextContent());
                         mlist.setName(childNodes.item(j).getChildNodes().item(1).getTextContent());
                         mlist.setChinese(childNodes.item(j).getChildNodes().item(3).getTextContent());
                         mlist.setMath(childNodes.item(j).getChildNodes().item(5).getTextContent());
                         mlist.setEnglish(childNodes.item(j).getChildNodes().item(7).getTextContent());
                        j +=2;
                    } 
                    tlist.add(mlist); 
                } 
                
            }
        } catch (ParserConfigurationException e) {          
            e.printStackTrace();
        } catch (SAXException e) {   
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	  return tlist;
	}

}
