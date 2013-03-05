package com.adslur.szt.dialog;



import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;


import com.jgoodies.animation.*;
import com.jgoodies.animation.animations.BasicTextAnimation;
import com.jgoodies.animation.animations.BasicTextAnimations;
import com.jgoodies.animation.components.BasicTextLabel;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;


public class AboutDialog extends JDialog {

    private BasicTextLabel label1;
    private BasicTextLabel label2;

    private Action animateAction;

	public AboutDialog(Frame parent, String title) {
		super(parent, title, true);

		if (title == null) {
			setTitle("");
		}
		if (parent != null) {
			setLocationRelativeTo(parent);
		}
	}

	public AboutDialog(Frame parent) {
		this(parent, null);
	}

	public AboutDialog() {
		this(null, null);
	}

	public void dialogInit() {
                                           initComponents();


		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		panel2.setBorder(BorderFactory.createEmptyBorder(20, 5, 5, 5));
                                        JLabel logoLabel = new JLabel(new ImageIcon("Images/SZTelecom.GIF"));

		panel2.add(logoLabel);
		JLabel textLabel1 = new JLabel("АРМ-СУЗ(юр.лица)");
		panel2.add(textLabel1);
		JLabel textLabel2 = new JLabel(
				"Copyright (c) 2010 Северо-Западный Телеком");
		panel2.add(textLabel2);
		JLabel textLabel3 = new JLabel("Web site http://www.avangard-dsl.ru");
		panel2.add(textLabel3);
                              
                                       panel1.add(buildPanel());
                                     



		JPanel panel3 = new JPanel();
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
		panel3.add(Box.createHorizontalGlue());

		panel3.add(panel2);
                                        

		panel3.add(Box.createHorizontalGlue());

		super.dialogInit();
                                      
		Container content = this.getContentPane();
		content.add(panel1, BorderLayout.NORTH);
		content.add(panel3, BorderLayout.SOUTH);
		pack();
                                    
       
       

                                         Animation animation = createAnimation();
                                                                         int fps = 30;
                                       animation.addAnimationListener(new StartStopHandler());
                                       new Animator(animation, fps).start();

	}

	public void showDialog() {
		setVisible(true);
        
	}

 ////////////////////
      private void initComponents() {
        Font font = new Font("Tahoma", Font.ITALIC, 15);
        label1 = new BasicTextLabel(" ");
        label1.setFont(font);
        //label1.setBounds(0, 0, 350, 100);
        label1.setOpaque(false);

        label2 = new BasicTextLabel(" ");
        label2.setFont(font);
        //label2.setBounds(0, 0, 350, 100);
        label2.setOpaque(false);
        
        animateAction = new AnimateAction();
    }
    

