/*
size*sizeĭ����
���� ��

���� ��ư ������:
	�Լ�:����
	�ʽð� �۵�
	
	�Է¹�����(x,y):
		�Լ�: ����(x,y)
		
---
�Լ� -����-(x,y):
	(x,y)�ڸ� ���� ������(z = -1): �� ����, ���ӿ���
	�ֺ��� ���� ������(z = 0):
		�Լ�: ����(x-1,y-1)
		�Լ�: ����(x-1,y)
		�Լ�: ����(x-1,y+1)
		�Լ�: ����(x,y-1)
		�Լ�: ����(x,y+1)
		�Լ�: ����(x+1,y-1)
		�Լ�: ����(x+1,y)
		�Լ�: ����(x+1,y+1)
	�ֺ��� ���� ������(z>0):
		���� ������
---
�Լ� -����-:
	(1,1)���� (size,size)���� ���� �������� ��ġ
	(0,0)���� (size+1,size+1)���� (x,y,z) �迭 arr ����
	
	arr �迭�� ����://���� ����
		���� (x,y)�� ���ڸ�:
			z = -1
		���� (x,y)�� ���ڰ� �ƴϸ�:
			(x-1,y-1)�� ���ڸ�: z++
			(x-1,y)�� ���ڸ�: z++
			(x-1,y+1)�� ���ڸ�: z++
			(x,y-1)�� ���ڸ�: z++
			(x,y+1)�� ���ڸ�: z++
			(x+1,y-1)�� ���ڸ�: z++
			(x+1,y)�� ���ڸ�: z++
			(x+1,y+1)�� ���ڸ�: z++
	

 */


package go;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;


public class first {
	//-----------������----------------------
	public boolean play = false;
	public int size = 32;//size*size ĭ����
	public int m = 100;//���� ��
	//(0,0)���� (size+1,size+1)���� (x,y,z) �迭 arr ����
	public int[][] arr = new int[size + 2][size + 2];
	public boolean[][] revealed = new boolean[size + 2][size + 2];
	public Random random = new Random();
	public JButton[] btns = new JButton[size * size + 1];
	public JLabel[] lab = new JLabel[size * size + 1];
	
	public JLabel lblName; // ũ��
	public JLabel lblNumber; // ���� ��
	public JTextField size_in; // ũ�� �Է�â
	public JTextField mine_in; // ���� �� �Է�â
	
	public JButton b_start;
	
	public setting set;
	public boolean restart = false;
	
	public int maybeM = 0;
	public int clearCount = 0;
	
	// �̹��� ������
	public ImageIcon img_start = new ImageIcon(first.class.getResource("/go/img/start.jpg"));
	public ImageIcon img_start2 = new ImageIcon(first.class.getResource("/go/img/start2.jpg"));
	public ImageIcon img_start3 = new ImageIcon(first.class.getResource("/go/img/start3.jpg"));
	public ImageIcon img_btn1 = new ImageIcon(first.class.getResource("/go/img/button1.jpg"));
	public ImageIcon img_btn2 = new ImageIcon(first.class.getResource("/go/img/button2.jpg"));
	public ImageIcon img_btn3 = new ImageIcon(first.class.getResource("/go/img/button3.jpg"));
	public ImageIcon img_btn4 = new ImageIcon(first.class.getResource("/go/img/button4.jpg"));
	public JFrame frm = new JFrame("���־� ����� ����ã��");// ������ ����	
	public JLayeredPane jlp = frm.getLayeredPane();
	public JLabel l_start, l_mine;
	
	public static first window;
	
	public Font font = new Font("�ձٸ��", Font.PLAIN, 30);
	public Font font2 = new Font("�ձٸ��", Font.PLAIN, 10);
	
	public first() {
		init();
	}
	
	public static void main(String[] args) {
		window = new first();
	}
	
	public void btn_create() {
		for(int i = 0; i < btns.length; i++) {
			btns[i] = null;
		}
        for(int x = 1; x <= size; x++) {
    		for(int y = 1; y <= size; y++) {
    			int index = (x - 1) * (size) + y;
    			int bx = x;
    			int by = y;
    			btns[index] = new JButton(img_btn1);
    			btns[index].setPressedIcon(img_btn2);
    			btns[index].setBounds(100 + 17 * bx, 200 + 17 * by, 17, 17);
    			jlp.add(btns[index],0);
//    			// ��ư ������ ��
//    			btns[index].addActionListener(event -> {
//    				push(bx, by);
//    				show2();
//    			});
    			
    			btns[index].addMouseListener(new MouseAdapter() {
    				public void mouseClicked(MouseEvent e) {
    					if (e.getButton() == 3) { // if right click
    						pushR(bx ,by);
    					} else if(btns[index].isEnabled()) { //left click
    						push(bx, by);
    					}
    				}
    			});
    		}
    	}
	}
	
