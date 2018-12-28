"test" 

### 查看页面
<http://localhost:8080/listpage>
<http://localhost:8080/swagger-ui.html>

### 查看具体每个bug
<http://localhost:8080/findByIdpage?BugListId=34>


### 介绍重定向
<https://www.cnblogs.com/kxkl123/p/7800967.html>


### 增加了log记录---20181221

### 关于log的文章
<http://www.cnblogs.com/Mrchengs/p/10121937.html>

##### git push -u origin master -f
echo "# ttt" >> README.md
git init
git add README.md
git commit -m "first commit"
git remote add origin git@github.com:JasmineQian/ttt.git
git push -u origin master


1、先删除远程 Git 仓库

$ git remote rm origin

2、再添加远程 Git 仓库

$ git remote add origin git@github.com:FBing/java-code-generator

如果执行 git remote rm origin 报错的话，我们可以手动修改gitconfig文件的内容

$ vi .git/config


##### git commit -m "add logger"


#### git remote add origin git@github.com:JasmineQian/SpringbootDemoDay03.git


### 20181222,用家里电脑，数据库从mssql转到MySQL，将更新时间getdate()等sql中获取时间，增加了获取系统时间
并转成字符串，存到db中
修复了mssql和mysql  ###以及-----区别，删除了这些[],``等


### 20181222,给更新页面，将下拉框等值回传到页面，默认回传到值为被选中状态
### 遇到一个错，因为笔记本没键盘，不下心一处生成几个字符，许久没有看出来，其实错就是因为这儿；试了很久，多种方式失败。
最终意识到这个错，修正了，还要讲之前到尝试再尝试一遍。

###  仔细看报错，看清楚，看清楚，看清楚


##### mvn clean install



 <dependency>
            <groupId>javax.el</groupId>
            <artifactId>javax.el-api</artifactId>
            <version>2.2.4</version>
</dependency>

<dependency>
            <groupId>org.glassfish.web</groupId>
            <artifactId>javax.el</artifactId>
            <version>2.2.4</version>
</dependency>

### 如何打包成为 war 包
##### 第一步：pom.xml 文件中，打包方式需要修改成war    

<packaging>war</packaging>
  

##### 第二步：pom.xml 文件中，spring-boot-starter-web下需要移除自带的tomcat

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <!-- 移除嵌入式tomcat插件 -->
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>


##### 第三步：修改启动类，继承SpringBootServletInitializer 
@SpringBootApplication
@EnableScheduling
public class Application extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(Application.class);
    }
}

##### 第四步：pom.xml 中添加依赖，否则报错
cannot access javax.servlet.ServletException
[ERROR] class file for javax.servlet.ServletException not found

<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>3.1.0</version>
    <scope>provided</scope>
</dependency>



        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <!--&lt;!&ndash; 移除嵌入式tomcat插件 &ndash;&gt;-->
            <!--<exclusions>-->
            <!--<exclusion>-->
            <!--<groupId>org.springframework.boot</groupId>-->
            <!--<artifactId>spring-boot-starter-tomcat</artifactId>-->
            <!--</exclusion>-->
            <!--</exclusions>-->
        </dependency>


        <!--打包为war包的时候-->
        <!--<dependency>-->
        <!--<groupId>javax.servlet</groupId>-->
        <!--<artifactId>javax.servlet-api</artifactId>-->
        <!--<version>3.1.0</version>-->
        <!--<scope>provided</scope>-->
        <!--</dependency>-->


