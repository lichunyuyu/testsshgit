package com.one.swing.jframe.littleFunctions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by vtstar on 2018/1/16.
 */
/** 计算器  -- 输入之后就计算了  没有运算符优先级 */
public class CaculatorJFrame extends JFrame implements ActionListener{

    /** 计算器上的键 的显示名字    sqrt --开平方  9 sqrt = 3 */
    private final String[] KEYS = { "7", "8", "9", "/", "sqrt", "4", "5", "6",
            "*", "%", "1", "2", "3", "-", "1/x", "0", "+/-", ".", "+", "=" };
    /** 计算器上的功能键 */
    private final String[] COMMAND =  { "Backspace", "CE", "C" };
    /** 计算器左边的 M 的显示名字*/
    private final String[] M = {" ","MC","MR","MS","M+"};
    /** 计算器上 键的按钮 */
    private JButton keys[] = new JButton[KEYS.length];
    /** 计算器上的功能键的按钮 */
    private JButton commands[] = new JButton[COMMAND.length];
    /** 计算器左边的 M 的按钮 */
    private JButton m[] = new JButton[M.length];
    /** 计算器结果文本框 */
    private JTextField resultText = new JTextField("0");

    // 标志用户按的是否是 整个表达式的第一个数字，或者是 运算符 后 的 第一个数字
    private boolean firstDigit = true;
    // 计算中间结果
    private double resultNum = 0.0;
    // 当前运算的运算符
    private String operator = "=";
    // 操作是否合法
    private boolean operateValidFlag = true;