	public void jframe() {

		frm.setSize(1000,1000);// ������ ũ�� ����	
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// �������� �ݾ��� �� �޸𸮿��� ���ŵǵ��� ����
        frm.getContentPane().setLayout(null);// ���̾ƿ�
        
        // �� ����
        
        //����ã�� ����!
        l_start = new JLabel();
        l_start.setBounds(200, 20, 500, 50);
        l_start.setHorizontalAlignment(JLabel.CENTER);
        frm.getContentPane().add(l_start);
        
        //���� ���� ����
        l_mine = new JLabel();
        l_mine.setBounds(500, 20, 500, 50);
        l_mine.setHorizontalAlignment(JLabel.CENTER);
        frm.getContentPane().add(l_mine);
        
      	// �� ���� ��ư ����
        b_start = new JButton(img_start);
        b_start.setRolloverIcon(img_start3);
        b_start.setPressedIcon(img_start2);
        b_start.setBounds(200, 100, 128, 64);// ��ư ��ġ, ũ�� ����
        frm.getContentPane().add(b_start);// �����ӿ� ��ư �߰�
        
        
        // ���� ��ư ������ ��
        b_start.addActionListener(event -> {
        	if(restart) {
        		frm.dispose();
        		window = new first();
        	} else {
        		restart = true;
        		
        		this.size = set.get_size();
        	    this.m = set.get_mine();
        	    
        		l_start.setText("����ã�� ����!");
        		l_start.setFont(font);
        		
        		l_mine.setText("size: " + size + "x" + size + " / mine: " + (m - maybeM));
        		l_mine.setFont(font2);
	        	btn_create();
	        	start();
	        	//�ʽð� �۵�
	        	
	        	System.out.println("���־� �����");
				System.out.println("ũ��: " + size + ", ���� ��: " + m);
        	}
        	
        	
        });

		// �������� ���̵��� ����
		frm.setVisible(true);
	}

	public void init() {
		jframe();
		
		set = new setting(); // Ŭ���� newWindow�� ���� ������
    	this.size = set.get_size();
	    this.m = set.get_mine();
	}

	//������ �� �Լ�
	public void start() {
		play = true;
		
		//(1,1)���� (size,size)���� ���� �������� ��ġ
		ArrayList<Integer> mines = new ArrayList<Integer>();
		while(mines.size() < m) {
			int mine = random.nextInt(size*size);
			if(!mines.contains(mine)) {
				mines.add(mine);
			}
			arr[(mine / size) + 1][(mine % size) + 1] = -1;
			System.out.print("size: " + mines.size()+  "mine: (" + (mine / size + 1) + ",");
			System.out.println((mine % size + 1) + ")");
		}
		
		//arr �迭�� ����://���� ����
		for(int x = 1; x <= size; x++) {
			for(int y = 1; y <= size; y++) {
				//���� (x,y)�� ���ڰ� �ƴϸ�:
				if(arr[x][y] > -1) {
					//(x-1,y-1)�� ���ڸ�: z++
					if(arr[x-1][y-1] == -1) {arr[x][y] ++;}
					if(arr[x-1][y] == -1) {arr[x][y] ++;}
					if(arr[x-1][y+1] == -1) {arr[x][y] ++;}
					if(arr[x][y-1] == -1) {arr[x][y] ++;}
					if(arr[x][y+1] == -1) {arr[x][y] ++;}
					if(arr[x+1][y-1] == -1) {arr[x][y] ++;}
					if(arr[x+1][y] == -1) {arr[x][y] ++;}
					if(arr[x+1][y+1] == -1) {arr[x][y] ++;}
				}
			}
		}
	}
	
