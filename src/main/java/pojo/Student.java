package pojo;

import Mapper.StudentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class Student {


    private String student_name;
    private String gender;
    private String student_id;
    private int age;

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "student_name='" + student_name + '\'' +
                ", gender='" + gender + '\'' +
                ", student_id='" + student_id + '\'' +
                ", age=" + age +
                '}';
    }

    public static void addStudent() throws IOException {

        //1.加载mybatis的核心配置文件，获取sqlsessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


        //2.获取sqlsession对象，用于执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3.1获取UserMapper接口的代理对象
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        //封装对象
        Student student = new Student();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入学生学号------");
        student.setStudent_id(scanner.nextLine());
        System.out.println("请输入学生姓名------");
        student.setStudent_name(scanner.nextLine());
        System.out.println("请输入学生性别------");
        student.setGender(scanner.nextLine());
        System.out.println("请输入学生年龄------");
        student.setAge(scanner.nextInt());

        //添加到数据库
        studentMapper.addStudent(student);


        //提交事物
        sqlSession.commit();

        System.out.println("添加成功------");

        //4.释放资源
        sqlSession.close();
    }

    public static void selectAll() throws IOException {
        //1.加载mybatis的核心配置文件，获取sqlsessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


        //2.获取sqlsession对象，用于执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();


        //3.1获取UserMapper接口的代理对象
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        //将查询到的学生封装成对象
        List<Student> students = studentMapper.selectAll();
        System.out.println(students);
    }

    public static void deleteById() throws IOException {
        //1.加载mybatis的核心配置文件，获取sqlsessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


        //2.获取sqlsession对象，用于执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3.1获取UserMapper接口的代理对象
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);


        //封装要删除对象的学号
        Scanner scanner = new Scanner(System.in);
        Student student = new Student();
        System.out.println("请输入要删除的学生学号");
        student.setStudent_id(scanner.nextLine());
        studentMapper.deleteById(student);

        //提交事务
        sqlSession.commit();
        System.out.println("删除成功");


        sqlSession.close();
    }
}
