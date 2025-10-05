package com.mapb.javautilities.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author MAPB
 */
public class XML {

    private void UtilidadesXML() {
        // No se permiten instanciar objetos de esta clase
    }

    /**
     *
     * Método al que le pasamos por parámetro la ruta del xml y este nos lo
     * transforma a dom, para su posterior obtecciond e los datos.
     *
     * @param ruta del xml
     * @return dom
     *
     */
    public Document xmlToDom(String rutaXml) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            System.out.println("ERROR: " + ex.toString());
        }
        Document dom = null;
        File fichero = new File(rutaXml);
        try {
            if (!fichero.exists()) {
                System.out.println("ERROR: No existe ningun archivo en el sistema.");
            } else {
                dom = builder.parse(new File(rutaXml));
                dom.getDocumentElement().normalize();
            }
        } catch (SAXException | IOException ex) {
            System.out.println("ERROR: " + ex.toString());
        }
        return dom;
    }

    /**
     *
     * Método al que le pasamos por parámetro un dom y una ruta y este nos
     * transformará el dom a xml a la ruta indicada.
     *
     * @param dom
     * @param ruta del xml
     */
    public void domToXml(Document dom, String rutaXml) {
        File directorio = new File("datos/");
        File fichero = new File(rutaXml);
        // Comprobamos si existe o no el directorio y si noe xiste lo creamos
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        // Para el archivo hacemos lo mismo
        if (!fichero.exists()) {
            try {
                fichero.createNewFile();
            } catch (IOException ioex) {
                System.out.println("ERROR: " + ioex.toString());
            }
        }
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource(dom);
            StreamResult result = new StreamResult(new File(rutaXml));
            // Finalmente transformamos el archivo mediante .transform() pasandole el dom y
            // el objeto tipo streamresult
            // con el archivo en cuestion que exportaremos.
            transformer.transform(source, result);
            System.out.println("Archivo guardado correctamente.");
        } catch (TransformerException | DOMException ex) {
            System.out.println("ERROR: " + ex.toString());
        }
    }

    /**
     *
     * Método al que le pasamos la raiz de los elementos y este nos devolvera un
     * dom vacio con la raiz ya establecida.
     *
     * @param raiz return dom
     */
    public Document crearDomVacio(String raiz) {
        try {
            DocumentBuilderFactory dbFactoria = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactoria.newDocumentBuilder();
            Document dom = dBuilder.newDocument();
            Element elementoRaiz = dom.createElement(raiz);
            dom.appendChild(elementoRaiz);
            return dom;
        } catch (ParserConfigurationException pce) {
            System.out.println("ERROR: " + pce.toString());
        }
        return null;
    }
}
