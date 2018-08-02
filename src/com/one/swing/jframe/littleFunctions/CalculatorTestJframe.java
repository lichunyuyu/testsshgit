package com.one.swing.jframe.littleFunctions;

/**
 * Created by vtstar on 2018/1/17.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/** 计算器  加入运算优先级 */
public class CalculatorTestJframe extends JFrame implements ActionListener{

    /** 计算器上的 键的显示名字 */
    private final String[] KEYS = {"7", "8", "9", "/", "sqrt", "4", "5", "6", "*", "%",
                                        "1", "2", "3", "-", "1/x", "0", "+/-", ".", "+", "="};
    /** 计算器上的功能键 */
    private final String[] COMMAND = {"Backspace", "CE", "C"};
    /** 计算器左边的 M 键的名字*/
    private final String[] M = {" ","MC","MR", "MS", "M+"};

    /** 计算器上 键 的按钮 */
    private JButton keys[] = new JButton[KEYS.length];
    /** 计算器上 功能键 的按钮*/
    private JButton commands[] = new JButton[COMMAND.length];
    /** 计算器左边 M 键 的按钮*/
    private JButton m[] = new JButton[M.length];

    /** 计算器上 输入文本框 及 结果 文本框  -- “=”之后 将结果放入 输入文本框 ，并清空 结果文本框 */
    private JTextField inputText = new JTextField("0");
    private JTextField resultText = new JTextField("0");


    // 标志用户按得是否是 整个表达式的第一个数字 ，或者是运算符后的第一个数字
    private Boolean firstDigit = true;
    // 计算中间结果
    private double resultNum = 0.0;
    // 当前运算的 运算符
    private String operator = "=";
    // 操作是否合法
    private boolean operateValidFlag = true;

    // inputlist 最后一位 小数点拼接     在下一个 运算符 诞生之前 一直拼接
    private boolean spotwordFlag = false;

    // 存放输入框中的  操作符   有序       计算一次  清空一次           （使用了 下面的 ，这里没用 ）
    List<String> keylist = new ArrayList<String>();
    // 存放 输入框中的值                 计算一次  只保留最后的结果
    //List<Double> inputList = new ArrayList<Double>();
    List<String> inputList = new ArrayList<String>();// 需要放小数点


    List<Double> numbers = new ArrayList<Double>();
    List<Character> keystrings = new ArrayList<Character>();

    /**
     * 构造函数
     * */
    public CalculatorTestJframe(){
        super();
        //初始化组件   计算器
        initCompoent();
        //设置计算器的背景颜色
        this.setBackground(Color.CYAN);
        this.setTitle("计算器");
        //在屏幕（500,300） 处显示计算器
        this.setLocation(500,300);
        // 不需修改计算器的大小
        this.setResizable(false);
        //使计算器中的各组件大小合适   ----调整此窗口的大小,以适合其子组件的首选大小和布局
        this.pack();
    }

