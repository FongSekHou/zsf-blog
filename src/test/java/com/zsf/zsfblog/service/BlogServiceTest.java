package com.zsf.zsfblog.service;

import com.zsf.zsfblog.po.Blog;
import com.zsf.zsfblog.po.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-config.xml")
public class BlogServiceTest {

    @Autowired
    private BlogService blogService;

    @Test
    public void testSaveBlog(){
        String[] contents = new String[6];
        contents[0] = "# day01【Object类、常用API】\n" +
                "\n" +
                "## 主要内容\n" +
                "\n" +
                "* Object类\n" +
                "* Date类\n" +
                "* DateFormat类\n" +
                "* Calendar类\n" +
                "* System类\n" +
                "* StringBuilder类\n" +
                "* 包装类\n" +
                "\n" +
                "## 教学目标\n" +
                "\n" +
                "- [ ] 能够说出Object类的特点\n" +
                "- [ ] 能够重写Object类的toString方法\n" +
                "- [ ]  能够重写Object类的equals方法\n" +
                "- [ ]  能够使用日期类输出当前日期\n" +
                "- [ ] 能够使用将日期格式化为字符串的方法\n" +
                "- [ ] 能够使用将字符串转换成日期的方法\n" +
                "- [ ] 能够使用System类的数组复制方法\n" +
                "- [ ] 能够使用System类获取当前毫秒时刻值\n" +
                "- [ ] 能够说出使用StringBuilder类可以解决的问题\n" +
                "- [ ] 能够使用StringBuilder进行字符串拼接操作\n" +
                "- [ ]  能够说出8种基本类型对应的包装类名称\n" +
                "- [ ]  能够说出自动装箱、自动拆箱的概念\n" +
                "- [ ]  能够将字符串转换为对应的基本类型\n" +
                "- [ ] 能够将基本类型转换为对应的字符串\n" +
                "\n" +
                "# 第一章 Object类\n" +
                "\n" +
                "## 1.1 概述\n" +
                "\n" +
                "`java.lang.Object`类是Java语言中的根类，即所有类的父类。它中描述的所有方法子类都可以使用。在对象实例化的时候，最终找的父类就是Object。\n" +
                "\n" +
                "如果一个类没有特别指定父类，\t那么默认则继承自Object类。例如：\n" +
                "\n" +
                "```java\n" +
                "public class MyClass /*extends Object*/ {\n" +
                "  \t// ...\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "根据JDK源代码及Object类的API文档，Object类当中包含的方法有11个。今天我们主要学习其中的2个：\n" +
                "\n" +
                "* `public String toString()`：返回该对象的字符串表示。\n" +
                "* `public boolean equals(Object obj)`：指示其他某个对象是否与此对象“相等”。\n" +
                "\n" +
                "## 1.2 toString方法\n" +
                "\n" +
                "### 方法摘要\n" +
                "\n" +
                "* `public String toString()`：返回该对象的字符串表示。\n" +
                "\n" +
                "toString方法返回该对象的字符串表示，其实该字符串内容就是对象的类型+@+内存地址值。\n" +
                "\n" +
                "由于toString方法返回的结果是内存地址，而在开发中，经常需要按照对象的属性得到相应的字符串表现形式，因此也需要重写它。\n" +
                "\n" +
                "### 覆盖重写\n" +
                "\n" +
                "如果不希望使用toString方法的默认行为，则可以对它进行覆盖重写。例如自定义的Person类：\n" +
                "\n" +
                "```java\n" +
                "public class Person {  \n" +
                "    private String name;\n" +
                "    private int age;\n" +
                "\n" +
                "    @Override\n" +
                "    public String toString() {\n" +
                "        return \"Person{\" + \"name='\" + name + '\\'' + \", age=\" + age + '}';\n" +
                "    }\n" +
                "\n" +
                "    // 省略构造器与Getter Setter\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "\n" +
                "\n" +
                "在IntelliJ IDEA中，可以点击`Code`菜单中的`Generate...`，也可以使用快捷键`alt+insert`，点击`toString()`选项。选择需要包含的成员变量并确定。如下图所示：\n" +
                "\n" +
                "![toString方法的自动重写](img\\toString方法的自动重写.bmp)\n" +
                "\n" +
                "> 小贴士： 在我们直接使用输出语句输出对象名的时候,其实通过该对象调用了其toString()方法。\n" +
                ">\n" +
                "\n" +
                "## 1.3 equals方法\n" +
                "\n" +
                "### 方法摘要\n" +
                "\n" +
                "* `public boolean equals(Object obj)`：指示其他某个对象是否与此对象“相等”。\n" +
                "     可以解决空指针异常的问题\n" +
                "\n" +
                "调用成员方法equals并指定参数为另一个对象，则可以判断这两个对象是否是相同的。这里的“相同”有默认和自定义两种方式。\n" +
                "\n" +
                "### 默认地址比较\n" +
                "\n" +
                "如果没有覆盖重写equals方法，那么Object类中默认进行`==`运算符的对象地址比较，只要不是同一个对象，结果必然为false。\n" +
                "\n" +
                "### 对象内容比较\n" +
                "\n" +
                "如果希望进行对象的内容比较，即所有或指定的部分成员变量相同就判定两个对象相同，则可以覆盖重写equals方法。例如：\n" +
                "\n" +
                "```java\n" +
                "import java.util.Objects;\n" +
                "\n" +
                "public class Person {\t\n" +
                "\tprivate String name;\n" +
                "\tprivate int age;\n" +
                "\t\n" +
                "    @Override\n" +
                "    public boolean equals(Object o) {\n" +
                "        // 如果对象地址一样，则认为相同\n" +
                "        if (this == o)\n" +
                "            return true;\n" +
                "        // 如果参数为空，或者类型信息不一样，则认为不同\n" +
                "        if (o == null || getClass() != o.getClass())\n" +
                "            return false;\n" +
                "        // 转换为当前类型\n" +
                "        Person person = (Person) o;\n" +
                "        // 要求基本类型相等，并且将引用类型交给java.util.Objects类的equals静态方法取用结果\n" +
                "        return age == person.age && Objects.equals(name, person.name);\n" +
                "    }\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "这段代码充分考虑了对象为空、类型一致等问题，但方法内容并不唯一。大多数IDE都可以自动生成equals方法的代码内容。在IntelliJ IDEA中，可以使用`Code`菜单中的`Generate…`选项，也可以使用快捷键`alt+insert`，并选择`equals() and hashCode()`进行自动代码生成。如下图所示：\n" +
                "\n" +
                "![](img\\equals方法1.png)\n" +
                "\n" +
                "![](img\\equals方法2.png)\n" +
                "\n" +
                "![](img\\equals方法3.png)\n" +
                "\n" +
                "> tips：Object类当中的hashCode等其他方法，今后学习。\n" +
                "\n" +
                "## 1.4 Objects类\n" +
                "\n" +
                "在刚才IDEA自动重写equals代码中，使用到了`java.util.Objects`类，那么这个类是什么呢？\n" +
                "\n" +
                "在**JDK7**添加了一个Objects工具类，它提供了一些方法来操作对象，它由一些静态的实用方法组成，这些方法是null-save（空指针安全的）或null-tolerant（容忍空指针的），用于计算对象的hashcode、返回对象的字符串表示形式、比较两个对象。\n" +
                "\n" +
                "在比较两个对象的时候，Object的equals方法容易抛出空指针异常，而Objects类中的equals方法就优化了这个问题。方法如下：\n" +
                "\n" +
                "* `public static boolean equals(Object a, Object b)`:判断两个对象是否相等。\n" +
                "\n" +
                "我们可以查看一下源码，学习一下：\n" +
                "\n" +
                "~~~java\n" +
                "public static boolean equals(Object a, Object b) {  \n" +
                "    return (a == b) || (a != null && a.equals(b));  \n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "# 第二章 日期时间类\n" +
                "\n" +
                "## 2.1 Date类\n" +
                "\n" +
                "### 概述\n" +
                "\n" +
                "` java.util.Date`类 表示特定的瞬间，精确到毫秒。\n" +
                "\n" +
                "继续查阅Date类的描述，发现Date拥有多个构造函数，只是部分已经过时，但是其中有未过时的构造函数可以把毫秒值转成日期对象。\n" +
                "\n" +
                "- `public Date()`：分配Date对象并初始化此对象，以表示分配它的时间（精确到毫秒）。\n" +
                "- `public Date(long date)`：分配Date对象并初始化此对象，以表示自从标准基准时间（称为“历元（epoch）”，即1970年1月1日00:00:00 GMT）以来的指定毫秒数。\n" +
                "\n" +
                "> tips: 由于我们处于东八区，所以我们的基准时间为1970年1月1日8时0分0秒。\n" +
                "\n" +
                "简单来说：使用无参构造，可以自动设置当前系统时间的毫秒时刻；指定long类型的构造参数，可以自定义毫秒时刻。例如：\n" +
                "\n" +
                "```java\n" +
                "import java.util.Date;\n" +
                "\n" +
                "public class Demo01Date {\n" +
                "    public static void main(String[] args) {\n" +
                "        // 创建日期对象，把当前的时间\n" +
                "        System.out.println(new Date()); // Tue Jan 16 14:37:35 CST 2018\n" +
                "        // 创建日期对象，把当前的毫秒值转成日期对象\n" +
                "        System.out.println(new Date(0L)); // Thu Jan 01 08:00:00 CST 1970\n" +
                "    }\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "> tips:在使用println方法时，会自动调用Date类中的toString方法。Date类对Object类中的toString方法进行了覆盖重写，所以结果为指定格式的字符串。\n" +
                ">\n" +
                "\n" +
                "### 常用方法\n" +
                "\n" +
                "Date类中的多数方法已经过时，常用的方法有：\n" +
                "\n" +
                "* `public long getTime()` 把日期对象转换成对应的时间毫秒值。\n" +
                "\n" +
                "## 2.2 DateFormat类\n" +
                "\n" +
                "`java.text.DateFormat` 是日期/时间格式化子类的抽象类，我们通过这个类可以帮我们完成日期和文本之间的转换,也就是可以在Date对象与String对象之间进行来回转换。\n" +
                "\n" +
                "* **格式化**：按照指定的格式，从Date对象转换为String对象。\n" +
                "* **解析**：按照指定的格式，从String对象转换为Date对象。\n" +
                "\n" +
                "### 构造方法\n" +
                "\n" +
                "由于DateFormat为抽象类，不能直接使用，所以需要常用的子类`java.text.SimpleDateFormat`。这个类需要一个模式（格式）来指定格式化或解析的标准。构造方法为：\n" +
                "\n" +
                "* `public SimpleDateFormat(String pattern)`：用给定的模式和默认语言环境的日期格式符号构造SimpleDateFormat。\n" +
                "\n" +
                "参数pattern是一个字符串，代表日期时间的自定义格式。\n" +
                "\n" +
                "### 格式规则\n" +
                "\n" +
                "常用的格式规则为：\n" +
                "\n" +
                "| 标识字母（区分大小写） | 含义   |\n" +
                "| ----------- | ---- |\n" +
                "| y           | 年    |\n" +
                "| M           | 月    |\n" +
                "| d           | 日    |\n" +
                "| H           | 时    |\n" +
                "| m           | 分    |\n" +
                "| s           | 秒    |\n" +
                "\n" +
                "> 备注：更详细的格式规则，可以参考SimpleDateFormat类的API文档0。\n" +
                "\n" +
                "创建SimpleDateFormat对象的代码如：\n" +
                "\n" +
                "```java\n" +
                "import java.text.DateFormat;\n" +
                "import java.text.SimpleDateFormat;\n" +
                "\n" +
                "public class Demo02SimpleDateFormat {\n" +
                "    public static void main(String[] args) {\n" +
                "        // 对应的日期格式如：2018-01-16 15:06:38\n" +
                "        DateFormat format = new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\");\n" +
                "    }    \n" +
                "}\n" +
                "```\n" +
                "\n" +
                "### 常用方法\n" +
                "\n" +
                "DateFormat类的常用方法有：\n" +
                "\n" +
                "- `public String format(Date date)`：将Date对象格式化为字符串。\n" +
                "- `public Date parse(String source)`：将字符串解析为Date对象。\n" +
                "\n" +
                "#### format方法\n" +
                "\n" +
                "使用format方法的代码为：\n" +
                "\n" +
                "```java\n" +
                "import java.text.DateFormat;\n" +
                "import java.text.SimpleDateFormat;\n" +
                "import java.util.Date;\n" +
                "/*\n" +
                " 把Date对象转换成String\n" +
                "*/\n" +
                "public class Demo03DateFormatMethod {\n" +
                "    public static void main(String[] args) {\n" +
                "        Date date = new Date();\n" +
                "        // 创建日期格式化对象,在获取格式化对象时可以指定风格\n" +
                "        DateFormat df = new SimpleDateFormat(\"yyyy年MM月dd日\");\n" +
                "        String str = df.format(date);\n" +
                "        System.out.println(str); // 2008年1月23日\n" +
                "    }\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "#### parse方法\n" +
                "\n" +
                "使用parse方法的代码为：\n" +
                "\n" +
                "```java\n" +
                "import java.text.DateFormat;\n" +
                "import java.text.ParseException;\n" +
                "import java.text.SimpleDateFormat;\n" +
                "import java.util.Date;\n" +
                "/*\n" +
                " 把String转换成Date对象\n" +
                "*/\n" +
                "public class Demo04DateFormatMethod {\n" +
                "    public static void main(String[] args) throws ParseException {\n" +
                "        DateFormat df = new SimpleDateFormat(\"yyyy年MM月dd日\");\n" +
                "        String str = \"2018年12月11日\";\n" +
                "        Date date = df.parse(str);\n" +
                "        System.out.println(date); // Tue Dec 11 00:00:00 CST 2018\n" +
                "    }\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "## 2.3 练习\n" +
                "\n" +
                "请使用日期时间相关的API，计算出一个人已经出生了多少天。\n" +
                "\n" +
                "**思路：**\n" +
                "\n" +
                "1.获取当前时间对应的毫秒值\n" +
                "\n" +
                "2.获取自己出生日期对应的毫秒值\n" +
                "\n" +
                "3.两个时间相减（当前时间– 出生日期）\n" +
                "\n" +
                "**代码实现：**\n" +
                "\n" +
                "```java\n" +
                "public static void function() throws Exception {\n" +
                "\tSystem.out.println(\"请输入出生日期 格式 YYYY-MM-dd\");\n" +
                "\t// 获取出生日期,键盘输入\n" +
                "\tString birthdayString = new Scanner(System.in).next();\n" +
                "\t// 将字符串日期,转成Date对象\n" +
                "\t// 创建SimpleDateFormat对象,写日期模式\n" +
                "\tSimpleDateFormat sdf = new SimpleDateFormat(\"yyyy-MM-dd\");\n" +
                "\t// 调用方法parse,字符串转成日期对象\n" +
                "\tDate birthdayDate = sdf.parse(birthdayString);\t\n" +
                "\t// 获取今天的日期对象\n" +
                "\tDate todayDate = new Date();\t\n" +
                "\t// 将两个日期转成毫秒值,Date类的方法getTime\n" +
                "\tlong birthdaySecond = birthdayDate.getTime();\n" +
                "\tlong todaySecond = todayDate.getTime();\n" +
                "\tlong secone = todaySecond-birthdaySecond;\t\n" +
                "\tif (secone < 0){\n" +
                "\t\tSystem.out.println(\"还没出生呢\");\n" +
                "\t} else {\n" +
                "\t\tSystem.out.println(secone/1000/60/60/24);\n" +
                "\t}\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "## 2.4 Calendar类\n" +
                "\n" +
                "###  概念\n" +
                "\n" +
                "日历我们都见过\n" +
                "\n" +
                "![](img\\日历.jpg)\n" +
                "\n" +
                "`java.util.Calendar`是日历类，在Date后出现，替换掉了许多Date的方法。该类将所有可能用到的时间信息封装为静态成员变量，方便获取。日历类就是方便获取各个时间属性的。\n" +
                "\n" +
                "### 获取方式\n" +
                "\n" +
                "Calendar为抽象类，由于语言敏感性，Calendar类在创建对象时并非直接创建，而是通过静态方法创建，返回子类对象，如下：\n" +
                "\n" +
                "Calendar静态方法\n" +
                "\n" +
                "* `public static Calendar getInstance()`：使用默认时区和语言环境获得一个日历\n" +
                "\n" +
                "例如：\n" +
                "\n" +
                "```java\n" +
                "import java.util.Calendar;\n" +
                "\n" +
                "public class Demo06CalendarInit {\n" +
                "    public static void main(String[] args) {\n" +
                "        Calendar cal = Calendar.getInstance();\n" +
                "    }    \n" +
                "}\n" +
                "```\n" +
                "\n" +
                "### 常用方法\n" +
                "\n" +
                "根据Calendar类的API文档，常用方法有：\n" +
                "\n" +
                "- `public int get(int field)`：返回给定日历字段的值。\n" +
                "- `public void set(int field, int value)`：将给定的日历字段设置为给定值。\n" +
                "- `public abstract void add(int field, int amount)`：根据日历的规则，为给定的日历字段添加或减去指定的时间量。\n" +
                "- `public Date getTime()`：返回一个表示此Calendar时间值（从历元到现在的毫秒偏移量）的Date对象。\n" +
                "\n" +
                "Calendar类中提供很多成员常量，代表给定的日历字段：\n" +
                "\n" +
                "| 字段值          | 含义                   |\n" +
                "| ------------ | -------------------- |\n" +
                "| YEAR         | 年                    |\n" +
                "| MONTH        | 月（从0开始，可以+1使用）       |\n" +
                "| DAY_OF_MONTH | 月中的天（几号）             |\n" +
                "| HOUR         | 时（12小时制）             |\n" +
                "| HOUR_OF_DAY  | 时（24小时制）             |\n" +
                "| MINUTE       | 分                    |\n" +
                "| SECOND       | 秒                    |\n" +
                "| DAY_OF_WEEK  | 周中的天（周几，周日为1，可以-1使用） |\n" +
                "\n" +
                "#### get/set方法\n" +
                "\n" +
                "get方法用来获取指定字段的值，set方法用来设置指定字段的值，代码使用演示：\n" +
                "\n" +
                "```java\n" +
                "import java.util.Calendar;\n" +
                "\n" +
                "public class CalendarUtil {\n" +
                "    public static void main(String[] args) {\n" +
                "        // 创建Calendar对象\n" +
                "        Calendar cal = Calendar.getInstance();\n" +
                "        // 设置年 \n" +
                "        int year = cal.get(Calendar.YEAR);\n" +
                "        // 设置月，西方的月份是0~11，因此要加上1才符合中国人的习惯\n" +
                "        int month = cal.get(Calendar.MONTH) + 1;\n" +
                "        // 设置日\n" +
                "        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);\n" +
                "        System.out.print(year + \"年\" + month + \"月\" + dayOfMonth + \"日\");\n" +
                "    }    \n" +
                "}\n" +
                "```\n" +
                "\n" +
                "```java\n" +
                "import java.util.Calendar;\n" +
                "\n" +
                "public class Demo07CalendarMethod {\n" +
                "    public static void main(String[] args) {\n" +
                "        Calendar cal = Calendar.getInstance();\n" +
                "        cal.set(Calendar.YEAR, 2020);\n" +
                "        System.out.print(year + \"年\" + month + \"月\" + dayOfMonth + \"日\"); // 2020年1月17日\n" +
                "        cal.set(8888,8,8);//同时设置calendar对象的年月日属性\n" +
                "    }\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "#### add方法\n" +
                "\n" +
                "add方法可以对指定日历字段的值进行加减操作，如果第二个参数为正数则加上偏移量，如果为负数则减去偏移量。代码如：\n" +
                "\n" +
                "```java\n" +
                "import java.util.Calendar;\n" +
                "\n" +
                "public class Demo08CalendarMethod {\n" +
                "    public static void main(String[] args) {\n" +
                "        Calendar cal = Calendar.getInstance();\n" +
                "        System.out.print(year + \"年\" + month + \"月\" + dayOfMonth + \"日\"); // 2018年1月17日\n" +
                "        // 使用add方法\n" +
                "        cal.add(Calendar.DAY_OF_MONTH, 2); // 加2天\n" +
                "        cal.add(Calendar.YEAR, -3); // 减3年\n" +
                "        System.out.print(year + \"年\" + month + \"月\" + dayOfMonth + \"日\"); // 2015年1月18日; \n" +
                "    }\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "#### getTime方法\n" +
                "\n" +
                "Calendar中的getTime方法并不是获取毫秒时刻，而是拿到对应的Date对象。\n" +
                "\n" +
                "```java\n" +
                "import java.util.Calendar;\n" +
                "import java.util.Date;\n" +
                "\n" +
                "public class Demo09CalendarMethod {\n" +
                "    public static void main(String[] args) {\n" +
                "        Calendar cal = Calendar.getInstance();\n" +
                "        Date date = cal.getTime();\n" +
                "        System.out.println(date); // Tue Jan 16 16:03:09 CST 2018\n" +
                "    }\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "> 小贴士：\n" +
                ">\n" +
                "> \u200B     西方星期的开始为周日，中国为周一。\n" +
                ">\n" +
                "> \u200B     在Calendar类中，月份的表示是以0-11代表1-12月。\n" +
                ">\n" +
                "> \u200B     日期是有大小关系的，时间靠后，时间越大。\n" +
                ">\n" +
                "\n" +
                "# 第三章 System类\n" +
                "\n" +
                "`java.lang.System`类中提供了大量的静态方法，可以获取与系统相关的信息或系统级操作，在System类的API文档中，常用的方法有：\n" +
                "\n" +
                "- `public static long currentTimeMillis()`：返回以毫秒为单位的当前时间。\n" +
                "- `public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)`：将数组中指定的数据拷贝到另一个数组中。\n" +
                "\n" +
                "## 3.1 currentTimeMillis方法\n" +
                "\n" +
                "实际上，currentTimeMillis方法就是 获取当前系统时间与1970年01月01日00:00点之间的毫秒差值\n" +
                "\n" +
                "```java\n" +
                "import java.util.Date;\n" +
                "\n" +
                "public class SystemDemo {\n" +
                "    public static void main(String[] args) {\n" +
                "       \t//获取当前时间毫秒值\n" +
                "        System.out.println(System.currentTimeMillis()); // 1516090531144\n" +
                "    }\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "### 练习\n" +
                "\n" +
                "验证for循环打印数字1-9999所需要使用的时间（毫秒）\n" +
                "\n" +
                "~~~java\n" +
                "public class SystemTest1 {\n" +
                "    public static void main(String[] args) {\n" +
                "        long start = System.currentTimeMillis();\n" +
                "        for (int i = 0; i < 10000; i++) {\n" +
                "            System.out.println(i);\n" +
                "        }\n" +
                "        long end = System.currentTimeMillis();\n" +
                "        System.out.println(\"共耗时毫秒：\" + (end - start));\n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "## 3.2 arraycopy方法\n" +
                "\n" +
                "* `public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)`：将数组中指定的数据拷贝到另一个数组中。\n" +
                "\n" +
                "数组的拷贝动作是系统级的，性能很高。System.arraycopy方法具有5个参数，含义分别为：\n" +
                "\n" +
                "| 参数序号 | 参数名称    | 参数类型   | 参数含义       |\n" +
                "| ---- | ------- | ------ | ---------- |\n" +
                "| 1    | src     | Object | 源数组        |\n" +
                "| 2    | srcPos  | int    | 源数组索引起始位置  |\n" +
                "| 3    | dest    | Object | 目标数组       |\n" +
                "| 4    | destPos | int    | 目标数组索引起始位置 |\n" +
                "| 5    | length  | int    | 复制元素个数     |\n" +
                "\n" +
                "### 练习\n" +
                "\n" +
                "将src数组中前3个元素，复制到dest数组的前3个位置上复制元素前：src数组元素[1,2,3,4,5]，dest数组元素[6,7,8,9,10]复制元素后：src数组元素[1,2,3,4,5]，dest数组元素[1,2,3,9,10]\n" +
                "\n" +
                "```java\n" +
                "import java.util.Arrays;\n" +
                "\n" +
                "public class Demo11SystemArrayCopy {\n" +
                "    public static void main(String[] args) {\n" +
                "        int[] src = new int[]{1,2,3,4,5};\n" +
                "        int[] dest = new int[]{6,7,8,9,10};\n" +
                "        System.arraycopy( src, 0, dest, 0, 3);\n" +
                "        /*代码运行后：两个数组中的元素发生了变化\n" +
                "         src数组元素[1,2,3,4,5]\n" +
                "         dest数组元素[1,2,3,9,10]\n" +
                "        */\n" +
                "    }\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "# 第四章 StringBuilder类\n" +
                "\n" +
                "## 4.1 字符串拼接问题\n" +
                "\n" +
                "由于String类的对象内容不可改变，所以每当进行字符串拼接时，总是会在内存中创建一个新的对象。例如：\n" +
                "\n" +
                "~~~java\n" +
                "public class StringDemo {\n" +
                "    public static void main(String[] args) {\n" +
                "        String s = \"Hello\";\n" +
                "        s += \"World\";\n" +
                "        System.out.println(s);\n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "在API中对String类有这样的描述：字符串是常量，它们的值在创建后不能被更改。\n" +
                "\n" +
                "根据这句话分析我们的代码，其实总共产生了三个字符串，即`\"Hello\"`、`\"World\"`和`\"HelloWorld\"`。引用变量s首先指向`Hello`对象，最终指向拼接出来的新字符串对象，即`HelloWord` 。\n" +
                "\n" +
                "![](img\\String拼接问题.bmp)\n" +
                "\n" +
                "由此可知，如果对字符串进行拼接操作，每次拼接，都会构建一个新的String对象，既耗时，又浪费空间。为了解决这一问题，可以使用`java.lang.StringBuilder`类。\n" +
                "\n" +
                "## 4.2 StringBuilder概述\n" +
                "\n" +
                "查阅`java.lang.StringBuilder`的API，StringBuilder又称为可变字符序列，它是一个类似于 String 的字符串缓冲区，通过某些方法调用可以改变该序列的长度和内容。\n" +
                "\n" +
                "原来StringBuilder是个字符串的缓冲区，即它是一个容器，容器中可以装很多字符串。并且能够对其中的字符串进行各种操作。\n" +
                "\n" +
                "它的内部拥有一个数组用来存放字符串内容，进行字符串拼接时，直接在数组中加入新内容。StringBuilder会自动维护数组的扩容。原理如下图所示：(默认16字符空间，超过自动扩充)\n" +
                "\n" +
                "![06-StringBuilder的原理](img\\06-StringBuilder的原理.png)\n" +
                "\n" +
                "## 4.3 构造方法\n" +
                "\n" +
                "根据StringBuilder的API文档，常用构造方法有2个：\n" +
                "\n" +
                "- `public StringBuilder()`：构造一个空的StringBuilder容器。\n" +
                "- `public StringBuilder(String str)`：构造一个StringBuilder容器，并将字符串添加进去。\n" +
                "\n" +
                "```java\n" +
                "public class StringBuilderDemo {\n" +
                "    public static void main(String[] args) {\n" +
                "        StringBuilder sb1 = new StringBuilder();\n" +
                "        System.out.println(sb1); // (空白)\n" +
                "        // 使用带参构造\n" +
                "        StringBuilder sb2 = new StringBuilder(\"itcast\");\n" +
                "        System.out.println(sb2); // itcast\n" +
                "    }\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "## 4.4 常用方法\n" +
                "\n" +
                "StringBuilder常用的方法有2个：\n" +
                "\n" +
                "- `public StringBuilder append(...)`：添加任意类型数据的字符串形式，并返回当前对象自身。\n" +
                "- `public String toString()`：将当前StringBuilder对象转换为String对象。\n" +
                "\n" +
                "### append方法\n" +
                "\n" +
                "append方法具有多种重载形式，可以接收任意类型的参数。任何数据作为参数都会将对应的字符串内容添加到StringBuilder中。例如：\n" +
                "\n" +
                "```java\n" +
                "public class Demo02StringBuilder {\n" +
                "\tpublic static void main(String[] args) {\n" +
                "\t\t//创建对象\n" +
                "\t\tStringBuilder builder = new StringBuilder();\n" +
                "\t\t//public StringBuilder append(任意类型)\n" +
                "\t\tStringBuilder builder2 = builder.append(\"hello\");\n" +
                "\t\t//对比一下\n" +
                "\t\tSystem.out.println(\"builder:\"+builder);\n" +
                "\t\tSystem.out.println(\"builder2:\"+builder2);\n" +
                "\t\tSystem.out.println(builder == builder2); //true\n" +
                "\t    // 可以添加 任何类型\n" +
                "\t\tbuilder.append(\"hello\");\n" +
                "\t\tbuilder.append(\"world\");\n" +
                "\t\tbuilder.append(true);\n" +
                "\t\tbuilder.append(100);\n" +
                "\t\t// 在我们开发中，会遇到调用一个方法后，返回一个对象的情况。然后使用返回的对象继续调用方法。\n" +
                "        // 这种时候，我们就可以把代码现在一起，如append方法一样，代码如下\n" +
                "\t\t//链式编程\n" +
                "\t\tbuilder.append(\"hello\").append(\"world\").append(true).append(100);\n" +
                "\t\tSystem.out.println(\"builder:\"+builder);\n" +
                "\t}\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "> 备注：StringBuilder已经覆盖重写了Object当中的toString方法。\n" +
                "\n" +
                "### toString方法\n" +
                "\n" +
                "通过toString方法，StringBuilder对象将会转换为不可变的String对象。如：\n" +
                "\n" +
                "```java\n" +
                "public class Demo16StringBuilder {\n" +
                "    public static void main(String[] args) {\n" +
                "        // 链式创建\n" +
                "        StringBuilder sb = new StringBuilder(\"Hello\").append(\"World\").append(\"Java\");\n" +
                "        // 调用方法\n" +
                "        String str = sb.toString();\n" +
                "        System.out.println(str); // HelloWorldJava\n" +
                "    }\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "# 第五章 包装类\n" +
                "\n" +
                "## 5.1 概述\n" +
                "\n" +
                "Java提供了两个类型系统，基本类型与引用类型，使用基本类型在于效率，然而很多情况，会创建对象使用，因为对象可以做更多的功能，如果想要我们的基本类型像对象一样操作，就可以使用基本类型对应的包装类，如下：\n" +
                "\n" +
                "| 基本类型    | 对应的包装类（位于java.lang包中） |\n" +
                "| ------- | --------------------- |\n" +
                "| byte    | Byte                  |\n" +
                "| short   | Short                 |\n" +
                "| int     | **Integer**           |\n" +
                "| long    | Long                  |\n" +
                "| float   | Float                 |\n" +
                "| double  | Double                |\n" +
                "| char    | **Character**         |\n" +
                "| boolean | Boolean               |\n" +
                "\n" +
                "## 5.2 装箱与拆箱\n" +
                "\n" +
                "基本类型与对应的包装类对象之间，来回转换的过程称为”装箱“与”拆箱“：\n" +
                "\n" +
                "* **装箱**：从基本类型转换为对应的包装类对象。\n" +
                "\n" +
                "* **拆箱**：从包装类对象转换为对应的基本类型。\n" +
                "\n" +
                "用Integer与 int为例：（看懂代码即可）\n" +
                "\n" +
                "基本数值---->包装对象\n" +
                "\n" +
                "~~~java\n" +
                "Integer i = new Integer(4);//使用构造函数函数\n" +
                "Integer iii = Integer.valueOf(4);//使用包装类中的valueOf方法\n" +
                "~~~\n" +
                "\n" +
                "包装对象---->基本数值\n" +
                "\n" +
                "~~~java\n" +
                "int num = i.intValue();\n" +
                "~~~\n" +
                "## 5.3自动装箱与自动拆箱\n" +
                "\n" +
                "由于我们经常要做基本类型与包装类之间的转换，从Java 5（JDK 1.5）开始，基本类型与包装类的装箱、拆箱动作可以自动完成。例如：\n" +
                "\n" +
                "```java\n" +
                "Integer i = 4;//自动装箱。相当于Integer i = Integer.valueOf(4);\n" +
                "i = i + 5;//等号右边：将i对象转成基本数值(自动拆箱) i.intValue() + 5;\n" +
                "//加法运算完成后，再次装箱，把基本数值转成对象。\n" +
                "```\n" +
                "\n" +
                "## 5.3 基本类型与字符串之间的转换\n" +
                "\n" +
                "### 基本类型转换为String\n" +
                "\n" +
                "   基本类型转换String总共有三种方式，查看课后资料可以得知，这里只讲最简单的一种方式： \n" +
                "\n" +
                "~~~\n" +
                "基本类型直接与””相连接即可；如：34+\"\"\n" +
                "~~~\n" +
                "\n" +
                "\u200B\t第二种方式：包装类的静态方法toString(参数)，不是Object类的toString(),是重载形式的toString()\n" +
                "\n" +
                "~~~java\n" +
                "static String toString(int i) //返回一个表示指定整数的String对象\n" +
                "~~~\n" +
                "\n" +
                "\u200B\t第三种方式：String类的静态方法valueOf(参数)\n" +
                "\n" +
                "~~~java\n" +
                "static String valueOf(int i)//返回int参数的字符串表现形式\n" +
                "~~~\n" +
                "\n" +
                "### String转换成对应的基本类型 \n" +
                "\n" +
                "除了Character类之外，其他所有包装类都具有parseXxx静态方法可以将字符串参数转换为对应的基本类型：\n" +
                "\n" +
                "- `public static byte parseByte(String s)`：将字符串参数转换为对应的byte基本类型。\n" +
                "- `public static short parseShort(String s)`：将字符串参数转换为对应的short基本类型。\n" +
                "- `public static int parseInt(String s)`：将字符串参数转换为对应的int基本类型。\n" +
                "- `public static long parseLong(String s)`：将字符串参数转换为对应的long基本类型。\n" +
                "- `public static float parseFloat(String s)`：将字符串参数转换为对应的float基本类型。\n" +
                "- `public static double parseDouble(String s)`：将字符串参数转换为对应的double基本类型。\n" +
                "- `public static boolean parseBoolean(String s)`：将字符串参数转换为对应的boolean基本类型。\n" +
                "\n" +
                "代码使用（仅以Integer类的静态方法parseXxx为例）如：\n" +
                "\n" +
                "```java\n" +
                "public class Demo18WrapperParse {\n" +
                "    public static void main(String[] args) {\n" +
                "        int num = Integer.parseInt(\"100\");\n" +
                "    }\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "> 注意:如果字符串参数的内容无法正确转换为对应的基本类型，则会抛出`java.lang.NumberFormatException`异常。\n" +
                ">\n" +
                "\n";
        contents[1] = "# day02【Collection、泛型】\n" +
                "\n" +
                "## 主要内容\n" +
                "\n" +
                "- Collection集合\n" +
                "- 迭代器\n" +
                "- 增强for\n" +
                "- 泛型\n" +
                "\n" +
                "## 教学目标\n" +
                "\n" +
                "- [ ] 能够说出集合与数组的区别\n" +
                "- [ ] 说出Collection集合的常用功能\n" +
                "- [ ] 能够使用迭代器对集合进行取元素\n" +
                "- [ ] 能够说出集合的使用细节\n" +
                "- [ ] 能够使用集合存储自定义类型\n" +
                "- [ ] 能够使用foreach循环遍历集合\n" +
                "- [ ] 能够使用泛型定义集合对象\n" +
                "- [ ] 能够理解泛型上下限\n" +
                "- [ ] 能够阐述泛型通配符的作用\n" +
                "\n" +
                "\n" +
                "\n" +
                "# 第一章 Collection集合\n" +
                "\n" +
                "## 1.1 集合概述\n" +
                "\n" +
                "在前面基础班我们已经学习过并使用过集合ArrayList<E> ,那么集合到底是什么呢?\n" +
                "\n" +
                "* **集合**：集合是java中提供的一种容器，可以用来存储多个数据。\n" +
                "\n" +
                "集合和数组既然都是容器，它们有啥区别呢？\n" +
                "\n" +
                "* 数组的长度是固定的。集合的长度是可变的。\n" +
                "* 数组中存储的是同一类型的元素，可以存储基本数据类型值。集合存储的都是对象。而且对象的类型可以不一致。在开发中一般当对象多的时候，使用集合进行存储。\n" +
                "\n" +
                "## 1.2  集合框架\n" +
                "\n" +
                "JAVASE提供了满足各种需求的API，在使用这些API前，先了解其继承与接口操作架构，才能了解何时采用哪个类，以及类之间如何彼此合作，从而达到灵活应用。\n" +
                "\n" +
                "集合按照其存储结构可以分为两大类，分别是单列集合`java.util.Collection`和双列集合`java.util.Map`，今天我们主要学习`Collection`集合，在day04时讲解`Map`集合。\n" +
                "\n" +
                "* **Collection**：单列集合类的根接口，用于存储一系列符合某种规则的元素，它有两个重要的子接口，分别是`java.util.List`和`java.util.Set`。其中，`List`的特点是元素有序、元素可重复。`Set`的特点是元素无序，而且不可重复。`List`接口的主要实现类有`java.util.ArrayList`和`java.util.LinkedList`，`Set`接口的主要实现类有`java.util.HashSet`和`java.util.TreeSet`。\n" +
                "\n" +
                "从上面的描述可以看出JDK中提供了丰富的集合类库，为了便于初学者进行系统地学习，接下来通过一张图来描述整个集合类的继承体系。\n" +
                "\n" +
                "![](img\\Collection集合体系图.png)\n" +
                "\n" +
                "其中，橙色框里填写的都是接口类型，而蓝色框里填写的都是具体的实现类。这几天将针对图中所列举的集合类进行逐一地讲解。\n" +
                "\n" +
                "集合本身是一个工具，它存放在java.util包中。在`Collection`接口定义着单列集合框架中最最共性的内容。\n" +
                "\n" +
                "## 1.3 Collection 常用功能\n" +
                "\n" +
                "Collection是所有单列集合的父接口，因此在Collection中定义了单列集合(List和Set)通用的一些方法，这些方法可用于操作所有的单列集合。方法如下：\n" +
                "\n" +
                "* `public boolean add(E e)`：  把给定的对象添加到当前集合中 。\n" +
                "* `public void clear()` :清空集合中所有的元素。\n" +
                "* `public boolean remove(E e)`: 把给定的对象在当前集合中删除。\n" +
                "* `public boolean contains(E e)`: 判断当前集合中是否包含给定的对象。\n" +
                "* `public boolean isEmpty()`: 判断当前集合是否为空。\n" +
                "* `public int size()`: 返回集合中元素的个数。\n" +
                "* `public Object[] toArray()`: 把集合中的元素，存储到数组中。\n" +
                "\n" +
                "方法演示：\n" +
                "\n" +
                "~~~java\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.Collection;\n" +
                "\n" +
                "public class Demo1Collection {\n" +
                "    public static void main(String[] args) {\n" +
                "\t\t// 创建集合对象 \n" +
                "    \t// 使用多态形式\n" +
                "    \tCollection<String> coll = new ArrayList<String>();\n" +
                "    \t// 使用方法\n" +
                "    \t// 添加功能  boolean  add(String s)\n" +
                "    \tcoll.add(\"小李广\");\n" +
                "    \tcoll.add(\"扫地僧\");\n" +
                "    \tcoll.add(\"石破天\");\n" +
                "    \tSystem.out.println(coll);\n" +
                "\n" +
                "    \t// boolean contains(E e) 判断o是否在集合中存在\n" +
                "    \tSystem.out.println(\"判断  扫地僧 是否在集合中\"+coll.contains(\"扫地僧\"));\n" +
                "\n" +
                "    \t//boolean remove(E e) 删除在集合中的o元素\n" +
                "    \tSystem.out.println(\"删除石破天：\"+coll.remove(\"石破天\"));\n" +
                "    \tSystem.out.println(\"操作之后集合中元素:\"+coll);\n" +
                "    \t\n" +
                "    \t// size() 集合中有几个元素\n" +
                "\t\tSystem.out.println(\"集合中有\"+coll.size()+\"个元素\");\n" +
                "\n" +
                "\t\t// Object[] toArray()转换成一个Object数组\n" +
                "    \tObject[] objects = coll.toArray();\n" +
                "    \t// 遍历数组\n" +
                "    \tfor (int i = 0; i < objects.length; i++) {\n" +
                "\t\t\tSystem.out.println(objects[i]);\n" +
                "\t\t}\n" +
                "\n" +
                "\t\t// void  clear() 清空集合\n" +
                "\t\tcoll.clear();\n" +
                "\t\tSystem.out.println(\"集合中内容为：\"+coll);\n" +
                "\t\t// boolean  isEmpty()  判断是否为空\n" +
                "\t\tSystem.out.println(coll.isEmpty());  \t\n" +
                "\t}\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "> tips: 有关Collection中的方法可不止上面这些，其他方法可以自行查看API学习。\n" +
                "\n" +
                "## 第二章 Iterator迭代器\n" +
                "\n" +
                "## 2.1 Iterator接口\n" +
                "\n" +
                "在程序开发中，经常需要遍历集合中的所有元素。针对这种需求，JDK专门提供了一个接口`java.util.Iterator`。`Iterator`接口也是Java集合中的一员，但它与`Collection`、`Map`接口有所不同，`Collection`接口与`Map`接口主要用于存储元素，而`Iterator`主要用于迭代访问（即遍历）`Collection`中的元素，因此`Iterator`对象也被称为迭代器。\n" +
                "\n" +
                "想要遍历Collection集合，那么就要获取该集合迭代器完成迭代操作，下面介绍一下获取迭代器的方法：\n" +
                "\n" +
                "* `public Iterator iterator()`: 获取集合对应的迭代器，用来遍历集合中的元素的。\n" +
                "\n" +
                "下面介绍一下迭代的概念：\n" +
                "\n" +
                "* **迭代**：即Collection集合元素的通用获取方式。在取元素之前先要判断集合中有没有元素，如果有，就把这个元素取出来，继续在判断，如果还有就再取出出来。一直把集合中的所有元素全部取出。这种取出方式专业术语称为迭代。\n" +
                "\n" +
                "Iterator接口的常用方法如下：\n" +
                "\n" +
                "* `public E next()`:返回迭代的下一个元素。\n" +
                "* `public boolean hasNext()`:如果仍有元素可以迭代，则返回 true。\n" +
                "\n" +
                "接下来我们通过案例学习如何使用Iterator迭代集合中元素：\n" +
                "\n" +
                "~~~java\n" +
                "public class IteratorDemo {\n" +
                "  \tpublic static void main(String[] args) {\n" +
                "        // 使用多态方式 创建对象\n" +
                "        Collection<String> coll = new ArrayList<String>();\n" +
                "\n" +
                "        // 添加元素到集合\n" +
                "        coll.add(\"串串星人\");\n" +
                "        coll.add(\"吐槽星人\");\n" +
                "        coll.add(\"汪星人\");\n" +
                "        //遍历\n" +
                "        //使用迭代器 遍历   每个集合对象都有自己的迭代器\n" +
                "        Iterator<String> it = coll.iterator();\n" +
                "        //  泛型指的是 迭代出 元素的数据类型\n" +
                "        while(it.hasNext()){ //判断是否有迭代元素\n" +
                "            String s = it.next();//获取迭代出的元素\n" +
                "            System.out.println(s);\n" +
                "        }\n" +
                "  \t}\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "> tips:：在进行集合元素取出时，如果集合中已经没有元素了，还继续使用迭代器的next方法，将会发生java.util.NoSuchElementException没有集合元素的错误。\n" +
                "\n" +
                "## 2.2 迭代器的实现原理\n" +
                "\n" +
                "我们在之前案例已经完成了Iterator遍历集合的整个过程。当遍历集合时，首先通过调用t集合的iterator()方法获得迭代器对象，然后使用hashNext()方法判断集合中是否存在下一个元素，如果存在，则调用next()方法将元素取出，否则说明已到达了集合末尾，停止遍历元素。\n" +
                "\n" +
                "Iterator迭代器对象在遍历集合时，内部采用指针的方式来跟踪集合中的元素，为了让初学者能更好地理解迭代器的工作原理，接下来通过一个图例来演示Iterator对象迭代元素的过程：\n" +
                "\n" +
                "![](img\\迭代器原理图.bmp)\n" +
                "\n" +
                "在调用Iterator的next方法之前，迭代器的索引位于第一个元素之前，不指向任何元素，当第一次调用迭代器的next方法后，迭代器的索引会向后移动一位，指向第一个元素并将该元素返回，当再次调用next方法时，迭代器的索引会指向第二个元素并将该元素返回，依此类推，直到hasNext方法返回false，表示到达了集合的末尾，终止对元素的遍历。\n" +
                "\n" +
                "## 2.3 增强for\n" +
                "\n" +
                "增强for循环(也称for each循环)是**JDK1.5**以后出来的一个高级for循环，专门用来遍历数组和集合的。它的内部原理其实是个Iterator迭代器，所以在遍历的过程中，不能对集合中的元素进行增删操作。\n" +
                "\n" +
                "格式：\n" +
                "\n" +
                "~~~java\n" +
                "for(元素的数据类型  变量 : Collection集合or数组){ \n" +
                "  \t//写操作代码\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "它用于遍历Collection和数组。通常只进行遍历元素，不要在遍历的过程中对集合元素进行增删操作。\n" +
                "\n" +
                "#### 练习1：遍历数组\n" +
                "\n" +
                "~~~java\n" +
                "public class NBForDemo1 {\n" +
                "    public static void main(String[] args) {\n" +
                "\t\tint[] arr = {3,5,6,87};\n" +
                "       \t//使用增强for遍历数组\n" +
                "\t\tfor(int a : arr){//a代表数组中的每个元素\n" +
                "\t\t\tSystem.out.println(a);\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "#### 练习2:遍历集合\n" +
                "\n" +
                "~~~java\n" +
                "public class NBFor {\n" +
                "    public static void main(String[] args) {        \n" +
                "    \tCollection<String> coll = new ArrayList<String>();\n" +
                "    \tcoll.add(\"小河神\");\n" +
                "    \tcoll.add(\"老河神\");\n" +
                "    \tcoll.add(\"神婆\");\n" +
                "    \t//使用增强for遍历\n" +
                "    \tfor(String s :coll){//接收变量s代表 代表被遍历到的集合元素\n" +
                "    \t\tSystem.out.println(s);\n" +
                "    \t}\n" +
                "\t}\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "> tips: 新for循环必须有被遍历的目标。目标只能是Collection或者是数组。新式for仅仅作为遍历操作出现。\n" +
                "\n" +
                "# 第三章 泛型\n" +
                "\n" +
                "## 3.1  泛型概述\n" +
                "\n" +
                "在前面学习集合时，我们都知道集合中是可以存放任意对象的，只要把对象存储集合后，那么这时他们都会被提升成Object类型。当我们在取出每一个对象，并且进行相应的操作，这时必须采用类型转换。\n" +
                "\n" +
                "大家观察下面代码：\n" +
                "\n" +
                "~~~java\n" +
                "public class GenericDemo {\n" +
                "\tpublic static void main(String[] args) {\n" +
                "\t\tCollection coll = new ArrayList();\n" +
                "\t\tcoll.add(\"abc\");\n" +
                "\t\tcoll.add(\"itcast\");\n" +
                "\t\tcoll.add(5);//由于集合没有做任何限定，任何类型都可以给其中存放\n" +
                "\t\tIterator it = coll.iterator();\n" +
                "\t\twhile(it.hasNext()){\n" +
                "\t\t\t//需要打印每个字符串的长度,就要把迭代出来的对象转成String类型\n" +
                "\t\t\tString str = (String) it.next();\n" +
                "\t\t\tSystem.out.println(str.length());\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "程序在运行时发生了问题**java.lang.ClassCastException**。                                                                                             为什么会发生类型转换异常呢？                                                                                                                                       我们来分析下：由于集合中什么类型的元素都可以存储。导致取出时强转引发运行时 ClassCastException。                                                                                                                                                       怎么来解决这个问题呢？                                                                                                                                                           Collection虽然可以存储各种对象，但实际上通常Collection只存储同一类型对象。例如都是存储字符串对象。因此在JDK5之后，新增了**泛型**(**Generic**)语法，让你在设计API时可以指定类或方法支持泛型，这样我们使用API的时候也变得更为简洁，并得到了编译时期的语法检查。\n" +
                "\n" +
                "* **泛型**：可以在类或方法中预支地使用未知的类型。\n" +
                "\n" +
                "> tips:一般在创建对象时，将未知的类型确定具体的类型。当没有指定泛型时，默认类型为Object类型。\n" +
                "\n" +
                "## 3.2  使用泛型的好处\n" +
                "\n" +
                "上一节只是讲解了泛型的引入，那么泛型带来了哪些好处呢？\n" +
                "\n" +
                "* 将运行时期的ClassCastException，转移到了编译时期变成了编译失败。\n" +
                "* 避免了类型强转的麻烦。\n" +
                "\n" +
                "通过我们如下代码体验一下：\n" +
                "\n" +
                "~~~java\n" +
                "public class GenericDemo2 {\n" +
                "\tpublic static void main(String[] args) {\n" +
                "        Collection<String> list = new ArrayList<String>();\n" +
                "        list.add(\"abc\");\n" +
                "        list.add(\"itcast\");\n" +
                "        // list.add(5);//当集合明确类型后，存放类型不一致就会编译报错\n" +
                "        // 集合已经明确具体存放的元素类型，那么在使用迭代器的时候，迭代器也同样会知道具体遍历元素类型\n" +
                "        Iterator<String> it = list.iterator();\n" +
                "        while(it.hasNext()){\n" +
                "            String str = it.next();\n" +
                "            //当使用Iterator<String>控制元素类型后，就不需要强转了。获取到的元素直接就是String类型\n" +
                "            System.out.println(str.length());\n" +
                "        }\n" +
                "\t}\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "> tips:泛型是数据类型的一部分，我们将类名与泛型合并一起看做数据类型。\n" +
                "\n" +
                "## 3.3  泛型的定义与使用\n" +
                "\n" +
                "我们在集合中会大量使用到泛型，这里来完整地学习泛型知识。\n" +
                "\n" +
                "泛型，用来灵活地将数据类型应用到不同的类、方法、接口当中。将数据类型作为参数进行传递。\n" +
                "\n" +
                "### 定义和使用含有泛型的类\n" +
                "\n" +
                "定义格式：\n" +
                "\n" +
                "~~~\n" +
                "修饰符 class 类名<代表泛型的变量> {  }\n" +
                "~~~\n" +
                "\n" +
                "例如，API中的ArrayList集合：\n" +
                "\n" +
                "~~~java\n" +
                "class ArrayList<E>{ \n" +
                "    public boolean add(E e){ }\n" +
                "\n" +
                "    public E get(int index){ }\n" +
                "   \t....\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "使用泛型： 即什么时候确定泛型。\n" +
                "\n" +
                "**在创建对象的时候确定泛型**\n" +
                "\n" +
                " 例如，`ArrayList<String> list = new ArrayList<String>();`\n" +
                "\n" +
                "此时，变量E的值就是String类型,那么我们的类型就可以理解为：\n" +
                "\n" +
                "~~~java \n" +
                "class ArrayList<String>{ \n" +
                "     public boolean add(String e){ }\n" +
                "\n" +
                "     public String get(int index){  }\n" +
                "     ...\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "再例如，`ArrayList<Integer> list = new ArrayList<Integer>();`\n" +
                "\n" +
                "此时，变量E的值就是Integer类型,那么我们的类型就可以理解为：\n" +
                "\n" +
                "~~~java\n" +
                "class ArrayList<Integer> { \n" +
                "     public boolean add(Integer e) { }\n" +
                "\n" +
                "     public Integer get(int index) {  }\n" +
                "     ...\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "举例自定义泛型类\n" +
                "\n" +
                "~~~java\n" +
                "public class MyGenericClass<MVP> {\n" +
                "\t//没有MVP类型，在这里代表 未知的一种数据类型 未来传递什么就是什么类型\n" +
                "\tprivate MVP mvp;\n" +
                "     \n" +
                "    public void setMVP(MVP mvp) {\n" +
                "        this.mvp = mvp;\n" +
                "    }\n" +
                "     \n" +
                "    public MVP getMVP() {\n" +
                "        return mvp;\n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "使用:\n" +
                "\n" +
                "~~~java\n" +
                "public class GenericClassDemo {\n" +
                "  \tpublic static void main(String[] args) {\t\t \n" +
                "         // 创建一个泛型为String的类\n" +
                "         MyGenericClass<String> my = new MyGenericClass<String>();    \t\n" +
                "         // 调用setMVP\n" +
                "         my.setMVP(\"大胡子登登\");\n" +
                "         // 调用getMVP\n" +
                "         String mvp = my.getMVP();\n" +
                "         System.out.println(mvp);\n" +
                "         //创建一个泛型为Integer的类\n" +
                "         MyGenericClass<Integer> my2 = new MyGenericClass<Integer>(); \n" +
                "         my2.setMVP(123);   \t  \n" +
                "         Integer mvp2 = my2.getMVP();\n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "###  含有泛型的方法\n" +
                "\n" +
                "定义格式：\n" +
                "\n" +
                "~~~\n" +
                "修饰符 <代表泛型的变量> 返回值类型 方法名(参数){  }\n" +
                "~~~\n" +
                "\n" +
                "例如，\n" +
                "\n" +
                "~~~java\n" +
                "public class MyGenericMethod {\t  \n" +
                "    public <MVP> void show(MVP mvp) {\n" +
                "    \tSystem.out.println(mvp.getClass());\n" +
                "    }\n" +
                "    \n" +
                "    public <MVP> MVP show2(MVP mvp) {\t\n" +
                "    \treturn mvp;\n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "使用格式：**调用方法时，确定泛型的类型**\n" +
                "\n" +
                "~~~java\n" +
                "public class GenericMethodDemo {\n" +
                "    public static void main(String[] args) {\n" +
                "        // 创建对象\n" +
                "        MyGenericMethod mm = new MyGenericMethod();\n" +
                "        // 演示看方法提示\n" +
                "        mm.show(\"aaa\");\n" +
                "        mm.show(123);\n" +
                "        mm.show(12.45);\n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "### 含有泛型的接口\n" +
                "\n" +
                "定义格式：\n" +
                "\n" +
                "~~~\n" +
                "修饰符 interface接口名<代表泛型的变量> {  }\n" +
                "~~~\n" +
                "\n" +
                "例如，\n" +
                "\n" +
                "~~~java\n" +
                "public interface MyGenericInterface<E>{\n" +
                "\tpublic abstract void add(E e);\n" +
                "\t\n" +
                "\tpublic abstract E getE();  \n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "使用格式：\n" +
                "\n" +
                "**1、定义类时确定泛型的类型**\n" +
                "\n" +
                "例如\n" +
                "\n" +
                "~~~java\n" +
                "public class MyImp1 implements MyGenericInterface<String> {\n" +
                "\t@Override\n" +
                "    public void add(String e) {\n" +
                "        // 省略...\n" +
                "    }\n" +
                "\n" +
                "\t@Override\n" +
                "\tpublic String getE() {\n" +
                "\t\treturn null;\n" +
                "\t}\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "此时，泛型E的值就是String类型。\n" +
                "\n" +
                " **2、始终不确定泛型的类型，直到创建对象时，确定泛型的类型**\n" +
                "\n" +
                " 例如\n" +
                "\n" +
                "~~~java\n" +
                "public class MyImp2<E> implements MyGenericInterface<E> {\n" +
                "\t@Override\n" +
                "\tpublic void add(E e) {\n" +
                "       \t // 省略...\n" +
                "\t}\n" +
                "\n" +
                "\t@Override\n" +
                "\tpublic E getE() {\n" +
                "\t\treturn null;\n" +
                "\t}\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "确定泛型：\n" +
                "\n" +
                "~~~java\n" +
                "/*\n" +
                " * 使用\n" +
                " */\n" +
                "public class GenericInterface {\n" +
                "    public static void main(String[] args) {\n" +
                "        MyImp2<String>  my = new MyImp2<String>();  \n" +
                "        my.add(\"aa\");\n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "## 3.4  泛型通配符\n" +
                "\n" +
                "当使用泛型类或者接口时，传递的数据中，泛型类型不确定，可以通过通配符<?>表示。但是一旦使用泛型的通配符后，只能使用Object类中的共性方法，集合中元素自身方法无法使用。\n" +
                "\n" +
                "#### 通配符基本使用\n" +
                "\n" +
                "泛型的通配符:**不知道使用什么类型来接收的时候,此时可以使用?,?表示未知通配符。**\n" +
                "\n" +
                "此时只能接受数据,不能往该集合中存储数据。\n" +
                "\n" +
                "举个例子大家理解使用即可：\n" +
                "\n" +
                "~~~java\n" +
                "public static void main(String[] args) {\n" +
                "    Collection<Intger> list1 = new ArrayList<Integer>();\n" +
                "    getElement(list1);\n" +
                "    Collection<String> list2 = new ArrayList<String>();\n" +
                "    getElement(list2);\n" +
                "}\n" +
                "public static void getElement(Collection<?> coll){}\n" +
                "//？代表可以接收任意类型，此时除了问号，往<>中填E、T都会报错\n" +
                "~~~\n" +
                "\n" +
                "> tips:泛型不存在继承关系 Collection<Object> list = new ArrayList<String>();这种是错误的。\n" +
                "\n" +
                "#### 通配符高级使用----受限泛型\n" +
                "\n" +
                "之前设置泛型的时候，实际上是可以任意设置的，只要是类就可以设置。但是在JAVA的泛型中可以指定一个泛型的**上限**和**下限**。\n" +
                "\n" +
                "**泛型的上限**：\n" +
                "\n" +
                "* **格式**： `类型名称 <? extends 类 > 对象名称`\n" +
                "* **意义**： `只能接收该类型及其子类`\n" +
                "\n" +
                "**泛型的下限**：\n" +
                "\n" +
                "- **格式**： `类型名称 <? super 类 > 对象名称`\n" +
                "- **意义**： `只能接收该类型及其父类型`\n" +
                "\n" +
                "比如：现已知Object类，String 类，Number类，Integer类，其中Number是Integer的父类\n" +
                "\n" +
                "~~~java\n" +
                "public static void main(String[] args) {\n" +
                "    Collection<Integer> list1 = new ArrayList<Integer>();\n" +
                "    Collection<String> list2 = new ArrayList<String>();\n" +
                "    Collection<Number> list3 = new ArrayList<Number>();\n" +
                "    Collection<Object> list4 = new ArrayList<Object>();\n" +
                "    \n" +
                "    getElement(list1);\n" +
                "    getElement(list2);//报错\n" +
                "    getElement(list3);\n" +
                "    getElement(list4);//报错\n" +
                "  \n" +
                "    getElement2(list1);//报错\n" +
                "    getElement2(list2);//报错\n" +
                "    getElement2(list3);\n" +
                "    getElement2(list4);\n" +
                "  \n" +
                "}\n" +
                "// 泛型的上限：此时的泛型?，必须是Number类型或者Number类型的子类\n" +
                "public static void getElement1(Collection<? extends Number> coll){}\n" +
                "// 泛型的下限：此时的泛型?，必须是Number类型或者Number类型的父类\n" +
                "public static void getElement2(Collection<? super Number> coll){}\n" +
                "~~~\n" +
                "\n" +
                "# 第四章 集合综合案例\n" +
                "\n" +
                "## 4.1 案例介绍\n" +
                "\n" +
                "按照斗地主的规则，完成洗牌发牌的动作。\n" +
                "具体规则：\n" +
                "\n" +
                "使用54张牌打乱顺序,三个玩家参与游戏，三人交替摸牌，每人17张牌，最后三张留作底牌。\n" +
                "\n" +
                "## 4.2 案例分析\n" +
                "\n" +
                "* 准备牌：\n" +
                "\n" +
                "  牌可以设计为一个ArrayList<String>,每个字符串为一张牌。\n" +
                "  每张牌由花色数字两部分组成，我们可以使用花色集合与数字集合嵌套迭代完成每张牌的组装。\n" +
                "  牌由Collections类的shuffle方法进行随机排序。\n" +
                "\n" +
                "* 发牌\n" +
                "\n" +
                "  将每个人以及底牌设计为ArrayList<String>,将最后3张牌直接存放于底牌，剩余牌通过对3取模依次发牌。\n" +
                "\n" +
                "\n" +
                "* 看牌\n" +
                "\n" +
                "  直接打印每个集合。\n" +
                "\n" +
                "## 4.3 代码实现\n" +
                "\n" +
                "~~~java\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.Collections;\n" +
                "\n" +
                "public class Poker {\n" +
                "    public static void main(String[] args) {\n" +
                "        /*\n" +
                "        * 1: 准备牌操作\n" +
                "        */\n" +
                "        //1.1 创建牌盒 将来存储牌面的 \n" +
                "        ArrayList<String> pokerBox = new ArrayList<String>();\n" +
                "        //1.2 创建花色集合\n" +
                "        ArrayList<String> colors = new ArrayList<String>();\n" +
                "\n" +
                "        //1.3 创建数字集合\n" +
                "        ArrayList<String> numbers = new ArrayList<String>();\n" +
                "\n" +
                "        //1.4 分别给花色 以及 数字集合添加元素\n" +
                "        colors.add(\"♥\");\n" +
                "        colors.add(\"♦\");\n" +
                "        colors.add(\"♠\");\n" +
                "        colors.add(\"♣\");\n" +
                "\n" +
                "        for(int i = 2;i<=10;i++){\n" +
                "            numbers.add(i+\"\");\n" +
                "        }\n" +
                "        numbers.add(\"J\");\n" +
                "        numbers.add(\"Q\");\n" +
                "        numbers.add(\"K\");\n" +
                "        numbers.add(\"A\");\n" +
                "        //1.5 创造牌  拼接牌操作\n" +
                "        // 拿出每一个花色  然后跟每一个数字 进行结合  存储到牌盒中\n" +
                "        for (String color : colors) {\n" +
                "            //color每一个花色 \n" +
                "            //遍历数字集合\n" +
                "            for(String number : numbers){\n" +
                "                //结合\n" +
                "                String card = color+number;\n" +
                "                //存储到牌盒中\n" +
                "                pokerBox.add(card);\n" +
                "            }\n" +
                "        }\n" +
                "        //1.6大王小王\n" +
                "        pokerBox.add(\"小☺\");\n" +
                "        pokerBox.add(\"大☠\");\t  \n" +
                "        // System.out.println(pokerBox);\n" +
                "        //洗牌 是不是就是将  牌盒中 牌的索引打乱 \n" +
                "        // Collections类  工具类  都是 静态方法\n" +
                "        // shuffer方法   \n" +
                "        /*\n" +
                "         * static void shuffle(List<?> list) \n" +
                "         *     使用默认随机源对指定列表进行置换。 \n" +
                "         */\n" +
                "        //2:洗牌\n" +
                "        Collections.shuffle(pokerBox);\n" +
                "        //3 发牌\n" +
                "        //3.1 创建 三个 玩家集合  创建一个底牌集合\n" +
                "        ArrayList<String> player1 = new ArrayList<String>();\n" +
                "        ArrayList<String> player2 = new ArrayList<String>();\n" +
                "        ArrayList<String> player3 = new ArrayList<String>();\n" +
                "        ArrayList<String> dipai = new ArrayList<String>();\t  \n" +
                "\n" +
                "        //遍历 牌盒  必须知道索引   \n" +
                "        for(int i = 0;i<pokerBox.size();i++){\n" +
                "            //获取 牌面\n" +
                "            String card = pokerBox.get(i);\n" +
                "            //留出三张底牌 存到 底牌集合中\n" +
                "            if(i>=51){//存到底牌集合中\n" +
                "                dipai.add(card);\n" +
                "            } else {\n" +
                "                //玩家1   %3  ==0\n" +
                "                if(i%3==0){\n" +
                "                  \tplayer1.add(card);\n" +
                "                }else if(i%3==1){//玩家2\n" +
                "                  \tplayer2.add(card);\n" +
                "                }else{//玩家3\n" +
                "                  \tplayer3.add(card);\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "        //看看\n" +
                "        System.out.println(\"令狐冲：\"+player1);\n" +
                "        System.out.println(\"田伯光：\"+player2);\n" +
                "        System.out.println(\"绿竹翁：\"+player3);\n" +
                "        System.out.println(\"底牌：\"+dipai);  \n" +
                "\t}\n" +
                "}\n" +
                "~~~\n" +
                "\n";
        contents[2] = "# day03 【List、Set、数据结构、Collections】\n" +
                "\n" +
                "## 主要内容\n" +
                "\n" +
                "- 数据结构\n" +
                "- List集合\n" +
                "- Set集合\n" +
                "- Collections\n" +
                "\n" +
                "## 教学目标\n" +
                "\n" +
                "- [ ] 能够说出List集合特点\n" +
                "- [ ] 能够说出常见的数据结构\n" +
                "- [ ] 能够说出数组结构特点\n" +
                "- [ ] 能够说出栈结构特点\n" +
                "- [ ] 能够说出队列结构特点\n" +
                "- [ ] 能够说出单向链表结构特点\n" +
                "- [ ] 能够说出Set集合的特点\n" +
                "- [ ] 能够说出哈希表的特点\n" +
                "- [ ] 使用HashSet集合存储自定义元素\n" +
                "- [ ] 能够说出可变参数的格式\n" +
                "- [ ] 能够使用集合工具类\n" +
                "- [ ] 能够使用Comparator比较器进行排序\n" +
                "\n" +
                "# 第一章 数据结构\n" +
                "\n" +
                "## 2.1 数据结构有什么用？\n" +
                "\n" +
                "当你用着java里面的容器类很爽的时候，你有没有想过，怎么ArrayList就像一个无限扩充的数组，也好像链表之类的。好用吗？好用，这就是数据结构的用处，只不过你在不知不觉中使用了。\n" +
                "\n" +
                "现实世界的存储，我们使用的工具和建模。每种数据结构有自己的优点和缺点，想想如果Google的数据用的是数组的存储，我们还能方便地查询到所需要的数据吗？而算法，在这么多的数据中如何做到最快的插入，查找，删除，也是在追求更快。\n" +
                "\n" +
                "我们java是面向对象的语言，就好似自动档轿车，C语言好似手动档吉普。数据结构呢？是变速箱的工作原理。你完全可以不知道变速箱怎样工作，就把自动档的车子从 A点 开到 B点，而且未必就比懂得的人慢。写程序这件事，和开车一样，经验可以起到很大作用，但如果你不知道底层是怎么工作的，就永远只能开车，既不会修车，也不能造车。当然了，数据结构内容比较多，细细的学起来也是相对费功夫的，不可能达到一蹴而就。我们将常见的数据结构：堆栈、队列、数组、链表和红黑树 这几种给大家介绍一下，作为数据结构的入门，了解一下它们的特点即可。\n" +
                "\n" +
                "![](img\\数据结构比喻.png)\n" +
                "\n" +
                "## 2.2 常见的数据结构\n" +
                "\n" +
                "数据存储的常用结构有：栈、队列、数组、链表和红黑树。我们分别来了解一下：\n" +
                "\n" +
                "#### 栈\n" +
                "\n" +
                "* **栈**：**stack**,又称堆栈，它是运算受限的线性表，其限制是仅允许在标的一端进行插入和删除操作，不允许在其他任何位置进行添加、查找、删除等操作。\n" +
                "\n" +
                "简单的说：采用该结构的集合，对元素的存取有如下的特点\n" +
                "\n" +
                "* 先进后出（即，存进去的元素，要在后它后面的元素依次取出后，才能取出该元素）。例如，子弹压进弹夹，先压进去的子弹在下面，后压进去的子弹在上面，当开枪时，先弹出上面的子弹，然后才能弹出下面的子弹。\n" +
                "\n" +
                "* 栈的入口、出口的都是栈的顶端位置。\n" +
                "\n" +
                "  ![](img\\堆栈.png)\n" +
                "\n" +
                "这里两个名词需要注意：\n" +
                "\n" +
                "* **压栈**：就是存元素。即，把元素存储到栈的顶端位置，栈中已有元素依次向栈底方向移动一个位置。\n" +
                "* **弹栈**：就是取元素。即，把栈的顶端位置元素取出，栈中已有元素依次向栈顶方向移动一个位置。\n" +
                "\n" +
                "\n" +
                "#### 队列\n" +
                "\n" +
                "* **队列**：**queue**,简称队，它同堆栈一样，也是一种运算受限的线性表，其限制是仅允许在表的一端进行插入，而在表的另一端进行删除。\n" +
                "\n" +
                "简单的说，采用该结构的集合，对元素的存取有如下的特点：\n" +
                "\n" +
                "* 先进先出（即，存进去的元素，要在后它前面的元素依次取出后，才能取出该元素）。例如，小火车过山洞，车头先进去，车尾后进去；车头先出来，车尾后出来。\n" +
                "* 队列的入口、出口各占一侧。例如，下图中的左侧为入口，右侧为出口。\n" +
                "\n" +
                "![](img\\队列图.bmp)\n" +
                "\n" +
                "#### 数组\n" +
                "\n" +
                "* **数组**:**Array**,是有序的元素序列，数组是在内存中开辟一段连续的空间，并在此空间存放元素。就像是一排出租屋，有100个房间，从001到100每个房间都有固定编号，通过编号就可以快速找到租房子的人。\n" +
                "\n" +
                "简单的说,采用该结构的集合，对元素的存取有如下的特点：\n" +
                "\n" +
                "*  查找元素快：通过索引，可以快速访问指定位置的元素\n" +
                "\n" +
                "   ![](img/数组查询快.png)\n" +
                "\n" +
                "*  增删元素慢\n" +
                "  * **指定索引位置增加元素**：需要创建一个新数组，将指定新元素存储在指定索引位置，再把原数组元素根据索引，复制到新数组对应索引的位置。如下图![](img/数组添加.png)\n" +
                "  * **指定索引位置删除元素：**需要创建一个新数组，把原数组元素根据索引，复制到新数组对应索引的位置，原数组中指定索引位置元素不复制到新数组中。如下图![](img/数组删除.png)\n" +
                "\n" +
                "\n" +
                "\n" +
                "####  链表\n" +
                "\n" +
                "* **链表**:**linked list**,由一系列结点node（链表中每一个元素称为结点）组成，结点可以在运行时i动态生成。每个结点包括两个部分：一个是存储数据元素的数据域，另一个是存储下一个结点地址的指针域。我们常说的链表结构有单向链表与双向链表，那么这里给大家介绍的是**单向链表**。\n" +
                "\n" +
                "  ![](img\\单链表结构特点.png)\n" +
                "\n" +
                "简单的说，采用该结构的集合，对元素的存取有如下的特点：\n" +
                "\n" +
                "* 多个结点之间，通过地址进行连接。例如，多个人手拉手，每个人使用自己的右手拉住下个人的左手，依次类推，这样多个人就连在一起了。\n" +
                "\n" +
                "  ![](img\\单链表结构.png)\n" +
                "\n" +
                "* 查找元素慢：想查找某个元素，需要通过连接的节点，依次向后查找指定元素\n" +
                "\n" +
                "* 增删元素快：\n" +
                "\n" +
                "  *  增加元素：只需要修改连接下个元素的地址即可。\n" +
                "\n" +
                "    ![](img\\增加结点.png)\n" +
                "\n" +
                "  *  删除元素：只需要修改连接下个元素的地址即可。\n" +
                "\n" +
                "    ![](img\\删除结点.bmp)\n" +
                "\n" +
                "#### 红黑树\n" +
                "\n" +
                "* **二叉树**：**binary tree** ,是每个结点不超过2的有序**树（tree）** 。\n" +
                "\n" +
                "简单的理解，就是一种类似于我们生活中树的结构，只不过每个结点上都最多只能有两个子结点。\n" +
                "\n" +
                "二叉树是每个节点最多有两个子树的树结构。顶上的叫根结点，两边被称作“左子树”和“右子树”。\n" +
                "\n" +
                "如图：\n" +
                "\n" +
                "![](img\\二叉树.bmp)\n" +
                "\n" +
                "我们要说的是二叉树的一种比较有意思的叫做**红黑树**，红黑树本身就是一颗二叉查找树，将节点插入后，该树仍然是一颗二叉查找树。也就意味着，树的键值仍然是有序的。\n" +
                "\n" +
                "红黑树的约束:\n" +
                "\n" +
                "1. 节点可以是红色的或者黑色的\n" +
                "\n" +
                "\n" +
                "2. 根节点是黑色的\n" +
                "\n" +
                "\n" +
                "3. 叶子节点(特指空节点)是黑色的\n" +
                "4. 每个红色节点的子节点都是黑色的\n" +
                "5. 任何一个节点到其每一个叶子节点的所有路径上黑色节点数相同\n" +
                "\n" +
                "红黑树的特点:\n" +
                "\n" +
                "\u200B\t速度特别快,趋近平衡树,查找叶子元素最少和最多次数不多于二倍\n" +
                "\n" +
                "# 第二章 List集合\n" +
                "\n" +
                "我们掌握了Collection接口的使用后，再来看看Collection接口中的子类，他们都具备那些特性呢？\n" +
                "\n" +
                "接下来，我们一起学习Collection中的常用几个子类（`java.util.List`集合、`java.util.Set`集合）。\n" +
                "\n" +
                "## 1.1 List接口介绍\n" +
                "\n" +
                "`java.util.List`接口继承自`Collection`接口，是单列集合的一个重要分支，习惯性地会将实现了`List`接口的对象称为List集合。在List集合中允许出现重复的元素，所有的元素是以一种线性方式进行存储的，在程序中可以通过索引来访问集合中的指定元素。另外，List集合还有一个特点就是元素有序，即元素的存入顺序和取出顺序一致。\n" +
                "\n" +
                "看完API，我们总结一下：\n" +
                "\n" +
                "List接口特点：\n" +
                "\n" +
                "1. 它是一个元素存取有序的集合。例如，存元素的顺序是11、22、33。那么集合中，元素的存储就是按照11、22、33的顺序完成的）。\n" +
                "2. 它是一个带有索引的集合，通过索引就可以精确的操作集合中的元素（与数组的索引是一个道理）。\n" +
                "3. 集合中可以有重复的元素，通过元素的equals方法，来比较是否为重复的元素。\n" +
                "\n" +
                "> tips:我们在基础班的时候已经学习过List接口的子类java.util.ArrayList类，该类中的方法都是来自List中定义。\n" +
                "\n" +
                "## 1.2 List接口中常用方法\n" +
                "\n" +
                "List作为Collection集合的子接口，不但继承了Collection接口中的全部方法，而且还增加了一些根据元素索引来操作集合的特有方法，如下：\n" +
                "\n" +
                "- `public void add(int index, E element)`: 将指定的元素，添加到该集合中的指定位置上。\n" +
                "- `public E get(int index)`:返回集合中指定位置的元素。\n" +
                "- `public E remove(int index)`: 移除列表中指定位置的元素, 返回的是被移除的元素。\n" +
                "- `public E set(int index, E element)`:用指定元素替换集合中指定位置的元素,返回值的更新前的元素。\n" +
                "\n" +
                "List集合特有的方法都是跟索引相关，我们在基础班都学习过，那么我们再来复习一遍吧：\n" +
                "\n" +
                "```java\n" +
                "public class ListDemo {\n" +
                "    public static void main(String[] args) {\n" +
                "\t\t// 创建List集合对象\n" +
                "    \tList<String> list = new ArrayList<String>();\n" +
                "    \t\n" +
                "    \t// 往 尾部添加 指定元素\n" +
                "    \tlist.add(\"图图\");\n" +
                "    \tlist.add(\"小美\");\n" +
                "    \tlist.add(\"不高兴\");\n" +
                "    \t\n" +
                "    \tSystem.out.println(list);\n" +
                "    \t// add(int index,String s) 往指定位置添加\n" +
                "    \tlist.add(1,\"没头脑\");\n" +
                "    \t\n" +
                "    \tSystem.out.println(list);\n" +
                "    \t// String remove(int index) 删除指定位置元素  返回被删除元素\n" +
                "    \t// 删除索引位置为2的元素 \n" +
                "    \tSystem.out.println(\"删除索引位置为2的元素\");\n" +
                "    \tSystem.out.println(list.remove(2));\n" +
                "    \t\n" +
                "    \tSystem.out.println(list);\n" +
                "    \t\n" +
                "    \t// String set(int index,String s)\n" +
                "    \t// 在指定位置 进行 元素替代（改） \n" +
                "    \t// 修改指定位置元素\n" +
                "    \tlist.set(0, \"三毛\");\n" +
                "    \tSystem.out.println(list);\n" +
                "    \t\n" +
                "    \t// String get(int index)  获取指定位置元素\n" +
                "    \t\n" +
                "    \t// 跟size() 方法一起用  来 遍历的 \n" +
                "    \tfor(int i = 0;i<list.size();i++){\n" +
                "    \t\tSystem.out.println(list.get(i));\n" +
                "    \t}\n" +
                "    \t//还可以使用增强for\n" +
                "    \tfor (String string : list) {\n" +
                "\t\t\tSystem.out.println(string);\n" +
                "\t\t}  \t\n" +
                "\t}\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "# 第三章 List的子类\n" +
                "\n" +
                "## 3.1 ArrayList集合\n" +
                "\n" +
                "`java.util.ArrayList`集合数据存储的结构是数组结构。元素增删慢，查找快，由于日常开发中使用最多的功能为查询数据、遍历数据，所以`ArrayList`是最常用的集合。\n" +
                "\n" +
                "许多程序员开发时非常随意地使用ArrayList完成任何需求，并不严谨，这种用法是不提倡的。\n" +
                "\n" +
                "## 3.2 LinkedList集合\n" +
                "\n" +
                "`java.util.LinkedList`集合数据存储的结构是链表结构。方便元素添加、删除的集合。\n" +
                "\n" +
                "> LinkedList是一个双向链表，那么双向链表是什么样子的呢，我们用个图了解下\n" +
                "\n" +
                "![](img\\双向链表.png)\n" +
                "\n" +
                "实际开发中对一个集合元素的添加与删除经常涉及到首尾操作，而LinkedList提供了大量首尾操作的方法。这些方法我们作为了解即可：\n" +
                "\n" +
                "* `public void addFirst(E e)`:将指定元素插入此列表的开头。\n" +
                "* `public void addLast(E e)`:将指定元素添加到此列表的结尾。\n" +
                "* `public E getFirst()`:返回此列表的第一个元素。\n" +
                "* `public E getLast()`:返回此列表的最后一个元素。\n" +
                "* `public E removeFirst()`:移除并返回此列表的第一个元素。\n" +
                "* `public E removeLast()`:移除并返回此列表的最后一个元素。\n" +
                "* `public E pop()`:从此列表所表示的堆栈处弹出一个元素。\n" +
                "* `public void push(E e)`:将元素推入此列表所表示的堆栈。\n" +
                "* `public boolean isEmpty()`：如果列表不包含元素，则返回true。\n" +
                "\n" +
                "LinkedList是List的子类，List中的方法LinkedList都是可以使用，这里就不做详细介绍，我们只需要了解LinkedList的特有方法即可。在开发时，LinkedList集合也可以作为堆栈，队列的结构使用。（了解即可）\n" +
                "\n" +
                "方法演示：\n" +
                "\n" +
                "~~~java\n" +
                "public class LinkedListDemo {\n" +
                "    public static void main(String[] args) {\n" +
                "        LinkedList<String> link = new LinkedList<String>();\n" +
                "        //添加元素\n" +
                "        link.addFirst(\"abc1\");\n" +
                "        link.addFirst(\"abc2\");\n" +
                "        link.addFirst(\"abc3\");\n" +
                "        System.out.println(link);\n" +
                "        // 获取元素\n" +
                "        System.out.println(link.getFirst());\n" +
                "        System.out.println(link.getLast());\n" +
                "        // 删除元素\n" +
                "        System.out.println(link.removeFirst());\n" +
                "        System.out.println(link.removeLast());\n" +
                "\n" +
                "        while (!link.isEmpty()) { //判断集合是否为空\n" +
                "            System.out.println(link.pop()); //弹出集合中的栈顶元素\n" +
                "        }\n" +
                "\n" +
                "        System.out.println(link);\n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "## 3.2 Vector集合\n" +
                "\n" +
                "是jdk1.0就有的单线程的单列集合，性能不如ArrayList（多线程，性能较好）；早期没有迭代器的时候，迭代Vector集合使用elements方法，会返回Enumeration，可以通过Enumeration迭代出集合中的元素；早期使用的是addElement()添加元素。在jdk1.2有了List接口和iterator接口后vector也实现了这两个接口，能够实现对应的方法。\n" +
                "\n" +
                "# 第四章 Set接口\n" +
                "\n" +
                "`java.util.Set`接口和`java.util.List`接口一样，同样继承自`Collection`接口，它与`Collection`接口中的方法基本一致，并没有对`Collection`接口进行功能上的扩充，只是比`Collection`接口更加严格了。与`List`接口不同的是，`Set`接口中元素无序，并且都会以某种规则保证存入的元素不出现重复。\n" +
                "\n" +
                "`Set`集合有多个子类，这里我们介绍其中的`java.util.HashSet`、`java.util.LinkedHashSet`这两个集合。\n" +
                "\n" +
                "> tips:Set集合取出元素的方式可以采用：迭代器、增强for。\n" +
                "\n" +
                "## 3.1 HashSet集合介绍\n" +
                "\n" +
                "`java.util.HashSet`是`Set`接口的一个实现类，它所存储的元素是不可重复的，并且元素都是无序的(即存取顺序不一致)。`java.util.HashSet`底层的实现其实是一个`java.util.HashMap`支持，由于我们暂时还未学习，先做了解。\n" +
                "\n" +
                "`HashSet`是根据对象的哈希值来确定元素在集合中的存储位置，因此具有良好的存取和查找性能。保证元素唯一性的方式依赖于：`hashCode`与`equals`方法。\n" +
                "\n" +
                "我们先来使用一下Set集合存储，看下现象，再进行原理的讲解:\n" +
                "\n" +
                "~~~java\n" +
                "public class HashSetDemo {\n" +
                "    public static void main(String[] args) {\n" +
                "        //创建 Set集合\n" +
                "        HashSet<String>  set = new HashSet<String>();\n" +
                "\n" +
                "        //添加元素\n" +
                "        set.add(new String(\"cba\"));\n" +
                "        set.add(\"abc\");\n" +
                "        set.add(\"bac\"); \n" +
                "        set.add(\"cba\");  \n" +
                "        //遍历\n" +
                "        for (String name : set) {\n" +
                "            System.out.println(name);\n" +
                "        }\n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "输出结果如下，说明集合中不能存储重复元素：\n" +
                "\n" +
                "~~~\n" +
                "cba\n" +
                "abc\n" +
                "bac\n" +
                "~~~\n" +
                "\n" +
                "> tips:根据结果我们发现字符串\"cba\"只存储了一个，也就是说重复的元素set集合不存储。\n" +
                "\n" +
                "## 2.2  HashSet集合存储数据的结构（哈希表）\n" +
                "\n" +
                "什么是哈希表呢？\n" +
                "\n" +
                "在**JDK1.8**之前，哈希表底层采用数组+链表实现，即使用链表处理冲突，同一hash值的链表都存储在一个链表里。但是当位于一个桶中的元素较多，即hash值相等的元素较多时，通过key值依次查找的效率较低。而JDK1.8中，哈希表存储采用数组+链表+红黑树实现，当链表长度超过阈值（8）时，将链表转换为红黑树，这样大大减少了查找时间。\n" +
                "\n" +
                "简单的来说，哈希表是由数组+链表+红黑树（JDK1.8增加了红黑树部分）实现的，如下图所示。![](img\\哈希表.png)\n" +
                "\n" +
                "看到这张图就有人要问了，这个是怎么存储的呢？\n" +
                "\n" +
                "为了方便大家的理解我们结合一个存储流程图来说明一下：\n" +
                "\n" +
                "![](img\\哈希流程图.png)\n" +
                "\n" +
                "总而言之，**JDK1.8**引入红黑树大程度优化了HashMap的性能，那么对于我们来讲保证HashSet集合元素的唯一，其实就是根据对象的hashCode和equals方法来决定的。如果我们往集合中存放自定义的对象，那么保证其唯一，就必须复写hashCode和equals方法建立属于当前对象的比较方式。\n" +
                "\n" +
                "## 2.3  HashSet存储自定义类型元素\n" +
                "\n" +
                "给HashSet中存放自定义类型元素时，需要重写对象中的hashCode和equals方法，建立自己的比较方式，才能保证HashSet集合中的对象唯一\n" +
                "\n" +
                "创建自定义Student类\n" +
                "\n" +
                "~~~java\n" +
                "public class Student {\n" +
                "    private String name;\n" +
                "    private int age;\n" +
                "\n" +
                "    public Student() {\n" +
                "    }\n" +
                "\n" +
                "    public Student(String name, int age) {\n" +
                "        this.name = name;\n" +
                "        this.age = age;\n" +
                "    }\n" +
                "\n" +
                "    public String getName() {\n" +
                "        return name;\n" +
                "    }\n" +
                "\n" +
                "    public void setName(String name) {\n" +
                "        this.name = name;\n" +
                "    }\n" +
                "\n" +
                "    public int getAge() {\n" +
                "        return age;\n" +
                "    }\n" +
                "\n" +
                "    public void setAge(int age) {\n" +
                "        this.age = age;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public boolean equals(Object o) {\n" +
                "        if (this == o)\n" +
                "            return true;\n" +
                "        if (o == null || getClass() != o.getClass())\n" +
                "            return false;\n" +
                "        Student student = (Student) o;\n" +
                "        return age == student.age &&\n" +
                "               Objects.equals(name, student.name);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public int hashCode() {\n" +
                "        return Objects.hash(name, age);\n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "~~~java\n" +
                "public class HashSetDemo2 {\n" +
                "    public static void main(String[] args) {\n" +
                "        //创建集合对象   该集合中存储 Student类型对象\n" +
                "        HashSet<Student> stuSet = new HashSet<Student>();\n" +
                "        //存储 \n" +
                "        Student stu = new Student(\"于谦\", 43);\n" +
                "        stuSet.add(stu);\n" +
                "        stuSet.add(new Student(\"郭德纲\", 44));\n" +
                "        stuSet.add(new Student(\"于谦\", 43));\n" +
                "        stuSet.add(new Student(\"郭麒麟\", 23));\n" +
                "        stuSet.add(stu);\n" +
                "\n" +
                "        for (Student stu2 : stuSet) {\n" +
                "            System.out.println(stu2);\n" +
                "        }\n" +
                "    }\n" +
                "}\n" +
                "执行结果：\n" +
                "Student [name=郭德纲, age=44]\n" +
                "Student [name=于谦, age=43]\n" +
                "Student [name=郭麒麟, age=23]\n" +
                "~~~\n" +
                "\n" +
                "## 2.3 LinkedHashSet\n" +
                "\n" +
                "我们知道HashSet保证元素唯一，可是元素存放进去是没有顺序的，那么我们要保证有序，怎么办呢？\n" +
                "\n" +
                "在HashSet下面有一个子类`java.util.LinkedHashSet`，它是链表和哈希表组合的一个数据存储结构。\n" +
                "\n" +
                "演示代码如下:\n" +
                "\n" +
                "~~~java\n" +
                "public class LinkedHashSetDemo {\n" +
                "\tpublic static void main(String[] args) {\n" +
                "\t\tSet<String> set = new LinkedHashSet<String>();\n" +
                "\t\tset.add(\"bbb\");\n" +
                "\t\tset.add(\"aaa\");\n" +
                "\t\tset.add(\"abc\");\n" +
                "\t\tset.add(\"bbc\");\n" +
                "        Iterator<String> it = set.iterator();\n" +
                "\t\twhile (it.hasNext()) {\n" +
                "\t\t\tSystem.out.println(it.next());\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}\n" +
                "结果：\n" +
                "  bbb\n" +
                "  aaa\n" +
                "  abc\n" +
                "  bbc\n" +
                "~~~\n" +
                "\n" +
                "## 1.9  可变参数\n" +
                "\n" +
                "在**JDK1.5**之后，如果我们定义一个方法需要接受多个参数，并且多个参数类型一致，我们可以对其简化成如下格式：\n" +
                "\n" +
                "```\n" +
                "修饰符 返回值类型 方法名(参数类型... 形参名){  }\n" +
                "```\n" +
                "\n" +
                "其实这个书写完全等价与\n" +
                "\n" +
                "```\n" +
                "修饰符 返回值类型 方法名(参数类型[] 形参名){  }\n" +
                "```\n" +
                "\n" +
                "只是后面这种定义，在调用时必须传递数组，而前者可以直接传递数据即可。\n" +
                "\n" +
                "**JDK1.5**以后。出现了简化操作。**...** 用在参数上，称之为可变参数。\n" +
                "\n" +
                "同样是代表数组，但是在调用这个带有可变参数的方法时，不用创建数组(这就是简单之处)，直接将数组中的元素作为实际参数进行传递，其实编译成的class文件，将这些元素先封装到一个数组中，在进行传递。这些动作都在编译.class文件时，自动完成了。\n" +
                "\n" +
                "代码演示：    \n" +
                "\n" +
                "```java\n" +
                "public class ChangeArgs {\n" +
                "    public static void main(String[] args) {\n" +
                "        int[] arr = { 1, 4, 62, 431, 2 };\n" +
                "        int sum = getSum(arr);\n" +
                "        System.out.println(sum);\n" +
                "        //  6  7  2 12 2121\n" +
                "        // 求 这几个元素和 6  7  2 12 2121\n" +
                "        int sum2 = getSum(6, 7, 2, 12, 2121);\n" +
                "        System.out.println(sum2);\n" +
                "    }\n" +
                "\n" +
                "    /*\n" +
                "     * 完成数组  所有元素的求和 原始写法\n" +
                "     \n" +
                "      public static int getSum(int[] arr){\n" +
                "        int sum = 0;\n" +
                "        for(int a : arr){\n" +
                "            sum += a;\n" +
                "        }\n" +
                "        \n" +
                "        return sum;\n" +
                "      }\n" +
                "    */\n" +
                "    //可变参数写法\n" +
                "    public static int getSum(int... arr) {\n" +
                "        int sum = 0;\n" +
                "        for (int a : arr) {\n" +
                "            sum += a;\n" +
                "        }\n" +
                "        return sum;\n" +
                "    }\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "> tips: 上述add方法在同一个类中，只能存在一个。因为会发生调用的不确定性\n" +
                ">\n" +
                "> 注意：如果在方法书写时，这个方法拥有多参数，参数中包含可变参数，可变参数一定要写在参数列表的末尾位置。\n" +
                "\n" +
                "\n" +
                "\n" +
                "# 第五章  Collections\n" +
                "\n" +
                "## 2.1 常用功能\n" +
                "\n" +
                "* `java.utils.Collections`是集合工具类，用来对集合进行操作。部分方法如下：\n" +
                "\n" +
                "- `public static <T> boolean addAll(Collection<T> c, T... elements)  `:往集合中添加一些元素。\n" +
                "- `public static void shuffle(List<?> list) 打乱顺序`:打乱集合顺序。\n" +
                "- `public static <T> void sort(List<T> list)`:将集合中元素按照默认规则排序。\n" +
                "- `public static <T> void sort(List<T> list，Comparator<? super T> )`:将集合中元素按照指定规则排序。\n" +
                "\n" +
                "代码演示：\n" +
                "\n" +
                "```java\n" +
                "public class CollectionsDemo {\n" +
                "    public static void main(String[] args) {\n" +
                "        ArrayList<Integer> list = new ArrayList<Integer>();\n" +
                "        //原来写法\n" +
                "        //list.add(12);\n" +
                "        //list.add(14);\n" +
                "        //list.add(15);\n" +
                "        //list.add(1000);\n" +
                "        //采用工具类 完成 往集合中添加元素  \n" +
                "        Collections.addAll(list, 5, 222, 1，2);\n" +
                "        System.out.println(list);\n" +
                "        //排序方法 \n" +
                "        Collections.sort(list);\n" +
                "        System.out.println(list);\n" +
                "    }\n" +
                "}\n" +
                "结果：\n" +
                "[5, 222, 1, 2]\n" +
                "[1, 2, 5, 222]\n" +
                "```\n" +
                "\n" +
                "代码演示之后 ，发现我们的集合按照顺序进行了排列，可是这样的顺序是采用默认的顺序，如果想要指定顺序那该怎么办呢？\n" +
                "\n" +
                "我们发现还有个方法没有讲，`public static <T> void sort(List<T> list，Comparator<? super T> )`:将集合中元素按照指定规则排序。接下来讲解一下指定规则的排列。\n" +
                "\n" +
                "## 2.2 Comparator比较器\n" +
                "\n" +
                "我们还是先研究这个方法\n" +
                "\n" +
                "`public static <T> void sort(List<T> list)`:将集合中元素按照默认规则排序。\n" +
                "\n" +
                "不过这次存储的是字符串类型。\n" +
                "\n" +
                "```java\n" +
                "public class CollectionsDemo2 {\n" +
                "    public static void main(String[] args) {\n" +
                "        ArrayList<String>  list = new ArrayList<String>();\n" +
                "        list.add(\"cba\");\n" +
                "        list.add(\"aba\");\n" +
                "        list.add(\"sba\");\n" +
                "        list.add(\"nba\");\n" +
                "        //排序方法\n" +
                "        Collections.sort(list);\n" +
                "        System.out.println(list);\n" +
                "    }\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "结果：\n" +
                "\n" +
                "```\n" +
                "[aba, cba, nba, sba]\n" +
                "```\n" +
                "\n" +
                "我们使用的是默认的规则完成字符串的排序，那么默认规则是怎么定义出来的呢？\n" +
                "\n" +
                "说到排序了，简单的说就是两个对象之间比较大小，那么在JAVA中提供了两种比较实现的方式，一种是比较死板的采用`java.lang.Comparable`接口去实现，一种是灵活的当我需要做排序的时候在去选择的`java.util.Comparator`接口完成。\n" +
                "\n" +
                "那么我们采用的`public static <T> void sort(List<T> list)`这个方法完成的排序，实际上要求了被排序的类型需要实现Comparable接口完成比较的功能，在String类型上如下：\n" +
                "\n" +
                "```java\n" +
                "public final class String implements java.io.Serializable, Comparable<String>, CharSequence {\n" +
                "```\n" +
                "\n" +
                "String类实现了这个接口，并完成了比较规则的定义，但是这样就把这种规则写死了，那比如我想要字符串按照第一个字符降序排列，那么这样就要修改String的源代码，这是不可能的了，那么这个时候我们可以使用\n" +
                "\n" +
                "`public static <T> void sort(List<T> list，Comparator<? super T> )`方法灵活的完成，这个里面就涉及到了Comparator这个接口，位于位于java.util包下，排序是comparator能实现的功能之一,该接口代表一个比较器，比较器具有可比性！顾名思义就是做排序的，通俗地讲需要比较两个对象谁排在前谁排在后，那么比较的方法就是：\n" +
                "\n" +
                "* ` public int compare(String o1, String o2)`：比较其两个参数的顺序。\n" +
                "\n" +
                "  > 两个对象比较的结果有三种：大于，等于，小于。\n" +
                "  >\n" +
                "  > 如果要按照升序排序，\n" +
                "  > 则o1 小于o2，返回（负数），相等返回0，01大于02返回（正数）\n" +
                "  > 如果要按照降序排序\n" +
                "  > 则o1 小于o2，返回（正数），相等返回0，01大于02返回（负数）\n" +
                "\n" +
                "操作如下:\n" +
                "\n" +
                "```java\n" +
                "public class CollectionsDemo3 {\n" +
                "    public static void main(String[] args) {\n" +
                "        ArrayList<String> list = new ArrayList<String>();\n" +
                "        list.add(\"cba\");\n" +
                "        list.add(\"aba\");\n" +
                "        list.add(\"sba\");\n" +
                "        list.add(\"nba\");\n" +
                "        //排序方法  按照第一个单词的降序\n" +
                "        Collections.sort(list, new Comparator<String>() {\n" +
                "            @Override\n" +
                "            public int compare(String o1, String o2) {\n" +
                "                return o2.charAt(0) - o1.charAt(0);\n" +
                "            }\n" +
                "        });\n" +
                "        System.out.println(list);\n" +
                "    }\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "结果如下：\n" +
                "\n" +
                "```\n" +
                "[sba, nba, cba, aba]\n" +
                "```\n" +
                "\n" +
                "## 2.3 简述Comparable和Comparator两个接口的区别。\n" +
                "\n" +
                "**Comparable**：强行对实现它的每个类的对象进行整体排序。这种排序被称为类的自然排序，类的compareTo方法被称为它的自然比较方法。只能在类中实现compareTo()一次，不能经常修改类的代码实现自己想要的排序。实现此接口的对象列表（和数组）可以通过Collections.sort（和Arrays.sort）进行自动排序，对象可以用作有序映射中的键或有序集合中的元素，无需指定比较器。\n" +
                "\n" +
                "**Comparator**强行对某个对象进行整体排序。可以将Comparator 传递给sort方法（如Collections.sort或 Arrays.sort），从而允许在排序顺序上实现精确控制。还可以使用Comparator来控制某些数据结构（如有序set或有序映射）的顺序，或者为那些没有自然顺序的对象collection提供排序。\n" +
                "\n" +
                "## 2.4  练习\n" +
                "\n" +
                "创建一个学生类，存储到ArrayList集合中完成指定排序操作。\n" +
                "\n" +
                "Student 初始类\n" +
                "\n" +
                "~~~java\n" +
                "public class Student{\n" +
                "    private String name;\n" +
                "    private int age;\n" +
                "\n" +
                "    public Student() {\n" +
                "    }\n" +
                "\n" +
                "    public Student(String name, int age) {\n" +
                "        this.name = name;\n" +
                "        this.age = age;\n" +
                "    }\n" +
                "\n" +
                "    public String getName() {\n" +
                "        return name;\n" +
                "    }\n" +
                "\n" +
                "    public void setName(String name) {\n" +
                "        this.name = name;\n" +
                "    }\n" +
                "\n" +
                "    public int getAge() {\n" +
                "        return age;\n" +
                "    }\n" +
                "\n" +
                "    public void setAge(int age) {\n" +
                "        this.age = age;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public String toString() {\n" +
                "        return \"Student{\" +\n" +
                "               \"name='\" + name + '\\'' +\n" +
                "               \", age=\" + age +\n" +
                "               '}';\n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "测试类：\n" +
                "\n" +
                "~~~java\n" +
                "public class Demo {\n" +
                "\n" +
                "    public static void main(String[] args) {\n" +
                "        // 创建四个学生对象 存储到集合中\n" +
                "        ArrayList<Student> list = new ArrayList<Student>();\n" +
                "\n" +
                "        list.add(new Student(\"rose\",18));\n" +
                "        list.add(new Student(\"jack\",16));\n" +
                "        list.add(new Student(\"abc\",16));\n" +
                "        list.add(new Student(\"ace\",17));\n" +
                "        list.add(new Student(\"mark\",16));\n" +
                "\n" +
                "\n" +
                "        /*\n" +
                "          让学生 按照年龄排序 升序\n" +
                "         */\n" +
                "//        Collections.sort(list);//要求 该list中元素类型  必须实现比较器Comparable接口\n" +
                "\n" +
                "\n" +
                "        for (Student student : list) {\n" +
                "            System.out.println(student);\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "发现，当我们调用Collections.sort()方法的时候 程序报错了。\n" +
                "\n" +
                "原因：如果想要集合中的元素完成排序，那么必须要实现比较器Comparable接口。\n" +
                "\n" +
                "于是我们就完成了Student类的一个实现，如下：\n" +
                "\n" +
                "~~~java\n" +
                "public class Student implements Comparable<Student>{\n" +
                "    ....\n" +
                "    @Override\n" +
                "    public int compareTo(Student o) {\n" +
                "        return this.age-o.age;//升序\n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "再次测试，代码就OK 了效果如下：\n" +
                "\n" +
                "~~~java\n" +
                "Student{name='jack', age=16}\n" +
                "Student{name='abc', age=16}\n" +
                "Student{name='mark', age=16}\n" +
                "Student{name='ace', age=17}\n" +
                "Student{name='rose', age=18}\n" +
                "~~~\n" +
                "\n" +
                "## 2.5 扩展\n" +
                "\n" +
                "如果在使用的时候，想要独立的定义规则去使用 可以采用Collections.sort(List list,Comparetor<T> c)方式，自己定义规则：\n" +
                "\n" +
                "~~~java\n" +
                "Collections.sort(list, new Comparator<Student>() {\n" +
                "    @Override\n" +
                "    public int compare(Student o1, Student o2) {\n" +
                "        return o2.getAge()-o1.getAge();//以学生的年龄降序\n" +
                "    }\n" +
                "});\n" +
                "~~~\n" +
                "\n" +
                "效果：\n" +
                "\n" +
                "~~~java\n" +
                "Student{name='rose', age=18}\n" +
                "Student{name='ace', age=17}\n" +
                "Student{name='jack', age=16}\n" +
                "Student{name='abc', age=16}\n" +
                "Student{name='mark', age=16}\n" +
                "~~~\n" +
                "\n" +
                "\n" +
                "\n" +
                "如果想要规则更多一些，可以参考下面代码：\n" +
                "\n" +
                "~~~java\n" +
                "Collections.sort(list, new Comparator<Student>() {\n" +
                "            @Override\n" +
                "            public int compare(Student o1, Student o2) {\n" +
                "                // 年龄降序\n" +
                "                int result = o2.getAge()-o1.getAge();//年龄降序\n" +
                "\n" +
                "                if(result==0){//第一个规则判断完了 下一个规则 姓名的首字母 升序\n" +
                "                    result = o1.getName().charAt(0)-o2.getName().charAt(0);\n" +
                "                }\n" +
                "\n" +
                "                return result;\n" +
                "            }\n" +
                "        });\n" +
                "~~~\n" +
                "\n" +
                "效果如下：\n" +
                "\n" +
                "~~~\n" +
                "Student{name='rose', age=18}\n" +
                "Student{name='ace', age=17}\n" +
                "Student{name='abc', age=16}\n" +
                "Student{name='jack', age=16}\n" +
                "Student{name='mark', age=16}\n" +
                "~~~";
        contents[3] = "# day04 【Map】\n" +
                "\n" +
                "## 主要内容\n" +
                "\n" +
                "-  Map集合\n" +
                "\n" +
                "## 教学目标\n" +
                "\n" +
                "- [ ] 能够说出Map集合特点\n" +
                "- [ ] 使用Map集合添加方法保存数据\n" +
                "- [ ] 使用”键找值”的方式遍历Map集合\n" +
                "- [ ] 使用”键值对”的方式遍历Map集合\n" +
                "- [ ] 能够使用HashMap存储自定义键值对的数据\n" +
                "- [ ] 能够使用HashMap编写斗地主洗牌发牌案例\n" +
                "\n" +
                "# 第一章 Map集合\n" +
                "\n" +
                "## 1.1 概述\n" +
                "\n" +
                "现实生活中，我们常会看到这样的一种集合：IP地址与主机名，身份证号与个人，系统用户名与系统用户对象等，这种一一对应的关系，就叫做映射。Java提供了专门的集合类用来存放这种对象关系的对象，即`java.util.Map`接口。\n" +
                "\n" +
                "我们通过查看`Map`接口描述，发现`Map`接口下的集合与`Collection`接口下的集合，它们存储数据的形式不同，如下图。\n" +
                "\n" +
                "![](img\\Collection与Map.bmp)\n" +
                "\n" +
                "* `Collection`中的集合，元素是孤立存在的（理解为单身），向集合中存储元素采用一个个元素的方式存储。\n" +
                "* `Map`中的集合，元素是成对存在的(理解为夫妻)。每个元素由键与值两部分组成，通过键可以找对所对应的值。\n" +
                "* `Collection`中的集合称为单列集合，`Map`中的集合称为双列集合。\n" +
                "* 需要注意的是，`Map`中的集合不能包含重复的键，值可以重复；每个键只能对应一个值。\n" +
                "\n" +
                "## 1.2  Map常用子类\n" +
                "\n" +
                "通过查看Map接口描述，看到Map有多个子类，这里我们主要讲解常用的HashMap集合、LinkedHashMap集合。\n" +
                "\n" +
                "* **HashMap<K,V>**：存储数据采用的哈希表结构，元素的存取顺序不能保证一致。由于要保证键的唯一、不重复，需要重写键的hashCode()方法、equals()方法。\n" +
                "* **LinkedHashMap<K,V>**：HashMap下有个子类LinkedHashMap，存储数据采用的哈希表结构+链表结构。通过链表结构可以保证元素的存取顺序一致；通过哈希表结构可以保证的键的唯一、不重复，需要重写键的hashCode()方法、equals()方法。\n" +
                "\n" +
                "> tips：Map接口中的集合都有两个泛型变量<K,V>,在使用时，要为两个泛型变量赋予数据类型。两个泛型变量<K,V>的数据类型可以相同，也可以不同。\n" +
                ">\n" +
                "\n" +
                "## 1.3  Map接口中的常用方法\n" +
                "\n" +
                "Map接口中定义了很多方法，常用的如下：\n" +
                "\n" +
                "* `public V put(K key, V value)`:  把指定的键与指定的值添加到Map集合中，返回值是被替换的value值，如果key原本在map中就不存在，那么返回的是null。\n" +
                "* `public V remove(Object key)`: 把指定的键所对应的键值对元素 在Map集合中删除，返回被删除元素的值。\n" +
                "* `public V get(Object key)` 根据指定的键，在Map集合中获取对应的值。\n" +
                "* `boolean containsKey(Object key)  ` 判断集合中是否包含指定的键。\n" +
                "* `public Set<K> keySet()`: 获取Map集合中所有的键，存储到Set集合中。\n" +
                "* `public Set<Map.Entry<K,V>> entrySet()`: 获取到Map集合中所有的键值对对象的集合(Set集合)。\n" +
                "\n" +
                "Map接口的方法演示\n" +
                "\n" +
                "~~~java\n" +
                "public class MapDemo {\n" +
                "    public static void main(String[] args) {\n" +
                "        //创建 map对象\n" +
                "        HashMap<String, String>  map = new HashMap<String, String>();\n" +
                "\n" +
                "        //添加元素到集合\n" +
                "        map.put(\"黄晓明\", \"杨颖\");\n" +
                "        map.put(\"文章\", \"马伊琍\");\n" +
                "        map.put(\"邓超\", \"孙俪\");\n" +
                "        System.out.println(map);\n" +
                "\n" +
                "        //String remove(String key)\n" +
                "        System.out.println(map.remove(\"邓超\"));\n" +
                "        System.out.println(map);\n" +
                "\n" +
                "        // 想要查看 黄晓明的媳妇 是谁\n" +
                "        System.out.println(map.get(\"黄晓明\"));\n" +
                "        System.out.println(map.get(\"邓超\"));    \n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "> tips:\n" +
                ">\n" +
                "> 使用put方法时，若指定的键(key)在集合中没有，则没有这个键对应的值，返回null，并把指定的键值添加到集合中； \n" +
                ">\n" +
                "> 若指定的键(key)在集合中存在，则返回值为集合中键对应的值（该值为替换前的值），并把指定键所对应的值，替换成指定的新值。 \n" +
                "\n" +
                "## 1.4   Map集合遍历键找值方式\n" +
                "\n" +
                "键找值方式：即通过元素中的键，获取键所对应的值\n" +
                "\n" +
                "分析步骤：\n" +
                "\n" +
                "1. 获取Map中所有的键，由于键是唯一的，所以返回一个Set集合存储所有的键。方法提示:`keyset()`\n" +
                "2. 遍历键的Set集合，得到每一个键。\n" +
                "3. 根据键，获取键所对应的值。方法提示:`get(K key)`\n" +
                "\n" +
                "代码演示：\n" +
                "\n" +
                "~~~java\n" +
                "public class MapDemo01 {\n" +
                "    public static void main(String[] args) {\n" +
                "        //创建Map集合对象 \n" +
                "        HashMap<String, String> map = new HashMap<String,String>();\n" +
                "        //添加元素到集合 \n" +
                "        map.put(\"胡歌\", \"霍建华\");\n" +
                "        map.put(\"郭德纲\", \"于谦\");\n" +
                "        map.put(\"薛之谦\", \"大张伟\");\n" +
                "\n" +
                "        //获取所有的键  获取键集\n" +
                "        Set<String> keys = map.keySet();\n" +
                "        // 遍历键集 得到 每一个键\n" +
                "        for (String key : keys) {\n" +
                "          \t//key  就是键\n" +
                "            //获取对应值\n" +
                "            String value = map.get(key);\n" +
                "            System.out.println(key+\"的CP是：\"+value);\n" +
                "        }  \n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "遍历图解：\n" +
                "\n" +
                "![](img\\Map集合遍历方式一.bmp)\n" +
                "\n" +
                "## 1.5  Entry键值对对象\n" +
                "\n" +
                "我们已经知道，`Map`中存放的是两种对象，一种称为**key**(键)，一种称为**value**(值)，它们在在`Map`中是一一对应关系，这一对对象又称做`Map`中的一个`Entry(项)`。`Entry`将键值对的对应关系封装成了对象。即键值对对象，这样我们在遍历`Map`集合时，就可以从每一个键值对（`Entry`）对象中获取对应的键与对应的值。\n" +
                "\n" +
                " 既然Entry表示了一对键和值，那么也同样提供了获取对应键和对应值得方法：\n" +
                "\n" +
                "* `public K getKey()`：获取Entry对象中的键。\n" +
                "* `public V getValue()`：获取Entry对象中的值。\n" +
                "\n" +
                "在Map集合中也提供了获取所有Entry对象的方法：\n" +
                "\n" +
                "* `public Set<Map.Entry<K,V>> entrySet()`: 获取到Map集合中所有的键值对对象的集合(Set集合)。\n" +
                "\n" +
                "## 1.6 Map集合遍历键值对方式\n" +
                "\n" +
                "键值对方式：即通过集合中每个键值对(Entry)对象，获取键值对(Entry)对象中的键与值。\n" +
                "\n" +
                "操作步骤与图解：\n" +
                "\n" +
                "1.  获取Map集合中，所有的键值对(Entry)对象，以Set集合形式返回。方法提示:`entrySet()`。\n" +
                "\n" +
                "2.  遍历包含键值对(Entry)对象的Set集合，得到每一个键值对(Entry)对象。\n" +
                "3.  通过键值对(Entry)对象，获取Entry对象中的键与值。  方法提示:`getkey() getValue()`     \n" +
                "\n" +
                "~~~java\n" +
                "public class MapDemo02 {\n" +
                "    public static void main(String[] args) {\n" +
                "        // 创建Map集合对象 \n" +
                "        HashMap<String, String> map = new HashMap<String,String>();\n" +
                "        // 添加元素到集合 \n" +
                "        map.put(\"胡歌\", \"霍建华\");\n" +
                "        map.put(\"郭德纲\", \"于谦\");\n" +
                "        map.put(\"薛之谦\", \"大张伟\");\n" +
                "\n" +
                "        // 获取 所有的 entry对象  entrySet\n" +
                "        Set<Entry<String,String>> entrySet = map.entrySet();\n" +
                "\n" +
                "        // 遍历得到每一个entry对象\n" +
                "        for (Entry<String, String> entry : entrySet) {\n" +
                "           \t// 解析 \n" +
                "            String key = entry.getKey();\n" +
                "            String value = entry.getValue();  \n" +
                "            System.out.println(key+\"的CP是:\"+value);\n" +
                "        }\n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "遍历图解：\n" +
                "\n" +
                "![](img\\Map集合遍历方式二.bmp)\n" +
                "\n" +
                "> tips：Map集合不能直接使用迭代器或者foreach进行遍历。但是转成Set之后就可以使用了。\n" +
                ">\n" +
                "\n" +
                "## 1.7  HashMap存储自定义类型键值\n" +
                "\n" +
                "练习：每位学生（姓名，年龄）都有自己的家庭住址。那么，既然有对应关系，则将学生对象和家庭住址存储到map集合中。学生作为键, 家庭住址作为值。\n" +
                "\n" +
                "> 注意，学生姓名相同并且年龄相同视为同一名学生。\n" +
                ">\n" +
                "\n" +
                "编写学生类：\n" +
                "\n" +
                "~~~java\n" +
                "public class Student {\n" +
                "    private String name;\n" +
                "    private int age;\n" +
                "\n" +
                "    public Student() {\n" +
                "    }\n" +
                "\n" +
                "    public Student(String name, int age) {\n" +
                "        this.name = name;\n" +
                "        this.age = age;\n" +
                "    }\n" +
                "\n" +
                "    public String getName() {\n" +
                "        return name;\n" +
                "    }\n" +
                "\n" +
                "    public void setName(String name) {\n" +
                "        this.name = name;\n" +
                "    }\n" +
                "\n" +
                "    public int getAge() {\n" +
                "        return age;\n" +
                "    }\n" +
                "\n" +
                "    public void setAge(int age) {\n" +
                "        this.age = age;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public boolean equals(Object o) {\n" +
                "        if (this == o)\n" +
                "            return true;\n" +
                "        if (o == null || getClass() != o.getClass())\n" +
                "            return false;\n" +
                "        Student student = (Student) o;\n" +
                "        return age == student.age && Objects.equals(name, student.name);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public int hashCode() {\n" +
                "        return Objects.hash(name, age);\n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "编写测试类：\n" +
                "\n" +
                "~~~java \n" +
                "public class HashMapTest {\n" +
                "    public static void main(String[] args) {\n" +
                "        //1,创建Hashmap集合对象。\n" +
                "        Map<Student,String>map = new HashMap<Student,String>();\n" +
                "        //2,添加元素。\n" +
                "        map.put(newStudent(\"lisi\",28), \"上海\");\n" +
                "        map.put(newStudent(\"wangwu\",22), \"北京\");\n" +
                "        map.put(newStudent(\"zhaoliu\",24), \"成都\");\n" +
                "        map.put(newStudent(\"zhouqi\",25), \"广州\");\n" +
                "        map.put(newStudent(\"wangwu\",22), \"南京\");\n" +
                "        \n" +
                "        //3,取出元素。键找值方式\n" +
                "        Set<Student>keySet = map.keySet();\n" +
                "        for(Student key: keySet){\n" +
                "            Stringvalue = map.get(key);\n" +
                "            System.out.println(key.toString()+\".....\"+value);\n" +
                "        }\n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "* 当给HashMap中存放自定义对象时，如果自定义对象作为key存在，这时要保证对象唯一，必须复写对象的hashCode和equals方法(如果忘记，请回顾HashSet存放自定义对象)。\n" +
                "* 如果要保证map中存放的key和取出的顺序一致，可以使用`java.util.LinkedHashMap`集合来存放。\n" +
                "\n" +
                "## 1.8   LinkedHashMap\n" +
                "\n" +
                "我们知道HashMap保证成对元素唯一，并且查询速度很快，可是成对元素存放进去是没有顺序的，那么我们要保证有序，还要速度快怎么办呢？\n" +
                "\n" +
                "在HashMap下面有一个子类LinkedHashMap，它是链表和哈希表组合的一个数据存储结构。\n" +
                "\n" +
                "~~~java\n" +
                "public class LinkedHashMapDemo {\n" +
                "    public static void main(String[] args) {\n" +
                "        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();\n" +
                "        map.put(\"邓超\", \"孙俪\");\n" +
                "        map.put(\"李晨\", \"范冰冰\");\n" +
                "        map.put(\"刘德华\", \"朱丽倩\");\n" +
                "        Set<Entry<String, String>> entrySet = map.entrySet();\n" +
                "        for (Entry<String, String> entry : entrySet) {\n" +
                "            System.out.println(entry.getKey() + \"  \" + entry.getValue());\n" +
                "        }\n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "结果:\n" +
                "\n" +
                "~~~\n" +
                "邓超  孙俪\n" +
                "李晨  范冰冰\n" +
                "刘德华  朱丽倩\n" +
                "~~~\n" +
                "\n" +
                "## 1.9  HashTable\n" +
                "\n" +
                "是最早的双列集合，jdk1.0开始就有了，底层也是一个哈希表，是一个线程安全的集合，单线程集合意味着速度慢，key和value不能够存取null值。现已被HashMap取代使用，但HashTable的子类Properties人在被广泛使用，Properties是唯一一个与IO流相结合的集合。\n" +
                "\n" +
                "## 1.10 Map集合练习\n" +
                "\n" +
                "**需求：**\n" +
                "\n" +
                "计算一个字符串中每个字符出现次数。\n" +
                "\n" +
                "**分析：**\n" +
                "\n" +
                "1.  获取一个字符串对象\n" +
                "2.  创建一个Map集合，键代表字符，值代表次数。\n" +
                "3.  遍历字符串得到每个字符。\n" +
                "4.  判断Map中是否有该键。\n" +
                "5.  如果没有，第一次出现，存储次数为1；如果有，则说明已经出现过，获取到对应的值进行++，再次存储。     \n" +
                "6.  打印最终结果\n" +
                "\n" +
                "**代码：**\n" +
                "\n" +
                "~~~java\n" +
                "public class MapTest {\n" +
                "public static void main(String[] args) {\n" +
                "        //友情提示\n" +
                "        System.out.println(\"请录入一个字符串:\");\n" +
                "        String line = new Scanner(System.in).nextLine();\n" +
                "        // 定义 每个字符出现次数的方法\n" +
                "        findChar(line);\n" +
                "    }\n" +
                "    private static void findChar(String line) {\n" +
                "        //1:创建一个集合 存储  字符 以及其出现的次数\n" +
                "        HashMap<Character, Integer> map = new HashMap<Character, Integer>();\n" +
                "        //2:遍历字符串\n" +
                "        for (int i = 0; i < line.length(); i++) {\n" +
                "            char c = line.charAt(i);\n" +
                "            //判断 该字符 是否在键集中\n" +
                "            if (!map.containsKey(c)) {//说明这个字符没有出现过\n" +
                "                //那就是第一次\n" +
                "                map.put(c, 1);\n" +
                "            } else {\n" +
                "                //先获取之前的次数\n" +
                "                Integer count = map.get(c);\n" +
                "                //count++;\n" +
                "                //再次存入  更新\n" +
                "                map.put(c, ++count);\n" +
                "            }\n" +
                "        }\n" +
                "        System.out.println(map);\n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "# 第二章 补充知识点\n" +
                "\n" +
                "## 2.1  JDK9对集合添加的优化\n" +
                "\n" +
                "通常，我们在代码中创建一个集合（例如，List 或 Set ），并直接用一些元素填充它。 实例化集合，几个 add方法 调用，使得代码重复。\n" +
                "\n" +
                "~~~java\n" +
                "public class Demo01 {\n" +
                "    public static void main(String[] args) {\n" +
                "        List<String> list = new ArrayList<>();\n" +
                "        list.add(\"abc\");\n" +
                "        list.add(\"def\");\n" +
                "        list.add(\"ghi\");\n" +
                "        System.out.println(list);\n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                " Java 9，添加了几种集合工厂方法,更方便创建少量元素的集合、map实例。新的List、Set、Map的静态工厂方法可以更方便地创建集合的不可变实例。\n" +
                "\n" +
                "例子：\n" +
                "\n" +
                "~~~java\n" +
                "public class HelloJDK9 {  \n" +
                "    public static void main(String[] args) {  \n" +
                "        Set<String> str1=Set.of(\"a\",\"b\",\"c\");  \n" +
                "        //str1.add(\"c\");这里编译的时候不会错，但是执行的时候会报错，因为是不可变的集合，会抛一个不支持\t\t   //操作的异常  \n" +
                "        System.out.println(str1);  \n" +
                "        Map<String,Integer> str2=Map.of(\"a\",1,\"b\",2);//map和set的key不能重复，否则会抛一个非法         //参数异常  \n" +
                "        System.out.println(str2);  \n" +
                "        List<String> str3=List.of(\"a\",\"b\");  \n" +
                "        System.out.println(str3);  \n" +
                "    }  \n" +
                "} \n" +
                "~~~\n" +
                "\n" +
                "需要注意以下两点：\n" +
                "\n" +
                "> 1:of()方法只是Map，List，Set这三个接口的静态方法，其父类接口和子类实现并没有这类方法，比如    HashSet，ArrayList等待；\n" +
                ">\n" +
                "> 2:返回的集合是不可变的；\n" +
                "\n" +
                "## 2.2 Debug追踪\n" +
                "\n" +
                "**使用IDEA的断点调试功能，查看程序的运行过程**\n" +
                "\n" +
                "1. 在有效代码行，点击行号右边的空白区域，设置断点，程序执行到断点将停止，我们可以手动来运行程序 \t\n" +
                "\n" +
                "      ![](img/debug1.png)\n" +
                "\n" +
                "2. 点击Debug运行模式       ![](img\\debug2.png)                                                                                                                                                                      \n" +
                "\n" +
                "3.  程序停止在断点上不再执行，而IDEA最下方打开了Debug调试窗口  \n" +
                "\n" +
                "     ![](img\\debug3.png)![](img\\debug4.png)\n" +
                "\n" +
                "4. Debug调试窗口介绍\n" +
                "\n" +
                "    ![](img\\debug5.png)\n" +
                "\n" +
                "5. 快捷键F8，代码向下执行一行,第九行执行完毕，执行到第10行（第10行还未执行）\n" +
                "\n" +
                "   ![](img\\debug6.png)\n" +
                "\n" +
                "6. 切换到控制台面板，控制台显示 请录入一个字符串： 并且等待键盘录入\n" +
                "\n" +
                "   ![](img\\debug7.png)\n" +
                "\n" +
                "7. 快捷键F8，程序继续向后执行，执行键盘录入操作，在控制台录入数据 ababcea\n" +
                "\n" +
                "   ![](img\\debug8.png)\n" +
                "\n" +
                "   回车之后效果：![](img\\debug9.png)\n" +
                "\n" +
                "   调试界面效果：![](img\\debug0.png)\n" +
                "\n" +
                "8. 此时到达findChar方法，快捷键F7，进入方法findChar\n" +
                "\n" +
                "   ![](img\\debug11.png)\n" +
                "\n" +
                "9. 快捷键F8 接续执行，创建了map对象，变量区域显示\n" +
                "\n" +
                "   ![](img\\debug12.png)\n" +
                "\n" +
                "10. 快捷键F8 接续执行，进入到循环中，循环变量i为 0,F8再继续执行，就获取到变量c赋值为字符‘a’ 字节值97\n" +
                "\n" +
                "    ![](img\\debug13.png)\n" +
                "\n" +
                "11. 快捷键F8 接续执行，进入到判断语句中，因为该字符 不在Map集合键集中，再按F8执行，进入该判断中\n" +
                "\n" +
                "    ![](img\\debug14.png)\n" +
                "\n" +
                "12. 快捷键F8 接续执行，循环结束，进入下次循环，此时map中已经添加一对儿元素\n" +
                "\n" +
                "    ![](img\\debug15.png)\n" +
                "\n" +
                "13. 快捷键F8 接续执行，进入下次循环，再继续上面的操作，我们就可以看到代码每次是如何执行的了\n" +
                "\n" +
                "    ![](img\\debug16.png)\n" +
                "\n" +
                "14. 如果不想继续debug,那么可以使用快捷键F9,程序正常执行到结束，程序结果在控制台显示\n" +
                "\n" +
                "    ![](img\\debug17.png)\n" +
                "\n" +
                "# 第三章  模拟斗地主洗牌发牌\n" +
                "\n" +
                "## 3.1 案例介绍\n" +
                "\n" +
                "按照斗地主的规则，完成洗牌发牌的动作。\n" +
                "\n" +
                "![](img\\斗地主.png)\n" +
                "\n" +
                "具体规则：\n" +
                "\n" +
                "1. 组装54张扑克牌将\n" +
                "2. 54张牌顺序打乱\n" +
                "3. 三个玩家参与游戏，三人交替摸牌，每人17张牌，最后三张留作底牌。\n" +
                "4. 查看三人各自手中的牌（按照牌的大小排序）、底牌\n" +
                "\n" +
                "> 规则：手中扑克牌从大到小的摆放顺序：大王,小王,2,A,K,Q,J,10,9,8,7,6,5,4,3\n" +
                ">\n" +
                "\n" +
                "## 3.2 案例需求分析\n" +
                "\n" +
                "1.  准备牌：\n" +
                "\n" +
                "\n" +
                "完成数字与纸牌的映射关系：\n" +
                "\n" +
                "使用双列Map(HashMap)集合，完成一个数字与字符串纸牌的对应关系(相当于一个字典)。\n" +
                "\n" +
                "2.  洗牌：\n" +
                "\n" +
                "通过数字完成洗牌发牌\n" +
                "\n" +
                "3.  发牌：\n" +
                "\n" +
                "将每个人以及底牌设计为ArrayList<String>,将最后3张牌直接存放于底牌，剩余牌通过对3取模依次发牌。\n" +
                "\n" +
                "存放的过程中要求数字大小与斗地主规则的大小对应。\n" +
                "\n" +
                "将代表不同纸牌的数字分配给不同的玩家与底牌。\n" +
                "\n" +
                "4.  看牌：\n" +
                "\n" +
                "通过Map集合找到对应字符展示。\n" +
                "\n" +
                "通过查询纸牌与数字的对应关系，由数字转成纸牌字符串再进行展示。\n" +
                "\n" +
                "![](img\\斗地主分析.png)\n" +
                "\n" +
                "## 3.3  实现代码步骤\n" +
                "\n" +
                "~~~java\n" +
                "public class Poker {\n" +
                "    public static void main(String[] args) {\n" +
                "        /*\n" +
                "         * 1组装54张扑克牌\n" +
                "         */\n" +
                "        // 1.1 创建Map集合存储\n" +
                "        HashMap<Integer, String> pokerMap = new HashMap<Integer, String>();\n" +
                "        // 1.2 创建 花色集合 与 数字集合\n" +
                "        ArrayList<String> colors = new ArrayList<String>();\n" +
                "        ArrayList<String> numbers = new ArrayList<String>();\n" +
                "\n" +
                "        // 1.3 存储 花色 与数字\n" +
                "        Collections.addAll(colors, \"♦\", \"♣\", \"♥\", \"♠\");\n" +
                "        Collections.addAll(numbers, \"2\", \"A\", \"K\", \"Q\", \"J\", \"10\", \"9\", \"8\", \"7\", \"6\", \"5\", \"4\", \"3\");\n" +
                "        // 设置 存储编号变量\n" +
                "        int count = 1;\n" +
                "        pokerMap.put(count++, \"大王\");\n" +
                "        pokerMap.put(count++, \"小王\");\n" +
                "        // 1.4 创建牌 存储到map集合中\n" +
                "        for (String number : numbers) {\n" +
                "            for (String color : colors) {\n" +
                "                String card = color + number;\n" +
                "                pokerMap.put(count++, card);\n" +
                "            }\n" +
                "        }\n" +
                "        /*\n" +
                "         * 2 将54张牌顺序打乱\n" +
                "         */\n" +
                "        // 取出编号 集合\n" +
                "        Set<Integer> numberSet = pokerMap.keySet();\n" +
                "        // 因为要将编号打乱顺序 所以 应该先进行转换到 list集合中\n" +
                "        ArrayList<Integer> numberList = new ArrayList<Integer>();\n" +
                "        numberList.addAll(numberSet);\n" +
                "\n" +
                "        // 打乱顺序\n" +
                "        Collections.shuffle(numberList);\n" +
                "\n" +
                "        // 3 完成三个玩家交替摸牌，每人17张牌，最后三张留作底牌\n" +
                "        // 3.1 发牌的编号\n" +
                "        // 创建三个玩家编号集合 和一个 底牌编号集合\n" +
                "        ArrayList<Integer> noP1 = new ArrayList<Integer>();\n" +
                "        ArrayList<Integer> noP2 = new ArrayList<Integer>();\n" +
                "        ArrayList<Integer> noP3 = new ArrayList<Integer>();\n" +
                "        ArrayList<Integer> dipaiNo = new ArrayList<Integer>();\n" +
                "\n" +
                "        // 3.2发牌的编号\n" +
                "        for (int i = 0; i < numberList.size(); i++) {\n" +
                "            // 获取该编号\n" +
                "            Integer no = numberList.get(i);\n" +
                "            // 发牌\n" +
                "            // 留出底牌\n" +
                "            if (i >= 51) {\n" +
                "                dipaiNo.add(no);\n" +
                "            } else {\n" +
                "                if (i % 3 == 0) {\n" +
                "                    noP1.add(no);\n" +
                "                } else if (i % 3 == 1) {\n" +
                "                    noP2.add(no);\n" +
                "                } else {\n" +
                "                    noP3.add(no);\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        // 4 查看三人各自手中的牌（按照牌的大小排序）、底牌\n" +
                "        // 4.1 对手中编号进行排序\n" +
                "        Collections.sort(noP1);\n" +
                "        Collections.sort(noP2);\n" +
                "        Collections.sort(noP3);\n" +
                "        Collections.sort(dipaiNo);\n" +
                "\n" +
                "        // 4.2 进行牌面的转换\n" +
                "        // 创建三个玩家牌面集合 以及底牌牌面集合\n" +
                "        ArrayList<String> player1 = new ArrayList<String>();\n" +
                "        ArrayList<String> player2 = new ArrayList<String>();\n" +
                "        ArrayList<String> player3 = new ArrayList<String>();\n" +
                "        ArrayList<String> dipai = new ArrayList<String>();\n" +
                "\n" +
                "        // 4.3转换\n" +
                "        for (Integer i : noP1) {\n" +
                "            // 4.4 根据编号找到 牌面 pokerMap\n" +
                "            String card = pokerMap.get(i);\n" +
                "            // 添加到对应的 牌面集合中\n" +
                "            player1.add(card);\n" +
                "        }\n" +
                "\n" +
                "        for (Integer i : noP2) {\n" +
                "            String card = pokerMap.get(i);\n" +
                "            player2.add(card);\n" +
                "        }\n" +
                "        for (Integer i : noP3) {\n" +
                "            String card = pokerMap.get(i);\n" +
                "            player3.add(card);\n" +
                "        }\n" +
                "        for (Integer i : dipaiNo) {\n" +
                "            String card = pokerMap.get(i);\n" +
                "            dipai.add(card);\n" +
                "        }\n" +
                "\n" +
                "        //4.5 查看\n" +
                "        System.out.println(\"令狐冲：\"+player1);\n" +
                "        System.out.println(\"石破天：\"+player2);\n" +
                "        System.out.println(\"鸠摩智：\"+player3);\n" +
                "        System.out.println(\"底牌：\"+dipai);\n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "\n" +
                "\n" +
                " \n" +
                "\n" +
                " \n" +
                "\n" +
                " ";
        contents[4] = "# day05 【异常、线程】\n" +
                "\n" +
                "## 主要内容\n" +
                "\n" +
                "- 异常、线程\n" +
                "\n" +
                "## 教学目标\n" +
                "\n" +
                "- [ ] 能够辨别程序中异常和错误的区别\n" +
                "- [ ] 说出异常的分类\n" +
                "- [ ] 说出虚拟机处理异常的方式\n" +
                "- [ ] 列举出常见的三个运行期异常\n" +
                "- [ ] 能够使用try...catch关键字处理异常\n" +
                "- [ ] 能够使用throws关键字处理异常\n" +
                "- [ ] 能够自定义异常类\n" +
                "- [ ] 能够处理自定义异常类\n" +
                "- [ ] 说出进程的概念\n" +
                "- [ ] 说出线程的概念\n" +
                "- [ ] 能够理解并发与并行的区别\n" +
                "- [ ] 能够开启新线程\n" +
                "\n" +
                "# 第一章    异常\n" +
                "\n" +
                "## 1.1 异常概念\n" +
                "\n" +
                "异常，就是不正常的意思。在生活中:医生说,你的身体某个部位有异常,该部位和正常相比有点不同,该部位的功能将受影响.在程序中的意思就是：\n" +
                "\n" +
                "* **异常** ：指的是程序在执行过程中，出现的非正常的情况，最终会导致JVM的非正常停止。\n" +
                "\n" +
                "在Java等面向对象的编程语言中，异常本身是一个类，产生异常就是创建异常对象并抛出了一个异常对象。Java处理异常的方式是中断处理。\n" +
                "\n" +
                "> 异常指的并不是语法错误,语法错了,编译不通过,不会产生字节码文件,根本不能运行.\n" +
                "\n" +
                "## 1.2 异常体系\n" +
                "\n" +
                "异常机制其实是帮助我们**找到**程序中的问题，异常的根类是`java.lang.Throwable`，其下有两个子类：`java.lang.Error`与`java.lang.Exception`，平常所说的异常指`java.lang.Exception`。\n" +
                "\n" +
                "![](img\\异常体系.png)\n" +
                "\n" +
                "**Throwable体系：**\n" +
                "\n" +
                "* **Error**:严重错误Error，无法通过处理的错误，只能事先避免，好比绝症。\n" +
                "* **Exception**:表示异常，异常产生后程序员可以通过代码的方式纠正，使程序继续运行，是必须要处理的。好比感冒、阑尾炎。\n" +
                "\n" +
                "**Throwable中的常用方法：**\n" +
                "\n" +
                "* `public void printStackTrace()`:打印异常的详细信息。\n" +
                "\n" +
                "  *包含了异常的类型,异常的原因,还包括异常出现的位置,在开发和调试阶段,都得使用printStackTrace。*\n" +
                "\n" +
                "* `public String getMessage()`:获取发生异常的原因。\n" +
                "\n" +
                "  *提示给用户的时候,就提示错误原因。*\n" +
                "\n" +
                "* `public String toString()`:获取异常的类型和异常描述信息(不用)。\n" +
                "\n" +
                "***出现异常,不要紧张,把异常的简单类名,拷贝到API中去查。***\n" +
                "\n" +
                "![](img\\简单的异常查看.bmp)\n" +
                "\n" +
                "## 1.3 异常分类\n" +
                "\n" +
                "我们平常说的异常就是指Exception，因为这类异常一旦出现，我们就要对代码进行更正，修复程序。\n" +
                "\n" +
                "**异常(Exception)的分类**:根据在编译时期还是运行时期去检查异常?\n" +
                "\n" +
                "* **编译时期异常**:checked异常。在编译时期,就会检查,如果没有处理异常,则编译失败。(如日期格式化异常)\n" +
                "* **运行时期异常**:runtime异常。在运行时期,检查异常.在编译时期,运行异常不会编译器检测(不报错)。(如数学异常)\n" +
                "\n" +
                "\u200B    ![](img\\异常的分类.png)\n" +
                "\n" +
                "## 1.4     异常的产生过程解析\n" +
                "\n" +
                "先运行下面的程序，程序会产生一个数组索引越界异常ArrayIndexOfBoundsException。我们通过图解来解析下异常产生的过程。\n" +
                "\n" +
                " 工具类\n" +
                "\n" +
                "~~~java\n" +
                "public class ArrayTools {\n" +
                "    // 对给定的数组通过给定的角标获取元素。\n" +
                "    public static int getElement(int[] arr, int index) {\n" +
                "        int element = arr[index];\n" +
                "        return element;\n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                " 测试类\n" +
                "\n" +
                "~~~java\n" +
                "public class ExceptionDemo {\n" +
                "    public static void main(String[] args) {\n" +
                "        int[] arr = { 34, 12, 67 };\n" +
                "        intnum = ArrayTools.getElement(arr, 4)\n" +
                "        System.out.println(\"num=\" + num);\n" +
                "        System.out.println(\"over\");\n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "上述程序执行过程图解：\n" +
                "\n" +
                " ![](img\\异常产生过程.png)\n" +
                "\n" +
                "# 第二章 异常的处理\n" +
                "\n" +
                "Java异常处理的五个关键字：**try、catch、finally、throw、throws**\n" +
                "\n" +
                "## 2.1 \t抛出异常throw\n" +
                "\n" +
                "在编写程序时，我们必须要考虑程序出现问题的情况。比如，在定义方法时，方法需要接受参数。那么，当调用方法使用接受到的参数时，首先需要先对参数数据进行合法的判断，数据若不合法，就应该告诉调用者，传递合法的数据进来。这时需要使用抛出异常的方式来告诉调用者。\n" +
                "\n" +
                "在java中，提供了一个**throw**关键字，它用来抛出一个指定的异常对象。那么，抛出一个异常具体如何操作呢？\n" +
                "\n" +
                "1. 创建一个异常对象。封装一些提示信息(信息可以自己编写)。\n" +
                "\n" +
                "2. 需要将这个异常对象告知给调用者。怎么告知呢？怎么将这个异常对象传递到调用者处呢？通过关键字throw就可以完成。throw 异常对象。\n" +
                "\n" +
                "   throw**用在方法内**，用来抛出一个异常对象，将这个异常对象传递到调用者处，并结束当前方法的执行。\n" +
                "   \n" +
                "3. throw后面new的对象必须是Exception或者Exception的子类对象\n" +
                "\n" +
                "4. throw关键字抛出的对象如果是RuntimeException或者RuntimeException的子类可以不处理该异常，由jvm处理（jvm的处理方式是将异常打印在控制台上并且中断程序的运行，又称中断处理）。如果创建的是编译异常，那么一定要处理这个异常，要在方法上声明异常，方法的调用者可以用throws或try catch处理。\n" +
                "\n" +
                "**使用格式：**\n" +
                "\n" +
                "~~~\n" +
                "throw new 异常类名(参数);\n" +
                "~~~\n" +
                "\n" +
                " 例如：\n" +
                "\n" +
                "~~~java\n" +
                "throw new NullPointerException(\"要访问的arr数组不存在\");\n" +
                "\n" +
                "throw new ArrayIndexOutOfBoundsException(\"该索引在数组中不存在，已超出范围\");\n" +
                "~~~\n" +
                "\n" +
                "学习完抛出异常的格式后，我们通过下面程序演示下throw的使用。\n" +
                "\n" +
                "~~~java\n" +
                "public class ThrowDemo {\n" +
                "    public static void main(String[] args) {\n" +
                "        //创建一个数组 \n" +
                "        int[] arr = {2,4,52,2};\n" +
                "        //根据索引找对应的元素 \n" +
                "        int index = 4;\n" +
                "        int element = getElement(arr, index);\n" +
                "\n" +
                "        System.out.println(element);\n" +
                "        System.out.println(\"over\");\n" +
                "    }\n" +
                "    /*\n" +
                "     * 根据 索引找到数组中对应的元素\n" +
                "     */\n" +
                "    public static int getElement(int[] arr,int index){ \n" +
                "       \t//判断  索引是否越界\n" +
                "        if(index<0 || index>arr.length-1){\n" +
                "             /*\n" +
                "             判断条件如果满足，当执行完throw抛出异常对象后，方法已经无法继续运算。\n" +
                "             这时就会结束当前方法的执行，并将异常告知给调用者。这时就需要通过异常来解决。 \n" +
                "              */\n" +
                "             throw new ArrayIndexOutOfBoundsException(\"哥们，角标越界了~~~\");\n" +
                "        }\n" +
                "        int element = arr[index];\n" +
                "        return element;\n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "> 注意：如果产生了问题，我们就会throw将问题描述类即异常进行抛出，也就是将问题返回给该方法的调用者。\n" +
                ">\n" +
                "> 那么对于调用者来说，该怎么处理呢？一种是进行捕获处理，另一种就是继续讲问题声明出去，使用throws声明处理。\n" +
                "\n" +
                "## 2.2 Objects非空判断\n" +
                "\n" +
                "还记得我们学习过一个类Objects吗，曾经提到过它由一些静态的实用方法组成，这些方法是null-save（空指针安全的）或null-tolerant（容忍空指针的），那么在它的源码中，对对象为null的值进行了抛出异常操作。\n" +
                "\n" +
                "* `public static <T> T requireNonNull(T obj)`:查看指定引用对象不是null。\n" +
                "\n" +
                "查看源码发现这里对为null的进行了抛出异常操作：\n" +
                "\n" +
                "~~~java\n" +
                "public static <T> T requireNonNull(T obj) {\n" +
                "    if (obj == null)\n" +
                "      \tthrow new NullPointerException();\n" +
                "    return obj;\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "## 2.3  声明异常throws\n" +
                "\n" +
                "**声明异常**：将问题标识出来，报告给调用者。如果方法内通过throw抛出了编译时异常，而没有捕获处理（稍后讲解该方式），那么必须通过throws进行声明，让调用者去处理。\n" +
                "\n" +
                "关键字**throws**运用于方法声明之上,用于表示当前方法不处理异常,而是提醒该方法的调用者来处理异常(抛出异常).\n" +
                "\n" +
                "**声明异常格式：**\n" +
                "\n" +
                "~~~\n" +
                "修饰符 返回值类型 方法名(参数) throws 异常类名1,异常类名2…{   }\t\n" +
                "~~~\n" +
                "\n" +
                "声明异常的代码演示：\n" +
                "\n" +
                "~~~java\n" +
                "public class ThrowsDemo {\n" +
                "    public static void main(String[] args) throws FileNotFoundException {\n" +
                "        read(\"a.txt\");\n" +
                "    }\n" +
                "\n" +
                "    // 如果定义功能时有问题发生需要报告给调用者。可以通过在方法上使用throws关键字进行声明\n" +
                "    public static void read(String path) throws FileNotFoundException {\n" +
                "        if (!path.equals(\"a.txt\")) {//如果不是 a.txt这个文件 \n" +
                "            // 我假设  如果不是 a.txt 认为 该文件不存在 是一个错误 也就是异常  throw\n" +
                "            throw new FileNotFoundException(\"文件不存在\");\n" +
                "        }\n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "throws用于进行异常类的声明，若该方法可能有多种异常情况产生，那么在throws后面可以写多个异常类，用逗号隔开，如果多个异常类存在父子关系，那么只声明父类异常，只抛出父类异常即可。\n" +
                "\n" +
                "~~~java\n" +
                "public class ThrowsDemo2 {\n" +
                "    public static void main(String[] args) throws IOException {\n" +
                "        read(\"a.txt\");\n" +
                "    }\n" +
                "\n" +
                "    public static void read(String path)throws FileNotFoundException, IOException {\n" +
                "        if (!path.equals(\"a.txt\")) {//如果不是 a.txt这个文件 \n" +
                "            // 我假设  如果不是 a.txt 认为 该文件不存在 是一个错误 也就是异常  throw\n" +
                "            throw new FileNotFoundException(\"文件不存在\");\n" +
                "        }\n" +
                "        if (!path.equals(\"b.txt\")) {\n" +
                "            throw new IOException();\n" +
                "        }\n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "## 2.4  捕获异常try…catch\n" +
                "\n" +
                "如果异常出现的话,会立刻终止程序,所以我们得处理异常:\n" +
                "\n" +
                "1. 该方法不处理,而是声明抛出,由该方法的调用者来处理(throws)。\n" +
                "2. 在方法中使用try-catch的语句块来处理异常。\n" +
                "3. try..catch与throws的区别就是throws的话抛出异常后下面的代码都不能执行了，但catch捕获并处理完异常后后面的代码还能执行。\n" +
                "\n" +
                "**try-catch**的方式就是捕获异常。\n" +
                "\n" +
                "* **捕获异常**：Java中对异常有针对性的语句进行捕获，可以对出现的异常进行指定方式的处理。\n" +
                "\n" +
                "捕获异常语法如下：\n" +
                "\n" +
                "~~~java\n" +
                "try{\n" +
                "     编写可能会出现异常的代码\n" +
                "}catch(异常类型  e){\n" +
                "     处理异常的代码\n" +
                "     //记录日志/打印异常信息/继续抛出异常\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "**try：**该代码块中编写可能产生异常的代码。\n" +
                "\n" +
                "**catch：**用来进行某种异常的捕获，实现对捕获到的异常进行处理。\n" +
                "\n" +
                "> 注意:try和catch都不能单独使用,必须连用。\n" +
                "\n" +
                "演示如下：\n" +
                "\n" +
                "~~~java\n" +
                "public class TryCatchDemo {\n" +
                "    public static void main(String[] args) {\n" +
                "        try {// 当产生异常时，必须有处理方式。要么捕获，要么声明。\n" +
                "            read(\"b.txt\");\n" +
                "        } catch (FileNotFoundException e) {// 括号中需要定义什么呢？\n" +
                "          \t//try中抛出的是什么异常，在括号中就定义什么异常类型\n" +
                "            System.out.println(e);\n" +
                "        }\n" +
                "        System.out.println(\"over\");\n" +
                "    }\n" +
                "    /*\n" +
                "     *\n" +
                "     * 我们 当前的这个方法中 有异常  有编译期异常\n" +
                "     */\n" +
                "    public static void read(String path) throws FileNotFoundException {\n" +
                "        if (!path.equals(\"a.txt\")) {//如果不是 a.txt这个文件 \n" +
                "            // 我假设  如果不是 a.txt 认为 该文件不存在 是一个错误 也就是异常  throw\n" +
                "            throw new FileNotFoundException(\"文件不存在\");\n" +
                "        }\n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "如何获取异常信息：\n" +
                "\n" +
                "Throwable类中定义了一些查看方法:\n" +
                "\n" +
                "* `public String getMessage()`:获取异常的描述信息,原因(提示给用户的时候,就提示错误原因)。\n" +
                "\n" +
                "\n" +
                "* `public String toString()`:获取异常的类型和异常描述信息(不用)。\n" +
                "* `public void printStackTrace()`:打印异常的跟踪栈信息并输出到控制台（异常信息最全面，推荐使用）。\n" +
                "\n" +
                "\u200B            *包含了异常的类型,异常的原因,还包括异常出现的位置,在开发和调试阶段,都得使用printStackTrace。*\n" +
                "\n" +
                "## 2.4 finally 代码块\n" +
                "\n" +
                "**finally**：有一些特定的代码无论异常是否发生，都需要执行。另外，因为异常会引发程序跳转，导致有些语句执行不到。而finally就是解决这个问题的，在finally代码块中存放的代码都是一定会被执行的。\n" +
                "\n" +
                "什么时候的代码必须最终执行？\n" +
                "\n" +
                "当我们在try语句块中打开了一些物理资源(磁盘文件/网络连接/数据库连接等),我们都得在使用完之后,最终关闭打开的资源。\n" +
                "\n" +
                "finally的语法:\n" +
                "\n" +
                " try...catch....finally:自身需要处理异常,最终还得关闭资源。\n" +
                "\n" +
                "> 注意:finally不能单独使用。\n" +
                "\n" +
                "比如在我们之后学习的IO流中，当打开了一个关联文件的资源，最后程序不管结果如何，都需要把这个资源关闭掉。\n" +
                "\n" +
                "finally代码参考如下：\n" +
                "\n" +
                "~~~java\n" +
                "public class TryCatchDemo4 {\n" +
                "    public static void main(String[] args) {\n" +
                "        try {\n" +
                "            read(\"a.txt\");\n" +
                "        } catch (FileNotFoundException e) {\n" +
                "            //抓取到的是编译期异常  抛出去的是运行期 \n" +
                "            throw new RuntimeException(e);\n" +
                "        } finally {\n" +
                "            System.out.println(\"不管程序怎样，这里都将会被执行。\");\n" +
                "        }\n" +
                "        System.out.println(\"over\");\n" +
                "    }\n" +
                "    /*\n" +
                "     *\n" +
                "     * 我们 当前的这个方法中 有异常  有编译期异常\n" +
                "     */\n" +
                "    public static void read(String path) throws FileNotFoundException {\n" +
                "        if (!path.equals(\"a.txt\")) {//如果不是 a.txt这个文件 \n" +
                "            // 我假设  如果不是 a.txt 认为 该文件不存在 是一个错误 也就是异常  throw\n" +
                "            throw new FileNotFoundException(\"文件不存在\");\n" +
                "        }\n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "> 当只有在try或者catch中调用退出JVM的相关方法,此时finally才不会执行,否则finally永远会执行。\n" +
                "\n" +
                "![](img\\死了都要try.bmp)\n" +
                "\n" +
                "## 2.5   异常注意事项\n" +
                "\n" +
                "* 多个异常使用捕获又该如何处理呢？\n" +
                "\n" +
                "  1. 多个异常分别处理。\n" +
                "  2. 多个异常一次捕获，多次处理。\n" +
                "  3. 多个异常一次捕获一次处理。\n" +
                "\n" +
                "  一般我们是使用一次捕获多次处理方式，格式如下：\n" +
                "\n" +
                "  ```java\n" +
                "  try{\n" +
                "       编写可能会出现异常的代码\n" +
                "  }catch(异常类型A  e){  当try中出现A类型异常,就用该catch来捕获.\n" +
                "       处理异常的代码\n" +
                "       //记录日志/打印异常信息/继续抛出异常\n" +
                "  }catch(异常类型B  e){  当try中出现B类型异常,就用该catch来捕获.\n" +
                "       处理异常的代码\n" +
                "       //记录日志/打印异常信息/继续抛出异常\n" +
                "  }\n" +
                "  ```\n" +
                "\n" +
                "  > 注意:这种异常处理方式，要求多个catch中的异常不能相同，并且若catch中的多个异常之间有子父类异常的关系，那么子类异常要求在上面的catch处理，父类异常在下面的catch处理。原因是多态性，如果父类异常的处理写在了子类异常处理的上方，那么即使有子类异常，也会被父类异常接收，那子类异常的处理就永远都执行不到。\n" +
                "\n" +
                "* 运行时异常被抛出可以不处理。即不捕获也不声明抛出。\n" +
                "\n" +
                "* 如果finally有return语句,永远返回finally中的结果,避免该情况. \n" +
                "\n" +
                "* 如果父类抛出了多个异常,子类重写父类方法时,抛出和父类相同的异常或者是父类异常的子类或者不抛出异常，上述三种方式都可以。\n" +
                "\n" +
                "* 父类方法没有抛出异常，子类重写父类该方法时也不可抛出异常。此时子类产生该异常，只能捕获处理，不能声明抛出\n" +
                "\n" +
                "\n" +
                "# 第三章 自定义异常\n" +
                "\n" +
                "## 3.1 概述\n" +
                "\n" +
                "**为什么需要自定义异常类:**\n" +
                "\n" +
                "我们说了Java中不同的异常类,分别表示着某一种具体的异常情况,那么在开发中总是有些异常情况是SUN没有定义好的,此时我们根据自己业务的异常情况来定义异常类。例如年龄负数问题,考试成绩负数问题等等。\n" +
                "\n" +
                "在上述代码中，发现这些异常都是JDK内部定义好的，但是实际开发中也会出现很多异常,这些异常很可能在JDK中没有定义过,例如年龄负数问题,考试成绩负数问题.那么能不能自己定义异常呢？\n" +
                "\n" +
                "**什么是自定义异常类:**\n" +
                "\n" +
                "在开发中根据自己业务的异常情况来定义异常类.\n" +
                "\n" +
                "自定义一个业务逻辑异常: **RegisterException**。一个注册异常类。\n" +
                "\n" +
                "**异常类如何定义:**\n" +
                "\n" +
                "1. 自定义一个编译期异常: 自定义类 并继承于`java.lang.Exception`。\n" +
                "2. 自定义一个运行时期的异常类:自定义类 并继承于`java.lang.RuntimeException`。\n" +
                "\n" +
                "## 3.2 自定义异常的练习\n" +
                "\n" +
                "要求：我们模拟注册操作，如果用户名已存在，则抛出异常并提示：亲，该用户名已经被注册。\n" +
                "\n" +
                "首先定义一个登陆异常类RegisterException：\n" +
                "\n" +
                "~~~java\n" +
                "// 业务逻辑异常\n" +
                "public class RegisterException extends Exception {\n" +
                "    /**\n" +
                "     * 空参构造\n" +
                "     */\n" +
                "    public RegisterException() {\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     *\n" +
                "     * @param message 表示异常提示\n" +
                "     */\n" +
                "    public RegisterException(String message) {\n" +
                "        super(message);\n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "模拟登陆操作，使用数组模拟数据库中存储的数据，并提供当前注册账号是否存在方法用于判断。\n" +
                "\n" +
                "~~~java\n" +
                "public class Demo {\n" +
                "    // 模拟数据库中已存在账号\n" +
                "    private static String[] names = {\"bill\",\"hill\",\"jill\"};\n" +
                "   \n" +
                "    public static void main(String[] args) {     \n" +
                "        //调用方法\n" +
                "        try{\n" +
                "              // 可能出现异常的代码\n" +
                "            checkUsername(\"nill\");\n" +
                "            System.out.println(\"注册成功\");//如果没有异常就是注册成功\n" +
                "        }catch(RegisterException e){\n" +
                "            //处理异常\n" +
                "            e.printStackTrace();\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    //判断当前注册账号是否存在\n" +
                "    //因为是编译期异常，又想调用者去处理 所以声明该异常\n" +
                "    public static boolean checkUsername(String uname) throws LoginException{\n" +
                "        for (String name : names) {\n" +
                "            if(name.equals(uname)){//如果名字在这里面 就抛出登陆异常\n" +
                "                throw new RegisterException(\"亲\"+name+\"已经被注册了！\");\n" +
                "            }\n" +
                "        }\n" +
                "        return true;\n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "#  第四章 多线程\n" +
                "\n" +
                "我们在之前，学习的程序在没有跳转语句的前提下，都是由上至下依次执行，那现在想要设计一个程序，边打游戏边听歌，怎么设计？\n" +
                "\n" +
                "要解决上述问题,咱们得使用多进程或者多线程来解决.\n" +
                "\n" +
                "## 4.1 并发与并行\n" +
                "\n" +
                "* **并发**：指两个或多个事件在**同一个时间段内**发生。\n" +
                "* **并行**：指两个或多个事件在**同一时刻**发生（同时发生）。\n" +
                "\n" +
                "![](img\\并行与并发.bmp)\n" +
                "\n" +
                "在操作系统中，安装了多个程序，并发指的是在一段时间内宏观上有多个程序同时运行，这在单 CPU 系统中，每一时刻只能有一道程序执行，即微观上这些程序是分时的交替运行，只不过是给人的感觉是同时运行，那是因为分时交替运行的时间是非常短的（并发）。\n" +
                "\n" +
                "而在多个 CPU 系统中，则这些可以并发执行的程序便可以分配到多个处理器上（CPU），实现多任务并行执行，即利用每个处理器来处理一个可以并发执行的程序，这样多个程序便可以同时执行。目前电脑市场上说的多核 CPU，便是多核处理器，核越多，并行处理的程序越多，能大大的提高电脑运行的效率（并行）。\n" +
                "\n" +
                "> 注意：单核处理器的计算机肯定是不能并行的处理多个任务的，只能是多个任务在单个CPU上并发运行。同理,线程也是一样的，从宏观角度上理解线程是并行运行的，但是从微观角度上分析却是串行运行的，即一个线程一个线程的去运行，当系统只有一个CPU时，线程会以某种顺序执行多个线程，我们把这种情况称之为线程调度。\n" +
                "\n" +
                "## 4.2 线程与进程\n" +
                "\n" +
                "* **进程**：是指一个内存中运行的应用程序，每个进程都有一个独立的内存空间，一个应用程序可以同时运行多个进程；进程也是程序的一次执行过程，是系统运行程序的基本单位；系统运行一个程序即是一个进程从创建、运行到消亡的过程。\n" +
                "\n" +
                "* **线程**：线程是进程中的一个执行单元，负责当前进程中程序的执行，一个进程中至少有一个线程。一个进程中是可以有多个线程的，这个应用程序也可以称之为多线程程序。 \n" +
                "\n" +
                "  简而言之：一个程序运行后至少有一个进程，一个进程中可以包含多个线程 \n" +
                "\n" +
                "我们可以再电脑底部任务栏，右键----->打开任务管理器,可以查看当前任务的进程：\n" +
                "\n" +
                "**进程**\n" +
                "\n" +
                "![](img\\进程概念.png)\n" +
                "\n" +
                "**线程**\n" +
                "\n" +
                "![](img\\线程概念.png)\n" +
                "\n" +
                "**线程调度:**\n" +
                "\n" +
                "- 分时调度\n" +
                "\n" +
                "  所有线程轮流使用 CPU 的使用权，平均分配每个线程占用 CPU 的时间。\n" +
                "\n" +
                "- 抢占式调度\n" +
                "\n" +
                "  优先让优先级高的线程使用 CPU，如果线程的优先级相同，那么会随机选择一个(线程随机性)，Java使用的为抢占式调度。\n" +
                "\n" +
                "  - 设置线程的优先级\n" +
                "\n" +
                "  ![设置线程优先级](img/设置线程优先级.bmp)\n" +
                "  -  抢占式调度详解\n" +
                "\n" +
                "    大部分操作系统都支持多进程并发运行，现在的操作系统几乎都支持同时运行多个程序。比如：现在我们上课一边使用编辑器，一边使用录屏软件，同时还开着画图板，dos窗口等软件。此时，这些程序是在同时运行，”感觉这些软件好像在同一时刻运行着“。\n" +
                "\n" +
                "    实际上，CPU(中央处理器)使用抢占式调度模式在多个线程间进行着高速的切换。对于CPU的一个核而言，某个时刻，只能执行一个线程，而 CPU的在多个线程间切换速度相对我们的感觉要快，看上去就是在同一时刻运行。\n" +
                "    其实，多线程程序并不能提高程序的运行速度，但能够提高程序运行效率，让CPU的使用率更高。\n" +
                "\n" +
                "    ![抢占式调度](img/抢占式调度.bmp)\n" +
                "## 4.3 主线程\n" +
                "\n" +
                "执行主(main)方法的线程，控制台输出的thread \"main\"就是代表主线程。单线程程序：Java程序中只有一个线程，执行从main开始，从上到下一次执行。\n" +
                "\n" +
                "## 4.4 创建线程类\n" +
                "\n" +
                "Java使用`java.lang.Thread`类代表**线程**，所有的线程对象都必须是Thread类或其子类的实例。每个线程的作用是完成一定的任务，实际上就是执行一段程序流即一段顺序执行的代码。Java使用线程执行体来代表这段程序流。Java中通过继承Thread类来**创建**并**启动多线程**的步骤如下：\n" +
                "\n" +
                "1. 定义Thread类的子类，并重写该类的run()方法，该run()方法的方法体就代表了线程需要完成的任务,因此把run()方法称为线程执行体。\n" +
                "2. 创建Thread子类的实例，即创建了线程对象\n" +
                "3. 调用线程对象的start()方法来启动该线程。官方文档说明： `void start()`使该线程开始运行，Java虚拟机调用该线程的run方法。结果是两个线程并发运行，当前线程（main线程）和另一个线程（创建新的线程，执行它的run方法）。多次启动一个线程是非法的。特别是当线程已经结束执行后，不能在启动。官方文档的意思是：调用start方法后，虚拟机执行该线程run方法，并且会创造出另一个线程也执行线程的run方法；一个线程只能使用一次start方法；线程结束后就不能再启动再调用start方法了。\n" +
                "\n" +
                "代码如下：\n" +
                "\n" +
                "测试类：\n" +
                "\n" +
                "~~~java\n" +
                "public class Demo01 {\n" +
                "\tpublic static void main(String[] args) {\n" +
                "\t\t//创建自定义线程对象\n" +
                "\t\tMyThread mt = new MyThread(\"新的线程！\");\n" +
                "\t\t//开启新线程\n" +
                "\t\tmt.start();\n" +
                "\t\t//在主方法中执行for循环\n" +
                "\t\tfor (int i = 0; i < 10; i++) {\n" +
                "\t\t\tSystem.out.println(\"main线程！\"+i);\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "自定义线程类：\n" +
                "\n" +
                "~~~java\n" +
                "public class MyThread extends Thread {\n" +
                "\t//定义指定线程名称的构造方法\n" +
                "\tpublic MyThread(String name) {\n" +
                "\t\t//调用父类的String参数的构造方法，指定线程的名称\n" +
                "\t\tsuper(name);\n" +
                "\t}\n" +
                "\t/**\n" +
                "\t * 重写run方法，完成该线程执行的逻辑\n" +
                "\t */\n" +
                "\t@Override\n" +
                "\tpublic void run() {\n" +
                "\t\tfor (int i = 0; i < 10; i++) {\n" +
                "\t\t\tSystem.out.println(getName()+\"：正在执行！\"+i);\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}\n" +
                "~~~\n";
        contents[5] = "# day07【线程池、Lambda表达式】\n" +
                "\n" +
                "## 主要内容\n" +
                "\n" +
                "*   等待与唤醒案例\n" +
                "*   线程池\n" +
                "*   Lambda表达式\n" +
                "\n" +
                "## 教学目标\n" +
                "\n" +
                "- [ ] 能够理解线程通信概念\n" +
                "- [ ] 能够理解等待唤醒机制\n" +
                "- [ ] 能够描述Java中线程池运行原理\n" +
                "- [ ] 能够理解函数式编程相对于面向对象的优点\n" +
                "- [ ] 能够掌握Lambda表达式的标准格式\n" +
                "- [ ] 能够使用Lambda标准格式使用Runnable与Comparator接口\n" +
                "- [ ] 能够掌握Lambda表达式的省略格式与规则\n" +
                "- [ ] 能够使用Lambda省略格式使用Runnable与Comparator接口\n" +
                "- [ ] 能够通过Lambda的标准格式使用自定义的接口（有且仅有一个抽象方法）\n" +
                "- [ ] 能够通过Lambda的省略格式使用自定义的接口（有且仅有一个抽象方法）\n" +
                "- [ ] 能够明确Lambda的两项使用前提\n" +
                "\n" +
                "# 第一章 等待唤醒机制\n" +
                "\n" +
                "## 1.1 线程间通信\n" +
                "\n" +
                "**概念：**多个线程在处理同一个资源，但是处理的动作（线程的任务）却不相同。\n" +
                "\n" +
                "比如：线程A用来生成包子的，线程B用来吃包子的，包子可以理解为同一资源，线程A与线程B处理的动作，一个是生产，一个是消费，那么线程A与线程B之间就存在线程通信问题。\n" +
                "\n" +
                "![](img\\线程间通信.bmp)\n" +
                "\n" +
                "**为什么要处理线程间通信：**\n" +
                "\n" +
                "多个线程并发执行时, 在默认情况下CPU是随机切换线程的，当我们需要多个线程来共同完成一件任务，并且我们希望他们有规律的执行, 那么多线程之间需要一些协调通信，以此来帮我们达到多线程共同操作一份数据。\n" +
                "\n" +
                "**如何保证线程间通信有效利用资源：**\n" +
                "\n" +
                "多个线程在处理同一个资源，并且任务不同时，需要线程通信来帮助解决线程之间对同一个变量的使用或操作。 就是多个线程在操作同一份数据时， 避免对同一共享变量的争夺。也就是我们需要通过一定的手段使各个线程能有效的利用资源。而这种手段即—— **等待唤醒机制。**\n" +
                "\n" +
                "## 1.2 等待唤醒机制\n" +
                "\n" +
                "**什么是等待唤醒机制**\n" +
                "\n" +
                "这是多个线程间的一种**协作**机制。谈到线程我们经常想到的是线程间的**竞争（race）**，比如去争夺锁，但这并不是故事的全部，线程间也会有协作机制。就好比在公司里你和你的同事们，你们可能存在在晋升时的竞争，但更多时候你们更多是一起合作以完成某些任务。\n" +
                "\n" +
                "就是在一个线程进行了规定操作后，就进入等待状态（**wait()**）， 等待其他线程执行完他们的指定代码过后 再将其唤醒（**notify()**）;在有多个线程进行等待时， 如果需要，可以使用 notifyAll()来唤醒所有的等待线程。\n" +
                "\n" +
                "wait/notify 就是线程间的一种协作机制。\n" +
                "\n" +
                "**等待唤醒中的方法**\n" +
                "\n" +
                "等待唤醒机制就是用于解决线程间通信的问题的，使用到的3个方法的含义如下：\n" +
                "\n" +
                "1. wait：线程不再活动，不再参与调度，进入 wait set 中，因此不会浪费 CPU 资源，也不会去竞争锁了，这时的线程状态即是 WAITING。它还要等着别的线程执行一个**特别的动作**，也即是“**通知（notify）**”在这个对象上等待的线程从wait set 中释放出来，重新进入到调度队列（ready queue）中\n" +
                "2. notify：则选取所通知对象的 wait set 中的一个线程释放；例如，餐馆有空位置后，等候就餐最久的顾客最先入座。\n" +
                "3. notifyAll：则释放所通知对象的 wait set 上的全部线程。\n" +
                "\n" +
                ">注意：\n" +
                ">\n" +
                ">哪怕只通知了一个等待的线程，被通知线程也不能立即恢复执行，因为它当初中断的地方是在同步块内，而此刻它已经不持有锁，所以她需要再次尝试去获取锁（很可能面临其它线程的竞争），成功后才能在当初调用 wait 方法之后的地方恢复执行。\n" +
                ">\n" +
                ">总结如下：\n" +
                ">\n" +
                ">- 如果能获取锁，线程就从 WAITING 状态变成 RUNNABLE 状态；\n" +
                ">- 否则，从 wait set 出来，又进入 entry set，线程就从 WAITING 状态又变成 BLOCKED 状态\n" +
                "\n" +
                "**调用wait和notify方法需要注意的细节**\n" +
                "\n" +
                "1. wait方法与notify方法必须要由同一个锁对象调用。因为：对应的锁对象可以通过notify唤醒使用同一个锁对象调用的wait方法后的线程。\n" +
                "2. wait方法与notify方法是属于Object类的方法的。因为：锁对象可以是任意对象，而任意对象的所属类都是继承了Object类的。\n" +
                "3. wait方法与notify方法必须要在同步代码块或者是同步函数中使用。因为：必须要通过锁对象调用这2个方法。\n" +
                "\n" +
                "## 1.3 生产者与消费者问题\n" +
                "\n" +
                "等待唤醒机制其实就是经典的“生产者与消费者”的问题。\n" +
                "\n" +
                "就拿生产包子消费包子来说等待唤醒机制如何有效利用资源：\n" +
                "\n" +
                "~~~java\n" +
                "包子铺线程生产包子，吃货线程消费包子。当包子没有时（包子状态为false），吃货线程等待，包子铺线程生产包子（即包子状态为true），并通知吃货线程（解除吃货的等待状态）,因为已经有包子了，那么包子铺线程进入等待状态。接下来，吃货线程能否进一步执行则取决于锁的获取情况。如果吃货获取到锁，那么就执行吃包子动作，包子吃完（包子状态为false），并通知包子铺线程（解除包子铺的等待状态）,吃货线程进入等待。包子铺线程能否进一步执行则取决于锁的获取情况。\n" +
                "~~~\n" +
                "\n" +
                "**代码演示：**\n" +
                "\n" +
                "包子资源类：\n" +
                "\n" +
                "~~~java\n" +
                "public class BaoZi {\n" +
                "     String  pier ;\n" +
                "     String  xianer ;\n" +
                "     boolean  flag = false ;//包子资源 是否存在  包子资源状态\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "吃货线程类：\n" +
                "\n" +
                "~~~java\n" +
                "public class ChiHuo extends Thread{\n" +
                "    private BaoZi bz;\n" +
                "\n" +
                "    public ChiHuo(String name,BaoZi bz){\n" +
                "        super(name);\n" +
                "        this.bz = bz;\n" +
                "    }\n" +
                "    @Override\n" +
                "    public void run() {\n" +
                "        while(true){\n" +
                "            synchronized (bz){\n" +
                "                if(bz.flag == false){//没包子\n" +
                "                    try {\n" +
                "                        bz.wait();\n" +
                "                    } catch (InterruptedException e) {\n" +
                "                        e.printStackTrace();\n" +
                "                    }\n" +
                "                }\n" +
                "                System.out.println(\"吃货正在吃\"+bz.pier+bz.xianer+\"包子\");\n" +
                "                bz.flag = false;\n" +
                "                bz.notify();\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "包子铺线程类：\n" +
                "\n" +
                "~~~java\n" +
                "public class BaoZiPu extends Thread {\n" +
                "\n" +
                "    private BaoZi bz;\n" +
                "\n" +
                "    public BaoZiPu(String name,BaoZi bz){\n" +
                "        super(name);\n" +
                "        this.bz = bz;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void run() {\n" +
                "        int count = 0;\n" +
                "        //造包子\n" +
                "        while(true){\n" +
                "            //同步\n" +
                "            synchronized (bz){\n" +
                "                if(bz.flag == true){//包子资源  存在\n" +
                "                    try {\n" +
                "\n" +
                "                        bz.wait();\n" +
                "\n" +
                "                    } catch (InterruptedException e) {\n" +
                "                        e.printStackTrace();\n" +
                "                    }\n" +
                "                }\n" +
                "\n" +
                "                // 没有包子  造包子\n" +
                "                System.out.println(\"包子铺开始做包子\");\n" +
                "                if(count%2 == 0){\n" +
                "                    // 冰皮  五仁\n" +
                "                    bz.pier = \"冰皮\";\n" +
                "                    bz.xianer = \"五仁\";\n" +
                "                }else{\n" +
                "                    // 薄皮  牛肉大葱\n" +
                "                    bz.pier = \"薄皮\";\n" +
                "                    bz.xianer = \"牛肉大葱\";\n" +
                "                }\n" +
                "                count++;\n" +
                "\n" +
                "                bz.flag=true;\n" +
                "                System.out.println(\"包子造好了：\"+bz.pier+bz.xianer);\n" +
                "                System.out.println(\"吃货来吃吧\");\n" +
                "                //唤醒等待线程 （吃货）\n" +
                "                bz.notify();\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "测试类：\n" +
                "\n" +
                "~~~java\n" +
                "public class Demo {\n" +
                "    public static void main(String[] args) {\n" +
                "        //等待唤醒案例\n" +
                "        BaoZi bz = new BaoZi();\n" +
                "\n" +
                "        ChiHuo ch = new ChiHuo(\"吃货\",bz);\n" +
                "        BaoZiPu bzp = new BaoZiPu(\"包子铺\",bz);\n" +
                "\n" +
                "        ch.start();\n" +
                "        bzp.start();\n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "执行效果：\n" +
                "\n" +
                "~~~java\n" +
                "包子铺开始做包子\n" +
                "包子造好了：冰皮五仁\n" +
                "吃货来吃吧\n" +
                "吃货正在吃冰皮五仁包子\n" +
                "包子铺开始做包子\n" +
                "包子造好了：薄皮牛肉大葱\n" +
                "吃货来吃吧\n" +
                "吃货正在吃薄皮牛肉大葱包子\n" +
                "包子铺开始做包子\n" +
                "包子造好了：冰皮五仁\n" +
                "吃货来吃吧\n" +
                "吃货正在吃冰皮五仁包子\n" +
                "~~~\n" +
                "\n" +
                "# 第二章 线程池\n" +
                "\n" +
                "## 2.1 线程池思想概述\n" +
                "\n" +
                "![](img\\游泳池.jpg)\n" +
                "\n" +
                "我们使用线程的时候就去创建一个线程，这样实现起来非常简便，但是就会有一个问题：\n" +
                "\n" +
                "如果并发的线程数量很多，并且每个线程都是执行一个时间很短的任务就结束了，这样频繁创建线程就会大大降低系统的效率，因为频繁创建线程和销毁线程需要时间。\n" +
                "\n" +
                "那么有没有一种办法使得线程可以复用，就是执行完一个任务，并不被销毁，而是可以继续执行其他的任务？\n" +
                "\n" +
                "在Java中可以通过线程池来达到这样的效果。今天我们就来详细讲解一下Java的线程池。\n" +
                "\n" +
                "## 2.2 线程池概念\n" +
                "\n" +
                "* **线程池：**其实就是一个容纳多个线程的容器，其中的线程可以反复使用，省去了频繁创建线程对象的操作，无需反复创建线程而消耗过多资源。\n" +
                "\n" +
                "由于线程池中有很多操作都是与优化资源相关的，我们在这里就不多赘述。我们通过一张图来了解线程池的工作原理：\n" +
                "\n" +
                "![](img\\线程池原理.bmp)\n" +
                "\n" +
                "合理利用线程池能够带来三个好处：\n" +
                "\n" +
                "1. 降低资源消耗。减少了创建和销毁线程的次数，每个工作线程都可以被重复利用，可执行多个任务。\n" +
                "2. 提高响应速度。当任务到达时，任务可以不需要的等到线程创建就能立即执行。\n" +
                "3. 提高线程的可管理性。可以根据系统的承受能力，调整线程池中工作线线程的数目，防止因为消耗过多的内存，而把服务器累趴下(每个线程需要大约1MB内存，线程开的越多，消耗的内存也就越大，最后死机)。\n" +
                "\n" +
                "## 2.3 线程池的使用\n" +
                "\n" +
                "Java里面线程池的顶级接口是`java.util.concurrent.Executor`，但是严格意义上讲`Executor`并不是一个线程池，而只是一个执行线程的工具。真正的线程池接口是`java.util.concurrent.ExecutorService`。\n" +
                "\n" +
                "要配置一个线程池是比较复杂的，尤其是对于线程池的原理不是很清楚的情况下，很有可能配置的线程池不是较优的，因此在`java.util.concurrent.Executors`线程工厂类里面提供了一些静态工厂，生成一些常用的线程池。官方建议使用Executors工程类来创建线程池对象。\n" +
                "\n" +
                "Executors类中有个创建线程池的方法如下：\n" +
                "\n" +
                "* `public static ExecutorService newFixedThreadPool(int nThreads)`：返回线程池对象。(创建的是有界线程池,也就是池中的线程个数可以指定最大数量)\n" +
                "\n" +
                "获取到了一个线程池ExecutorService 对象，那么怎么使用呢，在这里定义了一个使用线程池对象的方法如下：\n" +
                "\n" +
                "* `public Future<?> submit(Runnable task)`:获取线程池中的某一个线程对象，并执行\n" +
                "\n" +
                "  > Future接口：用来记录线程任务执行完毕后产生的结果。线程池创建与使用。\n" +
                "\n" +
                "使用线程池中线程对象的步骤：\n" +
                "\n" +
                "1. 创建线程池对象。\n" +
                "2. 创建Runnable接口子类对象。(task)\n" +
                "3. 提交Runnable接口子类对象。(take task)\n" +
                "4. 关闭线程池(一般不做)。\n" +
                "\n" +
                "Runnable实现类代码：\n" +
                "\n" +
                "~~~java\n" +
                "public class MyRunnable implements Runnable {\n" +
                "    @Override\n" +
                "    public void run() {\n" +
                "        System.out.println(\"我要一个教练\");\n" +
                "        try {\n" +
                "            Thread.sleep(2000);\n" +
                "        } catch (InterruptedException e) {\n" +
                "            e.printStackTrace();\n" +
                "        }\n" +
                "        System.out.println(\"教练来了： \" + Thread.currentThread().getName());\n" +
                "        System.out.println(\"教我游泳,交完后，教练回到了游泳池\");\n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "线程池测试类：\n" +
                "\n" +
                "~~~java\n" +
                "public class ThreadPoolDemo {\n" +
                "    public static void main(String[] args) {\n" +
                "        // 创建线程池对象\n" +
                "        ExecutorService service = Executors.newFixedThreadPool(2);//包含2个线程对象\n" +
                "        // 创建Runnable实例对象\n" +
                "        MyRunnable r = new MyRunnable();\n" +
                "\n" +
                "        //自己创建线程对象的方式\n" +
                "        // Thread t = new Thread(r);\n" +
                "        // t.start(); ---> 调用MyRunnable中的run()\n" +
                "\n" +
                "        // 从线程池中获取线程对象,然后调用MyRunnable中的run()\n" +
                "        service.submit(r);\n" +
                "        // 再获取个线程对象，调用MyRunnable中的run()\n" +
                "        service.submit(r);\n" +
                "        service.submit(r);\n" +
                "        // 注意：submit方法调用结束后，程序并不终止，是因为线程池控制了线程的关闭。\n" +
                "        // 将使用完的线程又归还到了线程池中\n" +
                "        // 关闭线程池\n" +
                "        //service.shutdown();\n" +
                "    }\n" +
                "}\n" +
                "~~~\n" +
                "\n" +
                "\n" +
                "\n" +
                "# 第三章 Lambda表达式\n" +
                "\n" +
                "## 3.1 函数式编程思想概述\n" +
                "\n" +
                "![](img/03-Overview.png)\n" +
                "\n" +
                "在数学中，**函数**就是有输入量、输出量的一套计算方案，也就是“拿什么东西做什么事情”。相对而言，面向对象过分强调“必须通过对象的形式来做事情”，而函数式思想则尽量忽略面向对象的复杂语法——**强调做什么，而不是以什么形式做**。\n" +
                "\n" +
                "面向对象的思想:\n" +
                "\n" +
                "\u200B\t做一件事情,找一个能解决这个事情的对象,调用对象的方法,完成事情.\n" +
                "\n" +
                "函数式编程思想:\n" +
                "\n" +
                "\u200B\t只要能获取到结果,谁去做的,怎么做的都不重要,重视的是结果,不重视过程\n" +
                "\n" +
                "## 3.2 冗余的Runnable代码\n" +
                "\n" +
                "### 传统写法\n" +
                "\n" +
                "当需要启动一个线程去完成任务时，通常会通过`java.lang.Runnable`接口来定义任务内容，并使用`java.lang.Thread`类来启动该线程。代码如下：\n" +
                "\n" +
                "```java\n" +
                "public class Demo01Runnable {\n" +
                "\tpublic static void main(String[] args) {\n" +
                "    \t// 匿名内部类\n" +
                "\t\tRunnable task = new Runnable() {\n" +
                "\t\t\t@Override\n" +
                "\t\t\tpublic void run() { // 覆盖重写抽象方法\n" +
                "\t\t\t\tSystem.out.println(\"多线程任务执行！\");\n" +
                "\t\t\t}\n" +
                "\t\t};\n" +
                "\t\tnew Thread(task).start(); // 启动线程\n" +
                "\t}\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "本着“一切皆对象”的思想，这种做法是无可厚非的：首先创建一个`Runnable`接口的匿名内部类对象来指定任务内容，再将其交给一个线程来启动。\n" +
                "\n" +
                "### 代码分析\n" +
                "\n" +
                "对于`Runnable`的匿名内部类用法，可以分析出几点内容：\n" +
                "\n" +
                "- `Thread`类需要`Runnable`接口作为参数，其中的抽象`run`方法是用来指定线程任务内容的核心；\n" +
                "- 为了指定`run`的方法体，**不得不**需要`Runnable`接口的实现类；\n" +
                "- 为了省去定义一个`RunnableImpl`实现类的麻烦，**不得不**使用匿名内部类；\n" +
                "- 必须覆盖重写抽象`run`方法，所以方法名称、方法参数、方法返回值**不得不**再写一遍，且不能写错；\n" +
                "- 而实际上，**似乎只有方法体才是关键所在**。\n" +
                "\n" +
                "## 3.3 编程思想转换\n" +
                "\n" +
                "### 做什么，而不是怎么做\n" +
                "\n" +
                "我们真的希望创建一个匿名内部类对象吗？不。我们只是为了做这件事情而**不得不**创建一个对象。我们真正希望做的事情是：将`run`方法体内的代码传递给`Thread`类知晓。\n" +
                "\n" +
                "**传递一段代码**——这才是我们真正的目的。而创建对象只是受限于面向对象语法而不得不采取的一种手段方式。那，有没有更加简单的办法？如果我们将关注点从“怎么做”回归到“做什么”的本质上，就会发现只要能够更好地达到目的，过程与形式其实并不重要。\n" +
                "\n" +
                "### 生活举例\n" +
                "\n" +
                "![](img/01-交通方式.png)\n" +
                "\n" +
                "当我们需要从北京到上海时，可以选择高铁、汽车、骑行或是徒步。我们的真正目的是到达上海，而如何才能到达上海的形式并不重要，所以我们一直在探索有没有比高铁更好的方式——搭乘飞机。\n" +
                "\n" +
                "![](img/02-Lambda.png)\n" +
                "\n" +
                "而现在这种飞机（甚至是飞船）已经诞生：2014年3月Oracle所发布的Java 8（JDK 1.8）中，加入了**Lambda表达式**的重量级新特性，为我们打开了新世界的大门。\n" +
                "\n" +
                "## 3.4 体验Lambda的更优写法\n" +
                "\n" +
                "借助Java 8的全新语法，上述`Runnable`接口的匿名内部类写法可以通过更简单的Lambda表达式达到等效：\n" +
                "\n" +
                "```java\n" +
                "public class Demo02LambdaRunnable {\n" +
                "\tpublic static void main(String[] args) {\n" +
                "\t\tnew Thread(() -> System.out.println(\"多线程任务执行！\")).start(); // 启动线程\n" +
                "\t}\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "这段代码和刚才的执行效果是完全一样的，可以在1.8或更高的编译级别下通过。从代码的语义中可以看出：我们启动了一个线程，而线程任务的内容以一种更加简洁的形式被指定。\n" +
                "\n" +
                "不再有“不得不创建接口对象”的束缚，不再有“抽象方法覆盖重写”的负担，就是这么简单！\n" +
                "\n" +
                "## 3.5 回顾匿名内部类\n" +
                "\n" +
                "Lambda是怎样击败面向对象的？在上例中，核心代码其实只是如下所示的内容：\n" +
                "\n" +
                "```java\n" +
                "() -> System.out.println(\"多线程任务执行！\")\n" +
                "```\n" +
                "\n" +
                "为了理解Lambda的语义，我们需要从传统的代码起步。\n" +
                "\n" +
                "### 使用实现类\n" +
                "\n" +
                "要启动一个线程，需要创建一个`Thread`类的对象并调用`start`方法。而为了指定线程执行的内容，需要调用`Thread`类的构造方法：\n" +
                "\n" +
                "* `public Thread(Runnable target)`\n" +
                "\n" +
                "为了获取`Runnable`接口的实现对象，可以为该接口定义一个实现类`RunnableImpl`：\n" +
                "\n" +
                "```java\n" +
                "public class RunnableImpl implements Runnable {\n" +
                "\t@Override\n" +
                "\tpublic void run() {\n" +
                "\t\tSystem.out.println(\"多线程任务执行！\");\n" +
                "\t}\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "然后创建该实现类的对象作为`Thread`类的构造参数：\n" +
                "\n" +
                "```java\n" +
                "public class Demo03ThreadInitParam {\n" +
                "\tpublic static void main(String[] args) {\n" +
                "\t\tRunnable task = new RunnableImpl();\n" +
                "\t\tnew Thread(task).start();\n" +
                "\t}\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "### 使用匿名内部类\n" +
                "\n" +
                "这个`RunnableImpl`类只是为了实现`Runnable`接口而存在的，而且仅被使用了唯一一次，所以使用匿名内部类的语法即可省去该类的单独定义，即匿名内部类：\n" +
                "\n" +
                "```java\n" +
                "public class Demo04ThreadNameless {\n" +
                "\tpublic static void main(String[] args) {\n" +
                "\t\tnew Thread(new Runnable() {\n" +
                "\t\t\t@Override\n" +
                "\t\t\tpublic void run() {\n" +
                "\t\t\t\tSystem.out.println(\"多线程任务执行！\");\n" +
                "\t\t\t}\n" +
                "\t\t}).start();\n" +
                "\t}\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "### 匿名内部类的好处与弊端\n" +
                "\n" +
                "一方面，匿名内部类可以帮我们**省去实现类的定义**；另一方面，匿名内部类的语法——**确实太复杂了！**\n" +
                "\n" +
                "### 语义分析\n" +
                "\n" +
                "仔细分析该代码中的语义，`Runnable`接口只有一个`run`方法的定义：\n" +
                "\n" +
                "* `public abstract void run();`\n" +
                "\n" +
                "即制定了一种做事情的方案（其实就是一个函数）：\n" +
                "\n" +
                "* **无参数**：不需要任何条件即可执行该方案。\n" +
                "* **无返回值**：该方案不产生任何结果。\n" +
                "* **代码块**（方法体）：该方案的具体执行步骤。\n" +
                "\n" +
                "同样的语义体现在`Lambda`语法中，要更加简单：\n" +
                "\n" +
                "```java\n" +
                "() -> System.out.println(\"多线程任务执行！\")\n" +
                "```\n" +
                "\n" +
                "* 前面的一对小括号即`run`方法的参数（无），代表不需要任何条件；\n" +
                "* 中间的一个箭头代表将前面的参数传递给后面的代码；\n" +
                "* 后面的输出语句即业务逻辑代码。\n" +
                "\n" +
                "## 3.6 Lambda标准格式\n" +
                "\n" +
                "Lambda省去面向对象的条条框框，格式由**3个部分**组成：\n" +
                "\n" +
                "* 一些参数\n" +
                "* 一个箭头\n" +
                "* 一段代码\n" +
                "\n" +
                "Lambda表达式的**标准格式**为：\n" +
                "\n" +
                "```\n" +
                "(参数类型 参数名称) -> { 代码语句 }\n" +
                "```\n" +
                "\n" +
                "格式说明：\n" +
                "\n" +
                "* 小括号内的语法与传统方法参数列表一致：无参数则留空；多个参数则用逗号分隔。\n" +
                "* `->`是新引入的语法格式，代表指向动作。\n" +
                "* 大括号内的语法与传统方法体要求基本一致。\n" +
                "\n" +
                "## 3.7 练习：使用Lambda标准格式（无参无返回）\n" +
                "\n" +
                "### 题目\n" +
                "\n" +
                "给定一个厨子`Cook`接口，内含唯一的抽象方法`makeFood`，且无参数、无返回值。如下：\n" +
                "\n" +
                "```java\n" +
                "public interface Cook {\n" +
                "    void makeFood();\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "在下面的代码中，请使用Lambda的**标准格式**调用`invokeCook`方法，打印输出“吃饭啦！”字样：\n" +
                "\n" +
                "```java\n" +
                "public class Demo05InvokeCook {\n" +
                "    public static void main(String[] args) {\n" +
                "        // TODO 请在此使用Lambda【标准格式】调用invokeCook方法\n" +
                "    }\n" +
                "\n" +
                "    private static void invokeCook(Cook cook) {\n" +
                "        cook.makeFood();\n" +
                "    }\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "### 解答\n" +
                "\n" +
                "```java\n" +
                "public static void main(String[] args) {\n" +
                "    invokeCook(() -> {\n" +
                "      \tSystem.out.println(\"吃饭啦！\");\n" +
                "    });\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "> 备注：小括号代表`Cook`接口`makeFood`抽象方法的参数为空，大括号代表`makeFood`的方法体。\n" +
                "\n" +
                "## 3.8 Lambda的参数和返回值\n" +
                "\n" +
                "```\n" +
                "需求:\n" +
                "    使用数组存储多个Person对象\n" +
                "    对数组中的Person对象使用Arrays的sort方法通过年龄进行升序排序\n" +
                "```\n" +
                "\n" +
                "下面举例演示`java.util.Comparator<T>`接口的使用场景代码，其中的抽象方法定义为：\n" +
                "\n" +
                "* `public abstract int compare(T o1, T o2);`\n" +
                "\n" +
                "当需要对一个对象数组进行排序时，`Arrays.sort`方法需要一个`Comparator`接口实例来指定排序的规则。假设有一个`Person`类，含有`String name`和`int age`两个成员变量：\n" +
                "\n" +
                "```java\n" +
                "public class Person { \n" +
                "    private String name;\n" +
                "    private int age;\n" +
                "    \n" +
                "    // 省略构造器、toString方法与Getter Setter \n" +
                "}\n" +
                "```\n" +
                "\n" +
                "### 传统写法\n" +
                "\n" +
                "如果使用传统的代码对`Person[]`数组进行排序，写法如下：\n" +
                "\n" +
                "```java\n" +
                "import java.util.Arrays;\n" +
                "import java.util.Comparator;\n" +
                "\n" +
                "public class Demo06Comparator {\n" +
                "    public static void main(String[] args) {\n" +
                "      \t// 本来年龄乱序的对象数组\n" +
                "        Person[] array = {\n" +
                "        \tnew Person(\"古力娜扎\", 19),\n" +
                "        \tnew Person(\"迪丽热巴\", 18),\n" +
                "       \t\tnew Person(\"马尔扎哈\", 20) };\n" +
                "\n" +
                "      \t// 匿名内部类\n" +
                "        Comparator<Person> comp = new Comparator<Person>() {\n" +
                "            @Override\n" +
                "            public int compare(Person o1, Person o2) {\n" +
                "                return o1.getAge() - o2.getAge();\n" +
                "            }\n" +
                "        };\n" +
                "        Arrays.sort(array, comp); // 第二个参数为排序规则，即Comparator接口实例\n" +
                "\n" +
                "        for (Person person : array) {\n" +
                "            System.out.println(person);\n" +
                "        }\n" +
                "    }\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "这种做法在面向对象的思想中，似乎也是“理所当然”的。其中`Comparator`接口的实例（使用了匿名内部类）代表了“按照年龄从小到大”的排序规则。\n" +
                "\n" +
                "### 代码分析\n" +
                "\n" +
                "下面我们来搞清楚上述代码真正要做什么事情。\n" +
                "\n" +
                "- 为了排序，`Arrays.sort`方法需要排序规则，即`Comparator`接口的实例，抽象方法`compare`是关键；\n" +
                "- 为了指定`compare`的方法体，**不得不**需要`Comparator`接口的实现类；\n" +
                "- 为了省去定义一个`ComparatorImpl`实现类的麻烦，**不得不**使用匿名内部类；\n" +
                "- 必须覆盖重写抽象`compare`方法，所以方法名称、方法参数、方法返回值**不得不**再写一遍，且不能写错；\n" +
                "- 实际上，**只有参数和方法体才是关键**。\n" +
                "\n" +
                "### Lambda写法\n" +
                "\n" +
                "```java\n" +
                "import java.util.Arrays;\n" +
                "\n" +
                "public class Demo07ComparatorLambda {\n" +
                "    public static void main(String[] args) {\n" +
                "        Person[] array = {\n" +
                "          \tnew Person(\"古力娜扎\", 19),\n" +
                "          \tnew Person(\"迪丽热巴\", 18),\n" +
                "          \tnew Person(\"马尔扎哈\", 20) };\n" +
                "\n" +
                "        Arrays.sort(array, (Person a, Person b) -> {\n" +
                "          \treturn a.getAge() - b.getAge();\n" +
                "        });\n" +
                "\n" +
                "        for (Person person : array) {\n" +
                "            System.out.println(person);\n" +
                "        }\n" +
                "    }\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "## 3.9 练习：使用Lambda标准格式（有参有返回）\n" +
                "\n" +
                "### 题目\n" +
                "\n" +
                "给定一个计算器`Calculator`接口，内含抽象方法`calc`可以将两个int数字相加得到和值：\n" +
                "\n" +
                "```java\n" +
                "public interface Calculator {\n" +
                "    int calc(int a, int b);\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "在下面的代码中，请使用Lambda的**标准格式**调用`invokeCalc`方法，完成120和130的相加计算：\n" +
                "\n" +
                "```java\n" +
                "public class Demo08InvokeCalc {\n" +
                "    public static void main(String[] args) {\n" +
                "        // TODO 请在此使用Lambda【标准格式】调用invokeCalc方法来计算120+130的结果ß\n" +
                "    }\n" +
                "\n" +
                "    private static void invokeCalc(int a, int b, Calculator calculator) {\n" +
                "        int result = calculator.calc(a, b);\n" +
                "        System.out.println(\"结果是：\" + result);\n" +
                "    }\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "### 解答\n" +
                "\n" +
                "```java\n" +
                "public static void main(String[] args) {\n" +
                "    invokeCalc(120, 130, (int a, int b) -> {\n" +
                "      \treturn a + b;\n" +
                "    });\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "> 备注：小括号代表`Calculator`接口`calc`抽象方法的参数，大括号代表`calc`的方法体。\n" +
                "\n" +
                "## 3.10 Lambda省略格式\n" +
                "\n" +
                "### 可推导即可省略\n" +
                "\n" +
                "Lambda强调的是“做什么”而不是“怎么做”，所以凡是可以根据上下文推导得知的信息，都可以省略。例如上例还可以使用Lambda的省略写法：\n" +
                "\n" +
                "```java\n" +
                "public static void main(String[] args) {\n" +
                "  \tinvokeCalc(120, 130, (a, b) -> a + b);\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "### 省略规则\n" +
                "\n" +
                "在Lambda标准格式的基础上，使用省略写法的规则为：\n" +
                "\n" +
                "1. 小括号内参数的类型可以省略；\n" +
                "2. 如果小括号内**有且仅有一个参**，则小括号可以省略；\n" +
                "3. 如果大括号内**有且仅有一个语句**，则无论是否有返回值，都可以省略大括号、return关键字及语句分号。\n" +
                "\n" +
                "> 备注：掌握这些省略规则后，请对应地回顾本章开头的多线程案例。\n" +
                "\n" +
                "## 3.11 练习：使用Lambda省略格式\n" +
                "\n" +
                "### 题目\n" +
                "\n" +
                "仍然使用前文含有唯一`makeFood`抽象方法的厨子`Cook`接口，在下面的代码中，请使用Lambda的**省略格式**调用`invokeCook`方法，打印输出“吃饭啦！”字样：\n" +
                "\n" +
                "```java\n" +
                "public class Demo09InvokeCook {\n" +
                "    public static void main(String[] args) {\n" +
                "        // TODO 请在此使用Lambda【省略格式】调用invokeCook方法\n" +
                "    }\n" +
                "\n" +
                "    private static void invokeCook(Cook cook) {\n" +
                "        cook.makeFood();\n" +
                "    }\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "### 解答\n" +
                "\n" +
                "```java\n" +
                "public static void main(String[] args) {\n" +
                "  \tinvokeCook(() -> System.out.println(\"吃饭啦！\"));\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "## 3.12 Lambda的使用前提\n" +
                "\n" +
                "\n" +
                "\n" +
                "Lambda的语法非常简洁，完全没有面向对象复杂的束缚。但是使用时有几个问题需要特别注意：\n" +
                "\n" +
                "1. 使用Lambda必须具有接口，且要求**接口中有且仅有一个抽象方法**。\n" +
                "   无论是JDK内置的`Runnable`、`Comparator`接口还是自定义的接口，只有当接口中的抽象方法存在且唯一时，才可以使用Lambda。\n" +
                "2. 使用Lambda必须具有**上下文推断**。\n" +
                "   也就是方法的参数或局部变量类型必须为Lambda对应的接口类型，才能使用Lambda作为该接口的实例。\n" +
                "\n" +
                "> 备注：有且仅有一个抽象方法的接口，称为“**函数式接口**”。\n" +
                "\n";
        for(int i = 0; i<200;i++){
            Blog blog = new Blog();
            blog.setUserId((int)(Math.random()*53+12));
            blog.setBlogtypeId((int)(Math.random()*4+10));
            blog.setStatus((int)(Math.random()*2+1));
            blog.setCompletetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            blog.setTitle("博客测试用例标题"+i);
            blog.setContent(contents[(int)(Math.random()*6)]);
            blogService.saveBlog(blog);
        }
    }
}
