import Mapper.StudentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.Student;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class hanry {
    public static void main(String[] args) throws IOException {
        while(true) {
            System.out.println("请输入指令");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            switch (command) {
                case "添加学生":
                    Student.addStudent();
                    break;
                case "查询学生":
                    Student.selectAll();
                    break;
                case "删除学生":
                    Student.deleteById();
            }
            if(command.equals("退出")){
                break;
            }
        }

    }
}
