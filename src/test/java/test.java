public class test {
    public static void main(String[] args) {
        String s = "\\1\\2\\3";
        System.out.println(s);
        String a = "\\\\1\\\\2";
        String h = s.replaceAll(a,"1");
        System.out.println(h);
    }
}
