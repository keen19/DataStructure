package com.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //先定义逆波兰表达式
        //(3+4)x5-6 = 3 4 + 5 x 6 -
//        String suffixExpression ="30 4 + 5 * 6 -";
//
//
//
//        //将表达式放入ArrayList
//        //将ArrayList传递给一个方法，配合栈计算
//        List<String> list=getListString(suffixExpression);
//        System.out.println("list:"+list);
//
//
//        int result=calculate(list);
//        System.out.println("\n计算的结果是"+result);
        String expression="1+((2+3)*4)-5";
        //获得中缀表达式
        List<String> list = toInfixExpressionList(expression);
        System.out.println(list);
        //转换为后缀表达式
        List<String> parseSuffixExpressionList=parseSuffixExpressionList(list);
        System.out.println(parseSuffixExpressionList);
        System.out.printf("计算结果："+calculate(parseSuffixExpressionList));
    }

    //将中缀表达式转换成后缀表达式
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //定义两个栈
        Stack<String> s1=new Stack<>();
        List<String>  s2=new ArrayList<>();

        //遍历ls
        for (String item : ls) {
            //如果是一个数,加入s2
            if (item.matches("\\d+")){
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if ((item.equals(")"))) {
                //如果是右括号，不压入s1，,且依次弹出s1栈顶的运算符并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                //这里是判断s1栈顶是否是左括号
                while (!s1.peek().equals("(")) {
                    //弹出s1的运算符放入s2
                    s2.add(s1.pop());
                }
                s1.pop();//将左括号弹出，即消除括号
            }else {
                //当item的优先级小于等于栈顶运算符，将s1栈顶的运算符弹出压入到s2，
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //将item压入栈中
                s1.push(item);
            }
        }
        //将s1中剩余的运算符压入s2中
        while (s1.size()!=0){
            s2.add(s1.pop());
        }
        //注意这里是List，存放的就是逆波兰表达式
        return s2;
    }

    public static List<String> toInfixExpressionList(String s){
        //定义一个List，存放中缀表达式对应的内容
        List<String> ls = new ArrayList<>();
        //这是一个指针用于遍历中缀表达式字符串
        int i=0;
        //多位数的对接
        String str;
        //每遍历一个字符，就放入c
        char c;
        do {
            //如果C是一个非数字，我就需要加入到ls
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add(""+c);
                i++;//i后移
            }else {
                //如果是一个数，就需要考虑多位数
                str="";//先将str置换成空字符串
                while (i<s.length()&&(c=s.charAt(i))>=48&& (c = s.charAt(i)) <= 57){
                    str +=c;//拼接
                    i++;
                }
                ls.add(str);
            }
        }while (i<s.length());
        return ls;
    }

    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        List<String> list=new ArrayList<>();
        for (String s1 : split) {
            list.add(s1);
        }
        return list;
    }

    //完成对逆波兰表达式的运算

    /**
     * 从左到右扫描，将3 ， 4压入栈；
     * 遇到+运算符，弹出4 和3 ，计算出3+4的值，得到7 压入栈
     * 将5压入栈
     * 遇到*运算符 弹出5 和 7 计算5*7 得到35 压入栈
     * 将6压入栈
     * 最后是-运算符 计算35-6的值 得到最终结果
     */
    public static int calculate(List<String> ls) {
        //创建栈，只需要一个栈
        Stack<String> stack=new Stack<>();
        //遍历
        for (String l : ls) {
            //用正则表达式
            //匹配多位数
            if (l.matches("\\d+")) {
                //入栈
                stack.push(l);
            }else {
                //pop出两个数，并运算，压入栈
                int num2=Integer.parseInt(stack.pop());
                int num1=Integer.parseInt(stack.pop());
                int res=0;
                if (l.equals("+")) {
                    res=num1+num2;
                } else if (l.equals("-")) {
                    res = num1 - num2;
                } else if (l.equals("*")) {
                    res=num1*num2;
                } else if (l.equals("/")) {
                    res=num1/num2;
                }else {
                    throw new RuntimeException("运算符有误");
                }
                //把res压入栈
                stack.push(""+res);
            }
        }
        //最后留在stack中的数据是运算结果
        return Integer.parseInt(stack.pop());
    }
}
//创建一个类，写一个可以返回运算符优先级的方法
class Operation{
    private static int ADD=1;
    private static int SUB=1;
    private static int MUL=2;
    private static int DIV=2;

    //返回优先级数字
    public static int getValue(String operation) {
        int result=0;
        switch (operation) {
            case "+":
                result=ADD;
                break;
            case "-":
                result=SUB;
                break;
            case "*":
                result=MUL;
                break;
            case "/":
                result=DIV;
                break;
            default:
                System.out.println("不是一个运算符");
                break;
        }
        return result;
    }
}