package go;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class setting extends JFrame {
	JLabel lblTitle; // Ÿ��Ʋ
	JLabel lblName; // �̸�
	JLabel lblNumber; // ��ȣ
	JTextField tfName; // �̸� �Է�â
	JTextField tfNumber; // ��ȣ �Է�â
	JButton btnSave; // ���� ��ư
	JButton btnGo; // ���� ��ư
	
	public Font font2 = new Font("�ձٸ��", Font.PLAIN, 15);
	
	public int size, mine;
	
	// ��ư�� �������� ��������� �� â�� ������ Ŭ����
    public setting() {
    	init();
    	setDisplay();
    	showFrame();
    }
    public void init() {
    	setTitle("���� ��� â");
        // ����, ���⼭ setDefaultCloseOperation() ���Ǹ� ���� ���ƾ� �Ѵ�
        // �����ϰ� �Ǹ� �� â�� ������ ��� â�� ���α׷��� ���ÿ� ������
        
        JPanel NewWindowContainer = new JPanel();
        setContentPane(NewWindowContainer);
        
        JLabel NewLabel = new JLabel("��������");
        
        lblTitle = new JLabel("������� ���� ���� �Է��ϼ���.");
        lblTitle.setFont(font2);
        
		lblName = new JLabel("������ (1~32) ");
		lblName.setFont(font2);
		
		lblNumber = new JLabel("���� ��");
		lblNumber.setFont(font2);
		
		tfName = new JTextField(10);
		tfNumber = new JTextField(10);
		
		btnGo = new JButton("Go");
		btnGo.setFont(font2);

		btnGo.addActionListener(event -> {
			size = Integer.parseInt(tfName.getText());
			mine = Integer.parseInt(tfNumber.getText());
			//go ��ư ���� �� �����
			dispose();
		});
		
        setLocationRelativeTo(null);// �������� ȭ�� ����� ��ġ

    	
    }
    public int get_size() {
    	return this.size;
    }
    public int get_mine() {
    	return this.mine;
    }
    
    private void setDisplay() {
		
		JPanel pnlNorth = new JPanel();
		pnlNorth.add(lblTitle);
		pnlNorth.setBorder(new TitledBorder(""));
		
		JPanel pnlWest = new JPanel(new GridLayout(0,1,0,10));
		pnlWest.add(lblName);
		pnlWest.add(lblNumber);
		pnlWest.setBorder(new TitledBorder(""));
		
		JPanel pnlEast = new JPanel(new GridLayout(0,1,0,10));
		pnlEast.add(tfName);
		pnlEast.add(tfNumber);
		pnlEast.setBorder(new TitledBorder(""));
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnGo);
		pnlSouth.setBorder(new TitledBorder(""));
		
		add(pnlNorth, BorderLayout.NORTH);
		add(pnlWest, BorderLayout.WEST);
		add(pnlEast, BorderLayout.EAST);
		add(pnlSouth, BorderLayout.SOUTH);
	}

	private void showFrame() {
		setTitle("���� ����");
		pack();
		setLocationRelativeTo(null);
		setResizable(false); // â�� ����
		setVisible(true);
		
	}
}