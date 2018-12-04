package com.java.soap;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
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

    //POST method
    @PostMapping(value = "/temp")
    public TempconvertResponse processRequest(@Valid @RequestBody TempconvertRequest req) throws MalformedURLException, IOException {
        
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
            SOAPMessage soapRequest = MessageFactory.newInstance().createMessage(null, inputStream);
            SOAPMessage soapResponse = soapConnection.call(soapRequest, urlWsdl);
//            soapResponse.writeTo(System.out);
            JAXBContext jaxbContext = JAXBContext.newInstance(TempconvertResponse.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            
            TempconvertResponse tempconvertResponse = (TempconvertResponse) unmarshaller.unmarshal(soapResponse.getSOAPBody().extractContentAsDocument());

            return tempconvertResponse;
        } catch (IOException | UnsupportedOperationException | SOAPException exception) {
            System.out.println(exception.getMessage());
            return null;
        } catch (JAXBException exception) {
            Logger.getLogger(RestFulController2.class.getName()).log(Level.SEVERE, null, exception);
            return null;
        }
    }
}
