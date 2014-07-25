import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.*;

public class TicTacToeApp extends JApplet implements MouseListener {
    
    java.awt.Panel buttonsPanel;
    
	private String playerName = "X";
    private javax.swing.JLabel playerNumber;
    private JButton[] buttons;
    private JButton playAgain;

    
    public void init() {
        initComponents();
    }

    
    private void initComponents() {
    	
		JPanel windowContent = new JPanel();
		
		// Create the layout manager
		BorderLayout bl = new BorderLayout(); 
	    windowContent.setLayout(bl); 
	         
	    playAgain = new JButton("Play Again");
	    windowContent.add("South",playAgain);
        playAgain.addMouseListener(this);
	
	    //Create the buttons panel    
	   	buttonsPanel = new Panel();	
	 	Font buttonFont =new Font ("Times New Roman", Font.PLAIN,40);
	 	buttonsPanel.setLayout(new GridLayout(4,3));
	 	
        buttons = new JButton[9];
        for (int i=0; i<9; i++){
        	buttons[i]= new JButton();
        	buttons[i].addMouseListener(this);
        	buttons[i].setFont(buttonFont);
        	buttonsPanel.add(buttons[i]);
        }
	 	 	
	 	playerNumber = new JLabel(playerName, SwingConstants.CENTER);
		playerNumber.setText("Your turn!");
		buttonsPanel.add(playerNumber);
	    windowContent.add("Center",buttonsPanel);
	 	add(windowContent);
 	}             
		
    
	public void mouseClicked(MouseEvent e) {
		JButton currentButton = (JButton)e.getComponent();
		    
		if (currentButton == playAgain) {
			playAgain.setEnabled(false);
			reset();
		} 
		else if (currentButton.getText() == "" && currentButton.getText() != "Play Again"){
			currentButton.setText("X");
            checkForWinner();
            
            computerMove();
            checkForWinner();
		}      
	}

	
	private void reset() {
		for (JButton b : buttons) {
			b.setText("");
			b.setBackground(null);
		}

		playerNumber.setText("Your turn!");
	}
    

	private boolean findThreeInARow() {
		if (buttons[0].getText() == buttons[1].getText() 
				&& buttons[1].getText() == buttons[2].getText() 
				&& buttons[0].getText() != "") {
			setColorGreen(0, 1, 2);
			playerName = buttons[0].getText();
			return true;
		} 
		else if (buttons[3].getText() == buttons[4].getText()
				&& buttons[4].getText() == buttons[5].getText()
				&& buttons[3].getText() != "") {
			setColorGreen(3, 4, 5);
			playerName = buttons[3].getText();
			return true;
		} 
		else if (buttons[6].getText() == buttons[7].getText()
				&& buttons[7].getText() == buttons[8].getText()
				&& buttons[6].getText() != "") {
			setColorGreen(6, 7, 8);
			playerName = buttons[6].getText();
			return true;
		}
		else if (buttons[0].getText() == buttons[3].getText()
				&& buttons[3].getText() == buttons[6].getText()
				&& buttons[0].getText() != "") {
			setColorGreen(0, 3, 6);
			playerName = buttons[0].getText();
			return true;
		}
		else if (buttons[1].getText() == buttons[4].getText()
				&& buttons[4].getText() == buttons[7].getText()
				&& buttons[1].getText() != "") {
			setColorGreen(1, 4, 7);
			playerName = buttons[1].getText();
			return true;
		}
		else if (buttons[2].getText() == buttons[5].getText()
				&& buttons[5].getText() == buttons[8].getText()
				&& buttons[2].getText() != "") {
			setColorGreen(2, 5, 8);
			playerName = buttons[2].getText();
			return true;
		}
		else if (buttons[0].getText() == buttons[4].getText()
				&& buttons[4].getText() == buttons[8].getText()
				&& buttons[0].getText() != "") {
			setColorGreen(0, 4, 8);
			playerName = buttons[0].getText();
			return true;
		}
		else if (buttons[2].getText() == buttons[4].getText()
				&& buttons[4].getText() == buttons[6].getText()
				&& buttons[2].getText() != "") {
			setColorGreen(2, 4, 6);
			playerName = buttons[2].getText();
			return true;
		}
		else
			return false;
	}
	
	
	private void setColorGreen(int b1, int b2, int b3) {
		buttons[b1].setOpaque(true);
		buttons[b1].setBackground(Color.GREEN);
		buttons[b2].setOpaque(true);
		buttons[b2].setBackground(Color.GREEN);
		buttons[b3].setOpaque(true);
		buttons[b3].setBackground(Color.GREEN);
	}
	
	
	private void computerMove() {
		Random r = new Random();
		int i = r.nextInt(9);
		if ((!buttons[i].getText().equals("")) && (checkEmptySquare())) {
			computerMove();
		}
		else if(checkEmptySquare()) {
			buttons[i].setText("O");
		}
	}
	
	
	private boolean checkEmptySquare() {
		for (int i = 0; i < buttons.length; i++) {
			if(buttons[i].getText() == "")
			{
				return true;
			}
		}
		return false;
	}
	
 
    public void checkForWinner() {
        if(findThreeInARow()){
            String winnerName=(playerName == "X")?"Human":"Computer";
            playerNumber.setText(winnerName + " WON!");
            playAgain.setEnabled(true);       
        }
        else if(!checkEmptySquare()) {
        	playerNumber.setText("Try again!");
        	playAgain.setEnabled(true);
        }
    }
	

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

}
