package com.Stack;

public class Calculator {
    public static void main(String[] args) {
        String expression = "3-2";
        //创建两个栈，数栈，符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        //定义需要的相关变量
        //用于扫描
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        //用于拼接多位数
        String keepNum = "";
        //将每次扫描到char保存到ch
        char ch = ' ';
        //while循环扫描expression
        while (true) {
            //依次得到epression的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是什么
            if (operStack.isOper(ch)) {
                //如果是运算符
                //判断当前的符号栈是否为空，
                if (!operStack.isEmpty()) {
                    //如果符号栈有操作符，就进行比较，如果当前的操作符优先级小于或等于
                    //栈中的操作符，就需要从数栈中pop出两个数，在符号栈中pop出一个符号
                    //计算结果，将结果放入数栈，然后将当前的操作符push符号栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //把运算结果push 入数栈
                        numStack.push(res);
                        //将当前运算符push 入符号栈
                        operStack.push(ch);
                    } else {
                        //如果当前的操作符优先级大于栈中的操作符，就直接入符号栈
                        operStack.push(ch);
                    }
                } else {
                    //如果为空直接入符号栈
                    operStack.push(ch);
                }
            } else {
                //如果是数，则直接入数栈
//                numStack.push(ch-48);
                //当处理多位数时，不能发现一个数就立即入栈，
                //在处理数时，需要向数后面再扫描一个数，如果是符号才入栈
                //因此我们需要定义一个变量字符串，用于拼接
                keepNum += ch;

                //如果ch已经是expression的最后一位，就直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入栈
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //如果后一位是运算符，则入栈
                        numStack.push(Integer.parseInt(keepNum));
                        //keepNum要清空
                        keepNum = "";
                    }
                }
            }
            //index+1,并判断是否扫描到epression最后，
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        //当表达式扫描完毕,就顺序的从数栈和符号栈中pop出相应的数好符号，并运行
        while (true) {
            //如果符号栈为空，则计算到最后的结果，数栈中只有一个数字（结果)
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        //将数栈最后的数取出，就是结果
        int res2 = numStack.pop();
        System.out.println("计算结果是：" + res2);
    }
}

class ArrayStack2 {
    //栈的大小
    private int maxSize;
    //数组，数组模拟栈，数据放在数组
    private int[] stack;
    //top 表示栈顶，初始化为-1
    private int top = -1;

    //返回当前栈顶的值
    public int peek() {
        return stack[top];
    }

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈pop
    public int pop() {
        //判断栈是否为空
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈的情况
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    //返回运算符的优先级
    //数字越大，优先级越大
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        //res存放计算的结果
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }


}
