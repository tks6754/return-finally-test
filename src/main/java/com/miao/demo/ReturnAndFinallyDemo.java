package com.miao.demo;

/**
 * Created by Mu on 2016/7/17.
 */
public class ReturnAndFinallyDemo {

    public static void main(String[] args) {
        //System.out.println(ReturnAndFinallyDemo.test01());
        //System.out.println(ReturnAndFinallyDemo.test02());
        //System.out.println(ReturnAndFinallyDemo.test11());
        //System.out.println(ReturnAndFinallyDemo.test12());
        //System.out.println(ReturnAndFinallyDemo.test21());
        //System.out.println(ReturnAndFinallyDemo.test().getName());

        //test
        //test
    }


    /*   返回值类型为基本数据类型 开始   */

    //try中没有异常，有return；finally中没有return
    static int test01(){
        int i = 0;

        try {
            i++;
            System.out.println("try block, i=" + i);
            return i;
        }catch (Exception e){
            System.out.println("catch block, i=" +i);
            System.out.println(e.getMessage());
        }finally {
            i++;
            System.out.println("finally block, i=" + i);
        }
        /*finally中没有return时，则try-catch-finally块外必须有return，否则编译不通过
        什么时候该return会执行？当try-catch-finally中没有return时会执行（可通过注释try块中return i 测试）
         */
        return 100;

    }

    //try中没有出现异常，try中有return；finally中也有return
    static int test02(){
        int i = 0;

        try {
            i++;
            System.out.println("try block, i=" + i);
            return i;
        }catch (Exception e){
            System.out.println("catch block, i=" +i);
            System.out.println(e.getMessage());
        }finally {
            i++;
            System.out.println("finally block, i=" + i);
            return i;
        }
        //return 100; 会提示Unreachable statement
    }


    //try中存在异常;catch中有return；finally中没有return
    static int test11(){
        int i = 0;

        try{
            ++i;
            System.out.println("try block, i=" + i);
            int err = 1/0;
            return i; //return没有执行
        }catch (Exception e){
            ++i;
            System.out.println("catch block, i=" +i);
            return i;
        }finally {
            ++i;
            System.out.println("finally block, i=" + i);
        }
    }
    //try中存在异常；catch中有return；finally中也有return
    static int test12(){
        int i = 0;
        try{
            ++i;
            System.out.println("try block, i=" + i);
            int err = 1/0;
        }catch (Exception e){
            ++i;
            System.out.println("catch block, i=" +i);
            return i;
        }finally {
            ++i;
            System.out.println("finally block, i=" + i);
            return i;
        }
    }

    /*  返回值类型为基本数据类型  结束  */

    /*  返回值类型为引用数据类型 开始  */
    static String test21(){
        StringBuffer s = new StringBuffer("init");

        try {
            s.append(" try");
            System.out.println("try block, s=" + s);
            return String.valueOf(s);
        }catch (Exception e){
            s.append(" catch");
            System.out.println("catch block, s=" + s);
        }finally {
            s.append(" finally");
            System.out.println("finally block, s=" + s);
        }

        return "test21";
    }

    static Person test31(){
        Person p = new Person(23, "xiaoming");
        try {
            p.setName("try");
            System.out.println("try block, p.name = " + p.getName());
            return p;
        }catch (Exception e){
            p.setName("catch");
            System.out.println("catch block, p.name = " + p.getName());
            System.out.println(e.getMessage());
        }finally {
            p.setName("finally");
            System.out.println("finally block, p.name = " + p.getName());
        }

        return p;
    }

    /*  返回值为引用数据类型  结束  */


    /**
     * 结论：
     *  1、try-catch-finally可视为一个整体，一旦进入try，根据try出现异常情况来决定进入catch块，finally无论如何都会执行（无论try或catch中有没有return）
     *  2、finally中没有return时，try-catch-finally后必须要有return；
     *      此前提下，
     *      2.1、try-catch中return未执行，或没有return，则try-catch-finally后的return会执行
     *           且此时，finally中对“值”的操作会影响返回结果（没有写测试，毋庸置疑）
     *      2.2、try-catch中return执行，则try-catch-finally外的return不会执行
     *           此时，由返回值类型会有不同情况：
     *                  返回值是基本数据类型时，finally中对“值”操作不会影响try-catch中的返回；
     *                  返回值是引用数据类型时，finally中对“值”操作会影响try-catch中的返回（测String和StringBuffer时，操作不影响try-catch中的return，可能和字符串类的实现有关系）
     *  3、finally中出现return，try-catch-finally后不能出现return，方法在finally中return后结束，并且该return一定会覆盖try-catch中的return
     */



}
