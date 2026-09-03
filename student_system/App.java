package student_system;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static  void main(String[] args){
        ArrayList<User> users=new ArrayList<>();

        Scanner sc= new Scanner(System.in);
        while(true){
            System.out.println("欢迎使用");
            System.out.println("选择操作，1登录，2注册，3忘记密码,4退出程序");
            String choose= sc.next();
            switch (choose){
                case "1" -> Login(users);
                case "2" -> Regester(users);
                case "3" -> ForgetPassword(users);
                case "4" ->{
                    System.out.println("感谢使用");
                    System.exit(0);

                }
                default -> System.out.println("请检查输入");

            }
        }

    }

    private static void Login(ArrayList<User> users){  //函数式
        System.out.println("登录");
        Scanner sc = new Scanner(System.in);//此时什么都没发生，只是创建了一个对象，关联了标准输入流

            System.out.println("请输入用户名");
            String username= sc.next();
            if(!my_contains(users,username)){
                System.out.println("当前用户未注册！");
                return;
            }
        while(true) {
            System.out.println("请输入密码");
            String passwd = sc.next();

            //校验验证码
            while (true) {
                String code = getCode();
                System.out.println("请输入验证码 :" + code);
                String now = sc.next();
                if (!now.equals(code)) {
                    System.out.println("验证错误！");
                    continue;
                } else {
                    break;
                }
            }
            User user = new User(username, passwd, null, null);
            if (checkrightpasswd(users, user)) {
                System.out.println("登陆成功");
                StudentSystem system = new StudentSystem();
                system.Start();
                break;
            } else {
                System.out.println("密码错误");
                continue;
            }
        }

    }

    private  static  boolean checkrightpasswd(ArrayList<User> users, User user){
        for(User u :users) {
            if (u.getName().equals(user.getName())) {
                if (u.getPassword().equals(user.getPassword())) return true;
            }
        }
        return false;
    }

    private static boolean CheckUsername(String username) {
        // 只允许字母数字组合，不允许其他字符出现
        int len = username.length();
        if(len<3||len>15) return false;
        int[] num={0,0,0};
        char[] tmp = username.toCharArray();
        for(int i=0;i<tmp.length;i++){
            char c=tmp[i];
            if(c>='a'&&c<='z') num[0]++;
            else if(c>='A'&&c<='Z') num[1]++;
            else if(c>='0'&&c<='9') num[2]++;
            else return false;
        }
        if(num[2]==len)  return false;
        return true;
    }

    private static boolean my_contains(ArrayList<User> users, String name){
        for(User user: users) {
            String username = user.getName();
            if(username.equals(name)){
                return true;
            }
        }
        return false;
    }

    private static void Regester(ArrayList<User> users) {
        //用户名，密码，身份证，手机号

        System.out.println("注册");
        Scanner sc= new Scanner(System.in);
        //后续需要检测输入是否合法，所以不合法时就会重复用到这段输入代码逻辑
        String passwd;
        String id;
        String phone;
        String username;
            while(true) {
                System.out.println("请输入用户名");
                username = sc.next();
                boolean result = CheckUsername(username);
                if (!result) {
                    System.out.println("不满足格式请重新输入");
                    continue;
                }
                boolean flag= my_contains(users, username);
                if(flag) {
                    System.out.println("已经存在该用户");
                    continue;
                }else{
                    break;
               }
            }

            while (true) {
                System.out.println("请输入密码");
                passwd = sc.next();
                System.out.println("请再次输入密码");
                String againpswd = sc.next();
                if (!passwd.equals(againpswd)) {

                    System.out.println("密码不一致，请重新输入");
                    continue;
                }
                else{
                    break;
                }
            }


            //录入id
            while(true){
                System.out.println("输入id");
                id= sc.next();
                boolean flag= Checkid(id);
                if(!flag) {
                    System.out.println("请重新输入");
                    continue;
                }
                break;
            }

            //录入手机

            while(true){
                System.out.println("输入手机");
                phone =sc.next();
                boolean flag= checkphone(phone);
                if(!flag) {
                    System.out.println("重新输入手机号");
                    continue;

                }else break;

            }
            User u = new User(username, passwd, id, phone);
            users.add(u);
            System.out.println("成功注册");
    }

    private static boolean checkphone(String phone) {
        int len = phone.length();
        if(len!=11) return false;

        if(phone.startsWith("0")) return false;
        char[] tmp = phone.toCharArray();
        for(char c : tmp){
            if(!(c>='0'&&c<='9')) return false;
        }
        return true;
    }

    private static boolean Checkid(String id) {
        int len = id.length();
        if(len!=18) return false;

        boolean flag = id.startsWith("0");
        if(flag) return false;

        char[] tmp = id.toCharArray();
        for(int i=0;i<tmp.length-1;i++){
            if(!(tmp[i]>='0'&&tmp[i]<='9'))  return false;
        }
        if(tmp[17]>='0'&&tmp[17]<='9'||tmp[17]=='X'||tmp[17]=='x') return true;
        else return false;

    }

    private static int findIndex(ArrayList<User>  users, String username){
        for(int i = 0 ; i<users.size(); i++){
            User user = users.get(i);
            if(user.getName().equals(username)){
                return i;
            }
        }
        return -1;  //无用户
    }

    private static void ForgetPassword(ArrayList<User> users){
        System.out.println("忘记密码");
        Scanner sc= new Scanner(System.in);
        System.out.println("请输入忘记密码用户名");
        String username=sc.next();
        if(my_contains(users, username)){
            System.out.println("不存在，请先注册");
            return;
        }
        System.out.println("请输入身份证");
        String id=sc.next();
        System.out.println("输入手机号");
        String phone = sc.next();
        //根据身份证和手机号验证是否与当前账户一致，给出密码，但是我要先拿到用户对象,users是个数组，找下标

        int index= findIndex(users,username);
        User user = users.get(index);
        String true_phone = user.getPhoneNumber();
        String true_id = user.getPersonId();

        if(!true_phone.equals(phone)&&true_id.equalsIgnoreCase(id)){
            System.out.println("身份证号或手机号有误");
            return ;
        }
        String newpswd;
        String againPswd;
        while(true) {
            System.out.println("请输入新的密码");
            newpswd = sc.next();
            System.out.println("请再次输入");
            againPswd = sc.next();
            if (newpswd.equals(againPswd)) {
                break;
            } else {
                System.out.println("不一致，请重新输入");
            }
        }
        System.out.println("修改成功");
        user.setPassword(newpswd);
    }

    static ArrayList<Character> word= new ArrayList<>();

    static {
        for (int i = 0; i < 26; i++) {
            word.add((char) ('a' + i));
            word.add((char) ('A' + i));
        }
    }

    private  static  String getCode(){
        Random r = new Random();
        StringBuilder ans= new StringBuilder();
        for(int i=0;i<4;i++){
            int index= r.nextInt(word.size());
            ans.append(word.get(index));
        }

        int num=r.nextInt(10);
        ans.append(num);
        char[] tmp = ans.toString().toCharArray();
        int ranindex= r.nextInt(tmp.length);

        char temp= tmp[tmp.length-1];
        tmp[tmp.length-1]=tmp[ranindex];
        tmp[ranindex]=temp;

        return new String(tmp);
    }
}
