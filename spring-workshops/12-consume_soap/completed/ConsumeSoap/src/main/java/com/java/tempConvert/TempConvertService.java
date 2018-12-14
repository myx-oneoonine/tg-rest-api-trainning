package com.java.tempConvert;

import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/service")
public class TempConvertService {

	@PostMapping(value = "/convertCelsiusToFarenheit")
	public TempconvertResponse convertCelsiusToFarenheit(@RequestBody TempconvertRequest request) {
		TempconvertResponse tempconvertResponse = new TempconvertResponse();
		try {
			SOAPConnectionFactory connectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = connectionFactory.createConnection();
			URL urlWsdl = new URL("https://www.w3schools.com/xml/tempconvert.asmx?wsdl");
			MessageFactory messageFactory = MessageFactory.newInstance();
			SOAPMessage soapMessage = messageFactory.createMessage();
			SOAPPart soapPart = soapMessage.getSOAPPart();

			// SOAP Envelope
			SOAPEnvelope envelope = soapPart.getEnvelope();
			envelope.addNamespaceDeclaration("xsd", "http://www.w3.org/2001/XMLSchema");
			envelope.addNamespaceDeclaration("xsi", "http://www.w3.org/2001/XMLSchema-instance");
			envelope.addNamespaceDeclaration("soap", "http://schemas.xmlsoap.org/soap/envelope/");

			// SOAP Body
			SOAPBody soapBody = envelope.getBody();
			SOAPElement soapBodyElem = soapBody.addChildElement("CelsiusToFahrenheit", "",
					"https://www.w3schools.com/xml/");
			SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("Celsius");
			soapBodyElem1.addTextNode(request.getCelsius());

			// SOAP Action
			MimeHeaders headers = soapMessage.getMimeHeaders();
			headers.addHeader("SOAPAction", "https://www.w3schools.com/xml/CelsiusToFahrenheit");

			soapMessage.saveChanges();

			SOAPMessage soapResponse = soapConnection.call(soapMessage, urlWsdl);

			JAXBContext jaxbContext = JAXBContext.newInstance(TempconvertResponse.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			tempconvertResponse = (TempconvertResponse) unmarshaller
					.unmarshal(soapResponse.getSOAPBody().extractContentAsDocument());
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		return tempconvertResponse;
	}
}
