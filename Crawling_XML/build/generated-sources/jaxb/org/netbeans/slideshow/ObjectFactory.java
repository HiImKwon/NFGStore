//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.07.05 at 10:19:21 AM ICT 
//


package org.netbeans.slideshow;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.netbeans.slideshow package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Slides_QNAME = new QName("http://netbeans.org/slideShow", "slides");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.netbeans.slideshow
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Slides }
     * 
     */
    public Slides createSlides() {
        return new Slides();
    }

    /**
     * Create an instance of {@link Slides.Slide }
     * 
     */
    public Slides.Slide createSlidesSlide() {
        return new Slides.Slide();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Slides }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://netbeans.org/slideShow", name = "slides")
    public JAXBElement<Slides> createSlides(Slides value) {
        return new JAXBElement<Slides>(_Slides_QNAME, Slides.class, null, value);
    }

}
