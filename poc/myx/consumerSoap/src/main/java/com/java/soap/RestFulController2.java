package com.java.soap;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/callSoapService")
public class RestFulController2 {

	// POST method
	@PostMapping(value = "/temp")
	public Class<?> processRequest(@Valid @RequestBody TempconvertRequest req)
			throws MalformedURLException, IOException {

		try {
			SOAPConnectionFactory connectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = connectionFactory.createConnection();
			URL urlWsdl = new URL("https://www.w3schools.com/xml/tempconvert.asmx?wsdl");
			String mesg = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
					+ "<soap:Body>" 
					+ "<CelsiusToFahrenheit xmlns=\"https://www.w3schools.com/xml/\">" 
					+ "<Celsius>" + req.getCelsius() + "</Celsius>" 
					+ "</CelsiusToFahrenheit>" 
					+ "</soap:Body>" 
					+ "</soap:Envelope>";

			InputStream inputStream = new ByteArrayInputStream(mesg.getBytes());
			SOAPMessage soapMessage = MessageFactory.newInstance().createMessage(null, inputStream);
			
			soapMessage = soapConnection.call(soapMessage, urlWsdl);

			Class<?> response = convertXMLToObject(TempconvertResponse.class, soapMessage);
			System.out.println(convertObjectToXML(TempconvertResponse.class, response));

			return response;
		} catch (IOException | UnsupportedOperationException | SOAPException exception) {
			System.out.println(exception.getMessage());
			return null;
		} catch (JAXBException exception) {
			Logger.getLogger(RestFulController2.class.getName()).log(Level.SEVERE, null, exception);
			return null;
		}
	}

	private String convertObjectToXML(Class<?> class1, Object object) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(class1);
		StringWriter sw = new StringWriter();
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.marshal(object, sw);
		String xmlString = sw.toString();
		
		return xmlString;
	}
	
	private Class<?> convertXMLToObject(Class<?> class1, SOAPMessage soapMessage) throws JAXBException, SOAPException {
		JAXBContext jaxbContext = JAXBContext.newInstance(class1);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		Class<?> result = (Class<?>) unmarshaller.unmarshal(soapMessage.getSOAPBody().extractContentAsDocument());
		
		return result;
	}
}
