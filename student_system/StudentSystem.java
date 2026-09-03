package student_system;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentSystem {
    public StudentSystem(){

    }

    public static  void  Start(){
        loop : while(true) {
            System.out.println("-------------欢迎使用学生信息管理系统-------------");
            System.out.println("1：添加学生");
            System.out.println("2：删除学生");
            System.out.println("3：修改学生");
            System.out.println("4：查询学生");
            System.out.println("5：退出");

            Scanner sc = new Scanner(System.in);
            String choose = sc.next();
            switch (choose) {
                case "1" -> addStudent();
                case "2" -> deleteStudent();
                case "3" -> changeStudent();
                case "4" -> searchStudent();
                case "5" -> {
                    System.out.println("退出");
                    break loop;
                    //System.exit(0); 停止jvm虚拟机运行，第二种
                }
                default -> System.out.println("没有这个选项");
            }
        }
    }

    static ArrayList<Student> students = new ArrayList<>();

    public static  void addStudent(){
        Scanner sc = new Scanner(System.in);
        for(int i =0; i<2; i++) {
            System.out.println("请输入姓名");
            String name = sc.next();
            System.out.println("请输入身份证");
            String id = sc.next();
            System.out.println("请输入年龄");
            int age = sc.nextInt();
            System.out.println("请输入住址");
            String address = sc.next();
            Student student = new Student(id, name, age, address);
            if (checkStudent(students, student)==-1){//找到了
                students.add(student);
                System.out.println("添加成功");
                return;
            } else {
                if(i==1) {
                    System.out.println("请勿多次重复操作");
                    return;
                }
                System.out.println("学生已存在，请勿重复添加");
                continue;
            }
        }
    }

    public static int  checkStudent(ArrayList<Student> students ,  Student student){
        for(int i = 0 ; i < students.size();i++){
            //找到了返回下标，没有-1
            String hasid= students.get(i).getId();
            String id= student.getId();
            if(hasid.equalsIgnoreCase(id)){
                return i;
            }
        }
        return -1;
    }

    public static  void deleteStudent(){
        Scanner sc = new Scanner(System.in);


            System.out.println("请输入要删除学生姓名以及身份证号码");
            String name = sc.next();
            String id = sc.next();

            Student student = new Student(id, name, -1, null);
            int index = checkStudent(students, student);
            if (index == -1) {
                System.out.println("不存在该学生");
            } else {
                students.remove(index);
                System.out.println("删除成功");
                return;
            }

    }

    public static void searchStudent(){
        System.out.println("请输入想要查询学生的姓名以及id");
        Scanner sc = new Scanner(System.in);
        String name= sc.next();
        String id= sc.next();

        int index= checkStudent(students ,  new Student(id, name, -1 ,null));
        if(index==-1){
            System.out.println("不存在该学生");
            return;
        }else{
            System.out.println("查询成功，学生为：");
            Student s= students.get(index);
            System.out.println(s.getId()+" "+s.getName()+" "+s.getAge()+" "+s.getAddress());
        }
    }

    public static  void changeStudent(){

        System.out.println("请输入要修改的学生姓名以及id");
        Scanner sc= new Scanner(System.in);

        String name= sc.next();
        String id= sc.next();

        int index = checkStudent(students, new Student(id, name , -1 , null));
        if(index==-1){
            System.out.println("不存在该学生");
            return;
        }else{
            Student s= students.get(index);
            System.out.println("请输入年龄");
            int age= sc.nextInt();
            System.out.println("请输入新的住址");
            String address= sc.next();

            s.setAge(age);
            s.setAddress(address);
            System.out.println("修改成功");
        }
    }
}