       private Animation createAnimation() {
        Animation welcome =
            BasicTextAnimation.defaultFade(
                label1,
                2500,
                "Вас приветствует   Avangard ADSL ",
                Color.RED);

        Animation theJGoodiesAnimation =
            BasicTextAnimation.defaultFade(
                label1,
                3000,
                "С помощью этой программы",
                Color.BLUE);

       Animation list =
            BasicTextAnimation.defaultFade(
                label1,
                3000,
                "Вы сможете :",
                Color.BLUE);

        Animation description =
            BasicTextAnimations.defaultFade(
                label1,
                label2,
                2000,
                -100,
                "1. Cформировать заявку на подлключение ADSL|" +
                "временно забронировать оборудование|постоянно забронировать оборудование.",
                Color.BLUE);

        Animation features =
            BasicTextAnimations.defaultFade(
                label1,
                label2,
                3000,
                500,
                "2.Проверять  наличие ограничений на  поключения:",
                Color.BLUE);

        Animation featureList =
            BasicTextAnimations.defaultFade(
                label1,
                label2,
                1750,
                0,
                "Наличие аппаратуры уплотнения|Наличие охранной сигнализации",
                Color.BLUE);


          Animation tesh =
            BasicTextAnimations.defaultFade(
                label1,
                label2,
                3000,
                500,
                "3.Проверять  техническую возможность подключения:",
                Color.BLUE);

        Animation teshList =
            BasicTextAnimations.defaultFade(
                label1,
                label2,
                1750,
                0,
                "Наличие наличие оборудования на станции|Наличие свободных портов на станции",
                Color.BLUE);

         Animation demand =
            BasicTextAnimations.defaultFade(
                label1,
                label2,
                3000,
                500,
                "4.Просматривать заявки на подкючение услуги ADSL:",
                Color.BLUE);

        Animation demandList =
            BasicTextAnimations.defaultFade(
                label1,
                label2,
                1750,
                0,
                "Статус заявки |И многое другое",
                Color.BLUE);


      Animation demandEnd =
            BasicTextAnimations.defaultFade(
                label1,
                label2,
                1750,
                0,
                "Благодарю за внимание !",
                 Color.RED);

        Animation all =
            Animations.sequential(
                new Animation[] {
                    Animations.pause(500),
                    welcome,
                    Animations.pause(500),
                    theJGoodiesAnimation,
                    Animations.pause(1000),
                     list,
                    Animations.pause(1000),
                    description,
                    Animations.pause(1000),
                    features,
                    Animations.pause(1000),
                    featureList,
                    Animations.pause(1500),
                    tesh,
                    Animations.pause(1000),
                    teshList,
                    Animations.pause(1500),   
                     demand,
                    Animations.pause(1000),
                    demandList,
                    Animations.pause(1500),   
                    demandEnd,
                    Animations.pause(1500),   });


        return all;
    }
    

      private final class AnimateAction extends AbstractAction {
        
        private AnimateAction() {
            super("Animate");
        }
          
        public void actionPerformed(ActionEvent e) {
            Animation animation = createAnimation();
            int fps = 30;
            animation.addAnimationListener(new StartStopHandler());
            new Animator(animation, fps).start();
        }  
       
    }
    
  
    private final class StartStopHandler extends AnimationAdapter {
        
        public void animationStarted(AnimationEvent e) {
            animateAction.setEnabled(false);
        }
        
        public void animationStopped(AnimationEvent e) {
            animateAction.setEnabled(true);
        }
    }


      private JComponent buildPanel() {
        FormLayout layout = new FormLayout(
                "fill:pref:grow",
                "fill:pref:grow, p, p");
        
        PanelBuilder builder = new PanelBuilder(layout);
        CellConstraints cc = new CellConstraints();
        builder.setBorder(BorderFactory.createEmptyBorder(20, 5, 5, 5));
        builder.addSeparator("",         cc.xy(1, 1));
        builder.add(buildAboutPanel(), cc.xy(1, 2));
        builder.addSeparator("",         cc.xy(1, 3));
       
        return builder.getPanel();
    }
     


    public JPanel buildAboutPanel() {
        FormLayout layout = new FormLayout(
                "fill:200dlu:grow",
                "fill:100dlu:grow");
        JPanel panel = new JPanel(layout);
        CellConstraints cc = new CellConstraints();
        panel.setBackground(Color.WHITE);
           
        panel.add(label1, cc.xy(1, 1));
        panel.add(label2, cc.xy(1, 1));
          
        return panel;
    }


//////////////////

                  public static void main(String args[]) {
	JFrame frame = new JFrame("Fredy Browser");
	frame.addWindowListener(new WindowAdapter() {
	    public void windowActivated(WindowEvent e) {}
	    public void windowClosed(WindowEvent e) {}
	    public void windowClosing(WindowEvent e) {System.exit(0);}
	    public void windowDeactivated(WindowEvent e) {}
	    public void windowDeiconified(WindowEvent e) {}
	    public void windowIconified(WindowEvent e) {}
	    public void windowOpened(WindowEvent e) {}});
	
	AboutDialog browser = new AboutDialog(frame );
	browser.dialogInit();
	//frame.getContentPane().add(browser);
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                     frame.setSize(1000,1000);
          
	frame.setVisible(true);
                    browser.showDialog()  ;
                 
    }
}