    /**
     * 初始化
     * */
    public void initCompoent(){
        // 文本框的内容采用右对齐
        inputText.setHorizontalAlignment(JTextField.RIGHT);
        resultText.setHorizontalAlignment(JTextField.RIGHT);
        // 不允许修改 结果文本框  输入文本框
        inputText.setEditable(false);
        resultText.setEditable(false);
        //设置文本框的背景颜色为
        inputText.setBackground(new Color(171,197,167));
        resultText.setBackground(new Color(171,197,167));
        // 设置文本框的大小
        inputText.setPreferredSize(new Dimension(300,30));
        resultText.setPreferredSize(new Dimension(300,30));

        // 初始化 计算器上的 键的 按钮 将其放在一个画板内
        JPanel calckeyPanel = new JPanel();
        // 采用网格 布局 管理器 ，4列 5行 ， 网格之间的水平方向间隔为3个像素 ， 垂直。。3个像素
        calckeyPanel.setLayout(new GridLayout(4,5,3,3));
        for(int i=0;i<KEYS.length;i++){
            keys[i] = new JButton(KEYS[i]);
            calckeyPanel.add(keys[i]);
            keys[i].setForeground(Color.blue);
        }
        // 运算键 用红色标示   其他键用蓝色标示
        keys[3].setForeground(Color.red);
        keys[8].setForeground(Color.red);
        keys[13].setForeground(Color.red);
        keys[18].setForeground(Color.red);
        keys[19].setForeground(Color.red);

        // 初始化 功能键   都用绿色标示  将其放在一个画板内
        JPanel commandsPanel = new JPanel();
        //使用网格布局器  1 行 3 列 ，  网格之间的 水平方向间隔为3个像素， 垂直。。3个像素
        commandsPanel.setLayout(new GridLayout(1,3,3,3));
        for(int i=0;i<COMMAND.length;i++){
            commands[i] = new JButton(COMMAND[i]);
            commandsPanel.add(commands[i]);
            commands[i].setForeground(Color.green);
        }

        // 初始化 M 键 按钮 都用 橘色标示 将其放在一个画板内
        JPanel mPanel = new JPanel();
        // 使用网格布局器 5 行 1 列
        mPanel.setLayout(new GridLayout(5,1,3,3));
        for(int i=0;i<M.length;i++){
            m[i] = new JButton(M[i]);
            mPanel.add(m[i]);
            m[i].setForeground(Color.orange);
        }

        // 下面进行计算器的整体布局 ， 将calckeys和command画板放在计算器的中部，
        // 将文本框放在北部，将calms画板放在计算器的西部。

         // 新建一个大画板 ，将上面建立的command 和 calckeys  画板放在该画板内
        JPanel panel1 = new JPanel();
        // 使用边界布局管理器  画板里组件 之间的 水平  垂直  方向上间隔都为3像素
        panel1.setLayout(new BorderLayout(3,3));
        panel1.add("North",commandsPanel);
        panel1.add("West",calckeyPanel);

        // 建立一个画板放文本框
        JPanel toptextPanel = new JPanel();
        //使用网格布局器 2行 1 列
        //toptextPanel.setLayout(new GridLayout(2,1,3,0));
        toptextPanel.setLayout(new BorderLayout(3,0));
        toptextPanel.add("North",inputText);
        toptextPanel.add("Center",resultText);

        //整体布局
        getContentPane().setLayout(new BorderLayout(3,5));
        getContentPane().add("North",toptextPanel);
        getContentPane().add("Center",panel1);
        getContentPane().add("West",mPanel);

        // 为每一个按钮添加时间侦听器
        //  都使用 同一个事件 监听器  即 本对象    ， 本类的声明中 有 implements ActionListener
        for(int i=0;i<KEYS.length;i++){
            keys[i].addActionListener(this);
        }
        for (int i = 0; i < COMMAND.length; i++) {
            commands[i].addActionListener(this);
        }
        for (int i = 0; i < M.length; i++) {
            m[i].addActionListener(this);
        }

    }

    /**
     * 处理事件
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
        // 获取时间源的标签
        String label = e.getActionCommand();
        if(label.equals(COMMAND[0])){
            //用户按了 Backspace  键
            _handleBackspace();
        }else if (label.equals(COMMAND[1])) {
            // 用户按了"CE"键
            resultText.setText("0");
        } else if (label.equals(COMMAND[2])) {
            // 用户按了"C"键
            _handleC();
        } else if (" 0123456789.".indexOf(label) >= 0) {
            // 用户按了数字键或者小数点键
            _handleNumber(label);
            // handlezero(zero);
        } else {
            // 用户按了运算符键
            _handleOperator(label);
        }

        // M 键  也 单独处理
    }

    /** 更新 --改写 keylist  与 inputlist  --判断 去掉 的 最后一个字符 是 数字 还是运算符* */
    public void _updateList(String lastIndexText){
        String lable = " 0123456789.";
        // 如果 是数字
        if(lable.indexOf(lastIndexText)>0){
             inputList.remove(inputList.size()-1);
        }else{
            keylist.remove(inputList.size()-1);
        }
    }

    /**
     * 处理Backspace 键被 按下的事件
     * */
    public void _handleBackspace(){
        String inputtext = inputText.getText();
        int i = inputtext.length();
        if(i>0){
            //判断 去掉的  字符 是 inputkey数字   还是 运算符
            String lastIndexText = inputtext.substring(i-1);
            _updateList(lastIndexText);

            System.out.println("inputtext="+inputtext);
            // 退格 将文本的最后一个字符去掉
            inputtext = inputtext.substring(0,i-1);
            inputText.setText(inputtext);
            _getNumberEnd();// 实时计算
            if(inputtext.length()==0){
                // 如果文本呢没有内容了 ，就初始化 计算器 的各种值
                inputText.setText("0");
                resultText.setText("0");
                firstDigit = true;
                operator = "=";
            }
        }else{
            //显示新的文本
            inputText.setText(inputtext);
        }

    }

