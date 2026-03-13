import java.util.*;
import java.io.*;

public class Main{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        List<User> list = new ArrayList<>();

        for (int i = 0 ; i < n; i++){
            st = new StringTokenizer(br.readLine());
            User u = new User(Integer.parseInt(st.nextToken()), st.nextToken(), i);
            list.add(u);
        }

        Collections.sort(list, (u1, u2) ->{
            if (u1.age == u2.age)
                return u1.order - u2.order;
            return u1.age - u2.age;
        });


        for (User u: list)
            System.out.println(u.age + " " + u.name);
    }

    static class User{
        int age; String name; int order;
        public User(int age, String name, int order){
            this.age = age;
            this.name = name;
            this.order = order;
        }
    }
}