    /**
     * 构造函数
     * */
    public CaculatorJFrame(){
        super();
        // 初始化计算器
        initComponent();
        // 设置计算器的背景颜色
        this.setBackground(Color.CYAN);
        this.setTitle("计算器");
        // 在屏幕（500,300） 坐标处显示计算器
        this.setLocation(550,400);
        // 不需修改计算器的大小
        this.setResizable(false);
        // 使计算器中的各组件大小合适    --调整此窗口的大小,以适合其子组件的首选大小和布局
        this.pack();
    }
    /**
     * 初始化计算器
     * */
    private void initComponent(){
        // 文本框的内容采用 右对齐的方式
        resultText.setHorizontalAlignment(JTextField.RIGHT);
        // 不允许修改结果文本框
        resultText.setEditable(false);
        //设置文本框背景颜色为
        resultText.setBackground(new Color(171,197,167));
        //resultText.setBackground(new Color(0xABC5A7)); // 或者
        //设置文本框的大小
        resultText.setPreferredSize(new Dimension(300,40));

        // 初始化计算器上 键的按钮 ，将 键 放在一个 画板内
        JPanel calckeyPanel = new JPanel();
        // 使用网格布局器 ，4行 5列的网格，网格之间的水平方向间隔为3个像素，垂直方向间隔为3个像素
        calckeyPanel.setLayout(new GridLayout(4,5,3,3));
        for(int i=0;i<KEYS.length;i++){
            keys[i] = new JButton(KEYS[i]);
            calckeyPanel.add(keys[i]);
            keys[i].setForeground(Color.blue);
        }
        //运算符键用红色标示，其他键用蓝色标示
        keys[3].setForeground(Color.red);
        keys[8].setForeground(Color.red);
        keys[13].setForeground(Color.red);
        keys[18].setForeground(Color.red);
        keys[19].setForeground(Color.red);

        // 初始化 功能键 ，都用 绿色标示。 将功能键放在一个画板内
        JPanel commandsPanel = new JPanel();
        // 使用网格布局器 ，1行 3列的网格， 网格之间水平方向间隔为3个像素，垂直方向间隔为3个像素
        commandsPanel.setLayout(new GridLayout(1,3,3,3));
        for(int i=0;i<COMMAND.length;i++){
            commands[i] = new JButton(COMMAND[i]);
            commandsPanel.add(commands[i]);
            commands[i].setForeground(Color.green);
        }
        // 初始化 M 键，用橘色标示 ，将M 键放在一个画板内
        JPanel mpanel = new JPanel();
        // 使用网格布局器，5行 1列 ，网格之间水平方向间隔为3个像素，垂直方向间隔3个像素
        mpanel.setLayout(new GridLayout(5,1,3,3));
        for(int i=0;i<M.length;i++){
            m[i] = new JButton(M[i]);
            mpanel.add(m[i]);
            m[i].setForeground(Color.orange);
        }

        // 下面进行计算器的整体布局 ， 将calckeys和command画板放在计算器的中部，
        // 将文本框放在北部，将calms画板放在计算器的西部。

        // 新建一个大画板 ，将上面建立的command 和 calckeys  画板放在该画板内
        JPanel panel1 = new JPanel();
        //画板采用 边界布局管理器，画板里组件之间的水平 和 垂直方向上间隔都为3像素
        panel1.setLayout(new BorderLayout(3, 3));
        panel1.add("North",commandsPanel);
        panel1.add("West",calckeyPanel);

        //建立一个画板放 文本框
        JPanel topJpanel = new JPanel();
        topJpanel.setLayout(new BorderLayout());
        topJpanel.add("Center",resultText);

        //整体布局
        getContentPane().setLayout(new BorderLayout(3,5));
        getContentPane().add("North",topJpanel);
        getContentPane().add("Center",panel1);
        getContentPane().add("West",mpanel);
        //为各个按钮添加事件侦听器
        // 都使用同一个事件侦听器，即 本对象 . 本类的声明中有implements ActionListener
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
        // 获取事件源的标签
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
        } else if ("0123456789.".indexOf(label) >= 0) {
            // 用户按了数字键或者小数点键
            _handleNumber(label);
            // handlezero(zero);
        } else {
            // 用户按了运算符键
            _handleOperator(label);
        }
    }

    /**
     * 处理 Backspace 键 被按下的事件
     * */
    private  void _handleBackspace(){
        String text = resultText.getText();
        int i = text.length();
        if(i>0){
            // 退格 ，将文本最后一个字符去掉
            text = text.substring(0,i-1);
            if(text.length()==0){
                // 如果文本没有内容了 ，就初始化 计算器的各种值
                resultText.setText("0");
                firstDigit = true;
                operator = "=";
            }else{
                //显示新的文本
                resultText.setText(text);
            }
        }
    }

    /**
     * 处理 数字键被按下的事件
     * @parm key
     * */
    private void _handleNumber(String key){
        if(firstDigit){
            // 输入的第一个数字
            resultText.setText(key);
        }else if((key.equals(".")) && (resultText.getText().indexOf(".")<0)){
            // 输入的是小数点，并且之前没有小数点，则将小数点附在结果文本框的后面
            resultText.setText(resultText.getText() + ".");
        }else if(!key.equals(".")){
            //如果输入的不是小数点，则将数字附在结果文本框的后面
            resultText.setText(resultText.getText()+key);
        }
        // 以后输入的肯定不是第一个数字了
        firstDigit = false;
    }

    /**
     * 处理 C  键被按下的事件
     * */
    private void _handleC(){
        // 初始化计算器的各种值
        resultText.setText("0");
        firstDigit = true;
        operator = "=";
    }

    /**
     * 处理运算符键被按下的事件
     * @param key
     * */
    private void _handleOperator(String key){
        if (operator.equals("/")) {
            // 除法运算
            // 如果当前结果文本框中的值等于0
            if (_getNumberFromText() == 0.0) {
                // 操作不合法
                operateValidFlag = false;
                resultText.setText("除数不能为零");
            } else {
                resultNum /= _getNumberFromText();
            }
        } else if (operator.equals("1/x")) {
            // 倒数运算
            if (resultNum == 0.0) {
                // 操作不合法
                operateValidFlag = false;
                resultText.setText("零没有倒数");
            } else {
                resultNum = 1 / resultNum;
            }
        } else if (operator.equals("+")) {
            // 加法运算
            resultNum += _getNumberFromText();
        } else if (operator.equals("-")) {
            // 减法运算
            resultNum -= _getNumberFromText();
        } else if (operator.equals("*")) {
            // 乘法运算
            resultNum *= _getNumberFromText();
        } else if (operator.equals("sqrt")) {
            // 平方根运算
            resultNum = Math.sqrt(resultNum);
        } else if (operator.equals("%")) {
            // 百分号运算，除以100
            resultNum = resultNum / 100;
        } else if (operator.equals("+/-")) {
            // 正数负数运算
            resultNum = resultNum * (-1);
        } else if (operator.equals("=")) {
            // 赋值运算
            resultNum = _getNumberFromText();
        }
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
        firstDigit = true;
        operateValidFlag = true;
    }

    /**
     * 从结果文本框中获取数字
     *
     * @return
     */
    private double _getNumberFromText() {
        double result = 0;
        try {
            result = Double.valueOf(resultText.getText()).doubleValue();
        } catch (NumberFormatException e) {
        }
        return result;
    }


    public static void main(String args[]) {
        CaculatorJFrame calculator1 = new CaculatorJFrame();
        calculator1.setVisible(true);
        calculator1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



    /**
     * 不带括弧的核心算法
     * @param exp 表达式
     * @return	结果
     */
//    public static double calc(String exp) {
//        //1.把一个表达式中的运算子提取出来
//        List<Character> operations = getOperation(exp);
//        //2.把一个表达式中的数提取出来
//        List<Double> numbers = getNumbers(exp);
//
//        //遍历计算(乘法和除法)
//        for (int i = 0; i < operations.size(); i++) {
//            //遍历获取运算子
//            char op = operations.get(i);
//            //如果这个运算子是乘法或者除法
//            if (op == '*' || op == '/') {
//                //移除这个运算子
//                Character remove = operations.remove(i);
//                //需要在同一个位置取出对应的运算数
//                Double double1 = numbers.remove(i);
//                //因为list的特性 移除了之后整个数据会向前移动一位 		因此第二个数据就在当前的角标位置
//                Double double2 = numbers.remove(i);
//                //判断是乘法还是除法	做相应的运算
//                double1 = (remove == '*' ? double1*double2 : double1/double2);
//                //计算完成之后 还需要在同一个位置插入运算数
//                numbers.add(i,double1);
//            }
//        }
//        //遍历计算加法和减法
//        while (!operations.isEmpty()) {
//            //一次计算 所以每次都是第一个运算符
//            char op = operations.remove(0);
//            //需要在同一个位置取出对应的运算数
//            Double double1 = numbers.remove(0);
//            //因为list的特性 移除了之后整个数据会向前移动一位 		因此第二个数据就在当前的角标位置
//            Double double2 = numbers.remove(0);
//            //判断是乘法还是除法	做相应的运算
//            double1 = (op == '-' ? double1-double2 : double1+double2);
//            //计算完成之后 还需要在同一个位置插入运算数
//            numbers.add(0,double1);
//        }
//
//        //计算完了以后 集合中还会剩下一个元素 就是结果
//        return numbers.get(0);
//    }




}