    /**
     * 处理 C  键被按下的事件
     * */
    private void _handleC() {
        // 初始化计算器的各种值
        inputText.setText("0");
        resultText.setText("0");
        firstDigit = true;
        operator = "=";

        numbers.clear();
        keystrings.clear();
    }

    /** 判断 input 最后一个 小数点 后 有没有 数字*/
    public boolean _getlastspot(){
        boolean flag = false;
        int oldLenth = inputText.getText().length();
        int index = inputText.getText().lastIndexOf(".");
        if((oldLenth-1)>index){
            flag = true;
        }
        return flag;
    }
    /** 被除数 不能为 0  ，当输入0 时 ，判断最后一个运算符 是否为 /  除号*/
    public boolean _getlastdivided(){
        boolean flag = false;
//        int index = inputText.getText().lastIndexOf("/");
//        if(index<0){
//            flag = true;
//        }
        char op = inputText.getText().charAt(inputText.getText().length()-1);
        if(op=='/'){
            flag = true;
        }
        return flag;
    }
    /** 1/x  0 没有倒数 -- 判断最后一个值 是否为 0   或 是否 为 .  */
    public boolean _getlastbackwords(){
        boolean flag = false;
//        int index = inputText.getText().lastIndexOf("0");
//        if(index<0){
//            flag = true;
//        }
        return flag;
    }
    /** 当输入运算符时 判断最后一位 是否是 .  */
    public boolean _getkeylastspot(){
        boolean flag = false;
        int indexs = inputText.getText().length()-1;
        int index = inputText.getText().lastIndexOf(".");
        //if(index>0){
        if(index==indexs){
            flag = true;
            inputText.setText(inputText.getText().substring(0,inputText.getText().length()-1));
            //inputList.remove(inputList.size()-1); //  (1-18 因为有空字符串   不用去掉)
            // 也要去掉 加的 ""  空字符串运算符   (1-17已更改为  实时 更新inputlist 的值  故 不需要 加 空字符串)
            //keylist.remove(keylist.size()-1);
        }
        System.out.println("_getkeylastspot--inputText="+inputText.getText());
        return flag;
    }
    /** 当 刚输完 . 之后 又输入运算符时 ，更新inputlist最后一位*/
    public void _updatelastInputlist(){

        if(spotwordFlag==false){
            String lastword = inputList.get(inputList.size()-1);
            lastword = lastword+".";
            inputList.set(inputList.size()-1,lastword);
        }
    }
    /**
     * 处理 数字键被按下的事件
     * @parm key
     * */
    private void _handleNumber(String key) {
        boolean flagspot = _getlastspot();
        if(firstDigit){
            // 输入的第一个 数字
            inputText.setText(key);
            // 放进 inputlist
            //inputList.add(Double.valueOf(key));
            inputList.add(key);
            //resultText.setText(String.valueOf(_getNumberEnd()));
        }else if((key.equals(".")) && (inputText.getText().indexOf(".")<0)){
            // 输入的是小数点，并且之前没有小数点，则将小数点附在文本框的后面
            inputText.setText(inputText.getText() + ".");
            inputList.add(key);
            // 如果输入了点 .  就  在 运算符中 加 空白-空字符串 ""
            keylist.add("");
            spotwordFlag = false;
            resultText.setText(String.valueOf(_getNumberEnd()));
        }else if(key.equals(".")){     // (key.equals(".")) && (inputText.getText().indexOf(".")>0)  && flagspot==true
            // 输入的是小数点   并且之前有小数点     即 不会连续存在 两个小数点 （可以 . + .   不可以  . . ）
            if(flagspot==true){
                // 表明 小数点后还有别的 字符
                inputText.setText(inputText.getText() + ".");
                inputList.add(key);
                // 如果输入了点 .  就  在 运算符中 加 空白-空字符串 ""
                keylist.add(""); // 一个输入值  对应一个运算符
                //更新 inputlist 最后一位
                spotwordFlag = false;
                resultText.setText(String.valueOf(_getNumberEnd()));
            }
            // 否则  就 忽略该 小数点  （连续 两个 点 的情况）
        }else if(!key.equals(".")){
            //如果输入的不是小数点，则将数字附在结果文本框的后面
            boolean flag = _getlastdivided();
            if(key.equals("0") && (flag==true)){
                // 判断最后一个符号是否为 / 号 （除号）
                resultText.setText("除数不能为零");
            }else{
                inputText.setText(inputText.getText()+key);
                inputList.add(key);
                resultText.setText(String.valueOf(_getNumberEnd()));
            }
        }
        // 以后输入的肯定不是第一个 数字了
        firstDigit = false;
    }

