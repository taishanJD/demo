package com.example.common;

import com.google.common.collect.Lists;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class Test1 {
    public static void main(String[] args) {

        String s1 = new String("aaa");
        String s2 = new String("aaa");
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));

        int a = 5;
        System.out.println("before == "+a);
        changeint(a);
        System.out.println("changed == "+a);

        String s = "ac";
        System.out.println("before == "+s);
        changestring(s);
        System.out.println("changed == "+s);

        System.out.println("before == "+s1);
        changestring(s1);
        System.out.println("changed == "+s1);

        String[] strs1 = {"aa","bb","cc","dd","ee","aa"};
        String[] strs2 = {"aab","bb","cc","dd","ee","ee"};

        System.out.println(getSame(strs1,strs2));
        System.out.println(getSame_lambda(strs1,strs2));

        Test1 test1 = new Test1();
        test1.sort();

    }

    public static void changeint(int a){
        a = 10;
    }


    public static void changestring(String a){
        a = "qqq";
    }



    //2。集合
    // ArrayList基于数组来实现集合的功能，其内部维护了一个可变长的对象数组；
    // ArrayList使用数组拷贝来实现指定位置的插入和删除
    // LinkedList基于链表来实现集合的功能，
    // ArrayList的随机访问更高，基于数组实现的ArrayList可直接定位到目标对象，而LinkedList需要从头Node或尾Node开始向后/向前遍历若干次才能定位到目标对象
    // LinkedList在头/尾节点执行插入/删除操作的效率比ArrayList要高
    // 由于ArrayList每次扩容的容量是当前的1.5倍，所以LinkedList所占的内存空间要更小一些
    // 二者的遍历效率接近，但需要注意，遍历LinkedList时应用iterator方式，不要用get(int)方式，否则效率会很低
    // HashMap 将key和value封装至一个叫做Entry的对象中，
    // HashMap将Entry对象存储在一个数组中，并通过哈希表来实现对Entry的快速访问：
    // 由每个Entry中的key的哈希值决定该Entry在数组中的位置。
    // 如果两个不同的key计算出的index是一样的，就会发生两个不同的key都对应到数组中同一个位置的情况，也就是所谓的哈希冲突。
    // HashMap处理哈 希冲突的方法是拉链法，也就是说数组中每个位置保存的实际是一个Entry链表，链表中每个Entry都拥有指向链表中后一个Entry的引用。
    // 在发生哈希冲突时，将冲突的Entry追加至链表的头部。当HashMap在寻址时发现某个key对应的数组index上有多个Entry，便会遍历该位置上的 Entry链表，直到找到目标的Entry。
    // Hashtable ConcurrentHashMap
    // Hashtable 在进行读写操作时会锁住整个Entry数组，这就导致数据越多性能越差。
    // 而ConcurrentHashMap使用分离锁的思路解决并发性能，其将 Entry数组拆分至16个Segment中，
    // 以哈希算法决定Entry应该存储在哪个Segment。
    // 这样就可以实现在写操作时只对一个Segment 加锁，大幅提升了并发写的性能。


    //4。新建。就绪。运行。阻塞。死亡。
    //5。基本类型作为参数传递时，是传递值的拷贝。在Java中对象作为参数传递时，是把对象在内存中的地址拷贝了一份传给了参数。

    //7。查找交集
    //求交集1
    public static List<String> getSame(String[] a,String[] b){
        Map<String,Boolean> map = new HashMap<>();
        List<String> result = new ArrayList<>();

        for (String s: a) {
            if (!map.containsKey(s)){
                map.put(s,false);
            }
        }
        for (String s: b){
            if (map.containsKey(s)){
                map.put(s,true);
            }
        }
        for (Map.Entry<String,Boolean> entry:map.entrySet()) {
            if (entry.getValue().equals(true)){
                result.add(entry.getKey());
            }
        }
        return result;
    }

    //求交集2
    public static List<String> getSame_lambda(String[] a,String[] b){
        List<String> aa = Arrays.asList(a);
        List<String> bb = Arrays.asList(b);
        List<String> result = aa.stream().filter(s -> bb.contains(s)).collect(Collectors.toList());
        return result;
    }

    //8。对象排序
    private void sort(){
        Person[] people=new Person[5];
        people[0]=(new Person("小明", 20));
        people[1]=(new Person("小张", 30));
        people[2]=(new Person("小刘", 18));
        people[3]=(new Person("小林", 17));
        people[4]=(new Person("小刘", 35));

        Arrays.sort(people,new MyCompare());//传进来一个比较器对象，使数组按比较器定义的规则来排序


        List<Person> list = Arrays.asList(people);
        list.sort(Comparator.comparing(Person::getName));
        list.forEach(System.out::println);
    }
    class Person {

        String name;
        int age;

        public Person() {
        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String toString(){
            return "姓名："+name+",年龄："+age;
        }

    }



    class MyCompare implements Comparator<Person>{//定义比较器
        /*

        重写比较方法compare(o1,o2)：

        方法传进来两个对象，用两个对象的某属性进行对比，返回一个int。

        int>0，则o1排在o2后面；

        int<0,则o1排在o2前面；

        int=0，则维持原相对位置，即原来存放时o1、o2的前后地址顺序。

        */
        public int compare(Person o1, Person o2) {
            int result;
            if(o1.age>o2.age){
                result=1;
            }
            else if(o1.age<o2.age){
                result=-1;
            }
            else{
                result=0;
            }
            return result;
        }
    }

    //9。String被final修饰，不能被继承，实现细节不允许改变。

    //10。
    //假如这个类没有重写equals方法，如果两个对象值相同(x.equals(y) == true)，那么那么它们的hashCode值一定要相同；
    //但是如果重写equals方法，没有重写hashCode的方法，就会出现不相等的情况。
    //1) 对象相等则hashCode一定相等；
    //2) hashCode相等对象未必相等
//            如果想完整的使用HashSet类 那么最少要重写equals()和hashCode()方法。
//            1.重写hashCode() 用于获得元素的存储位置。
//            2.重写equals() 用于在两个元素的位置相同的时候 比较两个元素是否相等。

    //11。一个java文件中可以定义多个类，但是最多只有一个类被public修饰，并且这个类的类名与文件名必须相同，

    //12。在static方法内部无法直接调用非static方法（可以通过先实例化对象，再用该对象调用非static方法）
    // 一个方法用static修饰，便是静态方法或类方法。静态方法不属于特定对象。
    //由于static方法在装载class时首先完成，比构造方法早，此时非static属性和方法还没有完成初始化。
    //所以，在static方法内部无法直接调用非static方法（可以通过先实例化对象，再用该对象调用非static方法），但非static方法中可以调用static方法，通过类.方法名()的方式。

    //13。对象大小 不可以。可以通过开源工具，SizeOf。


    //14。15。
//    匿名内部类在实现时必须借助一个类或一个接口，若从这个层次上讲它是可以继承其他类也可以实现接口的，
//    但若是通过extends 或implements 关键字那是不可能的.

    //16。sql优化

    // 1)无法使用索引的情况
    //(1).以%开头的like查询
    //(2).数据类型出现隐式转换的时候也不会使用索引，特别是当列类型是字符串，那么一定记得在where条件中把字符串常量值用引号引起来，否则即便这个列上有索引，MySQL也不会用到，因为MySQL默认把输入的常量值进行转换以后才进行检索
    //(3).复合索引的情况下，如果查询条件不包含索引列的最左边部分，即不满足最左前缀原则，则不会使用索引
    //(4).如果mysql估计使用索引扫描比全表扫描更慢，则不使用索引。(扫描数据超过30%，都会走全表)
    //(5).用or分割开的条件，如果 or前的条件中的列有索引，而后面的列中没有索引，那么涉及的索引都不会被用到
    //(6).字段使用函数，将无法使用索引
    //(7).Join 语句中 Join 条件字段类型不一致的时候 MySQL 无法使用索引

    // 2)优化子查询
    // 连接（JOIN）取而代之

    //dubbo由于是二进制的传输，占用带宽会更少
    //springCloud是http协议传输，带宽会比较多，同时使用http协议一般会使用JSON报文，消耗会更大

    //Redis一共支持五种数据类：string（字符串），hash（哈希），list（列表），set（集合）和zset（sorted set有序集合）。
}
