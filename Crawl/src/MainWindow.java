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
        textField_1.setBounds(109, 24, 128, 21);
        frame.getContentPane().add(textField_1);
        textField_1.setColumns(10);
        
        JButton btnNewButton = new JButton("Start");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                run(true);
            }
        });
        btnNewButton.setBounds(50, 202, 93, 23);
        frame.getContentPane().add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Stop");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                run(false);
            }
        });
        btnNewButton_1.setBounds(287, 202, 93, 23);
        frame.getContentPane().add(btnNewButton_1);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setBounds(45, 27, 54, 15);
        frame.getContentPane().add(lblNewLabel);
        
        JButton btnNewButton_2 = new JButton("New button");
        btnNewButton_2.setBounds(252, 23, 62, 23);
        frame.getContentPane().add(btnNewButton_2);
        
        JButton btnNewButton_3 = new JButton("New button");
        btnNewButton_3.setBounds(320, 23, 67, 23);
        frame.getContentPane().add(btnNewButton_3);

        
    }
    
    
    public void run(boolean flag){
        ClientWithResponseHandler.setStartFlag(flag);
        String addr ="http://www.szjt.gov.cn/BusQuery/APTSLine.aspx?cid=175ecd8d-c39d-4116-83ff-109b946d7cb4&LineGuid=e0e5561a-32ea-432d-ac98-38eed8c4e448&LineInfo=%E5%BF%AB%E7%BA%BF2%E5%8F%B7(%E7%8B%AC%E5%A2%85%E6%B9%96%E9%AB%98%E6%95%99%E5%8C%BA%E9%A6%96%E6%9C%AB%E7%AB%99=%3E%E7%81%AB%E8%BD%A6%E7%AB%99)";
        ClientWithResponseHandler thread1=new ClientWithResponseHandler("K2",addr);
        
        ExecutorService executor=Executors.newCachedThreadPool();
        executor.execute(thread1);
    }
    
}