    /**判断 inputText 最后一个字符是否 是 运算符 -- 如果是 就替换为最新的(即移除最后一个运算符，添加最新的)  */
    public void _updateLastkey(String key){
        String text = inputText.getText();
        char op = text.charAt(text.length()-1);
        if("0123456789.".indexOf(String.valueOf(op))<0){
            //text.replace(String.valueOf(op),key);  // 后面有加
            text = text.substring(0,text.length()-1);
            inputText.setText(text);
        }
    }
    /**
     * 处理 运算符 被按下的事件
     * @param key
     * */
    private void _handleOperator(String key) {

        // 判断文本框最后一位是否是小数点    对 值进行更新
        _getkeylastspot();
        // 判断 inputText 最后一个字符是否 是 运算符 -- 如果是 就替换为最新的
        _updateLastkey(key);
        if (operator.equals("/")) {
            // 除法运算
            // 如果当前结果文本框中的值等于0
            if (_getNumberFromText() == 0.0) {
                // 操作不合法
                operateValidFlag = false;
                resultText.setText("除数不能为零");
            } else {
                //先不计算
                //resultNum /= _getNumberFromText();
                // 先都 存放在 list  中  再一起计算
                inputText.setText(inputText.getText()+key);
                keylist.add(key);
                resultNum /= _getNumberFromText(); // 新实时计算   有新情况 再根据 inputText再重新计算
                //
                resultNum = _getNumberEnd();
            }
        } else if (operator.equals("1/x")) {
            boolean flag = _getlastbackwords();// 是否 前一位 是 0
            // 倒数运算
            if (resultNum == 0.0 || flag==true) {
                // 操作不合法
                operateValidFlag = false;
                resultText.setText("零没有倒数");
            } else {
                //resultNum = 1 / resultNum;
                // 先都 存放在 list  中  再一起计算
                inputText.setText(inputText.getText()+key);
                keylist.add(key);
                resultNum = 1 / resultNum;
                resultNum = _getNumberEnd();
            }
        } else if (operator.equals("+")) {
            // 加法运算
           // resultNum += _getNumberFromText();
            inputText.setText(inputText.getText()+key);
            keylist.add(key);
            resultNum += _getNumberFromText();
            resultNum = _getNumberEnd();
        } else if (operator.equals("-")) {
            // 减法运算
            //resultNum -= _getNumberFromText();
            inputText.setText(inputText.getText()+key);
            keylist.add(key);
            resultNum -= _getNumberFromText();
            resultNum = _getNumberEnd();
        } else if (operator.equals("*")) {
            // 乘法运算
            //resultNum *= _getNumberFromText();
            inputText.setText(inputText.getText()+key);
            keylist.add(key);
            resultNum *= _getNumberFromText();
            resultNum = _getNumberEnd();
        } else if (operator.equals("sqrt")) {
            // 平方根运算
            //resultNum = Math.sqrt(resultNum);
            inputText.setText(inputText.getText()+key);
            keylist.add(key);
            resultNum = Math.sqrt(resultNum);
            resultNum = _getNumberEnd();
        } else if (operator.equals("%")) {
            // 百分号运算，除以100
            //resultNum = resultNum / 100;
            inputText.setText(inputText.getText()+key);
            keylist.add(key);
            resultNum = resultNum / 100;
            resultNum = _getNumberEnd();
        } else if (operator.equals("+/-")) {
            // 正数负数运算
            //resultNum = resultNum * (-1);
            inputText.setText(inputText.getText()+key);
            keylist.add(key);
            resultNum = resultNum * (-1);
            resultNum = _getNumberEnd();
        } else if (operator.equals("=")) {
            // 赋值运算
            //resultNum = _getNumberFromText();
            inputText.setText(inputText.getText()+key);
            keylist.add(key);
            resultNum = _getNumberFromText();
            //总计算
            resultNum = _getNumberEnd();

        }
        System.out.println("resultNum===="+resultNum);
        if (operateValidFlag) {
            // 双精度浮点数的运算
            long t1;
            double t2;
            t1 = (long) resultNum;
            t2 = resultNum - t1;
            if (t2 == 0) {
                resultText.setText(String.valueOf(t1));
            } else {
                resultText.setText(String.valueOf(resultNum));
            }
        }
        // 运算符等于用户按的按钮
        operator = key;
        //firstDigit = true;
        operateValidFlag = true;
    }

