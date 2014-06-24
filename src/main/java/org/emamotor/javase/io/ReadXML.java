package org.emamotor.javase.io;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.FileReader;

public class ReadXML {
  private static String targetProperty;

  public static void main(String[] args) {

    XMLInputFactory factory = XMLInputFactory.newInstance();
    try {
      XMLStreamReader streamReader = factory
        .createXMLStreamReader(new FileReader("test.xml"));

      while (streamReader.hasNext()) {
        switch (streamReader.getEventType()) {
          case XMLStreamConstants.START_ELEMENT:

            String localName = streamReader.getLocalName();

            if ("Event".equals(localName)) {
            } else {
              String attrLocalName = null;
              String attrValue = null;
              if (streamReader.getAttributeCount() != 0) {
              /*
							attrLocalName = streamReader.getAttributeLocalName(0);
							attrValue = streamReader.getAttributeValue(0);
							*/
              }

              if ("Provider".equals(localName)) {
              } else if ("TimeCreated".equals(localName) && "SystemTime".equals(attrLocalName)) {
              }
              if ("TimeGenerated".equals(localName) && "SystemTime".equals(attrLocalName)) {
              } else {
                targetProperty = localName;
              }
            }

            break;
          case XMLStreamConstants.SPACE:
          case XMLStreamConstants.CHARACTERS:

            if (targetProperty != null) {
              if ("Error".equals(targetProperty)) {
              } else if ("MaxEventsWarn".equals(targetProperty)) {
              } else if ("BufferOverRunWarn".equals(targetProperty)) {
              } else {
                try {
                  if ("EventID".equals(targetProperty)) {
                  } else if ("Level".equals(targetProperty)) {
                  } else if ("EntryType".equals(targetProperty)) {
                  } else if ("Task".equals(targetProperty)) {
                  } else if ("Keywords".equals(targetProperty)) {
                  } else if ("EventRecordId".equals(targetProperty)) {
                  } else if ("Channel".equals(targetProperty)) {
                  } else if ("Computer".equals(targetProperty)) {
                  } else if ("Message".equals(targetProperty)) {
                    String message = new String(streamReader.getTextCharacters(), streamReader.getTextStart(), streamReader.getTextLength());
                    System.out.println("streamReader.getTextCharacters(): " + streamReader.getTextCharacters());
                    System.out.println("streamReader.getTextStart(): " + streamReader.getTextStart());
                    System.out.println("streamReader.getTextLength(): " + streamReader.getTextLength());
                    System.out.println("Messageの値 = " + message);
                  } else if ("Data".equals(targetProperty)) {
                  } else {
                  }
                } catch (NumberFormatException e) {
                }
              }
              targetProperty = null;
            }
            break;
        }
        streamReader.next();

      }

    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