	//���콺 Ŭ�� �� �Լ�
	public void push(int x, int y) {
		System.out.print("@@@pushed- x: " + x + ",y: " + y);
		int index = (x - 1) * (size) + y;
		if(x >= 1 && y >= 1 && x <= size && y <= size && index < btns.length && !revealed[x][y]) {
			//��ư �����
			reveal(x,y);
			revealed[x][y] = true;
			//(x,y)�ڸ� ���� ������(z = -1): �� ����, ���ӿ���
			
			if(arr[x][y] == -1) {
				btns[index].setIcon(img_btn3);
				gameOver();
			}
	//		�ֺ��� ���� ������(z = 0):
			else if(arr[x][y] == 0) {
				push(x-1,y-1);
				push(x-1,y);
				push(x-1,y+1);
				push(x,y-1);
				push(x,y+1);
				push(x+1,y-1);
				push(x+1,y);
				push(x+1,y+1);
			}
	//		�ֺ��� ���� ������(z>0):
			else if(arr[x][y] > 0) {
				//���� ������
				
				// �� ����
		        lab[index] = new JLabel();
		        lab[index].setBounds(100 + 17 * x, 200 + 17 * y, 17, 17);
		        
		        lab[index].setHorizontalAlignment(JLabel.CENTER);
		        jlp.add(lab[index],new Integer(200));
		        lab[index].setText(Integer.toString(arr[x][y]));
		        
				System.out.println("around mine: " + (arr[x][y]));
			}
			if(clearCount >= (size * size) - m) {
				clear();
			}
		}
	}
	
	//���콺 ��Ŭ���� �Լ�
	public void pushR(int x, int y) {
		int index = (x - 1) * (size) + y;
		if(btns[index].isEnabled()) {
			btns[index].setIcon(img_btn4);
			btns[index].setEnabled(false);
			maybeM ++;
		} else {
			btns[index].setIcon(img_btn1);
			btns[index].setEnabled(true);
			maybeM --;
		}
		l_mine.setText("size: " + size + "x" + size + " / mine: " + (m - maybeM));
	}
	
	//Ŭ����
	public void clear() {
		play = false;

        JLabel l_clear = new JLabel();
        l_clear.setBounds(600, 20, 500, 50);
        l_clear.setHorizontalAlignment(JLabel.CENTER);
        frm.getContentPane().add(l_clear);
        
        l_clear.setText("clear!");
        l_clear.setFont(font2);
		
		open();
		System.out.println("clear!");
	}
	
	//���ӿ���
	public void gameOver() {
		play = false;
		
		//���ӿ���
        JLabel l_gameover = new JLabel();
        l_gameover.setBounds(200, 150, 600, 300);
        l_gameover.setHorizontalAlignment(JLabel.CENTER);
        frm.getContentPane().add(l_gameover);
        
		l_gameover.setText("Game Over!");
		l_gameover.setFont(font);
		
		open();
		System.out.println("Game Over!");
	}
	
	//�ش�κ� ���� �巯��
	public void reveal(int x, int y) {
		int index = (x - 1) * (size) + y;
		
		System.out.println("index: " + index);
		System.out.println("x, y: " + x + "/" + y);
		System.out.println("btns[index]" + btns[index]);
		btns[index].setIcon(img_btn2);
		clearCount += 1;
		System.out.println("->reveal: " + x + ", " + y);
	}
	
	//�� ��ü �����ֱ�
	public void show() {
		System.out.println();
		for(int y = 1; y <= size; y++) {
			for(int x = 1; x <= size; x++) {
				if(revealed[x][y]) {//�̹� ���
					System.out.printf("%2d",arr[x][y]);
				} else {
					System.out.printf("%2c",'O');
				}
			}
			System.out.println();
		}
	}
	
	public void show2() {
		System.out.println();
		
		for(int y = 1; y <= size; y++) {
			for(int x = 1; x <= size; x++) {
				int index = (x - 1) * (size) + y;
				System.out.printf("%2d",index);
			}
			System.out.println();
		}
	}
	
	public void open() {
		for(int x = 1; x <= size; x++) {
			for(int y = 1; y <=size; y++) {
				int index = (x - 1) * (size) + y;
				
				reveal(x,y);
				revealed[x][y] = true;
				
				if(arr[x][y] == -1) {
					btns[index].setIcon(img_btn3);
				} else if(arr[x][y] > 0) {
					// �� ����
			        lab[index] = new JLabel();
			        lab[index].setBounds(100 + 17 * x, 200 + 17 * y, 17, 17);
			        
			        lab[index].setHorizontalAlignment(JLabel.CENTER);
			        jlp.add(lab[index],new Integer(200));
			        lab[index].setText(Integer.toString(arr[x][y]));
			        
					System.out.println("around mine: " + (arr[x][y]));
				}
				btns[index].setEnabled(false);
			}	
		}
	}
}



