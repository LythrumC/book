package Test;

import JavaBean.Person;
import JavaBean.PersonListType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 陈宽
 * @create 2021-05-12 16:44
 * @description
 */
public class JsonTest {


    /**
     * JSON对象转换成javabean对象
     */
    @Test
    public void test1(){
        Person person = new Person(1,"Spike");
        //创建按gson对象实例
        Gson gson = new Gson();
        //toJson方法可以把java对象转换成json
        String person_String = gson.toJson(person);
//        System.out.println(person_String);

        //fromJson把json字符串转换成javaBean对象
        //第一个参数时json字符串，第二个是要转换成的对象
//                                                       <?class>是转换成一个javaBean对象用的
        Person person1 = gson.fromJson(person_String, Person.class);
        System.out.println(person1.toString());
    }

    /**
     * List集合类型和json类型的转换
     */
    @Test
    public void test2(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1,"spike"));
        personList.add(new Person(2,"faye"));

        Gson gson = new Gson();
        //List集合对象转换成json
        String personListString = gson.toJson(personList);
        System.out.println(personListString);
        //json转换成List,Map等集合对象(必须创建一个接受对象，并且继承TypeToken,泛型接受自己需要转成的List类型即可)
//                                                                   Type是转换成多个对象用的(List,Map),也可以用匿名内部类
        List<Person> list = gson.fromJson(personListString,new PersonListType().getType());
        System.out.println(list);
        Person person = list.get(0);
        System.out.println(person);
    }

    /**
     * Map集合类型和json类型的转换
     */
    @Test
    public void test3(){
        Map<Integer,Person> personMap = new HashMap<>();
        personMap.put(1,new Person(1,"spike"));
        personMap.put(2,new Person(2,"faye"));

        Gson gson = new Gson();
        //Map集合对象转换成json
        String personMapString = gson.toJson(personMap);
        System.out.println(personMapString);

        //json转换成List,Map等集合对象(必须创建一个接受对象，并且继承TypeToken,泛型接受自己需要转成的List类型即可)
//                                                                  Type是转换成多个对象用的(List,Map),也可以用匿名内部类
//        Map<Integer,Person> map = gson.fromJson(personMapString,new PersonMapType().getType());
        //匿名内部类更加节省代码
        Map<Integer,Person> map = gson.fromJson(personMapString,new TypeToken<HashMap<Integer,Person>>(){}.getType());
        System.out.println(map);
        System.out.println(map.get(1));


    }

}
