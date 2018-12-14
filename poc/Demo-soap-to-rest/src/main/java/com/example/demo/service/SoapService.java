package com.example.demo.service;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

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

import org.springframework.stereotype.Service;

@Service
public class SoapService {

	public TempResponse convertCelsiusToFarenheit(TempRequest request) {
		TempResponse tempResponse = new TempResponse();

		try {
			SOAPConnection soapConnection = SOAPConnectionFactory.newInstance().createConnection();
			SOAPMessage soapRequest = MessageFactory.newInstance().createMessage();
			URL url = new URL("https://www.w3schools.com/xml/tempconvert.asmx");
			SOAPPart soapPart = soapRequest.getSOAPPart();

			// SOAP Envelope
			SOAPEnvelope envelope = soapPart.getEnvelope();

			// SOAP Body
			SOAPBody soapBody = envelope.getBody();
			SOAPElement rootBodyElement = soapBody.addChildElement("CelsiusToFahrenheit", "", "https://www.w3schools.com/xml/");
			SOAPElement bodyElement = rootBodyElement.addChildElement("Celsius");
			bodyElement.addTextNode(request.getCelsius());

			// SOAP Action
			MimeHeaders headers = soapRequest.getMimeHeaders();
			headers.addHeader("SOAPAction", "https://www.w3schools.com/xml/CelsiusToFahrenheit");

			SOAPMessage soapResponse = soapConnection.call(soapRequest, url);

			JAXBContext jaxbContext = JAXBContext.newInstance(TempResponse.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			tempResponse = (TempResponse) unmarshaller.unmarshal(soapResponse.getSOAPBody().extractContentAsDocument());

			soapConnection.close();
		} catch (Exception exception) {
			Logger.getLogger(SoapService.class.getName()).log(Level.SEVERE, null, exception);
		}

		return tempResponse;
	}
}