    /**
     * 从结果文本框中获取数字
     * @return
     */
    private double _getNumberFromText() {
        double result = 0;
        try {
            result = Double.valueOf(resultText.getText()).doubleValue();
        } catch (NumberFormatException e) {
            System.out.println("_getNumberFromText---NumberFormatException");
        }
        return result;
    }
    /**
     * 获取 值   与  运算符 集合
     * */

    public void _getlist(){
        String andwords = "";
        System.out.println("inputText="+inputText.getText()+",inputText.getText().length="+inputText.getText().length());
        String text = inputText.getText();
        String textcharAt = "";
        for(int i=0;i<text.length();i++){
            if(i==0){
               //if("1234567890.".indexOf(text.substring(0,1))<0){
                textcharAt = String.valueOf(text.charAt(0));
                if(" 1234567890.".indexOf(textcharAt)<0){
                    //numbers.add((double) 0);  // .6
                    andwords = "0";
                }
            }
            //if(i<(text.length()-1)){
            if(i<(text.length())){
                System.out.println("text.charAt(i)------"+text.charAt(i));
                //if("1234567890.".indexOf(text.substring(i,i+1))>0){
                if(" 0123456789.".indexOf(String.valueOf(text.charAt(i)))>0){
                        //andwords+=text.substring(i,i+1);
                    if(andwords.equals("") && ".".equals(String.valueOf(text.charAt(i)))){
                        andwords = "0";
                    }
                    andwords+=String.valueOf(text.charAt(i));
                    System.out.println("andwords="+andwords);
                    if(i==(text.length()-1)){
                        numbers.add(Double.valueOf(andwords));
                        andwords = "";
                    }
                }else{
                    // 每次 遇到 运算符 就 停止拼接   并重置   andwords
                    numbers.add(Double.valueOf(andwords));
                    andwords = "";
                    if(text.charAt(i)!='='){
                        keystrings.add(text.charAt(i));
                    }
                }
            }
        }
        System.out.println("_getlist--numbers="+numbers);
        System.out.println("_getlist--keystrings"+keystrings);
    }
    /** 总计算 */
    private double _getNumberEnd(){
        // 获取 输入值   及  运算符
        _getlist();

        double result = 0;
        try{
            // 遍历计算 乘法和 除法
            for(int i=0;i<keystrings.size();i++){
                // 遍历 获取 运算子
                char op = keystrings.get(i);
                if(op=='*' || op=='/'){
                    // 移除这个运算子
                    Character remove = keystrings.remove(i);
                    // 需要在 同一位置 取出 对应的 运算数
                    Double double1 = numbers.remove(i);
                    System.out.println("double1==="+double1);
                    Double double2 = Double.valueOf(1);
                    //if(numbers.size()>0){
                    if(i<numbers.size()){
                        // 因为 list 的 特性  移除了之后 整个数据都会向前移动一位    因此第二个数据就在当前角标位置
                        double2 = numbers.remove(i);  // 即后一位（因为已经移除了 一位）
                    }
                    //判断是乘法还是 除法 做相应的 判断
                    double1 = (remove == '*' ? double1*double2 : double1/double2);
                    //计算完成 之后还需要再同一位置 加入运算数
                    System.out.println("numbers.remove(i=="+numbers);
                    if(i==numbers.size()){
                        numbers.add(double1);  // 当是最后一位 时  ；
                    }else{
                        numbers.add(i,double1);   //  在 同一位置 加入运算数
                    }

                    // 此时 i 需要减 1   因为 keystrings 已 运行 一次 --即 长度已 -1  （而 i 依旧再加 故需-1）；
                    i-=1;
                    System.out.println("_getNumberEnd=***=numbers=="+numbers);
                    System.out.println("_getNumberEnd=***=keystrings=="+keystrings);
                }
            }
            // 遍历 计算 加法 和 减法
            while(!keystrings.isEmpty()){
                // 一次运算  所以每次都是第一个运算符
                char op = keystrings.remove(0);
                // 需要在 同一位置 取出 对应的 运算数
                Double double1 = numbers.remove(0);
                Double double2 = Double.valueOf(0);
//                if(numbers.size()>0){
                if(numbers.size()>0){
                    // 因为 list 的 特性  移除了之后 整个数据都会向前移动一位    因此第二个数据就在当前角标位置
                    double2 = numbers.remove(0);  // 即后一位（因为已经移除了 一位）
                }
                System.out.println("--double1="+double1+"--double2"+double2);
                //判断是加法还是 减法 做相应的 判断
                double1 = (op == '-' ? double1-double2 : double1+double2);
                System.out.println("---+++double1----"+double1);
                //计算完成 之后还需要再同一位置 加入运算数
                numbers.add(0,double1);
                // 此时 i 需要减 1   因为 keystrings 已 运行 一次 --即 长度已 -1  （而 i 依旧再加 故需-1）；
                //i-=1;
            }
            // 计算完成之后   集合中还剩下一个元素  就是结果
            result = numbers.get(0);

            System.out.println("result-----"+result);

            numbers.clear();
            keystrings.clear();
            return result;
        }catch (NumberFormatException e){
            System.out.println("总计算异常");
        }
        numbers.clear();
        keystrings.clear();
        return result;
    }

