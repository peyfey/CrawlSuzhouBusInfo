import java.awt.EventQueue;

import javax.swing.JFrame;

import edu.se.ustc.ClientWithResponseHandler;

import javax.swing.JButton;

import java.awt.BorderLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JTextField;
import javax.swing.JLabel;


public class MainWindow {

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private final int threadNum=100; //线程数量
    private JTextField textField_2;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainWindow window = new MainWindow();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public MainWindow() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        textField_1 = new JTextField();
        textField_1.setBounds(109, 24, 278, 21);
        frame.getContentPane().add(textField_1);
        textField_1.setColumns(10);
        
        JButton btnNewButton = new JButton("Start");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                run(true);
            }
        });
        btnNewButton.setBounds(45, 142, 93, 23);
        frame.getContentPane().add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Stop");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                run(false);
            }
        });
        btnNewButton_1.setBounds(294, 142, 93, 23);
        frame.getContentPane().add(btnNewButton_1);
        
        JLabel lblNewLabel = new JLabel("\u6587\u4EF6\u540D");
        lblNewLabel.setBounds(45, 27, 54, 15);
        frame.getContentPane().add(lblNewLabel);
        
        JButton btnNewButton_2 = new JButton("\u5220\u9664");
        btnNewButton_2.setBounds(186, 89, 67, 23);
        frame.getContentPane().add(btnNewButton_2);
        
        JButton btnNewButton_3 = new JButton("\u6DFB\u52A0");
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnNewButton_3.setBounds(109, 89, 67, 23);
        frame.getContentPane().add(btnNewButton_3);
        
        textField_2 = new JTextField();
        textField_2.setBounds(109, 58, 278, 21);
        frame.getContentPane().add(textField_2);
        textField_2.setColumns(10);
        
        JLabel lblurl = new JLabel("\u7EBF\u8DEFURL");
        lblurl.setBounds(45, 61, 54, 15);
        frame.getContentPane().add(lblurl);

    }
    
    
    public void run(boolean flag){
        ClientWithResponseHandler.setStartFlag(flag);
        String[] filename = new String[threadNum];
        String[] addr = new String[threadNum];
        ClientWithResponseHandler[] thread = new ClientWithResponseHandler[threadNum];
         //快线2号(独墅湖高教区首末站=>火车站)
        filename[0]="K2";
        addr[0] ="http://www.szjt.gov.cn/BusQuery/APTSLine.aspx?cid=175ecd8d-c39d-4116-83ff-109b946d7cb4&LineGuid=e0e5561a-32ea-432d-ac98-38eed8c4e448&LineInfo=%E5%BF%AB%E7%BA%BF2%E5%8F%B7(%E7%8B%AC%E5%A2%85%E6%B9%96%E9%AB%98%E6%95%99%E5%8C%BA%E9%A6%96%E6%9C%AB%E7%AB%99=%3E%E7%81%AB%E8%BD%A6%E7%AB%99)";
        // 146(启月街首末站=>虎丘首末站)
        filename[1]="146";
        addr[1]="http://www.szjt.gov.cn/BusQuery/APTSLine.aspx?cid=175ecd8d-c39d-4116-83ff-109b946d7cb4&LineGuid=ab9fdefc-a31d-461f-bbdf-030ee15d41e7&LineInfo=146(%E5%90%AF%E6%9C%88%E8%A1%97%E9%A6%96%E6%9C%AB%E7%AB%99=%3E%E8%99%8E%E4%B8%98%E9%A6%96%E6%9C%AB%E7%AB%99)";
        //166(新火车站北临时广场=>车坊首末站)
        filename[2]="166";
        addr[2]="http://www.szjt.gov.cn/BusQuery/APTSLine.aspx?cid=175ecd8d-c39d-4116-83ff-109b946d7cb4&LineGuid=09a1eb05-76a5-4633-813d-1b88b84f80ad&LineInfo=166(%E8%BD%A6%E5%9D%8A%E9%A6%96%E6%9C%AB%E7%AB%99)";
        //178(独墅湖高教区首末站=>火车站北广场首末站)
        filename[3]="178";
        addr[3]="http://www.szjt.gov.cn/BusQuery/APTSLine.aspx?cid=175ecd8d-c39d-4116-83ff-109b946d7cb4&LineGuid=2c5609cc-f7b2-4a88-a157-75e0d2bb5451&LineInfo=178(%E7%81%AB%E8%BD%A6%E7%AB%99%E5%8C%97%E5%B9%BF%E5%9C%BA%E9%A6%96%E6%9C%AB%E7%AB%99)";
        
        
        ExecutorService executor=Executors.newCachedThreadPool();
        for(int i=0; i<4; i++){
            thread[i]=new ClientWithResponseHandler(filename[i],addr[i]);
            executor.execute(thread[i]);
        }
    }
}
