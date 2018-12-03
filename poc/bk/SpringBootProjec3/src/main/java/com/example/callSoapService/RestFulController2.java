package com.example.callSoapService;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/callSoapService")
public class RestFulController2 {

    //POST method
    @PostMapping(value = "/temp")
    public ResponseEntity<?> processRequest(@Valid @RequestBody TempconvertRequest req) throws MalformedURLException, IOException {
        TempconvertResponse tempconvertResponse = new TempconvertResponse();
        HttpStatus httpStatus = null;
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
            JAXBContext jaxbContext = JAXBContext.newInstance(TempconvertResponse.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            tempconvertResponse = (TempconvertResponse) unmarshaller.unmarshal(soapResponse.getSOAPBody().extractContentAsDocument());
            httpStatus = HttpStatus.OK;
        } catch (IOException | UnsupportedOperationException | SOAPException exception) {
            System.out.println(exception.getMessage());
            httpStatus = HttpStatus.EXPECTATION_FAILED;
            tempconvertResponse.setErrorMsg(exception.getMessage());
        } catch (JAXBException exception) {
            Logger.getLogger(RestFulController2.class.getName()).log(Level.SEVERE, null, exception);
            httpStatus = HttpStatus.EXPECTATION_FAILED;
            tempconvertResponse.setErrorMsg(exception.getMessage());
        }

        return ResponseEntity.status(httpStatus).body(tempconvertResponse);
    }
}
