package Main;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.security.PublicKey;

/**
 * Created by phamquangkhang on 5/25/17.
 */
public class Main {

    public static String getOsPath() {
        String windowsPath = "\\";
        String nonWindowsPath = "/";
        return System.getProperty("os.name").startsWith("Windows") ? windowsPath : nonWindowsPath;
    }

    public static void main(String[] args) {
        //Yêu cầu: sử dụng file staff_data.xml
        //Yc3.1. để tính tổng lương của tất cả các công ty
        //Yc3.2. để tính tổng lương của nhân viên của công ty Apple
        //Yc3.3. để tính tổng lương của nhân viên của từng công ty
        //		Apple: 12M
        //		Microsoft: 5M
        //		Macromedia: 2M
        String fileName = "src" + getOsPath() + "staff_data.xml";
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(new File(fileName), new StaffHandler());
        } catch (Exception e) {

        }

    }
}
