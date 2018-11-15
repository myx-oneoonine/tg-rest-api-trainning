package com.example;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/callSoapService",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE,
        method = {RequestMethod.GET, RequestMethod.POST})
public class RestFulController2 {

    //POST method
    @RequestMapping(value = "/temp", method = RequestMethod.POST)
    public TempconvertResponse processRequest(@RequestBody TempconvertRequest req) throws MalformedURLException, IOException {
        TempconvertResponse response = new TempconvertResponse();
        try {
            System.out.println("in method");
            SOAPConnectionFactory connectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = connectionFactory.createConnection();
            URL url = new URL("https://www.w3schools.com/xml/tempconvert.asmx?wsdl");
            String mesg = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                    + "<soap:Body>"
                    + "<CelsiusToFahrenheit xmlns=\"https://www.w3schools.com/xml/\">"
                    + "<Celsius>50</Celsius>"
                    + "</CelsiusToFahrenheit>"
                    + "</soap:Body>"
                    + "</soap:Envelope>";

            InputStream is = new ByteArrayInputStream(mesg.getBytes());

            SOAPMessage request = MessageFactory.newInstance().createMessage(null, is);
            SOAPMessage soapResponse = soapConnection.call(request, url);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            soapResponse.writeTo(baos);

            String resp = baos.toString();
            System.out.println(resp);
            StringReader reader = new StringReader(resp);
            JAXBContext jaxbContext = JAXBContext.newInstance(TempconvertResponse.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            response = (TempconvertResponse) unmarshaller.unmarshal(reader);
            return response;
        } catch (IOException | UnsupportedOperationException | SOAPException e) {
            System.out.println(e.getMessage());
        } catch (JAXBException ex) {
            Logger.getLogger(RestFulController2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
}
