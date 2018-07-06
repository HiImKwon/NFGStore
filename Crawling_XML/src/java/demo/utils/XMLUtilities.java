/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.utils;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author Bui Quan
 */
public class XMLUtilities implements Serializable {

    public String crawler(StreamSource htmlPath, String xslPath)
            throws FileNotFoundException, IOException, TransformerConfigurationException, TransformerException {
        //init files
        StreamSource xslCate = new StreamSource(xslPath);
        //init transformer api
        TransformerFactory factory = TransformerFactory.newInstance();
        StringWriter stringWriter = new StringWriter();
        StreamResult result = new StreamResult(stringWriter);
//        CrawlingPureHTML cp = new CrawlingPureHTML();
//        factory.setURIResolver(cp);
        Transformer trans = factory.newTransformer(xslCate);
        //transform xml config by input xsl
        trans.transform(htmlPath, result);
        return stringWriter.toString();
    }

    public Object JAXBUnmarshalling(InputStream inputXML, Class ctxClass) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(ctxClass);
        Unmarshaller un = jc.createUnmarshaller();
        Object result = (Object) un.unmarshal(inputXML);
        return result;
    }

//    public void XJCGenerateJavaObj(String xsdSchema) {
//        try {
//            String output = "src/";
//            SchemaCompiler sc = XJC.createSchemaCompiler();
//            sc.setErrorListener(new ErrorListener() {
//                @Override
//                public void error(SAXParseException saxpe) {
//                    System.out.println("Error" + saxpe.getMessage());
//                }
//
//                @Override
//                public void fatalError(SAXParseException saxpe) {
//                    System.out.println("Error" + saxpe.getMessage());
//
//                }
//
//                @Override
//                public void warning(SAXParseException saxpe) {
//                    System.out.println("Error" + saxpe.getMessage());
//
//                }
//
//                @Override
//                public void info(SAXParseException saxpe) {
//                    System.out.println("Error" + saxpe.getMessage());
//
//                }
//            });
//            sc.forcePackageName("demo.jaxb");
//            File schema = new File("web/" + xsdSchema);
//            InputSource is = new InputSource(schema.toURI().toString());
//            sc.parseSchema(is);
//            S2JJAXBModel model = sc.bind();
//            JCodeModel code = model.generateCode(null, null);
//            code.build(new File(output));
//            System.out.println("Finished");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