    /**
     * */

    /** 获取 文本框的 内容 -- 无操作线程2秒自动计算-- 判断 运算符优先级 -- 获取结果      。     如果带括号 --只有一半时 自动计算线程休眠--当 括号完整 线程启动
     * */

    public static void main(String[] args) throws IOException {
        CalculatorTestJframe calculatorTestJframe = new CalculatorTestJframe();
        calculatorTestJframe.setVisible(true);
        calculatorTestJframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //System.out.println(Double.valueOf("."));//Exception in thread "main" java.lang.NumberFormatException: For input string: "."

//        File fr2 = new File("./src/com/one/swing/jframe/littleFunctions/index.html");
//        FileInputStream fileInputStream = new FileInputStream(fr2);
//        byte[] a2 = new byte[1024];
//      fileInputStream.read(a2,0,0);
//
//        //PrintWriter printWriter = new PrintWriter(new File("./src/com/one/swing/jframe/littleFunctions/index.html"));

        String a ="99.2";
        System.out.println(Double.valueOf(a));
        System.out.println(a.lastIndexOf(".")); // 2
        System.out.println(a.lastIndexOf("/")); // -1
        System.out.println(a.substring(a.length()-1));// 2
        System.out.println(a.substring(0,a.length()-1)); // 99.
        System.out.println(a.substring(0)); // 99.2
        System.out.println(a.substring(2)); // .2
        System.out.println(a.substring(2,3)); // .
        System.out.println(a.charAt(2)); // .

        char aa = '0';
        System.out.println("String.valueOf(\"0\")="+String.valueOf(aa));
        System.out.println("Double.valueOf(\"0\")="+Double.valueOf("0"));// 0.0
        String text = "6+0";
        System.out.println("0123456789.".indexOf(String.valueOf(text.charAt(2)))>0);  // false
        System.out.println("0123456789.".indexOf("0")>0);// false
        System.out.println("0123456789.".indexOf(".")>0);// true
        System.out.println("0123456789.".indexOf("1")>0);// true
        System.out.println("0123456789.".indexOf("4")>0);// true
        System.out.println("0123456789.".indexOf(0)>0);// false
        System.out.println("1234567890.".indexOf("0")>0);// true    -----
        System.out.println("1234567890.".indexOf("1")>0);// false   ----
        System.out.println("0123456789.".indexOf(text.charAt(2))>0); // false
        System.out.println(String.valueOf(text.charAt(2)).indexOf("0123456789.")>0); // false
        System.out.println(String.valueOf(text.charAt(2)).indexOf("0")>0); // false
        System.out.println("0".indexOf("0")>0);  //false
        System.out.println(" 0123456789.".indexOf("0")>0);// true      --加个空格

        List<String> list = new ArrayList<String>();
        list.add("9");
        list.add("");
        list.add(".");
        list.add("2");
        list.add("3.5");
        list.add("4");
        System.out.println(list.size()); // 6
        list.remove(list.size()-1);
        System.out.println(list);  // [9, , ., 2, 3.5]
        list.remove(3);
        list.add(3,"6");
        System.out.println(list);
        // 最后一位 不行
//        list.remove(5);
//        list.add(5,"6"); // java.lang.IndexOutOfBoundsException

        // 有序
//        List<String> list = new ArrayList<String>();
//        list.add("1");
//        list.add("2");
//        list.add("4");
//        list.add("6");
//        list.add("2");
//        System.out.println(list);
//        for(int i=0;i<list.size();i++){
//            System.out.println(list.get(i));
//        }
    }
}
