//package classdemo;
//
//import org.dom4j.Document;
//import org.dom4j.Element;
//import org.dom4j.io.SAXReader;
//
//import java.util.List;
//
///**
// * @author 陈宽
// * @create 2021-02-02 13:59
// * @describe books类测试使用xml文件
// */
//public class books {
//    public static void main(String[] args) throws Exception {
//        text2();
//    }
//
//
//    //测试使用text2()
//    public static void text2() throws Exception{
//        //1.读取xml文件
//        SAXReader reader = new SAXReader();
//        //2.在junit测试中，相对路径是从模块名开始算的
//        Document document = reader.read("_xml/src/xml/xmldemo.xml");
//        //3.通过document对象获取根元素
//        Element rootelement = document.getRootElement();
////        System.out.println(rootelement);
//        //4.通过根元素获取book标签对象
//        List<Element> books = rootelement.elements("book");
//        //5.遍历，处理每个book标签转化为Book类对象
//        for (Element book: books) {
//            //asXML()把标签对象。转化为标签字符串
////            System.out.println(book.asXML());
//            //方法一:获取指定的标签元素
////            Element nameElement = book.element("name");
////            Element priceElement = book.element("price");
////            Element authorElement = book.element("author");
//            //获取指定标签元素的文本内容
////            String bookName = nameElement.getText();
////            System.out.println(bookName);
//
//            //方法二:直接获取指定标签元素文本内容
//            String bookName = book.elementText("name");
////            System.out.println(bookName);
//            String bookPrice = book.elementText("price");
//            String bookAuthor = book.elementText("author");
//            //获取标签上指定属性值
//            String bookSN = book.attributeValue("sn");
//            System.out.println(new book(bookSN,bookName,bookAuthor,Double.parseDouble(bookPrice)).toString());
//
//        }
//
//    }
//
//}
