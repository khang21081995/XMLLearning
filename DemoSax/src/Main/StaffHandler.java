package Main;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by phamquangkhang on 5/25/17.
 */
public class StaffHandler extends DefaultHandler {
    boolean id = false;
    boolean fname = false;
    boolean lname = false;
    boolean dob = false;
    boolean salary = false;
    boolean email = false;
    boolean address = false;
    boolean city = false;
    boolean department = false;
    boolean company = false;
    String currentElementName;
    Staff staff = new Staff();
    double totalMoney = 0;
    double totalMoneyApple = 0;
    Map<String, Double> companySalary = new TreeMap<String, Double>();

    @Override
    public void startDocument() throws SAXException {
//        System.out.println("start document is fired");
    }

    @Override
    public void endDocument() throws SAXException {
//        System.out.println("End Document");
        System.out.println("3.1");
        System.out.println("Total salary: " + totalMoney);
        System.out.println("\n3.2");
        System.out.println("Total salary of Apple: " + companySalary.get("Apple"));
        System.out.println("\n3.3");
        for (Object entry : companySalary.entrySet()) {
            System.out.println(entry.toString().replace("="," : "));
        }
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        currentElementName = localName + qName;

//        System.out.println("Start Element :" + qName);

        if (currentElementName.equalsIgnoreCase("id")) {
            id = true;
        }
        if (currentElementName.equalsIgnoreCase("fname")) {
            fname = true;
        }
        if (currentElementName.equalsIgnoreCase("lname")) {
            lname = true;
        }
        if (currentElementName.equalsIgnoreCase("dob")) {
            dob = true;
        }
        if (currentElementName.equalsIgnoreCase("salary")) {
            salary = true;
        }
        if (currentElementName.equalsIgnoreCase("email")) {
            email = true;
        }
        if (currentElementName.equalsIgnoreCase("address")) {
            address = true;
        }
        if (currentElementName.equalsIgnoreCase("city")) {
            city = true;
        }
        if (currentElementName.equalsIgnoreCase("department")) {
            department = true;
        }
        if (currentElementName.equalsIgnoreCase("company")) {
            company = true;
        }
    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        currentElementName = localName + qName;
        if (currentElementName.equalsIgnoreCase("staff")) {
            double currentSalary = staff.getSalary();
            totalMoney += currentSalary;
            try {
                companySalary.put(staff.getCompany(), currentSalary + companySalary.get(staff.getCompany()).doubleValue());
            } catch (Exception e) {
//                System.err.println(e.getMessage());
                companySalary.put(staff.getCompany(), currentSalary);
            }

            staff = new Staff();

        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String currentChars = new String(ch, start, length);
//        currentChars = currentChars.replaceAll("\r\n","");
//        System.out.println(currentChars);
        try {
            if (id) {
                staff.setId(currentChars);
                id = false;
            }
            if (fname) {
                staff.setFname(currentChars);
                fname = false;
            }
            if (lname) {
                staff.setLname(currentChars);
                lname = false;
            }
            if (dob) {
                staff.setDob(currentChars);
                dob = false;
            }
            if (salary) {
                staff.setSalary(Double.parseDouble(currentChars));
                salary = false;
            }
            if (email) {
                staff.setEmail(currentChars);
                email = false;
            }
            if (address) {
                staff.setAddress(currentChars);
                address = false;
            }
            if (city) {
                staff.setCity(currentChars);
                city = false;
            }
            if (department) {
                staff.setDepartment(currentChars);
                department = false;
            }
            if (company) {
                staff.setCompany(currentChars);
                company = false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            staff.setSalary(0);
        } catch (Error err) {
            System.err.println(err.getMessage());
        }
    }


}
